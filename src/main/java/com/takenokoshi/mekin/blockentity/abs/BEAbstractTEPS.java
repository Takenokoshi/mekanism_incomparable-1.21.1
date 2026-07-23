package com.takenokoshi.mekin.blockentity.abs;

import java.util.function.Consumer;
import java.util.function.LongUnaryOperator;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.takenokoshi.mekaddonlib.blockentity.base.BEMultiScaledProgressMachine;
import com.takenokoshi.mekaddonlib.blockentity.interfaces.IHasGuiSizeOffset;
import com.takenokoshi.mekaddonlib.recipe.cached.ICachedRecipe;
import com.takenokoshi.mekaddonlib.recipe.type.IMekALRecipeTypeProvider;
import com.takenokoshi.mekaddonlib.registration.BlockEntityConstructor;
import com.takenokoshi.mekin.recipe_viewer.type.MekInRecipeViewerRecipeType;
import com.takenokoshi.mekin.registries.MekInRecipeTypes;
import com.takenokoshi.mekut.blockentity.interfaces.IHasMachineEnergyContainer;
import com.takenokoshi.mekut.blockentity.interfaces.IRecipeViewerTypeProvider;
import com.takenokoshi.mekut.blockentity.interfaces.machine.IItemStackChemicalToItemStackMachine;
import com.takenokoshi.mekut.recipe.cached.AdvancedItemStackChemicalToItemStackCachedRecipe;
import com.takenokoshi.mekut.recipe.inputcache.MekUtDoubleInputRecipeCache.MekUtItemChemical;

