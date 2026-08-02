package com.takenokoshi.mekin.blockentity.infinitemachine;

import com.takenokoshi.mekin.blockentity.abs.BEAbstractChemicalInfuser;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BEInfiniteChemicalInfuser extends BEAbstractChemicalInfuser {

    public BEInfiniteChemicalInfuser(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, 400);
    }

    @Override
    protected long initChemicalTankCapacity() {
        return 1_600_000_000L;
    }
    
}