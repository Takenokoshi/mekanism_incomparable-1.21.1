package com.takenokoshi.mekin.registries;

import com.takenokoshi.mekin.core.MekInConstants;
import com.takenokoshi.mekin.core.MekInLang;
import mekanism.common.registration.MekanismDeferredHolder;
import mekanism.common.registration.impl.CreativeTabDeferredRegister;
import net.minecraft.world.item.CreativeModeTab;

public class MekInCreativeTabs {
    public static final CreativeTabDeferredRegister CREATIVE_TABS = new CreativeTabDeferredRegister(
            MekInConstants.MODID);

    public static final MekanismDeferredHolder<CreativeModeTab, CreativeModeTab> MAIN = CREATIVE_TABS.registerMain(
            new MekInLang("creative_tab", "main"),
            MekInItems.TIMESPACE_CONTROL_CIRCUIT,
            builder -> builder.displayItems(
                    (displayParameters, output) -> {
                        CreativeTabDeferredRegister.addToDisplay(MekInItems.ITEMS, output);
                        CreativeTabDeferredRegister.addToDisplay(MekInBlocks.BLOCKS, output);
                        CreativeTabDeferredRegister.addToDisplay(MekInMachines.MACHINES.blockRegister, output);
                    }));
}
