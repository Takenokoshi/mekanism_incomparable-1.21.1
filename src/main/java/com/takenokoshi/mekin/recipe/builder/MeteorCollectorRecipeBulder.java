package com.takenokoshi.mekin.recipe.builder;

import java.util.ArrayList;
import java.util.List;

import com.mojang.datafixers.util.Function5;
import com.takenokoshi.mekin.recipe.output.MeteorCollectorRecipeOutput;
import com.takenokoshi.mekin.recipe.recipes.basic.BasicMeteorCollectorRecipe;

import mekanism.api.datagen.recipe.MekanismRecipeBuilder;
import mekanism.api.recipes.ingredients.ItemStackIngredient;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;

public class MeteorCollectorRecipeBulder extends MekanismRecipeBuilder<MeteorCollectorRecipeBulder> {

    private final ItemStackIngredient input;
    private final ItemStackIngredient catalyst;
    private final ResourceKey<Level> dimension;
    private final List<MeteorCollectorRecipeOutput> outputs = new ArrayList<>();
    private final boolean requiredAdvanced;
    private final Function5<ItemStackIngredient, ItemStackIngredient, ResourceKey<Level>, List<MeteorCollectorRecipeOutput>, Boolean, Recipe<?>> factory;

    protected MeteorCollectorRecipeBulder(ItemStackIngredient input, ItemStackIngredient catalyst,
            ResourceKey<Level> dimension, boolean requiredAdvanced,
            Function5<ItemStackIngredient, ItemStackIngredient, ResourceKey<Level>, List<MeteorCollectorRecipeOutput>, Boolean, Recipe<?>> factory) {
        this.input = input;
        this.catalyst = catalyst;
        this.dimension = dimension;
        this.requiredAdvanced = requiredAdvanced;
        this.factory = factory;
    }

    public static MeteorCollectorRecipeBulder meteorCollector(ItemStackIngredient input, ItemStackIngredient catalyst,
            ResourceKey<Level> dimension, boolean requiredAdvanced) {
        return new MeteorCollectorRecipeBulder(input, catalyst, dimension, requiredAdvanced,
                BasicMeteorCollectorRecipe::new);
    }

    public MeteorCollectorRecipeBulder addOutput(MeteorCollectorRecipeOutput output) {
        outputs.add(output);
        return this;
    }

    public MeteorCollectorRecipeBulder addOutput(int weight, List<ItemStack> value) {
        return addOutput(new MeteorCollectorRecipeOutput(weight, value));
    }

    @Override
    protected Recipe<?> asRecipe() {
        return factory.apply(input, catalyst, dimension, outputs, requiredAdvanced);
    }

}
