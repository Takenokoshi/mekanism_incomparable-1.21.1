package com.takenokoshi.mekin.core;

import com.takenokoshi.mekin.registries.MekInFluids;
import com.takenokoshi.mekin.registries.MekInScreens;

import mekanism.client.ClientRegistrationUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Holder;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

@Mod(value = MekInConstants.MODID, dist = Dist.CLIENT)
public class MekInClient extends MekIn {
    public MekInClient(IEventBus modEventBus, ModContainer modContainer) {
        super(modEventBus, modContainer);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(MekInScreens::registerScreens);
        modEventBus.addListener(this::registerClientExtensions);
    }

    private void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            @SuppressWarnings("unused")
            Minecraft minecraft = Minecraft.getInstance();
            for (Holder<Fluid> fluid : MekInFluids.FLUIDS.getFluidEntries()) {
                ItemBlockRenderTypes.setRenderLayer(fluid.value(), RenderType.translucent());
            }
        });
    }

    private void registerClientExtensions(RegisterClientExtensionsEvent event){
        ClientRegistrationUtil.registerFluidExtensions(event, MekInFluids.FLUIDS);
    }
}
