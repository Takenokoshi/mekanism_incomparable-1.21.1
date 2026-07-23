package com.takenokoshi.mekin.blockentity.absolutemachine;

import com.takenokoshi.mekin.blockentity.abs.BEAbstractRotaryCondensentrator;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BEAbsoluteRotaryCondensentrator extends BEAbstractRotaryCondensentrator {

    public BEAbsoluteRotaryCondensentrator(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, 20);
    }

    @Override
    protected long initChemicalTankCapacity() {
        return 200_000L;
    }

    @Override
    protected int initFluidTankCapacity() {
        return 200_000;
    }
    
}
