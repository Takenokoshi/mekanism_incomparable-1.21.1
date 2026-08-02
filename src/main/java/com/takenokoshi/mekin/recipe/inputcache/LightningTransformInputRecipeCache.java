package com.takenokoshi.mekin.recipe.inputcache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jetbrains.annotations.Nullable;

import com.moakiee.ae2lt.lightning.CountedIngredient;
import com.moakiee.ae2lt.lightning.LightningTransformRecipe;
import com.takenokoshi.mekaddonlib.recipe.inputcache.MekALAbstractInputRecipeCache;
import com.takenokoshi.mekaddonlib.recipe.type.MekALRecipeType;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;

public class LightningTransformInputRecipeCache extends MekALAbstractInputRecipeCache<LightningTransformRecipe> {

    private final Map<Item, Set<LightningTransformRecipe>> cacheMap = new HashMap<>();

    public LightningTransformInputRecipeCache(MekALRecipeType<?, LightningTransformRecipe, ?> recipeType) {
        super(recipeType);
    }

    @Override
    public void clear() {
        super.clear();
        cacheMap.clear();
    }

    public boolean containsInput(Level world, ItemStack input) {
        if (input.isEmpty()) {
            return false;
        }
        initCacheIfNeeded(world);
        return cacheMap.containsKey(input.getItem());
    }

    public boolean containsInput(Level world, ItemStack input, List<ItemStack> otherInputs) {
        if (otherInputs.isEmpty()) {
            return containsInput(world, input);
        }
        if (input.isEmpty()) {
            return false;
        }
        initCacheIfNeeded(world);
        return cacheMap.getOrDefault(input.getItem(), Set.of()).stream()
                .filter(recipe -> inputAllMatch(recipe, input, otherInputs)).findFirst().isPresent();
    }

    private static boolean inputAllMatch(LightningTransformRecipe recipe, ItemStack input,
            List<ItemStack> otherInputs) {
        List<ItemStack> inputs = new ArrayList<>();
        inputs.add(input);
        inputs.addAll(otherInputs);
        List<CountedIngredient> ingredients = recipe.inputs();
        if (ingredients.size() < inputs.size()) {
            return false;
        }
        boolean[] usedCache = new boolean[ingredients.size()];
        for (int i = 0; i < inputs.size(); i++) {
            ItemStack serching = inputs.get(i);
            boolean found = false;
            for (int j = 0; j < ingredients.size(); j++) {
                if (usedCache[j]) {
                    continue;
                }
                if (ingredients.get(j).ingredient().test(serching)) {
                    usedCache[j] = true;
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }

    public @Nullable LightningTransformRecipe findFirstRecipe(Level world, List<ItemStack> inputs) {
        if (inputs.isEmpty() || inputs.contains(ItemStack.EMPTY)) {
            return null;
        }
        initCacheIfNeeded(world);
        return cacheMap.getOrDefault(inputs.get(0).getItem(), Set.of()).stream()
                .filter(recipe -> isCollectInput(recipe, inputs))
                .findFirst()
                .orElse(null);
    }

    private static boolean isCollectInput(LightningTransformRecipe recipe, List<ItemStack> inputs) {
        List<CountedIngredient> ingredients = recipe.inputs();
        if (ingredients.size() != inputs.size()) {
            return false;
        }
        boolean[] usedCache = new boolean[ingredients.size()];
        for (int i = 0; i < inputs.size(); i++) {
            ItemStack serching = inputs.get(i);
            boolean found = false;
            for (int j = 0; j < ingredients.size(); j++) {
                if (usedCache[j]) {
                    continue;
                }
                if (ingredients.get(j).ingredient().test(serching)) {
                    usedCache[j] = true;
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void initCache(List<RecipeHolder<LightningTransformRecipe>> holders) {
        for (RecipeHolder<LightningTransformRecipe> holder : holders) {
            holder.value().getIngredients().forEach(ingredient -> {
                for (ItemStack stack : ingredient.getItems()) {
                    cacheMap.merge(
                            stack.getItem(),
                            new HashSet<>(List.of(holder.value())),
                            LightningTransformInputRecipeCache::mergeSet);
                }
            });
        }
    }

    private static Set<LightningTransformRecipe> mergeSet(Set<LightningTransformRecipe> a,
            Set<LightningTransformRecipe> b) {
        a.addAll(b);
        return a;
    }

}
