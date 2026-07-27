package com.takenokoshi.mekin.registries;

import com.takenokoshi.mekin.core.MekInConstants;
import com.takenokoshi.mekin.core.MekInLang;
import mekanism.common.registration.MekanismDeferredHolder;
import mekanism.common.registration.impl.CreativeTabDeferredRegister;
import net.minecraft.world.item.CreativeModeTab;

public class MekInCreativeTabs {
    public static final CreativeTabDeferredRegister CREATIVE_TABS = new CreativeTabDeferredRegister(
            MekInConstants.MODID);

    public static final MekanismDeferredHolder<CreativeModeTab, CreativeModeTab> MATERIALS = CREATIVE_TABS.register(
            "materials",
            MekInLang.CREATIVE_TAB_MATERIALS,
            MekInItems.TIMESPACE_CONTROL_CIRCUIT,
            builder -> builder.displayItems(
                    (displayParameters, output) -> {
                        CreativeTabDeferredRegister.addToDisplay(MekInItems.ITEMS, output);
                        CreativeTabDeferredRegister.addToDisplay(MekInBlocks.BLOCKS, output);
                    }));

    public static final MekanismDeferredHolder<CreativeModeTab, CreativeModeTab> MACHINES = CREATIVE_TABS.register(
            "machines",
            MekInLang.CREATIVE_TAB_MACHINES,
            MekInMachines.CHEMICAL_LEACHING_CHAMBER.getBlock().getItemHolder(),
            builder -> builder.displayItems(
                    (displayParameters, output) -> {
                        CreativeTabDeferredRegister.addToDisplay(MekInMachines.MACHINES.blockRegister, output);
                    }));

    public static final MekanismDeferredHolder<CreativeModeTab, CreativeModeTab> ABSOLUTE_MACHINES = CREATIVE_TABS
            .register(
                    "absolute_overclocked_machines",
                    MekInLang.CREATIVE_TAB_ABSOLUTE_MACHINES,
                    MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_BOILER.getBlock().getItemHolder(),
                    builder -> builder.displayItems(
                            (displayParameters, output) -> {
                                CreativeTabDeferredRegister.addToDisplay(MekInAbsoluteMachines.MACHINES.blockRegister,
                                        output);
                            }));

    public static final MekanismDeferredHolder<CreativeModeTab, CreativeModeTab> SUPREME_MACHINES = CREATIVE_TABS
            .register(
                    "supreme_quantum_machines",
                    MekInLang.CREATIVE_TAB_SUPREME_MACHINES,
                    MekInSupremeMachines.SUPREME_QUANTUM_CHEMICAL_CRYSTALLIZER.getBlock().getItemHolder(),
                    builder -> builder.displayItems(
                            (displayParameters, output) -> {
                                CreativeTabDeferredRegister.addToDisplay(MekInSupremeMachines.MACHINES.blockRegister,
                                        output);
                            }));

    public static final MekanismDeferredHolder<CreativeModeTab, CreativeModeTab> INFINITE_MACHINES = CREATIVE_TABS
            .register(
                    "infinite_multiversal_machines",
                    MekInLang.CREATIVE_TAB_INFINITE_MACHINES,
                    MekInInfiniteMachines.INFINITE_MULTIVERSAL_BOILER.getBlock().getItemHolder(),
                    builder -> builder.displayItems(
                            (displayParameters, output) -> {
                                CreativeTabDeferredRegister.addToDisplay(MekInInfiniteMachines.MACHINES.blockRegister,
                                        output);
                            }));
}
