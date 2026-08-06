package com.takenokoshi.mekin.recipe.building;

import com.glodblock.github.extendedae.common.EAESingletons;
import com.jerry.mekextras.common.registries.ExtraItems;
import com.jerry.mekextras.common.resource.ExtraResource;
import com.takenokoshi.mekin.core.MekInConstants;
import com.takenokoshi.mekin.recipe.builder.MeteorCollectorRecipeBulder;
import com.takenokoshi.mekin.registries.MekInItems;
import com.takenokoshi.mekut.enums.MUMaterial;
import com.takenokoshi.mekut.registries.MekUtItems;

import appeng.core.definitions.AEBlocks;
import appeng.core.definitions.AEItems;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import mekanism.common.registries.MekanismItems;
import mekanism.common.resource.PrimaryResource;
import mekanism.common.resource.ResourceType;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import static com.moakiee.ae2lt.registry.ModItems.OVERLOAD_SINGULARITY;
import static com.moakiee.ae2lt.registry.ModBlocks.FLAWLESS_BUDDING_OVERLOAD_CRYSTAL;
import static com.extendedae_plus.init.ModItems.OBLIVION_SINGULARITY;
import static com.moakiee.ae2lt.registry.ModItems.OVERLOAD_CRYSTAL_DUST;
import static com.moakiee.ae2lt.registry.ModItems.FIRMAMENT_DUST;
import static com.moakiee.ae2lt.registry.ModItems.FIRMAMENT_MIXTURE;

import java.util.List;

public class MeteorCollectorRecipes {

