package com.takenokoshi.mekin.recipe.recipes.basic;

import com.takenokoshi.mekin.recipe.recipes.prefab.ItemStackFluidChemicalToItemStackRecipe;
import com.takenokoshi.mekin.registries.MekInRecipeSerializers;
import com.takenokoshi.mekin.registries.MekInRecipeTypes;

import mekanism.api.recipes.ingredients.ChemicalStackIngredient;
import mekanism.api.recipes.ingredients.FluidStackIngredient;
import mekanism.api.recipes.ingredients.ItemStackIngredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class BasicLeachingRecipe extends ItemStackFluidChemicalToItemStackRecipe {

    public BasicLeachingRecipe(
            ItemStackIngredient itemInput, FluidStackIngredient fluidInput, ChemicalStackIngredient chemicalInput,
            long energyRequired, int duration, ItemStack outputItem) {
        super(MekInRecipeTypes.LEACHING.get(), itemInput, fluidInput, chemicalInput, energyRequired, duration,
                outputItem);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return MekInRecipeSerializers.LEACHING.get();
    }

}
