package com.takenokoshi.mekin.recipe.cached;

import java.util.List;
import java.util.function.BooleanSupplier;

import com.takenokoshi.mekin.recipe.recipes.prefab.LightningFabricationRecipe;
import com.takenokoshi.mekut.recipe.cached.BasicCachedRecipe;
import com.takenokoshi.mekut.recipe.input.ItemStackListInputHandler;

import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.inputs.IInputHandler;
import mekanism.api.recipes.outputs.IOutputHandler;
import net.minecraft.world.item.ItemStack;

public class LightningFabricationCachedRecipe extends BasicCachedRecipe<LightningFabricationRecipe> {

    private final IInputHandler<ItemStack> mainInputHandler;
    private final ItemStackListInputHandler extraInputHandler;
    private final IInputHandler<ChemicalStack> chemicalInputHandler;
    private final IOutputHandler<ItemStack> outputHandler;

    private ItemStack mainInput = ItemStack.EMPTY;
    private List<ItemStack> extraInputs = List.of();
    private ChemicalStack chemicalInput = ChemicalStack.EMPTY;

    private int[] extraIndexCache = new int[0];

    public LightningFabricationCachedRecipe(LightningFabricationRecipe recipe, BooleanSupplier recheckAllErrors,
            IInputHandler<ItemStack> mainInputHandler, ItemStackListInputHandler extraInputHandler,
            IInputHandler<ChemicalStack> chemicalInputHandler, IOutputHandler<ItemStack> outputHandler) {
        super(recipe, recheckAllErrors);
        this.mainInputHandler = mainInputHandler;
        this.extraInputHandler = extraInputHandler;
        this.chemicalInputHandler = chemicalInputHandler;
        this.outputHandler = outputHandler;
    }

    @Override
    protected void calculateOperationsThisTick(OperationTracker tracker) {
        super.calculateOperationsThisTick(tracker);
        if (!tracker.shouldContinueChecking()) {
            return;
        }
        mainInput = mainInputHandler.getRecipeInput(recipe.mainInput);

        if (mainInput.isEmpty()) {
            tracker.mismatchedRecipe();
            return;
        }
        mainInputHandler.calculateOperationsCanSupport(tracker, mainInput);
        if (recipe.extraInputs.isEmpty()) {
            if (!extraInputHandler.getInput().isEmpty()) {
                tracker.mismatchedRecipe();
                return;
            }
            extraInputs = List.of();
            extraIndexCache = new int[0];
        } else {
            extraInputs = extraInputHandler.getRecipeInput(recipe.extraInputs, v -> extraIndexCache = v);
            if (extraInputs.isEmpty()) {
                tracker.mismatchedRecipe();
                return;
            }
            extraInputHandler.calculateOperationsCanSupport(tracker, extraInputs, extraIndexCache);
        }
        if (recipe.chemicalInput == null) {
            if (!chemicalInputHandler.getInput().isEmpty()) {
                tracker.mismatchedRecipe();
                return;
            }
        } else {
            chemicalInput = chemicalInputHandler.getRecipeInput(recipe.chemicalInput);
            if (chemicalInput.isEmpty()) {
                tracker.mismatchedRecipe();
                return;
            }
            chemicalInputHandler.calculateOperationsCanSupport(tracker, chemicalInput);
        }
        outputHandler.calculateOperationsCanSupport(tracker, recipe.output);
    }

    @Override
    public boolean isInputValid() {
        return recipe.test(mainInputHandler.getInput(), extraInputHandler.getInput(), chemicalInputHandler.getInput());
    }

    @Override
    protected void finishProcessing(int operations) {
        mainInputHandler.use(mainInput, operations);
        if (!recipe.extraInputs.isEmpty()) {
            extraInputHandler.use(extraInputs, extraIndexCache, operations);
        }
        if (recipe.chemicalInput!=null) {
            chemicalInputHandler.use(chemicalInput, operations);
        }
        outputHandler.handleOutput(recipe.output, operations);
    }

}
