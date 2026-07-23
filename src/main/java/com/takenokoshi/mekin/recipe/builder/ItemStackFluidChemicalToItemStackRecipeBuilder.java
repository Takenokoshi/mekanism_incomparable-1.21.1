package com.takenokoshi.mekin.recipe.builder;

import com.mojang.datafixers.util.Function6;
import com.takenokoshi.mekin.recipe.recipes.basic.BasicLeachingRecipe;
import com.takenokoshi.mekin.recipe.recipes.prefab.ItemStackFluidChemicalToItemStackRecipe;

import mekanism.api.datagen.recipe.MekanismRecipeBuilder;
import mekanism.api.recipes.ingredients.ChemicalStackIngredient;
import mekanism.api.recipes.ingredients.FluidStackIngredient;
import mekanism.api.recipes.ingredients.ItemStackIngredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;

public class ItemStackFluidChemicalToItemStackRecipeBuilder
        extends MekanismRecipeBuilder<ItemStackFluidChemicalToItemStackRecipeBuilder> {

    protected final ItemStackIngredient itemInput;
    protected final FluidStackIngredient fluidInput;
    protected final ChemicalStackIngredient chemicalInput;
    protected final ItemStack output;
    protected final Function6<ItemStackIngredient, FluidStackIngredient, ChemicalStackIngredient, Long, Integer, ItemStack, ItemStackFluidChemicalToItemStackRecipe> factory;

    protected long energyRequired;
    protected int duration;

    protected ItemStackFluidChemicalToItemStackRecipeBuilder(ItemStackIngredient itemInput,
            FluidStackIngredient fluidInput,
            ChemicalStackIngredient chemicalInput,
            ItemStack output,
            Function6<ItemStackIngredient, FluidStackIngredient, ChemicalStackIngredient, Long, Integer, ItemStack, ItemStackFluidChemicalToItemStackRecipe> factory) {
        this.itemInput = itemInput;
        this.fluidInput = fluidInput;
        this.chemicalInput = chemicalInput;
        this.output = output;
        this.energyRequired = 0;
        this.duration = 100;
        this.factory = factory;
    }

    public static ItemStackFluidChemicalToItemStackRecipeBuilder leaching(ItemStackIngredient itemInput,
            FluidStackIngredient fluidInput,
            ChemicalStackIngredient chemicalInput,
            ItemStack output) {
        return new ItemStackFluidChemicalToItemStackRecipeBuilder(itemInput, fluidInput, chemicalInput, output,
                BasicLeachingRecipe::new);
    }

    public ItemStackFluidChemicalToItemStackRecipeBuilder setEnergyRequired(long value) {
        energyRequired = value < 0 ? 0 : value;
        return this;
    }

    public ItemStackFluidChemicalToItemStackRecipeBuilder setDuration(int duration) {
        this.duration = duration < 1 ? 100 : duration;
        return this;
    }

    @Override
    protected Recipe<?> asRecipe() {
        return factory.apply(itemInput, fluidInput, chemicalInput, energyRequired, duration, output);
    }

}
