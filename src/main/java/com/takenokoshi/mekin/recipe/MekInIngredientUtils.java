package com.takenokoshi.mekin.recipe;

import com.moakiee.ae2lt.lightning.CountedIngredient;
import com.moakiee.ae2lt.machine.lightningchamber.recipe.LightningSimulationIngredient;
import com.moakiee.ae2lt.me.key.LightningKey;
import com.takenokoshi.mekin.registries.MekInChemicals;

import mekanism.api.recipes.ingredients.ChemicalStackIngredient;
import mekanism.api.recipes.ingredients.ItemStackIngredient;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;

public class MekInIngredientUtils {
    public static ItemStackIngredient convert(CountedIngredient countedIngredient) {
        return IngredientCreatorAccess.item().from(countedIngredient.ingredient(), countedIngredient.count());
    }

    public static ItemStackIngredient convert(LightningSimulationIngredient simulationIngredient) {
        return IngredientCreatorAccess.item().from(simulationIngredient.ingredient(), simulationIngredient.count());
    }

    public static ChemicalStackIngredient getLightningIngredient(LightningKey.Tier tier, int lightningCost) {
        return IngredientCreatorAccess.chemicalStack().fromHolder(tier == LightningKey.Tier.HIGH_VOLTAGE
                ? MekInChemicals.HIGH_VOLTAGE_LIGHTNING
                : MekInChemicals.EXTREME_HIGH_VOLTAGE_LIGHTNING,
                lightningCost * 1000L);
    }
}
