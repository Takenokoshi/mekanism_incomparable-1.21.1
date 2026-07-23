package com.takenokoshi.mekin.recipe.building;

import com.extendedae_plus.init.ModItems;
import com.takenokoshi.mekin.core.MekInConstants;
import com.takenokoshi.mekin.recipe.builder.ItemStackChemicalToChemicalRecipeBuilder;
import com.takenokoshi.mekin.registries.MekInChemicals;

import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import mekanism.common.registries.MekanismChemicals;
import net.minecraft.data.recipes.RecipeOutput;

public class AETRecipes {

    public static void buildRecipes(RecipeOutput output) {
        ItemStackChemicalToChemicalRecipeBuilder
                .aet(
                        IngredientCreatorAccess.item().from(ModItems.OBLIVION_SINGULARITY, 1),
                        IngredientCreatorAccess.chemicalStack().from(MekanismChemicals.ANTIMATTER.asStack(1000)),
                        MekInChemicals.NULL.asStack(1))
                .build(output, MekInConstants.rl("aet/null"));
    }
}
