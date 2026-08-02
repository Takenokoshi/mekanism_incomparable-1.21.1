package com.takenokoshi.mekin.registries;

import com.mojang.serialization.MapCodec;
import com.takenokoshi.mekin.core.MekInConstants;
import com.takenokoshi.mekin.lootmodifier.OverloadLootModifier;

import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class MekInLootModifiers {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister
            .create(
                    NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS,
                    MekInConstants.MODID);

    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<OverloadLootModifier>> OVERLOAD = LOOT_MODIFIERS
            .register("replace_overload", () -> OverloadLootModifier.CODEC);
}
