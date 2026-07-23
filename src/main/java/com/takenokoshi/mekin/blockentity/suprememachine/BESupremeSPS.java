package com.takenokoshi.mekin.blockentity.suprememachine;

import com.takenokoshi.mekut.blockentity.abs.BEAbstractCompactSPS;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BESupremeSPS extends BEAbstractCompactSPS {

    public BESupremeSPS(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, 80, 1.0d);
    }

    @Override
    protected long initTankCapacity() {
        return 800_000;
    }

}
