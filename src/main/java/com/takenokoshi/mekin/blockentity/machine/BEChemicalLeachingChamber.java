package com.takenokoshi.mekin.blockentity.machine;

import com.takenokoshi.mekin.blockentity.abs.BEAbstractChemicalLeachingChamber;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BEChemicalLeachingChamber extends BEAbstractChemicalLeachingChamber {

    public BEChemicalLeachingChamber(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, 1);
    }

    @Override
    protected long initChemicalTankCapacity() {
        return 40000L;
    }

    @Override
    protected int initFluidTankCapacity() {
        return 40000;
    }

    @Override
    protected int initItemSlotCapacity() {
        return 512;
    }

}
