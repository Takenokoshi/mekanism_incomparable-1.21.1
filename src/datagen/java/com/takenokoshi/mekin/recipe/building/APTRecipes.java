package com.takenokoshi.mekin.recipe.building;

import com.jerry.mekmm.common.registries.MoreMachineChemicals;
import com.takenokoshi.mekin.core.MekInConstants;
import com.takenokoshi.mekin.recipe.builder.APTRecipeBuilder;
import com.takenokoshi.mekin.registries.MekInItems;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import mekanism.common.registries.MekanismChemicals;
import net.minecraft.data.recipes.RecipeOutput;

public class APTRecipes {
    public static void buildRecipes(RecipeOutput output) {
        new APTRecipeBuilder(
                IngredientCreatorAccess.item().from(MekInItems.SILVER_INGOT),
                IngredientCreatorAccess.chemicalStack().from(MekanismChemicals.REDSTONE.asStack(800)),
                MekInItems.SCARLET_SILVER_INGOT.asStack(1),
                false)
                .build(output, MekInConstants.rl("apt/scarlet_silver/ingot"));
        new APTRecipeBuilder(
                IngredientCreatorAccess.item().from(MekInItems.SILVER_DUST),
                IngredientCreatorAccess.chemicalStack().from(MekanismChemicals.REDSTONE.asStack(800)),
                MekInItems.SCARLET_SILVER_DUST.asStack(1),
                false)
                .build(output, MekInConstants.rl("apt/scarlet_silver/dust"));
        new APTRecipeBuilder(
                IngredientCreatorAccess.item().from(MekInItems.AMETHYST_STARLIGHT),
                IngredientCreatorAccess.chemicalStack().from(MekanismChemicals.ANTIMATTER.asStack(1000)),
                MekInItems.ANTIMATTER_SYNCHRONIZED_AMETHYST_SHARD.asStack(64),
                false)
                .build(output, MekInConstants.rl("apt/antimatter_synchronized_amethyst_shard"));
        new APTRecipeBuilder(
                IngredientCreatorAccess.item().from(MekInItems.CERTUS_QUARTZ_STARLIGHT),
                IngredientCreatorAccess.chemicalStack().from(MoreMachineChemicals.UU_MATTER.asStack(10000000L)),
                MekInItems.CESIUM_133_SYNCHRONIZED_CERTUS_QUARTZ_CRYSTAL.asStack(1),
                false)
                .build(output, MekInConstants.rl("apt/cesium_133_synchronized_certus_quartz_crystal"));
    }
}
