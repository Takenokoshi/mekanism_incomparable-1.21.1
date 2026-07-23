package com.takenokoshi.mekin.recipe.recipes.basic;

import com.takenokoshi.mekin.recipe.recipes.prefab.ItemStackChemicalToChemicalRecipe;
import com.takenokoshi.mekin.registries.MekInRecipeSerializers;
import com.takenokoshi.mekin.registries.MekInRecipeTypes;

import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.ingredients.ChemicalStackIngredient;
import mekanism.api.recipes.ingredients.ItemStackIngredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class BasicAETRecipe extends ItemStackChemicalToChemicalRecipe {

    public BasicAETRecipe(
            ItemStackIngredient itemInput, ChemicalStackIngredient chemicalInput, ChemicalStack output) {
        super(MekInRecipeTypes.AET.get(), itemInput, chemicalInput, output);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return MekInRecipeSerializers.AET.get();
    }

}
