package com.takenokoshi.mekin.recipe.serializer;

import com.mojang.datafixers.util.Function3;
import com.mojang.datafixers.util.Function4;
import com.mojang.datafixers.util.Function6;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.takenokoshi.mekin.recipe.recipes.prefab.FluidChemicalToBiChemicalRecipe;
import com.takenokoshi.mekin.recipe.recipes.prefab.ItemStackChemicalToChemicalRecipe;
import com.takenokoshi.mekin.recipe.recipes.prefab.ItemStackFluidChemicalToItemStackRecipe;

import mekanism.api.SerializationConstants;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.ingredients.ChemicalStackIngredient;
import mekanism.api.recipes.ingredients.FluidStackIngredient;
import mekanism.api.recipes.ingredients.ItemStackIngredient;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import mekanism.common.recipe.serializer.MekanismRecipeSerializer;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;

public class MekInRecipeSerializerBuilder {

    public static <RECIPE extends FluidChemicalToBiChemicalRecipe> MekanismRecipeSerializer<RECIPE> fluidChemicalToBiChemical(
            Function4<FluidStackIngredient, ChemicalStackIngredient, ChemicalStack, ChemicalStack, RECIPE> factory) {
        return new MekanismRecipeSerializer<>(RecordCodecBuilder.mapCodec(instance -> instance.group(
                IngredientCreatorAccess.fluid().codec().fieldOf(SerializationConstants.FLUID_INPUT)
                        .forGetter(FluidChemicalToBiChemicalRecipe::getFluidInput),
                IngredientCreatorAccess.chemicalStack().codec().fieldOf(SerializationConstants.CHEMICAL_INPUT)
                        .forGetter(FluidChemicalToBiChemicalRecipe::getChemicalInput),
                ChemicalStack.CODEC.fieldOf(SerializationConstants.MAIN_OUTPUT)
                        .forGetter(FluidChemicalToBiChemicalRecipe::getMainOutputRaw),
                ChemicalStack.CODEC.fieldOf(SerializationConstants.SECONDARY_OUTPUT)
                        .forGetter(FluidChemicalToBiChemicalRecipe::getSubOutputRaw))
                .apply(instance, factory)),
                StreamCodec.composite(
                        IngredientCreatorAccess.fluid().streamCodec(),
                        FluidChemicalToBiChemicalRecipe::getFluidInput,
                        IngredientCreatorAccess.chemicalStack().streamCodec(),
                        FluidChemicalToBiChemicalRecipe::getChemicalInput,
                        ChemicalStack.STREAM_CODEC,
                        FluidChemicalToBiChemicalRecipe::getMainOutputRaw,
                        ChemicalStack.STREAM_CODEC,
                        FluidChemicalToBiChemicalRecipe::getSubOutputRaw,
                        factory));
    }

    public static <RECIPE extends ItemStackFluidChemicalToItemStackRecipe> MekanismRecipeSerializer<RECIPE> itemStackFluidChemicalToItemStack(
            Function6<ItemStackIngredient, FluidStackIngredient, ChemicalStackIngredient, Long, Integer, ItemStack, RECIPE> factory) {
        return new MekanismRecipeSerializer<>(RecordCodecBuilder.mapCodec(instance -> instance.group(
                IngredientCreatorAccess.item().codec().fieldOf(SerializationConstants.ITEM_INPUT)
                        .forGetter(ItemStackFluidChemicalToItemStackRecipe::getItemInput),
                IngredientCreatorAccess.fluid().codec().fieldOf(SerializationConstants.FLUID_INPUT)
                        .forGetter(ItemStackFluidChemicalToItemStackRecipe::getFluidInput),
                IngredientCreatorAccess.chemicalStack().codec().fieldOf(SerializationConstants.CHEMICAL_INPUT)
                        .forGetter(ItemStackFluidChemicalToItemStackRecipe::getChemicalInput),
                Codec.LONG.fieldOf(SerializationConstants.ENERGY_REQUIRED)
                        .forGetter(ItemStackFluidChemicalToItemStackRecipe::getEnergyRequired),
                Codec.INT.fieldOf(SerializationConstants.DURATION)
                        .forGetter(ItemStackFluidChemicalToItemStackRecipe::getDuration),
                ItemStack.CODEC.fieldOf(SerializationConstants.OUTPUT)
                        .forGetter(ItemStackFluidChemicalToItemStackRecipe::getOutput))
                .apply(instance, factory)),
                StreamCodec.composite(
                        IngredientCreatorAccess.item().streamCodec(),
                        ItemStackFluidChemicalToItemStackRecipe::getItemInput,
                        IngredientCreatorAccess.fluid().streamCodec(),
                        ItemStackFluidChemicalToItemStackRecipe::getFluidInput,
                        IngredientCreatorAccess.chemicalStack().streamCodec(),
                        ItemStackFluidChemicalToItemStackRecipe::getChemicalInput,
                        ByteBufCodecs.VAR_LONG, ItemStackFluidChemicalToItemStackRecipe::getEnergyRequired,
                        ByteBufCodecs.VAR_INT, ItemStackFluidChemicalToItemStackRecipe::getDuration,
                        ItemStack.STREAM_CODEC, ItemStackFluidChemicalToItemStackRecipe::getOutput,
                        factory));
    }

    public static <RECIPE extends ItemStackChemicalToChemicalRecipe> MekanismRecipeSerializer<RECIPE> itemStackChemicalToChemical(
            Function3<ItemStackIngredient, ChemicalStackIngredient, ChemicalStack, RECIPE> factory) {
        return new MekanismRecipeSerializer<>(RecordCodecBuilder
                .mapCodec(instance -> instance.group(
                        IngredientCreatorAccess.item().codec().fieldOf(SerializationConstants.ITEM_INPUT)
                                .forGetter(ItemStackChemicalToChemicalRecipe::getItemInput),
                        IngredientCreatorAccess.chemicalStack().codec().fieldOf(SerializationConstants.CHEMICAL_INPUT)
                                .forGetter(ItemStackChemicalToChemicalRecipe::getChemicalInput),
                        ChemicalStack.CODEC.fieldOf(SerializationConstants.OUTPUT)
                                .forGetter(ItemStackChemicalToChemicalRecipe::getOutputRaw))
                        .apply(instance, factory)),
                StreamCodec.composite(
                        IngredientCreatorAccess.item().streamCodec(), ItemStackChemicalToChemicalRecipe::getItemInput,
                        IngredientCreatorAccess.chemicalStack().streamCodec(),
                        ItemStackChemicalToChemicalRecipe::getChemicalInput,
                        ChemicalStack.STREAM_CODEC, ItemStackChemicalToChemicalRecipe::getOutputRaw,
                        factory));
    }
}
