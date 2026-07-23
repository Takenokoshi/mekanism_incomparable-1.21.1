package com.takenokoshi.mekin.blockentity.abs;

import java.util.List;
import java.util.function.Consumer;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.jerry.mekextras.api.ExtraUpgrade;
import com.takenokoshi.mekaddonlib.blockentity.base.BEMultiScaledProgressMachine;
import com.takenokoshi.mekaddonlib.blockentity.component.EjectorComponentUtils;
import com.takenokoshi.mekaddonlib.recipe.cached.ICachedRecipe;
import com.takenokoshi.mekaddonlib.recipe.type.IMekALRecipeTypeProvider;
import com.takenokoshi.mekin.blockentity.interfaces.ISimpleItemStackChemicalToChemicalMachine;
import com.takenokoshi.mekin.recipe.cached.ItemStackChemicalToChemicalCachedRecipe;
import com.takenokoshi.mekin.recipe.inputcache.MekInDoubleInputRecipeCache;
import com.takenokoshi.mekin.recipe.lookup.recipe.IMekInDoubleRecipeLookupHandler;
import com.takenokoshi.mekin.recipe.recipes.prefab.ItemStackChemicalToChemicalRecipe;
import com.takenokoshi.mekin.recipe_viewer.type.MekInRecipeViewerRecipeType;
import com.takenokoshi.mekin.registries.MekInRecipeTypes;
import com.takenokoshi.mekut.blockentity.interfaces.IHasMachineEnergyContainer;
import com.takenokoshi.mekut.inventory.slot.ChemicalFillConvertOrSupplyingSlot;
import com.takenokoshi.mekut.inventory.slot.InputOrSupplyingSlot;
import com.takenokoshi.mekut.recipe.input.AdvancedChemicalInputHandler;
import com.takenokoshi.mekut.recipe.input.AdvancedItemInputHandler;

