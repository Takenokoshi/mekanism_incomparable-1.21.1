package com.takenokoshi.mekin.blockentity.absolutemachine;

import com.takenokoshi.mekut.blockentity.abs.BEAbstractChemicalCutter;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BEAbsoluteChemicalCutter extends BEAbstractChemicalCutter {

    public BEAbsoluteChemicalCutter(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, 40);
    }

    @Override
    protected long initChemicalTankCapacity() {
        return 4_000_000L;
    }

    @Override
    protected int initItemSlotCapacity() {
        return 4_096;
    }
    
}
