package com.takenokoshi.mekin.blockentity.infinitemachine;

import com.takenokoshi.mekut.blockentity.abs.BEAbstractCompactBoiler;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BEInfiniteBoiler extends BEAbstractCompactBoiler {

    public BEInfiniteBoiler(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, 51_200);
    }

    @Override
    protected long initCooledCoolantTankCapacity() {
        return 991_728_000_000L;
    }

    @Override
    protected int initFluidTankCapacity() {
        return 0x7fffffff;
    }

    @Override
    protected long initHeatedCoolantTankCapacity() {
        return 2_310_144_000_000L;
    }

    @Override
    protected long initSteamTankCapacity() {
        return 622_080_000_000L;
    }
    
}
