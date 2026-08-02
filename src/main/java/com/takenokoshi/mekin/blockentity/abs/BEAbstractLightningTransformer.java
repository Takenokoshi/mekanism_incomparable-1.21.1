package com.takenokoshi.mekin.blockentity.abs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.moakiee.ae2lt.lightning.LightningTransformRecipe;
import com.takenokoshi.mekaddonlib.blockentity.base.BEMultiScaledProgressMachine;
import com.takenokoshi.mekaddonlib.blockentity.interfaces.IHasGuiSizeOffset;
import com.takenokoshi.mekaddonlib.inventory.slot.LimitChangedInputInventorySlot;
import com.takenokoshi.mekaddonlib.inventory.slot.LimitChangedOutputInventorySlot;
import com.takenokoshi.mekaddonlib.recipe.cached.ICachedRecipe;
import com.takenokoshi.mekaddonlib.recipe.type.IMekALRecipeTypeProvider;
import com.takenokoshi.mekin.recipe.cached.LightningTransformCachedRecipe;
import com.takenokoshi.mekin.recipe.type.MekInWrappedRecipeTypes;
import com.takenokoshi.mekin.registries.MekInChemicals;
import com.takenokoshi.mekut.blockentity.interfaces.IHasMachineEnergyContainer;
import com.takenokoshi.mekut.recipe.input.ItemStackListInputHandler;
import com.takenokoshi.mekut.recipe.output.ItemOutputHandler;

