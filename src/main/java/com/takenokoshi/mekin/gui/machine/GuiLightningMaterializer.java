package com.takenokoshi.mekin.gui.machine;

import org.jetbrains.annotations.NotNull;

import com.takenokoshi.mekin.blockentity.machine.BELightningMaterializer;

import mekanism.client.gui.GuiConfigurableTile;
import mekanism.client.gui.element.button.ToggleButton;
import mekanism.client.gui.element.gauge.GaugeType;
import mekanism.client.gui.element.gauge.GuiChemicalGauge;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import mekanism.common.network.PacketUtils;
import mekanism.common.network.to_server.PacketGuiInteract;
import mekanism.common.network.to_server.PacketGuiInteract.GuiInteraction;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class GuiLightningMaterializer
        extends GuiConfigurableTile<BELightningMaterializer, MekanismTileContainer<BELightningMaterializer>> {

    public GuiLightningMaterializer(MekanismTileContainer<BELightningMaterializer> container, Inventory inv,
            Component title) {
        super(container, inv, title);
        dynamicSlots = true;
    }

    @Override
    protected void addGuiElements() {
        super.addGuiElements();
        addRenderableWidget(new GuiChemicalGauge(tile::getChemicalTank, () -> tile.getChemicalTanks(null),
                GaugeType.WIDE, this, 55, 18));
        addRenderableWidget(new ToggleButton(this, 4, 4, tile::getMode,
                (element, mouseX, mouseY) -> PacketUtils
                        .sendToServer(new PacketGuiInteract(GuiInteraction.NEXT_MODE, tile))));
    }

    @Override
    protected void drawForegroundText(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY) {
        renderTitleText(guiGraphics);
        renderInventoryText(guiGraphics);
        super.drawForegroundText(guiGraphics, mouseX, mouseY);
    }

}
