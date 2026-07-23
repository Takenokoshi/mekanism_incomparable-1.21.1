package com.takenokoshi.mekin.recipe.building;

import com.fxd927.mekanismelements.common.registries.MSGases;
import com.takenokoshi.mekin.core.MekInConstants;

import mekanism.api.datagen.recipe.builder.PressurizedReactionRecipeBuilder;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import mekanism.common.registries.MekanismItems;
import net.minecraft.data.recipes.RecipeOutput;
import net.neoforged.neoforge.common.Tags;

public class PRCRecipes {

    public static void buildRecipes(RecipeOutput output) {
        PressurizedReactionRecipeBuilder
                .reaction(
                        IngredientCreatorAccess.item().from(Tags.Items.SANDS, 1),
                        IngredientCreatorAccess.fluid().from(Tags.Fluids.WATER, 1000),
                        IngredientCreatorAccess.chemicalStack().from(MSGases.COMPRESSED_AIR.asStack(100)),
                        100,
                        MekanismItems.SALT.asStack(10),
                        MSGases.POTASSIUM_CHLORIDE.asStack(15))
                .build(output, MekInConstants.rl("pressurized_reaction/potassium"));
    }
}
