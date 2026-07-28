package com.takenokoshi.mekin.blockentity.infinitemachine;

import com.takenokoshi.mekin.blockentity.abs.BEAbstractChemicalDissolutionChamber;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BEInfiniteChemicalDissolutionChamber  extends BEAbstractChemicalDissolutionChamber {

    public BEInfiniteChemicalDissolutionChamber(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
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
