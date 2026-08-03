package com.takenokoshi.mekin.blockentity.timespacemachine;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.takenokoshi.mekaddonlib.blockentity.interfaces.IHasGuiSizeOffset;
import com.takenokoshi.mekaddonlib.blockentity.interfaces.IWarningSupporter;
import com.takenokoshi.mekaddonlib.inventory.slot.LimitChangedOutputInventorySlot;
import com.takenokoshi.mekut.blockentity.interfaces.IHasInputChemicalTank;
import com.takenokoshi.mekut.blockentity.interfaces.IHasMachineEnergyContainer;
import com.takenokoshi.mekut.blockentity.interfaces.IRecipeViewerTypeProvider;
import com.takenokoshi.mekut.blockentity.interfaces.IScaledProgressProvider;
import com.takenokoshi.mekut.blockentity.interfaces.machine.IItemStackChemicalToItemStackMachine;
import com.takenokoshi.mekut.inventory.slot.ChemicalFillConvertOrSupplyingSlot;
import com.takenokoshi.mekut.inventory.slot.InputOrSupplyingSlot;
import com.takenokoshi.mekut.recipe.input.AdvancedChemicalInputHandler;
import com.takenokoshi.mekut.recipe.input.AdvancedItemInputHandler;
import com.takenokoshi.mekut.recipe.output.ItemOutputHandler;

