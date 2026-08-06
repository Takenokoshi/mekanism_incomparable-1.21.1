package com.takenokoshi.mekin.blockentity.timespacemachine;

import com.takenokoshi.mekut.blockentity.abs.BEAbstractCompactBoiler;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BETimeSpaceBoiler extends BEAbstractCompactBoiler {

    public BETimeSpaceBoiler(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, 0x7fffffff);
    }

    @Override
    protected long initCooledCoolantTankCapacity() {
        return Long.MAX_VALUE;
    }

    @Override
    protected int initFluidTankCapacity() {
        return 0x7fffffff;
    }

    @Override
    protected long initHeatedCoolantTankCapacity() {
        return Long.MAX_VALUE;
    }

    @Override
    protected long initSteamTankCapacity() {
        return Long.MAX_VALUE;
    }

}
