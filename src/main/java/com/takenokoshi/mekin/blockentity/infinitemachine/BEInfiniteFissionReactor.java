package com.takenokoshi.mekin.blockentity.infinitemachine;

import com.takenokoshi.mekut.blockentity.abs.BEAbstractCompactFissionReactor;

import mekanism.generators.common.config.MekanismGeneratorsConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BEInfiniteFissionReactor extends BEAbstractCompactFissionReactor {
    public static final double avgSurfaceArea = 24_600.0d / 15.0d;

    public BEInfiniteFissionReactor(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, 768_000, 480_000.0d, Math.min(
                avgSurfaceArea / MekanismGeneratorsConfig.generators.fissionSurfaceAreaTarget.getAsDouble(),
                1.0d));
    }

    @Override
    protected long initChemicalCoolantTankCapacity() {
        return 1_399_680_000_000L;
    }

    @Override
    protected int initFluidCoolantTankCapacity() {
        return 0x7fffffff;
    }

    @Override
    protected long initFuelTankCapacity() {
        return 36_864_000_000L;
    }

    @Override
    protected double initHeatCapacity() {
        return 4_166_410.0d * MekanismGeneratorsConfig.generators.fissionCasingHeatCapacity.getAsDouble();
    }

    @Override
    protected long initHeatedCoolantTankCapacity() {
        return 13_996_800_000_000L;
    }

}
