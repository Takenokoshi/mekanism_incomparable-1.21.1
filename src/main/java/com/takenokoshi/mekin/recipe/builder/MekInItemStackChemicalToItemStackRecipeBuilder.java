package com.takenokoshi.mekin.recipe.builder;

import com.takenokoshi.mekin.recipe.recipes.basic.BasicRefiningRecipe;

import mekanism.api.datagen.recipe.builder.ItemStackChemicalToItemStackRecipeBuilder;
import mekanism.api.recipes.ingredients.ChemicalStackIngredient;
import mekanism.api.recipes.ingredients.ItemStackIngredient;
import net.minecraft.world.item.ItemStack;

public class MekInItemStackChemicalToItemStackRecipeBuilder extends ItemStackChemicalToItemStackRecipeBuilder {

    protected MekInItemStackChemicalToItemStackRecipeBuilder(ItemStackIngredient itemInput,
            ChemicalStackIngredient chemicalInput, ItemStack output, boolean perTickUsage, Factory factory) {
        super(itemInput, chemicalInput, output, perTickUsage, factory);
    }

    public static MekInItemStackChemicalToItemStackRecipeBuilder refining(ItemStackIngredient itemInput,
            ChemicalStackIngredient chemicalInput, ItemStack output, boolean perTickUsage) {
        return new MekInItemStackChemicalToItemStackRecipeBuilder(itemInput, chemicalInput, output, perTickUsage,
                BasicRefiningRecipe::new);
    }

}
