package com.takenokoshi.mekin.recipe.builder;

import com.mojang.datafixers.util.Function4;
import com.takenokoshi.mekin.recipe.recipes.basic.BasicChemicalExtractionRecipe;
import com.takenokoshi.mekin.recipe.recipes.prefab.FluidChemicalToBiChemicalRecipe;

import mekanism.api.chemical.ChemicalStack;
import mekanism.api.datagen.recipe.MekanismRecipeBuilder;
import mekanism.api.recipes.ingredients.ChemicalStackIngredient;
import mekanism.api.recipes.ingredients.FluidStackIngredient;
import net.minecraft.world.item.crafting.Recipe;

public class FluidChemicalToBiChemicalRecipeBuilder
        extends MekanismRecipeBuilder<FluidChemicalToBiChemicalRecipeBuilder> {

    protected final FluidStackIngredient fluidInput;
    protected final ChemicalStackIngredient chemicalInput;
    protected final ChemicalStack mainOutput;
    protected final ChemicalStack subOutput;
    protected final Function4<FluidStackIngredient, ChemicalStackIngredient, ChemicalStack, ChemicalStack, FluidChemicalToBiChemicalRecipe> builder;

    protected FluidChemicalToBiChemicalRecipeBuilder(
            Function4<FluidStackIngredient, ChemicalStackIngredient, ChemicalStack, ChemicalStack, FluidChemicalToBiChemicalRecipe> builder,
            FluidStackIngredient fluidInput, ChemicalStackIngredient chemicalInput, ChemicalStack mainOutput,
            ChemicalStack subOutput) {
        this.fluidInput = fluidInput;
        this.chemicalInput = chemicalInput;
        this.mainOutput = mainOutput;
        this.subOutput = subOutput;
        this.builder = builder;
    }

    public static FluidChemicalToBiChemicalRecipeBuilder chemicalExtraction(FluidStackIngredient fluidInput,
            ChemicalStackIngredient chemicalInput, ChemicalStack mainOutput,
            ChemicalStack subOutput) {
        return new FluidChemicalToBiChemicalRecipeBuilder(BasicChemicalExtractionRecipe::new, fluidInput, chemicalInput,
                mainOutput, subOutput);
    }

    @Override
    protected Recipe<?> asRecipe() {
        return builder.apply(fluidInput, chemicalInput, mainOutput, subOutput);
    }

}
