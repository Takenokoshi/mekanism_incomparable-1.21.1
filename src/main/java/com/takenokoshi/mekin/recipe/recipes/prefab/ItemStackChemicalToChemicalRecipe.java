package com.takenokoshi.mekin.recipe.recipes.prefab;

import java.util.Collections;
import java.util.List;

import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.ItemStackChemicalToObjectRecipe;
import mekanism.api.recipes.ingredients.ChemicalStackIngredient;
import mekanism.api.recipes.ingredients.ItemStackIngredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;

public abstract class ItemStackChemicalToChemicalRecipe extends ItemStackChemicalToObjectRecipe<ChemicalStack> {

    protected final RecipeType<? extends ItemStackChemicalToChemicalRecipe> recipeType;

    public final ItemStackIngredient itemInput;
    public final ChemicalStackIngredient chemicalInput;
    public final ChemicalStack output;

    protected ItemStackChemicalToChemicalRecipe(RecipeType<? extends ItemStackChemicalToChemicalRecipe> recipeType,
            ItemStackIngredient itemInput, ChemicalStackIngredient chemicalInput, ChemicalStack output) {
        this.recipeType = recipeType;
        this.itemInput = itemInput;
        this.chemicalInput = chemicalInput;
        this.output = output;
    }

    @Override
    public RecipeType<?> getType() {
        return recipeType;
    }

    public boolean perTickUsage() {
        return false;
    }

    public ItemStackIngredient getItemInput() {
        return this.itemInput;
    }

    public ChemicalStackIngredient getChemicalInput() {
        return this.chemicalInput;
    }

    public ChemicalStack getOutput(ItemStack inputItem, ChemicalStack inputChemical) {
        return this.output.copy();
    }

    public boolean test(ItemStack itemStack, ChemicalStack chemicalStack) {
        return this.itemInput.test(itemStack) && this.chemicalInput.test(chemicalStack);
    }

    public List<ChemicalStack> getOutputDefinition() {
        return Collections.singletonList(this.output);
    }

    public ChemicalStack getOutputRaw() {
        return this.output;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            ItemStackChemicalToChemicalRecipe other = (ItemStackChemicalToChemicalRecipe) o;
            return this.itemInput.equals(other.itemInput)
                    && this.chemicalInput.equals(other.chemicalInput)
                    && this.output.equals(other.output);
        } else {
            return false;
        }
    }

    public int hashCode() {
        int result = this.itemInput.hashCode();
        result = 31 * result + this.chemicalInput.hashCode();
        result = 31 * result + this.output.hashCode();
        return result;
    }
}
