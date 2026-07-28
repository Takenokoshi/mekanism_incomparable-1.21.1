package com.takenokoshi.mekin.blockentity.infinitemachine;

import com.takenokoshi.mekin.blockentity.abs.BEAbstractRotaryCondensentrator;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BEInfiniteRotaryCondensentrator extends BEAbstractRotaryCondensentrator {

    public BEInfiniteRotaryCondensentrator(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, 400);
    }

    @Override
    protected long initChemicalTankCapacity() {
        return 480_000_000L;
    }

    @Override
    protected int initFluidTankCapacity() {
        return 24_000_000;
    }
}
