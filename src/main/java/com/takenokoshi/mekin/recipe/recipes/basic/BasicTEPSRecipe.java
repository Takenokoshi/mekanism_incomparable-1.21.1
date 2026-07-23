package com.takenokoshi.mekin.recipe.recipes.basic;

import com.takenokoshi.mekin.registries.MekInRecipeSerializers;
import com.takenokoshi.mekin.registries.MekInRecipeTypes;

import mekanism.api.recipes.basic.BasicItemStackChemicalToItemStackRecipe;
import mekanism.api.recipes.ingredients.ChemicalStackIngredient;
import mekanism.api.recipes.ingredients.ItemStackIngredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class BasicTEPSRecipe extends BasicItemStackChemicalToItemStackRecipe {

    public BasicTEPSRecipe(ItemStackIngredient itemInput, ChemicalStackIngredient chemicalInput, ItemStack output,
            boolean perTickUsage) {
        super(itemInput, chemicalInput, output, perTickUsage, MekInRecipeTypes.TEPS.get());
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return MekInRecipeSerializers.TEPS.get();
    }

}
