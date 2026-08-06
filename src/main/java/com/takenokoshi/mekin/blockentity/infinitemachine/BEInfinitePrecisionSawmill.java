package com.takenokoshi.mekin.blockentity.infinitemachine;

import com.takenokoshi.mekin.blockentity.abs.BEAbstractPrecisionSawmill;
import com.takenokoshi.mekin.recipe.output.AdvancedChanceOutputHandler;
import com.takenokoshi.mekin.recipe.output.MekInChanceOutput;

import mekanism.api.inventory.IInventorySlot;
import mekanism.api.recipes.cache.CachedRecipe.OperationTracker.RecipeError;
import mekanism.api.recipes.outputs.IOutputHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BEInfinitePrecisionSawmill extends BEAbstractPrecisionSawmill {

    public BEInfinitePrecisionSawmill(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, 400);
    }

    @Override
    protected IOutputHandler<MekInChanceOutput> initSubOutputHandler(IInventorySlot slot,
            RecipeError notEnoughSpaceError) {
        return AdvancedChanceOutputHandler.create(slot, notEnoughSpaceError);
    }

    @Override
    protected int initItemSlotCapacity() {
        return 1_048_576;
    }

}
