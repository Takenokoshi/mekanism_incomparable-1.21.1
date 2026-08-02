package com.takenokoshi.mekin.recipe_viewer.jei.category;

import com.takenokoshi.mekin.recipe.recipes.prefab.LightningFabricationRecipe;

import mekanism.client.gui.element.GuiUpArrow;
import mekanism.client.gui.element.bar.GuiVerticalPowerBar;
import mekanism.client.gui.element.gauge.GaugeType;
import mekanism.client.gui.element.gauge.GuiChemicalGauge;
import mekanism.client.gui.element.gauge.GuiGauge;
import mekanism.client.gui.element.progress.ProgressType;
import mekanism.client.gui.element.slot.GuiSlot;
import mekanism.client.gui.element.slot.SlotType;
import mekanism.client.recipe_viewer.jei.HolderRecipeCategory;
import mekanism.client.recipe_viewer.type.IRecipeViewerRecipeType;
import mekanism.common.inventory.container.slot.SlotOverlay;
import mekanism.common.tile.component.config.DataType;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.crafting.RecipeHolder;

import static mekanism.client.recipe_viewer.RecipeViewerUtils.FULL_BAR;

import java.util.List;

public class LightningFabricationRecipeCategory extends HolderRecipeCategory<LightningFabricationRecipe> {

    private static final String INPUT_CHEMICAL = "input_chemical";

    private final GuiGauge<?> inputGas;
    private final GuiSlot input;
    private final GuiSlot extra;
    private final GuiSlot secondExtra;
    private final GuiSlot output;

    public LightningFabricationRecipeCategory(IGuiHelper helper,
            IRecipeViewerRecipeType<LightningFabricationRecipe> recipeType) {
        super(helper, recipeType);
        addElement(new GuiUpArrow(this, 68, 38));
        input = addSlot(SlotType.INPUT, 64, 17);
        extra = addSlot(SlotType.EXTRA, 55, 53);
        secondExtra = addSlot(SlotType.EXTRA, 75, 53);
        output = addSlot(SlotType.OUTPUT, 116, 35);
        inputGas = addElement(GuiChemicalGauge.getDummy(GaugeType.SMALL.with(DataType.INPUT), this, 28, 22));
        addSlot(SlotType.NORMAL, 29, 53);
        addSlot(SlotType.POWER, 141, 35).with(SlotOverlay.POWER);
        addElement(new GuiVerticalPowerBar(this, FULL_BAR, 164, 15));
        addSimpleProgress(ProgressType.BAR, 86, 38);
    }

    @Override
    protected void renderElements(RecipeHolder<LightningFabricationRecipe> recipe, IRecipeSlotsView recipeSlotsView,
            GuiGraphics guiGraphics, int x, int y) {
        super.renderElements(recipe, recipeSlotsView, guiGraphics, x, y);
        if (recipeSlotsView.findSlotByName(INPUT_CHEMICAL).isEmpty()) {
            inputGas.drawBarOverlay(guiGraphics);
        }
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<LightningFabricationRecipe> holder,
            IFocusGroup focusGroup) {
        LightningFabricationRecipe recipe = holder.value();
        initItem(builder, RecipeIngredientRole.INPUT, input, recipe.mainInput.getRepresentations());
        if (recipe.extraInputs.size() > 0) {
            initItem(builder, RecipeIngredientRole.INPUT, extra, recipe.extraInputs.get(0).getRepresentations());
        }
        if (recipe.extraInputs.size() > 1) {
            initItem(builder, RecipeIngredientRole.INPUT, secondExtra, recipe.extraInputs.get(1).getRepresentations());
        }
        if (recipe.chemicalInput != null) {
            initChemical(builder, RecipeIngredientRole.INPUT, inputGas, recipe.chemicalInput.getRepresentations());
        }
        initItem(builder, RecipeIngredientRole.OUTPUT, output, List.of(recipe.output));
    }

}
