package com.takenokoshi.mekin.blockentity.infinitemachine;

import com.takenokoshi.mekut.blockentity.abs.BEAbstractCompactSPS;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BEInfiniteSPS extends BEAbstractCompactSPS {

    public BEInfiniteSPS(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, 800, 1.0d);
    }

    @Override
    protected long initTankCapacity() {
        return 192_000_000;
    }

}