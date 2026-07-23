package com.takenokoshi.mekin.blockentity.machine;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.takenokoshi.mekaddonlib.recipe.type.IMekALRecipeTypeProvider;
import com.takenokoshi.mekin.recipe_viewer.type.MekInRecipeViewerRecipeType;
import com.takenokoshi.mekin.registries.MekInRecipeTypes;
import com.takenokoshi.mekut.blockentity.abs.BEAbstractChemicalCutter;
import com.takenokoshi.mekut.recipe.inputcache.MekUtDoubleInputRecipeCache.MekUtItemChemical;

import mekanism.api.recipes.ItemStackChemicalToItemStackRecipe;
import mekanism.client.recipe_viewer.type.IRecipeViewerRecipeType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BEChemicalRefiner extends BEAbstractChemicalCutter {

    public BEChemicalRefiner(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state, 20);
    }

    @Override
    protected long initChemicalTankCapacity() {
        return 2000L;
    }

    @Override
    protected int initItemSlotCapacity() {
        return 256;
    }

    @Override
    public @NotNull IMekALRecipeTypeProvider<?, ItemStackChemicalToItemStackRecipe, MekUtItemChemical<ItemStackChemicalToItemStackRecipe>> getRecipeType() {
        return MekInRecipeTypes.REFINING;
    }

    @Override
    public @Nullable IRecipeViewerRecipeType<ItemStackChemicalToItemStackRecipe> recipeViewerType() {
        return MekInRecipeViewerRecipeType.REFINING;
    }
    
}
