package com.takenokoshi.mekin.recipe.building;

import java.util.ArrayList;
import java.util.List;

import com.extendedae_plus.init.ModItems;
import com.fxd927.mekanismelements.common.registries.MSGases;
import com.jerry.mekextras.common.registries.ExtraBlocks;
import com.jerry.mekextras.common.registries.ExtraItems;
import com.jerry.mekmm.common.registries.MoreMachineChemicals;
import com.takenokoshi.mekin.core.MekInConstants;
import com.takenokoshi.mekin.registries.MekInBlocks;
import com.takenokoshi.mekin.registries.MekInFluids;
import com.takenokoshi.mekin.registries.MekInItems;
import com.takenokoshi.mekin.registries.MekInMachines;
import com.takenokoshi.mekut.recipe.builder.ItemStackListFluidChemicalToItemRecipeBuilder;
import com.takenokoshi.mekut.registries.MekUtChemicals;
import com.takenokoshi.mekut.registries.MekUtItems;
import com.takenokoshi.mekut.registries.MekUtMachines;

import appeng.core.definitions.AEItems;
import fr.iglee42.evolvedmekanism.registries.EMBlocks;
import fr.iglee42.evolvedmekanism.registries.EMItems;
import gripe._90.megacells.definition.MEGAItems;
import io.github.masyumero.emextras.common.registry.EMExtraItems;
import mekanism.common.registries.MekanismBlocks;
import mekanism.common.registries.MekanismChemicals;
import mekanism.common.resource.PrimaryResource;
import mekanism.common.resource.ResourceType;
import mekanism.common.tags.MekanismTags;
import mekanism.generators.common.registries.GeneratorsFluids;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;
import net.pedroksl.advanced_ae.common.definitions.AAEFluids;

public class SDARecipes {

    private static final List<MachineData> ABSOLUTE_MACHINES;
    private static final List<MachineData> SUPREME_MACHINES;

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
                .smallDigitalAssembler(new ItemStack(MekInBlocks.TITANIUM_CASING, 1))
                .addItemInput(MekInItems.TITANIUM_INGOT, 16)
                .addItemInput(MekanismBlocks.STEEL_CASING, 256)
                .setFluidInput(Tags.Fluids.WATER, 10000)
                .setChemicalInput(MekUtChemicals.IRIDIUM.asStack(4_000_000L))
                .build(output, MekInConstants.rl("small_digital_assembler/titanium_casing"));

