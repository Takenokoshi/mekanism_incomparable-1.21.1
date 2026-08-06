package com.takenokoshi.mekin.recipe.output;

import net.minecraft.world.item.ItemStack;

public record MekInChanceOutput(ItemStack value, double chance) {
    public static final MekInChanceOutput EMPTY = new MekInChanceOutput(ItemStack.EMPTY, 0.0d);

    public boolean isEmpty() {
        return value.isEmpty() || chance <= 0.0d;
    }
}
