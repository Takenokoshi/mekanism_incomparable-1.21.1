package com.takenokoshi.mekin.recipe.building;

import com.fxd927.mekanismelements.common.registries.MSGases;
import com.takenokoshi.mekin.core.MekInConstants;
import com.takenokoshi.mekin.registries.MekInChemicals;

import mekanism.api.datagen.recipe.builder.ChemicalChemicalToChemicalRecipeBuilder;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import mekanism.common.registries.MekanismChemicals;
import net.minecraft.data.recipes.RecipeOutput;

public class ChemicalInfusingRecipes {

    public static void buildRecipes(RecipeOutput output) {
        ChemicalChemicalToChemicalRecipeBuilder
                .chemicalInfusing(
                        IngredientCreatorAccess.chemicalStack().fromHolder(MSGases.POTASSIUM_HYDROXIDE, 1),
                        IngredientCreatorAccess.chemicalStack().fromHolder(MekanismChemicals.CHLORINE, 1),
                        MekInChemicals.POTASSIUM_HYPOCHLORITE.asStack(1))
                .build(output, MekInConstants.rl("chemical_infusing/potassium_hypochlorite"));
        ChemicalChemicalToChemicalRecipeBuilder
                .chemicalInfusing(
                        IngredientCreatorAccess.chemicalStack().fromHolder(MekInChemicals.POTASSIUM_HYPOCHLORITE, 1),
                        IngredientCreatorAccess.chemicalStack().fromHolder(MekanismChemicals.OXYGEN, 1),
                        MekInChemicals.POTASSIUM_CHLORATE.asStack(1))
                .build(output, MekInConstants.rl("chemical_infusing/potassium_chlorate"));
    }
}
