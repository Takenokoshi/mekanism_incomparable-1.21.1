package com.takenokoshi.mekin.recipe.builder;

import com.mojang.datafixers.util.Function3;
import com.takenokoshi.mekin.recipe.recipes.basic.BasicAETRecipe;

import mekanism.api.chemical.ChemicalStack;
import mekanism.api.datagen.recipe.MekanismRecipeBuilder;
import mekanism.api.recipes.ingredients.ChemicalStackIngredient;
import mekanism.api.recipes.ingredients.ItemStackIngredient;
import net.minecraft.world.item.crafting.Recipe;

public class ItemStackChemicalToChemicalRecipeBuilder
        extends MekanismRecipeBuilder<ItemStackChemicalToChemicalRecipeBuilder> {

    protected final ItemStackIngredient itemInput;
    protected final ChemicalStackIngredient chemicalInput;
    protected final ChemicalStack output;
    protected final Function3<ItemStackIngredient, ChemicalStackIngredient, ChemicalStack, Recipe<?>> factory;

    protected ItemStackChemicalToChemicalRecipeBuilder(
            ItemStackIngredient itemInput, ChemicalStackIngredient chemicalInput, ChemicalStack output,
            Function3<ItemStackIngredient, ChemicalStackIngredient, ChemicalStack, Recipe<?>> factory) {
        this.itemInput = itemInput;
        this.chemicalInput = chemicalInput;
        this.output = output;
        this.factory = factory;
    }

    public static ItemStackChemicalToChemicalRecipeBuilder aet(ItemStackIngredient itemInput,
            ChemicalStackIngredient chemicalInput, ChemicalStack output) {
        return new ItemStackChemicalToChemicalRecipeBuilder(itemInput, chemicalInput, output,
                BasicAETRecipe::new);
    }

    @Override
    protected Recipe<?> asRecipe() {
        return factory.apply(itemInput, chemicalInput, output);
    }

}
