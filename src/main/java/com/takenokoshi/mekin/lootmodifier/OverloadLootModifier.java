package com.takenokoshi.mekin.lootmodifier;

import com.moakiee.ae2lt.registry.ModItems;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.takenokoshi.mekin.registries.MekInItems;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

public class OverloadLootModifier extends LootModifier {

    public static final MapCodec<OverloadLootModifier> CODEC = RecordCodecBuilder
            .mapCodec(instance -> codecStart(instance).apply(instance, OverloadLootModifier::new));

    public OverloadLootModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(
            ObjectArrayList<ItemStack> generatedLoot,
            LootContext context) {
        for (int i = 0; i < generatedLoot.size(); i++) {
            ItemStack stack = generatedLoot.get(i);
            if (stack.is(ModItems.OVERLOAD_CRYSTAL)) {
                ItemStack replace = MekInItems.RAW_OVERLOAD.asStack(stack.getCount());
                replace.applyComponents(stack.getComponents());
                generatedLoot.set(i, replace);
            }
        }
        return generatedLoot;
    }

}
