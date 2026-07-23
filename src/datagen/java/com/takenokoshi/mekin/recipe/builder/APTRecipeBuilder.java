package com.takenokoshi.mekin.recipe.builder;

import fr.iglee42.evolvedmekanism.impl.BasicAPTRecipe;
import mekanism.api.datagen.recipe.builder.ItemStackChemicalToItemStackRecipeBuilder;
import mekanism.api.recipes.ingredients.ChemicalStackIngredient;
import mekanism.api.recipes.ingredients.ItemStackIngredient;
import net.minecraft.world.item.ItemStack;

public class APTRecipeBuilder extends ItemStackChemicalToItemStackRecipeBuilder {

    public APTRecipeBuilder(ItemStackIngredient itemInput, ChemicalStackIngredient chemicalInput, ItemStack output,
            boolean perTickUsage) {
        super(itemInput, chemicalInput, output, perTickUsage, BasicAPTRecipe::new);
    }

}
