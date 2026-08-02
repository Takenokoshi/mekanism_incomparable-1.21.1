package com.takenokoshi.mekin.recipe.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mojang.datafixers.util.Function6;

import mekanism.api.datagen.recipe.MekanismRecipeBuilder;
import mekanism.api.recipes.ingredients.ChemicalStackIngredient;
import mekanism.api.recipes.ingredients.ItemStackIngredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;

public class LightningFabricationRecipeBuilder extends MekanismRecipeBuilder<LightningFabricationRecipeBuilder> {

    private final ItemStackIngredient mainInput;
    private final List<ItemStackIngredient> subInputs = new ArrayList<>();
    private ChemicalStackIngredient chemicalInput;
    private final ItemStack output;

    private long energyRequired;
    private int duration;

    private final Function6<ItemStackIngredient, List<ItemStackIngredient>, Optional<ChemicalStackIngredient>, ItemStack, Long, Integer, Recipe<?>> factory;

    protected LightningFabricationRecipeBuilder(ItemStackIngredient mainInput, ItemStack output,
            Function6<ItemStackIngredient, List<ItemStackIngredient>, Optional<ChemicalStackIngredient>, ItemStack, Long, Integer, Recipe<?>> factory) {
        this.mainInput = mainInput;
        this.output = output;
        this.factory = factory;
        this.energyRequired = 8_000L;
        this.duration = 200;
    }

    public LightningFabricationRecipeBuilder addSubInput(ItemStackIngredient ingredient) {
        subInputs.add(ingredient);
        return this;
    }

    public LightningFabricationRecipeBuilder setChemicalInput(ChemicalStackIngredient ingredient) {
        chemicalInput = ingredient;
        return this;
    }

    public LightningFabricationRecipeBuilder setEnergyRequired(long value) {
        energyRequired = value;
        return this;
    }

    public LightningFabricationRecipeBuilder setDuration(int value) {
        duration = value;
        return this;
    }

    @Override
    protected Recipe<?> asRecipe() {
        return factory.apply(mainInput, subInputs, Optional.ofNullable(chemicalInput), output, energyRequired,
                duration);
    }

}
