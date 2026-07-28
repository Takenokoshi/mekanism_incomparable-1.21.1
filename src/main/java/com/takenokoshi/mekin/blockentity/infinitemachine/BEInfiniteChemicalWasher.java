package com.takenokoshi.mekin.blockentity.infinitemachine;

import com.takenokoshi.mekin.blockentity.abs.BEAbstractChemicalWasher;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BEInfiniteChemicalWasher extends BEAbstractChemicalWasher {

    public BEInfiniteChemicalWasher(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, 400);
    }

    @Override
    protected long initChemicalTankCapacity() {
        return 960_000_000L;
    }

    @Override
    protected int initFluidTankCapacity() {
        return 96_000_000;
    }

}