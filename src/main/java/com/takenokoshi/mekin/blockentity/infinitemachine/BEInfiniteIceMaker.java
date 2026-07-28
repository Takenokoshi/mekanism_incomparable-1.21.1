package com.takenokoshi.mekin.blockentity.infinitemachine;

import com.takenokoshi.mekut.blockentity.abs.BEAbstractIceMaker;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BEInfiniteIceMaker extends BEAbstractIceMaker {

    public BEInfiniteIceMaker(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, 400);
    }

    @Override
    protected int initFluidTankCapacity() {
        return 24_000_000;
    }

    @Override
    protected int initItemSlotCapacity() {
        return 1_041_664;
    }
}