package com.takenokoshi.mekin.recipe.recipes.basic;

import java.util.List;

import com.takenokoshi.mekin.recipe.output.MeteorCollectorRecipeOutput;
import com.takenokoshi.mekin.recipe.recipes.prefab.MeteorCollectorRecipe;
import com.takenokoshi.mekin.registries.MekInRecipeSerializers;
import com.takenokoshi.mekin.registries.MekInRecipeTypes;

import mekanism.api.recipes.ingredients.ItemStackIngredient;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

public class BasicMeteorCollectorRecipe extends MeteorCollectorRecipe {

    public BasicMeteorCollectorRecipe(ItemStackIngredient input, ItemStackIngredient catalyst,
            ResourceKey<Level> dimension, List<MeteorCollectorRecipeOutput> outputs, boolean requireAdvanced) {
        super(input, catalyst, dimension, outputs, MekInRecipeTypes.METEOR_COLLECTOR.get(), requireAdvanced);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return MekInRecipeSerializers.METEOR_COLLECTOR.get();
    }

}
