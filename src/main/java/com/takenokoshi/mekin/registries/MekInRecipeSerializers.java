package com.takenokoshi.mekin.registries;

import com.takenokoshi.mekin.core.MekInConstants;
import com.takenokoshi.mekin.recipe.MekInRecipeConstants;
import com.takenokoshi.mekin.recipe.recipes.basic.BasicAETRecipe;
import com.takenokoshi.mekin.recipe.recipes.basic.BasicChemicalExtractionRecipe;
import com.takenokoshi.mekin.recipe.recipes.basic.BasicLeachingRecipe;
import com.takenokoshi.mekin.recipe.recipes.basic.BasicRefiningRecipe;
import com.takenokoshi.mekin.recipe.recipes.basic.BasicTEPSRecipe;
import com.takenokoshi.mekin.recipe.serializer.MekInRecipeSerializerBuilder;

import mekanism.common.recipe.serializer.MekanismRecipeSerializer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MekInRecipeSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister
            .create(Registries.RECIPE_SERIALIZER, MekInConstants.MODID);

    public static final DeferredHolder<RecipeSerializer<?>, MekanismRecipeSerializer<BasicAETRecipe>> AET = RECIPE_SERIALIZERS
            .register(MekInRecipeConstants.AET,
                    () -> MekInRecipeSerializerBuilder.itemStackChemicalToChemical(BasicAETRecipe::new));

    public static final DeferredHolder<RecipeSerializer<?>, MekanismRecipeSerializer<BasicChemicalExtractionRecipe>> CHEMICAL_EXTRACTION = RECIPE_SERIALIZERS
            .register(MekInRecipeConstants.CHEMICAL_EXTRACTION,
                    () -> MekInRecipeSerializerBuilder.fluidChemicalToBiChemical(BasicChemicalExtractionRecipe::new));

    public static final DeferredHolder<RecipeSerializer<?>, MekanismRecipeSerializer<BasicRefiningRecipe>> REFINING = RECIPE_SERIALIZERS
            .register(MekInRecipeConstants.REFINING,
                    () -> MekanismRecipeSerializer.itemChemicalToItem(BasicRefiningRecipe::new));

    public static final DeferredHolder<RecipeSerializer<?>, MekanismRecipeSerializer<BasicLeachingRecipe>> LEACHING = RECIPE_SERIALIZERS
            .register(MekInRecipeConstants.LEACHING,
                    () -> MekInRecipeSerializerBuilder.itemStackFluidChemicalToItemStack(BasicLeachingRecipe::new));

    public static final DeferredHolder<RecipeSerializer<?>, MekanismRecipeSerializer<BasicTEPSRecipe>> TEPS = RECIPE_SERIALIZERS
            .register(MekInRecipeConstants.TEPS,
                    () -> MekanismRecipeSerializer.itemChemicalToItem(BasicTEPSRecipe::new));
}
