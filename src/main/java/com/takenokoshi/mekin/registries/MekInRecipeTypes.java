package com.takenokoshi.mekin.registries;

import com.takenokoshi.mekaddonlib.recipe.type.MekALRecipeType;
import com.takenokoshi.mekaddonlib.registration.MekALRecipeTypeDeferredRegister;
import com.takenokoshi.mekaddonlib.registration.MekALRecipeTypeRegistryObject;
import com.takenokoshi.mekin.core.MekInConstants;
import com.takenokoshi.mekin.recipe.MekInRecipeConstants;
import com.takenokoshi.mekin.recipe.inputcache.MekInDoubleInputRecipeCache;
import com.takenokoshi.mekin.recipe.inputcache.MekInTripleInputRecipeCache;
import com.takenokoshi.mekin.recipe.recipes.prefab.FluidChemicalToBiChemicalRecipe;
import com.takenokoshi.mekin.recipe.recipes.prefab.ItemStackChemicalToChemicalRecipe;
import com.takenokoshi.mekin.recipe.recipes.prefab.ItemStackFluidChemicalToItemStackRecipe;
import com.takenokoshi.mekut.recipe.inputcache.MekUtDoubleInputRecipeCache;

import mekanism.api.recipes.ItemStackChemicalToItemStackRecipe;
import mekanism.api.recipes.vanilla_input.ReactionRecipeInput;
import mekanism.api.recipes.vanilla_input.SingleFluidChemicalRecipeInput;
import mekanism.api.recipes.vanilla_input.SingleItemChemicalRecipeInput;

public class MekInRecipeTypes {
    public static final MekALRecipeTypeDeferredRegister RECIPE_TYPES = new MekALRecipeTypeDeferredRegister(
            MekInConstants.MODID);

    public static final MekALRecipeTypeRegistryObject<SingleItemChemicalRecipeInput, ItemStackChemicalToChemicalRecipe, MekInDoubleInputRecipeCache.MekInItemChemical<ItemStackChemicalToChemicalRecipe>> AET = RECIPE_TYPES
            .registerMekAL(MekInRecipeConstants.AET,
                    id -> new MekALRecipeType<>(id, MekInDoubleInputRecipeCache.MekInItemChemical::toChemical));

    public static final MekALRecipeTypeRegistryObject<SingleFluidChemicalRecipeInput, FluidChemicalToBiChemicalRecipe, MekInDoubleInputRecipeCache.MekInFluidChemical<FluidChemicalToBiChemicalRecipe>> CHEMICAL_EXTRACTION = RECIPE_TYPES
            .registerMekAL(MekInRecipeConstants.CHEMICAL_EXTRACTION,
                    id -> new MekALRecipeType<>(id, MekInDoubleInputRecipeCache.MekInFluidChemical::toBiChemical));

    public static final MekALRecipeTypeRegistryObject<SingleItemChemicalRecipeInput, ItemStackChemicalToItemStackRecipe, MekUtDoubleInputRecipeCache.MekUtItemChemical<ItemStackChemicalToItemStackRecipe>> REFINING = RECIPE_TYPES
            .registerMekAL(MekInRecipeConstants.REFINING,
                    id -> new MekALRecipeType<>(id, MekUtDoubleInputRecipeCache.MekUtItemChemical::toItem));

    public static final MekALRecipeTypeRegistryObject<ReactionRecipeInput, ItemStackFluidChemicalToItemStackRecipe, MekInTripleInputRecipeCache.MekInItemFluidChemical<ItemStackFluidChemicalToItemStackRecipe>> LEACHING = RECIPE_TYPES
            .registerMekAL(MekInRecipeConstants.LEACHING,
                    id -> new MekALRecipeType<>(id, MekInTripleInputRecipeCache.MekInItemFluidChemical::toItem));

    public static final MekALRecipeTypeRegistryObject<SingleItemChemicalRecipeInput, ItemStackChemicalToItemStackRecipe, MekUtDoubleInputRecipeCache.MekUtItemChemical<ItemStackChemicalToItemStackRecipe>> TEPS = RECIPE_TYPES
            .registerMekAL(MekInRecipeConstants.TEPS,
                    id -> new MekALRecipeType<>(id, MekUtDoubleInputRecipeCache.MekUtItemChemical::toItem));
}
