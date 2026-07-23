package com.takenokoshi.mekin.recipe.cached;

import java.util.function.BooleanSupplier;

import com.takenokoshi.mekin.recipe.recipes.prefab.ItemStackChemicalToChemicalRecipe;
import com.takenokoshi.mekut.recipe.cached.BasicCachedRecipe;

import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.inputs.IInputHandler;
import mekanism.api.recipes.outputs.IOutputHandler;
import net.minecraft.world.item.ItemStack;

public class ItemStackChemicalToChemicalCachedRecipe extends BasicCachedRecipe<ItemStackChemicalToChemicalRecipe> {


    private final IInputHandler<ItemStack> itemInputHandler;
    private final IInputHandler<ChemicalStack> chemicalInputHandler;
    private final IOutputHandler<ChemicalStack> outputHandler;

    private ItemStack itemInput = ItemStack.EMPTY;
    private ChemicalStack chemicalInput = ChemicalStack.EMPTY;

    public ItemStackChemicalToChemicalCachedRecipe(ItemStackChemicalToChemicalRecipe recipe,
            BooleanSupplier recheckAllErrors, IInputHandler<ItemStack> itemInputHandler, IInputHandler<ChemicalStack> chemicalInputHandler, IOutputHandler<ChemicalStack> outputHandler) {
        super(recipe, recheckAllErrors);
        this.itemInputHandler = itemInputHandler;
        this.chemicalInputHandler = chemicalInputHandler;
        this.outputHandler = outputHandler;
    }

    @Override
    protected void calculateOperationsThisTick(OperationTracker tracker) {
        super.calculateOperationsThisTick(tracker);
        if (!tracker.shouldContinueChecking()) {
            return;
        }
        itemInput = itemInputHandler.getRecipeInput(recipe.itemInput);
        chemicalInput = chemicalInputHandler.getRecipeInput(recipe.chemicalInput);
        if (itemInput.isEmpty()||chemicalInput.isEmpty()) {
            tracker.mismatchedRecipe();
            return;
        }
        itemInputHandler.calculateOperationsCanSupport(tracker, itemInput);
        chemicalInputHandler.calculateOperationsCanSupport(tracker, chemicalInput);
        outputHandler.calculateOperationsCanSupport(tracker, recipe.output);
    }

    @Override
    public boolean isInputValid() {
        return recipe.test(itemInputHandler.getInput(), chemicalInputHandler.getInput());
    }

    @Override
    protected void finishProcessing(int operations) {
        itemInputHandler.use(itemInput, operations);
        chemicalInputHandler.use(chemicalInput, operations);
        outputHandler.handleOutput(recipe.output, operations);
    }
    
}
