package com.takenokoshi.mekin.gui.machine;

import org.jetbrains.annotations.NotNull;

import com.takenokoshi.mekaddonlib.inventory.container.MekALDynamicSizedContainer;
import com.takenokoshi.mekin.blockentity.abs.BEAbstractLightningTransformer;
import com.takenokoshi.mekin.recipe_viewer.type.MekInRecipeViewerRecipeType;

import mekanism.api.recipes.cache.CachedRecipe.OperationTracker.RecipeError;
import mekanism.client.gui.GuiConfigurableTile;
import mekanism.client.gui.element.bar.GuiVerticalPowerBar;
import mekanism.client.gui.element.gauge.GaugeType;
import mekanism.client.gui.element.gauge.GuiChemicalGauge;
import mekanism.client.gui.element.progress.GuiProgress;
import mekanism.client.gui.element.progress.ProgressType;
import mekanism.client.gui.element.tab.GuiEnergyTab;
import mekanism.client.recipe_viewer.type.IRecipeViewerRecipeType;
import mekanism.common.inventory.warning.WarningTracker.WarningType;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class GuiLightningTransformer<BE extends BEAbstractLightningTransformer>
        extends GuiConfigurableTile<BE, MekALDynamicSizedContainer<BE>> {

    public GuiLightningTransformer(MekALDynamicSizedContainer<BE> container, Inventory inv, Component title) {
        super(container, inv, title);
        dynamicSlots = true;
        int extraWidth = tile.getExtraWidth();
        imageWidth += extraWidth;
        inventoryLabelX += extraWidth / 2;
        int extraHeight = tile.getExtraHeight();
        imageHeight += extraHeight;
        inventoryLabelY += extraHeight;
    }

    @Override
    protected void addGuiElements() {
        super.addGuiElements();
        addRenderableWidget(new GuiEnergyTab(this, tile.getEnergyContainer(), tile::getEnergyUsed));
        addRenderableWidget(new GuiChemicalGauge(tile::getChemicalTank, () -> tile.getChemicalTanks(null),
                GaugeType.SMALL, this, 5, 27)).warning(WarningType.NO_MATCHING_RECIPE,
                        tile.getWarningCheck(RecipeError.NOT_ENOUGH_SECONDARY_INPUT));
        addRenderableWidget(new GuiVerticalPowerBar(this, tile.getEnergyContainer(), 176, 21))
                .warning(WarningType.NOT_ENOUGH_ENERGY, tile.getWarningCheck(RecipeError.NOT_ENOUGH_ENERGY));
        addRenderableWidget(new GuiProgress(tile::getScaledProgress, ProgressType.RIGHT, this, 90, 43)).warning(
                WarningType.INPUT_DOESNT_PRODUCE_OUTPUT, tile.getWarningCheck(RecipeError.INPUT_DOESNT_PRODUCE_OUTPUT))
                .recipeViewerCategories(
                        new IRecipeViewerRecipeType[] { MekInRecipeViewerRecipeType.LIGHTNING_TRANSFORM, });
    }

    @Override
    protected void drawForegroundText(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY) {
        renderTitleText(guiGraphics);
        renderInventoryText(guiGraphics);
        super.drawForegroundText(guiGraphics, mouseX, mouseY);
    }

}
