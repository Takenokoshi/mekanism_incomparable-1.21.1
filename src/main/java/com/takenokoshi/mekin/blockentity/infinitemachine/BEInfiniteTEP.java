package com.takenokoshi.mekin.blockentity.infinitemachine;

import com.takenokoshi.mekut.blockentity.abs.BEAbstractCompactThermalEvaporationPlant;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BEInfiniteTEP extends BEAbstractCompactThermalEvaporationPlant {

    public BEInfiniteTEP(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, 7_200_000.0d);
    }

    @Override
    protected int initFluidTankCapacity() {
        return 0x7fffffff;
    }
}