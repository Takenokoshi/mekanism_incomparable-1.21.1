package com.takenokoshi.mekin.blockentity.machine;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.jetbrains.annotations.Nullable;

import com.takenokoshi.mekin.registries.MekInFluids;

import mekanism.api.Action;
import mekanism.api.AutomationType;
import mekanism.api.IContentsListener;
import mekanism.api.RelativeSide;
import mekanism.api.energy.IEnergyContainer;
import mekanism.api.fluid.IExtendedFluidTank;
import mekanism.common.attachments.component.AttachedSideConfig;
import mekanism.common.attachments.containers.ContainerType;
import mekanism.common.attachments.containers.energy.EnergyContainersBuilder;
import mekanism.common.attachments.containers.fluid.FluidTanksBuilder;
import mekanism.common.attachments.containers.item.ItemSlotsBuilder;
import mekanism.common.capabilities.energy.BasicEnergyContainer;
import mekanism.common.capabilities.fluid.BasicFluidTank;
import mekanism.common.capabilities.holder.energy.EnergyContainerHelper;
import mekanism.common.capabilities.holder.energy.IEnergyContainerHolder;
import mekanism.common.capabilities.holder.fluid.FluidTankHelper;
import mekanism.common.capabilities.holder.fluid.IFluidTankHolder;
import mekanism.common.capabilities.holder.slot.IInventorySlotHolder;
import mekanism.common.capabilities.holder.slot.InventorySlotHelper;
import mekanism.common.inventory.container.MekanismContainer;
import mekanism.common.inventory.container.sync.SyncableBoolean;
import mekanism.common.inventory.slot.EnergyInventorySlot;
import mekanism.common.inventory.slot.FluidInventorySlot;
import mekanism.common.inventory.slot.OutputInventorySlot;
import mekanism.common.lib.transmitter.TransmissionType;
import mekanism.common.registration.impl.ItemRegistryObject;
import mekanism.common.tile.component.TileComponentEjector;
import mekanism.common.tile.component.config.DataType;
import mekanism.common.tile.component.config.slot.InventorySlotInfo;
import mekanism.common.tile.interfaces.IHasMode;
import mekanism.common.tile.prefab.TileEntityConfigurableMachine;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BEFluxCondenser extends TileEntityConfigurableMachine implements IHasMode {

    public static final long ENERGY_PER_FLUX = 5_000_000_000L;

    public static void addContainrsToItem(ItemRegistryObject<?> value) {
        value.addAttachmentOnlyContainers(ContainerType.FLUID, () -> FluidTanksBuilder.builder()
                .addBasic(0x7fffffff)
                .build());
        value.addAttachmentOnlyContainers(ContainerType.ENERGY, () -> EnergyContainersBuilder.builder()
                .addBasic(() -> ENERGY_PER_FLUX, () -> Long.MAX_VALUE)
                .build());
        value.addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
                .addBasic(3)
                .addOutput()
                .build());
    }

    public static final AttachedSideConfig SIDE_CONFIG = Util.make(() -> {
        Map<TransmissionType, AttachedSideConfig.LightConfigInfo> configInfo = new EnumMap<>(TransmissionType.class);
        configInfo.put(TransmissionType.ENERGY, AttachedSideConfig.LightConfigInfo.OUT_EJECT);
        configInfo.put(TransmissionType.FLUID, AttachedSideConfig.LightConfigInfo.OUT_EJECT);
        configInfo.put(TransmissionType.ITEM, AttachedSideConfig.LightConfigInfo.OUT_EJECT);
        return new AttachedSideConfig(configInfo);
    });

    /**
     * True: fluid -> energy
     * <p>
     * False: energy -> fluid
     */
    private boolean mode;

    protected IEnergyContainer energyContainer;
    protected IExtendedFluidTank fluidTank;

    protected EnergyInventorySlot energyInputSlot;
    protected EnergyInventorySlot energyOutputSlot;
    protected FluidInventorySlot fluidSlot;
    protected OutputInventorySlot fluidReturnSlot;

    public BEFluxCondenser(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state);
        configComponent.setupIOConfig(TransmissionType.ENERGY, energyContainer, RelativeSide.RIGHT);
        configComponent.setupIOConfig(TransmissionType.FLUID, fluidTank, RelativeSide.RIGHT);
        var itemConfig = configComponent.getConfig(TransmissionType.ITEM);
        itemConfig.addSlotInfo(DataType.INPUT,
                new InventorySlotInfo(true, false, List.of(fluidSlot, energyInputSlot, energyOutputSlot)));
        itemConfig.addSlotInfo(DataType.OUTPUT,
                new InventorySlotInfo(false, true, List.of(fluidReturnSlot, energyInputSlot, energyOutputSlot)));
        itemConfig.addSlotInfo(DataType.INPUT_OUTPUT,
                new InventorySlotInfo(true, true,
                        List.of(fluidSlot, fluidReturnSlot, energyInputSlot, energyOutputSlot)));
        itemConfig.setCanEject(true);
        itemConfig.setEjecting(true);
        itemConfig.setDataType(DataType.INPUT, RelativeSide.TOP);
        itemConfig.setDataType(DataType.INPUT, RelativeSide.BOTTOM);
        itemConfig.setDataType(DataType.INPUT, RelativeSide.FRONT);
        itemConfig.setDataType(DataType.INPUT, RelativeSide.BACK);
        itemConfig.setDataType(DataType.INPUT, RelativeSide.LEFT);
        itemConfig.setDataType(DataType.OUTPUT, RelativeSide.RIGHT);
        ejectorComponent = new TileComponentEjector(this, () -> 0, () -> 0x7fffffff, () -> Long.MAX_VALUE)
                .setOutputData(configComponent, TransmissionType.ENERGY, TransmissionType.FLUID, TransmissionType.ITEM);
    }

    @Override
    protected @Nullable IEnergyContainerHolder getInitialEnergyContainers(IContentsListener listener) {
        EnergyContainerHelper builder = EnergyContainerHelper.forSideWithConfig(this);
        builder.addContainer(energyContainer = BasicEnergyContainer.create(Long.MAX_VALUE, listener));
        return builder.build();
    }

    @Override
    protected @Nullable IFluidTankHolder getInitialFluidTanks(IContentsListener listener) {
        FluidTankHelper builder = FluidTankHelper.forSideWithConfig(this);
        builder.addTank(fluidTank = BasicFluidTank.create(0x7fffffff,
                stack -> MekInFluids.FLUX.is(stack.getFluid()),
                listener));
        return builder.build();
    }

    @Override
    protected @Nullable IInventorySlotHolder getInitialInventory(IContentsListener listener) {
        InventorySlotHelper builder = InventorySlotHelper.forSideWithConfig(this);
        builder.addSlot(energyInputSlot = EnergyInventorySlot.fillOrConvert(
                energyContainer, this::getLevel, listener, 5, 25));
        builder.addSlot(energyOutputSlot = EnergyInventorySlot.drain(energyContainer, listener, 5, 56));
        builder.addSlot(fluidSlot = FluidInventorySlot.rotary(fluidTank, this::getMode, listener, 155, 25));
        builder.addSlot(fluidReturnSlot = OutputInventorySlot.at(listener, 155, 56));
        return builder.build();
    }

    @Override
    protected boolean onUpdateServer() {
        boolean needsPacket = super.onUpdateServer();
        energyInputSlot.fillContainerOrConvert();
        energyOutputSlot.drainContainer();
        if (mode) {
            fluidSlot.fillTank(fluidReturnSlot);
        } else {
            fluidSlot.drainTank(fluidReturnSlot);
        }
        if (mode) {
            int operations = Math.min(fluidTank.getFluidAmount(),
                    (int) (energyContainer.getNeeded() / ENERGY_PER_FLUX));
            if (operations < 1) {
                setActive(false);
            } else {
                setActive(true);
                energyContainer.insert(ENERGY_PER_FLUX * operations, Action.EXECUTE, AutomationType.INTERNAL);
                fluidTank.shrinkStack(operations, Action.EXECUTE);
            }
        } else {
            int operations = Math.min(fluidTank.getNeeded(), (int) (energyContainer.getEnergy() / ENERGY_PER_FLUX));
            if (operations < 1) {
                setActive(false);
            } else {
                setActive(true);
                energyContainer.extract(ENERGY_PER_FLUX * operations, Action.EXECUTE, AutomationType.INTERNAL);
                if (fluidTank.isEmpty()) {
                    fluidTank.setStack(MekInFluids.FLUX.asStack(operations));
                } else {
                    fluidTank.growStack(operations, Action.EXECUTE);
                }
            }
        }
        return needsPacket;
    }

    @Override
    public void addContainerTrackers(MekanismContainer container) {
        super.addContainerTrackers(container);
        container.track(SyncableBoolean.create(this::getMode, v -> mode = v));
    }

    public boolean getMode() {
        return mode;
    }

    @Override
    public void nextMode() {
        mode = (!mode);
        setChanged();
    }

    @Override
    public void previousMode() {
        mode = (!mode);
        setChanged();
    }

    public IExtendedFluidTank getFluidTank() {
        return fluidTank;
    }

    public IEnergyContainer getEnergyContainer() {
        return energyContainer;
    }

}
