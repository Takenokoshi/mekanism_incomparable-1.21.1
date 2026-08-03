package com.takenokoshi.mekin.recipe.building;

import java.util.function.Function;

import com.fxd927.mekanismelements.common.registries.MSItems;
import com.takenokoshi.mekin.core.MekInConstants;
import com.takenokoshi.mekin.registries.MekInItems;
import com.takenokoshi.mekut.registries.MekUtItems;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.level.ItemLike;

public class StarlightRecipes {
    public static void buildRecipes(RecipeOutput output,
            Function<ItemLike, Criterion<InventoryChangeTrigger.TriggerInstance>> has) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MekInItems.SUPER_QUARTZ_STARLIGHT)
                .define('a', MekInItems.SILICON_STARLIGHT)
                .define('c', MekInItems.CERTUS_QUARTZ_STARLIGHT)
                .define('l', MekInItems.AMETHYST_STARLIGHT)
                .define('r', MekInItems.QUARTZ_STARLIGHT)
                .define('t', MekInItems.OVERLOAD_STARLIGHT)
                .define('b', MekInItems.ENTRO_STARLIGHT)
                .pattern("ata")
                .pattern("lcr")
                .pattern("aba")
                .unlockedBy("unlock", has.apply(MekInItems.CERTUS_QUARTZ_STARLIGHT))
                .save(output, MekInConstants.rl("starlight/super_quartz"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MekInItems.RADIOACTIVE_STARLIGHT)
                .define('a', MSItems.NEUTRON_SOURCE_PELLET)
                .define('c', MekInItems.FLUORITE_STARLIGHT)
                .define('l', MekInItems.URANIUM_STARLIGHT)
                .define('r', MekInItems.URANIUM_STARLIGHT)
                .define('t', MekInItems.NAQUADAH_STARLIGHT)
                .define('b', MekInItems.NAQUADAH_STARLIGHT)
                .pattern("ata")
                .pattern("lcr")
                .pattern("aba")
                .unlockedBy("unlock", has.apply(MekInItems.FLUORITE_STARLIGHT))
                .save(output, MekInConstants.rl("starlight/radioactive"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MekInItems.HEAVY_METAL_STARLIGHT)
                .define('a', MekInItems.OSMIUM_STARLIGHT)
                .define('c', MekInItems.OSMIUM_STARLIGHT)
                .define('l', MekInItems.GOLD_STARLIGHT)
                .define('r', MekInItems.NETHERITE_STARLIGHT)
                .define('t', MekInItems.IRIDIUM_STARLIGHT)
                .define('b', MekInItems.LEAD_STARLIGHT)
                .pattern("ata")
                .pattern("lcr")
                .pattern("aba")
                .unlockedBy("unlock", has.apply(MekInItems.OSMIUM_STARLIGHT))
                .save(output, MekInConstants.rl("starlight/heavy_metal"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MekInItems.INDUSTRIAL_STARLIGHT)
                .define('a', MekInItems.IRON_STARLIGHT)
                .define('c', MekInItems.IRON_STARLIGHT)
                .define('l', MekInItems.SILVER_STARLIGHT)
                .define('r', MekInItems.TITANIUM_STARLIGHT)
                .define('t', MekInItems.COPPER_STARLIGHT)
                .define('b', MekInItems.TIN_STARLIGHT)
                .pattern("ata")
                .pattern("lcr")
                .pattern("aba")
                .unlockedBy("unlock", has.apply(MekInItems.IRON_STARLIGHT))
                .save(output, MekInConstants.rl("starlight/industrial"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MekInItems.ENERGITIC_STARLIGHT)
                .define('r', MekInItems.REDSTONE_STARLIGHT)
                .define('c', MekInItems.COAL_STARLIGHT)
                .pattern("rcr")
                .pattern("crc")
                .pattern("rcr")
                .unlockedBy("unlock", has.apply(MekInItems.REDSTONE_STARLIGHT))
                .save(output, MekInConstants.rl("starlight/energitic"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MekInItems.JEWELRY_STARLIGHT)
                .define('a', MekUtItems.XP_CRYSTAL)
                .define('c', MekInItems.LAPIS_LAZULI_STARLIGHT)
                .define('l', MekInItems.DIAMOND_STARLIGHT)
                .define('r', MekInItems.DIAMOND_STARLIGHT)
                .define('t', MekInItems.EMERALD_STARLIGHT)
                .define('b', MekInItems.EMERALD_STARLIGHT)
                .pattern("ata")
                .pattern("lcr")
                .pattern("aba")
                .unlockedBy("unlock", has.apply(MekInItems.LAPIS_LAZULI_STARLIGHT))
                .save(output, MekInConstants.rl("starlight/jewelry"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MekInItems.ETERNAL_STARLIGHT)
                .define('i', MekInItems.INDUSTRIAL_STARLIGHT)
                .define('r', MekInItems.RADIOACTIVE_STARLIGHT)
                .define('s', MekInItems.SUPER_QUARTZ_STARLIGHT)
                .define('e', MekInItems.ENERGITIC_STARLIGHT)
                .define('j', MekInItems.JEWELRY_STARLIGHT)
                .define('h', MekInItems.HEAVY_METAL_STARLIGHT)
                .pattern("iri")
                .pattern("sej")
                .pattern("hih")
                .unlockedBy("unlock", has.apply(MekInItems.ENERGITIC_STARLIGHT))
                .save(output, MekInConstants.rl("starlight/eternal"));
    }
}
