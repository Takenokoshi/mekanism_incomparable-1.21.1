package com.takenokoshi.mekin.blockentity.abs;

import java.util.List;
import java.util.function.Consumer;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.takenokoshi.mekaddonlib.blockentity.base.BEMultiScaledProgressMachine;
import com.takenokoshi.mekaddonlib.capabilities.energy.VariableUsageMachineEnergyContainer;
import com.takenokoshi.mekaddonlib.inventory.slot.LimitChangedOutputInventorySlot;
import com.takenokoshi.mekaddonlib.recipe.cached.ICachedRecipe;
import com.takenokoshi.mekaddonlib.recipe.type.IMekALRecipeTypeProvider;
import com.takenokoshi.mekin.recipe.cached.LightningFabricationCachedRecipe;
import com.takenokoshi.mekin.recipe.inputcache.LightningFabricationInputRecipeCache;
import com.takenokoshi.mekin.recipe.lookup.recipe.ILightnigFabricationLookupHandler;
import com.takenokoshi.mekin.recipe.recipes.prefab.LightningFabricationRecipe;
import com.takenokoshi.mekin.recipe_viewer.type.MekInRecipeViewerRecipeType;
import com.takenokoshi.mekin.registries.MekInRecipeTypes;
import com.takenokoshi.mekut.blockentity.interfaces.IHasMachineEnergyContainer;
import com.takenokoshi.mekut.inventory.slot.ChemicalFillConvertOrSupplyingSlot;
import com.takenokoshi.mekut.inventory.slot.InputOrSupplyingSlot;
import com.takenokoshi.mekut.recipe.input.AdvancedChemicalInputHandler;
import com.takenokoshi.mekut.recipe.input.AdvancedItemInputHandler;
import com.takenokoshi.mekut.recipe.input.ItemStackListInputHandler;
import com.takenokoshi.mekut.recipe.output.ItemOutputHandler;

import mekanism.api.IContentsListener;
import mekanism.api.Upgrade;
import mekanism.api.chemical.BasicChemicalTank;
import mekanism.api.chemical.IChemicalTank;
import mekanism.api.inventory.IInventorySlot;
import mekanism.api.recipes.cache.CachedRecipe.OperationTracker.RecipeError;
import mekanism.api.recipes.outputs.IOutputHandler;
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
import mekanism.common.inventory.container.MekanismContainer;
import mekanism.common.inventory.container.slot.SlotOverlay;
import mekanism.common.inventory.slot.EnergyInventorySlot;
import mekanism.common.inventory.warning.WarningTracker.WarningType;
import mekanism.common.lib.transmitter.TransmissionType;
import mekanism.common.registration.impl.ItemRegistryObject;
import mekanism.common.tile.component.TileComponentConfig;
import mekanism.common.tile.component.TileComponentEjector;
import mekanism.common.tile.component.config.ConfigInfo;
import mekanism.common.tile.component.config.DataType;
import mekanism.common.tile.component.config.slot.InventorySlotInfo;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import static fr.iglee42.evolvedmekanism.tiles.machine.TileEntityChemixer.NOT_ENOUGH_ITEM_INPUT_ERROR;
import static fr.iglee42.evolvedmekanism.tiles.machine.TileEntityChemixer.NOT_ENOUGH_SECONDARY_INPUT;
import static fr.iglee42.evolvedmekanism.tiles.machine.TileEntityChemixer.NOT_ENOUGH_GAS_INPUT_ERROR;
import static fr.iglee42.evolvedmekanism.tiles.machine.TileEntityChemixer.NOT_ENOUGH_SPACE_ITEM_OUTPUT_ERROR;

