package com.takenokoshi.mekin.recipe_viewer.jei.category;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.Codec;
import com.takenokoshi.mekin.recipe_viewer.recipe.FluxCondenserRVRecipe;

import mekanism.api.text.TextComponentUtil;
import mekanism.client.gui.element.gauge.GaugeType;
import mekanism.client.gui.element.gauge.GuiEnergyGauge;
import mekanism.client.gui.element.gauge.GuiFluidGauge;
import mekanism.client.gui.element.gauge.GuiGauge;
import mekanism.client.gui.element.progress.ProgressType;
import mekanism.client.gui.element.slot.SlotType;
import mekanism.client.recipe_viewer.jei.BaseRecipeCategory;
import mekanism.client.recipe_viewer.type.IRecipeViewerRecipeType;
import mekanism.common.MekanismLang;
import mekanism.common.inventory.container.slot.SlotOverlay;
import mekanism.common.util.text.EnergyDisplay;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.ITooltipBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.ICodecHelper;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.IRecipeManager;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.resources.ResourceLocation;

public class FluxCondenserRecipeCategory extends BaseRecipeCategory<FluxCondenserRVRecipe> {

    private final GuiGauge<?> energyGauge;
    private final GuiGauge<?> fluidGauge;

    public FluxCondenserRecipeCategory(IGuiHelper helper,
            IRecipeViewerRecipeType<FluxCondenserRVRecipe> recipeType) {
        super(helper, recipeType);
        energyGauge = addElement(GuiEnergyGauge.getDummy(GaugeType.STANDARD, this, 25, 13));
        fluidGauge = addElement(GuiFluidGauge.getDummy(GaugeType.STANDARD, this, 133, 13));
        addSlot(SlotType.INPUT, 5, 25).with(SlotOverlay.POWER);
        addSlot(SlotType.OUTPUT, 5, 56).with(SlotOverlay.POWER);
        addSlot(SlotType.INPUT, 155, 25);
        addSlot(SlotType.OUTPUT, 155, 56);
        addConstantProgress(ProgressType.LARGE_RIGHT, 64, 39);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, FluxCondenserRVRecipe recipe, IFocusGroup focusGroup) {
        initFluid(builder, RecipeIngredientRole.OUTPUT, fluidGauge, List.of(recipe.output()));
    }

    @Override
    protected void renderElements(FluxCondenserRVRecipe recipe, IRecipeSlotsView recipeSlotsView,
            GuiGraphics guiGraphics, int x, int y) {
        super.renderElements(recipe, recipeSlotsView, guiGraphics, x, y);
        energyGauge.renderContents(guiGraphics);
    }

    @Override
    public void getTooltip(ITooltipBuilder tooltip, FluxCondenserRVRecipe recipe, IRecipeSlotsView recipeSlotsView,
            double mouseX, double mouseY) {
        if (energyGauge.isMouseOver(mouseX, mouseY)) {
            tooltip.add(EnergyDisplay.of(recipe.inputEnergy()).getTextComponent());
            if (Minecraft.getInstance().options.advancedItemTooltips || Screen.hasShiftDown()) {
                tooltip.add(TextComponentUtil.build(ChatFormatting.DARK_GRAY,
                        MekanismLang.JEI_RECIPE_ID.translate(recipe.id())));
            }
        }
    }

    @Override
    public Codec<FluxCondenserRVRecipe> getCodec(ICodecHelper codecHelper, IRecipeManager recipeManager) {
        return FluxCondenserRVRecipe.CODEC;
    }

    @Override
    public @Nullable ResourceLocation getRegistryName(FluxCondenserRVRecipe recipe) {
        return recipe.id();
    }

}
