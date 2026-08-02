package com.takenokoshi.mekin.gui.machine;

import org.jetbrains.annotations.NotNull;

import com.takenokoshi.mekin.blockentity.machine.BELightningRecollector;

import mekanism.client.gui.GuiConfigurableTile;
import mekanism.client.gui.element.gauge.GaugeType;
import mekanism.client.gui.element.gauge.GuiChemicalGauge;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class GuiLightningRecollector
        extends GuiConfigurableTile<BELightningRecollector, MekanismTileContainer<BELightningRecollector>> {

    public GuiLightningRecollector(MekanismTileContainer<BELightningRecollector> container, Inventory inv,
            Component title) {
        super(container, inv, title);
        dynamicSlots = true;
    }

    @Override
    protected void addGuiElements() {
        super.addGuiElements();
        addRenderableWidget(new GuiChemicalGauge(tile::getChemicalTank, () -> tile.getChemicalTanks(null),
                GaugeType.WIDE, this, 55, 18));
    }

    @Override
    protected void drawForegroundText(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY) {
        renderTitleText(guiGraphics);
        renderInventoryText(guiGraphics);
        super.drawForegroundText(guiGraphics, mouseX, mouseY);
    }

}
