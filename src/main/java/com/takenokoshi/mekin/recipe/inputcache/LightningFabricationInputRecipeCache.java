package com.takenokoshi.mekin.recipe.inputcache;

import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.Nullable;

import com.takenokoshi.mekaddonlib.recipe.inputcache.MekALAbstractInputRecipeCache;
import com.takenokoshi.mekaddonlib.recipe.type.MekALRecipeType;
import com.takenokoshi.mekin.recipe.recipes.prefab.LightningFabricationRecipe;

import mekanism.api.chemical.ChemicalStack;
import mekanism.common.recipe.lookup.cache.type.ChemicalInputCache;
import mekanism.common.recipe.lookup.cache.type.ItemInputCache;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;

public class LightningFabricationInputRecipeCache extends MekALAbstractInputRecipeCache<LightningFabricationRecipe> {

    private final ItemInputCache<LightningFabricationRecipe> mainInputCache = new ItemInputCache<>();
    private final ItemInputCache<LightningFabricationRecipe> extraInputCache = new ItemInputCache<>();
    private final ChemicalInputCache<LightningFabricationRecipe> chemicalInputCache = new ChemicalInputCache<>();

    public LightningFabricationInputRecipeCache(MekALRecipeType<?, LightningFabricationRecipe, ?> recipeType) {
        super(recipeType);
    }

    @Override
    public void clear() {
        super.clear();
        mainInputCache.clear();
        extraInputCache.clear();
        chemicalInputCache.clear();
    }

    public boolean containsMainInput(Level world, ItemStack mainInput) {
        if (mainInput.isEmpty()) {
            return false;
        }
        initCacheIfNeeded(world);
        return mainInputCache.contains(mainInput);
    }

    public boolean containsExtraInput(Level world, ItemStack extraInput) {
        if (extraInput.isEmpty()) {
            return false;
        }
        initCacheIfNeeded(world);
        return extraInputCache.contains(extraInput);
    }

    public boolean containsChemicalInput(Level world, ChemicalStack chemicalInput) {
        if (chemicalInput.isEmpty()) {
            return false;
        }
        initCacheIfNeeded(world);
        return chemicalInputCache.contains(chemicalInput);
    }

    public boolean containsMainInputOther(Level world, ItemStack mainInput, List<ItemStack> extraInputs,
            ChemicalStack chemicalInput) {
        if (mainInput.isEmpty()) {
            return false;
        }
        initCacheIfNeeded(world);
        return mainInputCache.contains(mainInput, recipe -> recipe.cacheTest(mainInput, extraInputs, chemicalInput));
    }

    public boolean containsExtraInputOther(Level world, ItemStack mainInput, ItemStack extraInput,
            List<ItemStack> otherExtraInputs, ChemicalStack chemicalInput) {
        if (extraInput.isEmpty()) {
            return false;
        }
        initCacheIfNeeded(world);
        List<ItemStack> extraInputs = new ArrayList<>(otherExtraInputs);
        extraInputs.add(extraInput);
        return extraInputCache.contains(extraInput, recipe -> recipe.cacheTest(mainInput, extraInputs, chemicalInput));
    }

    public boolean containsChemicalInputOther(Level world, ItemStack mainInput, List<ItemStack> extraInputs,
            ChemicalStack chemicalInput) {
        if (chemicalInput.isEmpty()) {
            return false;
        }
        initCacheIfNeeded(world);
        return chemicalInputCache.contains(chemicalInput,
                recipe -> recipe.cacheTest(mainInput, extraInputs, chemicalInput));
    }

    public @Nullable LightningFabricationRecipe findFirstRecipe(Level world, ItemStack mainInput,
            List<ItemStack> extraInputs,
            ChemicalStack chemicalInput) {
        if (mainInput.isEmpty()) {
            return null;
        }
        initCacheIfNeeded(world);
        return mainInputCache.findFirstRecipe(mainInput, recipe -> recipe.test(mainInput, extraInputs, chemicalInput));
    }

    @Override
    protected void initCache(List<RecipeHolder<LightningFabricationRecipe>> recipeHolders) {
        recipeHolders.forEach(holder -> {
            LightningFabricationRecipe recipe = holder.value();
            mainInputCache.mapInputs(recipe, recipe.mainInput);
            recipe.extraInputs.forEach(ing -> extraInputCache.mapInputs(recipe, ing));
            if (recipe.chemicalInput != null) {
                chemicalInputCache.mapInputs(recipe, recipe.chemicalInput);
            }
        });
    }

}
