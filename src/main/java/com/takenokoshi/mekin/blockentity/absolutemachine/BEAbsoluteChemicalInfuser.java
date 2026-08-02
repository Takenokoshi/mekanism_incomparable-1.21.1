package com.takenokoshi.mekin.blockentity.absolutemachine;

import com.takenokoshi.mekin.blockentity.abs.BEAbstractChemicalInfuser;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BEAbsoluteChemicalInfuser extends BEAbstractChemicalInfuser {

    public BEAbsoluteChemicalInfuser(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, 20);
    }

    @Override
    protected long initChemicalTankCapacity() {
        return 4_000_000L;
    }
    
}
