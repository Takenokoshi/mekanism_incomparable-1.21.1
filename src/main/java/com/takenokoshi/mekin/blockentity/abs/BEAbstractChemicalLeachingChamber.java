package com.takenokoshi.mekin.blockentity.abs;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.jerry.mekextras.api.ExtraUpgrade;
import com.takenokoshi.mekaddonlib.blockentity.base.BEMultiScaledProgressMachine;
import com.takenokoshi.mekaddonlib.blockentity.interfaces.IHasGuiSizeOffset;
import com.takenokoshi.mekaddonlib.capabilities.energy.VariableUsageMachineEnergyContainer;
import com.takenokoshi.mekaddonlib.inventory.slot.LimitChangedOutputInventorySlot;
import com.takenokoshi.mekaddonlib.recipe.cached.ICachedRecipe;
import com.takenokoshi.mekaddonlib.recipe.type.IMekALRecipeTypeProvider;
import com.takenokoshi.mekin.recipe.cached.ItemStackFluidChemicalToItemStackCachedRecipe;
import com.takenokoshi.mekin.recipe.inputcache.MekInTripleInputRecipeCache;
import com.takenokoshi.mekin.recipe.lookup.recipe.IMekInTripleRecipeLookupHandler;
import com.takenokoshi.mekin.recipe.recipes.prefab.ItemStackFluidChemicalToItemStackRecipe;
import com.takenokoshi.mekin.recipe_viewer.type.MekInRecipeViewerRecipeType;
import com.takenokoshi.mekin.registries.MekInRecipeTypes;
import com.takenokoshi.mekut.blockentity.interfaces.IHasMachineEnergyContainer;
import com.takenokoshi.mekut.blockentity.interfaces.IRecipeViewerTypeProvider;
import com.takenokoshi.mekut.inventory.slot.ChemicalFillConvertOrSupplyingSlot;
import com.takenokoshi.mekut.inventory.slot.FluidFillOrSupplierSlot;
import com.takenokoshi.mekut.inventory.slot.InputOrSupplyingSlot;
import com.takenokoshi.mekut.recipe.input.AdvancedChemicalInputHandler;
import com.takenokoshi.mekut.recipe.input.AdvancedFluidInputHandler;
import com.takenokoshi.mekut.recipe.input.AdvancedItemInputHandler;
import com.takenokoshi.mekut.recipe.output.ItemOutputHandler;

import mekanism.api.AutomationType;
import mekanism.api.IContentsListener;
import mekanism.api.RelativeSide;
import mekanism.api.Upgrade;
import mekanism.api.chemical.BasicChemicalTank;
import mekanism.api.chemical.IChemicalTank;
import mekanism.api.chemical.attribute.ChemicalAttributeValidator;
import mekanism.api.fluid.IExtendedFluidTank;
import mekanism.api.recipes.cache.CachedRecipe.OperationTracker.RecipeError;
import mekanism.client.recipe_viewer.type.IRecipeViewerRecipeType;
import mekanism.common.attachments.component.AttachedSideConfig;
import mekanism.common.attachments.containers.ContainerType;
import mekanism.common.attachments.containers.chemical.ChemicalTanksBuilder;
import mekanism.common.attachments.containers.fluid.FluidTanksBuilder;
import mekanism.common.attachments.containers.item.ItemSlotsBuilder;
import mekanism.common.capabilities.energy.MachineEnergyContainer;
import mekanism.common.capabilities.fluid.BasicFluidTank;
import mekanism.common.capabilities.holder.chemical.ChemicalTankHelper;
import mekanism.common.capabilities.holder.chemical.IChemicalTankHolder;
import mekanism.common.capabilities.holder.energy.EnergyContainerHelper;
import mekanism.common.capabilities.holder.energy.IEnergyContainerHolder;
import mekanism.common.capabilities.holder.fluid.FluidTankHelper;
import mekanism.common.capabilities.holder.fluid.IFluidTankHolder;
import mekanism.common.capabilities.holder.slot.IInventorySlotHolder;
import mekanism.common.capabilities.holder.slot.InventorySlotHelper;
import mekanism.common.inventory.container.slot.SlotOverlay;
import mekanism.common.inventory.slot.EnergyInventorySlot;
import mekanism.common.inventory.slot.OutputInventorySlot;
import mekanism.common.inventory.warning.WarningTracker.WarningType;
import mekanism.common.lib.transmitter.TransmissionType;
import mekanism.common.registration.impl.ItemRegistryObject;
import mekanism.common.tile.component.TileComponentEjector;
import mekanism.common.tile.component.config.DataType;
import mekanism.common.tile.component.config.slot.InventorySlotInfo;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import static mekanism.common.tile.machine.TileEntityPressurizedReactionChamber.NOT_ENOUGH_CHEMICAL_INPUT_ERROR;
import static mekanism.common.tile.machine.TileEntityPressurizedReactionChamber.NOT_ENOUGH_FLUID_INPUT_ERROR;
import static mekanism.common.tile.machine.TileEntityPressurizedReactionChamber.NOT_ENOUGH_ITEM_INPUT_ERROR;
import static mekanism.common.tile.machine.TileEntityPressurizedReactionChamber.NOT_ENOUGH_SPACE_ITEM_OUTPUT_ERROR;

