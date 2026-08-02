package com.takenokoshi.mekin.blockentity.machine;

import com.takenokoshi.mekin.blockentity.abs.BEAbstractLightningFabricator;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BELightningFabricator extends BEAbstractLightningFabricator {

    public BELightningFabricator(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, 20);
    }

    @Override
    protected long initChemicalTankCapacity() {
        return 96_000_000L;
    }

    @Override
    protected int initItemSlotCapacity() {
        return 65_536;
    }

}
