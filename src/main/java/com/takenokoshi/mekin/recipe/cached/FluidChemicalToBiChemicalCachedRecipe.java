package com.takenokoshi.mekin.recipe.cached;

import java.util.function.BooleanSupplier;

import com.takenokoshi.mekin.recipe.recipes.prefab.FluidChemicalToBiChemicalRecipe;
import com.takenokoshi.mekut.recipe.cached.BasicCachedRecipe;

import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.inputs.IInputHandler;
import mekanism.api.recipes.outputs.IOutputHandler;
import net.neoforged.neoforge.fluids.FluidStack;

public class FluidChemicalToBiChemicalCachedRecipe extends BasicCachedRecipe<FluidChemicalToBiChemicalRecipe> {

    private final IInputHandler<FluidStack> fluidInputHandler;
    private final IInputHandler<ChemicalStack> chemicalInputHandler;
    private final IOutputHandler<ChemicalStack> mainOutputHandler;
    private final IOutputHandler<ChemicalStack> subOutputHandler;

    private FluidStack fluidRecipeInput = FluidStack.EMPTY;
    private ChemicalStack chemicalRecipeInput = ChemicalStack.EMPTY;

    public FluidChemicalToBiChemicalCachedRecipe(FluidChemicalToBiChemicalRecipe recipe,
            BooleanSupplier recheckAllErrors,
            IInputHandler<FluidStack> fluidInputHandler,
            IInputHandler<ChemicalStack> chemicalInputHandler,
            IOutputHandler<ChemicalStack> mainOutputHandler,
            IOutputHandler<ChemicalStack> subOutputHandler) {
        super(recipe, recheckAllErrors);
        this.fluidInputHandler = fluidInputHandler;
        this.chemicalInputHandler = chemicalInputHandler;
        this.mainOutputHandler = mainOutputHandler;
        this.subOutputHandler = subOutputHandler;
    }

    @Override
    protected void calculateOperationsThisTick(OperationTracker tracker) {
        super.calculateOperationsThisTick(tracker);
        if (!tracker.shouldContinueChecking()) {
            return;
        }
        fluidRecipeInput = fluidInputHandler.getRecipeInput(recipe.fluidInput);
        chemicalRecipeInput = chemicalInputHandler.getRecipeInput(recipe.chemicalInput);
        if (fluidRecipeInput.isEmpty()||chemicalRecipeInput.isEmpty()) {
            tracker.mismatchedRecipe();
            return;
        }
        fluidInputHandler.calculateOperationsCanSupport(tracker, fluidRecipeInput);
        chemicalInputHandler.calculateOperationsCanSupport(tracker, chemicalRecipeInput);
        mainOutputHandler.calculateOperationsCanSupport(tracker, recipe.mainOutput);
        subOutputHandler.calculateOperationsCanSupport(tracker, recipe.subOutput);
    }

    @Override
    public boolean isInputValid() {
        return recipe.test(fluidInputHandler.getInput(), chemicalInputHandler.getInput());
    }

    @Override
    protected void finishProcessing(int operations) {
        fluidInputHandler.use(fluidRecipeInput, operations);
        chemicalInputHandler.use(chemicalRecipeInput, operations);
        mainOutputHandler.handleOutput(recipe.mainOutput, operations);
        subOutputHandler.handleOutput(recipe.subOutput, operations);
    }

}
