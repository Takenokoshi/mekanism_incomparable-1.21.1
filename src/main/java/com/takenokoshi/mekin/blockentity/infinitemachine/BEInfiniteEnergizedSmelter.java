package com.takenokoshi.mekin.blockentity.infinitemachine;

import com.jerry.mekextras.api.ExtraUpgrade;
import com.takenokoshi.mekut.blockentity.abs.BEAbstractEnergizedSmelter;

import mekanism.api.Upgrade;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BEInfiniteEnergizedSmelter extends BEAbstractEnergizedSmelter {

    private int stackUpgrades = 0;

    public BEInfiniteEnergizedSmelter(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, 400);
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

    @Override
    protected int initItemSlotCapacity() {
        return 262_144;
    }

}
