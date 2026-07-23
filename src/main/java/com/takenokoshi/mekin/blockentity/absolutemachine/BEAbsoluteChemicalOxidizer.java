package com.takenokoshi.mekin.blockentity.absolutemachine;

import com.takenokoshi.mekin.blockentity.abs.BEAbstractChemicalOxidizer;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BEAbsoluteChemicalOxidizer extends BEAbstractChemicalOxidizer {

    public BEAbsoluteChemicalOxidizer(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, 20);
    }

    @Override
    protected long initChemicalTankCapacity() {
        return 400_000L;
    }

    @Override
    protected int initItemSlotCapacity() {
        return 1_024;
    }

}
