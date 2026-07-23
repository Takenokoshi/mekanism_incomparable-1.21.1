package com.takenokoshi.mekin.core;

import net.minecraft.resources.ResourceLocation;

public class MekInConstants {
    public static final String MODID = "mekanism_incomparable";

    public static ResourceLocation rl(String path){
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}
