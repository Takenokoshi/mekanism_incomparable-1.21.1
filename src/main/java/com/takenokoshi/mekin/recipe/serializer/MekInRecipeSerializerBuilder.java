package com.takenokoshi.mekin.recipe.serializer;

import java.util.List;
import java.util.Optional;

import com.mojang.datafixers.util.Function3;
import com.mojang.datafixers.util.Function4;
import com.mojang.datafixers.util.Function5;
import com.mojang.datafixers.util.Function6;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.takenokoshi.mekin.recipe.output.MeteorCollectorRecipeOutput;
import com.takenokoshi.mekin.recipe.recipes.prefab.FluidChemicalToBiChemicalRecipe;
import com.takenokoshi.mekin.recipe.recipes.prefab.ItemStackChemicalToChemicalRecipe;
import com.takenokoshi.mekin.recipe.recipes.prefab.ItemStackFluidChemicalToItemStackRecipe;
import com.takenokoshi.mekin.recipe.recipes.prefab.LightningFabricationRecipe;
import com.takenokoshi.mekin.recipe.recipes.prefab.MeteorCollectorRecipe;
import com.takenokoshi.mekut.recipe.serializer.MekUtCodecConstants;

import mekanism.api.SerializationConstants;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.ingredients.ChemicalStackIngredient;
import mekanism.api.recipes.ingredients.FluidStackIngredient;
import mekanism.api.recipes.ingredients.ItemStackIngredient;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import mekanism.common.recipe.serializer.MekanismRecipeSerializer;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

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

    public static <RECIPE extends LightningFabricationRecipe> MekanismRecipeSerializer<RECIPE> lightningFabrication(
            Function6<ItemStackIngredient, List<ItemStackIngredient>, Optional<ChemicalStackIngredient>, ItemStack, Long, Integer, RECIPE> factory) {
        return new MekanismRecipeSerializer<>(RecordCodecBuilder
                .mapCodec(instance -> instance.group(
                        IngredientCreatorAccess.item().codec().fieldOf(SerializationConstants.MAIN_INPUT)
                                .forGetter(LightningFabricationRecipe::getMainInput),
                        IngredientCreatorAccess.item().codec().listOf().fieldOf(SerializationConstants.EXTRA_INPUT)
                                .forGetter(LightningFabricationRecipe::getExtraInputs),
                        IngredientCreatorAccess.chemicalStack().codec()
                                .optionalFieldOf(SerializationConstants.CHEMICAL_INPUT)
                                .forGetter(LightningFabricationRecipe::getChemicalInputAsOptional),
                        ItemStack.CODEC.fieldOf(SerializationConstants.OUTPUT)
                                .forGetter(LightningFabricationRecipe::getOutput),
                        Codec.LONG.fieldOf(SerializationConstants.ENERGY_REQUIRED)
                                .forGetter(LightningFabricationRecipe::getEnergyRequired),
                        Codec.INT.fieldOf(SerializationConstants.DURATION)
                                .forGetter(LightningFabricationRecipe::getDuration))
                        .apply(instance, factory)),
                StreamCodec.composite(
                        IngredientCreatorAccess.item().streamCodec(),
                        LightningFabricationRecipe::getMainInput,
                        MekUtCodecConstants.ITEMSTACK_INGREDIENT_LIST_STREAM_CODEC,
                        LightningFabricationRecipe::getExtraInputs,
                        MekUtCodecConstants.CHEMICALSTACK_INGREDIENT_OPTIONAL_STREAM_CODEC,
                        LightningFabricationRecipe::getChemicalInputAsOptional,
                        ItemStack.STREAM_CODEC,
                        LightningFabricationRecipe::getOutput,
                        ByteBufCodecs.VAR_LONG,
                        LightningFabricationRecipe::getEnergyRequired,
                        ByteBufCodecs.VAR_INT,
                        LightningFabricationRecipe::getDuration,
                        factory));
    }

    public static <RECIPE extends MeteorCollectorRecipe> MekanismRecipeSerializer<RECIPE> MeteorCollector(
            Function5<ItemStackIngredient, ItemStackIngredient, ResourceKey<Level>, List<MeteorCollectorRecipeOutput>, Boolean, RECIPE> factory) {
        return new MekanismRecipeSerializer<>(RecordCodecBuilder
                .mapCodec(instance -> instance.group(
                        IngredientCreatorAccess.item().codec().fieldOf(SerializationConstants.INPUT)
                                .forGetter(MeteorCollectorRecipe::getInput),
                        IngredientCreatorAccess.item().codec().fieldOf("catalyst")
                                .forGetter(MeteorCollectorRecipe::getCatalyst),
                        ResourceKey.codec(Registries.DIMENSION).fieldOf("dimension")
                                .forGetter(MeteorCollectorRecipe::getDimension),
                        MeteorCollectorRecipeOutput.MAP_CODEC.codec().listOf().fieldOf("outputs")
                                .forGetter(MeteorCollectorRecipe::getOutputs),
                        Codec.BOOL.fieldOf("require_advanced").forGetter(MeteorCollectorRecipe::getRequireAdvanced))
                        .apply(instance, factory)),
                StreamCodec.composite(
                        IngredientCreatorAccess.item().streamCodec(),
                        MeteorCollectorRecipe::getInput,
                        IngredientCreatorAccess.item().streamCodec(),
                        MeteorCollectorRecipe::getCatalyst,
                        ResourceKey.streamCodec(Registries.DIMENSION),
                        MeteorCollectorRecipe::getDimension,
                        MeteorCollectorRecipeOutput.STREAM_CODEC.apply(ByteBufCodecs.list()),
                        MeteorCollectorRecipe::getOutputs,
                        ByteBufCodecs.BOOL,
                        MeteorCollectorRecipe::getRequireAdvanced,
                        factory));
    }
}
