package com.takenokoshi.mekin.recipe.recipes.basic;

import java.util.List;
import java.util.Optional;

import com.moakiee.ae2lt.machine.lightningchamber.recipe.LightningSimulationIngredient;
import com.moakiee.ae2lt.machine.lightningchamber.recipe.LightningSimulationRecipe;
import com.takenokoshi.mekin.recipe.MekInIngredientUtils;
import com.takenokoshi.mekin.recipe.recipes.prefab.LightningFabricationRecipe;
import com.takenokoshi.mekin.registries.MekInRecipeSerializers;
import com.takenokoshi.mekin.registries.MekInRecipeTypes;

import fr.iglee42.evolvedmekanism.recipes.AlloyerRecipe;
import mekanism.api.math.MathUtils;
import mekanism.api.recipes.CombinerRecipe;
import mekanism.api.recipes.ingredients.ChemicalStackIngredient;
import mekanism.api.recipes.ingredients.ItemStackIngredient;
import mekanism.common.config.MekanismConfig;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class BasicLightningFabricationRecipe extends LightningFabricationRecipe {

    public BasicLightningFabricationRecipe(
            ItemStackIngredient mainInput, List<ItemStackIngredient> extraInputs,
            Optional<ChemicalStackIngredient> chemicalInput,
            ItemStack output, long energyRequired, int duration) {
        super(MekInRecipeTypes.LIGHTNING_FABRICATION.get(), mainInput, extraInputs, chemicalInput, output,
                energyRequired, duration);
        if (extraInputs.size() > 2) {
            throw new IllegalStateException("LIGHTNING FABRICATION recipe supports at most 2 extraInputs");
        }
    }

    public static BasicLightningFabricationRecipe convertCombiner(CombinerRecipe combinerRecipe) {
        return new BasicLightningFabricationRecipe(
                combinerRecipe.getMainInput(),
                List.of(combinerRecipe.getExtraInput()),
                Optional.empty(),
                combinerRecipe.getOutputDefinition().get(0),
                MekanismConfig.usage.combiner.getAsLong() * 200L, // config value is per tick usage.
                200);
    }

    public static BasicLightningFabricationRecipe convertAlloyer(AlloyerRecipe alloyerRecipe) {
        return new BasicLightningFabricationRecipe(
                alloyerRecipe.getMainInput(),
                List.of(alloyerRecipe.getExtraInput(), alloyerRecipe.getTertiaryExtraInput()),
                Optional.empty(),
                alloyerRecipe.getOutputRaw(),
                MekanismConfig.usage.combiner.getAsLong() * 200L, // config value is per tick usage.
                200);
    }

    public static BasicLightningFabricationRecipe convertSimulationRoom(LightningSimulationRecipe simulationRecipe) {
        List<LightningSimulationIngredient> ingredients = simulationRecipe.inputs();
        List<LightningSimulationIngredient> subList = ingredients.size() > 1
                ? ingredients.subList(1, ingredients.size())
                : List.of();
        return new BasicLightningFabricationRecipe(
                MekInIngredientUtils.convert(simulationRecipe.inputs().get(0)),
                subList.stream().map(MekInIngredientUtils::convert).toList(),
                Optional.of(MekInIngredientUtils.getLightningIngredient(simulationRecipe.lightningTier(),
                        simulationRecipe.lightningCost())),
                simulationRecipe.getResultStack(),
                MathUtils.clampToLong(
                        simulationRecipe.totalEnergy() * MekanismConfig.general.forgeConversionRate.getOrDefault()),
                200);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return MekInRecipeSerializers.LIGHTNING_FABRICATION.get();
    }

}
