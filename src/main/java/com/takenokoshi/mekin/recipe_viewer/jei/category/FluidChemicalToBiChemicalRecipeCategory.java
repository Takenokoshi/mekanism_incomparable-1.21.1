package com.takenokoshi.mekin.recipe_viewer.jei.category;

import java.util.List;

import com.takenokoshi.mekin.recipe.recipes.prefab.FluidChemicalToBiChemicalRecipe;

import mekanism.client.gui.element.gauge.GaugeType;
import mekanism.client.gui.element.gauge.GuiChemicalGauge;
import mekanism.client.gui.element.gauge.GuiFluidGauge;
import mekanism.client.gui.element.gauge.GuiGauge;
import mekanism.client.gui.element.progress.ProgressType;
import mekanism.client.gui.element.slot.SlotType;
import mekanism.client.recipe_viewer.jei.HolderRecipeCategory;
import mekanism.client.recipe_viewer.type.IRecipeViewerRecipeType;
import mekanism.common.inventory.container.slot.SlotOverlay;
import mekanism.common.tile.component.config.DataType;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.world.item.crafting.RecipeHolder;

public class FluidChemicalToBiChemicalRecipeCategory extends HolderRecipeCategory<FluidChemicalToBiChemicalRecipe> {

    private final GuiGauge<?> fluidInput;
    private final GuiGauge<?> chemicalInput;
    private final GuiGauge<?> mainOutput;
    private final GuiGauge<?> subOutput;

    public FluidChemicalToBiChemicalRecipeCategory(IGuiHelper helper,
            IRecipeViewerRecipeType<FluidChemicalToBiChemicalRecipe> recipeType) {
        super(helper, recipeType);
        fluidInput = addElement(GuiFluidGauge.getDummy(GaugeType.STANDARD.with(DataType.INPUT), this, 7, 13));
        chemicalInput = addElement(GuiChemicalGauge.getDummy(GaugeType.STANDARD.with(DataType.INPUT), this, 28, 13));
        mainOutput = addElement(GuiChemicalGauge.getDummy(GaugeType.STANDARD.with(DataType.OUTPUT), this, 110, 13));
        subOutput = addElement(GuiChemicalGauge.getDummy(GaugeType.STANDARD.with(DataType.OUTPUT_2), this, 131, 13));
        addSlot(SlotType.POWER, 152, 14).with(SlotOverlay.POWER);
        addSlot(SlotType.OUTPUT, 152, 56).with(SlotOverlay.MINUS);
        addConstantProgress(ProgressType.LARGE_RIGHT, 54, 39);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<FluidChemicalToBiChemicalRecipe> recipeHolder,
            IFocusGroup focusGroup) {
        var recipe = recipeHolder.value();
        initFluid(builder, RecipeIngredientRole.INPUT, fluidInput, recipe.fluidInput.getRepresentations());
        initChemical(builder, RecipeIngredientRole.INPUT, chemicalInput, recipe.chemicalInput.getRepresentations());
        initChemical(builder, RecipeIngredientRole.OUTPUT, mainOutput, List.of(recipe.mainOutput));
        initChemical(builder, RecipeIngredientRole.OUTPUT, subOutput, List.of(recipe.subOutput));
    }

}
