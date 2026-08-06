package com.takenokoshi.mekin.recipe_viewer.recipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.takenokoshi.mekin.recipe.output.MeteorCollectorRecipeOutput;
import com.takenokoshi.mekin.recipe.recipes.prefab.MeteorCollectorRecipe;

import mekanism.api.SerializationConstants;
import mekanism.api.recipes.ingredients.ItemStackIngredient;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import mekanism.client.recipe_viewer.emi.INamedRVRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;

public record MeteorCollectorRVRecipe(ResourceLocation id, ItemStackIngredient input, ItemStackIngredient catalyst,
        ResourceKey<Level> dimension, boolean requireAdvanced, List<ItemStack> output, double chance)
        implements INamedRVRecipe {

    public static final Codec<MeteorCollectorRVRecipe> CODEC = RecordCodecBuilder
            .create(instance -> instance.group(
                    ResourceLocation.CODEC.fieldOf(SerializationConstants.ID).forGetter(MeteorCollectorRVRecipe::id),
                    IngredientCreatorAccess.item().codec().fieldOf(SerializationConstants.INPUT)
                            .forGetter(MeteorCollectorRVRecipe::input),
                    IngredientCreatorAccess.item().codec().fieldOf("catalyst")
                            .forGetter(MeteorCollectorRVRecipe::catalyst),
                    ResourceKey.codec(Registries.DIMENSION).fieldOf("dimension")
                            .forGetter(MeteorCollectorRVRecipe::dimension),
                    Codec.BOOL.fieldOf("require_advanced").forGetter(MeteorCollectorRVRecipe::requireAdvanced),
                    ItemStack.CODEC.listOf().fieldOf(SerializationConstants.OUTPUT)
                            .forGetter(MeteorCollectorRVRecipe::output),
                    Codec.DOUBLE.fieldOf("chance").forGetter(MeteorCollectorRVRecipe::chance))
                    .apply(instance, MeteorCollectorRVRecipe::new));

    public static List<MeteorCollectorRVRecipe> createRVRecipes(RecipeHolder<MeteorCollectorRecipe> holder) {
        List<MeteorCollectorRVRecipe> result = new ArrayList<>();
        MeteorCollectorRecipe recipe = holder.value();
        String nameSpace = holder.id().getNamespace();
        String path = holder.id().getPath();
        for (int i = 0; i < recipe.outputs.size(); i++) {
            MeteorCollectorRecipeOutput recipeOutput = recipe.outputs.get(i);
            result.add(new MeteorCollectorRVRecipe(
                    ResourceLocation.fromNamespaceAndPath(nameSpace, "/rv/" + path + "/" + i),
                    recipe.input,
                    recipe.catalyst,
                    recipe.dimension,
                    recipe.requireAdvanced,
                    recipeOutput.value(),
                    (double) recipeOutput.weight() / (double) recipe.totalWeight));
        }
        return Collections.unmodifiableList(result);
    }
}
