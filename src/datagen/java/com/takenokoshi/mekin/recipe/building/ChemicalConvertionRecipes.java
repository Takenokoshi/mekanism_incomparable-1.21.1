package com.takenokoshi.mekin.recipe.building;

import com.extendedae_plus.init.ModItems;
import com.takenokoshi.mekin.core.MekInConstants;
import com.takenokoshi.mekin.registries.MekInChemicals;
import com.takenokoshi.mekin.registries.MekInItems;

import mekanism.api.datagen.recipe.builder.ItemStackToChemicalRecipeBuilder;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import mekanism.common.registries.MekanismItems;
import mekanism.common.resource.PrimaryResource;
import mekanism.common.resource.ResourceType;
import net.minecraft.data.recipes.RecipeOutput;

public class ChemicalConvertionRecipes {

    public static void buildRecipes(RecipeOutput output) {
        ItemStackToChemicalRecipeBuilder
                .chemicalConversion(
                        IngredientCreatorAccess.item().from(MekInItems.SCARLET_SILVER_DUST),
                        MekInChemicals.SCARLET_SILVER.asStack(10))
                .build(output, MekInConstants.rl("chemical_convertion/scarlet_silver_1"));
        ItemStackToChemicalRecipeBuilder
                .chemicalConversion(
                        IngredientCreatorAccess.item().from(MekInItems.ENRICHED_SCARLET_SILVER),
                        MekInChemicals.SCARLET_SILVER.asStack(80))
                .build(output, MekInConstants.rl("chemical_convertion/scarlet_silver_2"));
        ItemStackToChemicalRecipeBuilder
                .chemicalConversion(
                        IngredientCreatorAccess.item().from(MekInItems.ELECTRINE_DUST),
                        MekInChemicals.ELECTRINE.asStack(10))
                .build(output, MekInConstants.rl("chemical_convertion/electrine_1"));
        ItemStackToChemicalRecipeBuilder
                .chemicalConversion(
                        IngredientCreatorAccess.item().from(MekInItems.ENRICHED_ELECTRINE),
                        MekInChemicals.ELECTRINE.asStack(80))
                .build(output, MekInConstants.rl("chemical_convertion/electrine_2"));
        ItemStackToChemicalRecipeBuilder
                .chemicalConversion(
                        IngredientCreatorAccess.item().from(ModItems.OBLIVION_SINGULARITY),
                        MekInChemicals.OBLIVION_SINGULARITY.asStack(20))
                .build(output, MekInConstants.rl("chemical_convertion/oblivion_singularity"));
        ItemStackToChemicalRecipeBuilder
                .chemicalConversion(
                        IngredientCreatorAccess.item()
                                .from(MekanismItems.PROCESSED_RESOURCES.get(ResourceType.DUST, PrimaryResource.IRON)),
                        MekInChemicals.IRON.asStack(10))
                .build(output, MekInConstants.rl("chemical_convertion/iron_1"));
        ItemStackToChemicalRecipeBuilder
                .chemicalConversion(
                        IngredientCreatorAccess.item().from(MekanismItems.ENRICHED_IRON),
                        MekInChemicals.IRON.asStack(80))
                .build(output, MekInConstants.rl("chemical_convertion/iron_2"));
    }
}