import mekanism.api.IContentsListener;
import mekanism.api.RelativeSide;
import mekanism.api.Upgrade;
import mekanism.api.chemical.BasicChemicalTank;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.chemical.IChemicalTank;
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
import mekanism.common.inventory.slot.EnergyInventorySlot;
import mekanism.common.inventory.slot.chemical.ChemicalInventorySlot;
import mekanism.common.inventory.warning.WarningTracker.WarningType;
import mekanism.common.lib.transmitter.TransmissionType;
import mekanism.common.registration.impl.ItemRegistryObject;
import mekanism.common.tile.component.TileComponentEjector;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public abstract class BEAbstractAET extends BEMultiScaledProgressMachine<ItemStackChemicalToChemicalRecipe> implements
        IMekInDoubleRecipeLookupHandler.MekInItemChemicalRecipeLoolupHandler<ItemStackChemicalToChemicalRecipe>,
        IHasMachineEnergyContainer, ISimpleItemStackChemicalToChemicalMachine {

    public static final List<CachedRecipe.OperationTracker.RecipeError> TRACKED_ERROR_TYPES = List.of(
            RecipeError.NOT_ENOUGH_ENERGY,
            RecipeError.NOT_ENOUGH_INPUT,
            RecipeError.NOT_ENOUGH_SECONDARY_INPUT,
            RecipeError.NOT_ENOUGH_OUTPUT_SPACE,
            RecipeError.INPUT_DOESNT_PRODUCE_OUTPUT);

    public static Consumer<ItemRegistryObject<?>> getContainerAdder(long chemicalTankCapacity) {
        return value -> {
            value.addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                    .addBasic(3)
                    .addEnergy()
                    .build());
            value.addAttachmentOnlyContainers(ContainerType.CHEMICAL, () -> ChemicalTanksBuilder.builder()
                    .addBasic(chemicalTankCapacity)
                    .addBasic(chemicalTankCapacity)
                    .build());
        };
    }

    protected IChemicalTank inputTank;
    protected IChemicalTank outputTank;
    protected MachineEnergyContainer<?> energyContainer;

    protected InputOrSupplyingSlot inputSlot;
    protected ChemicalFillConvertOrSupplyingSlot chemicalSlot;
    protected ChemicalInventorySlot outputSlot;
    protected EnergyInventorySlot energySlot;

    protected final AdvancedItemInputHandler itemInputHandler;
    protected final AdvancedChemicalInputHandler chemicalInputHandler;
    protected final IOutputHandler<ChemicalStack> outputHandler;

    protected BEAbstractAET(Holder<Block> blockProvider, BlockPos pos, BlockState state,
            int baselineMaxOperations) {
        super(blockProvider, pos, state, TRACKED_ERROR_TYPES, baselineMaxOperations, r -> 100);
        configComponent.setupItemIOExtraConfig(inputSlot, outputSlot, chemicalSlot, energySlot);
        configComponent.setupIOConfig(TransmissionType.CHEMICAL, inputTank, outputTank, RelativeSide.RIGHT);
        configComponent.setupInputConfig(TransmissionType.ENERGY, energyContainer);
        ejectorComponent = new TileComponentEjector(this, () -> Long.MAX_VALUE).setOutputData(configComponent,
                TransmissionType.ITEM, TransmissionType.CHEMICAL);
        EjectorComponentUtils.setCanChemicalTankEject(ejectorComponent,
                (type, tank) -> type.canOutput() && tank != inputTank);
        inputSlot.setSupplyingStackSetter((this.itemInputHandler = AdvancedItemInputHandler.create(inputSlot,
                RecipeError.NOT_ENOUGH_INPUT))::setSuppliedStack);
        chemicalSlot.setSupplyingStackSetter((this.chemicalInputHandler = AdvancedChemicalInputHandler.create(inputTank,
                RecipeError.NOT_ENOUGH_SECONDARY_INPUT))::setSuppliedStack);
        this.outputHandler = OutputHelper.getOutputHandler(outputTank, RecipeError.NOT_ENOUGH_OUTPUT_SPACE);
    }

    @Override
    protected @Nullable IChemicalTankHolder getInitialChemicalTanks(IContentsListener listener,
            IContentsListener recipeCacheListener, IContentsListener recipeCacheUnpauseListener) {
        ChemicalTankHelper builder = ChemicalTankHelper.forSideWithConfig(this);
        builder.addTank(inputTank = BasicChemicalTank.inputModern(
                initChemicalTankCapacity(),
                (stack) -> containsRecipeBA(itemInputHandler.getInput(), stack),
                this::containsRecipeB,
                recipeCacheListener));
        builder.addTank(outputTank = BasicChemicalTank.output(initChemicalTankCapacity(), recipeCacheUnpauseListener));
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
                recipeCacheListener,
                28, 36, initItemSlotCapacity()))
                .tracksWarnings(slot -> slot.warning(WarningType.NO_MATCHING_RECIPE,
                        getWarningCheck(RecipeError.NOT_ENOUGH_INPUT)));
        builder.addSlot(chemicalSlot = ChemicalFillConvertOrSupplyingSlot.create(inputTank,
                this::getLevel, recipeCacheListener, 8, 65));
        builder.addSlot(outputSlot = ChemicalInventorySlot.drain(outputTank, listener, 152, 55));
        builder.addSlot(energySlot = EnergyInventorySlot.fillOrConvert(energyContainer,
                this::getLevel, listener, 152, 14));
        return builder.build();
    }

    protected abstract int initItemSlotCapacity();

    @Override
    public @NotNull ICachedRecipe<ItemStackChemicalToChemicalRecipe> createNewCachedRecipe(
            @NotNull ItemStackChemicalToChemicalRecipe recipe, int arg1) {
        return new ItemStackChemicalToChemicalCachedRecipe(recipe, recheckAllRecipeErrors, itemInputHandler,
                chemicalInputHandler, outputHandler)
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
    public @Nullable ItemStackChemicalToChemicalRecipe getRecipe(int arg0) {
        return findFirstRecipe(itemInputHandler, chemicalInputHandler);
    }

    @Override
    protected boolean onUpdateServer() {
        boolean needsPacked = super.onUpdateServer();
        chemicalSlot.fillTankOrConvert();
        energySlot.fillContainerOrConvert();
        outputSlot.drainTank();
        clientEnergyUsed = recipeCacheLookupMonitor.updateAndProcess(energyContainer);
        return needsPacked;
    }

    @Override
    public void recalculateUpgrades(Upgrade upgrade) {
        if (upgrade == ExtraUpgrade.STACK) {
            recaluculateProcessingSpeed();
        }
        super.recalculateUpgrades(upgrade);
    }

    @Override
    protected void recaluculateProcessingSpeed() {
        super.recaluculateProcessingSpeed();
        operationsPerTick = operationsPerTick << upgradeComponent.getUpgrades(ExtraUpgrade.STACK);
    }

    @Override
    public MachineEnergyContainer<?> getEnergyContainer() {
        return energyContainer;
    }

    public IChemicalTank getInputTank() {
        return inputTank;
    }

    public IChemicalTank getOutputTank() {
        return outputTank;
    }

    @Override
    public @NotNull IMekALRecipeTypeProvider<?, ItemStackChemicalToChemicalRecipe, MekInDoubleInputRecipeCache.MekInItemChemical<ItemStackChemicalToChemicalRecipe>> getRecipeType() {
        return MekInRecipeTypes.AET;
    }

    @Override
    public @Nullable IRecipeViewerRecipeType<?> recipeViewerType() {
        return MekInRecipeViewerRecipeType.AET;
    }

}
