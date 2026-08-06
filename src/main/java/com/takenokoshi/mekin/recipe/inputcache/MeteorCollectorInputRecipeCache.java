package com.takenokoshi.mekin.recipe.inputcache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jetbrains.annotations.Nullable;

import com.takenokoshi.mekaddonlib.recipe.inputcache.MekALAbstractInputRecipeCache;
import com.takenokoshi.mekaddonlib.recipe.type.MekALRecipeType;
import com.takenokoshi.mekin.recipe.recipes.prefab.MeteorCollectorRecipe;

import mekanism.common.recipe.lookup.cache.type.ItemInputCache;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;

public class MeteorCollectorInputRecipeCache extends MekALAbstractInputRecipeCache<MeteorCollectorRecipe> {

    private final Map<ResourceKey<Level>, ItemInputCache<MeteorCollectorRecipe>> catalystCacheMap = new HashMap<>();
    private final Map<ResourceKey<Level>, ItemInputCache<MeteorCollectorRecipe>> inputCacheMap = new HashMap<>();

    public MeteorCollectorInputRecipeCache(MekALRecipeType<?, MeteorCollectorRecipe, ?> recipeType) {
        super(recipeType);
    }

    @Override
    public void clear() {
        super.clear();
        catalystCacheMap.clear();
        inputCacheMap.clear();
    }

    public boolean containsInput(Level world, ItemStack input) {
        if (input.isEmpty()) {
            return false;
        }
        initCacheIfNeeded(world);
        ItemInputCache<MeteorCollectorRecipe> cache = inputCacheMap.get(world.dimension());
        if (cache != null) {
            return cache.contains(input);
        }
        return false;
    }

    public boolean containsCatalyst(Level world, ItemStack catalyst) {
        if (catalyst.isEmpty()) {
            return false;
        }
        initCacheIfNeeded(world);
        ItemInputCache<MeteorCollectorRecipe> cache = catalystCacheMap.get(world.dimension());
        if (cache != null) {
            return cache.contains(catalyst);
        }
        return false;
    }

    public boolean containsInputCatalyst(Level world, ItemStack input, ItemStack catalyst) {
        if (catalyst.isEmpty()) {
            return containsInput(world, input);
        }
        if (input.isEmpty()) {
            return false;
        }
        initCacheIfNeeded(world);
        ItemInputCache<MeteorCollectorRecipe> cache = inputCacheMap.get(world.dimension());
        if (cache != null) {
            return cache.contains(input, recipe -> recipe.test(input, catalyst, world));
        }
        return false;
    }

    public boolean containsCatalystInput(Level world, ItemStack input, ItemStack catalyst) {
        if (input.isEmpty()) {
            return containsCatalyst(world, catalyst);
        }
        if (catalyst.isEmpty()) {
            return false;
        }
        initCacheIfNeeded(world);
        ItemInputCache<MeteorCollectorRecipe> cache = catalystCacheMap.get(world.dimension());
        if (cache != null) {
            return cache.contains(catalyst, recipe -> recipe.test(input, catalyst, world));
        }
        return false;
    }

    public @Nullable MeteorCollectorRecipe findFirstRecipe(Level world, ItemStack input, ItemStack catalyst) {
        if (input.isEmpty() || catalyst.isEmpty() || world == null) {
            return null;
        }
        initCacheIfNeeded(world);
        ItemInputCache<MeteorCollectorRecipe> cache = inputCacheMap.get(world.dimension());
        if (cache != null) {
            return cache.findFirstRecipe(input, recipe -> recipe.test(input, catalyst, world));
        }
        return null;

    }

    @Override
    protected void initCache(List<RecipeHolder<MeteorCollectorRecipe>> recipeHolders) {
        recipeHolders.forEach(holder -> {
            final MeteorCollectorRecipe recipe = holder.value();
            catalystCacheMap.computeIfAbsent(recipe.dimension, key -> new ItemInputCache<>());
            inputCacheMap.computeIfAbsent(recipe.dimension, key -> new ItemInputCache<>());
            catalystCacheMap.get(recipe.dimension).mapInputs(recipe, recipe.catalyst);
            inputCacheMap.get(recipe.dimension).mapInputs(recipe, recipe.input);
        });
    }

}
