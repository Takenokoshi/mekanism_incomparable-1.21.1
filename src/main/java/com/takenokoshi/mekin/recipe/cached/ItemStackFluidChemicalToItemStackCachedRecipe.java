package com.takenokoshi.mekin.recipe.cached;

import java.util.function.BooleanSupplier;

import com.takenokoshi.mekin.recipe.recipes.prefab.ItemStackFluidChemicalToItemStackRecipe;
import com.takenokoshi.mekut.recipe.cached.BasicCachedRecipe;

import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.inputs.IInputHandler;
import mekanism.api.recipes.outputs.IOutputHandler;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidStack;

public class ItemStackFluidChemicalToItemStackCachedRecipe extends BasicCachedRecipe<ItemStackFluidChemicalToItemStackRecipe> {


    private final IInputHandler<ItemStack> itemInputHandler;
    private final IInputHandler<FluidStack> fluidInputHandler;
    private final IInputHandler<ChemicalStack> chemicalInputHandler;
    private final IOutputHandler<ItemStack> outputHandler;

    private ItemStack inputItem = ItemStack.EMPTY;
    private FluidStack inputFluid = FluidStack.EMPTY;
    private ChemicalStack inputChemical = ChemicalStack.EMPTY;

    public ItemStackFluidChemicalToItemStackCachedRecipe(ItemStackFluidChemicalToItemStackRecipe recipe,
            BooleanSupplier recheckAllErrors, IInputHandler<ItemStack> itemInputHandler, IInputHandler<FluidStack> fluidInputHandler, IInputHandler<ChemicalStack> chemicalInputHandler, IOutputHandler<ItemStack> outputHandler) {
        super(recipe, recheckAllErrors);
        this.itemInputHandler = itemInputHandler;
        this.fluidInputHandler = fluidInputHandler;
        this.chemicalInputHandler = chemicalInputHandler;
        this.outputHandler = outputHandler;
    }

    @Override
    protected void calculateOperationsThisTick(OperationTracker tracker) {
        super.calculateOperationsThisTick(tracker);
        if (!tracker.shouldContinueChecking()) {
            return;
        }
        inputItem = itemInputHandler.getRecipeInput(recipe.itemInput);
        inputFluid = fluidInputHandler.getRecipeInput(recipe.fluidInput);
        inputChemical = chemicalInputHandler.getRecipeInput(recipe.chemicalInput);
        if (inputItem.isEmpty()||inputFluid.isEmpty()||inputChemical.isEmpty()) {
            tracker.mismatchedRecipe();
            return;
        }
        itemInputHandler.calculateOperationsCanSupport(tracker, inputItem);
        fluidInputHandler.calculateOperationsCanSupport(tracker, inputFluid);
        chemicalInputHandler.calculateOperationsCanSupport(tracker, inputChemical);
        outputHandler.calculateOperationsCanSupport(tracker, recipe.outputItem);
    }

    @Override
    public boolean isInputValid() {
        return recipe.test(itemInputHandler.getInput(), fluidInputHandler.getInput(), chemicalInputHandler.getInput());
    }

    @Override
    protected void finishProcessing(int operations) {
        itemInputHandler.use(inputItem, operations);
        fluidInputHandler.use(inputFluid, operations);
        chemicalInputHandler.use(inputChemical, operations);
        outputHandler.handleOutput(recipe.outputItem, operations);
    }
    
}
