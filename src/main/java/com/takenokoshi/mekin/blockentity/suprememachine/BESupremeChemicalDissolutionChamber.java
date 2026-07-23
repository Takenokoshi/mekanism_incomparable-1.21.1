package com.takenokoshi.mekin.blockentity.suprememachine;

import com.takenokoshi.mekin.blockentity.abs.BEAbstractChemicalDissolutionChamber;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BESupremeChemicalDissolutionChamber extends BEAbstractChemicalDissolutionChamber {

    public BESupremeChemicalDissolutionChamber(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, 40);
    }

    @Override
    protected long initChemicalTankCapacity() {
        return 4_000_000L;
    }

    @Override
    protected int initItemSlotCapacity() {
        return 4_096;
    }

}
