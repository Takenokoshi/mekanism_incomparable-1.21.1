package com.takenokoshi.mekin.recipe.cached;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import com.takenokoshi.mekin.recipe.output.MeteorCollectorOutputHandler;
import com.takenokoshi.mekin.recipe.recipes.prefab.MeteorCollectorRecipe;
import com.takenokoshi.mekut.recipe.cached.BasicCachedRecipe;

import mekanism.api.recipes.inputs.IInputHandler;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MeteorCollectorCachedRecipe extends BasicCachedRecipe<MeteorCollectorRecipe> {

    private final IInputHandler<ItemStack> inputHandler, catalystHandler;
    private final Supplier<Level> levelSupplier;
    private final MeteorCollectorOutputHandler outputHandler;
    private final boolean isAdvanced;

    private ItemStack recipeInput = ItemStack.EMPTY;
    private ItemStack recipeCatalyst = ItemStack.EMPTY;

    public MeteorCollectorCachedRecipe(MeteorCollectorRecipe recipe, BooleanSupplier recheckAllErrors,
            IInputHandler<ItemStack> inputHandler, IInputHandler<ItemStack> catalystHandler,
            Supplier<Level> levelSupplier, MeteorCollectorOutputHandler outputHandler, boolean isAdvanced) {
        super(recipe, recheckAllErrors);
        this.inputHandler = inputHandler;
        this.catalystHandler = catalystHandler;
        this.levelSupplier = levelSupplier;
        this.outputHandler = outputHandler;
        this.isAdvanced = isAdvanced;
    }

    @Override
    protected void calculateOperationsThisTick(OperationTracker tracker) {
        super.calculateOperationsThisTick(tracker);
        if (!tracker.shouldContinueChecking()) {
            return;
        }
        if (recipe.requireAdvanced && !isAdvanced) {
            tracker.mismatchedRecipe();
            return;
        }
        if (!recipe.dimension.equals(levelSupplier.get().dimension())) {
            tracker.mismatchedRecipe();
            return;
        }
        if (!outputHandler.canOutputNow(recipe.maxOutputSize)) {
            outputHandler.notEnoughSpaceError(tracker);
            return;
        }
        recipeInput = inputHandler.getRecipeInput(recipe.input);
        recipeCatalyst = catalystHandler.getRecipeInput(recipe.catalyst);
        if (recipeInput.isEmpty() || recipeCatalyst.isEmpty()) {
            tracker.mismatchedRecipe();
            return;
        }
        inputHandler.calculateOperationsCanSupport(tracker, recipeInput);
    }

    @Override
    public boolean isInputValid() {
        return recipe.input.test(inputHandler.getInput())
                && recipe.catalyst.test(catalystHandler.getInput())
                && recipe.dimension.equals(levelSupplier.get().dimension())
                && (recipe.requireAdvanced ? isAdvanced : true);
    }

    @Override
    protected void finishProcessing(int operations) {
        if (operations > 1) {
            return;
        }
        inputHandler.use(recipeInput, operations);
        outputHandler.handleOutput(recipe.getOutputByValue(ThreadLocalRandom.current().nextInt(recipe.totalWeight)));
    }

}
