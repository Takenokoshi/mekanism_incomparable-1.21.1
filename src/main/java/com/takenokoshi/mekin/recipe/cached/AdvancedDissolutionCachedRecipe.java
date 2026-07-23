package com.takenokoshi.mekin.recipe.cached;

import java.util.function.BooleanSupplier;
import java.util.function.IntSupplier;
import java.util.function.LongUnaryOperator;

import com.takenokoshi.mekut.recipe.cached.BasicCachedRecipe;

import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.ChemicalDissolutionRecipe;
import mekanism.api.recipes.inputs.IInputHandler;
import mekanism.api.recipes.outputs.IOutputHandler;
import net.minecraft.world.item.ItemStack;

public class AdvancedDissolutionCachedRecipe extends BasicCachedRecipe<ChemicalDissolutionRecipe> {

    private final IInputHandler<ItemStack> itemInputHandler;
    private final IInputHandler<ChemicalStack> chemicalInputHandler;
    private final IOutputHandler<ChemicalStack> outputHandler;
    private final LongUnaryOperator chemicalUsageModifier;

    private IntSupplier requiredTicks;
    private long perTickUsage;
    private long lastTickUsage;
    private ItemStack inputItem = ItemStack.EMPTY;
    private ChemicalStack inputChemical = ChemicalStack.EMPTY;
    private ChemicalStack output = ChemicalStack.EMPTY;

    public AdvancedDissolutionCachedRecipe(ChemicalDissolutionRecipe recipe, BooleanSupplier recheckAllErrors,
            IInputHandler<ItemStack> itemInputHandler,
            IInputHandler<ChemicalStack> chemicalInputHandler,
            IOutputHandler<ChemicalStack> outputHandler, LongUnaryOperator chemicalUsageModifier) {
        super(recipe, recheckAllErrors);
        this.itemInputHandler = itemInputHandler;
        this.chemicalInputHandler = chemicalInputHandler;
        this.outputHandler = outputHandler;
        this.chemicalUsageModifier = chemicalUsageModifier;
        this.requiredTicks = () -> 1;
    }

    @Override
    protected void calculateOperationsThisTick(OperationTracker tracker) {
        super.calculateOperationsThisTick(tracker);
        if (!tracker.shouldContinueChecking()) {
            return;
        }
        inputChemical = chemicalInputHandler.getRecipeInput(recipe.getChemicalInput());
        inputItem = itemInputHandler.getRecipeInput(recipe.getItemInput());
        if (inputChemical.isEmpty() || inputItem.isEmpty()) {
            tracker.mismatchedRecipe();
            return;
        }
        if (recipe.perTickUsage()) {
            int ticksRequired = requiredTicks.getAsInt();
            long totalUsage = chemicalUsageModifier.applyAsLong(recipe.getChemicalInput().amount() * 100);
            perTickUsage = totalUsage / ticksRequired;
            lastTickUsage = perTickUsage + totalUsage % ticksRequired;
        } else {
            perTickUsage = 0;
            lastTickUsage = recipe.getChemicalInput().amount();
        }
        chemicalInputHandler.calculateOperationsCanSupport(tracker, inputChemical.copyWithAmount(lastTickUsage));
        itemInputHandler.calculateOperationsCanSupport(tracker, inputItem);
        output = recipe.getOutput(inputItem, inputChemical);
        outputHandler.calculateOperationsCanSupport(tracker, output);
    }

    @Override
    public boolean isInputValid() {
        return recipe.test(itemInputHandler.getInput(), chemicalInputHandler.getInput());
    }

    @Override
    protected void useResources(int operations) {
        chemicalInputHandler.use(inputChemical.copyWithAmount(perTickUsage), operations);
    }

    @Override
    protected void finishProcessing(int operations) {
        itemInputHandler.use(inputItem, operations);
        chemicalInputHandler.use(inputChemical.copyWithAmount(lastTickUsage), operations);
        outputHandler.handleOutput(output, operations);
    }

    public BasicCachedRecipe<ChemicalDissolutionRecipe> setRequiredTicks(IntSupplier requiredTicks) {
        super.setRequiredTicks(requiredTicks);
        this.requiredTicks = requiredTicks;
        return this;
    }

}
