package com.takenokoshi.mekin.blockentity.abs;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.jerry.mekextras.api.ExtraUpgrade;
import com.takenokoshi.mekaddonlib.blockentity.base.BEExpScaledRecipeMachine;
import com.takenokoshi.mekaddonlib.blockentity.component.EjectorComponentUtils;
import com.takenokoshi.mekaddonlib.recipe.cached.ICachedRecipe;
import com.takenokoshi.mekaddonlib.recipe.type.IMekALRecipeTypeProvider;
import com.takenokoshi.mekin.recipe.cached.FluidChemicalToBiChemicalCachedRecipe;
import com.takenokoshi.mekin.recipe.inputcache.MekInDoubleInputRecipeCache;
import com.takenokoshi.mekin.recipe.lookup.recipe.IMekInDoubleRecipeLookupHandler;
import com.takenokoshi.mekin.recipe.recipes.prefab.FluidChemicalToBiChemicalRecipe;
import com.takenokoshi.mekin.recipe_viewer.type.MekInRecipeViewerRecipeType;
import com.takenokoshi.mekin.registries.MekInRecipeTypes;
import com.takenokoshi.mekut.blockentity.interfaces.IRecipeViewerTypeProvider;
import com.takenokoshi.mekut.inventory.slot.ChemicalFillConvertOrSupplyingSlot;
import com.takenokoshi.mekut.inventory.slot.FluidFillOrSupplierSlot;
import com.takenokoshi.mekut.recipe.input.AdvancedChemicalInputHandler;
import com.takenokoshi.mekut.recipe.input.AdvancedFluidInputHandler;

