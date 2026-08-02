package com.takenokoshi.mekin.recipe_viewer.jei.category;

import java.util.List;

import com.moakiee.ae2lt.lightning.LightningTransformRecipe;
import com.takenokoshi.mekin.recipe.MekInIngredientUtils;

import mekanism.api.recipes.ingredients.ItemStackIngredient;
import mekanism.client.gui.element.bar.GuiVerticalPowerBar;
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

import static com.takenokoshi.mekin.recipe.MekInRecipeConstants.LIGHTNING_INGREDIENT;

public class LightningTransformRecipeCategory extends HolderRecipeCategory<LightningTransformRecipe> {

    private final GuiGauge<?> inputChemical;
    private final GuiSlot[] inputItems;
    private final GuiSlot outputItem;

    public LightningTransformRecipeCategory(IGuiHelper helper,
            IRecipeViewerRecipeType<LightningTransformRecipe> recipeType) {
        super(helper, recipeType);
        GaugeType type1 = GaugeType.SMALL.with(DataType.INPUT);
        inputChemical = addElement(GuiChemicalGauge.getDummy(type1, this, 5, 27));
        inputItems = new GuiSlot[9];
        for (int index = 0; index < inputItems.length; index++) {
            inputItems[index] = addSlot(SlotType.INPUT, 31 + index % 3 * 18, 22 + index / 3 * 18);
        }
        outputItem = addSlot(SlotType.OUTPUT, 128, 40);
        addElement(new GuiVerticalPowerBar(this, RecipeViewerUtils.FULL_BAR, 176, 21));
        addSimpleProgress(ProgressType.RIGHT, 90, 43);
        addSlot(SlotType.POWER, 154, 22).with(SlotOverlay.POWER);
        addSlot(SlotType.EXTRA, 6, 58).with(SlotOverlay.MINUS);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<LightningTransformRecipe> holder,
            IFocusGroup group) {
        LightningTransformRecipe recipe = holder.value();
        List<ItemStackIngredient> ingredients = recipe.inputs().stream().map(MekInIngredientUtils::convert).toList();
        for (int i = 0; i < recipe.ingredientCount(); i++) {
            initItem(builder, RecipeIngredientRole.INPUT, inputItems[i], ingredients.get(i).getRepresentations());
        }
        initChemical(builder, RecipeIngredientRole.INPUT, inputChemical, LIGHTNING_INGREDIENT.getRepresentations());
        initItem(builder, RecipeIngredientRole.OUTPUT, outputItem, List.of(recipe.getResultItem(null)));
    }

}
