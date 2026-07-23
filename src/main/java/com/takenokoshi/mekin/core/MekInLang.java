package com.takenokoshi.mekin.core;

import mekanism.api.text.ILangEntry;
import net.minecraft.Util;

public class MekInLang implements ILangEntry {

    public final String key;

    public MekInLang(String type, String path) {
        this.key = Util.makeDescriptionId(type, MekInConstants.rl(path));
    }

    @Override
    public String getTranslationKey() {
        return key;
    }
}
