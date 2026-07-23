package com.takenokoshi.mekin.recipe_viewer.jei.category;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import fr.iglee42.evolvedmekanism.client.bars.GuiCustomDynamicHorizontalRateBar;
import mekanism.api.recipes.ItemStackChemicalToItemStackRecipe;
import mekanism.client.gui.element.GuiInnerScreen;
import mekanism.client.gui.element.gauge.GaugeType;
import mekanism.client.gui.element.gauge.GuiChemicalGauge;
import mekanism.client.gui.element.gauge.GuiEnergyGauge;
import mekanism.client.gui.element.gauge.GuiGauge;
import mekanism.client.gui.element.slot.GuiSlot;
import mekanism.client.gui.element.slot.SlotType;
import mekanism.client.recipe_viewer.jei.HolderRecipeCategory;
import mekanism.client.recipe_viewer.type.IRecipeViewerRecipeType;
import mekanism.common.MekanismLang;
import mekanism.common.lib.Color;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.crafting.RecipeHolder;

public class TPSRecipeCategory extends HolderRecipeCategory<ItemStackChemicalToItemStackRecipe> {

    private final GuiGauge<?> inputGas;
    private final GuiSlot input;
    private final GuiSlot output;
    private final GuiCustomDynamicHorizontalRateBar bar;

    public TPSRecipeCategory(IGuiHelper helper,
            IRecipeViewerRecipeType<ItemStackChemicalToItemStackRecipe> recipeType) {
        super(helper, recipeType);
        inputGas = addElement(GuiChemicalGauge.getDummy(GaugeType.STANDARD, this, 6, 17));
        input = addSlot(SlotType.INPUT, 27, 40);
        output = addSlot(SlotType.OUTPUT, 132, 40);
        addElement(new GuiInnerScreen(this, 47, 17, 82, 60, () -> {
            List<Component> list = new ArrayList<>();
            list.add(MekanismLang.STATUS.translate(MekanismLang.ACTIVE));
            return list;
        }));
        addElement(new GuiEnergyGauge(new GuiEnergyGauge.IEnergyInfoHandler() {
            @Override
            public long getEnergy() {
                return 1;
            }

            @Override
            public long getMaxEnergy() {
                return 1;
            }
        }, GaugeType.STANDARD, this, 151, 17));
        bar = addElement(new GuiCustomDynamicHorizontalRateBar(this, getBarProgressTimer(), 6, 79, 160,
                c -> Color.WHITE));
    }

    @Override
    public void setRecipe(@NotNull IRecipeLayoutBuilder builder,
            RecipeHolder<ItemStackChemicalToItemStackRecipe> recipe, @NotNull IFocusGroup focusGroup) {
        initChemical(builder, RecipeIngredientRole.INPUT, inputGas,
                recipe.value().getChemicalInput().getRepresentations());
        initItem(builder, RecipeIngredientRole.INPUT, input, recipe.value().getItemInput().getRepresentations());
        initItem(builder, RecipeIngredientRole.OUTPUT, output, recipe.value().getOutputDefinition());
        bar.setColorFunction(
                p -> Color.rgb(recipe.value().getChemicalInput().getRepresentations().get(0).getChemicalTint()));
    }

}
