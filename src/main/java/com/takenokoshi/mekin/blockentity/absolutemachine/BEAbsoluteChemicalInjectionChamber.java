package com.takenokoshi.mekin.blockentity.absolutemachine;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.takenokoshi.mekin.blockentity.abs.BEAbstractItemStackChemicalToItemStackMachine;

import mekanism.api.recipes.ItemStackChemicalToItemStackRecipe;
import mekanism.client.recipe_viewer.type.IRecipeViewerRecipeType;
import mekanism.client.recipe_viewer.type.RecipeViewerRecipeType;
import mekanism.common.recipe.IMekanismRecipeTypeProvider;
import mekanism.common.recipe.MekanismRecipeType;
import mekanism.common.recipe.lookup.cache.InputRecipeCache.ItemChemical;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BEAbsoluteChemicalInjectionChamber extends BEAbstractItemStackChemicalToItemStackMachine {

    public BEAbsoluteChemicalInjectionChamber(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, 200, 20);
    }

    @Override
    public @NotNull IMekanismRecipeTypeProvider<?, ItemStackChemicalToItemStackRecipe, ItemChemical<ItemStackChemicalToItemStackRecipe>> getRecipeType() {
        return MekanismRecipeType.INJECTING;
    }

    @Override
    protected long initChemicalTankCapacity() {
        return 40_000L;
    }

    @Override
    protected int initItemSlotCapacity() {
        return 1_024;
    }

    @Override
    public @Nullable IRecipeViewerRecipeType<ItemStackChemicalToItemStackRecipe> recipeViewerType() {
        return RecipeViewerRecipeType.INJECTING;
    }

}
