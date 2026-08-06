package com.takenokoshi.mekin.recipe.lookup.recipe;

import org.jetbrains.annotations.Nullable;

import com.takenokoshi.mekaddonlib.recipe.lookup.IMekALRecipeTypedLookupHandler;
import com.takenokoshi.mekin.recipe.inputcache.MeteorCollectorInputRecipeCache;
import com.takenokoshi.mekin.recipe.recipes.prefab.MeteorCollectorRecipe;

import mekanism.api.recipes.inputs.IInputHandler;
import net.minecraft.world.item.ItemStack;

public interface IMeteorCollectorRecipeLookupHandler
        extends IMekALRecipeTypedLookupHandler<MeteorCollectorRecipe, MeteorCollectorInputRecipeCache> {

    default boolean containsInput(ItemStack input) {
        return getRecipeType().getInputCache().containsInput(getLevel(), input);
    }

    default boolean containsCatalyst(ItemStack catalyst) {
        return getRecipeType().getInputCache().containsCatalyst(getLevel(), catalyst);
    }

    default boolean containsInputCatalyst(ItemStack input, ItemStack catalyst) {
        return getRecipeType().getInputCache().containsInputCatalyst(getLevel(), input, catalyst);
    }

    default boolean containsCatalystInput(ItemStack input, ItemStack catalyst) {
        return getRecipeType().getInputCache().containsCatalystInput(getLevel(), input, catalyst);
    }

    default @Nullable MeteorCollectorRecipe findFirstRecipe(ItemStack input, ItemStack catalyst) {
        return getRecipeType().getInputCache().findFirstRecipe(getLevel(), input, catalyst);
    }

    default @Nullable MeteorCollectorRecipe findFirstRecipe(IInputHandler<ItemStack> inputHandler,
            IInputHandler<ItemStack> catalystHandler) {
        return findFirstRecipe(inputHandler.getInput(), catalystHandler.getInput());
    }
}
