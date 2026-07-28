package com.takenokoshi.mekin.blockentity.infinitemachine;

import com.takenokoshi.mekut.blockentity.abs.BEAbstractEnergizedSmelter;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BEInfiniteEnergizedSmelter extends BEAbstractEnergizedSmelter {

    public BEInfiniteEnergizedSmelter(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, 400);
    }

    @Override
    protected int initItemSlotCapacity() {
        return 262_144;
    }
    
}
