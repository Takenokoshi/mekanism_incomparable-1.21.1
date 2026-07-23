package com.takenokoshi.mekin.gui.machine;

import org.jetbrains.annotations.NotNull;

import com.takenokoshi.mekaddonlib.inventory.container.MekALDynamicSizedContainer;
import com.takenokoshi.mekin.blockentity.abs.BEAbstractChemicalLeachingChamber;

import mekanism.api.recipes.cache.CachedRecipe.OperationTracker.RecipeError;
import mekanism.client.SpecialColors;
import mekanism.client.gui.GuiConfigurableTile;
import mekanism.client.gui.element.GuiDownArrow;
import mekanism.client.gui.element.GuiSideHolder;
import mekanism.client.gui.element.bar.GuiVerticalPowerBar;
import mekanism.client.gui.element.gauge.GaugeType;
import mekanism.client.gui.element.gauge.GuiChemicalGauge;
import mekanism.client.gui.element.gauge.GuiFluidGauge;
import mekanism.client.gui.element.progress.GuiProgress;
import mekanism.client.gui.element.progress.ProgressType;
import mekanism.client.gui.element.tab.GuiEnergyTab;
import mekanism.client.recipe_viewer.type.IRecipeViewerRecipeType;
import mekanism.common.inventory.warning.WarningTracker.WarningType;
import mekanism.common.tile.machine.TileEntityPressurizedReactionChamber;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class GuiChemicalLeacher<BE extends BEAbstractChemicalLeachingChamber>
        extends GuiConfigurableTile<BE, MekALDynamicSizedContainer<BE>> {

    public GuiChemicalLeacher(MekALDynamicSizedContainer<BE> container, Inventory inv, Component title) {
        super(container, inv, title);
        dynamicSlots = true;
        imageHeight += tile.getExtraHeight();
        inventoryLabelY += tile.getExtraHeight();
    }

    @Override
    protected void addGuiElements() {
        addRenderableWidget(
                GuiSideHolder.create(this, imageWidth, 66, 57, false, true, SpecialColors.TAB_CHEMICAL_WASHER));
        super.addGuiElements();
        addRenderableWidget(new GuiDownArrow(this, imageWidth + 8, 91));
        addRenderableWidget(new GuiEnergyTab(this, tile.getEnergyContainer(), tile::getActive));
        addRenderableWidget(new GuiFluidGauge(tile::getInputFluidTank, () -> tile.getFluidTanks(null),
                GaugeType.STANDARD, this, 5, 15)
                .warning(WarningType.NO_MATCHING_RECIPE,
                        tile.getWarningCheck(TileEntityPressurizedReactionChamber.NOT_ENOUGH_FLUID_INPUT_ERROR)));
        addRenderableWidget(new GuiChemicalGauge(tile::getInputChemicalTank, () -> tile.getChemicalTanks(null),
                GaugeType.SMALL, this, 28, 27)
                .warning(WarningType.NO_MATCHING_RECIPE,
                        tile.getWarningCheck(TileEntityPressurizedReactionChamber.NOT_ENOUGH_CHEMICAL_INPUT_ERROR)));
        addRenderableWidget(new GuiVerticalPowerBar(this, tile.getEnergyContainer(), 163, 21)
                .warning(WarningType.NOT_ENOUGH_ENERGY, tile.getWarningCheck(RecipeError.NOT_ENOUGH_ENERGY)));
        addRenderableWidget(new GuiProgress(tile::getScaledProgress, ProgressType.RIGHT, this, 77, 43))
                .warning(WarningType.INPUT_DOESNT_PRODUCE_OUTPUT,
                        tile.getWarningCheck(RecipeError.INPUT_DOESNT_PRODUCE_OUTPUT))
                .recipeViewerCategories(new IRecipeViewerRecipeType[] { tile.recipeViewerType() });
    }

    @Override
    protected void drawForegroundText(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY) {
        renderTitleText(guiGraphics);
        renderInventoryText(guiGraphics);
        super.drawForegroundText(guiGraphics, mouseX, mouseY);
    }

}
