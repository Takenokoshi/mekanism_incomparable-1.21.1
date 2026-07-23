package com.takenokoshi.mekin.recipe_viewer.jei.category;

import com.takenokoshi.mekin.recipe.recipes.prefab.ItemStackChemicalToChemicalRecipe;

import mekanism.client.gui.element.bar.GuiHorizontalPowerBar;
import mekanism.client.gui.element.gauge.GaugeType;
import mekanism.client.gui.element.gauge.GuiChemicalGauge;
import mekanism.client.gui.element.gauge.GuiGauge;
import mekanism.client.gui.element.progress.ProgressType;
import mekanism.client.gui.element.slot.GuiSlot;
import mekanism.client.gui.element.slot.SlotType;
import mekanism.client.recipe_viewer.RecipeViewerUtils;
import mekanism.client.recipe_viewer.jei.HolderRecipeCategory;
import mekanism.client.recipe_viewer.type.IRecipeViewerRecipeType;
import mekanism.common.inventory.container.slot.SlotOverlay;
import mekanism.common.tile.component.config.DataType;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.world.item.crafting.RecipeHolder;

public class ItemStackChemicalToChemicalRecipeCategory extends HolderRecipeCategory<ItemStackChemicalToChemicalRecipe> {

    private final GuiGauge<?> inputGauge;
    private final GuiGauge<?> outputGauge;
    private final GuiSlot inputSlot;

    public ItemStackChemicalToChemicalRecipeCategory(IGuiHelper helper,
            IRecipeViewerRecipeType<ItemStackChemicalToChemicalRecipe> recipeType) {
        super(helper, recipeType);
        GaugeType type1 = GaugeType.STANDARD.with(DataType.INPUT);
        inputGauge = addElement(GuiChemicalGauge.getDummy(type1, this, 7, 4));
        GaugeType type = GaugeType.STANDARD.with(DataType.OUTPUT);
        outputGauge = addElement(GuiChemicalGauge.getDummy(type, this, 131, 13));
        inputSlot = addSlot(SlotType.INPUT, 28, 36);
        addSlot(SlotType.EXTRA, 8, 65).with(SlotOverlay.MINUS);
        addSlot(SlotType.OUTPUT, 152, 55).with(SlotOverlay.PLUS);
        addSlot(SlotType.POWER, 152, 14).with(SlotOverlay.POWER);
        addSimpleProgress(ProgressType.LARGE_RIGHT, 64, 40);
        addElement(new GuiHorizontalPowerBar(this, RecipeViewerUtils.FULL_BAR, 115, 75));
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<ItemStackChemicalToChemicalRecipe> holder,
            IFocusGroup focusGroup) {
        ItemStackChemicalToChemicalRecipe recipe = holder.value();
        initItem(builder, RecipeIngredientRole.INPUT, inputSlot, recipe.itemInput.getRepresentations());
        initChemical(builder, RecipeIngredientRole.INPUT, inputGauge, recipe.chemicalInput.getRepresentations());
        initChemical(builder, RecipeIngredientRole.OUTPUT, outputGauge, recipe.getOutputDefinition());
    }

}
