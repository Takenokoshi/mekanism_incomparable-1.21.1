package com.takenokoshi.mekin.blockentity.infinitemachine;

import com.takenokoshi.mekut.blockentity.abs.BEAbstractSmallDigitalReactionChamber;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BEInfiniteSmallDigitalReactionChamber extends BEAbstractSmallDigitalReactionChamber {

    public BEInfiniteSmallDigitalReactionChamber(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, 4800);
    }

    @Override
    protected long initChemicalTankCapacity() {
        return 1_920_000_000_000l;
    }

    @Override
    protected int initFluidTankCapacity() {
        return 96_000_000;
    }

    @Override
    protected int initItemSlotCapacity() {
        return 67_108_864;
    }
    
}
