package com.takenokoshi.mekin.recipe;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.fxd927.mekanismelements.common.registries.MSGases;
import com.glodblock.github.extendedae.common.EAESingletons;
import com.jerry.mekextras.common.registries.ExtraChemicals;
import com.jerry.mekextras.common.registries.ExtraItems;
import com.jerry.mekextras.common.resource.ExtraResource;
import com.jerry.mekmm.common.registries.MoreMachineChemicals;
import com.takenokoshi.mekin.registries.MekInChemicals;
import com.takenokoshi.mekin.registries.MekInItems;
import com.takenokoshi.mekut.enums.MUMaterial;
import com.takenokoshi.mekut.registries.MekUtChemicals;
import com.takenokoshi.mekut.registries.MekUtItems;

import appeng.core.definitions.AEItems;
import mekanism.api.chemical.Chemical;
import mekanism.common.registries.MekanismChemicals;
import mekanism.common.registries.MekanismItems;
import mekanism.common.resource.PrimaryResource;
import mekanism.common.resource.ResourceType;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

/**
 * 
 * MekInMaterial
 * 
 * @param name
 * @param raw
 * @param dirtySlurry
 * @param cleanSlurry
 * @param shard
 * @param clump
 * @param dirtyDust
 * @param dust
 * @param type        0:ingot 1:certus_quartz 2:gem 3:redstone
 * @param finalItem
 */
