package com.takenokoshi.mekin.blockentity.absolutemachine;

import com.takenokoshi.mekin.blockentity.abs.BEAbstractElectrolyticSeparator;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BEAbsoluteElectrolyticSeparator extends BEAbstractElectrolyticSeparator {

    public BEAbsoluteElectrolyticSeparator(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, 20);
    }

    @Override
    protected int initFluidTankCapacity() {
        return 480_000;
    }

    @Override
    protected long initChemicalTankCapacity() {
        return 480_000L;
    }
    
}
