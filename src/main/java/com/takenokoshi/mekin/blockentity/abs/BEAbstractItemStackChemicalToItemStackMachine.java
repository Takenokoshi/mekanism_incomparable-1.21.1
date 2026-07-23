package com.takenokoshi.mekin.blockentity.abs;

import java.util.List;
import java.util.function.Consumer;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.jerry.mekextras.api.ExtraUpgrade;
import com.takenokoshi.mekaddonlib.blockentity.interfaces.IWarningSupporter;
import com.takenokoshi.mekaddonlib.inventory.slot.LimitChangedOutputInventorySlot;
import com.takenokoshi.mekut.blockentity.interfaces.IHasInputChemicalTank;
import com.takenokoshi.mekut.blockentity.interfaces.IHasMachineEnergyContainer;
import com.takenokoshi.mekut.blockentity.interfaces.IRecipeViewerTypeProvider;
import com.takenokoshi.mekut.blockentity.interfaces.IScaledProgressProvider;
import com.takenokoshi.mekut.inventory.slot.ChemicalFillConvertOrSupplyingSlot;
import com.takenokoshi.mekut.inventory.slot.InputOrSupplyingSlot;
import com.takenokoshi.mekut.recipe.cached.AdvancedItemStackChemicalToItemStackCachedRecipe;
import com.takenokoshi.mekut.recipe.input.AdvancedChemicalInputHandler;
import com.takenokoshi.mekut.recipe.input.AdvancedItemInputHandler;

