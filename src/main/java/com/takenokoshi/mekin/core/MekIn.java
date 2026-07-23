package com.takenokoshi.mekin.core;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import com.takenokoshi.mekin.config.MekInConfig;
import com.takenokoshi.mekin.registries.MekInBlocks;
import com.takenokoshi.mekin.registries.MekInChemicals;
import com.takenokoshi.mekin.registries.MekInCreativeTabs;
import com.takenokoshi.mekin.registries.MekInFluids;
import com.takenokoshi.mekin.registries.MekInItems;
import com.takenokoshi.mekin.registries.MekInMachines;
import com.takenokoshi.mekin.registries.MekInRecipeSerializers;
import com.takenokoshi.mekin.registries.MekInRecipeTypes;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(value = MekInConstants.MODID, dist = Dist.DEDICATED_SERVER)
public class MekIn {
    public static final Logger LOGGER = LogUtils.getLogger();

    public MekIn(IEventBus modEventBus, ModContainer modContainer) {
        MekInConfig.registerConfigs(modContainer);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(MekInConfig::onConfigLoad);
        addRegistrationListeners(modEventBus);
    }

    private void addRegistrationListeners(IEventBus modEventBus) {
        MekInBlocks.BLOCKS.register(modEventBus);
        MekInItems.ITEMS.register(modEventBus);
        MekInChemicals.CHEMICALS.register(modEventBus);
        MekInFluids.FLUIDS.register(modEventBus);
        MekInMachines.MACHINES.register(modEventBus);
        MekInCreativeTabs.CREATIVE_TABS.register(modEventBus);
        MekInRecipeTypes.RECIPE_TYPES.register(modEventBus);
        MekInRecipeSerializers.RECIPE_SERIALIZERS.register(modEventBus);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM MekUt SETUP");
    }
}
