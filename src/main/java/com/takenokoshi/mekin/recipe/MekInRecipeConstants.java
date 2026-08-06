package com.takenokoshi.mekin.recipe;

import com.takenokoshi.mekin.registries.MekInChemicals;

import mekanism.api.recipes.ingredients.ChemicalStackIngredient;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;

public class MekInRecipeConstants {
    public static final String AET = "aet";
    public static final String CHEMICAL_EXTRACTION = "chemical_extraction";
    public static final String REFINING = "refining";
    public static final String LEACHING = "leaching";
    public static final String LIGHTNING_FABRICATION = "lightning_fabrication";
    public static final String METOR_COLLECTOR = "meteor_collector";
    public static final String TEPS = "teps";

    @SuppressWarnings("unchecked")
    public static final ChemicalStackIngredient LIGHTNING_INGREDIENT = IngredientCreatorAccess.chemicalStack()
            .fromHolders(1000L, MekInChemicals.THUNDERCLOUD, MekInChemicals.HIGH_VOLTAGE_LIGHTNING);
}
