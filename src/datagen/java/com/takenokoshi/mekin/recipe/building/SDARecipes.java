package com.takenokoshi.mekin.recipe.building;

import java.util.ArrayList;
import java.util.List;

import com.extendedae_plus.init.ModItems;
import com.fxd927.mekanismelements.common.registries.MSFluids;
import com.fxd927.mekanismelements.common.registries.MSGases;
import com.github.misosouptgit.mwgr.MekanismWaterGeneratorRebuild;
import com.jerry.genextras.common.registries.GenExtraBlocks;
import com.jerry.mekextras.common.registries.ExtraBlocks;
import com.jerry.mekextras.common.registries.ExtraChemicals;
import com.jerry.mekextras.common.registries.ExtraItems;
import com.jerry.meklg.common.registries.LargeGeneratorBlocks;
import com.jerry.meklm.common.registries.LargeMachineBlocks;
import com.jerry.mekmm.common.registries.MoreMachineChemicals;
import com.jerry.mekmm.common.registries.MoreMachineItems;
import com.takenokoshi.mekin.core.MekInConstants;
import com.takenokoshi.mekin.registries.MekInAbsoluteMachines;
import com.takenokoshi.mekin.registries.MekInBlocks;
import com.takenokoshi.mekin.registries.MekInChemicals;
import com.takenokoshi.mekin.registries.MekInFluids;
import com.takenokoshi.mekin.registries.MekInInfiniteMachines;
import com.takenokoshi.mekin.registries.MekInItems;
import com.takenokoshi.mekin.registries.MekInMachines;
import com.takenokoshi.mekin.registries.MekInSupremeMachines;
import com.takenokoshi.mekut.recipe.builder.ItemStackListFluidChemicalToItemRecipeBuilder;
import com.takenokoshi.mekut.registries.MekUtChemicals;
import com.takenokoshi.mekut.registries.MekUtItems;
import com.takenokoshi.mekut.registries.MekUtMachines;

import appeng.core.definitions.AEBlocks;
import appeng.core.definitions.AEItems;
import fr.iglee42.emgenerators.registries.EMGenBlocks;
import fr.iglee42.evolvedmekanism.registries.EMBlocks;
import fr.iglee42.evolvedmekanism.registries.EMItems;
import gripe._90.megacells.definition.MEGAItems;
import io.github.masyumero.emextras.common.registry.EMExtraItems;
import mekanism.common.registries.MekanismBlocks;
import mekanism.common.registries.MekanismChemicals;
import mekanism.common.registries.MekanismFluids;
import mekanism.common.registries.MekanismItems;
import mekanism.common.resource.PrimaryResource;
import mekanism.common.resource.ResourceType;
import mekanism.common.tags.MekanismTags;
import mekanism.generators.common.registries.GeneratorsBlocks;
import mekanism.generators.common.registries.GeneratorsFluids;
import mekanism.tools.common.registries.ToolsItems;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;
import net.pedroksl.advanced_ae.common.definitions.AAEFluids;

public class SDARecipes {

    private static final List<MachineData> ABSOLUTE_MACHINES;
    private static final List<MachineData> SUPREME_MACHINES;
    private static final List<MachineData> INFINITE_MACHINES;