import fr.iglee42.evolvedmekanism.registries.EMRecipeType;
import mekanism.api.AutomationType;
import mekanism.api.IContentsListener;
import mekanism.api.chemical.BasicChemicalTank;
import mekanism.api.chemical.IChemicalTank;
import mekanism.api.chemical.attribute.ChemicalAttributeValidator;
import mekanism.api.recipes.ItemStackChemicalToItemStackRecipe;
import mekanism.api.recipes.cache.CachedRecipe;
import mekanism.api.recipes.cache.CachedRecipe.OperationTracker.RecipeError;
import mekanism.api.recipes.cache.TwoInputCachedRecipe;
import mekanism.client.recipe_viewer.type.IRecipeViewerRecipeType;
import mekanism.common.capabilities.energy.MachineEnergyContainer;
import mekanism.common.capabilities.holder.chemical.ChemicalTankHelper;
import mekanism.common.capabilities.holder.chemical.IChemicalTankHolder;
import mekanism.common.capabilities.holder.energy.EnergyContainerHelper;
import mekanism.common.capabilities.holder.energy.IEnergyContainerHolder;
import mekanism.common.capabilities.holder.slot.IInventorySlotHolder;
import mekanism.common.capabilities.holder.slot.InventorySlotHelper;
import mekanism.common.inventory.container.MekanismContainer;
import mekanism.common.inventory.container.sync.SyncableLong;
import mekanism.common.inventory.slot.BasicInventorySlot;
import mekanism.common.inventory.slot.EnergyInventorySlot;
import mekanism.common.inventory.warning.WarningTracker.WarningType;
import mekanism.common.recipe.IMekanismRecipeTypeProvider;
import mekanism.common.recipe.lookup.IDoubleRecipeLookupHandler.ItemChemicalRecipeLookupHandler;
import mekanism.common.recipe.lookup.cache.InputRecipeCache;
import mekanism.common.tile.prefab.TileEntityRecipeMachine;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BETimeSpaceAPT extends TileEntityRecipeMachine<ItemStackChemicalToItemStackRecipe>
        implements IHasMachineEnergyContainer, IWarningSupporter, IHasGuiSizeOffset,
        ItemChemicalRecipeLookupHandler<ItemStackChemicalToItemStackRecipe>, IHasInputChemicalTank,
        IScaledProgressProvider, IRecipeViewerTypeProvider {

    protected long clientEnergyUsed = 0;
    InputOrSupplyingSlot inputSlot;
    BasicInventorySlot outputSlot;
    ChemicalFillConvertOrSupplyingSlot secondarySlot;
    EnergyInventorySlot energySlot;

    private IChemicalTank chemicalTank;
    private MachineEnergyContainer<?> energyContainer;

    protected final ItemOutputHandler outputHandler;
    protected final AdvancedItemInputHandler itemInputHandler;
    protected final AdvancedChemicalInputHandler chemicalInputHandler;

    public BETimeSpaceAPT(Holder<Block> blockProvider, BlockPos pos, BlockState state,
            List<RecipeError> errorTypes) {
        super(blockProvider, pos, state, IItemStackChemicalToItemStackMachine.TRACKED_ERROR_TYPES);
        ejectorComponent = IItemStackChemicalToItemStackMachine.setUpConfig(this, configComponent, inputSlot,
                outputSlot, secondarySlot, energySlot, chemicalTank, energyContainer);
        itemInputHandler = AdvancedItemInputHandler.create(inputSlot, RecipeError.NOT_ENOUGH_INPUT);
        chemicalInputHandler = AdvancedChemicalInputHandler.create(chemicalTank,
                RecipeError.NOT_ENOUGH_SECONDARY_INPUT);
        outputHandler = new ItemOutputHandler(outputSlot, RecipeError.NOT_ENOUGH_OUTPUT_SPACE);
    }

    @SuppressWarnings("removal")
    @NotNull
    @Override
    public IChemicalTankHolder getInitialChemicalTanks(IContentsListener listener,
            IContentsListener recipeCacheListener, IContentsListener recipeCacheUnpauseListener) {
        ChemicalTankHelper builder = ChemicalTankHelper.forSideWithConfig(this);
        builder.addTank(chemicalTank = BasicChemicalTank.create(Long.MAX_VALUE,
                (gas, automationType) -> automationType == AutomationType.MANUAL,
                (gas, automationType) -> containsRecipeBA(inputSlot.getStack(), gas.getStack(Long.MAX_VALUE)),
                gas -> containsRecipeB(gas.getStack(Long.MAX_VALUE)),
                ChemicalAttributeValidator.ALWAYS_ALLOW,
                recipeCacheListener));
        return builder.build();
    }

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
        builder.addSlot(inputSlot = InputOrSupplyingSlot.at(item -> containsRecipeAB(item, chemicalTank.getStack()),
                this::containsRecipeA, recipeCacheListener, 28, 40, 0x7fffffff))
                .tracksWarnings(slot -> slot.warning(WarningType.NO_MATCHING_RECIPE,
                        getWarningCheck(RecipeError.NOT_ENOUGH_INPUT)));
        builder.addSlot(
                secondarySlot = ChemicalFillConvertOrSupplyingSlot.create(chemicalTank, this::getLevel, listener, 8,
                        58));
        builder.addSlot(
                outputSlot = LimitChangedOutputInventorySlot.at(recipeCacheUnpauseListener, 132, 40, 0x7fffffff))
                .tracksWarnings(slot -> slot.warning(WarningType.NO_SPACE_IN_OUTPUT,
                        getWarningCheck(RecipeError.NOT_ENOUGH_OUTPUT_SPACE)));
        builder.addSlot(
                energySlot = EnergyInventorySlot.fillOrConvert(energyContainer, this::getLevel, listener, 152, 58));
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
    public @NotNull IMekanismRecipeTypeProvider<?, ItemStackChemicalToItemStackRecipe, InputRecipeCache.ItemChemical<ItemStackChemicalToItemStackRecipe>> getRecipeType() {
        return EMRecipeType.APT;
    }

    @Override
    public @NotNull CachedRecipe<ItemStackChemicalToItemStackRecipe> createNewCachedRecipe(
            @NotNull ItemStackChemicalToItemStackRecipe recipe, int arg1) {
        return TwoInputCachedRecipe
                .itemChemicalToItem(recipe, recheckAllRecipeErrors, itemInputHandler, chemicalInputHandler,
                        outputHandler)
                .setErrorsChanged(this::onErrorsChanged)
                .setCanHolderFunction(this::canFunction)
                .setActive(this::setActive)
                .setEnergyRequirements(energyContainer::getEnergyPerTick, energyContainer)
                .setOnFinish(this::markForSave)
                .setBaselineMaxOperations(() -> 0x7fffffff);
    }

    @Override
    public @Nullable ItemStackChemicalToItemStackRecipe getRecipe(int arg0) {

        return findFirstRecipe(itemInputHandler, chemicalInputHandler);
    }

    @Override
    public void addContainerTrackers(MekanismContainer container) {
        super.addContainerTrackers(container);
        container.track(SyncableLong.create(this::getEnergyUsed, v -> clientEnergyUsed = v));
    }

    @Override
    public long getEnergyUsed() {
        return clientEnergyUsed;
    }

    @Override
    public IChemicalTank getInputTank() {
        return chemicalTank;
    }

    @Override
    public MachineEnergyContainer<?> getEnergyContainer() {
        return energyContainer;
    }

    @Override
    public @Nullable IRecipeViewerRecipeType<ItemStackChemicalToItemStackRecipe> recipeViewerType() {
        return null;
    }

    @Override
    public double getScaledProgress() {
        return getActive() ? 1.0d : 0.0d;
    }

}
