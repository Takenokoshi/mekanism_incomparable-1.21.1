package com.takenokoshi.mekin.blockentity.machine;

import com.takenokoshi.mekin.blockentity.abs.BEAbstractCompactAPT;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BECompactAPT extends BEAbstractCompactAPT {

    public BECompactAPT(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, 1);
    }

    @Override
    protected long initChemicalTankCapacity() {
        return 10000L;
    }

}
