package com.takenokoshi.mekin.recipe.recipes.prefab;

import java.util.Collections;
import java.util.List;

import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.MekanismRecipe;
import mekanism.api.recipes.ingredients.ChemicalStackIngredient;
import mekanism.api.recipes.ingredients.FluidStackIngredient;
import mekanism.api.recipes.ingredients.ItemStackIngredient;
import mekanism.api.recipes.vanilla_input.ReactionRecipeInput;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.util.TriPredicate;
import net.neoforged.neoforge.fluids.FluidStack;

public abstract class ItemStackFluidChemicalToItemStackRecipe extends MekanismRecipe<ReactionRecipeInput>
        implements TriPredicate<ItemStack, FluidStack, ChemicalStack> {

    protected final RecipeType<? extends ItemStackFluidChemicalToItemStackRecipe> recipeType;

    public final ItemStackIngredient itemInput;
    public final FluidStackIngredient fluidInput;
    public final ChemicalStackIngredient chemicalInput;
    public final long energyRequired;
    public final int duration;
    public final ItemStack outputItem;

    protected ItemStackFluidChemicalToItemStackRecipe(
            RecipeType<? extends ItemStackFluidChemicalToItemStackRecipe> recipeType,
            ItemStackIngredient itemInput,
            FluidStackIngredient fluidInput,
            ChemicalStackIngredient chemicalInput,
            long energyRequired,
            int duration,
            ItemStack outputItem) {
        this.recipeType = recipeType;
        this.itemInput = itemInput;
        this.fluidInput = fluidInput;
        this.chemicalInput = chemicalInput;
        this.energyRequired = energyRequired;
        this.duration = duration;
        this.outputItem = outputItem;
    }

    @Override
    public RecipeType<?> getType() {
        return recipeType;
    }

    public ItemStackIngredient getItemInput() {
        return itemInput;
    }

    public FluidStackIngredient getFluidInput() {
        return fluidInput;
    }

    public ChemicalStackIngredient getChemicalInput() {
        return chemicalInput;
    }

    public long getEnergyRequired() {
        return this.energyRequired;
    }

    public int getDuration() {
        return this.duration;
    }

    @Override
    public boolean test(ItemStack arg0, FluidStack arg1, ChemicalStack arg2) {
        return itemInput.test(arg0) && fluidInput.test(arg1) && chemicalInput.test(arg2);
    }

    public List<ItemStack> getOutputDefinition() {
        return Collections.singletonList(outputItem);
    }

    public ItemStack getOutput() {
        return outputItem;
    }

    @Override
    public boolean matches(ReactionRecipeInput input, Level arg1) {
        return itemInput.test(input.item()) && fluidInput.testType(input.fluid())
                && chemicalInput.testType(input.chemical());
    }

    @Override
    public boolean isIncomplete() {
        return itemInput.hasNoMatchingInstances()||fluidInput.hasNoMatchingInstances()||chemicalInput.hasNoMatchingInstances();
    }

    @Override
    public void logMissingTags() {
        itemInput.logMissingTags();
        fluidInput.logMissingTags();
        chemicalInput.logMissingTags();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            ItemStackFluidChemicalToItemStackRecipe other = (ItemStackFluidChemicalToItemStackRecipe) o;
            return this.energyRequired == other.energyRequired
                    && this.duration == other.duration
                    && this.itemInput.equals(other.itemInput)
                    && this.fluidInput.equals(other.fluidInput)
                    && this.chemicalInput.equals(other.chemicalInput)
                    && ItemStack.matches(this.outputItem, other.outputItem);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int result = this.itemInput.hashCode();
        result = 31 * result + this.fluidInput.hashCode();
        result = 31 * result + this.chemicalInput.hashCode();
        result = 31 * result + Long.hashCode(this.energyRequired);
        result = 31 * result + this.duration;
        result = 31 * result + ItemStack.hashItemAndComponents(this.outputItem);
        result = 31 * result + this.outputItem.getCount();
        return result;
    }

}
