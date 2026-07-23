package com.takenokoshi.mekin.recipe.building;

import com.jerry.genextras.common.registries.GenExtraChemicals;
import com.takenokoshi.mekin.core.MekInConstants;
import com.takenokoshi.mekin.recipe.builder.ChemixerRecipeBuilder;
import com.takenokoshi.mekin.registries.MekInItems;

import fr.iglee42.evolvedmekanism.registries.EMItems;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import mekanism.common.registries.MekanismChemicals;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;
import net.pedroksl.advanced_ae.common.definitions.AAEItems;

public class ChemixerRecipes {

    public static void buildRecipes(RecipeOutput output) {
        new ChemixerRecipeBuilder(
                IngredientCreatorAccess.item().from(MekInItems.TITANIUM_INGOT, 4),
                IngredientCreatorAccess.item().from(AAEItems.QUANTUM_INFUSED_DUST, 8),
                IngredientCreatorAccess.chemicalStack().from(GenExtraChemicals.POLONIUM208.asStack(2000)),
                MekInItems.METASTABLE_ALLOY_INGOT.asStack(1))
                .build(output, MekInConstants.rl("chemixer/metastable_alloy_ingot"));
        new ChemixerRecipeBuilder(
                IngredientCreatorAccess.item().from(EMItems.BETTER_GOLD_INGOT, 64),
                IngredientCreatorAccess.item().from(MekInItems.SCARLET_SILVER_INGOT, 64),
                IngredientCreatorAccess.chemicalStack().from(MekanismChemicals.ANTIMATTER.asStack(20000)),
                MekInItems.ELECTRINE_INGOT.asStack(1))
                .build(output, MekInConstants.rl("chemixer/electrine_ingot"));
        new ChemixerRecipeBuilder(
                IngredientCreatorAccess.item().from(EMItems.BETTER_GOLD_DUST, 64),
                IngredientCreatorAccess.item().from(MekInItems.SCARLET_SILVER_DUST, 64),
                IngredientCreatorAccess.chemicalStack().from(MekanismChemicals.ANTIMATTER.asStack(20000)),
                MekInItems.ELECTRINE_DUST.asStack(1))
                .build(output, MekInConstants.rl("chemixer/electrine_dust"));
        new ChemixerRecipeBuilder(
                IngredientCreatorAccess.item().from(MekInItems.ANTIMATTER_SYNCHRONIZED_AMETHYST_SHARD, 1),
                IngredientCreatorAccess.item().from(MekInItems.METASTABLE_ALLOY_INGOT, 256),
                IngredientCreatorAccess.chemicalStack().from(MekanismChemicals.ANTIMATTER.asStack(20000)),
                MekInItems.AMETHYST_SYNCHRONIZED_METASTABLE_ALLOY_INGOT.asStack(1))
                .build(output, MekInConstants.rl("chemixer/amethyst_synchronized_metastable_alloy_ingot"));
        new ChemixerRecipeBuilder(
                IngredientCreatorAccess.item().from(MekInItems.NEUTRONIUM_INGOT, 1024),
                IngredientCreatorAccess.item().from(Items.GRASS_BLOCK, 256),
                IngredientCreatorAccess.chemicalStack().from(MekanismChemicals.ANTIMATTER.asStack(20000)),
                MekInItems.NATURAL_ENVIRONMENT_SYNCHRONIZED_NEUTRONIUM_INGOT.asStack(1))
                .build(output, MekInConstants.rl("chemixer/natural_environment_synchronized_neutronium_ingot"));
    }
}
