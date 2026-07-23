package com.takenokoshi.mekin.blockentity.absolutemachine;

import com.takenokoshi.mekut.blockentity.abs.BEAbstractIceMaker;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BEAbsoluteIceMaker extends BEAbstractIceMaker {

    public BEAbsoluteIceMaker(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, 20);
    }

    @Override
    protected int initFluidTankCapacity() {
        return 200_000;
    }

    @Override
    protected int initItemSlotCapacity() {
        return 2_048;
    }

}
