package com.takenokoshi.mekin.recipe.lookup.recipe;

import java.util.function.BiPredicate;

import org.jetbrains.annotations.Nullable;

import com.takenokoshi.mekaddonlib.recipe.lookup.IMekALRecipeTypedLookupHandler;
import com.takenokoshi.mekin.recipe.inputcache.MekInDoubleInputRecipeCache;
import com.takenokoshi.mekut.recipe.inputcache.MekUtDoubleInputRecipeCache;

import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.MekanismRecipe;
import mekanism.api.recipes.inputs.IInputHandler;
import mekanism.common.recipe.lookup.cache.DoubleInputRecipeCache;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidStack;

public interface IMekInDoubleRecipeLookupHandler<INPUT_A, INPUT_B, RECIPE extends MekanismRecipe<?> & BiPredicate<INPUT_A, INPUT_B>, INPUT_CACHE extends MekUtDoubleInputRecipeCache<INPUT_A, ?, INPUT_B, ?, RECIPE, ?, ?>>
        extends IMekALRecipeTypedLookupHandler<RECIPE, INPUT_CACHE> {

    /**
     * Checks if there is a matching recipe of type {@link #getRecipeType()} that
     * has the given inputs.
     *
     * @param inputA Recipe input a.
     * @param inputB Recipe input b.
     *
     * @return {@code true} if there is a match, {@code false} if there isn't.
     *
     * @apiNote See
     *          {@link DoubleInputRecipeCache#containsInputAB(Level, Object, Object)}
     *          and
     *          {@link DoubleInputRecipeCache#containsInputBA(Level, Object, Object)}
     *          for
     *          more details about when this method should be called versus when
     *          {@link #containsRecipeBA(Object, Object)} should be called.
     */
    default boolean containsRecipeAB(INPUT_A inputA, INPUT_B inputB) {
        return getRecipeType().getInputCache().containsInputAB(getLevel(), inputA, inputB);
    }

    /**
     * Checks if there is a matching recipe of type {@link #getRecipeType()} that
     * has the given inputs.
     *
     * @param inputA Recipe input a.
     * @param inputB Recipe input b.
     *
     * @return {@code true} if there is a match, {@code false} if there isn't.
     *
     * @apiNote See
     *          {@link DoubleInputRecipeCache#containsInputAB(Level, Object, Object)}
     *          and
     *          {@link DoubleInputRecipeCache#containsInputBA(Level, Object, Object)}
     *          for
     *          more details about when this method should be called versus when
     *          {@link #containsRecipeAB(Object, Object)} should be called.
     */
    default boolean containsRecipeBA(INPUT_A inputA, INPUT_B inputB) {
        return getRecipeType().getInputCache().containsInputBA(getLevel(), inputA, inputB);
    }

    /**
     * Checks if there is a matching recipe of type {@link #getRecipeType()} that
     * has the given input.
     *
     * @param input Recipe input.
     *
     * @return {@code true} if there is a match, {@code false} if there isn't.
     */
    default boolean containsRecipeA(INPUT_A input) {
        return getRecipeType().getInputCache().containsInputA(getLevel(), input);
    }

    /**
     * Checks if there is a matching recipe of type {@link #getRecipeType()} that
     * has the given input.
     *
     * @param input Recipe input.
     *
     * @return {@code true} if there is a match, {@code false} if there isn't.
     */
    default boolean containsRecipeB(INPUT_B input) {
        return getRecipeType().getInputCache().containsInputB(getLevel(), input);
    }

    /**
     * Finds the first recipe for the type of recipe we handle
     * ({@link #getRecipeType()}) by looking up the given inputs against the recipe
     * type's input cache.
     *
     * @param inputA Recipe input a.
     * @param inputB Recipe input b.
     *
     * @return Recipe matching the given inputs, or {@code null} if no recipe
     *         matches.
     */
    @Nullable
    default RECIPE findFirstRecipe(INPUT_A inputA, INPUT_B inputB) {
        return getRecipeType().getInputCache().findFirstRecipe(getLevel(), inputA, inputB);
    }

    /**
     * Finds the first recipe for the type of recipe we handle
     * ({@link #getRecipeType()}) by looking up the given inputs against the recipe
     * type's input cache.
     *
     * @param inputAHandler Input handler to grab the first recipe input from.
     * @param inputBHandler Input handler to grab the second recipe input from.
     *
     * @return Recipe matching the given inputs, or {@code null} if no recipe
     *         matches.
     */
    @Nullable
    default RECIPE findFirstRecipe(IInputHandler<INPUT_A> inputAHandler, IInputHandler<INPUT_B> inputBHandler) {
        return findFirstRecipe(inputAHandler.getInput(), inputBHandler.getInput());
    }

    static interface MekInFluidChemicalRecipeLookupHandler<RECIPE extends MekanismRecipe<?> & BiPredicate<FluidStack, ChemicalStack>>
            extends
            IMekInDoubleRecipeLookupHandler<FluidStack, ChemicalStack, RECIPE, MekInDoubleInputRecipeCache.MekInFluidChemical<RECIPE>> {
    }

    static interface MekInItemChemicalRecipeLoolupHandler<RECIPE extends MekanismRecipe<?> & BiPredicate<ItemStack, ChemicalStack>>
            extends
            IMekInDoubleRecipeLookupHandler<ItemStack, ChemicalStack, RECIPE, MekInDoubleInputRecipeCache.MekInItemChemical<RECIPE>> {
    }
}