        ABSOLUTE_MACHINES.forEach(data -> {
            ItemStackListFluidChemicalToItemRecipeBuilder
                    .smallDigitalAssembler(new ItemStack(data.afterMachine))
                    .addItemInput(data.beforeMachine, 1)
                    .addItemInput(EMExtraItems.ABSOLUTE_OVERCLOCKED_CONTROL_CIRCUIT, 8)
                    .addItemInput(EMItems.HYPERCHARGED_ALLOY, 8)
                    .addItemInput(ExtraItems.RADIANCE_ALLOY, 8)
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
                    .addItemInput(EMItems.SUBATOMIC_ALLOY, 8)
                    .addItemInput(ExtraItems.THERMONUCLEAR_ALLOY, 8)
                    .addItemInput(
                            MekanismTags.Items.PROCESSED_RESOURCES.get(ResourceType.INGOT, PrimaryResource.TIN), 256)
                    .addItemInput(Tags.Items.INGOTS_NETHERITE, 256)
                    .setFluidInput(Tags.Fluids.LAVA, 1000)
                    .setChemicalInput(MSGases.AMERICIUM.asStack(10000))
                    .build(output,
                            MekInConstants.rl("small_digital_assembler/machine/absolute_overclocked/" + data.name));
        });
    }

    private record MachineData(ItemLike beforeMachine, ItemLike afterMachine, String name) {
    }

    static {
        ABSOLUTE_MACHINES = new ArrayList<>();
        ABSOLUTE_MACHINES.add(new MachineData(
                MekUtMachines.CHEMICAL_CUTTER,
                MekInMachines.ABSOLUTE_OVERCLOCKED_CHEMICAL_CUTTER,
                "chemical_cutter"));
        ABSOLUTE_MACHINES.add(new MachineData(
                MekanismBlocks.CHEMICAL_INJECTION_CHAMBER,
                MekInMachines.ABSOLUTE_OVERCLOCKED_CHEMICAL_INJECTION_CHAMBER,
                "chemical_injection_chamber"));
        ABSOLUTE_MACHINES.add(new MachineData(
                MekanismBlocks.CHEMICAL_OXIDIZER,
                MekInMachines.ABSOLUTE_OVERCLOCKED_CHEMICAL_OXIDIZER,
                "chemical_oxidizer"));
        ABSOLUTE_MACHINES.add(new MachineData(
                MekanismBlocks.CRUSHER,
                MekInMachines.ABSOLUTE_OVERCLOCKED_CRUSHER,
                "crusher"));
        ABSOLUTE_MACHINES.add(new MachineData(
                MekanismBlocks.ELECTROLYTIC_SEPARATOR,
                MekInMachines.ABSOLUTE_OVERCLOCKED_ELECTROLYTIC_SEPARATOR,
                "electrolytic_separator"));
        ABSOLUTE_MACHINES.add(new MachineData(
                MekUtMachines.TWEAKED_ENERGIZED_SMELTER,
                MekInMachines.ABSOLUTE_OVERCLOCKED_ENERIZED_SMELTER,
                "energized_smelter"));
        ABSOLUTE_MACHINES.add(new MachineData(
                MekanismBlocks.ENRICHMENT_CHAMBER,
                MekInMachines.ABSOLUTE_OVERCLOCKED_ENRICHMENT_CHAMBER,
                "enrichment_chamber"));
        ABSOLUTE_MACHINES.add(new MachineData(
                MekUtMachines.ICE_MAKER,
                MekInMachines.ABSOLUTE_OVERCLOCKED_ICE_MAKER,
                "ice_maker"));
        ABSOLUTE_MACHINES.add(new MachineData(
                MekanismBlocks.METALLURGIC_INFUSER,
                MekInMachines.ABSOLUTE_OVERCLOCKED_METALLURGIC_INFUSER,
                "metallurgic_infuser"));
        ABSOLUTE_MACHINES.add(new MachineData(
                MekanismBlocks.OSMIUM_COMPRESSOR,
                MekInMachines.ABSOLUTE_OVERCLOCKED_OSMIUM_COMPRESSOR,
                "osmium_compressor"));
        ABSOLUTE_MACHINES.add(new MachineData(
                MekanismBlocks.PAINTING_MACHINE,
                MekInMachines.ABSOLUTE_OVERCLOCKED_PAINTING_MACHINE,
                "painting_machine"));
        ABSOLUTE_MACHINES.add(new MachineData(
                MekanismBlocks.PURIFICATION_CHAMBER,
                MekInMachines.ABSOLUTE_OVERCLOCKED_PURIFICATION_CHAMBER,
                "purification_chamber"));
        ABSOLUTE_MACHINES.add(new MachineData(
                MekanismBlocks.ROTARY_CONDENSENTRATOR,
                MekInMachines.ABSOLUTE_OVERCLOCKED_ROTARY_CONDENSENTRATOR,
                "rotary_condensentrator"));
        ABSOLUTE_MACHINES.add(new MachineData(
                MekUtMachines.SMALL_DIGITAL_ASSEMBLER,
                MekInMachines.ABSOLUTE_OVERCLOCKED_SMALL_DIGITAL_ASSEMBLER,
                "small_digital_assembler"));
        ABSOLUTE_MACHINES.add(new MachineData(
                MekUtMachines.SMALL_DIGITAL_REACTION_CHAMBER,
                MekInMachines.ABSOLUTE_OVERCLOCKED_SMALL_DIGITAL_REACTION_CAHMBER,
                "small_digital_reaction_chamber"));
        ABSOLUTE_MACHINES.add(new MachineData(
                MekUtMachines.COMPACT_THERMAL_EVAPOLATION_PLANT,
                MekInMachines.ABSOLUTE_OVERCLOCKED_THERMAL_EVAPORATION_PLANT,
                "thermal_evapolation_plant"));

        SUPREME_MACHINES = new ArrayList<>();
        SUPREME_MACHINES.add(new MachineData(
                MekanismBlocks.CHEMICAL_CRYSTALLIZER,
                MekInMachines.SUPREME_QUANTUM_CHEMICAL_CRYSTALLIZER,
                "chemical_crystallizer"));
        SUPREME_MACHINES.add(new MachineData(
                MekanismBlocks.CHEMICAL_DISSOLUTION_CHAMBER,
                MekInMachines.SUPREME_QUANTUM_CHEMICAL_DISSOLUTION_CHAMBER,
                "chemical_dissolution_chamber"));
        SUPREME_MACHINES.add(new MachineData(
                MekanismBlocks.CHEMICAL_WASHER,
                MekInMachines.SUPREME_QUANTUM_CHEMICAL_WASHER,
                "chemical_washer"));
        SUPREME_MACHINES.add(new MachineData(
                MekUtMachines.LAZER_COMPRESS_NUCLEO_SYNTHESIZER,
                MekInMachines.SUPREME_QUANTUM_LAZER_COMPRESS_NUCLEO_SYNTHESIZER,
                "lazer_compress_nucleo_synthesizer"));
        SUPREME_MACHINES.add(new MachineData(
                MekUtMachines.COMPACT_SUPERCRITICAL_PHASE_SHIFTER,
                MekInMachines.SUPREME_QUANTUM_SUPERCRITICAL_PHASE_SHIFTER,
                "supercritical_phase_shifter"));
    }
}
