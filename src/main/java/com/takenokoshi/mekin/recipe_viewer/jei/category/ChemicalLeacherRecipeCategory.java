package com.takenokoshi.mekin.recipe_viewer.jei.category;

import com.takenokoshi.mekin.recipe.recipes.prefab.ItemStackFluidChemicalToItemStackRecipe;

import mekanism.client.gui.element.bar.GuiVerticalPowerBar;
import mekanism.client.gui.element.gauge.GaugeType;
import mekanism.client.gui.element.gauge.GuiChemicalGauge;
import mekanism.client.gui.element.gauge.GuiFluidGauge;
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

public class ChemicalLeacherRecipeCategory extends HolderRecipeCategory<ItemStackFluidChemicalToItemStackRecipe> {

    private final GuiGauge<?> inputChemical;
    private final GuiGauge<?> inputFluid;
    private final GuiSlot inputItem;
    private final GuiSlot outputItem;

    public ChemicalLeacherRecipeCategory(IGuiHelper helper,
            IRecipeViewerRecipeType<ItemStackFluidChemicalToItemStackRecipe> recipeType) {
        super(helper, recipeType);
        inputItem = addSlot(SlotType.INPUT, 54, 40);
        outputItem = addSlot(SlotType.OUTPUT, 116, 40);
        addSlot(SlotType.POWER, 141, 22).with(SlotOverlay.POWER);
        inputFluid = addElement(GuiFluidGauge.getDummy(GaugeType.STANDARD.with(DataType.INPUT), this, 5, 15));
        GaugeType type1 = GaugeType.STANDARD.with(DataType.INPUT);
        inputChemical = addElement(GuiChemicalGauge.getDummy(type1, this, 28, 15));
        addElement(new GuiVerticalPowerBar(this, RecipeViewerUtils.FULL_BAR, 164, 21));
        addSimpleProgress(ProgressType.RIGHT, 77, 43);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder,
            RecipeHolder<ItemStackFluidChemicalToItemStackRecipe> recipeHolder,
            IFocusGroup focusGroup) {
        ItemStackFluidChemicalToItemStackRecipe recipe = recipeHolder.value();
        initItem(builder, RecipeIngredientRole.INPUT, inputItem, recipe.itemInput.getRepresentations());
        initFluid(builder, RecipeIngredientRole.INPUT, inputFluid, recipe.fluidInput.getRepresentations());
        initChemical(builder, RecipeIngredientRole.INPUT, inputChemical, recipe.chemicalInput.getRepresentations());
        initItem(builder, RecipeIngredientRole.OUTPUT, outputItem, recipe.getOutputDefinition());
    }

}
