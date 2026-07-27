package com.takenokoshi.mekin.blockentity.infinitemachine;

import com.takenokoshi.mekin.blockentity.abs.BEAbstractElectrolyticSeparator;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BEInfiniteElectrolyticSeparator extends BEAbstractElectrolyticSeparator {

    public BEInfiniteElectrolyticSeparator(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, 2400);
    }

    @Override
    protected int initFluidTankCapacity() {
        return 60_000_000;
    }

    @Override
    protected long initChemicalTankCapacity() {
        return 1_200_000_000L;
    }
    
}
