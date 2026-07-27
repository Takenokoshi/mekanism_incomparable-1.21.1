package com.takenokoshi.mekin.blockentity.infinitemachine;

import com.takenokoshi.mekut.blockentity.abs.BEAbstractCompactIndustrialTurbine;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BEInfiniteIndustrialTurbine extends BEAbstractCompactIndustrialTurbine {
    

    public BEInfiniteIndustrialTurbine(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, 6_988_900L, 972_000L);
    }

    @Override
    protected long initChemicalTankCapacity() {
        return 577_075_200_000L;
    }

    @Override
    protected long initEnergyContainerCapacity() {
        return 199_756_800_000_000L;
    }

    @Override
    protected int initFluidTankCapacity() {
        return 0x7fffffff;
    }
}
