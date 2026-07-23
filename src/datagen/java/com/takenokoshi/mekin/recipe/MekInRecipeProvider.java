package com.takenokoshi.mekin.recipe;

import java.util.concurrent.CompletableFuture;

import com.takenokoshi.mekin.recipe.building.*;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

public class MekInRecipeProvider extends RecipeProvider {

    public MekInRecipeProvider(PackOutput output, CompletableFuture<Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput output) {
        AETRecipes.buildRecipes(output);
        APTRecipes.buildRecipes(output);
        ChemicalConvertionRecipes.buildRecipes(output);
        ChemicalInfusingRecipes.buildRecipes(output);
        ChemixerRecipes.buildRecipes(output);
        ControlCircuitRecipes.buildRecipes(output, RecipeProvider::has);
        ControlMatrixRecipes.buildRecipes(output);
        EnrichingRecipes.buildRecipes(output);
        MaterialProcessingRecipes.buildRecipes(output, RecipeProvider::has);
        MekanismAlloyRecipes.buildRecipes(output);
        PRCRecipes.buildRecipes(output);
        ProcessorRecipes.buildRecipes(output);
        SDARecipes.buildRecipes(output);
        SDRCRecipes.buildRecipes(output);
        SPSRecipes.buildRecipes(output);
        StellerGenesisRecipes.buildRecipes(output);
    }

}
