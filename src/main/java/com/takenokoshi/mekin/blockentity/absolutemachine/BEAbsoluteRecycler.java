package com.takenokoshi.mekin.blockentity.absolutemachine;

import com.takenokoshi.mekin.blockentity.abs.BEAbstractRecycler;
import com.takenokoshi.mekin.recipe.output.MekInChanceOutput;
import com.takenokoshi.mekin.recipe.output.SimpleChanceOutputHandler;

import mekanism.api.inventory.IInventorySlot;
import mekanism.api.recipes.cache.CachedRecipe.OperationTracker.RecipeError;
import mekanism.api.recipes.outputs.IOutputHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BEAbsoluteRecycler extends BEAbstractRecycler {

    public BEAbsoluteRecycler(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, 20);
    }

    @Override
    protected IOutputHandler<MekInChanceOutput> initOutputHandler(IInventorySlot slot,
            RecipeError notEnoughSpaceError) {
        return SimpleChanceOutputHandler.create(slot, notEnoughSpaceError);
    }

    @Override
    protected int initItemSlotCapacity() {
        return 4_096;
    }

}
