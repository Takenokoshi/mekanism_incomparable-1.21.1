package com.takenokoshi.mekin.recipe.building;

import java.util.function.Function;

import com.fxd927.mekanismelements.common.registries.MSGases;
import com.takenokoshi.mekin.core.MekInConstants;
import com.takenokoshi.mekin.recipe.MekInMaterial;
import com.takenokoshi.mekin.recipe.builder.FluidChemicalToBiChemicalRecipeBuilder;
import com.takenokoshi.mekin.recipe.builder.ItemStackChemicalToChemicalRecipeBuilder;
import com.takenokoshi.mekin.recipe.builder.ItemStackFluidChemicalToItemStackRecipeBuilder;
import com.takenokoshi.mekin.recipe.builder.MekInItemStackChemicalToItemStackRecipeBuilder;
import com.takenokoshi.mekin.registries.MekInChemicals;
import com.takenokoshi.mekin.registries.MekInItems;

import mekanism.api.chemical.ChemicalStack;
import mekanism.api.datagen.recipe.builder.ChemicalCrystallizerRecipeBuilder;
import mekanism.api.datagen.recipe.builder.ChemicalDissolutionRecipeBuilder;
import mekanism.api.datagen.recipe.builder.FluidChemicalToChemicalRecipeBuilder;
import mekanism.api.datagen.recipe.builder.ItemStackChemicalToItemStackRecipeBuilder;
import mekanism.api.datagen.recipe.builder.ItemStackToItemStackRecipeBuilder;
import mekanism.api.datagen.recipe.builder.NucleosynthesizingRecipeBuilder;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import mekanism.common.registries.MekanismChemicals;
import mekanism.common.tags.MekanismTags;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;

