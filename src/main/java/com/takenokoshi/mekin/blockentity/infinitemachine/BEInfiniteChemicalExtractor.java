package com.takenokoshi.mekin.blockentity.infinitemachine;

import com.takenokoshi.mekin.blockentity.abs.BEAbstractChemicalExtractor;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BEInfiniteChemicalExtractor extends BEAbstractChemicalExtractor {

    public BEInfiniteChemicalExtractor(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, 20);
    }

    @Override
    protected long initChemicalTankCapacity() {
        return 4_800_000_000L;
    }

    @Override
    protected int initFluidTankCapacity() {
        return 240_000_000;
    }

}
