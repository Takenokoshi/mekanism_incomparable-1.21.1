package com.takenokoshi.mekin.recipe.building;

import com.jerry.genextras.common.registries.GenExtraChemicals;
import com.takenokoshi.mekin.core.MekInConstants;
import com.takenokoshi.mekin.recipe.builder.FluidChemicalToBiChemicalRecipeBuilder;

import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import mekanism.common.registries.MekanismChemicals;
import net.minecraft.data.recipes.RecipeOutput;
import net.neoforged.neoforge.common.Tags;

public class ChemicalExtractionRecipes {

    public static void buildRecipes(RecipeOutput output) {
        FluidChemicalToBiChemicalRecipeBuilder
                .chemicalExtraction(
                        IngredientCreatorAccess.fluid().from(Tags.Fluids.LAVA, 1),
                        IngredientCreatorAccess.chemicalStack()
                                .fromHolder(GenExtraChemicals.POLONIUM_CONTAINING_STEAM, 10L),
                        MekanismChemicals.STEAM.asStack(10L),
                        GenExtraChemicals.POLONIUM208.asStack(1L))
                .build(output, MekInConstants.rl("chemical_extraction/polonium-208"));
    }
}
