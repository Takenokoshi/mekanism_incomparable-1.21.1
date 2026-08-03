package com.takenokoshi.mekin.blockentity.infinitemachine;

import com.jerry.mekextras.api.ExtraUpgrade;
import com.takenokoshi.mekin.blockentity.abs.BEAbstractLightningFabricator;

import mekanism.api.Upgrade;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BEInfiniteLightningFabricator extends BEAbstractLightningFabricator {

    private int stackUpgrades = 0;

    public BEInfiniteLightningFabricator(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, 400);
    }

    @Override
    protected long initChemicalTankCapacity() {
        return 38_400_000_000L;
    }

    @Override
    protected int initItemSlotCapacity() {
        return 1_048_576;
    }

    @Override
    public void recalculateUpgrades(Upgrade upgrade) {
        super.recalculateUpgrades(upgrade);
        if (upgrade == ExtraUpgrade.STACK) {
            stackUpgrades = upgradeComponent.getUpgrades(ExtraUpgrade.STACK);
        }
    }

    @Override
    public int getOperationsPerTick() {
        return super.getOperationsPerTick() << stackUpgrades;
    }

}