import mekanism.api.AutomationType;
import mekanism.api.IContentsListener;
import mekanism.api.chemical.BasicChemicalTank;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.chemical.IChemicalTank;
import mekanism.api.chemical.attribute.ChemicalAttributeValidator;
import mekanism.api.recipes.ItemStackChemicalToItemStackRecipe;
import mekanism.api.recipes.cache.CachedRecipe.OperationTracker.RecipeError;
import mekanism.api.recipes.inputs.IInputHandler;
import mekanism.api.recipes.inputs.InputHelper;
import mekanism.api.recipes.outputs.IOutputHandler;
import mekanism.api.recipes.outputs.OutputHelper;
import mekanism.client.recipe_viewer.type.IRecipeViewerRecipeType;
import mekanism.common.attachments.containers.ContainerType;
import mekanism.common.attachments.containers.chemical.ChemicalTanksBuilder;
import mekanism.common.attachments.containers.item.ItemSlotsBuilder;
import mekanism.common.block.prefab.BlockTile;
import mekanism.common.capabilities.energy.MachineEnergyContainer;
import mekanism.common.capabilities.holder.chemical.ChemicalTankHelper;
import mekanism.common.capabilities.holder.chemical.IChemicalTankHolder;
import mekanism.common.capabilities.holder.energy.EnergyContainerHelper;
import mekanism.common.capabilities.holder.energy.IEnergyContainerHolder;
import mekanism.common.capabilities.holder.slot.IInventorySlotHolder;
import mekanism.common.capabilities.holder.slot.InventorySlotHelper;
import mekanism.common.content.blocktype.Machine;
import mekanism.common.inventory.slot.BasicInventorySlot;
import mekanism.common.inventory.slot.EnergyInventorySlot;
import mekanism.common.inventory.slot.InputInventorySlot;
import mekanism.common.inventory.slot.OutputInventorySlot;
import mekanism.common.inventory.slot.chemical.ChemicalInventorySlot;
import mekanism.common.inventory.warning.WarningTracker.WarningType;
import mekanism.common.registration.impl.ItemRegistryObject;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public abstract class BEAbstractTEPS extends BEMultiScaledProgressMachine<ItemStackChemicalToItemStackRecipe>
        implements IItemStackChemicalToItemStackMachine, IHasMachineEnergyContainer, IHasGuiSizeOffset,
        IRecipeViewerTypeProvider {

    BasicInventorySlot inputSlot;
    BasicInventorySlot outputSlot;
    ChemicalInventorySlot secondarySlot;
    EnergyInventorySlot energySlot;

    private IChemicalTank chemicalTank;
    private MachineEnergyContainer<?> energyContainer;

    protected final IOutputHandler<@NotNull ItemStack> outputHandler;
    protected final IInputHandler<@NotNull ItemStack> itemInputHandler;
    protected final IInputHandler<@NotNull ChemicalStack> chemicalInputHandler;

    protected BEAbstractTEPS(Holder<Block> blockProvider, BlockPos pos, BlockState state,
            int baselineMaxOperations) {
        super(blockProvider, pos, state, TRACKED_ERROR_TYPES, baselineMaxOperations, r -> 200);
        ejectorComponent = IItemStackChemicalToItemStackMachine.setUpConfig(this, configComponent, inputSlot,
                outputSlot, secondarySlot, energySlot, chemicalTank, energyContainer);
        itemInputHandler = InputHelper.getInputHandler(inputSlot, RecipeError.NOT_ENOUGH_INPUT);
        chemicalInputHandler = InputHelper.getInputHandler(chemicalTank, RecipeError.NOT_ENOUGH_SECONDARY_INPUT);
        outputHandler = OutputHelper.getOutputHandler(outputSlot, RecipeError.NOT_ENOUGH_OUTPUT_SPACE);
    }

    public static BlockEntityConstructor<BEAbstractTEPS, Machine<BEAbstractTEPS>, BlockTile.BlockTileModel<BEAbstractTEPS, Machine<BEAbstractTEPS>>> getConstructor(
            int baselineMaxOperationsValue) {
        return (p, q, r) -> new BEAbstractTEPS(p, q, r, baselineMaxOperationsValue) {
            @Override
            protected long initChemicalTankCapacity() {
                return baselineMaxOperationsValue * 10000l;
            }
        };
    }

    public static Consumer<ItemRegistryObject<?>> getContainerAddar(int baselineMaxOperations) {
        return registry -> {
            registry
                    .addAttachmentOnlyContainers(ContainerType.ITEM,
                            () -> ItemSlotsBuilder.builder()
                                    .addInput(1)
                                    .addChemicalFillOrConvertSlot(0)
                                    .addOutput(1)
                                    .addEnergy().build())
                    .addAttachmentOnlyContainers(ContainerType.CHEMICAL,
                            () -> ChemicalTanksBuilder.builder()
                                    .addBasic(10000l * baselineMaxOperations)
                                    .build());
        };
    }

    @NotNull
    @Override
    public IChemicalTankHolder getInitialChemicalTanks(IContentsListener listener,
            IContentsListener recipeCacheListener, IContentsListener recipeCacheUnpauseListener) {
        ChemicalTankHelper builder = ChemicalTankHelper.forSideWithConfig(this);
        builder.addTank(chemicalTank = BasicChemicalTank.create(initChemicalTankCapacity(),
                (gas, automationType) -> automationType == AutomationType.MANUAL,
                (gas, automationType) -> containsRecipeBA(inputSlot.getStack(), gas.getStack(Long.MAX_VALUE)),
                gas -> containsRecipeB(gas.getStack(Long.MAX_VALUE)),
                ChemicalAttributeValidator.ALWAYS_ALLOW,
                recipeCacheListener));
        return builder.build();
    }

    protected abstract long initChemicalTankCapacity();

    @Override
    protected IEnergyContainerHolder getInitialEnergyContainers(IContentsListener listener,
            IContentsListener recipeCacheListener, IContentsListener recipeCacheUnpauseListener) {
        EnergyContainerHelper builder = EnergyContainerHelper.forSideWithConfig(this);
        builder.addContainer(energyContainer = MachineEnergyContainer.input(this, recipeCacheUnpauseListener));
        return builder.build();
    }

    @NotNull
    @Override
    protected IInventorySlotHolder getInitialInventory(IContentsListener listener,
            IContentsListener recipeCacheListener, IContentsListener recipeCacheUnpauseListener) {
        InventorySlotHelper builder = InventorySlotHelper.forSideWithConfig(this);
        builder.addSlot(inputSlot = InputInventorySlot.at(item -> containsRecipeAB(item, chemicalTank.getStack()),
                this::containsRecipeA, recipeCacheListener, 64, 17))
                .tracksWarnings(slot -> slot.warning(WarningType.NO_MATCHING_RECIPE,
                        getWarningCheck(RecipeError.NOT_ENOUGH_INPUT)));
        builder.addSlot(
                secondarySlot = ChemicalInventorySlot.fillOrConvert(chemicalTank, this::getLevel, listener, 64, 53));
        builder.addSlot(outputSlot = OutputInventorySlot.at(recipeCacheUnpauseListener, 116, 35))
                .tracksWarnings(slot -> slot.warning(WarningType.NO_SPACE_IN_OUTPUT,
                        getWarningCheck(RecipeError.NOT_ENOUGH_OUTPUT_SPACE)));
        builder.addSlot(
                energySlot = EnergyInventorySlot.fillOrConvert(energyContainer, this::getLevel, listener, 39, 35));
        return builder.build();
    }

    @Override
    public int getExtraHeight() {
        return 16;
    }

    @Override
    protected boolean onUpdateServer() {
        boolean sendUpdatePacket = super.onUpdateServer();
        energySlot.fillContainerOrConvert();
        secondarySlot.fillTankOrConvert();
        clientEnergyUsed = recipeCacheLookupMonitor.updateAndProcess(energyContainer);
        return sendUpdatePacket;
    }

    @Override
    public @NotNull IMekALRecipeTypeProvider<?, ItemStackChemicalToItemStackRecipe, MekUtItemChemical<ItemStackChemicalToItemStackRecipe>> getRecipeType() {
        return MekInRecipeTypes.TEPS;
    }

    @Override
    public @NotNull ICachedRecipe<ItemStackChemicalToItemStackRecipe> createNewCachedRecipe(
            @NotNull ItemStackChemicalToItemStackRecipe recipe, int cacheIndex) {
        return new AdvancedItemStackChemicalToItemStackCachedRecipe(recipe, recheckAllRecipeErrors, itemInputHandler,
                chemicalInputHandler, outputHandler, LongUnaryOperator.identity())
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
    public @Nullable ItemStackChemicalToItemStackRecipe getRecipe(int cacheIndex) {
        return findFirstRecipe(itemInputHandler, chemicalInputHandler);
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
    public @Nullable IRecipeViewerRecipeType<ItemStackChemicalToItemStackRecipe> recipeViewerType() {
        return MekInRecipeViewerRecipeType.TEPS;
    }

}
