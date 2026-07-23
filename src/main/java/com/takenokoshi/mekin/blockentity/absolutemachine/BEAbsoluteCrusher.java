package com.takenokoshi.mekin.blockentity.absolutemachine;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.takenokoshi.mekin.blockentity.abs.BEAbstractItemStackToItemStackMachine;

import mekanism.api.recipes.ItemStackToItemStackRecipe;
import mekanism.client.recipe_viewer.type.IRecipeViewerRecipeType;
import mekanism.client.recipe_viewer.type.RecipeViewerRecipeType;
import mekanism.common.recipe.IMekanismRecipeTypeProvider;
import mekanism.common.recipe.MekanismRecipeType;
import mekanism.common.recipe.lookup.cache.InputRecipeCache.SingleItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BEAbsoluteCrusher extends BEAbstractItemStackToItemStackMachine {

    public BEAbsoluteCrusher(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, BASE_TICKS_REQUIRED, 20);
    }

    @Override
    public @NotNull IMekanismRecipeTypeProvider<?, ItemStackToItemStackRecipe, SingleItem<ItemStackToItemStackRecipe>> getRecipeType() {
        return MekanismRecipeType.CRUSHING;
    }

    @Override
    protected int initItemSlotCapacity() {
        return 1_024;
    }

    @Override
    public @Nullable IRecipeViewerRecipeType<ItemStackToItemStackRecipe> recipeViewerType() {
        return RecipeViewerRecipeType.CRUSHING;
    }

}
