package com.takenokoshi.mekin.recipe.cached;

import java.util.List;
import java.util.function.BooleanSupplier;

import com.moakiee.ae2lt.lightning.LightningTransformRecipe;
import com.takenokoshi.mekaddonlib.recipe.cached.AbstractCachedRecipe;
import com.takenokoshi.mekin.recipe.MekInIngredientUtils;
import com.takenokoshi.mekut.recipe.input.ItemStackListInputHandler;

import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.cache.CachedRecipe.OperationTracker;
import mekanism.api.recipes.ingredients.ItemStackIngredient;
import mekanism.api.recipes.inputs.IInputHandler;
import mekanism.api.recipes.outputs.IOutputHandler;
import net.minecraft.world.item.ItemStack;

import static com.takenokoshi.mekin.recipe.MekInRecipeConstants.LIGHTNING_INGREDIENT;

public class LightningTransformCachedRecipe extends AbstractCachedRecipe<LightningTransformRecipe> {

    private final ItemStackListInputHandler itemInputHandler;
    private final IInputHandler<ChemicalStack> chemicalInputHandler;
    private final IOutputHandler<ItemStack> outputHandler;

    private final List<ItemStackIngredient> ingredients;

    private int[] itemIngredientIndexCache = new int[] {};

    private List<ItemStack> itemRecipeInput = List.of();
    private ChemicalStack chemicalRecipeInput = ChemicalStack.EMPTY;

    private final ItemStack recipeOutput;

    public LightningTransformCachedRecipe(LightningTransformRecipe recipe, BooleanSupplier recheckAllErrors,
            ItemStackListInputHandler itemInputHandler, IInputHandler<ChemicalStack> chemicalInputHandler,
            IOutputHandler<ItemStack> outputHandler) {
        super(recipe, recheckAllErrors);
        this.itemInputHandler = itemInputHandler;
        this.chemicalInputHandler = chemicalInputHandler;
        this.outputHandler = outputHandler;

        this.ingredients = this.recipe.inputs().stream().map(MekInIngredientUtils::convert).toList();
        this.recipeOutput = this.recipe.getResultItem(null);
    }

    @Override
    protected void calculateOperationsThisTick(OperationTracker tracker) {
        super.calculateOperationsThisTick(tracker);
        if (!tracker.shouldContinueChecking()) {
            return;
        }
        itemRecipeInput = itemInputHandler.getRecipeInput(ingredients, v -> itemIngredientIndexCache = v);
        chemicalRecipeInput = chemicalInputHandler.getRecipeInput(LIGHTNING_INGREDIENT);

        if (itemRecipeInput.isEmpty() || chemicalRecipeInput.isEmpty()) {
            tracker.mismatchedRecipe();
            return;
        }

        itemInputHandler.calculateOperationsCanSupport(tracker, itemRecipeInput, itemIngredientIndexCache);
        chemicalInputHandler.calculateOperationsCanSupport(tracker, chemicalRecipeInput);
        outputHandler.calculateOperationsCanSupport(tracker, recipeOutput);
    }

    @Override
    protected void finishProcessing(int operations) {
        itemInputHandler.use(itemRecipeInput, itemIngredientIndexCache, operations);
        chemicalInputHandler.use(chemicalRecipeInput, operations);
        outputHandler.handleOutput(recipeOutput, operations);
    }

    @Override
    public boolean isInputValid() {
        return !itemInputHandler.getRecipeInput(ingredients, v -> {
        }).isEmpty() && !chemicalInputHandler.getRecipeInput(LIGHTNING_INGREDIENT).isEmpty();
    }

}
