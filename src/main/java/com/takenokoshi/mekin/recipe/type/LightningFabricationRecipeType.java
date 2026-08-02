package com.takenokoshi.mekin.recipe.type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.takenokoshi.mekaddonlib.recipe.type.MekALRecipeType;
import com.takenokoshi.mekin.recipe.inputcache.LightningFabricationInputRecipeCache;
import com.takenokoshi.mekin.recipe.recipes.basic.BasicLightningFabricationRecipe;
import com.takenokoshi.mekin.recipe.recipes.prefab.LightningFabricationRecipe;

import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeManager;

import static mekanism.common.recipe.MekanismRecipeType.COMBINING;
import static fr.iglee42.evolvedmekanism.registries.EMRecipeType.ALLOYING;
import static com.moakiee.ae2lt.registry.ModRecipeTypes.LIGHTNING_SIMULATION_TYPE;

public class LightningFabricationRecipeType
        extends MekALRecipeType<RecipeInput, LightningFabricationRecipe, LightningFabricationInputRecipeCache> {

    public LightningFabricationRecipeType(ResourceLocation name) {
        super(name, LightningFabricationInputRecipeCache::new);
    }

    @Override
    protected @NotNull List<RecipeHolder<LightningFabricationRecipe>> getRecipesUncached(
            @NotNull RecipeManager recipeManager, @Nullable RegistryAccess registryAccess) {
        List<RecipeHolder<LightningFabricationRecipe>> result = new ArrayList<>(
                super.getRecipesUncached(recipeManager, registryAccess));
        recipeManager.getAllRecipesFor(LIGHTNING_SIMULATION_TYPE.get()).forEach(holder -> {
            result.add(new RecipeHolder<>(
                    ResourceLocation.fromNamespaceAndPath(holder.id().getNamespace(),
                            "/runtime_generated/from_lightning_simulation/" + holder.id().getPath()),
                    BasicLightningFabricationRecipe.convertSimulationRoom(holder.value())));
        });
        recipeManager.getAllRecipesFor(COMBINING.getRecipeType()).forEach(holder -> {
            result.add(new RecipeHolder<>(
                    ResourceLocation.fromNamespaceAndPath(holder.id().getNamespace(),
                            "/runtime_generated/from_combining/" + holder.id().getPath()),
                    BasicLightningFabricationRecipe.convertCombiner(holder.value())));
        });
        recipeManager.getAllRecipesFor(ALLOYING.getRecipeType()).forEach(holder -> {
            result.add(new RecipeHolder<>(
                    ResourceLocation.fromNamespaceAndPath(holder.id().getNamespace(),
                            "/runtime_generated/from_alloying/" + holder.id().getPath()),
                    BasicLightningFabricationRecipe.convertAlloyer(holder.value())));
        });
        return Collections.unmodifiableList(result);
    }

}
