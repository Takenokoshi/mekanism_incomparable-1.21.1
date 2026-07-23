package com.takenokoshi.mekin.recipe.recipes.basic;

import com.takenokoshi.mekin.recipe.recipes.prefab.FluidChemicalToBiChemicalRecipe;
import com.takenokoshi.mekin.registries.MekInRecipeSerializers;
import com.takenokoshi.mekin.registries.MekInRecipeTypes;

import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.ingredients.ChemicalStackIngredient;
import mekanism.api.recipes.ingredients.FluidStackIngredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class BasicChemicalExtractionRecipe extends FluidChemicalToBiChemicalRecipe {

    public BasicChemicalExtractionRecipe(
            FluidStackIngredient fluidInput, ChemicalStackIngredient chemicalInput, ChemicalStack mainOutput,
            ChemicalStack subOutput) {
        super(MekInRecipeTypes.CHEMICAL_EXTRACTION.get(), fluidInput, chemicalInput, mainOutput, subOutput);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return MekInRecipeSerializers.CHEMICAL_EXTRACTION.get();
    }

}
