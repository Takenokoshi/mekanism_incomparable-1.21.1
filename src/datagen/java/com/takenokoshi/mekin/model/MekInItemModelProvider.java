package com.takenokoshi.mekin.model;

import com.jerry.mekextras.MekanismExtras;
import com.takenokoshi.mekin.core.MekInConstants;
import com.takenokoshi.mekin.registries.MekInItems;
import com.takenokoshi.mekut.core.MekUtConstants;

import appeng.core.AppEng;
import mekanism.common.Mekanism;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class MekInItemModelProvider extends ItemModelProvider {

    public MekInItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MekInConstants.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(MekInItems.EXOREFRACTIVE_PRISMATIC_ALLOY.get()).texture("layer0",
                MekInConstants.rl("item/alloy/exorefractive_prismatic"));
        simpleItem(MekInItems.TRAJECTORY_ALLOY.get()).texture("layer0",
                MekInConstants.rl("item/alloy/trajectory"));
        simpleItem(MekInItems.RESONANCE_ALLOY.get()).texture("layer0",
                MekInConstants.rl("item/alloy/resonance"));
        simpleItem(MekInItems.COODINATE_ALLOY.get()).texture("layer0",
                MekInConstants.rl("item/alloy/coodinate"));

        simpleItem(MekInItems.CONTROL_CIRCUIT_COMPONENT.get()).texture("layer0",
                MekInConstants.rl("item/control_circuit/component"));
        simpleItem(MekInItems.CONTROL_CIRCUIT_CORE.get()).texture("layer0",
                MekInConstants.rl("item/control_circuit/core"));
        simpleItem(MekInItems.ANALYSIS_CONTROL_CIRCUIT.get()).texture("layer0",
                MekInConstants.rl("item/control_circuit/analysis"));
        simpleItem(MekInItems.MEMORY_CONTROL_CIRCUIT.get()).texture("layer0",
                MekInConstants.rl("item/control_circuit/memory"));
        simpleItem(MekInItems.RELATIVITY_CONTROL_CIRCUIT.get()).texture("layer0",
                MekInConstants.rl("item/control_circuit/relativity"));
        simpleItem(MekInItems.TIMESPACE_CONTROL_CIRCUIT.get()).texture("layer0",
                MekInConstants.rl("item/control_circuit/timespace"));

        simpleItem(MekInItems.PHOTON_PROCESSOR.get()).texture("layer0",
                MekInConstants.rl("item/processor/photon"));
        simpleItem(MekInItems.GRAVITON_PROCESSOR.get()).texture("layer0",
                MekInConstants.rl("item/processor/graviton"));
        simpleItem(MekInItems.DIMENTIONAL_PROCESSOR.get()).texture("layer0",
                MekInConstants.rl("item/processor/dimentional"));
        simpleItem(MekInItems.OBSERVATION_PROCESSOR.get()).texture("layer0",
                MekInConstants.rl("item/processor/observation"));
        simpleItem(MekInItems.TRANSMISSION_PROCESSOR.get()).texture("layer0",
                MekInConstants.rl("item/processor/transmission"));
        simpleItem(MekInItems.RANDOM_PROCESSOR.get()).texture("layer0",
                MekInConstants.rl("item/processor/random"));
        simpleItem(MekInItems.CLOCK_PROCESSOR.get()).texture("layer0",
                MekInConstants.rl("item/processor/clock"));

        simpleItem(MekInItems.PRINTED_PHOTON_PROCESSOR.get()).texture("layer0",
                MekInConstants.rl("item/printed_processor/photon"));
        simpleItem(MekInItems.PRINTED_GRAVITON_PROCESSOR.get()).texture("layer0",
                MekInConstants.rl("item/printed_processor/graviton"));
        simpleItem(MekInItems.PRINTED_DIMENTIONAL_PROCESSOR.get()).texture("layer0",
                MekInConstants.rl("item/printed_processor/dimentional"));
        simpleItem(MekInItems.PRINTED_OBSERVATION_PROCESSOR.get()).texture("layer0",
                MekInConstants.rl("item/printed_processor/observation"));
        simpleItem(MekInItems.PRINTED_TRANSMISSION_PROCESSOR.get()).texture("layer0",
                MekInConstants.rl("item/printed_processor/transmission"));
        simpleItem(MekInItems.PRINTED_RANDOM_PROCESSOR.get()).texture("layer0",
                MekInConstants.rl("item/printed_processor/random"));
        simpleItem(MekInItems.PRINTED_CLOCK_PROCESSOR.get()).texture("layer0",
                MekInConstants.rl("item/printed_processor/clock"));

        simpleItem(MekInItems.SILVER_INGOT.get()).texture("layer0",
                MekInConstants.rl("item/ingot/silver"));
        simpleItem(MekInItems.TITANIUM_INGOT.get()).texture("layer0",
                MekInConstants.rl("item/ingot/titanium"));
        simpleItem(MekInItems.SCARLET_SILVER_INGOT.get()).texture("layer0",
                MekInConstants.rl("item/ingot/scarlet_silver"));
        simpleItem(MekInItems.ASTRAL_GLOWSTONE_INGOT.get()).texture("layer0",
                MekInConstants.rl("item/ingot/astral_glowstone"));
        simpleItem(MekInItems.NEUTRONIUM_INGOT.get()).texture("layer0",
                MekInConstants.rl("item/ingot/neutronium"));
        simpleItem(MekInItems.SEMICONDUCTIVE_ALLOY_INGOT.get()).texture("layer0",
                MekInConstants.rl("item/ingot/semiconductive_alloy"));
        simpleItem(MekInItems.METASTABLE_ALLOY_INGOT.get()).texture("layer0",
                MekInConstants.rl("item/ingot/metastable_alloy"));
        simpleItem(MekInItems.ELECTRINE_INGOT.get()).texture("layer0",
                MekInConstants.rl("item/ingot/electrine"));

        simpleItem(MekInItems.SILVER_DUST.get()).texture("layer0",
                MekInConstants.rl("item/dust/silver"));
        simpleItem(MekInItems.TITANIUM_DUST.get()).texture("layer0",
                MekInConstants.rl("item/dust/titanium"));
        simpleItem(MekInItems.SCARLET_SILVER_DUST.get()).texture("layer0",
                MekInConstants.rl("item/dust/scarlet_silver"));
        simpleItem(MekInItems.ELECTRINE_DUST.get()).texture("layer0",
                MekInConstants.rl("item/dust/electrine"));

        simpleItem(MekInItems.ENRICHED_SCARLET_SILVER.get()).texture("layer0",
                MekInConstants.rl("item/enriched/scarlet_silver"));
        simpleItem(MekInItems.ENRICHED_ELECTRINE.get()).texture("layer0",
                MekInConstants.rl("item/enriched/electrine"));

        simpleItem(MekInItems.ANTIMATTER_SYNCHRONIZED_AMETHYST_SHARD.get()).texture("layer0",
                mcLoc("item/amethyst_shard"));
        simpleItem(MekInItems.AMETHYST_SYNCHRONIZED_METASTABLE_ALLOY_INGOT.get()).texture("layer0",
                MekInConstants.rl("item/ingot/semiconductive_alloy"));
        simpleItem(MekInItems.NATURAL_ENVIRONMENT_SYNCHRONIZED_NEUTRONIUM_INGOT.get()).texture("layer0",
                MekInConstants.rl("item/ingot/neutronium"));
        existingFileHelper.trackGenerated(AppEng.makeId("item/certus_quartz_crystal"), TEXTURE);
        simpleItem(MekInItems.CESIUM_133_SYNCHRONIZED_CERTUS_QUARTZ_CRYSTAL.get()).texture("layer0",
                AppEng.makeId("item/certus_quartz_crystal"));

        simpleItem(MekInItems.DIRTY_AMETHYST_CRYSTAL.get())
                .texture("layer0", uncheckedTexture(MekUtConstants.rl("item/crystal/amethyst")))
                .texture("layer1", MekInConstants.rl("item/crystal/dirty"));
        simpleItem(MekInItems.DIRTY_CERTUS_QUARTZ_CRYSTAL.get())
                .texture("layer0", uncheckedTexture(MekUtConstants.rl("item/crystal/certus_quartz")))
                .texture("layer1", MekInConstants.rl("item/crystal/dirty"));
        simpleItem(MekInItems.DIRTY_COAL_CRYSTAL.get())
                .texture("layer0", uncheckedTexture(MekUtConstants.rl("item/crystal/coal")))
                .texture("layer1", MekInConstants.rl("item/crystal/dirty"));
        simpleItem(MekInItems.DIRTY_COPPER_CRYSTAL.get())
                .texture("layer0", uncheckedTexture(Mekanism.rl("item/crystal_copper")))
                .texture("layer1", MekInConstants.rl("item/crystal/dirty"));
        simpleItem(MekInItems.DIRTY_DIAMOND_CRYSTAL.get())
                .texture("layer0", uncheckedTexture(MekUtConstants.rl("item/crystal/diamond")))
                .texture("layer1", MekInConstants.rl("item/crystal/dirty"));
        simpleItem(MekInItems.DIRTY_EMERALD_CRYSTAL.get())
                .texture("layer0", uncheckedTexture(MekUtConstants.rl("item/crystal/emerald")))
                .texture("layer1", MekInConstants.rl("item/crystal/dirty"));
        simpleItem(MekInItems.DIRTY_ENTRO_CRYSTAL.get())
                .texture("layer0", uncheckedTexture(MekUtConstants.rl("item/crystal/entro")))
                .texture("layer1", MekInConstants.rl("item/crystal/dirty"));
        simpleItem(MekInItems.DIRTY_FLUORITE_CRYSTAL.get())
                .texture("layer0", uncheckedTexture(MekUtConstants.rl("item/crystal/fluorite")))
                .texture("layer1", MekInConstants.rl("item/crystal/dirty"));
        simpleItem(MekInItems.DIRTY_GOLD_CRYSTAL.get())
                .texture("layer0", uncheckedTexture(Mekanism.rl("item/crystal_gold")))
                .texture("layer1", MekInConstants.rl("item/crystal/dirty"));
        simpleItem(MekInItems.DIRTY_IRON_CRYSTAL.get())
                .texture("layer0", uncheckedTexture(Mekanism.rl("item/crystal_iron")))
                .texture("layer1", MekInConstants.rl("item/crystal/dirty"));
        simpleItem(MekInItems.DIRTY_LAPIS_LAZULI_CRYSTAL.get())
                .texture("layer0", uncheckedTexture(MekUtConstants.rl("item/crystal/lapis_lazuli")))
                .texture("layer1", MekInConstants.rl("item/crystal/dirty"));
        simpleItem(MekInItems.DIRTY_LEAD_CRYSTAL.get())
                .texture("layer0", uncheckedTexture(Mekanism.rl("item/crystal_lead")))
                .texture("layer1", MekInConstants.rl("item/crystal/dirty"));
        simpleItem(MekInItems.DIRTY_NAQUADAH_CRYSTAL.get())
                .texture("layer0", uncheckedTexture(MekanismExtras.rl("item/crystal_naquadah")))
                .texture("layer1", MekInConstants.rl("item/crystal/dirty"));
        simpleItem(MekInItems.DIRTY_NETHERITE_CRYSTAL.get())
                .texture("layer0", uncheckedTexture(MekUtConstants.rl("item/crystal/netherite")))
                .texture("layer1", MekInConstants.rl("item/crystal/dirty"));
        simpleItem(MekInItems.DIRTY_OSMIUM_CRYSTAL.get())
                .texture("layer0", uncheckedTexture(Mekanism.rl("item/crystal_osmium")))
                .texture("layer1", MekInConstants.rl("item/crystal/dirty"));
        simpleItem(MekInItems.DIRTY_QUARTZ_CRYSTAL.get())
                .texture("layer0", uncheckedTexture(MekUtConstants.rl("item/crystal/quartz")))
                .texture("layer1", MekInConstants.rl("item/crystal/dirty"));
        simpleItem(MekInItems.DIRTY_REDSTONE_CRYSTAL.get())
                .texture("layer0", uncheckedTexture(MekUtConstants.rl("item/crystal/redstone")))
                .texture("layer1", MekInConstants.rl("item/crystal/dirty"));
        simpleItem(MekInItems.DIRTY_TIN_CRYSTAL.get())
                .texture("layer0", uncheckedTexture(Mekanism.rl("item/crystal_tin")))
                .texture("layer1", MekInConstants.rl("item/crystal/dirty"));
        simpleItem(MekInItems.DIRTY_URANIUM_CRYSTAL.get())
                .texture("layer0", uncheckedTexture(Mekanism.rl("item/crystal_uranium")))
                .texture("layer1", MekInConstants.rl("item/crystal/dirty"));

        simpleItem(MekInItems.CHARGED_AMETHYST_SHARD.get())
                .texture("layer0", uncheckedTexture(MekUtConstants.rl("item/shard/amethyst")))
                .texture("layer1", MekInConstants.rl("item/shard/charged"));
        simpleItem(MekInItems.CHARGED_CERTYS_QUARTZ_SHARD.get())
                .texture("layer0", uncheckedTexture(MekUtConstants.rl("item/shard/certus_quartz")))
                .texture("layer1", MekInConstants.rl("item/shard/charged"));
        simpleItem(MekInItems.CHARGED_COAL_SHARD.get())
                .texture("layer0", uncheckedTexture(MekUtConstants.rl("item/shard/coal")))
                .texture("layer1", MekInConstants.rl("item/shard/charged"));
        simpleItem(MekInItems.CHARGED_COPPER_SHARD.get())
                .texture("layer0", uncheckedTexture(Mekanism.rl("item/shard_copper")))
                .texture("layer1", MekInConstants.rl("item/shard/charged"));
        simpleItem(MekInItems.CHARGED_DIAMOND_SHARD.get())
                .texture("layer0", uncheckedTexture(MekUtConstants.rl("item/shard/diamond")))
                .texture("layer1", MekInConstants.rl("item/shard/charged"));
        simpleItem(MekInItems.CHARGED_EMERALD_SHARD.get())
                .texture("layer0", uncheckedTexture(MekUtConstants.rl("item/shard/emerald")))
                .texture("layer1", MekInConstants.rl("item/shard/charged"));
        simpleItem(MekInItems.CHARGED_ENTRO_SHARD.get())
                .texture("layer0", uncheckedTexture(MekUtConstants.rl("item/shard/entro")))
                .texture("layer1", MekInConstants.rl("item/shard/charged"));
        simpleItem(MekInItems.CHARGED_FLUORITE_SHARD.get())
                .texture("layer0", uncheckedTexture(MekUtConstants.rl("item/shard/fluorite")))
                .texture("layer1", MekInConstants.rl("item/shard/charged"));
        simpleItem(MekInItems.CHARGED_GOLD_SHARD.get())
                .texture("layer0", uncheckedTexture(Mekanism.rl("item/shard_gold")))
                .texture("layer1", MekInConstants.rl("item/shard/charged"));
        simpleItem(MekInItems.CHARGED_IRON_SHARD.get())
                .texture("layer0", uncheckedTexture(Mekanism.rl("item/shard_iron")))
                .texture("layer1", MekInConstants.rl("item/shard/charged"));
        simpleItem(MekInItems.CHARGED_LAPIS_LAZULI_SHARD.get())
                .texture("layer0", uncheckedTexture(MekUtConstants.rl("item/shard/lapis_lazuli")))
                .texture("layer1", MekInConstants.rl("item/shard/charged"));
        simpleItem(MekInItems.CHARGED_LEAD_SHARD.get())
                .texture("layer0", uncheckedTexture(Mekanism.rl("item/shard_lead")))
                .texture("layer1", MekInConstants.rl("item/shard/charged"));
        simpleItem(MekInItems.CHARGED_NAQUADAH_SHARD.get())
                .texture("layer0", uncheckedTexture(MekanismExtras.rl("item/shard_naquadah")))
                .texture("layer1", MekInConstants.rl("item/shard/charged"));
        simpleItem(MekInItems.CHARGED_NETHERITE_SHARD.get())
                .texture("layer0", uncheckedTexture(MekUtConstants.rl("item/shard/netherite")))
                .texture("layer1", MekInConstants.rl("item/shard/charged"));
        simpleItem(MekInItems.CHARGED_OSMIUM_SHARD.get())
                .texture("layer0", uncheckedTexture(Mekanism.rl("item/shard_osmium")))
                .texture("layer1", MekInConstants.rl("item/shard/charged"));
        simpleItem(MekInItems.CHARGED_QUARTZ_SHARD.get())
                .texture("layer0", uncheckedTexture(MekUtConstants.rl("item/shard/quartz")))
                .texture("layer1", MekInConstants.rl("item/shard/charged"));
        simpleItem(MekInItems.CHARGED_REDSTONE_SHARD.get())
                .texture("layer0", uncheckedTexture(MekUtConstants.rl("item/shard/redstone")))
                .texture("layer1", MekInConstants.rl("item/shard/charged"));
        simpleItem(MekInItems.CHARGED_TIN_SHARD.get())
                .texture("layer0", uncheckedTexture(Mekanism.rl("item/shard_tin")))
                .texture("layer1", MekInConstants.rl("item/shard/charged"));
        simpleItem(MekInItems.CHARGED_URANIUM_SHARD.get())
                .texture("layer0", uncheckedTexture(Mekanism.rl("item/shard_uranium")))
                .texture("layer1", MekInConstants.rl("item/shard/charged"));

        simpleItem(MekInItems.AMETHYST_STARLIGHT.get()).texture("layer0", MekInConstants.rl("item/starlight/amethyst"));
        simpleItem(MekInItems.CERTUS_QUARTZ_STARLIGHT.get()).texture("layer0",
                MekInConstants.rl("item/starlight/certus_quartz"));
        simpleItem(MekInItems.COAL_STARLIGHT.get()).texture("layer0", MekInConstants.rl("item/starlight/coal"));
        simpleItem(MekInItems.COPPER_STARLIGHT.get()).texture("layer0", MekInConstants.rl("item/starlight/copper"));
        simpleItem(MekInItems.DIAMOND_STARLIGHT.get()).texture("layer0", MekInConstants.rl("item/starlight/diamond"));
        simpleItem(MekInItems.EMERALD_STARLIGHT.get()).texture("layer0", MekInConstants.rl("item/starlight/emerald"));
        simpleItem(MekInItems.ENTRO_STARLIGHT.get()).texture("layer0", MekInConstants.rl("item/starlight/entro"));
        simpleItem(MekInItems.FLUORITE_STARLIGHT.get()).texture("layer0", MekInConstants.rl("item/starlight/fluorite"));
        simpleItem(MekInItems.GOLD_STARLIGHT.get()).texture("layer0", MekInConstants.rl("item/starlight/gold"));
        simpleItem(MekInItems.IRON_STARLIGHT.get()).texture("layer0", MekInConstants.rl("item/starlight/iron"));
        simpleItem(MekInItems.LAPIS_LAZULI_STARLIGHT.get()).texture("layer0",
                MekInConstants.rl("item/starlight/lapis_lazuli"));
        simpleItem(MekInItems.LEAD_STARLIGHT.get()).texture("layer0", MekInConstants.rl("item/starlight/lead"));
        simpleItem(MekInItems.NAQUADAH_STARLIGHT.get()).texture("layer0", MekInConstants.rl("item/starlight/naquadah"));
        simpleItem(MekInItems.NETHERITE_STARLIGHT.get()).texture("layer0",
                MekInConstants.rl("item/starlight/netherite"));
        simpleItem(MekInItems.OSMIUM_STARLIGHT.get()).texture("layer0", MekInConstants.rl("item/starlight/osmium"));
        simpleItem(MekInItems.QUARTZ_STARLIGHT.get()).texture("layer0", MekInConstants.rl("item/starlight/quartz"));
        simpleItem(MekInItems.REDSTONE_STARLIGHT.get()).texture("layer0", MekInConstants.rl("item/starlight/redstone"));
        simpleItem(MekInItems.TIN_STARLIGHT.get()).texture("layer0", MekInConstants.rl("item/starlight/tin"));
        simpleItem(MekInItems.URANIUM_STARLIGHT.get()).texture("layer0", MekInConstants.rl("item/starlight/uranium"));

        simpleItem(MekInItems.ETERNAL_STARLIGHT.get()).texture("layer0", MekInConstants.rl("item/starlight/eternal"));

        simpleItem(MekInItems.SEMICONDUCTIVE_ALLOY_CIRCUIT_BOARD.get()).texture("layer0",
                MekInConstants.rl("item/misc/semiconductive_alloy_circuit_board"));
        simpleItem(MekInItems.NEUTRON_STAR_FRAGMENT.get()).texture("layer0",
                MekInConstants.rl("item/misc/neutron_star_flagment"));
    }

    public ItemModelBuilder simpleItem(Item item) {
        return getBuilder(BuiltInRegistries.ITEM.getKey(item).toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"));
    }

    public ResourceLocation uncheckedTexture(ResourceLocation location) {
        existingFileHelper.trackGenerated(location, TEXTURE);
        return location;
    }

}
