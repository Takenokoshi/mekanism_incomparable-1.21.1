package com.takenokoshi.mekin.recipe.builder;

import fr.iglee42.evolvedmekanism.impl.BasicChemixerRecipe;
import mekanism.api.datagen.recipe.MekanismRecipeBuilder;
import mekanism.api.recipes.ingredients.ChemicalStackIngredient;
import mekanism.api.recipes.ingredients.ItemStackIngredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;

public class ChemixerRecipeBuilder extends MekanismRecipeBuilder<ChemixerRecipeBuilder> {

    private final ItemStackIngredient mainInput;
    private final ItemStackIngredient subInput;
    private final ChemicalStackIngredient chemicalInput;
    private final ItemStack output;

    public ChemixerRecipeBuilder(ItemStackIngredient mainInput, ItemStackIngredient subInput,
            ChemicalStackIngredient chemicalInput, ItemStack output) {
        this.mainInput = mainInput;
        this.subInput = subInput;
        this.chemicalInput = chemicalInput;
        this.output = output;
    }

    @Override
    protected Recipe<?> asRecipe() {
        return new BasicChemixerRecipe(mainInput, subInput, chemicalInput, output);
    }
}