    public static void buildRecipes(RecipeOutput output) {
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(ModItems.BASIC_CORE.asItem(), 1))
                .addItemInput(MEGAItems.BULK_CELL_COMPONENT, 4)
                .addItemInput(Items.NETHERITE_INGOT, 64)
                .addItemInput(AEItems.LOGIC_PROCESSOR, 32)
                .addItemInput(AEItems.CALCULATION_PROCESSOR, 32)
                .addItemInput(AEItems.ENGINEERING_PROCESSOR, 32)
                .addItemInput(MekInItems.DIMENTIONAL_PROCESSOR, 1)
                .setFluidInput(MekInFluids.FLUX.asStack(10000))
                .setChemicalInput(MekUtChemicals.ASTRAL_ETHER.asStack(2000))
                .setEnergyRequired(8000000)
                .build(output, MekInConstants.rl("small_digital_assembler/basic_core"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(ModItems.INFINITY_CORE.asItem(), 1))
                .addItemInput(ModItems.QUANTUM_STORAGE_CORE, 8)
                .addItemInput(ModItems.STORAGE_CORE, 8)
                .addItemInput(ModItems.ENERGY_STORAGE_CORE, 8)
                .addItemInput(ModItems.SPATIAL_CORE, 8)
                .addItemInput(ModItems.OBLIVION_SINGULARITY, 16)
                .addItemInput(Items.NETHERITE_INGOT, 64)
                .setFluidInput(MekInFluids.FLUX.asStack(10000))
                .setChemicalInput(MekanismChemicals.ANTIMATTER.asStack(10000))
                .setEnergyRequired(8000000)
                .build(output, MekInConstants.rl("small_digital_assembler/infinity_core"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(MekInBlocks.TITANIUM_CASING, 1))
                .addItemInput(MekInItems.TITANIUM_INGOT, 16)
                .addItemInput(MekanismBlocks.STEEL_CASING, 256)
                .setFluidInput(Tags.Fluids.WATER, 10000)
                .setChemicalInput(MekUtChemicals.IRIDIUM.asStack(4_000_000L))
                .build(output, MekInConstants.rl("small_digital_assembler/titanium_casing"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(MekanismBlocks.SPS_CASING, 1))
                .addItemInput(MekanismBlocks.STEEL_CASING, 4)
                .addItemInput(MekanismItems.POLONIUM_PELLET, 16)
                .addItemInput(MekanismItems.PLUTONIUM_PELLET, 4)
                .addItemInput(MekanismItems.HDPE_SHEET, 64)
                .setFluidInput(Tags.Fluids.WATER, 4000)
                .setChemicalInput(MekanismChemicals.ETHENE.asStack(10000L))
                .build(output, MekInConstants.rl("small_digital_assembler/sps_casing"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(MekanismBlocks.BOILER_CASING, 4))
                .addItemInput(MekanismBlocks.STEEL_CASING, 1)
                .addItemInput(Tags.Items.INGOTS_IRON, 4)
                .setFluidInput(Tags.Fluids.WATER, 100)
                .setChemicalInput(MekanismChemicals.TIN.asStack(80L))
                .build(output, MekInConstants.rl("small_digital_assembler/boiler_casing"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(MekanismBlocks.THERMAL_EVAPORATION_BLOCK, 4))
                .addItemInput(Tags.Items.INGOTS_COPPER, 4)
                .addItemInput(MekanismTags.Items.INGOTS_STEEL, 4)
                .setFluidInput(Tags.Fluids.WATER, 100)
                .setChemicalInput(MekanismChemicals.REDSTONE.asStack(10L))
                .build(output, MekInConstants.rl("small_digital_assembler/thermal_evaporation_block"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(GeneratorsBlocks.FISSION_REACTOR_CASING, 4))
                .addItemInput(MekanismBlocks.STEEL_CASING, 1)
                .addItemInput(MekanismTags.Items.PROCESSED_RESOURCES.get(ResourceType.INGOT, PrimaryResource.LEAD), 4)
                .setFluidInput(Tags.Fluids.WATER, 100)
                .setChemicalInput(MekanismChemicals.TIN.asStack(80L))
                .build(output, MekInConstants.rl("small_digital_assembler/fission_reactor_casing"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(GeneratorsBlocks.CONTROL_ROD_ASSEMBLY, 1))
                .addItemInput(MekanismItems.ELITE_CONTROL_CIRCUIT, 1)
                .addItemInput(MekanismTags.Items.INGOTS_STEEL, 8)
                .addItemInput(MekanismTags.Items.PROCESSED_RESOURCES.get(ResourceType.INGOT, PrimaryResource.LEAD), 8)
                .setFluidInput(Tags.Fluids.WATER, 100)
                .setChemicalInput(MekanismChemicals.GOLD.asStack(80L))
                .build(output, MekInConstants.rl("small_digital_assembler/control_rod_assembly"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(GeneratorsBlocks.FISSION_FUEL_ASSEMBLY, 1))
                .addItemInput(MekanismBlocks.DYNAMIC_TANK, 4)
                .addItemInput(MekanismTags.Items.PROCESSED_RESOURCES.get(ResourceType.INGOT, PrimaryResource.LEAD), 4)
                .setFluidInput(Tags.Fluids.WATER, 100)
                .setChemicalInput(MekanismChemicals.GOLD.asStack(80L))
                .build(output, MekInConstants.rl("small_digital_assembler/fission_fuel_assembly"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(GeneratorsBlocks.FUSION_REACTOR_FRAME, 4))
                .addItemInput(MekanismBlocks.STEEL_CASING, 4)
                .addItemInput(MekanismTags.Items.PELLETS_POLONIUM, 8)
                .addItemInput(MekanismTags.Items.ALLOYS_ATOMIC, 16)
                .setFluidInput(Tags.Fluids.WATER, 1000)
                .setChemicalInput(MekanismChemicals.GOLD.asStack(80L))
                .build(output, MekInConstants.rl("small_digital_assembler/fusion_reactor_frame"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(
                        new ItemStack(MekanismWaterGeneratorRebuild.LAVA_GENERATOR_ITEM.getDelegate(), 1))
                .addItemInput(MekanismBlocks.STEEL_CASING, 16)
                .addItemInput(MekanismTags.Items.INGOTS_REFINED_OBSIDIAN, 64)
                .addItemInput(AEBlocks.QUARTZ_VIBRANT_GLASS, 64)
                .setChemicalInput(ExtraChemicals.MOLTEN_THERMONUCLEAR.asStack(48000L))
                .setFluidInput(MSFluids.SUPERHEATED_HELIUM.asStack(50000))
                .build(output, MekInConstants.rl("small_digital_assembler/lava_generator"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(
                        new ItemStack(MekanismWaterGeneratorRebuild.HEAVY_WATER_GENERATOR_ITEM.getDelegate(), 1))
                .addItemInput(MekanismWaterGeneratorRebuild.WATER_GENERATOR_ITEM, 64)
                .addItemInput(MekanismBlocks.DYNAMIC_TANK, 16)
                .setChemicalInput(MekanismChemicals.TIN.asStack(4800L))
                .setFluidInput(AAEFluids.QUANTUM_INFUSION.stack(1000))
                .build(output, MekInConstants.rl("small_digital_assembler/heavywater_generator"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(MekanismItems.ELECTROLYTIC_CORE.asStack(1))
                .addItemInput(MekanismItems.INFUSED_ALLOY.asStack(32))
                .addItemInput(MekanismTags.Items.PROCESSED_RESOURCES.get(ResourceType.DUST, PrimaryResource.OSMIUM), 16)
                .addItemInput(MekanismTags.Items.PROCESSED_RESOURCES.get(ResourceType.DUST, PrimaryResource.IRON), 8)
                .addItemInput(MekanismTags.Items.PROCESSED_RESOURCES.get(ResourceType.DUST, PrimaryResource.GOLD), 8)
                .setFluidInput(Tags.Fluids.WATER, 1000)
                .setChemicalInput(MekUtChemicals.FLUIX.asStack(80L))
                .build(output, MekInConstants.rl("small_digital_assembler/electrolytic_core"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(MekanismItems.TELEPORTATION_CORE.asStack(1))
                .addItemInput(MekanismItems.ATOMIC_ALLOY.asStack(16))
                .addItemInput(Tags.Items.GEMS_DIAMOND, 4)
                .addItemInput(Items.ENDER_PEARL, 64)
                .setFluidInput(Tags.Fluids.WATER, 1000)
                .setChemicalInput(MekanismChemicals.GOLD.asStack(2560L))
                .build(output, MekInConstants.rl("small_digital_assembler/teleportation_core"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(MekanismItems.ROBIT.asStack(1))
                .addItemInput(MekanismItems.ATOMIC_ALLOY, 4)
                .addItemInput(MekanismTags.Items.INGOTS_REFINED_OBSIDIAN, 16)
                .addItemInput(MekanismTags.Items.INGOTS_STEEL, 16)
                .addItemInput(MekanismTags.Items.PERSONAL_STORAGE, 1)
                .addItemInput(MekanismItems.ENERGY_TABLET, 16)
                .setFluidInput(Tags.Fluids.WATER, 1000)
                .setChemicalInput(MekanismChemicals.GOLD.asStack(2560L))
                .build(output, MekInConstants.rl("small_digital_assembler/robit"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(MoreMachineItems.ADVANCED_ELECTROLYSIS_CORE.asStack(1))
                .addItemInput(MekanismItems.ELECTROLYTIC_CORE.asStack(8))
                .addItemInput(MekanismItems.ATOMIC_ALLOY.asStack(32))
                .addItemInput(MekanismTags.Items.DUSTS_NETHERITE, 16)
                .addItemInput(MekanismTags.Items.DUSTS_DIAMOND, 8)
                .addItemInput(MekanismTags.Items.DUSTS_LAPIS, 8)
                .setFluidInput(Tags.Fluids.LAVA, 10000)
                .setChemicalInput(MSGases.HELIUM.asStack(1000L))
                .build(output, MekInConstants.rl("small_digital_assembler/advanced_electrolysis_core"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(MekInMachines.ANTINEUTRONIC_EXISTENCE_TRANSMUTATOR, 1))
                .addItemInput(EMExtraItems.COSMIC_DENSE_CONTROL_CIRCUIT, 32)
                .addItemInput(ExtraItems.SHINING_ALLOY, 64)
                .addItemInput(EMItems.SINGULAR_ALLOY, 64)
                .addItemInput(MekInBlocks.TITANIUM_CASING, 256)
                .addItemInput(MekanismBlocks.ANTIPROTONIC_NUCLEOSYNTHESIZER, 16)
                .setFluidInput(GeneratorsFluids.FUSION_FUEL.asStack(10000))
                .setChemicalInput(MekanismChemicals.ANTIMATTER.asStack(10000))
                .build(output, MekInConstants
                        .rl("small_digital_assembler/machine/normal/antineutronic_existence_transmutator"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(MekInMachines.CHEMICAL_EXTRACTOR, 1))
                .addItemInput(EMExtraItems.SUPREME_QUANTUM_CONTROL_CIRCUIT, 2)
                .addItemInput(ExtraItems.THERMONUCLEAR_ALLOY, 2)
                .addItemInput(EMItems.SUBATOMIC_ALLOY, 2)
                .addItemInput(MekUtItems.IRIDIUM_DUST, 8)
                .addItemInput(MekanismBlocks.CHEMICAL_WASHER, 1)
                .addItemInput(MekanismBlocks.ELECTROLYTIC_SEPARATOR, 1)
                .setFluidInput(AAEFluids.QUANTUM_INFUSION.stack(1000))
                .setChemicalInput(MekanismChemicals.LITHIUM.asStack(1000))
                .build(output, MekInConstants.rl("small_digital_assembler/machine/normal/chamical_extractor"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(MekInMachines.CHEMICAL_LEACHING_CHAMBER, 1))
                .addItemInput(EMExtraItems.ABSOLUTE_OVERCLOCKED_CONTROL_CIRCUIT, 2)
                .addItemInput(ExtraItems.RADIANCE_ALLOY, 2)
                .addItemInput(EMItems.HYPERCHARGED_ALLOY, 2)
                .addItemInput(MekanismBlocks.PRESSURIZED_REACTION_CHAMBER, 1)
                .addItemInput(MekanismTags.Items.INGOTS_BRONZE, 128)
                .setFluidInput(GeneratorsFluids.FUSION_FUEL.asStack(2000))
                .setChemicalInput(MekanismChemicals.OSMIUM.asStack(6400))
                .build(output, MekInConstants.rl("small_digital_assembler/machine/normal/chamical_leaching_chamber"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(MekInMachines.CHEMICAL_REFINER, 1))
                .addItemInput(ExtraItems.SUPREME_CONTROL_CIRCUIT, 8)
                .addItemInput(ExtraItems.THERMONUCLEAR_ALLOY, 16)
                .addItemInput(MekanismBlocks.CHEMICAL_INJECTION_CHAMBER, 1)
                .addItemInput(MekanismBlocks.PURIFICATION_CHAMBER, 1)
                .addItemInput(MekanismBlocks.CRUSHER, 1)
                .addItemInput(MekanismBlocks.ENRICHMENT_CHAMBER, 1)
                .addItemInput(MekanismBlocks.ENERGIZED_SMELTER, 1)
                .setFluidInput(Tags.Fluids.LAVA, 2000)
                .setChemicalInput(MekanismChemicals.LITHIUM.asStack(4000))
                .build(output, MekInConstants.rl("small_digital_assembler/machine/normal/chamical_refiner"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(MekInMachines.COMPACT_ANTIMATTER_PROTOMOLECULAR_TRANSMUTATOR, 1))
                .addItemInput(EMItems.QUANTUM_CONTROL_CIRCUIT, 4)
                .addItemInput(EMItems.SUBATOMIC_ALLOY, 8)
                .addItemInput(EMBlocks.APT_CASING, 52)
                .addItemInput(EMBlocks.APT_PORT, 4)
                .addItemInput(MekanismBlocks.STRUCTURAL_GLASS, 82)
                .setFluidInput(AAEFluids.QUANTUM_INFUSION.stack(1000))
                .setChemicalInput(MekanismChemicals.ANTIMATTER.asStack(5))
                .build(output, MekInConstants
                        .rl("small_digital_assembler/machine/normal/compact_antimatter_protomolecular_transmutator"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(MekInMachines.COMPACT_NAQUADAH_REACTOR, 1))
                .addItemInput(GenExtraBlocks.NAQUADAH_REACTOR_CONTROLLER, 1)
                .addItemInput(GenExtraBlocks.NAQUADAH_REACTOR_CASING, 108)
                .addItemInput(GenExtraBlocks.NAQUADAH_REACTOR_PORT, 4)
                .addItemInput(GeneratorsBlocks.REACTOR_GLASS, 217)
                .addItemInput(ExtraItems.COSMIC_CONTROL_CIRCUIT, 4)
                .addItemInput(ExtraItems.SHINING_ALLOY, 16)
                .setFluidInput(MekanismFluids.ETHENE.asStack(4000))
                .setChemicalInput(MSGases.BERYLLIUM.asStack(8000L))
                .build(output, MekInConstants
                        .rl("small_digital_assembler/machine/normal/compact_naquadah_reactor"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(MekInMachines.FLUX_CONDENSER, 1))
                .addItemInput(EMExtraItems.INFINITE_MULTIVERSAL_CONTROL_CIRCUIT, 4)
                .addItemInput(ExtraItems.SPECTRUM_ALLOY, 4)
                .addItemInput(EMItems.EXOVERSAL_ALLOY, 4)
                .addItemInput(ExtraBlocks.INFINITE_INDUCTION_CELL, 8)
                .addItemInput(EMBlocks.MULTIVERSAL_INDUCTION_CELL, 8)
                .addItemInput(MekanismBlocks.DYNAMIC_TANK, 64)
                .addItemInput(MekanismBlocks.STEEL_BLOCK, 64)
                .setFluidInput(GeneratorsFluids.FUSION_FUEL.asStack(1000))
                .setChemicalInput(MoreMachineChemicals.UU_MATTER.asStack(10000))
                .build(output, MekInConstants.rl("small_digital_assembler/machine/normal/flux_condenser"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(MekanismBlocks.CRUSHER, 1))
                .addItemInput(MekanismBlocks.STEEL_CASING, 1)
                .addItemInput(MekanismItems.BASIC_CONTROL_CIRCUIT, 4)
                .addItemInput(MekanismTags.Items.INGOTS_BRONZE, 16)
                .setFluidInput(Tags.Fluids.LAVA, 2000)
                .setChemicalInput(MekanismChemicals.REDSTONE.asStack(320))
                .build(output, MekInConstants.rl("small_digital_assembler/machine/normal/crusher"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(MekanismBlocks.PURIFICATION_CHAMBER, 1))
                .addItemInput(MekanismBlocks.ENRICHMENT_CHAMBER, 1)
                .addItemInput(MekanismItems.ADVANCED_CONTROL_CIRCUIT, 4)
                .addItemInput(MekanismItems.INFUSED_ALLOY, 16)
                .setFluidInput(Tags.Fluids.WATER, 2000)
                .setChemicalInput(MekanismChemicals.OSMIUM.asStack(3200))
                .build(output, MekInConstants.rl("small_digital_assembler/machine/normal/purification_chamber"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(MekanismBlocks.CHEMICAL_INJECTION_CHAMBER, 1))
                .addItemInput(MekanismBlocks.PURIFICATION_CHAMBER, 1)
                .addItemInput(MekanismItems.ELITE_CONTROL_CIRCUIT, 4)
                .addItemInput(MekanismItems.REINFORCED_ALLOY, 16)
                .setFluidInput(Tags.Fluids.WATER, 2000)
                .setChemicalInput(MekanismChemicals.GOLD.asStack(320))
                .build(output, MekInConstants.rl("small_digital_assembler/machine/normal/chemical_injection_chamber"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(MekanismBlocks.OSMIUM_COMPRESSOR, 1))
                .addItemInput(MekanismBlocks.STEEL_CASING, 1)
                .addItemInput(MekanismItems.ADVANCED_CONTROL_CIRCUIT, 4)
                .addItemInput(MekanismItems.INFUSED_ALLOY, 16)
                .addItemInput(MekanismBlocks.DYNAMIC_TANK, 64)
                .setFluidInput(Tags.Fluids.WATER, 2000)
                .setChemicalInput(MekanismChemicals.HYDROGEN.asStack(1000))
                .build(output, MekInConstants.rl("small_digital_assembler/machine/normal/osmium_compressor"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(MekanismBlocks.ROTARY_CONDENSENTRATOR, 1))
                .addItemInput(MekanismBlocks.STEEL_CASING, 1)
                .addItemInput(MekanismBlocks.DYNAMIC_TANK, 64)
                .addItemInput(MekanismItems.ENERGY_TABLET, 4)
                .addItemInput(MekanismItems.BASIC_CONTROL_CIRCUIT, 4)
                .addItemInput(Tags.Items.GLASS_BLOCKS_CHEAP, 16)
                .addItemInput(MekanismBlocks.BASIC_FLUID_TANK, 1)
                .addItemInput(MekanismBlocks.BASIC_CHEMICAL_TANK, 1)
                .setFluidInput(Tags.Fluids.WATER, 2000)
                .setChemicalInput(MekanismChemicals.OXYGEN.asStack(1000))
                .build(output, MekInConstants.rl("small_digital_assembler/machine/normal/rotary_condensentrator"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(MekanismBlocks.CHEMICAL_OXIDIZER, 1))
                .addItemInput(MekanismBlocks.STEEL_CASING, 1)
                .addItemInput(MekanismBlocks.DYNAMIC_TANK, 64)
                .addItemInput(MekanismItems.BASIC_CONTROL_CIRCUIT, 4)
                .addItemInput(MekanismItems.INFUSED_ALLOY, 16)
                .addItemInput(MekanismTags.Items.PERSONAL_STORAGE, 1)
                .addItemInput(MekanismBlocks.BASIC_CHEMICAL_TANK, 1)
                .setFluidInput(Tags.Fluids.WATER, 2000)
                .setChemicalInput(MekanismChemicals.OXYGEN.asStack(1000))
                .build(output, MekInConstants.rl("small_digital_assembler/machine/normal/chemical_oxidizer"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(MekanismBlocks.CHEMICAL_INFUSER, 1))
                .addItemInput(MekanismBlocks.STEEL_CASING, 1)
                .addItemInput(MekanismBlocks.DYNAMIC_TANK, 64)
                .addItemInput(MekanismItems.BASIC_CONTROL_CIRCUIT, 4)
                .addItemInput(MekanismItems.INFUSED_ALLOY, 16)
                .addItemInput(MekanismBlocks.BASIC_CHEMICAL_TANK, 2)
                .setFluidInput(Tags.Fluids.WATER, 2000)
                .setChemicalInput(MekanismChemicals.TIN.asStack(640))
                .build(output, MekInConstants.rl("small_digital_assembler/machine/normal/chemical_infuser"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(MekanismBlocks.CHEMICAL_DISSOLUTION_CHAMBER, 1))
                .addItemInput(MekanismBlocks.STEEL_CASING, 1)
                .addItemInput(MekanismBlocks.DYNAMIC_TANK, 64)
                .addItemInput(MekanismItems.ULTIMATE_CONTROL_CIRCUIT, 4)
                .addItemInput(MekanismTags.Items.INGOTS_REFINED_OBSIDIAN, 16)
                .addItemInput(MekanismBlocks.BASIC_CHEMICAL_TANK, 2)
                .setFluidInput(Tags.Fluids.WATER, 2000)
                .setChemicalInput(MekanismChemicals.REDSTONE.asStack(640))
                .build(output,
                        MekInConstants.rl("small_digital_assembler/machine/normal/chemical_dissolution_chamber"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(MekanismBlocks.CHEMICAL_WASHER, 1))
                .addItemInput(MekanismBlocks.STEEL_CASING, 1)
                .addItemInput(MekanismBlocks.DYNAMIC_TANK, 64)
                .addItemInput(MekanismItems.ULTIMATE_CONTROL_CIRCUIT, 4)
                .addItemInput(MekanismTags.Items.INGOTS_REFINED_OBSIDIAN, 16)
                .addItemInput(MekanismBlocks.BASIC_CHEMICAL_TANK, 1)
                .addItemInput(MekanismBlocks.BASIC_FLUID_TANK, 1)
                .setFluidInput(Tags.Fluids.WATER, 2000)
                .setChemicalInput(MekanismChemicals.REDSTONE.asStack(640))
                .build(output,
                        MekInConstants.rl("small_digital_assembler/machine/normal/chemical_washer"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(MekanismBlocks.CHEMICAL_CRYSTALLIZER, 1))
                .addItemInput(MekanismBlocks.STEEL_CASING, 1)
                .addItemInput(MekanismBlocks.DYNAMIC_TANK, 64)
                .addItemInput(MekanismItems.ULTIMATE_CONTROL_CIRCUIT, 4)
                .addItemInput(MekanismTags.Items.INGOTS_REFINED_OBSIDIAN, 16)
                .addItemInput(MekanismTags.Items.GEMS_FLUORITE, 16)
                .setFluidInput(Tags.Fluids.WATER, 2000)
                .setChemicalInput(MekanismChemicals.REDSTONE.asStack(640))
                .build(output,
                        MekInConstants.rl("small_digital_assembler/machine/normal/chemical_crystallizer"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(MekanismBlocks.ISOTOPIC_CENTRIFUGE, 1))
                .addItemInput(MekanismBlocks.STEEL_CASING, 1)
                .addItemInput(MekanismBlocks.DYNAMIC_TANK, 64)
                .addItemInput(MekanismItems.ULTIMATE_CONTROL_CIRCUIT, 4)
                .addItemInput(MekanismTags.Items.PROCESSED_RESOURCES.get(ResourceType.INGOT, PrimaryResource.LEAD), 96)
                .addItemInput(MekanismBlocks.BASIC_CHEMICAL_TANK, 1)
                .setFluidInput(Tags.Fluids.WATER, 2000)
                .setChemicalInput(MekanismChemicals.GOLD.asStack(640))
                .build(output, MekInConstants.rl("small_digital_assembler/machine/normal/isotopic_centrifuge"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(MekanismBlocks.PRECISION_SAWMILL, 1))
                .addItemInput(MekanismBlocks.STEEL_CASING, 1)
                .addItemInput(MekanismItems.BASIC_CONTROL_CIRCUIT, 4)
                .addItemInput(Tags.Items.INGOTS_IRON, 16)
                .addItemInput(MekanismItems.INFUSED_ALLOY, 4)
                .addItemInput(ToolsItems.BRONZE_AXE, 2)
                .setFluidInput(Tags.Fluids.WATER, 2000)
                .setChemicalInput(MekanismChemicals.TIN.asStack(640))
                .build(output, MekInConstants.rl("small_digital_assembler/machine/normal/precision_sawmill"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(MekanismBlocks.NUTRITIONAL_LIQUIFIER, 1))
                .addItemInput(MekanismBlocks.STEEL_CASING, 1)
                .addItemInput(MekanismItems.BASIC_CONTROL_CIRCUIT, 4)
                .addItemInput(Tags.Items.DUSTS_REDSTONE, 16)
                .addItemInput(Items.BOWL, 4)
                .setFluidInput(Tags.Fluids.WATER, 2000)
                .setChemicalInput(MekanismChemicals.OSMIUM.asStack(800))
                .build(output, MekInConstants.rl("small_digital_assembler/machine/normal/nutritional_liquifier"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(MekanismBlocks.SOLAR_NEUTRON_ACTIVATOR, 1))
                .addItemInput(MekanismBlocks.STEEL_CASING, 1)
                .addItemInput(MekanismItems.ELITE_CONTROL_CIRCUIT, 4)
                .addItemInput(MekanismItems.REINFORCED_ALLOY, 16)
                .addItemInput(MekanismItems.HDPE_SHEET, 16)
                .addItemInput(MekanismTags.Items.INGOTS_BRONZE, 32)
                .setFluidInput(MekanismFluids.HEAVY_WATER.asStack(2000))
                .setChemicalInput(MekanismChemicals.OSMIUM.asStack(800))
                .build(output, MekInConstants.rl("small_digital_assembler/machine/normal/solar_neutron_activator"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(LargeMachineBlocks.LARGE_ROTARY_CONDENSENTRATOR, 1))
                .addItemInput(MekanismBlocks.STEEL_CASING, 64)
                .addItemInput(MekanismItems.ROBIT, 1)
                .addItemInput(MekanismBlocks.DYNAMIC_TANK, 256)
                .addItemInput(MekanismItems.ULTIMATE_CONTROL_CIRCUIT, 4)
                .addItemInput(LargeMachineBlocks.ULTIMATE_MAX_CHEMICAL_TANK, 1)
                .addItemInput(MekanismBlocks.ULTIMATE_FLUID_TANK, 1)
                .setFluidInput(MekanismFluids.HEAVY_WATER.asStack(2000))
                .setChemicalInput(MekanismChemicals.OSMIUM.asStack(800))
                .build(output, MekInConstants.rl("small_digital_assembler/machine/large/rotary_condensentrator"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(LargeMachineBlocks.LARGE_CHEMICAL_INFUSER, 1))
                .addItemInput(MekanismBlocks.STEEL_CASING, 64)
                .addItemInput(MekanismItems.ROBIT, 1)
                .addItemInput(MekanismBlocks.DYNAMIC_TANK, 256)
                .addItemInput(MekanismItems.ULTIMATE_CONTROL_CIRCUIT, 4)
                .addItemInput(LargeMachineBlocks.ULTIMATE_MAX_CHEMICAL_TANK, 2)
                .setFluidInput(MekanismFluids.HEAVY_WATER.asStack(2000))
                .setChemicalInput(MekanismChemicals.GOLD.asStack(800))
                .build(output, MekInConstants.rl("small_digital_assembler/machine/large/chemical_infuser"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(LargeMachineBlocks.LARGE_ELECTROLYTIC_SEPARATOR, 1))
                .addItemInput(MekanismBlocks.STEEL_CASING, 64)
                .addItemInput(MekanismItems.ROBIT, 1)
                .addItemInput(MekanismBlocks.DYNAMIC_TANK, 256)
                .addItemInput(MoreMachineItems.ADVANCED_ELECTROLYSIS_CORE, 4)
                .addItemInput(MekanismBlocks.ULTIMATE_FLUID_TANK, 1)
                .addItemInput(LargeMachineBlocks.ULTIMATE_MAX_CHEMICAL_TANK, 1)
                .setFluidInput(MekanismFluids.HEAVY_WATER.asStack(2000))
                .setChemicalInput(MekanismChemicals.GOLD.asStack(800))
                .build(output, MekInConstants.rl("small_digital_assembler/machine/large/electrolytic_separator"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(LargeMachineBlocks.LARGE_SOLAR_NEUTRON_ACTIVATOR, 1))
                .addItemInput(MekanismBlocks.STEEL_CASING, 64)
                .addItemInput(MekanismItems.ROBIT, 1)
                .addItemInput(MekanismBlocks.DYNAMIC_TANK, 256)
                .addItemInput(MekanismBlocks.LASER, 4)
                .addItemInput(EMGenBlocks.ULTIMATE_SOLAR_GENERATOR, 16)
                .addItemInput(LargeMachineBlocks.ULTIMATE_MAX_CHEMICAL_TANK, 2)
                .setFluidInput(MekanismFluids.HEAVY_WATER.asStack(2000))
                .setChemicalInput(MekanismChemicals.ETHENE.asStack(10000))
                .build(output, MekInConstants.rl("small_digital_assembler/machine/large/solar_neutron_activator"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(LargeMachineBlocks.LARGE_ANTIPROTONIC_NUCLEOSYNTHESIZER, 1))
                .addItemInput(MekanismBlocks.STEEL_CASING, 64)
                .addItemInput(MekanismItems.ROBIT, 1)
                .addItemInput(MekanismBlocks.DYNAMIC_TANK, 256)
                .addItemInput(MekanismItems.ULTIMATE_CONTROL_CIRCUIT, 4)
                .addItemInput(LargeMachineBlocks.ULTIMATE_MAX_CHEMICAL_TANK, 1)
                .addItemInput(MekanismTags.Items.PERSONAL_STORAGE, 1)
                .setFluidInput(MekanismFluids.HEAVY_WATER.asStack(2000))
                .setChemicalInput(MekanismChemicals.ANTIMATTER.asStack(40000L))
                .build(output,
                        MekInConstants.rl("small_digital_assembler/machine/large/antiprotonic_nucleosynthesizer"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(LargeGeneratorBlocks.LARGE_HEAT_GENERATOR, 1))
                .addItemInput(MekanismBlocks.STEEL_CASING, 64)
                .addItemInput(MekanismItems.ROBIT, 1)
                .addItemInput(MekanismBlocks.DYNAMIC_TANK, 256)
                .addItemInput(MekanismItems.ULTIMATE_CONTROL_CIRCUIT, 4)
                .addItemInput(MekanismBlocks.ULTIMATE_FLUID_TANK, 2)
                .addItemInput(MekanismBlocks.SUPERHEATING_ELEMENT, 16)
                .setFluidInput(MekanismFluids.HEAVY_WATER.asStack(2000))
                .setChemicalInput(MekanismChemicals.LITHIUM.asStack(1000L))
                .build(output, MekInConstants.rl("small_digital_assembler/machine/large/heat_generator"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(LargeGeneratorBlocks.LARGE_GAS_BURNING_GENERATOR, 1))
                .addItemInput(MekanismBlocks.STEEL_CASING, 64)
                .addItemInput(MekanismItems.ROBIT, 1)
                .addItemInput(MekanismBlocks.DYNAMIC_TANK, 256)
                .addItemInput(MoreMachineItems.ADVANCED_ELECTROLYSIS_CORE, 4)
                .addItemInput(LargeMachineBlocks.ULTIMATE_MAX_CHEMICAL_TANK, 2)
                .setFluidInput(Tags.Fluids.LAVA, 4000)
                .setChemicalInput(MekanismChemicals.LITHIUM.asStack(1000L))
                .build(output, MekInConstants.rl("small_digital_assembler/machine/large/gas_burning_generator"));

        ABSOLUTE_MACHINES.forEach(data -> {
            ItemStackListFluidChemicalToItemRecipeBuilder
                    .smallDigitalAssembler(new ItemStack(data.afterMachine))
                    .addItemInput(data.beforeMachine, 1)
                    .addItemInput(EMExtraItems.ABSOLUTE_OVERCLOCKED_CONTROL_CIRCUIT, 8)
                    .addItemInput(EMItems.HYPERCHARGED_ALLOY, 16)
                    .addItemInput(ExtraItems.RADIANCE_ALLOY, 16)
                    .addItemInput(
                            MekanismTags.Items.PROCESSED_RESOURCES.get(ResourceType.INGOT, PrimaryResource.URANIUM), 64)
                    .addItemInput(Tags.Items.GEMS_EMERALD, 64)
                    .setFluidInput(Tags.Fluids.WATER, 1000)
                    .setChemicalInput(MekanismChemicals.GOLD.asStack(5120))
                    .build(output,
                            MekInConstants.rl("small_digital_assembler/machine/absolute_overclocked/" + data.name));
        });
        SUPREME_MACHINES.forEach(data -> {
            ItemStackListFluidChemicalToItemRecipeBuilder
                    .smallDigitalAssembler(new ItemStack(data.afterMachine))
                    .addItemInput(data.beforeMachine, 1)
                    .addItemInput(EMExtraItems.SUPREME_QUANTUM_CONTROL_CIRCUIT, 8)
                    .addItemInput(EMItems.SUBATOMIC_ALLOY, 16)
                    .addItemInput(ExtraItems.THERMONUCLEAR_ALLOY, 16)
                    .addItemInput(
                            MekanismTags.Items.PROCESSED_RESOURCES.get(ResourceType.INGOT, PrimaryResource.TIN), 256)
                    .addItemInput(Tags.Items.INGOTS_NETHERITE, 256)
                    .setFluidInput(Tags.Fluids.LAVA, 1000)
                    .setChemicalInput(MSGases.AMERICIUM.asStack(10000))
                    .build(output,
                            MekInConstants.rl("small_digital_assembler/machine/supreme_quantum/" + data.name));
        });
        INFINITE_MACHINES.forEach(data->{
            ItemStackListFluidChemicalToItemRecipeBuilder
                    .smallDigitalAssembler(new ItemStack(data.afterMachine))
                    .addItemInput(data.beforeMachine, 1)
                    .addItemInput(EMExtraItems.INFINITE_MULTIVERSAL_CONTROL_CIRCUIT, 8)
                    .addItemInput(EMItems.EXOVERSAL_ALLOY, 16)
                    .addItemInput(ExtraItems.SPECTRUM_ALLOY, 16)
                    .addItemInput(MekanismTags.Items.PELLETS_PLUTONIUM, 512)
                    .addItemInput(MekanismTags.Items.PELLETS_POLONIUM, 512)
                    .addItemInput(Tags.Items.INGOTS_NETHERITE, 1024)
                    .setFluidInput(MekInFluids.FLUX.asStack(10))
                    .setChemicalInput(MekInChemicals.NULL.asStack(100))
                    .build(output,
                            MekInConstants.rl("small_digital_assembler/machine/infinite_multiversal/" + data.name));
        });
    }

    private record MachineData(ItemLike beforeMachine, ItemLike afterMachine, String name) {
    }

    static {
        ABSOLUTE_MACHINES = new ArrayList<>();
        ABSOLUTE_MACHINES.add(new MachineData(
                MekUtMachines.COMPACT_BOILER,
                MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_BOILER,
                "boiler"));
        ABSOLUTE_MACHINES.add(new MachineData(
                MekUtMachines.CHEMICAL_CUTTER,
                MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_CHEMICAL_CUTTER,
                "chemical_cutter"));
        ABSOLUTE_MACHINES.add(new MachineData(
                MekanismBlocks.CHEMICAL_INJECTION_CHAMBER,
                MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_CHEMICAL_INJECTION_CHAMBER,
                "chemical_injection_chamber"));
        ABSOLUTE_MACHINES.add(new MachineData(
                MekanismBlocks.CHEMICAL_OXIDIZER,
                MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_CHEMICAL_OXIDIZER,
                "chemical_oxidizer"));
        ABSOLUTE_MACHINES.add(new MachineData(
                MekanismBlocks.CRUSHER,
                MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_CRUSHER,
                "crusher"));
        ABSOLUTE_MACHINES.add(new MachineData(
                MekanismBlocks.ELECTROLYTIC_SEPARATOR,
                MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_ELECTROLYTIC_SEPARATOR,
                "electrolytic_separator"));
        ABSOLUTE_MACHINES.add(new MachineData(
                MekUtMachines.TWEAKED_ENERGIZED_SMELTER,
                MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_ENERIZED_SMELTER,
                "energized_smelter"));
        ABSOLUTE_MACHINES.add(new MachineData(
                MekanismBlocks.ENRICHMENT_CHAMBER,
                MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_ENRICHMENT_CHAMBER,
                "enrichment_chamber"));
        ABSOLUTE_MACHINES.add(new MachineData(
                MekUtMachines.COMPACT_FISSION_REACTOR,
                MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_FISSION_REACTOR,
                "fission_reactor"));
        ABSOLUTE_MACHINES.add(new MachineData(
                MekUtMachines.ICE_MAKER,
                MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_ICE_MAKER,
                "ice_maker"));
        ABSOLUTE_MACHINES.add(new MachineData(
                MekUtMachines.COMPACT_INDUSTRIAL_TURBINE,
                MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_INDUSTRIAL_TURBINE,
                "industrial_turbine"));
        ABSOLUTE_MACHINES.add(new MachineData(
                MekanismBlocks.METALLURGIC_INFUSER,
                MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_METALLURGIC_INFUSER,
                "metallurgic_infuser"));
        ABSOLUTE_MACHINES.add(new MachineData(
                MekanismBlocks.OSMIUM_COMPRESSOR,
                MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_OSMIUM_COMPRESSOR,
                "osmium_compressor"));
        ABSOLUTE_MACHINES.add(new MachineData(
                MekanismBlocks.PAINTING_MACHINE,
                MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_PAINTING_MACHINE,
                "painting_machine"));
        ABSOLUTE_MACHINES.add(new MachineData(
                MekanismBlocks.PURIFICATION_CHAMBER,
                MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_PURIFICATION_CHAMBER,
                "purification_chamber"));
        ABSOLUTE_MACHINES.add(new MachineData(
                MekanismBlocks.ROTARY_CONDENSENTRATOR,
                MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_ROTARY_CONDENSENTRATOR,
                "rotary_condensentrator"));
        ABSOLUTE_MACHINES.add(new MachineData(
                MekUtMachines.SMALL_DIGITAL_ASSEMBLER,
                MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_SMALL_DIGITAL_ASSEMBLER,
                "small_digital_assembler"));
        ABSOLUTE_MACHINES.add(new MachineData(
                MekUtMachines.SMALL_DIGITAL_REACTION_CHAMBER,
                MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_SMALL_DIGITAL_REACTION_CAHMBER,
                "small_digital_reaction_chamber"));
        ABSOLUTE_MACHINES.add(new MachineData(
                MekUtMachines.COMPACT_THERMAL_EVAPOLATION_PLANT,
                MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_THERMAL_EVAPORATION_PLANT,
                "thermal_evapolation_plant"));

        SUPREME_MACHINES = new ArrayList<>();
        SUPREME_MACHINES.add(new MachineData(
                MekanismBlocks.CHEMICAL_CRYSTALLIZER,
                MekInSupremeMachines.SUPREME_QUANTUM_CHEMICAL_CRYSTALLIZER,
                "chemical_crystallizer"));
        SUPREME_MACHINES.add(new MachineData(
                MekanismBlocks.CHEMICAL_DISSOLUTION_CHAMBER,
                MekInSupremeMachines.SUPREME_QUANTUM_CHEMICAL_DISSOLUTION_CHAMBER,
                "chemical_dissolution_chamber"));
        SUPREME_MACHINES.add(new MachineData(
                MekanismBlocks.CHEMICAL_WASHER,
                MekInSupremeMachines.SUPREME_QUANTUM_CHEMICAL_WASHER,
                "chemical_washer"));
        SUPREME_MACHINES.add(new MachineData(
                MekUtMachines.LAZER_COMPRESS_NUCLEO_SYNTHESIZER,
                MekInSupremeMachines.SUPREME_QUANTUM_LAZER_COMPRESS_NUCLEO_SYNTHESIZER,
                "lazer_compress_nucleo_synthesizer"));
        SUPREME_MACHINES.add(new MachineData(
                MekUtMachines.COMPACT_SUPERCRITICAL_PHASE_SHIFTER,
                MekInSupremeMachines.SUPREME_QUANTUM_SUPERCRITICAL_PHASE_SHIFTER,
                "supercritical_phase_shifter"));

        INFINITE_MACHINES = List.of(new MachineData[] {
                new MachineData(
                        MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_BOILER,
                        MekInInfiniteMachines.INFINITE_MULTIVERSAL_BOILER,
                        "boiler"),
                new MachineData(
                        MekInSupremeMachines.SUPREME_QUANTUM_CHEMICAL_CRYSTALLIZER,
                        MekInInfiniteMachines.INFINITE_MULTIVERSAL_CHEMICAL_CRYSTALLIZER,
                        "chemical_crystallizer"),
                new MachineData(
                        MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_CHEMICAL_CUTTER,
                        MekInInfiniteMachines.INFINITE_MULTIVERSAL_CHEMICAL_CUTTER,
                        "chemical_cutter"),
                new MachineData(
                        MekInSupremeMachines.SUPREME_QUANTUM_CHEMICAL_DISSOLUTION_CHAMBER,
                        MekInInfiniteMachines.INFINITE_MULTIVERSAL_CHEMICAL_DISSOLUTION_CHAMBER,
                        "chemical_dissolution_chamber"),
                new MachineData(
                        MekInMachines.CHEMICAL_EXTRACTOR,
                        MekInInfiniteMachines.INFINITE_MULTIVERSAL_CHEMICAL_EXTRACTOR,
                        "chemical_extractor"),
                new MachineData(
                        MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_CHEMICAL_INJECTION_CHAMBER,
                        MekInInfiniteMachines.INFINITE_MULTIVERSAL_CHEMICAL_INJECTION_CHAMBER,
                        "chemical_injection_chamber"),
                new MachineData(
                        MekInMachines.CHEMICAL_LEACHING_CHAMBER,
                        MekInInfiniteMachines.INFINITE_MULTIVERSAL_CHEMICAL_LEACHING_CHAMBER,
                        "chemical_leaching_chamber"),
                new MachineData(
                        MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_CHEMICAL_OXIDIZER,
                        MekInInfiniteMachines.INFINITE_MULTIVERSAL_CHEMICAL_OXIDIZER,
                        "chemical_oxidizer"),
                new MachineData(
                        MekInSupremeMachines.SUPREME_QUANTUM_CHEMICAL_WASHER,
                        MekInInfiniteMachines.INFINITE_MULTIVERSAL_CHEMICAL_WASHER,
                        "chemical_washer"),
                new MachineData(
                        MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_CRUSHER,
                        MekInInfiniteMachines.INFINITE_MULTIVERSAL_CRUSHER,
                        "crusher"),
                new MachineData(
                        MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_ELECTROLYTIC_SEPARATOR,
                        MekInInfiniteMachines.INFINITE_MULTIVERSAL_ELECTROLYTIC_SEPARATOR,
                        "electrolytic_separator"),
                new MachineData(
                        MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_ENERIZED_SMELTER,
                        MekInInfiniteMachines.INFINITE_MULTIVERSAL_ENERGIZED_SMELTER,
                        "energized_smelter"),
                new MachineData(
                        MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_ENRICHMENT_CHAMBER,
                        MekInInfiniteMachines.INFINITE_MULTIVERSAL_ENRICHMENT_CHAMBER,
                        "enrichment_chamber"),
                new MachineData(
                        MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_FISSION_REACTOR,
                        MekInInfiniteMachines.INFINITE_MULTIVERSAL_FISSION_REACTOR,
                        "fission_reactor"),
                new MachineData(
                        MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_ICE_MAKER,
                        MekInInfiniteMachines.INFINITE_MULTIVERSAL_ICE_MAKER,
                        "ice_maker"),
                new MachineData(
                        MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_INDUSTRIAL_TURBINE,
                        MekInInfiniteMachines.INFINITE_MULTIVERSAL_INDUSTRIAL_TURBINE,
                        "industrial_turbine"),
                new MachineData(
                       MekInSupremeMachines.SUPREME_QUANTUM_LAZER_COMPRESS_NUCLEO_SYNTHESIZER,
                        MekInInfiniteMachines.INFINITE_MULTIVERSAL_LAZER_COMPRESS_NUCLEO_SYNTHESIZER,
                        "lazer_compress_nucleo_synthesizer"),
                new MachineData(
                        MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_METALLURGIC_INFUSER,
                        MekInInfiniteMachines.INFINITE_MULTIVERSAL_METALLURGIC_INFUSER,
                        "metallurgic_infuser"),
                new MachineData(
                        MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_OSMIUM_COMPRESSOR,
                        MekInInfiniteMachines.INFINITE_MULTIVERSAL_OSMIUM_COMPRESSOR,
                        "osmium_compressor"),
                new MachineData(
                        MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_PAINTING_MACHINE,
                        MekInInfiniteMachines.INFINITE_MULTIVERSAL_PAINTING_MACHINE,
                        "painting_machine"),
                new MachineData(
                        MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_PURIFICATION_CHAMBER,
                        MekInInfiniteMachines.INFINITE_MULTIVERSAL_PURIFICATION_CHAMBER,
                        "purification_chamber"),
                new MachineData(
                        MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_ROTARY_CONDENSENTRATOR,
                        MekInInfiniteMachines.INFINITE_MULTIVERSAL_ROTARY_CONDENSENTRATOR,
                        "rotary_condensentrator"),
                new MachineData(
                        MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_SMALL_DIGITAL_ASSEMBLER,
                        MekInInfiniteMachines.INFINITE_MULTIVERSAL_SMALL_DIGITAL_ASSEMBLER,
                        "small_digital_assembler"),
                new MachineData(
                        MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_SMALL_DIGITAL_REACTION_CAHMBER,
                        MekInInfiniteMachines.INFINITE_MULTIVERSAL_SMALL_DIGITAL_REACTION_CHAMBER,
                        "small_digital_reaction_chamber"),
                new MachineData(
                        MekInSupremeMachines.SUPREME_QUANTUM_SUPERCRITICAL_PHASE_SHIFTER,
                        MekInInfiniteMachines.INFINITE_MULTIVERSAL_SUPERCRITICAL_PHASE_SHIFTER,
                        "supercritical_phase_shifter"),
                new MachineData(
                        MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_THERMAL_EVAPORATION_PLANT,
                        MekInInfiniteMachines.INFINITE_MULTIVERSAL_THERMAL_EVAPORATION_PLANT,
                        "thermal_evaporation_plant"),
        });
    }
}