import mekanism.api.IContentsListener;
import mekanism.api.Upgrade;
import mekanism.api.chemical.BasicChemicalTank;
import mekanism.api.chemical.IChemicalTank;
import mekanism.api.math.MathUtils;
import mekanism.api.recipes.ItemStackChemicalToItemStackRecipe;
import mekanism.api.recipes.cache.CachedRecipe;
import mekanism.api.recipes.cache.CachedRecipe.OperationTracker.RecipeError;
import mekanism.api.recipes.outputs.IOutputHandler;
import mekanism.api.recipes.outputs.OutputHelper;
import mekanism.client.recipe_viewer.type.IRecipeViewerRecipeType;
import mekanism.common.attachments.containers.ContainerType;
import mekanism.common.attachments.containers.chemical.ChemicalTanksBuilder;
import mekanism.common.attachments.containers.item.ItemSlotsBuilder;
import mekanism.common.capabilities.energy.MachineEnergyContainer;
import mekanism.common.capabilities.holder.chemical.ChemicalTankHelper;
import mekanism.common.capabilities.holder.chemical.IChemicalTankHolder;
import mekanism.common.capabilities.holder.energy.EnergyContainerHelper;
import mekanism.common.capabilities.holder.energy.IEnergyContainerHolder;
import mekanism.common.capabilities.holder.slot.IInventorySlotHolder;
import mekanism.common.capabilities.holder.slot.InventorySlotHelper;
import mekanism.common.config.MekanismConfig;
import mekanism.common.inventory.container.MekanismContainer;
import mekanism.common.inventory.container.slot.SlotOverlay;
import mekanism.common.inventory.container.sync.SyncableLong;
import mekanism.common.inventory.slot.EnergyInventorySlot;
import mekanism.common.inventory.warning.WarningTracker.WarningType;
import mekanism.common.lib.transmitter.TransmissionType;
import mekanism.common.recipe.lookup.IDoubleRecipeLookupHandler;
import mekanism.common.registration.impl.ItemRegistryObject;
import mekanism.common.tile.component.TileComponentEjector;
import mekanism.common.tile.prefab.TileEntityProgressMachine;
import net.minecraft.SharedConstants;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public abstract class BEAbstractItemStackChemicalToItemStackMachine
        extends TileEntityProgressMachine<ItemStackChemicalToItemStackRecipe>
        implements IDoubleRecipeLookupHandler.ItemChemicalRecipeLookupHandler<ItemStackChemicalToItemStackRecipe>,
        IHasMachineEnergyContainer, IHasInputChemicalTank, IScaledProgressProvider, IWarningSupporter,
        IRecipeViewerTypeProvider {

    public static Consumer<ItemRegistryObject<?>> getContainerAdder(long chemicalTankCapacity) {
        return value -> {
            value.addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                    .addInput(2)
                    .addOutput()
                    .addEnergy()
                    .build());
            value.addAttachmentOnlyContainers(ContainerType.CHEMICAL, () -> ChemicalTanksBuilder.builder()
                    .addBasic(chemicalTankCapacity)
                    .build());
        };
    }

    private static final List<RecipeError> TRACKED_ERROR_TYPES = List.of(
            RecipeError.NOT_ENOUGH_ENERGY,
            RecipeError.NOT_ENOUGH_INPUT,
            RecipeError.NOT_ENOUGH_SECONDARY_INPUT,
            RecipeError.NOT_ENOUGH_OUTPUT_SPACE,
            RecipeError.INPUT_DOESNT_PRODUCE_OUTPUT);
    public static final int BASE_TICKS_REQUIRED = 10 * SharedConstants.TICKS_PER_SECOND;

    protected long clientEnergyUsed = 0;
    private double chemicalUsageModifier = 1.0d;

    protected final int baselineMaxOperations;
    private int stackUpgrade;

    protected MachineEnergyContainer<?> energyContainer;
    protected IChemicalTank chemicalTank;

    protected InputOrSupplyingSlot inputSlot;
    protected LimitChangedOutputInventorySlot outputSlot;
    protected ChemicalFillConvertOrSupplyingSlot chemicalSlot;
    protected EnergyInventorySlot energySlot;

    protected final AdvancedItemInputHandler itemInputHandler;
    protected final AdvancedChemicalInputHandler chemicalInputHandler;
    protected final IOutputHandler<ItemStack> outputHandler;

    protected BEAbstractItemStackChemicalToItemStackMachine(Holder<Block> blockProvider, BlockPos pos, BlockState state,
            int baseTicksRequired, int baselineMaxOperations) {
        super(blockProvider, pos, state, TRACKED_ERROR_TYPES, baseTicksRequired);
        configComponent.setupItemIOExtraConfig(inputSlot, outputSlot, chemicalSlot, energySlot);
        configComponent.setupInputConfig(TransmissionType.CHEMICAL, chemicalTank);
        configComponent.setupInputConfig(TransmissionType.ENERGY, energyContainer);
        ejectorComponent = new TileComponentEjector(this).setOutputData(configComponent, TransmissionType.ITEM);
        this.baselineMaxOperations = baselineMaxOperations;
        this.itemInputHandler = AdvancedItemInputHandler.create(inputSlot, RecipeError.NOT_ENOUGH_INPUT);
        this.chemicalInputHandler = AdvancedChemicalInputHandler.create(chemicalTank,
                RecipeError.NOT_ENOUGH_SECONDARY_INPUT);
        this.outputHandler = OutputHelper.getOutputHandler(outputSlot, RecipeError.NOT_ENOUGH_OUTPUT_SPACE);
    }

    @Override
    protected @Nullable IChemicalTankHolder getInitialChemicalTanks(IContentsListener listener,
            IContentsListener recipeCacheListener, IContentsListener recipeCacheUnpauseListener) {
        ChemicalTankHelper builder = ChemicalTankHelper.forSideWithConfig(this);
        builder.addTank(chemicalTank = BasicChemicalTank.inputModern(initChemicalTankCapacity(),
                (stack) -> containsRecipeBA(itemInputHandler.getInput(), stack),
                this::containsRecipeB,
                recipeCacheListener));
        return builder.build();
    }

    protected abstract long initChemicalTankCapacity();

    @NotNull
    @Override
    protected IEnergyContainerHolder getInitialEnergyContainers(IContentsListener listener,
            IContentsListener recipeCacheListener, IContentsListener recipeCacheUnpauseListener) {
        EnergyContainerHelper builder = EnergyContainerHelper.forSideWithConfig(this);
        builder.addContainer(energyContainer = MachineEnergyContainer.input(this, recipeCacheUnpauseListener));
        return builder.build();
    }

    @Override
    protected @Nullable IInventorySlotHolder getInitialInventory(IContentsListener listener,
            IContentsListener recipeCacheListener, IContentsListener recipeCacheUnpauseListener) {
        InventorySlotHelper builder = InventorySlotHelper.forSideWithConfig(this);
        builder.addSlot(inputSlot = InputOrSupplyingSlot.at(
                (stack) -> containsRecipeAB(stack, chemicalInputHandler.getInput()),
                this::containsRecipeA,
                recipeCacheListener, initInputSlotX(), initInputSlotY(), initItemSlotCapacity()))
                .tracksWarnings(slot -> slot.warning(WarningType.NO_MATCHING_RECIPE,
                        getWarningCheck(RecipeError.NOT_ENOUGH_INPUT)));
        builder.addSlot(chemicalSlot = ChemicalFillConvertOrSupplyingSlot.create(
                chemicalTank, this::getLevel, recipeCacheListener, initChemicalSlotX(), initChemicalSlotY()))
                .setSlotOverlay(SlotOverlay.MINUS);
        builder.addSlot(outputSlot = LimitChangedOutputInventorySlot.at(
                recipeCacheUnpauseListener, initOutputSlotX(), initOutputSlotY(), initItemSlotCapacity()))
                .tracksWarnings(slot -> slot.warning(WarningType.NO_SPACE_IN_OUTPUT,
                        getWarningCheck(RecipeError.NOT_ENOUGH_OUTPUT_SPACE)));
        builder.addSlot(energySlot = EnergyInventorySlot.fillOrConvert(
                energyContainer, this::getLevel, listener, initEnergySlotX(), initEnergySlotY()));
        return builder.build();
    }

    protected abstract int initItemSlotCapacity();

    protected int initInputSlotX() {
        return 64;
    }

    protected int initInputSlotY() {
        return 17;
    }

    protected int initChemicalSlotX() {
        return 64;
    }

    protected int initChemicalSlotY() {
        return 53;
    }

    protected int initOutputSlotX() {
        return 116;
    }

    protected int initOutputSlotY() {
        return 35;
    }

    protected int initEnergySlotX() {
        return 39;
    }

    protected int initEnergySlotY() {
        return 35;
    }

    @Override
    protected boolean onUpdateServer() {
        boolean sendUpdatePacket = super.onUpdateServer();
        energySlot.fillContainerOrConvert();
        chemicalSlot.fillTankOrConvert();
        clientEnergyUsed = recipeCacheLookupMonitor.updateAndProcess(energyContainer);
        return sendUpdatePacket;
    }

    @Override
    public @Nullable ItemStackChemicalToItemStackRecipe getRecipe(int arg0) {
        return findFirstRecipe(itemInputHandler, chemicalInputHandler);
    }

    @Override
    public @NotNull CachedRecipe<ItemStackChemicalToItemStackRecipe> createNewCachedRecipe(
            @NotNull ItemStackChemicalToItemStackRecipe recipe, int arg1) {
        return new AdvancedItemStackChemicalToItemStackCachedRecipe(
                recipe, recheckAllRecipeErrors, itemInputHandler,
                chemicalInputHandler, outputHandler, this::getChemicalUsage)
                .setErrorsChanged(this::onErrorsChanged)
                .setCanHolderFunction(this::canFunction)
                .setActive(this::setActive)
                .setEnergyRequirements(energyContainer::getEnergyPerTick, energyContainer)
                .setRequiredTicks(this::getTicksRequired)
                .setOnFinish(this::markForSave)
                .setOperatingTicksChanged(this::setOperatingTicks)
                .setBaselineMaxOperations(this::getOperationsPerTick);
    }

    @Override
    public void recalculateUpgrades(Upgrade upgrade) {
        super.recalculateUpgrades(upgrade);
        if (upgrade == Upgrade.CHEMICAL) {
            chemicalUsageModifier = Math.pow(MekanismConfig.general.maxUpgradeMultiplier.getAsInt(),
                    -upgradeComponent.getUpgrades(Upgrade.CHEMICAL) / 8.0d);
        } else if (upgrade == ExtraUpgrade.STACK) {
            stackUpgrade = upgradeComponent.getUpgrades(ExtraUpgrade.STACK);
        }
    }

    private long getChemicalUsage(long defaultValue) {
        return MathUtils.clampToLong(defaultValue * chemicalUsageModifier);
    }

    @Override
    public int getOperationsPerTick() {
        return MathUtils.clampToInt(1L * baselineMaxOperations * super.getOperationsPerTick() << stackUpgrade);
    }

    @Override
    public long getEnergyUsed() {
        return clientEnergyUsed;
    }

    @Override
    public void addContainerTrackers(MekanismContainer container) {
        super.addContainerTrackers(container);
        container.track(SyncableLong.create(this::getEnergyUsed, v -> clientEnergyUsed = v));
    }

    @Override
    public MachineEnergyContainer<?> getEnergyContainer() {
        return energyContainer;
    }

    @Override
    public IChemicalTank getInputTank() {
        return chemicalTank;
    }

    @Override
    public abstract IRecipeViewerRecipeType<ItemStackChemicalToItemStackRecipe> recipeViewerType();

}