public class MaterialProcessingRecipes {
    public static void buildRecipes(RecipeOutput output,
            Function<ItemLike, Criterion<InventoryChangeTrigger.TriggerInstance>> has) {

        MekInMaterial.MATERIALS.forEach(material -> {
            String finalType = material.type() < 2
                    ? material.type() == 0 ? "ingot" : "gem"
                    : material.type() == 2 ? "gem" : "dust";
            ItemStackChemicalToChemicalRecipeBuilder
                    .aet(
                            IngredientCreatorAccess.item().from(material.rawTag(), 1),
                            IngredientCreatorAccess.chemicalStack().from(MekanismChemicals.ANTIMATTER.asStack(2)),
                            new ChemicalStack(material.plasma(), 600))
                    .build(output, MekInConstants.rl("processing/" + material.name() + "/plasma_from_raw"));
            NucleosynthesizingRecipeBuilder
                    .nucleosynthesizing(
                            IngredientCreatorAccess.item().from(MekanismTags.Items.DUSTS_OBSIDIAN, 1),
                            IngredientCreatorAccess.chemicalStack().fromHolder(material.plasma(), 100),
                            new ItemStack(material.chargedShard(), 5),
                            1, false)
                    .build(output, MekInConstants.rl("processing/" + material.name() + "/charged_shard"));
            ItemStackFluidChemicalToItemStackRecipeBuilder
                    .leaching(
                            IngredientCreatorAccess.item().from(material.chargedShard(), 1),
                            IngredientCreatorAccess.fluid().from(Tags.Fluids.LAVA, 100),
                            IngredientCreatorAccess.chemicalStack().fromHolder(MSGases.AQUA_REGIA, 100),
                            new ItemStack(material.dirtyCrystal(), 5))
                    .build(output,
                            MekInConstants.rl("processing/" + material.name() + "/dirty_crystal_from_charged_shard"));
            ItemStackFluidChemicalToItemStackRecipeBuilder
                    .leaching(
                            IngredientCreatorAccess.item().from(material.rawTag(), 1),
                            IngredientCreatorAccess.fluid().from(Tags.Fluids.LAVA, 100),
                            IngredientCreatorAccess.chemicalStack().fromHolder(MSGases.AQUA_REGIA, 100),
                            new ItemStack(material.dirtyCrystal(), 15))
                    .build(output, MekInConstants.rl("processing/" + material.name() + "/dirty_crystal_from_raw"));
            ChemicalDissolutionRecipeBuilder
                    .dissolution(
                            IngredientCreatorAccess.item().from(material.dirtyCrystal()),
                            IngredientCreatorAccess.chemicalStack().from(MekanismChemicals.SULFURIC_ACID.asStack(1)),
                            new ChemicalStack(material.dirtySlurry(), 3840),
                            true)
                    .build(output,
                            MekInConstants.rl("processing/" + material.name() + "/dirty_slurry_from_dirty_crystal"));
            ChemicalDissolutionRecipeBuilder
                    .dissolution(
                            IngredientCreatorAccess.item().from(material.rawTag()),
                            IngredientCreatorAccess.chemicalStack().from(MekanismChemicals.SULFURIC_ACID.asStack(1)),
                            new ChemicalStack(material.dirtySlurry(), 7680),
                            true)
                    .build(output, MekInConstants.rl("processing/" + material.name() + "/dirty_slurry_from_raw"));
            FluidChemicalToChemicalRecipeBuilder
                    .washing(
                            IngredientCreatorAccess.fluid().from(Tags.Fluids.WATER, 1),
                            IngredientCreatorAccess.chemicalStack().fromHolder(material.dirtySlurry(), 1),
                            new ChemicalStack(material.cleanSlurry(), 10))
                    .build(output, MekInConstants.rl("processing/" + material.name() + "/clean_slurry_washing"));
            FluidChemicalToBiChemicalRecipeBuilder
                    .chemicalExtraction(
                            IngredientCreatorAccess.fluid().from(Tags.Fluids.WATER, 1),
                            IngredientCreatorAccess.chemicalStack().fromHolder(material.dirtySlurry(), 2),
                            new ChemicalStack(material.cleanSlurry(), 20),
                            new ChemicalStack(material.secondaryChemical(), 1))
                    .build(output, MekInConstants.rl("processing/" + material.name() + "/clean_slurry_extraction"));
            ItemStackChemicalToItemStackRecipeBuilder
                    .injecting(
                            IngredientCreatorAccess.item().from(material.rawTag(), 1),
                            IngredientCreatorAccess.chemicalStack()
                                    .from(MekanismChemicals.HYDROGEN_CHLORIDE.asStack(1)),
                            new ItemStack(material.shard(), 48),
                            true)
                    .build(output, MekInConstants.rl("processing/" + material.name() + "/shard_from_raw"));
            ItemStackChemicalToItemStackRecipeBuilder
                    .purifying(
                            IngredientCreatorAccess.item().from(material.shardTag(), 1),
                            IngredientCreatorAccess.chemicalStack()
                                    .from(MekanismChemicals.OXYGEN.asStack(1)),
                            new ItemStack(material.clump(), 2),
                            true)
                    .build(output, MekInConstants.rl("processing/" + material.name() + "/clump_from_shard"));
            ItemStackChemicalToItemStackRecipeBuilder
                    .purifying(
                            IngredientCreatorAccess.item().from(material.rawTag(), 1),
                            IngredientCreatorAccess.chemicalStack()
                                    .from(MekanismChemicals.OXYGEN.asStack(1)),
                            new ItemStack(material.clump(), 24),
                            true)
                    .build(output, MekInConstants.rl("processing/" + material.name() + "/clump_from_raw"));
            ItemStackToItemStackRecipeBuilder
                    .enriching(
                            IngredientCreatorAccess.item().from(material.rawTag()),
                            new ItemStack(material.type() == 0 ? material.dust() : material.finalItem(), 6))
                    .build(output, MekInConstants.rl("processing/" + material.name() + "/" + finalType + "_from_raw"));
            MekInItemStackChemicalToItemStackRecipeBuilder
                    .refining(
                            IngredientCreatorAccess.item().from(material.crystalTag(), 16),
                            IngredientCreatorAccess.chemicalStack().fromHolder(MekInChemicals.POTASSIUM_CHLORATE, 1),
                            new ItemStack(material.finalItem(), 32), true)
                    .build(output,
                            MekInConstants.rl("processing/" + material.name() + "/" + finalType + "_from_crystal"));
        });

        // electrine
        SimpleCookingRecipeBuilder
                .smelting(Ingredient.of(MekInItems.ELECTRINE_DUST), RecipeCategory.MISC, MekInItems.ELECTRINE_INGOT,
                        5, 200)
                .unlockedBy("unlock", has.apply(MekInItems.ELECTRINE_DUST))
                .save(output, MekInConstants.rl("processing/electrine/ingot"));
        ItemStackToItemStackRecipeBuilder
                .enriching(IngredientCreatorAccess.item().from(MekInItems.ELECTRINE_DUST),
                        MekInItems.ENRICHED_ELECTRINE.asStack(1))
                .build(output, MekInConstants.rl("processing/electrine/enriched"));
        ItemStackToItemStackRecipeBuilder
                .crushing(IngredientCreatorAccess.item().from(MekInItems.ELECTRINE_INGOT),
                        MekInItems.ELECTRINE_DUST.asStack(1))
                .build(output, MekInConstants.rl("processing/electrine/dust"));
        // scarlet silver
        SimpleCookingRecipeBuilder
                .smelting(Ingredient.of(MekInItems.SCARLET_SILVER_DUST), RecipeCategory.MISC,
                        MekInItems.SCARLET_SILVER_INGOT,
                        5, 200)
                .unlockedBy("unlock", has.apply(MekInItems.SCARLET_SILVER_DUST))
                .save(output, MekInConstants.rl("processing/scarlet_silver/ingot"));
        ItemStackToItemStackRecipeBuilder
                .enriching(IngredientCreatorAccess.item().from(MekInItems.SCARLET_SILVER_DUST),
                        MekInItems.ENRICHED_SCARLET_SILVER.asStack(1))
                .build(output, MekInConstants.rl("processing/scarlet_silver/enriched"));
        ItemStackToItemStackRecipeBuilder
                .crushing(IngredientCreatorAccess.item().from(MekInItems.SCARLET_SILVER_INGOT),
                        MekInItems.SCARLET_SILVER_DUST.asStack(1))
                .build(output, MekInConstants.rl("processing/scarlet_silver/dust"));
        // silver
        SimpleCookingRecipeBuilder
                .smelting(Ingredient.of(MekInItems.SILVER_DUST), RecipeCategory.MISC,
                        MekInItems.SILVER_INGOT,
                        5, 200)
                .unlockedBy("unlock", has.apply(MekInItems.SILVER_DUST))
                .save(output, MekInConstants.rl("processing/silver/ingot"));
        ItemStackToItemStackRecipeBuilder
                .crushing(IngredientCreatorAccess.item().from(MekInItems.SILVER_INGOT),
                        MekInItems.SILVER_DUST.asStack(1))
                .build(output, MekInConstants.rl("processing/silver/dust_from_ingot"));
        ChemicalCrystallizerRecipeBuilder
                .crystallizing(
                        IngredientCreatorAccess.chemicalStack().from(MekInChemicals.SILVER.asStack(1000)),
                        MekInItems.SILVER_DUST.asStack(1))
                .build(output, MekInConstants.rl("processing/silver/dust"));
        // titanium
        SimpleCookingRecipeBuilder
                .smelting(Ingredient.of(MekInItems.TITANIUM_DUST), RecipeCategory.MISC,
                        MekInItems.TITANIUM_INGOT,
                        5, 200)
                .unlockedBy("unlock", has.apply(MekInItems.TITANIUM_DUST))
                .save(output, MekInConstants.rl("processing/titanium/ingot"));
        ItemStackToItemStackRecipeBuilder
                .crushing(IngredientCreatorAccess.item().from(MekInItems.TITANIUM_INGOT),
                        MekInItems.TITANIUM_DUST.asStack(1))
                .build(output, MekInConstants.rl("processing/titanium/dust_from_ingot"));
        ChemicalCrystallizerRecipeBuilder
                .crystallizing(
                        IngredientCreatorAccess.chemicalStack().from(MekInChemicals.TITANIUM.asStack(1000)),
                        MekInItems.TITANIUM_DUST.asStack(1))
                .build(output, MekInConstants.rl("processing/titanium/dust"));

    }
}
