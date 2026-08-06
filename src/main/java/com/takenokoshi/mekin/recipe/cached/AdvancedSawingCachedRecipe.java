package com.takenokoshi.mekin.recipe.cached;

import java.util.function.BooleanSupplier;

import com.takenokoshi.mekin.recipe.output.MekInChanceOutput;
import com.takenokoshi.mekut.recipe.cached.BasicCachedRecipe;

import mekanism.api.recipes.SawmillRecipe;
import mekanism.api.recipes.inputs.IInputHandler;
import mekanism.api.recipes.outputs.IOutputHandler;
import net.minecraft.world.item.ItemStack;

public class AdvancedSawingCachedRecipe extends BasicCachedRecipe<SawmillRecipe> {

    private final IInputHandler<ItemStack> inputHandler;
    private final IOutputHandler<ItemStack> mainOutputHandler;
    private final IOutputHandler<MekInChanceOutput> subOutputHandler;

    private ItemStack recipeInput = ItemStack.EMPTY;
    private ItemStack mainOutput = ItemStack.EMPTY;
    private MekInChanceOutput subOutput = MekInChanceOutput.EMPTY;

    public AdvancedSawingCachedRecipe(SawmillRecipe recipe, BooleanSupplier recheckAllErrors,
            IInputHandler<ItemStack> inputHandler, IOutputHandler<ItemStack> mainOutputHandler,
            IOutputHandler<MekInChanceOutput> subOutputHandler) {
        super(recipe, recheckAllErrors);
        this.inputHandler = inputHandler;
        this.mainOutputHandler = mainOutputHandler;
        this.subOutputHandler = subOutputHandler;
    }

    @Override
    protected void calculateOperationsThisTick(OperationTracker tracker) {
        super.calculateOperationsThisTick(tracker);
        if (!tracker.shouldContinueChecking()) {
            return;
        }
        recipeInput = inputHandler.getRecipeInput(recipe.getInput());

        recipeInput = inputHandler.getRecipeInput(recipe.getInput());
        if (recipeInput.isEmpty()) {
            tracker.mismatchedRecipe();
            return;
        }
        var recipeOutput = recipe.getOutput(recipeInput);
        mainOutput = recipeOutput.getMainOutput();
        subOutput = new MekInChanceOutput(recipeOutput.getMaxSecondaryOutput(), recipe.getSecondaryChance());
        mainOutputHandler.calculateOperationsCanSupport(tracker, mainOutput);
        subOutputHandler.calculateOperationsCanSupport(tracker, subOutput);
    }

    @Override
    public boolean isInputValid() {
        return recipe.getInput().test(inputHandler.getInput());
    }

    @Override
    protected void finishProcessing(int operations) {
        if (recipeInput.isEmpty() || (mainOutput.isEmpty() && subOutput.isEmpty())) {
            return;
        }
        inputHandler.use(recipeInput, operations);
        mainOutputHandler.handleOutput(mainOutput, operations);
        subOutputHandler.handleOutput(subOutput, operations);
    }

}
