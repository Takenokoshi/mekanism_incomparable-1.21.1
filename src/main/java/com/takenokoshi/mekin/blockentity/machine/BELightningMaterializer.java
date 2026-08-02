package com.takenokoshi.mekin.blockentity.machine;

import java.util.EnumMap;
import java.util.Map;

import org.jetbrains.annotations.Nullable;

import com.moakiee.ae2lt.me.key.LightningKey;
import com.takenokoshi.mekin.blockentity.base.BENetworkConfigurableMachine;
import com.takenokoshi.mekin.registries.MekInChemicals;

import appeng.api.config.Actionable;
import appeng.api.networking.security.IActionSource;
import appeng.api.storage.MEStorage;
import mekanism.api.Action;
import mekanism.api.IContentsListener;
import mekanism.api.RelativeSide;
import mekanism.api.chemical.BasicChemicalTank;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.chemical.IChemicalTank;
import mekanism.common.attachments.component.AttachedSideConfig;
import mekanism.common.attachments.containers.ContainerType;
import mekanism.common.attachments.containers.chemical.ChemicalTanksBuilder;
import mekanism.common.capabilities.holder.chemical.ChemicalTankHelper;
import mekanism.common.capabilities.holder.chemical.IChemicalTankHolder;
import mekanism.common.inventory.container.MekanismContainer;
import mekanism.common.inventory.container.sync.SyncableBoolean;
import mekanism.common.lib.transmitter.TransmissionType;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.registration.impl.ItemRegistryObject;
import mekanism.common.tile.component.TileComponentEjector;
import mekanism.common.tile.interfaces.IHasMode;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class BELightningMaterializer extends BENetworkConfigurableMachine implements IHasMode {

    public static final AttachedSideConfig SIDE_CONFIG = Util.make(() -> {
        Map<TransmissionType, AttachedSideConfig.LightConfigInfo> configInfo = new EnumMap<>(TransmissionType.class);
        configInfo.put(TransmissionType.CHEMICAL, AttachedSideConfig.LightConfigInfo.OUT_EJECT);
        return new AttachedSideConfig(configInfo);
    });

    public static void addContainrsToItem(ItemRegistryObject<?> value) {
        value.addAttachmentOnlyContainers(ContainerType.CHEMICAL,
                () -> ChemicalTanksBuilder.builder()
                        .addBasic(Long.MAX_VALUE)
                        .build());
    }

    private IChemicalTank chemicalTank;

    /**
     * false= High Voltage
     * true = Extreme High Voltage
     */
    private boolean mode;

    public BELightningMaterializer(BlockRegistryObject<?, ?> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state);
        configComponent.setupOutputConfig(TransmissionType.CHEMICAL, chemicalTank, RelativeSide.RIGHT);
        ejectorComponent = new TileComponentEjector(this, () -> Long.MAX_VALUE).setOutputData(configComponent,
                TransmissionType.CHEMICAL);
    }

    @Override
    public @Nullable IChemicalTankHolder getInitialChemicalTanks(IContentsListener listener) {
        ChemicalTankHelper builder = ChemicalTankHelper.forSideWithConfig(this);
        builder.addTank(chemicalTank = BasicChemicalTank.output(Long.MAX_VALUE, listener));
        return builder.build();
    }

    @Override
    protected boolean onUpdateServer() {
        boolean sendUpdatePacket = super.onUpdateServer();
        materialize();
        return sendUpdatePacket;
    }

    private void materialize() {
        if (!chemicalTank.isEmpty() && !chemicalTank.isTypeEqual(
                mode ? MekInChemicals.EXTREME_HIGH_VOLTAGE_LIGHTNING : MekInChemicals.HIGH_VOLTAGE_LIGHTNING)) {
            return;
        }
        MEStorage meStorage = getMeStorage();
        if (meStorage == null) {
            return;
        }
        long materializing = chemicalTank.getNeeded() / 1000L;
        if (materializing == 0L) {
            return;
        }
        materializing = meStorage.extract(mode
                ? LightningKey.EXTREME_HIGH_VOLTAGE
                : LightningKey.HIGH_VOLTAGE,
                materializing, Actionable.MODULATE, IActionSource.ofMachine(this));
        if (chemicalTank.isEmpty()) {
            chemicalTank.setStack(new ChemicalStack(
                    mode ? MekInChemicals.EXTREME_HIGH_VOLTAGE_LIGHTNING : MekInChemicals.HIGH_VOLTAGE_LIGHTNING,
                    materializing * 1000L));
        } else {
            chemicalTank.growStack(materializing * 1000L, Action.EXECUTE);
        }
    }

    @Override
    public void nextMode() {
        mode = !mode;
    }

    @Override
    public void previousMode() {
        nextMode();
    }

    @Override
    public void addContainerTrackers(MekanismContainer container) {
        super.addContainerTrackers(container);
        container.track(SyncableBoolean.create(this::getMode, v -> mode = v));
    }

    public IChemicalTank getChemicalTank() {
        return chemicalTank;
    }

    public boolean getMode() {
        return mode;
    }

}
