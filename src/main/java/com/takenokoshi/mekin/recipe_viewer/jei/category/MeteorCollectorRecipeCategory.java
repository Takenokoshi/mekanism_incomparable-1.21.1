package com.takenokoshi.mekin.recipe_viewer.jei.category;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.Codec;
import com.takenokoshi.mekin.recipe_viewer.recipe.MeteorCollectorRVRecipe;

import fr.iglee42.evolvedmekanism.EvolvedMekanismLang;
import mekanism.api.text.EnumColor;
import mekanism.client.gui.element.GuiUpArrow;
import mekanism.client.gui.element.bar.GuiVerticalPowerBar;
import mekanism.client.gui.element.progress.GuiProgress;
import mekanism.client.gui.element.progress.ProgressType;
import mekanism.client.gui.element.slot.GuiSlot;
import mekanism.client.gui.element.slot.SlotType;
import mekanism.client.recipe_viewer.RecipeViewerUtils;
import mekanism.client.recipe_viewer.jei.BaseRecipeCategory;
import mekanism.client.recipe_viewer.type.IRecipeViewerRecipeType;
import mekanism.common.inventory.container.slot.SlotOverlay;
import mekanism.common.util.text.TextUtils;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.placement.HorizontalAlignment;
import mezz.jei.api.gui.widgets.IRecipeExtrasBuilder;
import mezz.jei.api.helpers.ICodecHelper;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.IRecipeManager;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class MeteorCollectorRecipeCategory extends BaseRecipeCategory<MeteorCollectorRVRecipe> {

    private final GuiSlot input;
    private final GuiSlot catalyst;
    private final GuiSlot[] outputs;

    private final GuiProgress progress;

    public MeteorCollectorRecipeCategory(IGuiHelper helper,
            IRecipeViewerRecipeType<MeteorCollectorRVRecipe> recipeType) {
        super(helper, recipeType);
        input = addSlot(SlotType.INPUT, 46, 17);
        catalyst = addSlot(SlotType.NORMAL, 46, 53);
        outputs = new GuiSlot[9];
        for (int i = 0; i < outputs.length; i++) {
            outputs[i] = addSlot(SlotType.OUTPUT, 98 + 18 * (i % 3), 17 + 18 * (i / 3));
        }
        addSlot(SlotType.POWER, 21, 35).with(SlotOverlay.POWER);
        addElement(new GuiVerticalPowerBar(this, RecipeViewerUtils.FULL_BAR, 164, 16));
        progress = addSimpleProgress(ProgressType.BAR, 68, 38);
        addElement(new GuiUpArrow(this, 50, 38));
    }

    @Override
    public void createRecipeExtras(IRecipeExtrasBuilder builder, MeteorCollectorRVRecipe recipe, IFocusGroup focuses) {
        super.createRecipeExtras(builder, recipe, focuses);
        builder.addText(TextUtils.getPercent(recipe.chance()), progress.getWidth(), font().lineHeight)
                .setPosition(
                        getGuiLeft() + progress.getRelativeX() + 1,
                        getGuiTop() + progress.getRelativeBottom() + 2)
                .setTextAlignment(HorizontalAlignment.RIGHT)
                .setColor(titleTextColor());
        if (recipe.requireAdvanced()) {
            builder.addText(Component.literal("Advanced").withColor(EnumColor.DARK_BLUE.getPackedColor()),
                    progress.getWidth(), font().lineHeight)
                    .setPosition(
                            getGuiLeft() + progress.getRelativeX() + 1,
                            getGuiTop() + progress.getRelativeBottom() + font().lineHeight + 4)
                    .setTextAlignment(HorizontalAlignment.RIGHT)
                    .setColor(titleTextColor());
        }
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, MeteorCollectorRVRecipe recipe, IFocusGroup focusGroup) {
        initItem(builder, RecipeIngredientRole.INPUT, input, recipe.input().getRepresentations());
        initItem(builder, RecipeIngredientRole.CATALYST, catalyst, recipe.catalyst().getRepresentations())
                .addRichTooltipCallback((recipeSlotView, tooltip) -> {
                    tooltip.add(EvolvedMekanismLang.TOOLTIP_NO_CONSUMED.translateColored(EnumColor.YELLOW));
                });
        for (int i = 0; i < Math.min(outputs.length, recipe.output().size()); i++) {
            initItem(builder, RecipeIngredientRole.OUTPUT, outputs[i], List.of(recipe.output().get(i)));
        }
        ResourceKey<Level> dimension = recipe.dimension();
        initItem(builder, RecipeIngredientRole.RENDER_ONLY, input.getX() + 26, input.getY(),
                List.of(getDimensionStack(dimension)))
                .addRichTooltipCallback((recipeSlotView, tooltip) -> {
                    tooltip.add(Component.literal("Executable Dimension:"));
                    tooltip.add(Component.translatable(dimension.location().toLanguageKey("dimension")));
                });
    }

    private static ItemStack getDimensionStack(ResourceKey<Level> dimension) {
        ItemStack dimensionStack = new ItemStack(Items.BEDROCK);
        if (dimension.equals(Level.OVERWORLD)) {
            dimensionStack = new ItemStack(Items.GRASS_BLOCK);
        } else if (dimension.equals(Level.NETHER)) {
            dimensionStack = new ItemStack(Items.NETHERRACK);
        } else if (dimension.equals(Level.END)) {
            dimensionStack = new ItemStack(Items.END_STONE);
        }
        return dimensionStack;
    }

    @Override
    public Codec<MeteorCollectorRVRecipe> getCodec(ICodecHelper codecHelper, IRecipeManager recipeManager) {
        return MeteorCollectorRVRecipe.CODEC;
    }

    @Override
    public @Nullable ResourceLocation getRegistryName(MeteorCollectorRVRecipe recipe) {
        return recipe.id();
    }

}
