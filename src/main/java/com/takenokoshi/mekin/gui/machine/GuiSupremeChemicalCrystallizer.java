package com.takenokoshi.mekin.gui.machine;

import java.lang.ref.WeakReference;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.takenokoshi.mekin.blockentity.abs.BEAbstractChemicalCrystallizer;

import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.ChemicalCrystallizerRecipe;
import mekanism.api.recipes.cache.CachedRecipe.OperationTracker.RecipeError;
import mekanism.client.gui.GuiConfigurableTile;
import mekanism.client.gui.element.GuiElement;
import mekanism.client.gui.element.bar.GuiVerticalPowerBar;
import mekanism.client.gui.element.custom.GuiQIOCrystallizerScreen;
import mekanism.client.gui.element.custom.GuiQIOCrystallizerScreen.IOreInfo;
import mekanism.client.gui.element.gauge.GaugeType;
import mekanism.client.gui.element.gauge.GuiChemicalGauge;
import mekanism.client.gui.element.progress.GuiProgress;
import mekanism.client.gui.element.progress.ProgressType;
import mekanism.client.gui.element.tab.GuiEnergyTab;
import mekanism.client.recipe_viewer.type.IRecipeViewerRecipeType;
import mekanism.client.recipe_viewer.type.RecipeViewerRecipeType;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import mekanism.common.inventory.warning.WarningTracker.WarningType;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class GuiSupremeChemicalCrystallizer<BE extends BEAbstractChemicalCrystallizer>
        extends GuiConfigurableTile<BE, MekanismTileContainer<BE>> {

    private final IOreInfo oreInfo = new OreInfo();
    private GuiElement inputGauge;

    public GuiSupremeChemicalCrystallizer(MekanismTileContainer<BE> container, Inventory inv, Component title) {
        super(container, inv, title);
        dynamicSlots = true;
        titleLabelY = 4;
    }

    @Override
    protected void addGuiElements() {
        super.addGuiElements();
        addRenderableWidget(new GuiVerticalPowerBar(this, tile.getEnergyContainer(), 157, 23))
                .warning(WarningType.NOT_ENOUGH_ENERGY, tile.getWarningCheck(RecipeError.NOT_ENOUGH_ENERGY));
        addRenderableWidget(new GuiEnergyTab(this, tile.getEnergyContainer(), tile::getEnergyUsed));
        inputGauge = addRenderableWidget(new GuiChemicalGauge(() -> tile.inputTank, () -> tile.getChemicalTanks(null),
                GaugeType.STANDARD, this, 7, 4))
                .warning(WarningType.NO_MATCHING_RECIPE, tile.getWarningCheck(RecipeError.NOT_ENOUGH_INPUT));
        addRenderableWidget(new GuiProgress(tile::getScaledProgress, ProgressType.LARGE_RIGHT, this, 53, 61))
                .warning(WarningType.INPUT_DOESNT_PRODUCE_OUTPUT,
                        tile.getWarningCheck(RecipeError.INPUT_DOESNT_PRODUCE_OUTPUT))
                .recipeViewerCategories(new IRecipeViewerRecipeType[] { RecipeViewerRecipeType.CRYSTALLIZING });
        addRenderableWidget(new GuiQIOCrystallizerScreen(this, 31, 13, 115, 42, oreInfo));
    }

    @Override
    protected void drawForegroundText(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY) {
        renderTitleTextWithOffset(guiGraphics, inputGauge.getRelativeRight(), tile.getEnergySlotX());
        super.drawForegroundText(guiGraphics, mouseX, mouseY);
    }

    private class OreInfo implements IOreInfo {

        private WeakReference<ChemicalCrystallizerRecipe> cachedRecipe;

        @NotNull
        @Override
        public ChemicalStack getInputChemical() {
            return tile.inputTank.getStack();
        }

        @Nullable
        @Override
        public ChemicalCrystallizerRecipe getRecipe() {
            ChemicalStack input = getInputChemical();
            if (input.isEmpty()) {
                return null;
            }
            ChemicalCrystallizerRecipe recipe;
            if (cachedRecipe == null) {
                recipe = getRecipeAndCache();
            } else {
                recipe = cachedRecipe.get();
                if (recipe == null || !recipe.testType(input)) {
                    recipe = getRecipeAndCache();
                }
            }
            return recipe;
        }

        private ChemicalCrystallizerRecipe getRecipeAndCache() {
            ChemicalCrystallizerRecipe recipe = tile.getRecipe(0);
            if (recipe == null) {
                cachedRecipe = null;
            } else {
                cachedRecipe = new WeakReference<>(recipe);
            }
            return recipe;
        }
    }
}