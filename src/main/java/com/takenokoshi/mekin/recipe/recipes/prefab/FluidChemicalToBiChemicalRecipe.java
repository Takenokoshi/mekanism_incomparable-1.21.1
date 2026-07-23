package com.takenokoshi.mekin.recipe.recipes.prefab;

import java.util.function.BiPredicate;

import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.MekanismRecipe;
import mekanism.api.recipes.ingredients.ChemicalStackIngredient;
import mekanism.api.recipes.ingredients.FluidStackIngredient;
import mekanism.api.recipes.vanilla_input.SingleFluidChemicalRecipeInput;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.FluidStack;

public abstract class FluidChemicalToBiChemicalRecipe extends MekanismRecipe<SingleFluidChemicalRecipeInput>
        implements BiPredicate<FluidStack, ChemicalStack> {

    protected final RecipeType<FluidChemicalToBiChemicalRecipe> recipeType;

    public final FluidStackIngredient fluidInput;
    public final ChemicalStackIngredient chemicalInput;

    public final ChemicalStack mainOutput;
    public final ChemicalStack subOutput;

    protected FluidChemicalToBiChemicalRecipe(RecipeType<FluidChemicalToBiChemicalRecipe> recipeType,
            FluidStackIngredient fluidInput,
            ChemicalStackIngredient chemicalInput,
            ChemicalStack mainOutput,
            ChemicalStack subOutput) {
        this.recipeType = recipeType;
        this.fluidInput = fluidInput;
        this.chemicalInput = chemicalInput;
        this.mainOutput = mainOutput;
        this.subOutput = subOutput;
    }

    @Override
    public RecipeType<?> getType() {
        return recipeType;
    }

    public FluidStackIngredient getFluidInput() {
        return fluidInput;
    }

    public ChemicalStackIngredient getChemicalInput() {
        return chemicalInput;
    }

    public ChemicalStack getMainOutputRaw() {
        return mainOutput;
    }

    public ChemicalStack getSubOutputRaw() {
        return subOutput;
    }

    @Override
    public boolean test(FluidStack t, ChemicalStack u) {
        return fluidInput.test(t) && chemicalInput.test(u);
    }

    @Override
    public boolean isIncomplete() {
        return fluidInput.hasNoMatchingInstances() || chemicalInput.hasNoMatchingInstances();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FluidChemicalToBiChemicalRecipe other = (FluidChemicalToBiChemicalRecipe) o;
        return fluidInput.equals(other.fluidInput) && chemicalInput.equals(other.chemicalInput);
    }

    @Override
    public int hashCode() {
        int result = fluidInput.hashCode();
        result = result*31+chemicalInput.hashCode();
        result = result*31+mainOutput.hashCode();
        result = result*31+subOutput.hashCode();
        return result;
    }

    @Override
    public boolean matches(SingleFluidChemicalRecipeInput input, Level world) {
        return !isIncomplete() && fluidInput.test(input.fluid()) && chemicalInput.test(input.chemical());
    }

    @Override
    public void logMissingTags() {
        fluidInput.logMissingTags();
        chemicalInput.logMissingTags();
    }
}
