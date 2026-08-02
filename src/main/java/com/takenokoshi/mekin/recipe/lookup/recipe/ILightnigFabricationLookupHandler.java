package com.takenokoshi.mekin.recipe.lookup.recipe;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.takenokoshi.mekaddonlib.recipe.lookup.IMekALRecipeTypedLookupHandler;
import com.takenokoshi.mekin.recipe.inputcache.LightningFabricationInputRecipeCache;
import com.takenokoshi.mekin.recipe.recipes.prefab.LightningFabricationRecipe;
import com.takenokoshi.mekut.recipe.input.ItemStackListInputHandler;

import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.inputs.IInputHandler;
import net.minecraft.world.item.ItemStack;

public interface ILightnigFabricationLookupHandler
        extends IMekALRecipeTypedLookupHandler<LightningFabricationRecipe, LightningFabricationInputRecipeCache> {

    default boolean containsRecipeMain(ItemStack stack) {
        return getRecipeType().getInputCache().containsMainInput(getLevel(), stack);
    }

    default boolean containsRecipeExtra(ItemStack stack) {
        return getRecipeType().getInputCache().containsExtraInput(getLevel(), stack);
    }

    default boolean containsRecipeChemical(ChemicalStack stack) {
        return getRecipeType().getInputCache().containsChemicalInput(getLevel(), stack);
    }

    default boolean containsRecipeMainOther(ItemStack mainInput, List<ItemStack> extraInputs,
            ChemicalStack chemicalInput) {
        return getRecipeType().getInputCache().containsMainInputOther(getLevel(), mainInput, extraInputs,
                chemicalInput);
    }

    default boolean containsRecipeExtraOther(ItemStack mainInput, ItemStack extraInput,
            List<ItemStack> otherExtraInputs, ChemicalStack chemicalInput) {
        return getRecipeType().getInputCache().containsExtraInputOther(getLevel(), mainInput, extraInput,
                otherExtraInputs, chemicalInput);
    }

    default boolean containsRecipeChemicalOther(ItemStack mainInput, List<ItemStack> extraInputs,
            ChemicalStack chemicalInput) {
        return getRecipeType().getInputCache().containsChemicalInputOther(getLevel(), mainInput, extraInputs,
                chemicalInput);
    }

    default @Nullable LightningFabricationRecipe findFirstRecipe(ItemStack mainInput, List<ItemStack> extraInputs,
            ChemicalStack chemicalInput) {
        return getRecipeType().getInputCache().findFirstRecipe(getLevel(), mainInput, extraInputs, chemicalInput);
    }

    default @Nullable LightningFabricationRecipe findFirstRecipe(IInputHandler<ItemStack> mainInputHandler,
            ItemStackListInputHandler extraInputHandler, IInputHandler<ChemicalStack> chemicalInputHandler) {
        return findFirstRecipe(mainInputHandler.getInput(), extraInputHandler.getInput(),
                chemicalInputHandler.getInput());
    }

}
