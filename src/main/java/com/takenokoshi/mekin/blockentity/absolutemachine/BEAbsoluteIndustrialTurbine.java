package com.takenokoshi.mekin.blockentity.absolutemachine;

import com.takenokoshi.mekut.blockentity.abs.BEAbstractCompactIndustrialTurbine;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BEAbsoluteIndustrialTurbine extends BEAbstractCompactIndustrialTurbine {

    public BEAbsoluteIndustrialTurbine(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, 58_240L, 8_100L);
    }

    @Override
    protected long initChemicalTankCapacity() {
        return 4_808_960_000L;
    }

    @Override
    protected long initEnergyContainerCapacity() {
        return 1_664_640_000_000L;
    }

    @Override
    protected int initFluidTankCapacity() {
        return 259_840_000;
    }

}