    public static void buildRecipes(RecipeOutput output) {
        MeteorCollectorRecipeBulder
                .meteorCollector(
                        IngredientCreatorAccess.item().from(OVERLOAD_SINGULARITY, 16),
                        IngredientCreatorAccess.item().from(AEItems.SKY_DUST, 1),
                        Level.OVERWORLD,
                        false)
                .addOutput(20, List.of(new ItemStack[] {
                        AEItems.SKY_DUST.stack(2048),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.CERTUS_QUARTZ).asStack(192),
                }))
                .addOutput(5, List.of(new ItemStack[] {
                        AEItems.SKY_DUST.stack(1024),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.AMETHYST).asStack(64),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.FLUORITE).asStack(256),
                }))
                .addOutput(20, List.of(new ItemStack[] {
                        AEItems.SKY_DUST.stack(1024),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.REDSTONE).asStack(64),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.COAL).asStack(64),
                }))
                .addOutput(6, List.of(new ItemStack[] {
                        AEItems.SKY_DUST.stack(1024),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.EMERALD).asStack(64),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.DIAMOND).asStack(64),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.LAPIS_LAZULI).asStack(128),
                }))
                .addOutput(20, List.of(new ItemStack[] {
                        AEItems.SKY_DUST.stack(1024),
                        new ItemStack(Items.RAW_IRON, 128),
                        new ItemStack(Items.RAW_COPPER, 64),
                        MekanismItems.PROCESSED_RESOURCES.get(ResourceType.RAW, PrimaryResource.TIN).asStack(64),
                }))
                .addOutput(20, List.of(new ItemStack[] {
                        AEItems.SKY_DUST.stack(1024),
                        new ItemStack(Items.RAW_GOLD, 64),
                        MekanismItems.PROCESSED_RESOURCES.get(ResourceType.RAW, PrimaryResource.OSMIUM).asStack(128),
                        MekanismItems.PROCESSED_RESOURCES.get(ResourceType.RAW, PrimaryResource.LEAD).asStack(64),
                }))
                .addOutput(8, List.of(new ItemStack[] {
                        AEItems.SKY_DUST.stack(4096),
                        MekanismItems.PROCESSED_RESOURCES.get(ResourceType.RAW, PrimaryResource.URANIUM).asStack(64),
                        ExtraItems.PROCESSED_RESOURCES.get(ResourceType.RAW, ExtraResource.NAQUADAH).asStack(64),
                }))
                .addOutput(1, List.of(new ItemStack[] {
                        AEItems.SKY_DUST.stack(4096),
                        new ItemStack(AEBlocks.FLAWLESS_BUDDING_QUARTZ, 64),
                        new ItemStack(AEBlocks.MYSTERIOUS_CUBE, 4),
                }))
                .build(output, MekInConstants.rl("meteor_collector/normal/overworld"));
        MeteorCollectorRecipeBulder
                .meteorCollector(
                        IngredientCreatorAccess.item().from(OVERLOAD_SINGULARITY, 16),
                        IngredientCreatorAccess.item().from(AEItems.SKY_DUST, 1),
                        Level.NETHER,
                        false)
                .addOutput(5, List.of(new ItemStack[] {
                        AEItems.SKY_DUST.stack(512),
                        new ItemStack(Items.GLOWSTONE_DUST, 512),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.QUARTZ).asStack(128),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.FLUORITE).asStack(128),
                }))
                .addOutput(2, List.of(new ItemStack[] {
                        AEItems.SKY_DUST.stack(1024),
                        new ItemStack(Items.RAW_GOLD, 128),
                        MekanismItems.PROCESSED_RESOURCES.get(ResourceType.RAW, PrimaryResource.OSMIUM).asStack(64),
                        MekanismItems.PROCESSED_RESOURCES.get(ResourceType.RAW, PrimaryResource.TIN).asStack(64),
                }))
                .addOutput(2, List.of(new ItemStack[] {
                        AEItems.SKY_DUST.stack(1024),
                        new ItemStack(Items.RAW_GOLD, 128),
                        MekanismItems.PROCESSED_RESOURCES.get(ResourceType.RAW, PrimaryResource.URANIUM).asStack(64),
                        MekanismItems.PROCESSED_RESOURCES.get(ResourceType.RAW, PrimaryResource.LEAD).asStack(64),
                }))
                .addOutput(1, List.of(new ItemStack[] {
                        AEItems.SKY_DUST.stack(1024),
                        new ItemStack(Items.RAW_GOLD, 192),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.NETHERITE).asStack(64),
                }))
                .build(output, MekInConstants.rl("meteor_collector/normal/the_nether"));
        MeteorCollectorRecipeBulder
                .meteorCollector(
                        IngredientCreatorAccess.item().from(OVERLOAD_SINGULARITY, 16),
                        IngredientCreatorAccess.item().from(AEItems.SKY_DUST, 1),
                        Level.END,
                        false)
                .addOutput(8, List.of(new ItemStack[] {
                        AEItems.SKY_DUST.stack(1024),
                        AEItems.ENDER_DUST.stack(256),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.FLUORITE).asStack(64),
                        MekanismItems.PROCESSED_RESOURCES.get(ResourceType.RAW, PrimaryResource.URANIUM).asStack(64),
                        ExtraItems.PROCESSED_RESOURCES.get(ResourceType.RAW, ExtraResource.NAQUADAH).asStack(64),
                }))
                .addOutput(6, List.of(new ItemStack[] {
                        AEItems.SKY_DUST.stack(1024),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.CERTUS_QUARTZ).asStack(64),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.ENTRO).asStack(64),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.AMETHYST).asStack(64),
                        MekInItems.RAW_OVERLOAD.asStack(64)
                }))
                .addOutput(5, List.of(new ItemStack[] {
                        AEItems.SKY_DUST.stack(1024),
                        new ItemStack(Items.RAW_IRON, 64),
                        MekanismItems.PROCESSED_RESOURCES.get(ResourceType.RAW, PrimaryResource.OSMIUM).asStack(64),
                        MekanismItems.PROCESSED_RESOURCES.get(ResourceType.RAW, PrimaryResource.TIN).asStack(64),
                        MekanismItems.PROCESSED_RESOURCES.get(ResourceType.RAW, PrimaryResource.LEAD).asStack(64),
                }))
                .addOutput(1, List.of(new ItemStack[] {
                        AEItems.SKY_DUST.stack(1024),
                        new ItemStack(AEBlocks.FLAWLESS_BUDDING_QUARTZ, 64),
                        new ItemStack(EAESingletons.FULLY_ENTROIZED_FLUIX_BUDDING, 64),
                        new ItemStack(FLAWLESS_BUDDING_OVERLOAD_CRYSTAL, 64),
                        new ItemStack(AEBlocks.MYSTERIOUS_CUBE, 4),
                }))
                .build(output, MekInConstants.rl("meteor_collector/normal/the_end"));
        MeteorCollectorRecipeBulder
                .meteorCollector(
                        IngredientCreatorAccess.item().from(OBLIVION_SINGULARITY, 1),
                        IngredientCreatorAccess.item().from(MekUtItems.AMETHYST_DUST),
                        Level.OVERWORLD,
                        true)
                .addOutput(1, List.of(new ItemStack[] {
                        AEItems.SKY_DUST.stack(4096),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.AMETHYST).asStack(16384),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.CERTUS_QUARTZ).asStack(64),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.QUARTZ).asStack(64),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.ENTRO).asStack(64),
                        MekInItems.RAW_OVERLOAD.asStack(64)
                }))
                .build(output, MekInConstants.rl("meteor_collector/advanced/overworld/amethyst"));
        MeteorCollectorRecipeBulder
                .meteorCollector(
                        IngredientCreatorAccess.item().from(OBLIVION_SINGULARITY, 1),
                        IngredientCreatorAccess.item().from(AEItems.CERTUS_QUARTZ_DUST),
                        Level.OVERWORLD,
                        true)
                .addOutput(1, List.of(new ItemStack[] {
                        AEItems.SKY_DUST.stack(4096),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.CERTUS_QUARTZ).asStack(16384),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.AMETHYST).asStack(64),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.QUARTZ).asStack(64),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.ENTRO).asStack(64),
                        MekInItems.RAW_OVERLOAD.asStack(64)
                }))
                .build(output, MekInConstants.rl("meteor_collector/advanced/overworld/certus_quartz"));
        MeteorCollectorRecipeBulder
                .meteorCollector(
                        IngredientCreatorAccess.item().from(OBLIVION_SINGULARITY, 1),
                        IngredientCreatorAccess.item().from(MekanismItems.COAL_DUST),
                        Level.OVERWORLD,
                        true)
                .addOutput(1, List.of(new ItemStack[] {
                        AEItems.SKY_DUST.stack(4096),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.COAL).asStack(16384),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.DIAMOND).asStack(64),
                }))
                .build(output, MekInConstants.rl("meteor_collector/advanced/overworld/coal"));
        MeteorCollectorRecipeBulder
                .meteorCollector(
                        IngredientCreatorAccess.item().from(OBLIVION_SINGULARITY, 1),
                        IngredientCreatorAccess.item()
                                .from(MekanismItems.PROCESSED_RESOURCES.get(ResourceType.DUST, PrimaryResource.COPPER)),
                        Level.OVERWORLD,
                        true)
                .addOutput(1, List.of(new ItemStack[] {
                        AEItems.SKY_DUST.stack(4096),
                        new ItemStack(Items.RAW_COPPER, 16384),
                        new ItemStack(Items.RAW_GOLD, 64),
                }))
                .build(output, MekInConstants.rl("meteor_collector/advanced/overworld/copper"));
        MeteorCollectorRecipeBulder
                .meteorCollector(
                        IngredientCreatorAccess.item().from(OBLIVION_SINGULARITY, 1),
                        IngredientCreatorAccess.item().from(MekanismItems.DIAMOND_DUST),
                        Level.OVERWORLD,
                        true)
                .addOutput(1, List.of(new ItemStack[] {
                        AEItems.SKY_DUST.stack(4096),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.DIAMOND).asStack(16384),
                        new ItemStack(Items.OBSIDIAN, 1024),
                }))
                .build(output, MekInConstants.rl("meteor_collector/advanced/overworld/diamond"));
        MeteorCollectorRecipeBulder
                .meteorCollector(
                        IngredientCreatorAccess.item().from(OBLIVION_SINGULARITY, 1),
                        IngredientCreatorAccess.item().from(MekanismItems.EMERALD_DUST),
                        Level.OVERWORLD,
                        true)
                .addOutput(1, List.of(new ItemStack[] {
                        AEItems.SKY_DUST.stack(4096),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.EMERALD).asStack(16384),
                        MekUtItems.XP_CRYSTAL.asStack(64),
                }))
                .build(output, MekInConstants.rl("meteor_collector/advanced/overworld/emerald"));
        MeteorCollectorRecipeBulder
                .meteorCollector(
                        IngredientCreatorAccess.item().from(OBLIVION_SINGULARITY, 1),
                        IngredientCreatorAccess.item().from(EAESingletons.ENTRO_DUST),
                        Level.OVERWORLD,
                        true)
                .addOutput(1, List.of(new ItemStack[] {
                        AEItems.SKY_DUST.stack(4096),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.ENTRO).asStack(16384),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.AMETHYST).asStack(64),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.CERTUS_QUARTZ).asStack(64),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.QUARTZ).asStack(64),
                        MekInItems.RAW_OVERLOAD.asStack(64)
                }))
                .build(output, MekInConstants.rl("meteor_collector/advanced/overworld/entro"));
        MeteorCollectorRecipeBulder
                .meteorCollector(
                        IngredientCreatorAccess.item().from(OBLIVION_SINGULARITY, 1),
                        IngredientCreatorAccess.item().from(MekanismItems.FLUORITE_DUST),
                        Level.OVERWORLD,
                        true)
                .addOutput(1, List.of(new ItemStack[] {
                        AEItems.SKY_DUST.stack(4096),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.FLUORITE).asStack(16384),
                        new ItemStack(Items.GLOWSTONE_DUST, 1024),
                }))
                .build(output, MekInConstants.rl("meteor_collector/advanced/overworld/fluorite"));
        MeteorCollectorRecipeBulder
                .meteorCollector(
                        IngredientCreatorAccess.item().from(OBLIVION_SINGULARITY, 1),
                        IngredientCreatorAccess.item()
                                .from(MekanismItems.PROCESSED_RESOURCES.get(ResourceType.DUST, PrimaryResource.GOLD)),
                        Level.OVERWORLD,
                        true)
                .addOutput(1, List.of(new ItemStack[] {
                        AEItems.SKY_DUST.stack(4096),
                        new ItemStack(Items.RAW_GOLD, 16384),
                        new ItemStack(Items.RAW_COPPER, 64),
                }))
                .build(output, MekInConstants.rl("meteor_collector/advanced/overworld/gold"));
        MeteorCollectorRecipeBulder
                .meteorCollector(
                        IngredientCreatorAccess.item().from(OBLIVION_SINGULARITY, 1),
                        IngredientCreatorAccess.item()
                                .from(MekanismItems.PROCESSED_RESOURCES.get(ResourceType.DUST, PrimaryResource.IRON)),
                        Level.OVERWORLD,
                        true)
                .addOutput(1, List.of(new ItemStack[] {
                        AEItems.SKY_DUST.stack(4096),
                        new ItemStack(Items.RAW_IRON, 16384),
                        MekanismItems.PROCESSED_RESOURCES.get(ResourceType.RAW, PrimaryResource.TIN).asStack(64),
                }))
                .build(output, MekInConstants.rl("meteor_collector/advanced/overworld/iron"));
        MeteorCollectorRecipeBulder
                .meteorCollector(
                        IngredientCreatorAccess.item().from(OBLIVION_SINGULARITY, 1),
                        IngredientCreatorAccess.item().from(MekanismItems.LAPIS_LAZULI_DUST),
                        Level.OVERWORLD,
                        true)
                .addOutput(1, List.of(new ItemStack[] {
                        AEItems.SKY_DUST.stack(4096),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.LAPIS_LAZULI).asStack(16384),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.FLUORITE).asStack(64),
                }))
                .build(output, MekInConstants.rl("meteor_collector/advanced/overworld/lapis_lazuli"));
        MeteorCollectorRecipeBulder
                .meteorCollector(
                        IngredientCreatorAccess.item().from(OBLIVION_SINGULARITY, 1),
                        IngredientCreatorAccess.item()
                                .from(MekanismItems.PROCESSED_RESOURCES.get(ResourceType.DUST, PrimaryResource.LEAD)),
                        Level.OVERWORLD,
                        true)
                .addOutput(1, List.of(new ItemStack[] {
                        AEItems.SKY_DUST.stack(4096),
                        MekanismItems.PROCESSED_RESOURCES.get(ResourceType.RAW, PrimaryResource.LEAD).asStack(16384),
                        MekanismItems.PROCESSED_RESOURCES.get(ResourceType.RAW, PrimaryResource.OSMIUM).asStack(64),
                }))
                .build(output, MekInConstants.rl("meteor_collector/advanced/overworld/lead"));
        MeteorCollectorRecipeBulder
                .meteorCollector(
                        IngredientCreatorAccess.item().from(OBLIVION_SINGULARITY, 1),
                        IngredientCreatorAccess.item()
                                .from(ExtraItems.PROCESSED_RESOURCES.get(ResourceType.DUST, ExtraResource.NAQUADAH)),
                        Level.OVERWORLD,
                        true)
                .addOutput(1, List.of(new ItemStack[] {
                        AEItems.SKY_DUST.stack(4096),
                        ExtraItems.PROCESSED_RESOURCES.get(ResourceType.RAW, ExtraResource.NAQUADAH).asStack(16384),
                        MekanismItems.PROCESSED_RESOURCES.get(ResourceType.RAW, PrimaryResource.URANIUM).asStack(64),
                }))
                .build(output, MekInConstants.rl("meteor_collector/advanced/overworld/naquadah"));
        MeteorCollectorRecipeBulder
                .meteorCollector(
                        IngredientCreatorAccess.item().from(OBLIVION_SINGULARITY, 1),
                        IngredientCreatorAccess.item()
                                .from(MekanismItems.PROCESSED_RESOURCES.get(ResourceType.DUST, PrimaryResource.OSMIUM)),
                        Level.OVERWORLD,
                        true)
                .addOutput(1, List.of(new ItemStack[] {
                        AEItems.SKY_DUST.stack(4096),
                        MekanismItems.PROCESSED_RESOURCES.get(ResourceType.RAW, PrimaryResource.OSMIUM).asStack(16384),
                        new ItemStack(Items.RAW_GOLD, 64),
                }))
                .build(output, MekInConstants.rl("meteor_collector/advanced/overworld/osmium"));
        MeteorCollectorRecipeBulder
                .meteorCollector(
                        IngredientCreatorAccess.item().from(OBLIVION_SINGULARITY, 1),
                        IngredientCreatorAccess.item().from(OVERLOAD_CRYSTAL_DUST),
                        Level.OVERWORLD,
                        true)
                .addOutput(1, List.of(new ItemStack[] {
                        AEItems.SKY_DUST.stack(4096),
                        MekInItems.RAW_OVERLOAD.asStack(16384),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.ENTRO).asStack(64),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.AMETHYST).asStack(64),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.CERTUS_QUARTZ).asStack(64),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.QUARTZ).asStack(64),
                }))
                .build(output, MekInConstants.rl("meteor_collector/advanced/overworld/overload"));
        MeteorCollectorRecipeBulder
                .meteorCollector(
                        IngredientCreatorAccess.item().from(OBLIVION_SINGULARITY, 1),
                        IngredientCreatorAccess.item().from(Items.REDSTONE),
                        Level.OVERWORLD,
                        true)
                .addOutput(1, List.of(new ItemStack[] {
                        AEItems.SKY_DUST.stack(4096),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.REDSTONE).asStack(16384),
                        new ItemStack(Items.RAW_IRON, 64),
                }))
                .build(output, MekInConstants.rl("meteor_collector/advanced/overworld/redstone"));
        MeteorCollectorRecipeBulder
                .meteorCollector(
                        IngredientCreatorAccess.item().from(OBLIVION_SINGULARITY, 1),
                        IngredientCreatorAccess.item()
                                .from(MekanismItems.PROCESSED_RESOURCES.get(ResourceType.DUST, PrimaryResource.TIN)),
                        Level.OVERWORLD,
                        true)
                .addOutput(1, List.of(new ItemStack[] {
                        AEItems.SKY_DUST.stack(4096),
                        MekanismItems.PROCESSED_RESOURCES.get(ResourceType.RAW, PrimaryResource.TIN).asStack(16384),
                        MekanismItems.PROCESSED_RESOURCES.get(ResourceType.RAW, PrimaryResource.LEAD).asStack(64),
                }))
                .build(output, MekInConstants.rl("meteor_collector/advanced/overworld/tin"));
        MeteorCollectorRecipeBulder
                .meteorCollector(
                        IngredientCreatorAccess.item().from(OBLIVION_SINGULARITY, 1),
                        IngredientCreatorAccess.item()
                                .from(MekanismItems.PROCESSED_RESOURCES.get(ResourceType.DUST,
                                        PrimaryResource.URANIUM)),
                        Level.OVERWORLD,
                        true)
                .addOutput(1, List.of(new ItemStack[] {
                        AEItems.SKY_DUST.stack(4096),
                        MekanismItems.PROCESSED_RESOURCES.get(ResourceType.RAW, PrimaryResource.URANIUM).asStack(16384),
                        ExtraItems.PROCESSED_RESOURCES.get(ResourceType.RAW, ExtraResource.NAQUADAH).asStack(64),
                }))
                .build(output, MekInConstants.rl("meteor_collector/advanced/overworld/uranium"));

        MeteorCollectorRecipeBulder
                .meteorCollector(
                        IngredientCreatorAccess.item().from(OBLIVION_SINGULARITY, 1),
                        IngredientCreatorAccess.item().from(MekanismItems.FLUORITE_DUST),
                        Level.NETHER,
                        true)
                .addOutput(1, List.of(new ItemStack[] {
                        new ItemStack(Items.GLOWSTONE_DUST, 4096),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.FLUORITE).asStack(16384),
                        AEItems.SKY_DUST.stack(1024),
                }))
                .build(output, MekInConstants.rl("meteor_collector/advanced/nether/fluorite"));
        MeteorCollectorRecipeBulder
                .meteorCollector(
                        IngredientCreatorAccess.item().from(OBLIVION_SINGULARITY, 1),
                        IngredientCreatorAccess.item()
                                .from(MekanismItems.PROCESSED_RESOURCES.get(ResourceType.DUST, PrimaryResource.GOLD)),
                        Level.NETHER,
                        true)
                .addOutput(1, List.of(new ItemStack[] {
                        AEItems.SKY_DUST.stack(4096),
                        new ItemStack(Items.RAW_GOLD, 16384),
                        MekanismItems.PROCESSED_RESOURCES.get(ResourceType.RAW, PrimaryResource.OSMIUM).asStack(64),
                }))
                .build(output, MekInConstants.rl("meteor_collector/advanced/nether/gold"));
        MeteorCollectorRecipeBulder
                .meteorCollector(
                        IngredientCreatorAccess.item().from(OBLIVION_SINGULARITY, 1),
                        IngredientCreatorAccess.item()
                                .from(MekanismItems.PROCESSED_RESOURCES.get(ResourceType.DUST, PrimaryResource.LEAD)),
                        Level.NETHER,
                        true)
                .addOutput(1, List.of(new ItemStack[] {
                        AEItems.SKY_DUST.stack(4096),
                        MekanismItems.PROCESSED_RESOURCES.get(ResourceType.RAW, PrimaryResource.LEAD).asStack(16384),
                        MekanismItems.PROCESSED_RESOURCES.get(ResourceType.RAW, PrimaryResource.TIN).asStack(64),
                }))
                .build(output, MekInConstants.rl("meteor_collector/advanced/nether/lead"));
        MeteorCollectorRecipeBulder
                .meteorCollector(
                        IngredientCreatorAccess.item().from(OBLIVION_SINGULARITY, 1),
                        IngredientCreatorAccess.item().from(MekanismItems.NETHERITE_DUST),
                        Level.NETHER,
                        true)
                .addOutput(1, List.of(new ItemStack[] {
                        AEItems.SKY_DUST.stack(4096),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.NETHERITE).asStack(16384),
                        MekanismItems.PROCESSED_RESOURCES.get(ResourceType.RAW, PrimaryResource.LEAD).asStack(64),
                        MekanismItems.PROCESSED_RESOURCES.get(ResourceType.RAW, PrimaryResource.URANIUM).asStack(64),
                }))
                .build(output, MekInConstants.rl("meteor_collector/advanced/nether/netherite"));
        MeteorCollectorRecipeBulder
                .meteorCollector(
                        IngredientCreatorAccess.item().from(OBLIVION_SINGULARITY, 1),
                        IngredientCreatorAccess.item()
                                .from(MekanismItems.PROCESSED_RESOURCES.get(ResourceType.DUST, PrimaryResource.OSMIUM)),
                        Level.NETHER,
                        true)
                .addOutput(1, List.of(new ItemStack[] {
                        AEItems.SKY_DUST.stack(4096),
                        MekanismItems.PROCESSED_RESOURCES.get(ResourceType.RAW, PrimaryResource.OSMIUM).asStack(16384),
                        new ItemStack(Items.RAW_GOLD, 64),
                }))
                .build(output, MekInConstants.rl("meteor_collector/advanced/nether/osmium"));
        MeteorCollectorRecipeBulder
                .meteorCollector(
                        IngredientCreatorAccess.item().from(OBLIVION_SINGULARITY, 1),
                        IngredientCreatorAccess.item()
                                .from(MekanismItems.PROCESSED_RESOURCES.get(ResourceType.DUST, PrimaryResource.TIN)),
                        Level.NETHER,
                        true)
                .addOutput(1, List.of(new ItemStack[] {
                        AEItems.SKY_DUST.stack(4096),
                        MekanismItems.PROCESSED_RESOURCES.get(ResourceType.RAW, PrimaryResource.TIN).asStack(16384),
                        MekanismItems.PROCESSED_RESOURCES.get(ResourceType.RAW, PrimaryResource.LEAD).asStack(64),
                }))
                .build(output, MekInConstants.rl("meteor_collector/advanced/nether/tin"));
        MeteorCollectorRecipeBulder
                .meteorCollector(
                        IngredientCreatorAccess.item().from(OBLIVION_SINGULARITY, 1),
                        IngredientCreatorAccess.item().from(MekanismItems.QUARTZ_DUST),
                        Level.NETHER,
                        true)
                .addOutput(1, List.of(new ItemStack[] {
                        new ItemStack(Items.GLOWSTONE_DUST, 4096),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.QUARTZ).asStack(16384),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.AMETHYST).asStack(64),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.CERTUS_QUARTZ).asStack(64),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.ENTRO).asStack(64),
                        MekInItems.RAW_OVERLOAD.asStack(64)
                }))
                .build(output, MekInConstants.rl("meteor_collector/advanced/nether/quartz"));

        MeteorCollectorRecipeBulder
                .meteorCollector(
                        IngredientCreatorAccess.item().from(OBLIVION_SINGULARITY, 1),
                        IngredientCreatorAccess.item().from(MekanismItems.FLUORITE_DUST),
                        Level.END,
                        true)
                .addOutput(1, List.of(new ItemStack[] {
                        AEItems.SKY_DUST.stack(4096),
                        new ItemStack(Items.GLOWSTONE_DUST, 1024),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.FLUORITE).asStack(16384),
                        MekInItems.RAW_OVERLOAD.asStack(64)
                }))
                .build(output, MekInConstants.rl("meteor_collector/advanced/end/fluorite"));
        MeteorCollectorRecipeBulder
                .meteorCollector(
                        IngredientCreatorAccess.item().from(OBLIVION_SINGULARITY, 1),
                        IngredientCreatorAccess.item()
                                .from(ExtraItems.PROCESSED_RESOURCES.get(ResourceType.DUST, ExtraResource.NAQUADAH)),
                        Level.END,
                        true)
                .addOutput(1, List.of(new ItemStack[] {
                        AEItems.SKY_DUST.stack(4096),
                        new ItemStack(Items.GLOWSTONE_DUST, 1024),
                        ExtraItems.PROCESSED_RESOURCES.get(ResourceType.RAW, ExtraResource.NAQUADAH).asStack(16384),
                        MekanismItems.PROCESSED_RESOURCES.get(ResourceType.RAW, PrimaryResource.URANIUM).asStack(64),
                }))
                .build(output, MekInConstants.rl("meteor_collector/advanced/end/naquadah"));
        MeteorCollectorRecipeBulder
                .meteorCollector(
                        IngredientCreatorAccess.item().from(OBLIVION_SINGULARITY, 1),
                        IngredientCreatorAccess.item()
                                .from(MekanismItems.PROCESSED_RESOURCES.get(ResourceType.DUST,
                                        PrimaryResource.URANIUM)),
                        Level.END,
                        true)
                .addOutput(1, List.of(new ItemStack[] {
                        AEItems.SKY_DUST.stack(4096),
                        new ItemStack(Items.GLOWSTONE_DUST, 1024),
                        MekanismItems.PROCESSED_RESOURCES.get(ResourceType.RAW, PrimaryResource.URANIUM).asStack(16384),
                        ExtraItems.PROCESSED_RESOURCES.get(ResourceType.RAW, ExtraResource.NAQUADAH).asStack(64),
                }))
                .build(output, MekInConstants.rl("meteor_collector/advanced/end/uranium"));
        MeteorCollectorRecipeBulder
                .meteorCollector(
                        IngredientCreatorAccess.item().from(OBLIVION_SINGULARITY, 1),
                        IngredientCreatorAccess.item()
                                .from(MekanismItems.PROCESSED_RESOURCES.get(ResourceType.DUST, PrimaryResource.LEAD)),
                        Level.NETHER,
                        true)
                .addOutput(1, List.of(new ItemStack[] {
                        AEItems.SKY_DUST.stack(4096),
                        MekanismItems.PROCESSED_RESOURCES.get(ResourceType.RAW, PrimaryResource.LEAD).asStack(16384),
                        MekanismItems.PROCESSED_RESOURCES.get(ResourceType.RAW, PrimaryResource.TIN).asStack(64),
                        MekanismItems.PROCESSED_RESOURCES.get(ResourceType.RAW, PrimaryResource.OSMIUM).asStack(64),
                }))
                .build(output, MekInConstants.rl("meteor_collector/advanced/end/lead"));
        MeteorCollectorRecipeBulder
                .meteorCollector(
                        IngredientCreatorAccess.item().from(OBLIVION_SINGULARITY, 1),
                        IngredientCreatorAccess.item()
                                .from(MekanismItems.PROCESSED_RESOURCES.get(ResourceType.DUST, PrimaryResource.TIN)),
                        Level.NETHER,
                        true)
                .addOutput(1, List.of(new ItemStack[] {
                        AEItems.SKY_DUST.stack(4096),
                        MekanismItems.PROCESSED_RESOURCES.get(ResourceType.RAW, PrimaryResource.TIN).asStack(16384),
                        MekanismItems.PROCESSED_RESOURCES.get(ResourceType.RAW, PrimaryResource.LEAD).asStack(64),
                        MekanismItems.PROCESSED_RESOURCES.get(ResourceType.RAW, PrimaryResource.OSMIUM).asStack(64),
                }))
                .build(output, MekInConstants.rl("meteor_collector/advanced/end/tin"));
        MeteorCollectorRecipeBulder
                .meteorCollector(
                        IngredientCreatorAccess.item().from(OBLIVION_SINGULARITY, 1),
                        IngredientCreatorAccess.item()
                                .from(MekanismItems.PROCESSED_RESOURCES.get(ResourceType.DUST, PrimaryResource.OSMIUM)),
                        Level.NETHER,
                        true)
                .addOutput(1, List.of(new ItemStack[] {
                        AEItems.SKY_DUST.stack(4096),
                        MekanismItems.PROCESSED_RESOURCES.get(ResourceType.RAW, PrimaryResource.OSMIUM).asStack(16384),
                        MekanismItems.PROCESSED_RESOURCES.get(ResourceType.RAW, PrimaryResource.LEAD).asStack(64),
                        MekanismItems.PROCESSED_RESOURCES.get(ResourceType.RAW, PrimaryResource.TIN).asStack(64),
                }))
                .build(output, MekInConstants.rl("meteor_collector/advanced/end/osmium"));
        MeteorCollectorRecipeBulder
                .meteorCollector(
                        IngredientCreatorAccess.item().from(OBLIVION_SINGULARITY, 1),
                        IngredientCreatorAccess.item().from(OVERLOAD_CRYSTAL_DUST),
                        Level.END,
                        true)
                .addOutput(99, List.of(new ItemStack[] {
                        AEItems.SKY_DUST.stack(4096),
                        MekInItems.RAW_OVERLOAD.asStack(16384),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.ENTRO).asStack(64),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.AMETHYST).asStack(64),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.CERTUS_QUARTZ).asStack(64),
                        MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.QUARTZ).asStack(64),
                }))
                .addOutput(1, List.of(new ItemStack[] {
                        AEItems.SKY_DUST.stack(4096),
                        MekInItems.RAW_OVERLOAD.asStack(16384),
                        new ItemStack(FIRMAMENT_DUST.getDelegate(), 16),
                        new ItemStack(FIRMAMENT_MIXTURE.getDelegate(), 16),
                }))
                .build(output, MekInConstants.rl("meteor_collector/advanced/end/overload"));
    }
}