public record MekInMaterial(String name, ItemLike raw, Holder<Chemical> plasma, ItemLike chargedShard,
        ItemLike dirtyCrystal, Holder<Chemical> dirtySlurry,
        Holder<Chemical> cleanSlurry,
        Holder<Chemical> secondaryChemical,
        ItemLike crystal, ItemLike shard, ItemLike clump, @Nullable ItemLike dirtyDust, ItemLike dust, int type,
        ItemLike finalItem) {

    public TagKey<Item> rawTag() {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "raw_materials/" + name));
    }

    public TagKey<Item> crystalTag() {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "crystals/" + name));
    }

    public TagKey<Item> shardTag() {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "shards/" + name));
    }

    public TagKey<Item> clumpTag() {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "clumps/" + name));
    }

    public TagKey<Item> dirtyDustTag() {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "dirty_dusts/" + name));
    }

    public TagKey<Item> dustsTag() {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "dusts/" + name));
    }

    public static final MekInMaterial IRON = new MekInMaterial("iron",
            Items.RAW_IRON,
            MekInChemicals.IRON_PLASMA,
            MekInItems.CHARGED_IRON_SHARD,
            MekInItems.DIRTY_IRON_CRYSTAL,
            MekanismChemicals.PROCESSED_RESOURCES.get(PrimaryResource.IRON).getDelegate(),
            MekanismChemicals.PROCESSED_RESOURCES.get(PrimaryResource.IRON).getCleanSlurry(),
            MekInChemicals.TITANIUM,
            MekanismItems.PROCESSED_RESOURCES.get(ResourceType.CRYSTAL, PrimaryResource.IRON),
            MekanismItems.PROCESSED_RESOURCES.get(ResourceType.SHARD, PrimaryResource.IRON),
            MekanismItems.PROCESSED_RESOURCES.get(ResourceType.CLUMP, PrimaryResource.IRON),
            MekanismItems.PROCESSED_RESOURCES.get(ResourceType.DIRTY_DUST, PrimaryResource.IRON),
            MekanismItems.PROCESSED_RESOURCES.get(ResourceType.DUST, PrimaryResource.IRON),
            0,
            Items.IRON_INGOT);

    public static final MekInMaterial GOLD = new MekInMaterial("gold",
            Items.RAW_GOLD,
            MekInChemicals.GOLD_PLASMA,
            MekInItems.CHARGED_GOLD_SHARD,
            MekInItems.DIRTY_GOLD_CRYSTAL,
            MekanismChemicals.PROCESSED_RESOURCES.get(PrimaryResource.GOLD).getDelegate(),
            MekanismChemicals.PROCESSED_RESOURCES.get(PrimaryResource.GOLD).getCleanSlurry(),
            MekInChemicals.SILVER,
            MekanismItems.PROCESSED_RESOURCES.get(ResourceType.CRYSTAL, PrimaryResource.GOLD),
            MekanismItems.PROCESSED_RESOURCES.get(ResourceType.SHARD, PrimaryResource.GOLD),
            MekanismItems.PROCESSED_RESOURCES.get(ResourceType.CLUMP, PrimaryResource.GOLD),
            MekanismItems.PROCESSED_RESOURCES.get(ResourceType.DIRTY_DUST, PrimaryResource.GOLD),
            MekanismItems.PROCESSED_RESOURCES.get(ResourceType.DUST, PrimaryResource.GOLD),
            0,
            Items.GOLD_INGOT);

    public static final MekInMaterial COPPER = new MekInMaterial("copper",
            Items.RAW_COPPER,
            MekInChemicals.COPPER_PLASMA,
            MekInItems.CHARGED_COPPER_SHARD,
            MekInItems.DIRTY_COPPER_CRYSTAL,
            MekanismChemicals.PROCESSED_RESOURCES.get(PrimaryResource.COPPER).getDelegate(),
            MekanismChemicals.PROCESSED_RESOURCES.get(PrimaryResource.COPPER).getCleanSlurry(),
            MekInChemicals.SILVER,
            MekanismItems.PROCESSED_RESOURCES.get(ResourceType.CRYSTAL, PrimaryResource.COPPER),
            MekanismItems.PROCESSED_RESOURCES.get(ResourceType.SHARD, PrimaryResource.COPPER),
            MekanismItems.PROCESSED_RESOURCES.get(ResourceType.CLUMP, PrimaryResource.COPPER),
            MekanismItems.PROCESSED_RESOURCES.get(ResourceType.DIRTY_DUST, PrimaryResource.COPPER),
            MekanismItems.PROCESSED_RESOURCES.get(ResourceType.DUST, PrimaryResource.COPPER),
            0,
            Items.COPPER_INGOT);

    public static final MekInMaterial OSMIUM = new MekInMaterial("osmium",
            MekanismItems.PROCESSED_RESOURCES.get(ResourceType.RAW, PrimaryResource.OSMIUM),
            MekInChemicals.OSMIUM_PLASMA,
            MekInItems.CHARGED_OSMIUM_SHARD,
            MekInItems.DIRTY_OSMIUM_CRYSTAL,
            MekanismChemicals.PROCESSED_RESOURCES.get(PrimaryResource.OSMIUM).getDelegate(),
            MekanismChemicals.PROCESSED_RESOURCES.get(PrimaryResource.OSMIUM).getCleanSlurry(),
            MekUtChemicals.IRIDIUM,
            MekanismItems.PROCESSED_RESOURCES.get(ResourceType.CRYSTAL, PrimaryResource.OSMIUM),
            MekanismItems.PROCESSED_RESOURCES.get(ResourceType.SHARD, PrimaryResource.OSMIUM),
            MekanismItems.PROCESSED_RESOURCES.get(ResourceType.CLUMP, PrimaryResource.OSMIUM),
            MekanismItems.PROCESSED_RESOURCES.get(ResourceType.DIRTY_DUST, PrimaryResource.OSMIUM),
            MekanismItems.PROCESSED_RESOURCES.get(ResourceType.DUST, PrimaryResource.OSMIUM),
            0,
            MekanismItems.PROCESSED_RESOURCES.get(ResourceType.INGOT, PrimaryResource.OSMIUM));

    public static final MekInMaterial URANIUM = new MekInMaterial("uranium",
            MekanismItems.PROCESSED_RESOURCES.get(ResourceType.RAW, PrimaryResource.URANIUM),
            MekInChemicals.URANIUM_PLASMA,
            MekInItems.CHARGED_URANIUM_SHARD,
            MekInItems.DIRTY_URANIUM_CRYSTAL,
            MekanismChemicals.PROCESSED_RESOURCES.get(PrimaryResource.URANIUM).getDelegate(),
            MekanismChemicals.PROCESSED_RESOURCES.get(PrimaryResource.URANIUM).getCleanSlurry(),
            MoreMachineChemicals.UNSTABLE_DIMENSIONAL_GAS,
            MekanismItems.PROCESSED_RESOURCES.get(ResourceType.CRYSTAL, PrimaryResource.URANIUM),
            MekanismItems.PROCESSED_RESOURCES.get(ResourceType.SHARD, PrimaryResource.URANIUM),
            MekanismItems.PROCESSED_RESOURCES.get(ResourceType.CLUMP, PrimaryResource.URANIUM),
            MekanismItems.PROCESSED_RESOURCES.get(ResourceType.DIRTY_DUST, PrimaryResource.URANIUM),
            MekanismItems.PROCESSED_RESOURCES.get(ResourceType.DUST, PrimaryResource.URANIUM),
            0,
            MekanismItems.PROCESSED_RESOURCES.get(ResourceType.INGOT, PrimaryResource.URANIUM));

    public static final MekInMaterial TIN = new MekInMaterial("tin",
            MekanismItems.PROCESSED_RESOURCES.get(ResourceType.RAW, PrimaryResource.TIN),
            MekInChemicals.TIN_PLASMA,
            MekInItems.CHARGED_TIN_SHARD,
            MekInItems.DIRTY_TIN_CRYSTAL,
            MekanismChemicals.PROCESSED_RESOURCES.get(PrimaryResource.TIN).getDelegate(),
            MekanismChemicals.PROCESSED_RESOURCES.get(PrimaryResource.TIN).getCleanSlurry(),
            MekInChemicals.TITANIUM,
            MekanismItems.PROCESSED_RESOURCES.get(ResourceType.CRYSTAL, PrimaryResource.TIN),
            MekanismItems.PROCESSED_RESOURCES.get(ResourceType.SHARD, PrimaryResource.TIN),
            MekanismItems.PROCESSED_RESOURCES.get(ResourceType.CLUMP, PrimaryResource.TIN),
            MekanismItems.PROCESSED_RESOURCES.get(ResourceType.DIRTY_DUST, PrimaryResource.TIN),
            MekanismItems.PROCESSED_RESOURCES.get(ResourceType.DUST, PrimaryResource.TIN),
            0,
            MekanismItems.PROCESSED_RESOURCES.get(ResourceType.INGOT, PrimaryResource.TIN));

    public static final MekInMaterial LEAD = new MekInMaterial("lead",
            MekanismItems.PROCESSED_RESOURCES.get(ResourceType.RAW, PrimaryResource.LEAD),
            MekInChemicals.LEAD_PLASMA,
            MekInItems.CHARGED_LEAD_SHARD,
            MekInItems.DIRTY_LEAD_CRYSTAL,
            MekanismChemicals.PROCESSED_RESOURCES.get(PrimaryResource.LEAD).getDelegate(),
            MekanismChemicals.PROCESSED_RESOURCES.get(PrimaryResource.LEAD).getCleanSlurry(),
            MekInChemicals.SILVER,
            MekanismItems.PROCESSED_RESOURCES.get(ResourceType.CRYSTAL, PrimaryResource.LEAD),
            MekanismItems.PROCESSED_RESOURCES.get(ResourceType.SHARD, PrimaryResource.LEAD),
            MekanismItems.PROCESSED_RESOURCES.get(ResourceType.CLUMP, PrimaryResource.LEAD),
            MekanismItems.PROCESSED_RESOURCES.get(ResourceType.DIRTY_DUST, PrimaryResource.LEAD),
            MekanismItems.PROCESSED_RESOURCES.get(ResourceType.DUST, PrimaryResource.LEAD),
            0,
            MekanismItems.PROCESSED_RESOURCES.get(ResourceType.INGOT, PrimaryResource.LEAD));

    public static final MekInMaterial NAQUADAH = new MekInMaterial("naquadah",
            ExtraItems.PROCESSED_RESOURCES.get(ResourceType.RAW, ExtraResource.NAQUADAH),
            MekInChemicals.NAQUADAH_PLASMA,
            MekInItems.CHARGED_NAQUADAH_SHARD,
            MekInItems.DIRTY_NAQUADAH_CRYSTAL,
            ExtraChemicals.DIRTY_AND_CLEAN_SLURRIES_NAQUADAH.getDelegate(),
            ExtraChemicals.DIRTY_AND_CLEAN_SLURRIES_NAQUADAH.getCleanSlurry(),
            MekUtChemicals.IRIDIUM,
            ExtraItems.PROCESSED_RESOURCES.get(ResourceType.CRYSTAL, ExtraResource.NAQUADAH),
            ExtraItems.PROCESSED_RESOURCES.get(ResourceType.SHARD, ExtraResource.NAQUADAH),
            ExtraItems.PROCESSED_RESOURCES.get(ResourceType.CLUMP, ExtraResource.NAQUADAH),
            ExtraItems.PROCESSED_RESOURCES.get(ResourceType.DIRTY_DUST, ExtraResource.NAQUADAH),
            ExtraItems.PROCESSED_RESOURCES.get(ResourceType.DUST, ExtraResource.NAQUADAH),
            0,
            ExtraItems.PROCESSED_RESOURCES.get(ResourceType.INGOT, ExtraResource.NAQUADAH));

    public static final MekInMaterial NETHERITE = new MekInMaterial("netherite",
            MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.NETHERITE),
            MekInChemicals.NETHERITE_PLASMA,
            MekInItems.CHARGED_NETHERITE_SHARD,
            MekInItems.DIRTY_NETHERITE_CRYSTAL,
            MekUtChemicals.MU_MATERIALS_DIRTY_SLURRY.get(MUMaterial.NETHERITE),
            MekUtChemicals.MU_MATERIALS_CLEAN_SLURRY.get(MUMaterial.NETHERITE),
            MekUtChemicals.IRIDIUM,
            MekUtItems.MU_MATERIALS_CRYSTAL.get(MUMaterial.NETHERITE),
            MekUtItems.MU_MATERIALS_SHARD.get(MUMaterial.NETHERITE),
            MekUtItems.MU_MATERIALS_CLUMP.get(MUMaterial.NETHERITE),
            MekUtItems.MU_MATERIALS_DIRTY_DUST.get(MUMaterial.NETHERITE),
            MekanismItems.NETHERITE_DUST,
            0,
            Items.NETHERITE_INGOT);

    public static final MekInMaterial AMETHYST = new MekInMaterial("amethyst",
            MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.AMETHYST),
            MekInChemicals.AMETHYST_PLASMA,
            MekInItems.CHARGED_AMETHYST_SHARD,
            MekInItems.DIRTY_AMETHYST_CRYSTAL,
            MekUtChemicals.MU_MATERIALS_DIRTY_SLURRY.get(MUMaterial.AMETHYST),
            MekUtChemicals.MU_MATERIALS_CLEAN_SLURRY.get(MUMaterial.AMETHYST),
            MekUtChemicals.MU_MATERIALS_CLEAN_SLURRY.get(MUMaterial.QUARTZ),
            MekUtItems.MU_MATERIALS_CRYSTAL.get(MUMaterial.AMETHYST),
            MekUtItems.MU_MATERIALS_SHARD.get(MUMaterial.AMETHYST),
            MekUtItems.MU_MATERIALS_CLUMP.get(MUMaterial.AMETHYST),
            null,
            MekUtItems.AMETHYST_DUST,
            0,
            Items.AMETHYST_SHARD);

    public static final MekInMaterial CERTUS_QUARTZ = new MekInMaterial("certus_quartz",
            MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.CERTUS_QUARTZ),
            MekInChemicals.CERTUS_QUARTZ_PLASMA,
            MekInItems.CHARGED_CERTYS_QUARTZ_SHARD,
            MekInItems.DIRTY_CERTUS_QUARTZ_CRYSTAL,
            MekUtChemicals.MU_MATERIALS_DIRTY_SLURRY.get(MUMaterial.CERTUS_QUARTZ),
            MekUtChemicals.MU_MATERIALS_CLEAN_SLURRY.get(MUMaterial.CERTUS_QUARTZ),
            MekUtChemicals.MU_MATERIALS_CLEAN_SLURRY.get(MUMaterial.QUARTZ),
            MekUtItems.MU_MATERIALS_CRYSTAL.get(MUMaterial.CERTUS_QUARTZ),
            MekUtItems.MU_MATERIALS_SHARD.get(MUMaterial.CERTUS_QUARTZ),
            MekUtItems.MU_MATERIALS_CLUMP.get(MUMaterial.CERTUS_QUARTZ),
            null,
            AEItems.CERTUS_QUARTZ_DUST,
            1,
            AEItems.CERTUS_QUARTZ_CRYSTAL);

    public static final MekInMaterial ENTRO = new MekInMaterial("entro",
            MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.ENTRO),
            MekInChemicals.ENTRO_PLASMA,
            MekInItems.CHARGED_ENTRO_SHARD,
            MekInItems.DIRTY_ENTRO_CRYSTAL,
            MekUtChemicals.MU_MATERIALS_DIRTY_SLURRY.get(MUMaterial.ENTRO),
            MekUtChemicals.MU_MATERIALS_CLEAN_SLURRY.get(MUMaterial.ENTRO),
            MekUtChemicals.MU_MATERIALS_CLEAN_SLURRY.get(MUMaterial.CERTUS_QUARTZ),
            MekUtItems.MU_MATERIALS_CRYSTAL.get(MUMaterial.ENTRO),
            MekUtItems.MU_MATERIALS_SHARD.get(MUMaterial.ENTRO),
            MekUtItems.MU_MATERIALS_CLUMP.get(MUMaterial.ENTRO),
            null,
            EAESingletons.ENTRO_DUST,
            2,
            EAESingletons.ENTRO_CRYSTAL);

    public static final MekInMaterial FLOURITE = new MekInMaterial("fluorite",
            MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.FLUORITE),
            MekInChemicals.FLUORITE_PLASMA,
            MekInItems.CHARGED_FLUORITE_SHARD,
            MekInItems.DIRTY_FLUORITE_CRYSTAL,
            MekUtChemicals.MU_MATERIALS_DIRTY_SLURRY.get(MUMaterial.FLUORITE),
            MekUtChemicals.MU_MATERIALS_CLEAN_SLURRY.get(MUMaterial.FLUORITE),
            MSGases.YTTRIUM,
            MekUtItems.MU_MATERIALS_CRYSTAL.get(MUMaterial.FLUORITE),
            MekUtItems.MU_MATERIALS_SHARD.get(MUMaterial.FLUORITE),
            MekUtItems.MU_MATERIALS_CLUMP.get(MUMaterial.FLUORITE),
            null,
            MekanismItems.FLUORITE_DUST,
            2,
            MekanismItems.FLUORITE_GEM);

    public static final MekInMaterial COAL = new MekInMaterial("coal",
            MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.COAL),
            MekInChemicals.COAL_PLASMA,
            MekInItems.CHARGED_COAL_SHARD,
            MekInItems.DIRTY_COAL_CRYSTAL,
            MekUtChemicals.MU_MATERIALS_DIRTY_SLURRY.get(MUMaterial.COAL),
            MekUtChemicals.MU_MATERIALS_CLEAN_SLURRY.get(MUMaterial.COAL),
            MoreMachineChemicals.UNSTABLE_DIMENSIONAL_GAS,
            MekUtItems.MU_MATERIALS_CRYSTAL.get(MUMaterial.COAL),
            MekUtItems.MU_MATERIALS_SHARD.get(MUMaterial.COAL),
            MekUtItems.MU_MATERIALS_CLUMP.get(MUMaterial.COAL),
            null,
            MekanismItems.COAL_DUST,
            2,
            Items.COAL);

    public static final MekInMaterial DIAMOND = new MekInMaterial("diamond",
            MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.DIAMOND),
            MekInChemicals.DIAMOND_PLASMA,
            MekInItems.CHARGED_DIAMOND_SHARD,
            MekInItems.DIRTY_DIAMOND_CRYSTAL,
            MekUtChemicals.MU_MATERIALS_DIRTY_SLURRY.get(MUMaterial.DIAMOND),
            MekUtChemicals.MU_MATERIALS_CLEAN_SLURRY.get(MUMaterial.DIAMOND),
            MoreMachineChemicals.UNSTABLE_DIMENSIONAL_GAS,
            MekUtItems.MU_MATERIALS_CRYSTAL.get(MUMaterial.DIAMOND),
            MekUtItems.MU_MATERIALS_SHARD.get(MUMaterial.DIAMOND),
            MekUtItems.MU_MATERIALS_CLUMP.get(MUMaterial.DIAMOND),
            null,
            MekanismItems.DIAMOND_DUST,
            2,
            Items.DIAMOND);

    public static final MekInMaterial EMERALD = new MekInMaterial("emerald",
            MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.EMERALD),
            MekInChemicals.EMERALD_PLASMA,
            MekInItems.CHARGED_EMERALD_SHARD,
            MekInItems.DIRTY_EMERALD_CRYSTAL,
            MekUtChemicals.MU_MATERIALS_DIRTY_SLURRY.get(MUMaterial.EMERALD),
            MekUtChemicals.MU_MATERIALS_CLEAN_SLURRY.get(MUMaterial.EMERALD),
            MSGases.BERYLLIUM,
            MekUtItems.MU_MATERIALS_CRYSTAL.get(MUMaterial.EMERALD),
            MekUtItems.MU_MATERIALS_SHARD.get(MUMaterial.EMERALD),
            MekUtItems.MU_MATERIALS_CLUMP.get(MUMaterial.EMERALD),
            null,
            MekanismItems.EMERALD_DUST,
            2,
            Items.EMERALD);

    public static final MekInMaterial LAPIS_LAZULI = new MekInMaterial("lapis_lazuli",
            MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.LAPIS_LAZULI),
            MekInChemicals.LAPIS_LAZULI_PLASMA,
            MekInItems.CHARGED_LAPIS_LAZULI_SHARD,
            MekInItems.DIRTY_LAPIS_LAZULI_CRYSTAL,
            MekUtChemicals.MU_MATERIALS_DIRTY_SLURRY.get(MUMaterial.LAPIS_LAZULI),
            MekUtChemicals.MU_MATERIALS_CLEAN_SLURRY.get(MUMaterial.LAPIS_LAZULI),
            MoreMachineChemicals.UNSTABLE_DIMENSIONAL_GAS,
            MekUtItems.MU_MATERIALS_CRYSTAL.get(MUMaterial.LAPIS_LAZULI),
            MekUtItems.MU_MATERIALS_SHARD.get(MUMaterial.LAPIS_LAZULI),
            MekUtItems.MU_MATERIALS_CLUMP.get(MUMaterial.LAPIS_LAZULI),
            null,
            MekanismItems.LAPIS_LAZULI_DUST,
            2,
            Items.LAPIS_LAZULI);

    public static final MekInMaterial QUARTZ = new MekInMaterial("quartz",
            MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.QUARTZ),
            MekInChemicals.QUARTZ_PLASMA,
            MekInItems.CHARGED_QUARTZ_SHARD,
            MekInItems.DIRTY_QUARTZ_CRYSTAL,
            MekUtChemicals.MU_MATERIALS_DIRTY_SLURRY.get(MUMaterial.QUARTZ),
            MekUtChemicals.MU_MATERIALS_CLEAN_SLURRY.get(MUMaterial.QUARTZ),
            MekUtChemicals.MU_MATERIALS_CLEAN_SLURRY.get(MUMaterial.CERTUS_QUARTZ),
            MekUtItems.MU_MATERIALS_CRYSTAL.get(MUMaterial.QUARTZ),
            MekUtItems.MU_MATERIALS_SHARD.get(MUMaterial.QUARTZ),
            MekUtItems.MU_MATERIALS_CLUMP.get(MUMaterial.QUARTZ),
            null,
            MekanismItems.QUARTZ_DUST,
            2,
            Items.QUARTZ);

    public static final MekInMaterial REDSTONE = new MekInMaterial("redstone",
            MekUtItems.RAW_MU_MATERIALS.get(MUMaterial.REDSTONE),
            MekInChemicals.REDSTONE_PLASMA,
            MekInItems.CHARGED_REDSTONE_SHARD,
            MekInItems.DIRTY_REDSTONE_CRYSTAL,
            MekUtChemicals.MU_MATERIALS_DIRTY_SLURRY.get(MUMaterial.REDSTONE),
            MekUtChemicals.MU_MATERIALS_CLEAN_SLURRY.get(MUMaterial.REDSTONE),
            MekUtChemicals.IRIDIUM,
            MekUtItems.MU_MATERIALS_CRYSTAL.get(MUMaterial.REDSTONE),
            MekUtItems.MU_MATERIALS_SHARD.get(MUMaterial.REDSTONE),
            MekUtItems.MU_MATERIALS_CLUMP.get(MUMaterial.REDSTONE),
            MekUtItems.MU_MATERIALS_DIRTY_DUST.get(MUMaterial.REDSTONE),
            Items.REDSTONE,
            3,
            Items.REDSTONE);

    public static final List<MekInMaterial> MATERIALS = List.of(new MekInMaterial[] {
            IRON, GOLD, COPPER, OSMIUM, LEAD, TIN, URANIUM, NAQUADAH, NETHERITE, COAL, DIAMOND, EMERALD, FLOURITE,
            CERTUS_QUARTZ, AMETHYST, LAPIS_LAZULI, QUARTZ, REDSTONE, ENTRO,
    });
}
