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

    public static final MekInLang CREATIVE_TAB_MATERIALS = new MekInLang("creative_tab", "materials");
    public static final MekInLang CREATIVE_TAB_MACHINES = new MekInLang("creative_tab", "machines");
    public static final MekInLang CREATIVE_TAB_ABSOLUTE_MACHINES = new MekInLang("creative_tab",
            "absolute_overclocked_machines");
    public static final MekInLang CREATIVE_TAB_SUPREME_MACHINES = new MekInLang("creative_tab",
            "supreme_quantum_machines");
    public static final MekInLang CREATIVE_TAB_INFINITE_MACHINES = new MekInLang("creative_tab",
            "infinite_multiversal_machines");
}