public abstract class BEAbstractChemicalLeachingChamber
        extends BEMultiScaledProgressMachine<ItemStackFluidChemicalToItemStackRecipe> implements
        IMekInTripleRecipeLookupHandler.MekInItemFluidChemicalRecipeLookupHandler<ItemStackFluidChemicalToItemStackRecipe>,
        IHasMachineEnergyContainer, IRecipeViewerTypeProvider, IHasGuiSizeOffset {

    public static final List<RecipeError> TRACKED_ERROR_TYPES = List.of(
            RecipeError.NOT_ENOUGH_ENERGY,
            NOT_ENOUGH_ITEM_INPUT_ERROR,
            NOT_ENOUGH_FLUID_INPUT_ERROR,
            NOT_ENOUGH_CHEMICAL_INPUT_ERROR,
            NOT_ENOUGH_SPACE_ITEM_OUTPUT_ERROR,
            RecipeError.INPUT_DOESNT_PRODUCE_OUTPUT);

    public static final AttachedSideConfig SIDE_CONFIG = Util.make(() -> {
        Map<TransmissionType, AttachedSideConfig.LightConfigInfo> configInfo = new EnumMap<>(TransmissionType.class);
        configInfo.put(TransmissionType.ITEM, AttachedSideConfig.LightConfigInfo.EXTRA_MACHINE);
        configInfo.put(TransmissionType.FLUID, AttachedSideConfig.LightConfigInfo.INPUT_ONLY);
        configInfo.put(TransmissionType.CHEMICAL, AttachedSideConfig.LightConfigInfo.INPUT_ONLY);
        configInfo.put(TransmissionType.ENERGY, AttachedSideConfig.LightConfigInfo.INPUT_ONLY);
        return new AttachedSideConfig(configInfo);
    });

    public static Consumer<ItemRegistryObject<?>> getContainerAdder(long chemicalTankCapacity, int fluidTankCapacity) {
        return value -> {
            value.addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                    .addBasic(3)
                    .addOutput()
                    .addOutput()
                    .addEnergy()
                    .build());
            value.addAttachmentOnlyContainers(ContainerType.FLUID, () -> FluidTanksBuilder.builder()
                    .addBasic(fluidTankCapacity)
                    .build());
            value.addAttachmentOnlyContainers(ContainerType.CHEMICAL, () -> ChemicalTanksBuilder.builder()
                    .addBasic(chemicalTankCapacity)
                    .build());
        };
    }

    protected IChemicalTank inputChemicalTank;
    protected IExtendedFluidTank inputFluidTank;

    protected VariableUsageMachineEnergyContainer<?> energyContainer;

    protected InputOrSupplyingSlot inputSlot;
    protected FluidFillOrSupplierSlot fluidInputSlot;
    protected ChemicalFillConvertOrSupplyingSlot chemicalInputSlot;
    protected LimitChangedOutputInventorySlot outputSlot;
    protected OutputInventorySlot fluidReturnSlot;
    protected EnergyInventorySlot energySlot;

    protected final AdvancedItemInputHandler itemInputHandler;
    protected final AdvancedFluidInputHandler fluidInputHandler;
    protected final AdvancedChemicalInputHandler chemicalInputHandler;
    protected final ItemOutputHandler outputHandler;

    protected BEAbstractChemicalLeachingChamber(Holder<Block> blockProvider, BlockPos pos, BlockState state,
            int baselineMaxOperations) {
        super(blockProvider, pos, state, TRACKED_ERROR_TYPES, baselineMaxOperations,
                ItemStackFluidChemicalToItemStackRecipe::getDuration);
        var itemConfig = configComponent.getConfig(TransmissionType.ITEM);
        itemConfig.addSlotInfo(DataType.INPUT, new InventorySlotInfo(true, false, List.of(inputSlot)));
        itemConfig.addSlotInfo(DataType.OUTPUT, new InventorySlotInfo(false, true, List.of(outputSlot)));
        itemConfig.addSlotInfo(DataType.INPUT_OUTPUT,
                new InventorySlotInfo(true, true, List.of(inputSlot, outputSlot)));
        itemConfig.addSlotInfo(DataType.EXTRA,
                new InventorySlotInfo(true, true, List.of(fluidInputSlot, chemicalInputSlot, fluidReturnSlot)));
        itemConfig.addSlotInfo(DataType.ENERGY, new InventorySlotInfo(true, true, List.of(energySlot)));
        itemConfig.setCanEject(true);
        itemConfig.setDataType(DataType.INPUT, RelativeSide.LEFT);
        itemConfig.setDataType(DataType.INPUT, RelativeSide.TOP);
        itemConfig.setDataType(DataType.INPUT, RelativeSide.FRONT);
        itemConfig.setDataType(DataType.OUTPUT, RelativeSide.RIGHT);
        itemConfig.setDataType(DataType.ENERGY, RelativeSide.BACK);
        itemConfig.setDataType(DataType.EXTRA, RelativeSide.BOTTOM);
        configComponent.setupInputConfig(TransmissionType.FLUID, inputFluidTank);
        configComponent.setupInputConfig(TransmissionType.CHEMICAL, inputChemicalTank);
        configComponent.setupInputConfig(TransmissionType.ENERGY, energyContainer);
        ejectorComponent = new TileComponentEjector(this).setOutputData(configComponent, TransmissionType.ITEM);
        this.itemInputHandler = AdvancedItemInputHandler.create(inputSlot, NOT_ENOUGH_ITEM_INPUT_ERROR);
        this.fluidInputHandler = AdvancedFluidInputHandler.create(inputFluidTank, NOT_ENOUGH_FLUID_INPUT_ERROR);
        this.chemicalInputHandler = AdvancedChemicalInputHandler.create(inputChemicalTank,
                NOT_ENOUGH_CHEMICAL_INPUT_ERROR);
        this.outputHandler = new ItemOutputHandler(outputSlot, NOT_ENOUGH_SPACE_ITEM_OUTPUT_ERROR);
    }

    @Override
    protected @Nullable IChemicalTankHolder getInitialChemicalTanks(IContentsListener listener,
            IContentsListener recipeCacheListener, IContentsListener recipeCacheUnpauseListener) {
        ChemicalTankHelper builder = ChemicalTankHelper.forSideWithConfig(this);
        builder.addTank(inputChemicalTank = BasicChemicalTank.createModern(
                initChemicalTankCapacity(),
                (stack, type) -> type != AutomationType.EXTERNAL,
                (stack, type) -> type != AutomationType.EXTERNAL
                        && containsRecipeCAB(itemInputHandler.getInput(), fluidInputHandler.getInput(), stack),
                this::containsRecipeC,
                ChemicalAttributeValidator.ALWAYS_ALLOW,
                recipeCacheListener));
        return builder.build();
    }

    protected abstract long initChemicalTankCapacity();

    @Override
    protected @Nullable IEnergyContainerHolder getInitialEnergyContainers(IContentsListener listener,
            IContentsListener recipeCacheListener, IContentsListener recipeCacheUnpauseListener) {
        EnergyContainerHelper builder = EnergyContainerHelper.forSideWithConfig(this);
        builder.addContainer(
                energyContainer = VariableUsageMachineEnergyContainer.input(this, recipeCacheUnpauseListener));
        return builder.build();
    }

    @Override
    protected @Nullable IFluidTankHolder getInitialFluidTanks(IContentsListener listener,
            IContentsListener recipeCacheListener, IContentsListener recipeCacheUnpauseListener) {
        FluidTankHelper builder = FluidTankHelper.forSideWithConfig(this);
        builder.addTank(inputFluidTank = BasicFluidTank.input(
                initFluidTankCapacity(),
                (stack) -> containsRecipeBAC(itemInputHandler.getInput(), stack, chemicalInputHandler.getInput()),
                this::containsRecipeB,
                recipeCacheListener));
        return builder.build();
    }

    protected abstract int initFluidTankCapacity();

    @Override
    protected @Nullable IInventorySlotHolder getInitialInventory(IContentsListener listener,
            IContentsListener recipeCacheListener, IContentsListener recipeCacheUnpauseListener) {
        InventorySlotHelper builder = InventorySlotHelper.forSideWithConfig(this);
        builder.addSlot(inputSlot = InputOrSupplyingSlot.at(
                (stack) -> containsRecipeABC(stack, fluidInputHandler.getInput(), chemicalInputHandler.getInput()),
                this::containsRecipeA,
                recipeCacheListener, 54, 40, initItemSlotCapacity()))
                .tracksWarnings((slot) -> slot.warning(WarningType.NO_MATCHING_RECIPE,
                        this.getWarningCheck(NOT_ENOUGH_ITEM_INPUT_ERROR)));
        builder.addSlot(fluidInputSlot = FluidFillOrSupplierSlot.create(inputFluidTank, recipeCacheListener, 180, 71))
                .setSlotOverlay(SlotOverlay.MINUS);
        builder.addSlot(chemicalInputSlot = ChemicalFillConvertOrSupplyingSlot.create(inputChemicalTank, this::getLevel,
                recipeCacheListener, 29, 58))
                .setSlotOverlay(SlotOverlay.MINUS);
        builder.addSlot(outputSlot = LimitChangedOutputInventorySlot.at(recipeCacheUnpauseListener, 116, 40,
                initItemSlotCapacity()))
                .tracksWarnings((slot) -> slot.warning(WarningType.NO_SPACE_IN_OUTPUT,
                        this.getWarningCheck(NOT_ENOUGH_SPACE_ITEM_OUTPUT_ERROR)));
        builder.addSlot(fluidReturnSlot = OutputInventorySlot.at(listener, 180, 102));
        builder.addSlot(energySlot = EnergyInventorySlot.fillOrConvert(energyContainer, this::getLevel, listener,
                141, 22));
        return builder.build();
    }

    protected abstract int initItemSlotCapacity();

    @Override
    public int getExtraHeight() {
        return 5;
    }

    @Override
    public @NotNull ICachedRecipe<ItemStackFluidChemicalToItemStackRecipe> createNewCachedRecipe(
            @NotNull ItemStackFluidChemicalToItemStackRecipe recipe, int arg1) {
        return new ItemStackFluidChemicalToItemStackCachedRecipe(recipe, recheckAllRecipeErrors, itemInputHandler,
                fluidInputHandler, chemicalInputHandler, outputHandler)
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
    public void onCachedRecipeChanged(@Nullable ICachedRecipe<ItemStackFluidChemicalToItemStackRecipe> cachedRecipe,
            int cacheIndex) {
        super.onCachedRecipeChanged(cachedRecipe, cacheIndex);
        if (cachedRecipe != null) {
            energyContainer.updateAdditionalUsage(cachedRecipe.getRecipe().energyRequired);
        }
    }

    @Override
    protected boolean onUpdateServer() {
        boolean needsPacked = super.onUpdateServer();
        fluidInputSlot.fillTank(fluidReturnSlot);
        chemicalInputSlot.fillTankOrConvert();
        energySlot.fillContainerOrConvert();
        clientEnergyUsed = recipeCacheLookupMonitor.updateAndProcess(energyContainer);
        return needsPacked;
    }

    @Override
    public @Nullable ItemStackFluidChemicalToItemStackRecipe getRecipe(int arg0) {
        return findFirstRecipe(itemInputHandler, fluidInputHandler, chemicalInputHandler);
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

    public IChemicalTank getInputChemicalTank() {
        return inputChemicalTank;
    }

    public IExtendedFluidTank getInputFluidTank() {
        return inputFluidTank;
    }

    @Override
    public @NotNull IMekALRecipeTypeProvider<?, ItemStackFluidChemicalToItemStackRecipe, MekInTripleInputRecipeCache.MekInItemFluidChemical<ItemStackFluidChemicalToItemStackRecipe>> getRecipeType() {
        return MekInRecipeTypes.LEACHING;
    }

    @Override
    public @Nullable IRecipeViewerRecipeType<?> recipeViewerType() {
        return MekInRecipeViewerRecipeType.LEACHING;
    }
}
