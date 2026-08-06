package com.takenokoshi.mekin.recipe.output;

import java.util.Arrays;
import java.util.List;

import mekanism.api.inventory.IInventorySlot;
import mekanism.api.recipes.cache.CachedRecipe.OperationTracker;
import mekanism.api.recipes.cache.CachedRecipe.OperationTracker.RecipeError;
import net.minecraft.world.item.ItemStack;

public class MeteorCollectorOutputHandler {

    private final IInventorySlot[] slots;
    private final RecipeError notEnoughSpaceError;

    public MeteorCollectorOutputHandler(IInventorySlot[] slots, RecipeError notSpaceEnoughError) {
        this.slots = slots;
        this.notEnoughSpaceError = notSpaceEnoughError;
    }

    public boolean canOutputNow(int maxOutputSize) {
        return slots.length >= maxOutputSize && Arrays.stream(slots).allMatch(IInventorySlot::isEmpty);
    }

    public void notEnoughSpaceError(OperationTracker tracker) {
        tracker.resetProgress(notEnoughSpaceError);
    }

    public void handleOutput(List<ItemStack> value) {
        for (int i = 0; i < Math.min(slots.length, value.size()); i++) {
            slots[i].setStack(value.get(i).copy());
        }
    }
}
