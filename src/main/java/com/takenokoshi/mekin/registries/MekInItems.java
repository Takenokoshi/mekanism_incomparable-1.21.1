package com.takenokoshi.mekin.registries;

import com.takenokoshi.mekin.core.MekInConstants;

import mekanism.api.text.EnumColor;
import mekanism.common.registration.impl.ItemDeferredRegister;
import mekanism.common.registration.impl.ItemRegistryObject;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class MekInItems {
    public static final ItemDeferredRegister ITEMS = new ItemDeferredRegister(MekInConstants.MODID);

    // alloy
    public static final ItemRegistryObject<Item> EXOREFRACTIVE_PRISMATIC_ALLOY = ITEMS
            .register("exorefractive_prismatic_alloy", EnumColor.DARK_BLUE);
    public static final ItemRegistryObject<Item> TRAJECTORY_ALLOY = ITEMS
            .register("trajectory_alloy", EnumColor.GRAY);
    public static final ItemRegistryObject<Item> RESONANCE_ALLOY = ITEMS
            .register("resonance_alloy", EnumColor.YELLOW);
    public static final ItemRegistryObject<Item> COODINATE_ALLOY = ITEMS
            .register("coodinate_alloy", EnumColor.INDIGO);

    // control circuit
    public static final ItemRegistryObject<Item> CONTROL_CIRCUIT_COMPONENT = ITEMS
            .register("control_circuit_component");
    public static final ItemRegistryObject<Item> CONTROL_CIRCUIT_CORE = ITEMS
            .register("control_circuit_core");
    public static final ItemRegistryObject<Item> ANALYSIS_CONTROL_CIRCUIT = ITEMS
            .register("analysis_control_circuit", EnumColor.DARK_BLUE);
    public static final ItemRegistryObject<Item> MEMORY_CONTROL_CIRCUIT = ITEMS
            .register("memory_control_circuit", EnumColor.GRAY);
    public static final ItemRegistryObject<Item> RELATIVITY_CONTROL_CIRCUIT = ITEMS
            .register("relativity_control_circuit", EnumColor.YELLOW);
    public static final ItemRegistryObject<Item> TIMESPACE_CONTROL_CIRCUIT = ITEMS
            .register("timespace_control_circuit", EnumColor.INDIGO);

    // processor
    public static final ItemRegistryObject<Item> PHOTON_PROCESSOR = ITEMS
            .register("photon_processor");
    public static final ItemRegistryObject<Item> GRAVITON_PROCESSOR = ITEMS
            .register("graviton_processor");
    public static final ItemRegistryObject<Item> DIMENTIONAL_PROCESSOR = ITEMS
            .register("dimentional_processor");
    public static final ItemRegistryObject<Item> OBSERVATION_PROCESSOR = ITEMS
            .register("observation_processor");
    public static final ItemRegistryObject<Item> TRANSMISSION_PROCESSOR = ITEMS
            .register("transmission_processor");
    public static final ItemRegistryObject<Item> RANDOM_PROCESSOR = ITEMS
            .register("random_processor");
    public static final ItemRegistryObject<Item> CLOCK_PROCESSOR = ITEMS
            .register("clock_processor");

    // printed processor
    public static final ItemRegistryObject<Item> PRINTED_PHOTON_PROCESSOR = ITEMS
            .register("printed_photon_processor");
    public static final ItemRegistryObject<Item> PRINTED_GRAVITON_PROCESSOR = ITEMS
            .register("printed_graviton_processor");
    public static final ItemRegistryObject<Item> PRINTED_DIMENTIONAL_PROCESSOR = ITEMS
            .register("printed_dimentional_processor");
    public static final ItemRegistryObject<Item> PRINTED_OBSERVATION_PROCESSOR = ITEMS
            .register("printed_observation_processor");
    public static final ItemRegistryObject<Item> PRINTED_TRANSMISSION_PROCESSOR = ITEMS
            .register("printed_transmission_processor");
    public static final ItemRegistryObject<Item> PRINTED_RANDOM_PROCESSOR = ITEMS
            .register("printed_random_processor");
    public static final ItemRegistryObject<Item> PRINTED_CLOCK_PROCESSOR = ITEMS
            .register("printed_clock_processor");

    // ingot
    public static final ItemRegistryObject<Item> SILVER_INGOT = ITEMS
            .register("silver_ingot");
    public static final ItemRegistryObject<Item> TITANIUM_INGOT = ITEMS
            .register("titanium_ingot");
    public static final ItemRegistryObject<Item> SCARLET_SILVER_INGOT = ITEMS
            .register("scarlet_silver_ingot");
    public static final ItemRegistryObject<Item> ASTRAL_GLOWSTONE_INGOT = ITEMS
            .register("astral_glowstone_ingot");
    public static final ItemRegistryObject<Item> NEUTRONIUM_INGOT = ITEMS
            .register("neutronium_ingot");
    public static final ItemRegistryObject<Item> SEMICONDUCTIVE_ALLOY_INGOT = ITEMS
            .register("semiconductive_alloy_ingot");
    public static final ItemRegistryObject<Item> METASTABLE_ALLOY_INGOT = ITEMS
            .register("metastable_alloy_ingot");
    public static final ItemRegistryObject<Item> ELECTRINE_INGOT = ITEMS
            .register("electrine_ingot");

    // dust
    public static final ItemRegistryObject<Item> SILVER_DUST = ITEMS
            .register("silver_dust");
    public static final ItemRegistryObject<Item> TITANIUM_DUST = ITEMS
            .register("titanium_dust");
    public static final ItemRegistryObject<Item> SCARLET_SILVER_DUST = ITEMS
            .register("scarlet_silver_dust");
    public static final ItemRegistryObject<Item> ELECTRINE_DUST = ITEMS
            .register("electrine_dust");

    // enriched
    public static final ItemRegistryObject<Item> ENRICHED_SCARLET_SILVER = ITEMS
            .register("enriched_scarlet_silver");
    public static final ItemRegistryObject<Item> ENRICHED_ELECTRINE = ITEMS
            .register("enriched_electrine");

    // synchronized
    public static final ItemRegistryObject<Item> ANTIMATTER_SYNCHRONIZED_AMETHYST_SHARD = registerFoiling(
            "antimatter_synchronized_amethyst_shard");
    public static final ItemRegistryObject<Item> AMETHYST_SYNCHRONIZED_METASTABLE_ALLOY_INGOT = registerFoiling(
            "amethyst_synchronized_metastable_alloy_ingot");
    public static final ItemRegistryObject<Item> NATURAL_ENVIRONMENT_SYNCHRONIZED_NEUTRONIUM_INGOT = registerFoiling(
            "natural_environment_synchronized_neutronium_ingot");
    public static final ItemRegistryObject<Item> CESIUM_133_SYNCHRONIZED_CERTUS_QUARTZ_CRYSTAL = registerFoiling(
            "cesium_133_synchronized_certus_quartz_crystal");

    // charged shard
    public static final ItemRegistryObject<Item> CHARGED_AMETHYST_SHARD = ITEMS.register("charged_amethyst_shard");
    public static final ItemRegistryObject<Item> CHARGED_CERTYS_QUARTZ_SHARD = ITEMS
            .register("charged_certus_quartz_shard");
    public static final ItemRegistryObject<Item> CHARGED_COAL_SHARD = ITEMS.register("charged_coal_shard");
    public static final ItemRegistryObject<Item> CHARGED_COPPER_SHARD = ITEMS.register("charged_copper_shard");
    public static final ItemRegistryObject<Item> CHARGED_DIAMOND_SHARD = ITEMS.register("charged_diamond_shard");
    public static final ItemRegistryObject<Item> CHARGED_EMERALD_SHARD = ITEMS.register("charged_emerald_shard");
    public static final ItemRegistryObject<Item> CHARGED_ENTRO_SHARD = ITEMS.register("charged_entro_shard");
    public static final ItemRegistryObject<Item> CHARGED_FLUORITE_SHARD = ITEMS.register("charged_fluorite_shard");
    public static final ItemRegistryObject<Item> CHARGED_GOLD_SHARD = ITEMS.register("charged_gold_shard");
    public static final ItemRegistryObject<Item> CHARGED_IRON_SHARD = ITEMS.register("charged_iron_shard");
    public static final ItemRegistryObject<Item> CHARGED_LAPIS_LAZULI_SHARD = ITEMS
            .register("charged_lapis_lazuli_shard");
    public static final ItemRegistryObject<Item> CHARGED_LEAD_SHARD = ITEMS.register("charged_lead_shard");
    public static final ItemRegistryObject<Item> CHARGED_NAQUADAH_SHARD = ITEMS.register("charged_naquadah_shard");
    public static final ItemRegistryObject<Item> CHARGED_NETHERITE_SHARD = ITEMS.register("charged_netherite_shard");
    public static final ItemRegistryObject<Item> CHARGED_OSMIUM_SHARD = ITEMS.register("charged_osmium_shard");
    public static final ItemRegistryObject<Item> CHARGED_QUARTZ_SHARD = ITEMS.register("charged_quartz_shard");
    public static final ItemRegistryObject<Item> CHARGED_REDSTONE_SHARD = ITEMS.register("charged_redstone_shard");
    public static final ItemRegistryObject<Item> CHARGED_TIN_SHARD = ITEMS.register("charged_tin_shard");
    public static final ItemRegistryObject<Item> CHARGED_URANIUM_SHARD = ITEMS.register("charged_uranium_shard");

    // dirty crystal
    public static final ItemRegistryObject<Item> DIRTY_AMETHYST_CRYSTAL = ITEMS.register("dirty_amethyst_crystal");
    public static final ItemRegistryObject<Item> DIRTY_CERTUS_QUARTZ_CRYSTAL = ITEMS
            .register("dirty_certus_quartz_crystal");
    public static final ItemRegistryObject<Item> DIRTY_COAL_CRYSTAL = ITEMS.register("dirty_coal_crystal");
    public static final ItemRegistryObject<Item> DIRTY_COPPER_CRYSTAL = ITEMS.register("dirty_copper_crystal");
    public static final ItemRegistryObject<Item> DIRTY_DIAMOND_CRYSTAL = ITEMS.register("dirty_diamond_crystal");
    public static final ItemRegistryObject<Item> DIRTY_EMERALD_CRYSTAL = ITEMS.register("dirty_emerald_crystal");
    public static final ItemRegistryObject<Item> DIRTY_ENTRO_CRYSTAL = ITEMS.register("dirty_entro_crystal");
    public static final ItemRegistryObject<Item> DIRTY_FLUORITE_CRYSTAL = ITEMS.register("dirty_fluorite_crystal");
    public static final ItemRegistryObject<Item> DIRTY_GOLD_CRYSTAL = ITEMS.register("dirty_gold_crystal");
    public static final ItemRegistryObject<Item> DIRTY_IRON_CRYSTAL = ITEMS.register("dirty_iron_crystal");
    public static final ItemRegistryObject<Item> DIRTY_LAPIS_LAZULI_CRYSTAL = ITEMS
            .register("dirty_lapis_lazuli_crystal");
    public static final ItemRegistryObject<Item> DIRTY_LEAD_CRYSTAL = ITEMS.register("dirty_lead_crystal");
    public static final ItemRegistryObject<Item> DIRTY_NAQUADAH_CRYSTAL = ITEMS.register("dirty_naquadah_crystal");
    public static final ItemRegistryObject<Item> DIRTY_NETHERITE_CRYSTAL = ITEMS.register("dirty_netherite_crystal");
    public static final ItemRegistryObject<Item> DIRTY_OSMIUM_CRYSTAL = ITEMS.register("dirty_osmium_crystal");
    public static final ItemRegistryObject<Item> DIRTY_QUARTZ_CRYSTAL = ITEMS.register("dirty_quartz_crystal");
    public static final ItemRegistryObject<Item> DIRTY_REDSTONE_CRYSTAL = ITEMS.register("dirty_redstone_crystal");
    public static final ItemRegistryObject<Item> DIRTY_TIN_CRYSTAL = ITEMS.register("dirty_tin_crystal");
    public static final ItemRegistryObject<Item> DIRTY_URANIUM_CRYSTAL = ITEMS.register("dirty_uranium_crystal");

    // starlight
    public static final ItemRegistryObject<Item> AMETHYST_STARLIGHT = registerFoiling("amethyst_starlight");
    public static final ItemRegistryObject<Item> CERTUS_QUARTZ_STARLIGHT = registerFoiling("certus_quartz_starlight");
    public static final ItemRegistryObject<Item> COAL_STARLIGHT = registerFoiling("coal_starlight");
    public static final ItemRegistryObject<Item> COPPER_STARLIGHT = registerFoiling("copper_starlight");
    public static final ItemRegistryObject<Item> DIAMOND_STARLIGHT = registerFoiling("diamond_starlight");
    public static final ItemRegistryObject<Item> EMERALD_STARLIGHT = registerFoiling("emerald_starlight");
    public static final ItemRegistryObject<Item> ENTRO_STARLIGHT = registerFoiling("entro_starlight");
    public static final ItemRegistryObject<Item> FLUORITE_STARLIGHT = registerFoiling("fluorite_starlight");
    public static final ItemRegistryObject<Item> GOLD_STARLIGHT = registerFoiling("gold_starlight");
    public static final ItemRegistryObject<Item> IRON_STARLIGHT = registerFoiling("iron_starlight");
    public static final ItemRegistryObject<Item> LAPIS_LAZULI_STARLIGHT = registerFoiling("lapis_lazuli_starlight");
    public static final ItemRegistryObject<Item> LEAD_STARLIGHT = registerFoiling("lead_starlight");
    public static final ItemRegistryObject<Item> NAQUADAH_STARLIGHT = registerFoiling("naquadah_starlight");
    public static final ItemRegistryObject<Item> NETHERITE_STARLIGHT = registerFoiling("netherite_starlight");
    public static final ItemRegistryObject<Item> OSMIUM_STARLIGHT = registerFoiling("osmium_starlight");
    public static final ItemRegistryObject<Item> QUARTZ_STARLIGHT = registerFoiling("quartz_starlight");
    public static final ItemRegistryObject<Item> REDSTONE_STARLIGHT = registerFoiling("redstone_starlight");
    public static final ItemRegistryObject<Item> TIN_STARLIGHT = registerFoiling("tin_starlight");
    public static final ItemRegistryObject<Item> URANIUM_STARLIGHT = registerFoiling("uranium_starlight");

    public static final ItemRegistryObject<Item> ETERNAL_STARLIGHT = ITEMS.register("eternal_starlight",
            EnumColor.DARK_BLUE);

    // misc
    public static final ItemRegistryObject<Item> SEMICONDUCTIVE_ALLOY_CIRCUIT_BOARD = ITEMS
            .register("semiconductive_alloy_circuit_board");
    public static final ItemRegistryObject<Item> NEUTRON_STAR_FRAGMENT = ITEMS
            .register("neutron_star_flagment");

    private static ItemRegistryObject<Item> registerFoiling(String name) {
        return ITEMS.registerItem(name, props -> new Item(props) {
            public boolean isFoil(ItemStack stack) {
                return true;
            };
        });
    }
}
