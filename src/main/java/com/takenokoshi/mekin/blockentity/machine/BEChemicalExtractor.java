package com.takenokoshi.mekin.blockentity.machine;

import com.takenokoshi.mekin.blockentity.abs.BEAbstractChemicalExtractor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BEChemicalExtractor extends BEAbstractChemicalExtractor {

    public BEChemicalExtractor(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, 1);
    }

    @Override
    protected long initChemicalTankCapacity() {
        return 2_000_000L;
    }

    @Override
    protected int initFluidTankCapacity() {
        return 2_000_000;
    }

}
