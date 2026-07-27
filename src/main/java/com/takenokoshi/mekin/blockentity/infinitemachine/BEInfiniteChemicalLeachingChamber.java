package com.takenokoshi.mekin.blockentity.infinitemachine;

import com.takenokoshi.mekin.blockentity.abs.BEAbstractChemicalLeachingChamber;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BEInfiniteChemicalLeachingChamber extends BEAbstractChemicalLeachingChamber {

    public BEInfiniteChemicalLeachingChamber(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, 120);
    }

    @Override
    protected long initChemicalTankCapacity() {
        return 96_000_000L;
    }

    @Override
    protected int initFluidTankCapacity() {
        return 4_800_000;
    }

    @Override
    protected int initItemSlotCapacity() {
        return 65_536;
    }
}
