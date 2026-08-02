package com.takenokoshi.mekin.blockentity.base;

import mekanism.common.block.attribute.Attribute;
import mekanism.common.block.attribute.AttributeSideConfig;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.tile.component.TileComponentConfig;
import mekanism.common.tile.component.TileComponentEjector;
import mekanism.common.tile.interfaces.ISideConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;

public class BENetworkConfigurableMachine extends BENetworkMekanism implements ISideConfiguration {

    public TileComponentEjector ejectorComponent;
    public final TileComponentConfig configComponent;

    public BENetworkConfigurableMachine(BlockRegistryObject<?, ?> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state);
        this.configComponent = new TileComponentConfig(this,
                (Attribute.getOrThrow(blockProvider, AttributeSideConfig.class)).supportedTypes());
    }

    @Override
    public TileComponentConfig getConfig() {
        return configComponent;
    }

    @Override
    public TileComponentEjector getEjector() {
        return ejectorComponent;
    }

    public CompoundTag getConfigurationData(HolderLookup.Provider provider, Player player) {
        CompoundTag data = super.getConfigurationData(provider, player);
        this.configComponent.write(data, provider);
        this.ejectorComponent.write(data, provider);
        return data;
    }

    public void setConfigurationData(HolderLookup.Provider provider, Player player, CompoundTag data) {
        super.setConfigurationData(provider, player, data);
        this.configComponent.read(data, provider);
        this.ejectorComponent.read(data, provider);
    }

    protected boolean onUpdateServer() {
        boolean sendUpdatePacket = super.onUpdateServer();
        this.ejectorComponent.tickServer();
        return sendUpdatePacket;
    }
}