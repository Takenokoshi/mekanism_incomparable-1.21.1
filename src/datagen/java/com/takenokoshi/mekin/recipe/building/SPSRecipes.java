package com.takenokoshi.mekin.recipe.building;

import com.jerry.mekextras.common.registries.ExtraChemicals;
import com.takenokoshi.mekin.core.MekInConstants;
import com.takenokoshi.mekin.registries.MekInChemicals;
import com.takenokoshi.mekut.enums.MUMaterial;
import com.takenokoshi.mekut.recipe.builder.MekUtChemicalToChemicalRecipeBuilder;
import com.takenokoshi.mekut.registries.MekUtChemicals;

import fr.iglee42.evolvedmekanism.registries.EMChemicals;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import mekanism.common.registries.MekanismChemicals;
import mekanism.common.resource.PrimaryResource;
import net.minecraft.data.recipes.RecipeOutput;

public class SPSRecipes {
    public static void buildRecipes(RecipeOutput output) {
        MekUtChemicalToChemicalRecipeBuilder
                .sps(IngredientCreatorAccess.chemicalStack().fromHolder(
                        MekanismChemicals.PROCESSED_RESOURCES.get(PrimaryResource.IRON).getCleanSlurry(), 2000),
                        MekInChemicals.IRON.asStack(1600))
                .build(output, MekInConstants.rl("sps/iron"));
        MekUtChemicalToChemicalRecipeBuilder
                .sps(IngredientCreatorAccess.chemicalStack().fromHolder(
                        MekanismChemicals.PROCESSED_RESOURCES.get(PrimaryResource.COPPER).getCleanSlurry(), 2000),
                        MekInChemicals.COPPER.asStack(1600))
                .build(output, MekInConstants.rl("sps/copper"));
        MekUtChemicalToChemicalRecipeBuilder
                .sps(IngredientCreatorAccess.chemicalStack().fromHolder(
                        ExtraChemicals.DIRTY_AND_CLEAN_SLURRIES_NAQUADAH.getCleanSlurry(), 2000),
                        MekInChemicals.NAQUADAH.asStack(1600))
                .build(output, MekInConstants.rl("sps/naquadah"));
        MekUtChemicalToChemicalRecipeBuilder
                .sps(IngredientCreatorAccess.chemicalStack().fromHolder(
                        MekanismChemicals.PROCESSED_RESOURCES.get(PrimaryResource.URANIUM).getCleanSlurry(), 2000),
                        EMChemicals.URANIUM.asStack(1600))
                .build(output, MekInConstants.rl("sps/uranium"));
        MekUtChemicalToChemicalRecipeBuilder
                .sps(IngredientCreatorAccess.chemicalStack().fromHolder(
                        MekanismChemicals.PROCESSED_RESOURCES.get(PrimaryResource.LEAD).getCleanSlurry(), 2000),
                        ExtraChemicals.LEAD.asStack(1600))
                .build(output, MekInConstants.rl("sps/lead"));
        MekUtChemicalToChemicalRecipeBuilder
                .sps(IngredientCreatorAccess.chemicalStack().fromHolder(
                        MekanismChemicals.PROCESSED_RESOURCES.get(PrimaryResource.OSMIUM).getCleanSlurry(), 100),
                        MekanismChemicals.OSMIUM.asStack(1600))
                .build(output, MekInConstants.rl("sps/osmium"));
        MekUtChemicalToChemicalRecipeBuilder
                .sps(IngredientCreatorAccess.chemicalStack().fromHolder(
                        MekanismChemicals.PROCESSED_RESOURCES.get(PrimaryResource.GOLD).getCleanSlurry(), 2000),
                        MekanismChemicals.GOLD.asStack(1600))
                .build(output, MekInConstants.rl("sps/gold"));
        MekUtChemicalToChemicalRecipeBuilder
                .sps(IngredientCreatorAccess.chemicalStack().fromHolder(
                        MekanismChemicals.PROCESSED_RESOURCES.get(PrimaryResource.TIN).getCleanSlurry(), 2000),
                        MekanismChemicals.TIN.asStack(1600))
                .build(output, MekInConstants.rl("sps/tin"));
        MekUtChemicalToChemicalRecipeBuilder
                .sps(IngredientCreatorAccess.chemicalStack().fromHolder(
                        MekUtChemicals.MU_MATERIALS_CLEAN_SLURRY.get(MUMaterial.AMETHYST), 2000),
                        MekInChemicals.AMETHYST.asStack(1600))
                .build(output, MekInConstants.rl("sps/amethyst"));
        MekUtChemicalToChemicalRecipeBuilder
                .sps(IngredientCreatorAccess.chemicalStack().fromHolder(
                        MekUtChemicals.MU_MATERIALS_CLEAN_SLURRY.get(MUMaterial.CERTUS_QUARTZ), 2000),
                        MekInChemicals.CERTUS_QUARTZ.asStack(1600))
                .build(output, MekInConstants.rl("sps/certus_quartz"));
        MekUtChemicalToChemicalRecipeBuilder
                .sps(IngredientCreatorAccess.chemicalStack().fromHolder(
                        MekUtChemicals.MU_MATERIALS_CLEAN_SLURRY.get(MUMaterial.COAL), 2000),
                        MekInChemicals.COAL.asStack(1600))
                .build(output, MekInConstants.rl("sps/coal"));
        MekUtChemicalToChemicalRecipeBuilder
                .sps(IngredientCreatorAccess.chemicalStack().fromHolder(
                        MekUtChemicals.MU_MATERIALS_CLEAN_SLURRY.get(MUMaterial.DIAMOND), 2000),
                        MekanismChemicals.DIAMOND.asStack(1600))
                .build(output, MekInConstants.rl("sps/diamond"));
        MekUtChemicalToChemicalRecipeBuilder
                .sps(IngredientCreatorAccess.chemicalStack().fromHolder(
                        MekUtChemicals.MU_MATERIALS_CLEAN_SLURRY.get(MUMaterial.EMERALD), 2000),
                        MekInChemicals.EMERALD.asStack(1600))
                .build(output, MekInConstants.rl("sps/emerald"));
        MekUtChemicalToChemicalRecipeBuilder
                .sps(IngredientCreatorAccess.chemicalStack().fromHolder(
                        MekUtChemicals.MU_MATERIALS_CLEAN_SLURRY.get(MUMaterial.ENTRO), 2000),
                        MekInChemicals.ENTRO.asStack(1600))
                .build(output, MekInConstants.rl("sps/entro"));
        MekUtChemicalToChemicalRecipeBuilder
                .sps(IngredientCreatorAccess.chemicalStack().fromHolder(
                        MekUtChemicals.MU_MATERIALS_CLEAN_SLURRY.get(MUMaterial.FLUORITE), 2000),
                        MekInChemicals.FLUORITE.asStack(1600))
                .build(output, MekInConstants.rl("sps/fluorite"));
        MekUtChemicalToChemicalRecipeBuilder
                .sps(IngredientCreatorAccess.chemicalStack().fromHolder(
                        MekUtChemicals.MU_MATERIALS_CLEAN_SLURRY.get(MUMaterial.LAPIS_LAZULI), 2000),
                        MekInChemicals.LAPIS_LAZULI.asStack(1600))
                .build(output, MekInConstants.rl("sps/lapis_lazuli"));
        MekUtChemicalToChemicalRecipeBuilder
                .sps(IngredientCreatorAccess.chemicalStack().fromHolder(
                        MekUtChemicals.MU_MATERIALS_CLEAN_SLURRY.get(MUMaterial.NETHERITE), 200),
                        MekUtChemicals.NETHERITE.asStack(2000))
                .build(output, MekInConstants.rl("sps/netherite"));
        MekUtChemicalToChemicalRecipeBuilder
                .sps(IngredientCreatorAccess.chemicalStack().fromHolder(
                        MekUtChemicals.MU_MATERIALS_CLEAN_SLURRY.get(MUMaterial.QUARTZ), 2000),
                        MekInChemicals.QUARTZ.asStack(1600))
                .build(output, MekInConstants.rl("sps/quartz"));
        MekUtChemicalToChemicalRecipeBuilder
                .sps(IngredientCreatorAccess.chemicalStack().fromHolder(
                        MekUtChemicals.MU_MATERIALS_CLEAN_SLURRY.get(MUMaterial.REDSTONE), 2000),
                        MekanismChemicals.REDSTONE.asStack(1600))
                .build(output, MekInConstants.rl("sps/redstone"));
    }
}
