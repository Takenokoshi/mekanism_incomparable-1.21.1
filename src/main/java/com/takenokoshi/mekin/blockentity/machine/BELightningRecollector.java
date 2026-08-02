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
import mekanism.api.chemical.BasicChemicalTank;
import mekanism.api.chemical.IChemicalTank;
import mekanism.common.attachments.component.AttachedSideConfig;
import mekanism.common.attachments.containers.ContainerType;
import mekanism.common.attachments.containers.chemical.ChemicalTanksBuilder;
import mekanism.common.capabilities.holder.chemical.ChemicalTankHelper;
import mekanism.common.capabilities.holder.chemical.IChemicalTankHolder;
import mekanism.common.lib.transmitter.TransmissionType;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.registration.impl.ItemRegistryObject;
import mekanism.common.tile.component.TileComponentEjector;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class BELightningRecollector extends BENetworkConfigurableMachine {

    public static final AttachedSideConfig SIDE_CONFIG = Util.make(() -> {
        Map<TransmissionType, AttachedSideConfig.LightConfigInfo> configInfo = new EnumMap<>(TransmissionType.class);
        configInfo.put(TransmissionType.CHEMICAL, AttachedSideConfig.LightConfigInfo.INPUT_ONLY);
        return new AttachedSideConfig(configInfo);
    });

    public static void addContainrsToItem(ItemRegistryObject<?> value) {
        value.addAttachmentOnlyContainers(ContainerType.CHEMICAL,
                () -> ChemicalTanksBuilder.builder()
                        .addBasic(Long.MAX_VALUE)
                        .build());
    }

    private IChemicalTank chemicalTank;

    public BELightningRecollector(BlockRegistryObject<?, ?> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state);
        configComponent.setupInputConfig(TransmissionType.CHEMICAL, chemicalTank);
        ejectorComponent = new TileComponentEjector(this);
    }

    @Override
    public @Nullable IChemicalTankHolder getInitialChemicalTanks(IContentsListener listener) {
        ChemicalTankHelper builder = ChemicalTankHelper.forSideWithConfig(this);
        builder.addTank(chemicalTank = BasicChemicalTank.createModern(Long.MAX_VALUE,
                stack -> stack.is(MekInChemicals.HIGH_VOLTAGE_LIGHTNING)
                        || stack.is(MekInChemicals.EXTREME_HIGH_VOLTAGE_LIGHTNING),
                listener));
        return builder.build();
    }

    @Override
    protected boolean onUpdateServer() {
        boolean sendUpdatePacket = super.onUpdateServer();
        recollect();
        return sendUpdatePacket;
    }

    private void recollect() {
        if (chemicalTank.isEmpty()) {
            return;
        }

        MEStorage storage = getMeStorage();
        if (storage == null) {
            return;
        }
        IActionSource actionSource = IActionSource.ofMachine(this);
        long amount = chemicalTank.getStored() / 1000L;
        if (amount == 0L) {
            return;
        }
        long recollecting = storage.insert(chemicalTank.getStack().is(MekInChemicals.HIGH_VOLTAGE_LIGHTNING)
                ? LightningKey.HIGH_VOLTAGE
                : LightningKey.EXTREME_HIGH_VOLTAGE,
                amount, Actionable.MODULATE, actionSource);
        chemicalTank.shrinkStack(recollecting * 1000L, Action.EXECUTE);
    }

    public IChemicalTank getChemicalTank() {
        return chemicalTank;
    }

}
