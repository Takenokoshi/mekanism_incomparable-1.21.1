package com.takenokoshi.mekin.recipe.recipes.prefab;

import java.util.List;

import com.takenokoshi.mekin.recipe.output.MeteorCollectorRecipeOutput;

import mekanism.api.recipes.MekanismRecipe;
import mekanism.api.recipes.ingredients.ItemStackIngredient;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.util.TriPredicate;

public abstract class MeteorCollectorRecipe extends MekanismRecipe<RecipeInput>
        implements TriPredicate<ItemStack, ItemStack, Level> {

    public final ItemStackIngredient input;
    public final ItemStackIngredient catalyst;
    public final ResourceKey<Level> dimension;
    public final List<MeteorCollectorRecipeOutput> outputs;
    public final boolean requireAdvanced;

    public final int totalWeight;
    private final int[] weightCache;

    public final int maxOutputSize;

    private final RecipeType<? extends MeteorCollectorRecipe> recipeType;

    protected MeteorCollectorRecipe(ItemStackIngredient input, ItemStackIngredient catalyst,
            ResourceKey<Level> dimension, List<MeteorCollectorRecipeOutput> outputs,
            RecipeType<? extends MeteorCollectorRecipe> recipeType, boolean requireAdvanced) {
        this.input = input;
        this.catalyst = catalyst;
        this.dimension = dimension;
        if (outputs.isEmpty()) {
            throw new IllegalArgumentException("Meteor collector recipe requires at least one output.");
        }
        this.outputs = List.copyOf(outputs);

        this.totalWeight = this.outputs.stream().mapToInt(MeteorCollectorRecipeOutput::weight).sum();
        if (totalWeight <= 0) {
            throw new IllegalArgumentException("Total weight must be positive.");
        }
        this.weightCache = new int[this.outputs.size()];
        int total = 0;
        this.requireAdvanced = requireAdvanced;
        for (int i = 0; i < weightCache.length; i++) {
            total += this.outputs.get(i).weight();
            weightCache[i] = total;
        }
        this.maxOutputSize = this.outputs.stream().mapToInt(MeteorCollectorRecipeOutput::valueSize).max()
                .getAsInt();
        this.recipeType = recipeType;
    }

    @Override
    public RecipeType<?> getType() {
        return recipeType;
    }

    public int getIndexByValue(int value) {
        value = Math.clamp(value, 0, totalWeight - 1);
        int i = 0;
        for (; i < weightCache.length; i++) {
            if (weightCache[i] > value) {
                return i;
            }
        }
        return i;
    }

    public List<ItemStack> getOutputByValue(int value) {
        return this.outputs.get(getIndexByValue(value)).value().stream()
                .map(ItemStack::copy)
                .toList();
    }

    public ItemStackIngredient getInput() {
        return input;
    }

    public ItemStackIngredient getCatalyst() {
        return catalyst;
    }

    public ResourceKey<Level> getDimension() {
        return dimension;
    }

    public List<MeteorCollectorRecipeOutput> getOutputs() {
        return outputs;
    }

    public boolean getRequireAdvanced() {
        return requireAdvanced;
    }

    @Override
    public boolean test(ItemStack input, ItemStack catalyst, Level dimension) {
        return this.input.test(input) && this.catalyst.test(catalyst) && this.dimension.equals(dimension.dimension());
    }

    @Override
    public boolean matches(RecipeInput arg0, Level arg1) {
        return false;
    }

    @Override
    public boolean isIncomplete() {
        return input.hasNoMatchingInstances() || catalyst.hasNoMatchingInstances();
    }

    @Override
    public void logMissingTags() {
        super.logMissingTags();
        input.logMissingTags();
        catalyst.logMissingTags();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o != null && o.getClass() == this.getClass()) {
            MeteorCollectorRecipe other = (MeteorCollectorRecipe) o;
            return this.dimension.equals(other.dimension)
                    && this.input.equals(other.input)
                    && this.catalyst.equals(other.catalyst)
                    && this.outputs.equals(other.outputs);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int result = this.input.hashCode();
        result = 31 * result + this.catalyst.hashCode();
        result = 31 * result + this.dimension.hashCode();
        result = 31 * result + this.outputs.hashCode();
        return result;
    }

}
