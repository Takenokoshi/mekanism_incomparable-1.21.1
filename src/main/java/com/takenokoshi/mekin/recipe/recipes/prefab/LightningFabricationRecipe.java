package com.takenokoshi.mekin.recipe.recipes.prefab;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.jetbrains.annotations.Nullable;

import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.MekanismRecipe;
import mekanism.api.recipes.ingredients.ChemicalStackIngredient;
import mekanism.api.recipes.ingredients.ItemStackIngredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public abstract class LightningFabricationRecipe extends MekanismRecipe<RecipeInput> {

    protected final RecipeType<? extends LightningFabricationRecipe> recipeType;

    public final ItemStackIngredient mainInput;
    public final List<ItemStackIngredient> extraInputs;
    public final @Nullable ChemicalStackIngredient chemicalInput;
    public final ItemStack output;

    public final long energyRequired;
    public final int duration;

    protected LightningFabricationRecipe(RecipeType<? extends LightningFabricationRecipe> recipeType,
            ItemStackIngredient mainInput, List<ItemStackIngredient> extraInputs,
            Optional<ChemicalStackIngredient> chemicalInput,
            ItemStack output, long energyRequired, int duration) {
        this.recipeType = recipeType;
        this.mainInput = mainInput;
        this.extraInputs = List.copyOf(extraInputs);
        this.chemicalInput = chemicalInput.orElse(null);
        this.output = output.copy();
        this.energyRequired = energyRequired;
        this.duration = duration;
    }

    @Override
    public RecipeType<?> getType() {
        return recipeType;
    }

    public ItemStackIngredient getMainInput() {
        return mainInput;
    }

    public List<ItemStackIngredient> getExtraInputs() {
        return extraInputs;
    }

    public @Nullable ChemicalStackIngredient getChemicalInput() {
        return chemicalInput;
    }

    public Optional<ChemicalStackIngredient> getChemicalInputAsOptional() {
        return chemicalInput == null ? Optional.empty() : Optional.of(chemicalInput);
    }

    public ItemStack getOutput() {
        return output;
    }

    public long getEnergyRequired() {
        return energyRequired;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public boolean isIncomplete() {
        return mainInput.hasNoMatchingInstances()
                || extraInputs.stream().anyMatch(ItemStackIngredient::hasNoMatchingInstances)
                || (chemicalInput != null && chemicalInput.hasNoMatchingInstances());
    }

    @Override
    public void logMissingTags() {
        mainInput.logMissingTags();
        extraInputs.forEach(ItemStackIngredient::logMissingTags);
        if (chemicalInput != null) {
            chemicalInput.logMissingTags();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o != null && o.getClass() == this.getClass()) {
            LightningFabricationRecipe other = (LightningFabricationRecipe) o;
            return this.energyRequired == other.energyRequired
                    && this.duration == other.duration
                    && this.mainInput.equals(other.mainInput)
                    && this.extraInputs.equals(other.extraInputs)
                    && Objects.equals(this.chemicalInput, other.chemicalInput)
                    && ItemStack.matches(this.output, other.output);
        } else {
            return false;
        }
    }

    /**
     * Unused by the Mekanism/MekAL recipe system.
     */
    @Override
    public boolean matches(RecipeInput arg0, Level arg1) {
        return false;
    }

    @Override
    public int hashCode() {
        int result = this.mainInput.hashCode();
        result = 31 * result + this.extraInputs.hashCode();
        result = 31 * result + Objects.hashCode(this.chemicalInput);
        result = 31 * result + Long.hashCode(this.energyRequired);
        result = 31 * result + this.duration;
        result = 31 * result + ItemStack.hashItemAndComponents(this.output);
        result = 31 * result + this.output.getCount();
        return result;
    }

    public boolean test(ItemStack mainInput, List<ItemStack> subInputs, ChemicalStack chemicalInput) {
        if (!this.mainInput.test(mainInput)) {
            return false;
        }
        if (this.chemicalInput == null
                ? !chemicalInput.isEmpty()
                : !this.chemicalInput.test(chemicalInput)) {
            return false;
        }
        if (this.extraInputs.size() != subInputs.size()) {
            return false;
        }
        boolean[] matchedIngredients = new boolean[this.extraInputs.size()];
        for (int i = 0; i < subInputs.size(); i++) {
            ItemStack subInput = subInputs.get(i);
            boolean found = false;
            for (int j = 0; j < this.extraInputs.size(); j++) {
                if (matchedIngredients[j]) {
                    continue;
                }
                if (this.extraInputs.get(j).test(subInput)) {
                    matchedIngredients[j] = true;
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }

    public boolean cacheTest(ItemStack mainInput, List<ItemStack> extraInputs, ChemicalStack chemicalInput) {
        if (!mainInput.isEmpty() && !this.mainInput.test(mainInput)) {
            return false;
        }
        if (!chemicalInput.isEmpty()) {
            if (this.chemicalInput == null) {
                return false;
            } else if (!this.chemicalInput.test(chemicalInput)) {
                return false;
            }
        }
        if (this.extraInputs.size() < extraInputs.size()) {
            return false;
        }
        boolean[] matchedIngredients = new boolean[this.extraInputs.size()];
        for (ItemStack extraInput : extraInputs) {
            boolean found = false;
            for (int j = 0; j < this.extraInputs.size(); j++) {
                if (matchedIngredients[j]) {
                    continue;
                }
                if (this.extraInputs.get(j).test(extraInput)) {
                    matchedIngredients[j] = true;
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }

}
