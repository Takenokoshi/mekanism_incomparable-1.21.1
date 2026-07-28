package com.takenokoshi.mekin.blockentity.infinitemachine;

import com.jerry.mekextras.api.ExtraUpgrade;
import com.takenokoshi.mekut.blockentity.abs.BEAbstractLazerCompressNucleoSynthesizer;

import mekanism.api.Upgrade;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BEInfiniteLCNS extends BEAbstractLazerCompressNucleoSynthesizer {

    private int stackUpgrade;

    public BEInfiniteLCNS(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, 2_400);
    }

    @Override
    protected long initChemicalTankCapacity() {
        return 1_920_000_000_000L;
    }

    @Override
    public void recalculateUpgrades(Upgrade upgrade) {
        super.recalculateUpgrades(upgrade);
        if (upgrade == ExtraUpgrade.STACK) {
            stackUpgrade = upgradeComponent.getUpgrades(ExtraUpgrade.STACK);
        }
    }

    @Override
    public int getOperationsPerTick() {
        return super.getOperationsPerTick() << stackUpgrade;
    }

}