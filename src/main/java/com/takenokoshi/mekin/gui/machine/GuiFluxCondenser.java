package com.takenokoshi.mekin.gui.machine;

import org.jetbrains.annotations.NotNull;

import com.takenokoshi.mekin.blockentity.machine.BEFluxCondenser;
import com.takenokoshi.mekin.recipe_viewer.type.MekInRecipeViewerRecipeType;

import mekanism.api.text.ILangEntry;
import mekanism.client.gui.GuiConfigurableTile;
import mekanism.client.gui.element.GuiDownArrow;
import mekanism.client.gui.element.button.ToggleButton;
import mekanism.client.gui.element.gauge.GaugeType;
import mekanism.client.gui.element.gauge.GuiEnergyGauge;
import mekanism.client.gui.element.gauge.GuiFluidGauge;
import mekanism.client.gui.element.progress.GuiProgress;
import mekanism.client.gui.element.progress.IProgressInfoHandler.IBooleanProgressInfoHandler;
import mekanism.client.recipe_viewer.type.IRecipeViewerRecipeType;
import mekanism.client.gui.element.progress.ProgressType;
import mekanism.common.MekanismLang;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import mekanism.common.network.PacketUtils;
import mekanism.common.network.to_server.PacketGuiInteract;
import mekanism.common.network.to_server.PacketGuiInteract.GuiInteraction;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class GuiFluxCondenser extends GuiConfigurableTile<BEFluxCondenser, MekanismTileContainer<BEFluxCondenser>> {

    public GuiFluxCondenser(MekanismTileContainer<BEFluxCondenser> container, Inventory inv, Component title) {
        super(container, inv, title);
        dynamicSlots = true;
        titleLabelY = 4;
    }

    @Override
    protected void addGuiElements() {
        super.addGuiElements();
        addRenderableWidget(new GuiDownArrow(this, 159, 44));
        addRenderableWidget(new GuiFluidGauge(tile::getFluidTank, () -> tile.getFluidTanks(null),
                GaugeType.STANDARD, this, 133, 13));
        addRenderableWidget(new GuiEnergyGauge(tile.getEnergyContainer(),
                GaugeType.STANDARD, this, 25, 13));
        addRenderableWidget(new GuiProgress(new IBooleanProgressInfoHandler() {
            @Override
            public boolean fillProgressBar() {
                return tile.getActive();
            }

            @Override
            public boolean isActive() {
                return !tile.getMode();
            }
        }, ProgressType.LARGE_RIGHT, this, 64, 39))
                .recipeViewerCategories(new IRecipeViewerRecipeType[] { MekInRecipeViewerRecipeType.FLUX_CONDENSER });
        addRenderableWidget(new GuiProgress(new IBooleanProgressInfoHandler() {
            @Override
            public boolean fillProgressBar() {
                return tile.getActive();
            }

            @Override
            public boolean isActive() {
                return tile.getMode();
            }
        }, ProgressType.LARGE_LEFT, this, 64, 39))
                .recipeViewerCategories(new IRecipeViewerRecipeType[] { MekInRecipeViewerRecipeType.FLUX_CONDENSER });
        addRenderableWidget(new ToggleButton(this, 4, 4, tile::getMode,
                (element, mouseX, mouseY) -> PacketUtils
                        .sendToServer(new PacketGuiInteract(GuiInteraction.NEXT_MODE, tile))))
                .setTooltip(MekanismLang.CONDENSENTRATOR_TOGGLE);
    }

    @Override
    protected void drawForegroundText(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY) {
        renderTitleText(guiGraphics);
        ILangEntry modeLang = tile.getMode() ? MekanismLang.DECONDENSENTRATING : MekanismLang.CONDENSENTRATING;
        drawScrollingString(guiGraphics, modeLang.translate(), 4, imageHeight - 92, TextAlignment.LEFT,
                titleTextColor(), 111, 2, false);
        super.drawForegroundText(guiGraphics, mouseX, mouseY);
    }

}
