package com.takenokoshi.mekin.blockentity.machine;

import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.takenokoshi.mekaddonlib.blockentity.base.BEMultiScaledProgressMachine;
import com.takenokoshi.mekaddonlib.inventory.slot.LimitChangedInputInventorySlot;
import com.takenokoshi.mekaddonlib.inventory.slot.LimitChangedOutputInventorySlot;
import com.takenokoshi.mekaddonlib.recipe.cached.ICachedRecipe;
import com.takenokoshi.mekaddonlib.recipe.type.IMekALRecipeTypeProvider;
import com.takenokoshi.mekin.recipe.cached.MeteorCollectorCachedRecipe;
import com.takenokoshi.mekin.recipe.inputcache.MeteorCollectorInputRecipeCache;
import com.takenokoshi.mekin.recipe.lookup.recipe.IMeteorCollectorRecipeLookupHandler;
import com.takenokoshi.mekin.recipe.output.MeteorCollectorOutputHandler;
import com.takenokoshi.mekin.recipe.recipes.prefab.MeteorCollectorRecipe;
import com.takenokoshi.mekin.registries.MekInRecipeTypes;
import com.takenokoshi.mekut.blockentity.interfaces.IHasMachineEnergyContainer;

import mekanism.api.IContentsListener;
import mekanism.api.inventory.IInventorySlot;
import mekanism.api.recipes.cache.CachedRecipe.OperationTracker.RecipeError;
import mekanism.api.recipes.inputs.IInputHandler;
import mekanism.api.recipes.inputs.InputHelper;
import mekanism.common.attachments.containers.ContainerType;
import mekanism.common.attachments.containers.item.ItemSlotsBuilder;
import mekanism.common.capabilities.energy.MachineEnergyContainer;
import mekanism.common.capabilities.holder.energy.EnergyContainerHelper;
import mekanism.common.capabilities.holder.energy.IEnergyContainerHolder;
import mekanism.common.capabilities.holder.slot.IInventorySlotHolder;
import mekanism.common.capabilities.holder.slot.InventorySlotHelper;
import mekanism.common.inventory.slot.EnergyInventorySlot;
import mekanism.common.inventory.slot.InputInventorySlot;
import mekanism.common.lib.transmitter.TransmissionType;
import mekanism.common.registration.impl.ItemRegistryObject;
import mekanism.common.tile.component.TileComponentEjector;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BEMeteorCollector extends BEMultiScaledProgressMachine<MeteorCollectorRecipe>
        implements IMeteorCollectorRecipeLookupHandler, IHasMachineEnergyContainer {

    private static final List<RecipeError> TRACKED_ERROR_TYPES = List.of(
            RecipeError.NOT_ENOUGH_ENERGY,
            RecipeError.NOT_ENOUGH_ENERGY_REDUCED_RATE,
            RecipeError.NOT_ENOUGH_INPUT,
            RecipeError.NOT_ENOUGH_SECONDARY_INPUT,
            RecipeError.NOT_ENOUGH_OUTPUT_SPACE,
            RecipeError.INPUT_DOESNT_PRODUCE_OUTPUT);

    public static void addContainersToItem(ItemRegistryObject<?> value) {
        value.addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                .addInput(2)
                .addOutput()
                .addOutput()
                .addOutput()
                .addOutput()
                .addOutput()
                .addOutput()
                .addOutput()
                .addOutput()
                .addOutput()
                .addEnergy()
                .build());
    }

    private MachineEnergyContainer<?> energyContainer;

    private IInventorySlot inputSlot;
    private IInventorySlot catalystSlot;
    private IInventorySlot[] outputSlots;
    private EnergyInventorySlot energySlot;

    private final IInputHandler<ItemStack> inputHandler;
    private final IInputHandler<ItemStack> catalystHandler;
    private final MeteorCollectorOutputHandler outputHandler;

    private final boolean isAdvancedMachine;

    public BEMeteorCollector(Holder<Block> blockProvider, BlockPos pos, BlockState state,
            boolean isAdvancedMachine) {
        super(blockProvider, pos, state, TRACKED_ERROR_TYPES, 1, r -> 1000);
        configComponent.setupItemIOConfig(List.of(inputSlot), List.of(outputSlots), energySlot, false);
        configComponent.setupInputConfig(TransmissionType.ENERGY, energyContainer);
        ejectorComponent = new TileComponentEjector(this).setOutputData(configComponent, TransmissionType.ITEM);
        this.isAdvancedMachine = isAdvancedMachine;
        this.inputHandler = InputHelper.getInputHandler(inputSlot, RecipeError.NOT_ENOUGH_INPUT);
        this.catalystHandler = InputHelper.getInputHandler(catalystSlot, RecipeError.NOT_ENOUGH_SECONDARY_INPUT);
        this.outputHandler = new MeteorCollectorOutputHandler(outputSlots, RecipeError.NOT_ENOUGH_OUTPUT_SPACE);
    }

    public static BEMeteorCollector basic(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        return new BEMeteorCollector(blockProvider, pos, state, false);
    }

    public static BEMeteorCollector advanced(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        return new BEMeteorCollector(blockProvider, pos, state, true);
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
    protected @Nullable IInventorySlotHolder getInitialInventory(IContentsListener listener,
            IContentsListener recipeCacheListener, IContentsListener recipeCacheUnpauseListener) {
        InventorySlotHelper builder = InventorySlotHelper.forSideWithConfig(this);
        builder.addSlot(inputSlot = LimitChangedInputInventorySlot.at(
                (stack) -> containsInputCatalyst(stack, catalystSlot.getStack()),
                this::containsInput,
                recipeCacheListener,
                46, 17, 1_000_000_000));
        builder.addSlot(catalystSlot = InputInventorySlot.at(
                (stack) -> containsCatalystInput(inputSlot.getStack(), stack),
                this::containsCatalyst,
                recipeCacheListener,
                46, 53));
        this.outputSlots = new LimitChangedOutputInventorySlot[9];
        for (int i = 0; i < outputSlots.length; i++) {
            builder.addSlot(outputSlots[i] = LimitChangedOutputInventorySlot.at(
                    recipeCacheUnpauseListener,
                    98 + 18 * (i % 3), 17 + 18 * (i / 3), 1_000_000_000));
        }
        builder.addSlot(energySlot = EnergyInventorySlot.fillOrConvert(energyContainer, this::getLevel,
                listener,
                21, 35));
        return builder.build();
    }

    @Override
    protected boolean onUpdateServer() {
        boolean sendUpdatePacket = super.onUpdateServer();
        energySlot.fillContainerOrConvert();
        clientEnergyUsed = recipeCacheLookupMonitor.updateAndProcess(energyContainer);
        return sendUpdatePacket;
    }

    @Override
    public @Nullable MeteorCollectorRecipe getRecipe(int arg0) {
        return findFirstRecipe(inputHandler, catalystHandler);
    }

    @Override
    public @NotNull IMekALRecipeTypeProvider<?, MeteorCollectorRecipe, MeteorCollectorInputRecipeCache> getRecipeType() {
        return MekInRecipeTypes.METEOR_COLLECTOR;
    }

    @Override
    public @NotNull ICachedRecipe<MeteorCollectorRecipe> createNewCachedRecipe(@NotNull MeteorCollectorRecipe recipe,
            int arg1) {
        return new MeteorCollectorCachedRecipe(recipe, recheckAllRecipeErrors, inputHandler, catalystHandler,
                this::getLevel, outputHandler, isAdvancedMachine)
                .setErrorsChanged(this::onErrorsChanged)
                .setCanHolderFunction(this::canFunction)
                .setActive(this::setActive)
                .setEnergyRequirements(energyContainer::getEnergyPerTick, energyContainer)
                .setRequiredTicks(this::getTicksRequired)
                .setOnFinish(this::markForSave)
                .setOperatingTicksChanged(this::setOperatingTicks);
    }

    @Override
    public MachineEnergyContainer<?> getEnergyContainer() {
        return energyContainer;
    }

}