public abstract class BEAbstractLightningFabricator extends BEMultiScaledProgressMachine<LightningFabricationRecipe>
        implements IHasMachineEnergyContainer, ILightnigFabricationLookupHandler {
    private static final List<RecipeError> TRACKED_ERROR_TYPES = List.of(
            RecipeError.NOT_ENOUGH_ENERGY,
            NOT_ENOUGH_ITEM_INPUT_ERROR,
            NOT_ENOUGH_SECONDARY_INPUT,
            NOT_ENOUGH_GAS_INPUT_ERROR,
            NOT_ENOUGH_SPACE_ITEM_OUTPUT_ERROR,
            RecipeError.INPUT_DOESNT_PRODUCE_OUTPUT);

    public static Consumer<ItemRegistryObject<?>> getContainerAdder(long chemicalTankCapacity) {
        return value -> {
            value.addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                    .addBasic(4)
                    .addOutput()
                    .addEnergy()
                    .build());
            value.addAttachmentOnlyContainers(ContainerType.CHEMICAL, () -> ChemicalTanksBuilder.builder()
                    .addBasic(chemicalTankCapacity)
                    .build());
        };
    }

    protected IChemicalTank chemicalTank;

    protected InputOrSupplyingSlot mainInputSlot;
    protected InputOrSupplyingSlot extraInputSlot1;
    protected InputOrSupplyingSlot extraInputSlot2;

    protected ChemicalFillConvertOrSupplyingSlot chemicalSlot;

    protected LimitChangedOutputInventorySlot outputSlot;

    protected EnergyInventorySlot energySlot;

    protected VariableUsageMachineEnergyContainer<?> energyContainer;

    protected final AdvancedItemInputHandler mainInputHandler;
    protected final ItemStackListInputHandler extraInputHandler;
    protected final AdvancedChemicalInputHandler chemicalInputHandler;
    protected final IOutputHandler<ItemStack> outputHandler;

    protected long recipeEnergyRequired = 0L;

    public BEAbstractLightningFabricator(Holder<Block> blockProvider, BlockPos pos, BlockState state,
            int baselineMaxOperations) {
        super(blockProvider, pos, state, TRACKED_ERROR_TYPES, baselineMaxOperations,
                LightningFabricationRecipe::getDuration);
        setupItemIOExtraConfig(configComponent, mainInputSlot, outputSlot, extraInputSlot1, extraInputSlot2,
                energySlot);
        configComponent.setupInputConfig(TransmissionType.CHEMICAL, chemicalTank);
        configComponent.setupInputConfig(TransmissionType.ENERGY, energyContainer);

        ejectorComponent = new TileComponentEjector(this).setOutputData(configComponent, TransmissionType.ITEM);

        mainInputHandler = AdvancedItemInputHandler.create(mainInputSlot, NOT_ENOUGH_ITEM_INPUT_ERROR);
        extraInputHandler = new ItemStackListInputHandler(List.of(extraInputSlot1, extraInputSlot2),
                NOT_ENOUGH_SECONDARY_INPUT);
        chemicalInputHandler = AdvancedChemicalInputHandler.create(chemicalTank, NOT_ENOUGH_GAS_INPUT_ERROR);
        outputHandler = new ItemOutputHandler(outputSlot, NOT_ENOUGH_SPACE_ITEM_OUTPUT_ERROR);

        mainInputSlot.setSupplyingStackSetter(mainInputHandler::setSuppliedStack);
        extraInputSlot1.setSupplyingStackSetter(stack -> extraInputHandler.setSuppliedStack(stack, 1));
        extraInputSlot2.setSupplyingStackSetter(stack -> extraInputHandler.setSuppliedStack(stack, 2));
        chemicalSlot.setSupplyingStackSetter(chemicalInputHandler::setSuppliedStack);
    }

    private static ConfigInfo setupItemIOExtraConfig(TileComponentConfig config, IInventorySlot inputSlot,
            IInventorySlot outputSlot, IInventorySlot extraSlot, IInventorySlot secondaryExtraSlot,
            IInventorySlot energySlot) {
        ConfigInfo itemConfig = config.getConfig(TransmissionType.ITEM);
        if (itemConfig != null) {
            itemConfig.addSlotInfo(DataType.INPUT, new InventorySlotInfo(true, false, inputSlot));
            itemConfig.addSlotInfo(DataType.OUTPUT, new InventorySlotInfo(false, true, outputSlot));
            itemConfig.addSlotInfo(DataType.INPUT_OUTPUT, new InventorySlotInfo(true, true, inputSlot, outputSlot));
            itemConfig.addSlotInfo(DataType.EXTRA, new InventorySlotInfo(true, true, extraSlot, secondaryExtraSlot));
            itemConfig.addSlotInfo(DataType.ENERGY, new InventorySlotInfo(true, true, energySlot));
            // Set default config directions
        }
        return itemConfig;
    }

    @Override
    protected @Nullable IChemicalTankHolder getInitialChemicalTanks(IContentsListener listener,
            IContentsListener recipeCacheListener, IContentsListener recipeCacheUnpauseListener) {
        ChemicalTankHelper builder = ChemicalTankHelper.forSideWithConfig(this);
        builder.addTank(chemicalTank = BasicChemicalTank.inputModern(initChemicalTankCapacity(),
                stack -> containsRecipeChemicalOther(mainInputHandler.getInput(), extraInputHandler.getInput(), stack),
                this::containsRecipeChemical, recipeCacheListener));
        return builder.build();
    }

    protected abstract long initChemicalTankCapacity();

    protected IEnergyContainerHolder getInitialEnergyContainers(IContentsListener listener,
            IContentsListener recipeCacheListener, IContentsListener recipeCacheUnpauseListener) {
        EnergyContainerHelper builder = EnergyContainerHelper.forSideWithConfig(this);
        builder.addContainer(
                this.energyContainer = VariableUsageMachineEnergyContainer.input(this, recipeCacheUnpauseListener));
        return builder.build();
    }

    @Override
    protected @Nullable IInventorySlotHolder getInitialInventory(IContentsListener listener,
            IContentsListener recipeCacheListener, IContentsListener recipeCacheUnpauseListener) {
        InventorySlotHelper builder = InventorySlotHelper.forSideWithConfig(this);
        builder.addSlot(mainInputSlot = InputOrSupplyingSlot.at(
                stack -> containsRecipeMainOther(stack, extraInputHandler.getInput(), chemicalInputHandler.getInput()),
                this::containsRecipeMain,
                recipeCacheListener,
                64, 17, initItemSlotCapacity()))
                .tracksWarnings(warning -> warning.warning(WarningType.NO_MATCHING_RECIPE,
                        getWarningCheck(NOT_ENOUGH_ITEM_INPUT_ERROR)));
        builder.addSlot(extraInputSlot1 = InputOrSupplyingSlot.at(
                stack -> containsRecipeExtraOther(mainInputHandler.getInput(), stack,
                        extraInputHandler.getOtherSlotInput(0), chemicalInputHandler.getInput()),
                this::containsRecipeExtra,
                recipeCacheListener,
                55, 53, initItemSlotCapacity()))
                .tracksWarnings(warning -> warning.warning(WarningType.NO_MATCHING_RECIPE,
                        getWarningCheck(NOT_ENOUGH_SECONDARY_INPUT)));
        builder.addSlot(extraInputSlot2 = InputOrSupplyingSlot.at(
                stack -> containsRecipeExtraOther(mainInputHandler.getInput(), stack,
                        extraInputHandler.getOtherSlotInput(1), chemicalInputHandler.getInput()),
                this::containsRecipeExtra,
                recipeCacheListener,
                75, 53, initItemSlotCapacity()))
                .tracksWarnings(warning -> warning.warning(WarningType.NO_MATCHING_RECIPE,
                        getWarningCheck(NOT_ENOUGH_SECONDARY_INPUT)));
        builder.addSlot(chemicalSlot = ChemicalFillConvertOrSupplyingSlot.create(chemicalTank, this::getLevel,
                recipeCacheListener, 29, 53)).setSlotOverlay(SlotOverlay.MINUS);
        builder.addSlot(outputSlot = LimitChangedOutputInventorySlot.at(recipeCacheUnpauseListener,
                116, 35, initItemSlotCapacity()))
                .tracksWarnings(warning -> warning.warning(WarningType.NO_SPACE_IN_OUTPUT,
                        getWarningCheck(NOT_ENOUGH_SPACE_ITEM_OUTPUT_ERROR)));
        builder.addSlot(energySlot = EnergyInventorySlot.fillOrConvert(energyContainer, this::getLevel,
                listener, 141, 35));
        return builder.build();
    }

    protected abstract int initItemSlotCapacity();

    @Override
    public @NotNull ICachedRecipe<LightningFabricationRecipe> createNewCachedRecipe(
            @NotNull LightningFabricationRecipe recipe, int arg1) {
        return new LightningFabricationCachedRecipe(recipe, recheckAllRecipeErrors, mainInputHandler, extraInputHandler,
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
    public void onCachedRecipeChanged(@Nullable ICachedRecipe<LightningFabricationRecipe> cachedRecipe,
            int cacheIndex) {
        if (cachedRecipe != null) {
            recipeEnergyRequired = cachedRecipe.getRecipe().energyRequired;
        }
        updateEnergyPerTick();
    }

    @Override
    public @Nullable LightningFabricationRecipe getRecipe(int arg0) {
        return findFirstRecipe(mainInputHandler, extraInputHandler, chemicalInputHandler);
    }

    protected void updateEnergyPerTick() {
        if (recipeTicksRequired > 0) {
            energyContainer.updateAdditionalUsage((recipeEnergyRequired - 1L) / recipeTicksRequired + 1L);
            energyContainer.updateEnergyPerTick();
        }
    }

    @Override
    public void recalculateUpgrades(Upgrade upgrade) {
        super.recalculateUpgrades(upgrade);
        updateEnergyPerTick();
    }

    @Override
    protected boolean onUpdateServer() {
        boolean sendUpdatePacket = super.onUpdateServer();
        chemicalSlot.fillTankOrConvert();
        energySlot.fillContainerOrConvert();
        clientEnergyUsed = recipeCacheLookupMonitor.updateAndProcess(energyContainer);
        return sendUpdatePacket;
    }

    @Override
    public MachineEnergyContainer<?> getEnergyContainer() {
        return energyContainer;
    }

    public IChemicalTank getChemicalTank() {
        return chemicalTank;
    }

    @Override
    public @NotNull IMekALRecipeTypeProvider<?, LightningFabricationRecipe, LightningFabricationInputRecipeCache> getRecipeType() {
        return MekInRecipeTypes.LIGHTNING_FABRICATION;
    }

    public IRecipeViewerRecipeType<?> recipeViewerType() {
        return MekInRecipeViewerRecipeType.LIGHTNING_FABRICATION;
    }

    @Override
    public void addContainerTrackers(MekanismContainer container) {
        super.addContainerTrackers(container);
        energyContainer.track(container);
    }

}
