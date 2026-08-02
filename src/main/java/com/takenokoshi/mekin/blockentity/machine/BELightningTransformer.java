package com.takenokoshi.mekin.blockentity.machine;

import com.takenokoshi.mekin.blockentity.abs.BEAbstractLightningTransformer;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BELightningTransformer extends BEAbstractLightningTransformer {

    public BELightningTransformer(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, 1);
    }

    @Override
    protected long initChemicalTankCapacity() {
        return 64_000L;
    }

    @Override
    protected int initItemSlotCapacity() {
        return 64;
    }
    
}
