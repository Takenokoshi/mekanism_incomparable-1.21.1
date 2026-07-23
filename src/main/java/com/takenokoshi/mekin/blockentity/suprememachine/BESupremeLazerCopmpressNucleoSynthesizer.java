package com.takenokoshi.mekin.blockentity.suprememachine;

import com.jerry.mekextras.api.ExtraUpgrade;
import com.takenokoshi.mekut.blockentity.abs.BEAbstractLazerCompressNucleoSynthesizer;

import mekanism.api.Upgrade;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BESupremeLazerCopmpressNucleoSynthesizer extends BEAbstractLazerCompressNucleoSynthesizer {

    private int stackUpgrade;

    public BESupremeLazerCopmpressNucleoSynthesizer(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, 120);
    }

    @Override
    protected long initChemicalTankCapacity() {
        return 8_000_000_000L;
    }

    @Override
    public void recalculateUpgrades(Upgrade upgrade) {
        super.recalculateUpgrades(upgrade);
        if (upgrade == ExtraUpgrade.STACK) {
            stackUpgrade = upgradeComponent.getUpgrades(ExtraUpgrade.STACK);
        }
    }

    @Override
    public int getOperationsPerTick() {
        return super.getOperationsPerTick() << stackUpgrade;
    }

}