import mekanism.api.IContentsListener;
import mekanism.api.RelativeSide;
import mekanism.api.chemical.BasicChemicalTank;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.chemical.IChemicalTank;
import mekanism.api.inventory.IInventorySlot;
import mekanism.api.recipes.cache.CachedRecipe.OperationTracker.RecipeError;
import mekanism.api.recipes.inputs.IInputHandler;
import mekanism.api.recipes.inputs.InputHelper;
import mekanism.api.recipes.outputs.IOutputHandler;
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
import mekanism.common.lib.transmitter.TransmissionType;
import mekanism.common.registration.impl.ItemRegistryObject;
import mekanism.common.tile.component.TileComponentEjector;
import mekanism.common.tile.component.config.DataType;
import mekanism.common.tile.component.config.slot.InventorySlotInfo;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public abstract class BEAbstractLightningTransformer extends BEMultiScaledProgressMachine<LightningTransformRecipe>
        implements IHasMachineEnergyContainer, IHasGuiSizeOffset {

    public static Consumer<ItemRegistryObject<?>> getContainerAdder(long chemicalTankCapacity) {
        return value -> {
            value.addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                    .addBasic(10)
                    .addOutput()
                    .addEnergy()
                    .build());
            value.addAttachmentOnlyContainers(ContainerType.CHEMICAL, () -> ChemicalTanksBuilder.builder()
                    .addBasic(chemicalTankCapacity)
                    .build());
        };
    }

    public static final List<RecipeError> TRACKED_ERROR_TYPES = List.of(
            RecipeError.NOT_ENOUGH_INPUT,
            RecipeError.NOT_ENOUGH_SECONDARY_INPUT,
            RecipeError.NOT_ENOUGH_OUTPUT_SPACE,
            RecipeError.NOT_ENOUGH_ENERGY,
            RecipeError.NOT_ENOUGH_ENERGY_REDUCED_RATE);

    private IChemicalTank chemicalTank;

    private MachineEnergyContainer<?> energyContainer;

    private IInventorySlot[] inputSlots;
    private ChemicalInventorySlot chemicalSlot;
    private IInventorySlot outputSlot;
    private EnergyInventorySlot energySlot;

    private final ItemStackListInputHandler itemInputHandler;
    private final IInputHandler<ChemicalStack> chemicalInputHandler;
    private final IOutputHandler<ItemStack> outputHandler;

    public BEAbstractLightningTransformer(Holder<Block> blockProvider, BlockPos pos, BlockState state,
            int baselineMaxOperations) {
        super(blockProvider, pos, state, TRACKED_ERROR_TYPES, baselineMaxOperations, r -> 100);
        var itemConfig = configComponent.setupItemIOConfig(List.of(inputSlots), List.of(outputSlot), energySlot, false);
        itemConfig.addSlotInfo(DataType.EXTRA, new InventorySlotInfo(true, true, chemicalSlot));
        itemConfig.setDataType(DataType.EXTRA, RelativeSide.BOTTOM);

        configComponent.setupInputConfig(TransmissionType.CHEMICAL, chemicalTank);
        configComponent.setupInputConfig(TransmissionType.ENERGY, energyContainer);

        ejectorComponent = new TileComponentEjector(this).setOutputData(configComponent, TransmissionType.ITEM);

        this.itemInputHandler = new ItemStackListInputHandler(List.of(inputSlots), RecipeError.NOT_ENOUGH_INPUT);
        this.chemicalInputHandler = InputHelper.getInputHandler(chemicalTank, RecipeError.NOT_ENOUGH_SECONDARY_INPUT);
        this.outputHandler = new ItemOutputHandler(outputSlot, RecipeError.NOT_ENOUGH_OUTPUT_SPACE);
    }

    @NotNull
    @Override
    protected IEnergyContainerHolder getInitialEnergyContainers(IContentsListener listener,
            IContentsListener recipeCacheListener, IContentsListener recipeCacheUnpauseListener) {
        EnergyContainerHelper builder = EnergyContainerHelper.forSideWithConfig(this);
        builder.addContainer(energyContainer = MachineEnergyContainer.input(this, recipeCacheUnpauseListener));
        return builder.build();
    }

    @Override
    protected @Nullable IChemicalTankHolder getInitialChemicalTanks(IContentsListener listener,
            IContentsListener recipeCacheListener, IContentsListener recipeCacheUnpauseListener) {
        ChemicalTankHelper builder = ChemicalTankHelper.forSideWithConfig(this);
        builder.addTank(chemicalTank = BasicChemicalTank.inputModern(initChemicalTankCapacity(),
                stack -> stack.is(MekInChemicals.THUNDERCLOUD) || stack.is(MekInChemicals.HIGH_VOLTAGE_LIGHTNING),
                recipeCacheListener));
        return builder.build();
    }

    protected abstract long initChemicalTankCapacity();

    @Override
    protected @Nullable IInventorySlotHolder getInitialInventory(IContentsListener listener,
            IContentsListener recipeCacheListener, IContentsListener recipeCacheUnpauseListener) {
        InventorySlotHelper builder = InventorySlotHelper.forSideWithConfig(this);
        inputSlots = new IInventorySlot[9];
        for (int index = 0; index < 9; index++) {
            int p = index;
            builder.addSlot(inputSlots[index] = LimitChangedInputInventorySlot.at(
                    stack -> MekInWrappedRecipeTypes.LIGHTNING_TRANSFORM.getInputCache().containsInput(getLevel(),
                            stack, getItemsInOtherSlots(p)),
                    stack -> MekInWrappedRecipeTypes.LIGHTNING_TRANSFORM.getInputCache().containsInput(getLevel(),
                            stack),
                    recipeCacheListener, 31 + index % 3 * 18, 22 + index / 3 * 18, initItemSlotCapacity()));
        }
        builder.addSlot(
                chemicalSlot = ChemicalInventorySlot.fill(chemicalTank, listener, 6, 58));
        builder.addSlot(outputSlot = LimitChangedOutputInventorySlot.at(recipeCacheUnpauseListener,
                129, 40, initItemSlotCapacity()));
        builder.addSlot(energySlot = EnergyInventorySlot.fillOrConvert(energyContainer, this::getLevel, listener,
                154, 22));
        return builder.build();
    }

    protected abstract int initItemSlotCapacity();

    private List<ItemStack> getItemsInOtherSlots(int slotIndex) {
        List<ItemStack> result = new ArrayList<>();
        for (int i = 0; i < inputSlots.length; i++) {
            if (i != slotIndex && !inputSlots[i].isEmpty()) {
                result.add(inputSlots[i].getStack());
            }
        }
        return result;
    }

    private List<ItemStack> getItems() {
        return Arrays.stream(inputSlots).filter(Predicate.not(IInventorySlot::isEmpty)).map(IInventorySlot::getStack).toList();
    }

    @Override
    public int getExtraWidth() {
        return 14;
    }

    @Override
    public int getExtraHeight() {
        return 5;
    }

    @Override
    public @NotNull ICachedRecipe<LightningTransformRecipe> createNewCachedRecipe(
            @NotNull LightningTransformRecipe recipe, int arg1) {
        return new LightningTransformCachedRecipe(recipe, recheckAllRecipeErrors, itemInputHandler,
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
    public @Nullable LightningTransformRecipe getRecipe(int arg0) {
        return MekInWrappedRecipeTypes.LIGHTNING_TRANSFORM.getInputCache().findFirstRecipe(level, getItems());
    }

    @Override
    public @NotNull IMekALRecipeTypeProvider<?, LightningTransformRecipe, ?> getRecipeType() {
        return MekInWrappedRecipeTypes.LIGHTNING_TRANSFORM;
    }

    @Override
    protected boolean onUpdateServer() {
        boolean sendUpdatePacket = super.onUpdateServer();
        chemicalSlot.fillTank();
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

}
