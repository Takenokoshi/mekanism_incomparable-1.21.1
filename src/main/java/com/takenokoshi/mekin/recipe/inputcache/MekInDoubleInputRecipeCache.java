package com.takenokoshi.mekin.recipe.inputcache;

import java.util.function.BiPredicate;
import java.util.function.Function;

import com.takenokoshi.mekaddonlib.recipe.type.MekALRecipeType;
import com.takenokoshi.mekin.recipe.recipes.prefab.FluidChemicalToBiChemicalRecipe;
import com.takenokoshi.mekin.recipe.recipes.prefab.ItemStackChemicalToChemicalRecipe;
import com.takenokoshi.mekut.recipe.inputcache.MekUtDoubleInputRecipeCache;
import com.takenokoshi.mekut.recipe.inputcache.MekUtDoubleInputRecipeCache.MekUtItemChemical;

import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.MekanismRecipe;
import mekanism.api.recipes.ingredients.ChemicalStackIngredient;
import mekanism.api.recipes.ingredients.FluidStackIngredient;
import mekanism.api.recipes.ingredients.ItemStackIngredient;
import mekanism.common.recipe.lookup.cache.type.ChemicalInputCache;
import mekanism.common.recipe.lookup.cache.type.FluidInputCache;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidStack;

public class MekInDoubleInputRecipeCache {

    public static class MekInFluidChemical<RECIPE extends MekanismRecipe<?> & BiPredicate<FluidStack, ChemicalStack>>
            extends
            MekUtDoubleInputRecipeCache<FluidStack, FluidStackIngredient, ChemicalStack, ChemicalStackIngredient, RECIPE, FluidInputCache<RECIPE>, ChemicalInputCache<RECIPE>> {

        protected MekInFluidChemical(MekALRecipeType<?, RECIPE, ?> recipeType,
                Function<RECIPE, FluidStackIngredient> inputAExtractor,
                Function<RECIPE, ChemicalStackIngredient> inputBExtractor) {
            super(recipeType, inputAExtractor, new FluidInputCache<>(), inputBExtractor, new ChemicalInputCache<>());
        }

        public static MekInFluidChemical<FluidChemicalToBiChemicalRecipe> toBiChemical(
                MekALRecipeType<?, FluidChemicalToBiChemicalRecipe, ?> recipeType) {
            return new MekInFluidChemical<>(recipeType,
                    FluidChemicalToBiChemicalRecipe::getFluidInput,
                    FluidChemicalToBiChemicalRecipe::getChemicalInput);
        }

    }

    public static class MekInItemChemical<RECIPE extends MekanismRecipe<?> & BiPredicate<ItemStack, ChemicalStack>>
            extends MekUtItemChemical<RECIPE> {

        protected MekInItemChemical(MekALRecipeType<?, RECIPE, ?> recipeType,
                Function<RECIPE, ItemStackIngredient> inputAExtractor,
                Function<RECIPE, ChemicalStackIngredient> inputBExtractor) {
            super(recipeType, inputAExtractor, inputBExtractor);
        }

        public static MekInItemChemical<ItemStackChemicalToChemicalRecipe> toChemical(
                MekALRecipeType<?, ItemStackChemicalToChemicalRecipe, ?> recipeType) {
            return new MekInItemChemical<>(recipeType,
                    ItemStackChemicalToChemicalRecipe::getItemInput,
                    ItemStackChemicalToChemicalRecipe::getChemicalInput);
        }

    }
}
