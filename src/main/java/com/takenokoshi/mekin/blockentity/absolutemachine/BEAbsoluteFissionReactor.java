package com.takenokoshi.mekin.blockentity.absolutemachine;

import com.takenokoshi.mekut.blockentity.abs.BEAbstractCompactFissionReactor;

import mekanism.generators.common.config.MekanismGeneratorsConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BEAbsoluteFissionReactor extends BEAbstractCompactFissionReactor {

    public static final double avgSurfaceArea = 1_240.0d / 15.0d;

    public BEAbsoluteFissionReactor(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, 38_400, 24_000.0d, Math.min(
                avgSurfaceArea / MekanismGeneratorsConfig.generators.fissionSurfaceAreaTarget.getAsDouble(),
                1.0d));
    }

    @Override
    protected long initChemicalCoolantTankCapacity() {
        return 11_664_000_000L;
    }

    @Override
    protected int initFluidCoolantTankCapacity() {
        return 0x7fffffff;
    }

    @Override
    protected long initFuelTankCapacity() {
        return 307_200_000L;
    }

    @Override
    protected double initHeatCapacity() {
        return 34_720.0d * MekanismGeneratorsConfig.generators.fissionCasingHeatCapacity.getAsDouble();
    }

    @Override
    protected long initHeatedCoolantTankCapacity() {
        return 116_640_000_000L;
    }

}
