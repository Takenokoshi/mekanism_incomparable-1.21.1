package com.takenokoshi.mekin.blockentity.infinitemachine;

import com.takenokoshi.mekin.blockentity.abs.BEAbstractChemicalCrystallizer;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BEInfiniteChemicalCrystallizer extends BEAbstractChemicalCrystallizer {

    public BEInfiniteChemicalCrystallizer(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, 400);
    }

    @Override
    protected long initChemicalTankCapacity() {
        return 960_000_000L;
    }

    @Override
    protected int initItemSlotCapacity() {
        return 1_048_576;
    }
    
}