import mekanism.api.IContentsListener;
import mekanism.api.RelativeSide;
import mekanism.api.Upgrade;
import mekanism.api.chemical.BasicChemicalTank;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.chemical.IChemicalTank;
import mekanism.api.fluid.IExtendedFluidTank;
import mekanism.api.recipes.cache.CachedRecipe.OperationTracker.RecipeError;
import mekanism.api.recipes.outputs.IOutputHandler;
import mekanism.api.recipes.outputs.OutputHelper;
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
import mekanism.common.inventory.slot.EnergyInventorySlot;
import mekanism.common.inventory.slot.OutputInventorySlot;
import mekanism.common.inventory.slot.chemical.ChemicalInventorySlot;
import mekanism.common.lib.transmitter.TransmissionType;
import mekanism.common.registration.impl.ItemRegistryObject;
import mekanism.common.tile.component.TileComponentEjector;
import mekanism.common.tile.component.config.DataType;
import mekanism.common.tile.component.config.slot.ChemicalSlotInfo;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public abstract class BEAbstractChemicalExtractor extends BEExpScaledRecipeMachine<FluidChemicalToBiChemicalRecipe>
        implements
        IMekInDoubleRecipeLookupHandler.MekInFluidChemicalRecipeLookupHandler<FluidChemicalToBiChemicalRecipe>,
        IRecipeViewerTypeProvider {

    public static final RecipeError NOT_ENOUGH_SUB_OUTPUT_SPACE = RecipeError.create();

    public static final List<RecipeError> TRACKED_ERROR_TYPES = List.of(
            RecipeError.NOT_ENOUGH_INPUT,
            RecipeError.NOT_ENOUGH_SECONDARY_INPUT,
            RecipeError.NOT_ENOUGH_OUTPUT_SPACE,
            NOT_ENOUGH_SUB_OUTPUT_SPACE,
            RecipeError.NOT_ENOUGH_ENERGY,
            RecipeError.NOT_ENOUGH_ENERGY_REDUCED_RATE);

    public static final AttachedSideConfig SIDE_CONFIG = Util.make(() -> {
        Map<TransmissionType, AttachedSideConfig.LightConfigInfo> configInfo = new EnumMap<>(TransmissionType.class);
        configInfo.put(TransmissionType.FLUID, AttachedSideConfig.LightConfigInfo.INPUT_ONLY);
        configInfo.put(TransmissionType.CHEMICAL, AttachedSideConfig.LightConfigInfo.TWO_OUTPUT);
        configInfo.put(TransmissionType.ENERGY, AttachedSideConfig.LightConfigInfo.INPUT_ONLY);
        configInfo.put(TransmissionType.ITEM, AttachedSideConfig.LightConfigInfo.MACHINE);
        return new AttachedSideConfig(configInfo);
    });

    public static Consumer<ItemRegistryObject<?>> getContainerAdder(long chemicalTankCapacity, int fluidTankCapacity) {
        return value -> {
            value.addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                    .addBasic(4)
                    .addOutput()
                    .addEnergy()
                    .build());
            value.addAttachmentOnlyContainers(ContainerType.FLUID, () -> FluidTanksBuilder.builder()
                    .addBasic(fluidTankCapacity)
                    .build());
            value.addAttachmentOnlyContainers(ContainerType.CHEMICAL, () -> ChemicalTanksBuilder.builder()
                    .addBasic(chemicalTankCapacity)
                    .addBasic(chemicalTankCapacity)
                    .addBasic(chemicalTankCapacity)
                    .build());
        };
    }

    protected IExtendedFluidTank inputFluidTank;
    protected IChemicalTank inputChemicalTank;
    protected IChemicalTank mainOutputTank;
    protected IChemicalTank subOutputTank;

    protected MachineEnergyContainer<?> energyContainer;

    protected FluidFillOrSupplierSlot fluidInputSlot;
    protected ChemicalFillConvertOrSupplyingSlot chemicalInputSlot;
    protected OutputInventorySlot fluidReturnSlot;
    protected ChemicalInventorySlot mainOutputSlot;
    protected ChemicalInventorySlot subOutputSlot;
    protected EnergyInventorySlot energySlot;

    protected final AdvancedFluidInputHandler fluidInputHandler;
    protected final AdvancedChemicalInputHandler chemicalInputHandler;
    protected final IOutputHandler<ChemicalStack> mainOutputHandler;
    protected final IOutputHandler<ChemicalStack> subOutputHandler;

    protected BEAbstractChemicalExtractor(Holder<Block> blockProvider, BlockPos pos, BlockState state,
            int baselineMaxOperations) {
        super(blockProvider, pos, state, TRACKED_ERROR_TYPES, baselineMaxOperations);
        configComponent.setupInputConfig(TransmissionType.FLUID, inputFluidTank);
        var chemicalConfig = configComponent.getConfig(TransmissionType.CHEMICAL);
        chemicalConfig.addSlotInfo(DataType.INPUT, new ChemicalSlotInfo(true, false, List.of(inputChemicalTank)));
        chemicalConfig.addSlotInfo(DataType.OUTPUT_1, new ChemicalSlotInfo(false, true, List.of(mainOutputTank)));
        chemicalConfig.addSlotInfo(DataType.OUTPUT_2, new ChemicalSlotInfo(false, true, List.of(subOutputTank)));
        chemicalConfig.addSlotInfo(DataType.INPUT_OUTPUT,
                new ChemicalSlotInfo(true, true, List.of(inputChemicalTank, mainOutputTank, subOutputTank)));
        chemicalConfig.setDataType(DataType.OUTPUT_1, RelativeSide.LEFT);
        chemicalConfig.setDataType(DataType.OUTPUT_2, RelativeSide.RIGHT);
        configComponent.setupInputConfig(TransmissionType.ENERGY, energyContainer);
        configComponent.setupItemIOConfig(
                List.of(fluidInputSlot, chemicalInputSlot, mainOutputSlot, subOutputSlot),
                List.of(fluidReturnSlot, chemicalInputSlot, mainOutputSlot, subOutputSlot), energySlot, false);
        ejectorComponent = new TileComponentEjector(this, () -> Long.MAX_VALUE).setOutputData(configComponent,
                TransmissionType.CHEMICAL, TransmissionType.ITEM);
        EjectorComponentUtils.setCanChemicalTankEject(ejectorComponent, (type, tank) -> tank != inputChemicalTank);
        this.fluidInputHandler = AdvancedFluidInputHandler.create(inputFluidTank, RecipeError.NOT_ENOUGH_INPUT);
        this.chemicalInputHandler = AdvancedChemicalInputHandler.create(inputChemicalTank,
                RecipeError.NOT_ENOUGH_SECONDARY_INPUT);
        this.mainOutputHandler = OutputHelper.getOutputHandler(mainOutputTank, RecipeError.NOT_ENOUGH_OUTPUT_SPACE);
        this.subOutputHandler = OutputHelper.getOutputHandler(subOutputTank, NOT_ENOUGH_SUB_OUTPUT_SPACE);
        fluidInputSlot.setSupplyingStackSetter(fluidInputHandler::setSuppliedStack);
        chemicalInputSlot.setSupplyingStackSetter(chemicalInputHandler::setSuppliedStack);
    }

    @Override
    protected @Nullable IChemicalTankHolder getInitialChemicalTanks(IContentsListener listener,
            IContentsListener recipeCacheListener, IContentsListener recipeCacheUnpauseListener) {
        ChemicalTankHelper builder = ChemicalTankHelper.forSideWithConfig(this);
        builder.addTank(inputChemicalTank = BasicChemicalTank.inputModern(initChemicalTankCapacity(),
                (stack) -> containsRecipeBA(fluidInputHandler.getInput(), stack),
                this::containsRecipeB,
                recipeCacheListener));
        builder.addTank(
                mainOutputTank = BasicChemicalTank.output(initChemicalTankCapacity(), recipeCacheUnpauseListener));
        builder.addTank(
                subOutputTank = BasicChemicalTank.output(initChemicalTankCapacity(), recipeCacheUnpauseListener));
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
    protected @Nullable IFluidTankHolder getInitialFluidTanks(IContentsListener listener,
            IContentsListener recipeCacheListener, IContentsListener recipeCacheUnpauseListener) {
        FluidTankHelper builder = FluidTankHelper.forSideWithConfig(this);
        builder.addTank(inputFluidTank = BasicFluidTank.input(initFluidTankCapacity(),
                (stack) -> containsRecipeAB(stack, chemicalInputHandler.getInput()),
                this::containsRecipeA,
                recipeCacheListener));
        return builder.build();
    }

    protected abstract int initFluidTankCapacity();

    @Override
    protected @Nullable IInventorySlotHolder getInitialInventory(IContentsListener listener,
            IContentsListener recipeCacheListener, IContentsListener recipeCacheUnpauseListener) {
        InventorySlotHelper builder = InventorySlotHelper.forSideWithConfig(this);
        builder.addSlot(fluidInputSlot = FluidFillOrSupplierSlot.create(inputFluidTank, recipeCacheListener, 180, 71));
        builder.addSlot(chemicalInputSlot = ChemicalFillConvertOrSupplyingSlot.create(inputChemicalTank, this::getLevel,
                recipeCacheListener, 50, 56));
        builder.addSlot(mainOutputSlot = ChemicalInventorySlot.drain(mainOutputTank, listener, 89, 56));
        builder.addSlot(subOutputSlot = ChemicalInventorySlot.drain(subOutputTank, listener, 152, 56));
        builder.addSlot(fluidReturnSlot = OutputInventorySlot.at(listener, 180, 102));
        builder.addSlot(energySlot = EnergyInventorySlot.fillOrConvert(energyContainer, this::getLevel, listener,
                152, 14));
        return builder.build();
    }

    @Override
    protected boolean onUpdateServer() {
        boolean needsPacked = super.onUpdateServer();
        fluidInputSlot.fillTank(fluidReturnSlot);
        chemicalInputSlot.fillTankOrConvert();
        energySlot.fillContainerOrConvert();
        mainOutputSlot.drainTank();
        subOutputSlot.drainTank();
        clientEnergyUsed = recipeCacheLookupMonitor.updateAndProcess(energyContainer);
        return needsPacked;
    }

    @Override
    public @NotNull ICachedRecipe<FluidChemicalToBiChemicalRecipe> createNewCachedRecipe(
            @NotNull FluidChemicalToBiChemicalRecipe recipe, int cacheIndex) {
        return new FluidChemicalToBiChemicalCachedRecipe(recipe, recheckAllRecipeErrors, fluidInputHandler,
                chemicalInputHandler, mainOutputHandler, subOutputHandler)
                .setErrorsChanged(this::onErrorsChanged)
                .setCanHolderFunction(this::canFunction)
                .setActive(this::setActive)
                .setEnergyRequirements(energyContainer::getEnergyPerTick, energyContainer)
                .setOnFinish(this::markForSave)
                .setBaselineMaxOperations(this::getOperationsPerTick);
    }

    @Override
    public @Nullable FluidChemicalToBiChemicalRecipe getRecipe(int arg0) {
        return findFirstRecipe(fluidInputHandler, chemicalInputHandler);
    }

    public MachineEnergyContainer<?> getEnergyContainer() {
        return energyContainer;
    }

    public IExtendedFluidTank getInputFluidTank() {
        return inputFluidTank;
    }

    public IChemicalTank getInputChemicalTank() {
        return inputChemicalTank;
    }

    public IChemicalTank getMainOutputTank() {
        return mainOutputTank;
    }

    public IChemicalTank getSubOutputTank() {
        return subOutputTank;
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
    public @NotNull IMekALRecipeTypeProvider<?, FluidChemicalToBiChemicalRecipe, MekInDoubleInputRecipeCache.MekInFluidChemical<FluidChemicalToBiChemicalRecipe>> getRecipeType() {
        return MekInRecipeTypes.CHEMICAL_EXTRACTION;
    }

    @Override
    public IRecipeViewerRecipeType<?> recipeViewerType() {
        return MekInRecipeViewerRecipeType.CHEMICAL_EXTRACTION;
    }
}
