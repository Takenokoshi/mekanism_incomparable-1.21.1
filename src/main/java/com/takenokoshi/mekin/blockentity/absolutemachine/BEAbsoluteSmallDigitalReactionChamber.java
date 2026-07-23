package com.takenokoshi.mekin.blockentity.absolutemachine;

import com.takenokoshi.mekut.blockentity.abs.BEAbstractSmallDigitalReactionChamber;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BEAbsoluteSmallDigitalReactionChamber extends BEAbstractSmallDigitalReactionChamber {

    public BEAbsoluteSmallDigitalReactionChamber(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, 40);
    }

    @Override
    protected long initChemicalTankCapacity() {
        return 80_000_000l;
    }

    @Override
    protected int initFluidTankCapacity() {
        return 800_000;
    }

    @Override
    protected int initItemSlotCapacity() {
        return 262_144;
    }
    
}
