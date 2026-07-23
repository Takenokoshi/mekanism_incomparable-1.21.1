package com.takenokoshi.mekin.blockentity.suprememachine;

import com.takenokoshi.mekin.blockentity.abs.BEAbstractChemicalWasher;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BESupremeChemicalWasher extends BEAbstractChemicalWasher {

    public BESupremeChemicalWasher(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, 40);
    }

    @Override
    protected long initChemicalTankCapacity() {
        return 4_000_000L;
    }

    @Override
    protected int initFluidTankCapacity() {
        return 400_000;
    }

}
