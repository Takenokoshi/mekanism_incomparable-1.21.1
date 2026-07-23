package com.takenokoshi.mekin.blockentity.absolutemachine;

import com.takenokoshi.mekut.blockentity.abs.BEAbstractCompactBoiler;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BEAbsoluteBoiler extends BEAbstractCompactBoiler {

    public BEAbsoluteBoiler(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, 2560);
    }

    @Override
    protected long initCooledCoolantTankCapacity() {
        return 8_294_400_000L;
    }

    @Override
    protected int initFluidTankCapacity() {
        return 123_200_000;
    }

    @Override
    protected long initHeatedCoolantTankCapacity() {
        return 19_251_200_000L;
    }

    @Override
    protected long initSteamTankCapacity() {
        return 5_184_000_000L;
    }

}
