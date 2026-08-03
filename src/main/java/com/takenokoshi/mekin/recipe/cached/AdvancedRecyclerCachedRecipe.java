package com.takenokoshi.mekin.recipe.cached;

import java.util.function.BooleanSupplier;

import com.jerry.mekmm.api.recipes.RecyclerRecipe;
import com.takenokoshi.mekin.recipe.output.MekInChanceOutput;
import com.takenokoshi.mekut.recipe.cached.BasicCachedRecipe;

import mekanism.api.recipes.inputs.IInputHandler;
import mekanism.api.recipes.outputs.IOutputHandler;
import net.minecraft.world.item.ItemStack;

public class AdvancedRecyclerCachedRecipe extends BasicCachedRecipe<RecyclerRecipe> {

    private final IInputHandler<ItemStack> inputHandler;
    private final IOutputHandler<MekInChanceOutput> outputHandler;

    private MekInChanceOutput recipeOutput = MekInChanceOutput.EMPTY;
    private ItemStack recipeInput = ItemStack.EMPTY;

    public AdvancedRecyclerCachedRecipe(RecyclerRecipe recipe, BooleanSupplier recheckAllErrors,
            IInputHandler<ItemStack> inputHandler, IOutputHandler<MekInChanceOutput> outputHandler) {
        super(recipe, recheckAllErrors);
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
    }

    @Override
    protected void calculateOperationsThisTick(OperationTracker tracker) {
        super.calculateOperationsThisTick(tracker);
        if (!tracker.shouldContinueChecking()) {
            return;
        }
        recipeInput = inputHandler.getRecipeInput(recipe.getInput());
        if (recipeInput.isEmpty()) {
            tracker.mismatchedRecipe();
            return;
        }
        recipeOutput = new MekInChanceOutput(recipe.getOutput(recipeInput).getMaxChanceOutput(),
                recipe.getOutputChance());
        inputHandler.calculateOperationsCanSupport(tracker, recipeInput);
        outputHandler.calculateOperationsCanSupport(tracker, recipeOutput);
    }

    @Override
    public boolean isInputValid() {
        return recipe.getInput().test(inputHandler.getInput());
    }

    @Override
    protected void finishProcessing(int operations) {
        if (recipeInput.isEmpty()) {
            return;
        }
        inputHandler.use(recipeInput, operations);
        outputHandler.handleOutput(recipeOutput, operations);
    }

}
