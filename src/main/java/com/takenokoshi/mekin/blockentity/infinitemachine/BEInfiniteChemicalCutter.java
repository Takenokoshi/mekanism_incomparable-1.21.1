package com.takenokoshi.mekin.blockentity.infinitemachine;

import com.takenokoshi.mekut.blockentity.abs.BEAbstractChemicalCutter;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BEInfiniteChemicalCutter extends BEAbstractChemicalCutter {

    public BEInfiniteChemicalCutter(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, 4800);
    }

    @Override
    protected long initChemicalTankCapacity() {
        return 9_600_000_000L;
    }

    @Override
    protected int initItemSlotCapacity() {
        return 1_041_664;
    }
    
}
