package com.takenokoshi.mekin.blockentity.absolutemachine;

import com.takenokoshi.mekut.blockentity.abs.BEAbstractCompactThermalEvaporationPlant;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BEAbsoluteTEP extends BEAbstractCompactThermalEvaporationPlant {

    public BEAbsoluteTEP(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, 60_000.0d);
    }

    @Override
    protected int initFluidTankCapacity() {
        return 100_000_000;
    }
    
}
