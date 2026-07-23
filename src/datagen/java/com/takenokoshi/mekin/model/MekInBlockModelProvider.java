package com.takenokoshi.mekin.model;

import com.takenokoshi.mekaddonlib.registration.MachineRegistryObject;
import com.takenokoshi.mekin.core.MekInConstants;
import com.takenokoshi.mekin.registries.MekInBlocks;
import com.takenokoshi.mekin.registries.MekInMachines;
import com.takenokoshi.mekut.core.MekUtConstants;

import mekanism.common.Mekanism;
import mekanism.common.block.attribute.AttributeStateActive;
import mekanism.common.block.attribute.Attributes;
import mekanism.common.registration.impl.BlockRegistryObject;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.ModelFile.UncheckedModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class MekInBlockModelProvider extends BlockStateProvider {

    public static final ExistingFileHelper.ResourceType TEXTURE = new ExistingFileHelper.ResourceType(
            PackType.CLIENT_RESOURCES, ".png", "textures");
    protected static final ExistingFileHelper.ResourceType MODEL = new ExistingFileHelper.ResourceType(
            PackType.CLIENT_RESOURCES, ".json", "models");

    protected final ExistingFileHelper exFileHelper;

    public MekInBlockModelProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, MekInConstants.MODID, exFileHelper);
        this.exFileHelper = exFileHelper;
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlockWithItem(MekInBlocks.ABSOLUTE_CONTROL_MATRIX.get(),
                models().cubeAll("block/control_matrix/absolute", MekInConstants.rl("block/control_matrix/absolute")));
        simpleBlockWithItem(MekInBlocks.ADVANCED_CONTROL_MATRIX.get(),
                models().cubeAll("block/control_matrix/advanced", MekInConstants.rl("block/control_matrix/advanced")));
        simpleBlockWithItem(MekInBlocks.BASIC_CONTROL_MATRIX.get(),
                models().cubeAll("block/control_matrix/basic", MekInConstants.rl("block/control_matrix/basic")));
        simpleBlockWithItem(MekInBlocks.COSMIC_CONTROL_MATRIX.get(),
                models().cubeAll("block/control_matrix/cosmic", MekInConstants.rl("block/control_matrix/cosmic")));
        simpleBlockWithItem(MekInBlocks.DENSE_CONTROL_MATRIX.get(),
                models().cubeAll("block/control_matrix/dense", MekInConstants.rl("block/control_matrix/dense")));
        simpleBlockWithItem(MekInBlocks.DIGITAL_CONTROL_MATRIX.get(),
                models().cubeAll("block/control_matrix/digital", MekInConstants.rl("block/control_matrix/digital")));
        simpleBlockWithItem(MekInBlocks.ELITE_CONTROL_MATRIX.get(),
                models().cubeAll("block/control_matrix/elite", MekInConstants.rl("block/control_matrix/elite")));
        simpleBlockWithItem(MekInBlocks.INFINITE_CONTROL_MATRIX.get(),
                models().cubeAll("block/control_matrix/infinite", MekInConstants.rl("block/control_matrix/infinite")));
        simpleBlockWithItem(MekInBlocks.AUGMENT_CONTROL_MATRIX.get(),
                models().cubeAll("block/control_matrix/knowledge", MekInConstants.rl("block/control_matrix/augment")));
        simpleBlockWithItem(MekInBlocks.MULTIVERSAL_CONTROL_MATRIX.get(),
                models().cubeAll("block/control_matrix/multiversal",
                        MekInConstants.rl("block/control_matrix/multiversal")));
        simpleBlockWithItem(MekInBlocks.OVERCLOCKED_CONTROL_MATRIX.get(),
                models().cubeAll("block/control_matrix/overclocked",
                        MekInConstants.rl("block/control_matrix/overclocked")));
        simpleBlockWithItem(MekInBlocks.QUANTUM_CONTROL_MATRIX.get(),
                models().cubeAll("block/control_matrix/quantum", MekInConstants.rl("block/control_matrix/quantum")));
        simpleBlockWithItem(MekInBlocks.STANDARD_CONTROL_MATRIX.get(),
                models().cubeAll("block/control_matrix/standard", MekInConstants.rl("block/control_matrix/standard")));
        simpleBlockWithItem(MekInBlocks.SUPREME_CONTROL_MATRIX.get(),
                models().cubeAll("block/control_matrix/supreme", MekInConstants.rl("block/control_matrix/supreme")));
        simpleBlockWithItem(MekInBlocks.ULTIMATE_CONTROL_MATRIX.get(),
                models().cubeAll("block/control_matrix/ultimate", MekInConstants.rl("block/control_matrix/ultimate")));
        simpleBlockWithItem(MekInBlocks.SPECTRUM_ALLOY_CASING.get(),
                models().cubeAll("block/casing/spectrum_alloy", MekInConstants.rl("block/casing/spectrum_alloy")));
        simpleBlockWithItem(MekInBlocks.EXOVERSAL_ALLOY_CASING.get(),
                models().cubeAll("block/casing/exoversal_alloy", MekInConstants.rl("block/casing/exoversal_alloy")));
        simpleBlockWithItem(MekInBlocks.TITANIUM_CASING.get(),
                models().cubeAll("block/casing/titanium", MekInConstants.rl("block/casing/titanium")));

        simpleMachine(
                MekInMachines.ANTINEUTRONIC_EXISTENCE_TRANSMUTATOR, true,
                "block/machine/aet",
                MekInConstants.rl("block/tier_decoration/cosmic_dense"),
                MekInConstants.rl("block/machine_front/aet"),
                MekInConstants.rl("block/machine_front_active/aet"));
        simpleMachine(
                MekInMachines.COMPACT_ANTIMATTER_PROTOMOLECULAR_TRANSMUTATOR, true,
                "block/machine/compact_apt",
                MekInConstants.rl("block/tier_decoration/quantum"),
                MekInConstants.rl("block/machine_front/apt"),
                MekInConstants.rl("block/machine_front_active/apt"));
        simpleMachine(
                MekInMachines.CHEMICAL_EXTRACTOR, true,
                "block/machine/chemical_extractor",
                MekInConstants.rl("block/tier_decoration/supreme_quantum"),
                MekInConstants.rl("block/machine_front/chemical_extractor"),
                MekInConstants.rl("block/machine_front_active/chemical_extractor"));
        simpleMachine(
                MekInMachines.CHEMICAL_LEACHING_CHAMBER, true,
                "block/machine/chemical_leaching_chamber",
                MekInConstants.rl("block/tier_decoration/absolute_overclocked"),
                MekInConstants.rl("block/machine_front/chemical_leaching_chamber"),
                MekInConstants.rl("block/machine_front_active/chemical_leaching_chamber"));
        simpleMachine(
                MekInMachines.CHEMICAL_REFINER, true,
                "block/machine/chemical_refiner",
                MekInConstants.rl("block/tier_decoration/supreme"),
                MekInConstants.rl("block/machine_front/chemical_refiner"),
                MekInConstants.rl("block/machine_front_active/chemical_refiner"));
        simpleMachine(
                MekInMachines.FLUX_CONDENSER, true,
                "block/machine/flux_condenser",
                MekInConstants.rl("block/tier_decoration/infinite_multiversal"),
                MekInConstants.rl("block/machine_front/flux_condenser"),
                MekInConstants.rl("block/machine_front_active/flux_condenser"));

        simpleMachine(
                MekInMachines.ABSOLUTE_OVERCLOCKED_BOILER, false,
                "block/machine/absolute_overclocked/boiler",
                MekInConstants.rl("block/tier_decoration/absolute_overclocked"),
                MekUtConstants.rl("block/machine_front/boiler"),
                MekUtConstants.rl("block/machine_front_active/boiler"));
        simpleMachine(
                MekInMachines.ABSOLUTE_OVERCLOCKED_CHEMICAL_CUTTER, true,
                "block/machine/absolute_overclocked/chemical_cutter",
                MekInConstants.rl("block/tier_decoration/absolute_overclocked"),
                MekUtConstants.rl("block/machine_front/chemical_cutter"),
                MekUtConstants.rl("block/machine_front_active/chemical_cutter"));
        simpleMachine(
                MekInMachines.ABSOLUTE_OVERCLOCKED_CHEMICAL_INJECTION_CHAMBER, true,
                "block/machine/absolute_overclocked/chemical_injection_chamber",
                MekInConstants.rl("block/tier_decoration/absolute_overclocked"),
                Mekanism.rl("block/chemical_injection_chamber/front"),
                Mekanism.rl("block/chemical_injection_chamber/front_active"));
        simpleMachine(
                MekInMachines.ABSOLUTE_OVERCLOCKED_CHEMICAL_OXIDIZER, true,
                "block/machine/absolute_overclocked/chemical_oxidizer",
                MekInConstants.rl("block/tier_decoration/absolute_overclocked"),
                MekInConstants.rl("block/machine_front/chemical_oxidizer"),
                MekInConstants.rl("block/machine_front_active/chemical_oxidizer"));
        simpleMachine(
                MekInMachines.ABSOLUTE_OVERCLOCKED_CRUSHER, true,
                "block/machine/absolute_overclocked/crusher",
                MekInConstants.rl("block/tier_decoration/absolute_overclocked"),
                Mekanism.rl("block/crusher/front"),
                Mekanism.rl("block/crusher/front_active"));
        simpleMachine(
                MekInMachines.ABSOLUTE_OVERCLOCKED_ELECTROLYTIC_SEPARATOR, true,
                "block/machine/absolute_overclocked/electrolytic_separator",
                MekInConstants.rl("block/tier_decoration/absolute_overclocked"),
                MekInConstants.rl("block/machine_front/electrolytic_separator"),
                MekInConstants.rl("block/machine_front_active/electrolytic_separator"));
        simpleMachine(
                MekInMachines.ABSOLUTE_OVERCLOCKED_ENERIZED_SMELTER, true,
                "block/machine/absolute_overclocked/energized_smelter",
                MekInConstants.rl("block/tier_decoration/absolute_overclocked"),
                Mekanism.rl("block/energized_smelter/front"),
                Mekanism.rl("block/energized_smelter/front_active"));
        simpleMachine(
                MekInMachines.ABSOLUTE_OVERCLOCKED_ENRICHMENT_CHAMBER, true,
                "block/machine/absolute_overclocked/enrichment_chamber",
                MekInConstants.rl("block/tier_decoration/absolute_overclocked"),
                Mekanism.rl("block/enrichment_chamber/front"),
                Mekanism.rl("block/enrichment_chamber/front_active"));
        simpleMachine(
                MekInMachines.ABSOLUTE_OVERCLOCKED_FISSION_REACTOR, false,
                "block/machine/absolute_overclocked/fission_reactor",
                MekInConstants.rl("block/tier_decoration/absolute_overclocked"),
                MekUtConstants.rl("block/machine_front/fission_reactor"),
                MekUtConstants.rl("block/machine_front_active/fission_reactor"));
        simpleMachine(
                MekInMachines.ABSOLUTE_OVERCLOCKED_ICE_MAKER, true,
                "block/machine/absolute_overclocked/ice_maker",
                MekInConstants.rl("block/tier_decoration/absolute_overclocked"),
                MekUtConstants.rl("block/machine_front/ice_maker"),
                MekUtConstants.rl("block/machine_front_active/ice_maker"));
        simpleMachine(
                MekInMachines.ABSOLUTE_OVERCLOCKED_INDUSTRIAL_TURBINE, true,
                "block/machine/absolute_overclocked/industrial_turbine",
                MekInConstants.rl("block/tier_decoration/absolute_overclocked"),
                MekUtConstants.rl("block/machine_front/industrial_turbine"),
                MekUtConstants.rl("block/machine_front_active/industrial_turbine"));
        simpleMachine(
                MekInMachines.ABSOLUTE_OVERCLOCKED_METALLURGIC_INFUSER, true,
                "block/machine/absolute_overclocked/metallurgic_infuser",
                MekInConstants.rl("block/tier_decoration/absolute_overclocked"),
                MekInConstants.rl("block/machine_front/metallurgic_infuser"),
                MekInConstants.rl("block/machine_front_active/metallurgic_infuser"));
        simpleMachine(
                MekInMachines.ABSOLUTE_OVERCLOCKED_OSMIUM_COMPRESSOR, true,
                "block/machine/absolute_overclocked/osmium_compressor",
                MekInConstants.rl("block/tier_decoration/absolute_overclocked"),
                Mekanism.rl("block/osmium_compressor/front"),
                Mekanism.rl("block/osmium_compressor/front_active"));
        simpleMachine(
                MekInMachines.ABSOLUTE_OVERCLOCKED_PAINTING_MACHINE, true,
                "block/machine/absolute_overclocked/painting_machine",
                MekInConstants.rl("block/tier_decoration/absolute_overclocked"),
                Mekanism.rl("block/painting_machine/front"),
                Mekanism.rl("block/painting_machine/front_active"));
        simpleMachine(
                MekInMachines.ABSOLUTE_OVERCLOCKED_PURIFICATION_CHAMBER, true,
                "block/machine/absolute_overclocked/purification_chamber",
                MekInConstants.rl("block/tier_decoration/absolute_overclocked"),
                Mekanism.rl("block/purification_chamber/front"),
                Mekanism.rl("block/purification_chamber/front_active"));
        simpleMachine(
                MekInMachines.ABSOLUTE_OVERCLOCKED_ROTARY_CONDENSENTRATOR, true,
                "block/machine/absolute_overclocked/rotary_condensentrator",
                MekInConstants.rl("block/tier_decoration/absolute_overclocked"),
                MekInConstants.rl("block/machine_front/rotary_condensentrator"),
                MekInConstants.rl("block/machine_front_active/rotary_condensentrator"));
        simpleMachine(
                MekInMachines.ABSOLUTE_OVERCLOCKED_SMALL_DIGITAL_ASSEMBLER, true,
                "block/machine/absolute_overclocked/small_digital_assembler",
                MekInConstants.rl("block/tier_decoration/absolute_overclocked"),
                MekUtConstants.rl("block/machine_front/small_digital_assembler"),
                MekUtConstants.rl("block/machine_front_active/small_digital_assembler"));
        simpleMachine(
                MekInMachines.ABSOLUTE_OVERCLOCKED_SMALL_DIGITAL_REACTION_CAHMBER, true,
                "block/machine/absolute_overclocked/small_digital_reaction_chamber",
                MekInConstants.rl("block/tier_decoration/absolute_overclocked"),
                MekUtConstants.rl("block/machine_front/small_digital_reaction_chamber"),
                MekUtConstants.rl("block/machine_front_active/small_digital_reaction_chamber"));
        simpleMachine(
                MekInMachines.ABSOLUTE_OVERCLOCKED_THERMAL_EVAPORATION_PLANT, false,
                "block/machine/absolute_overclocked/tep",
                MekInConstants.rl("block/tier_decoration/absolute_overclocked"),
                MekUtConstants.rl("block/machine_front/tep"),
                MekUtConstants.rl("block/machine_front_active/tep"));

        simpleMachine(MekInMachines.SUPREME_QUANTUM_CHEMICAL_CRYSTALLIZER, true,
                "block/machine/supreme_quantum/chemical_crystallizer",
                MekInConstants.rl("block/tier_decoration/supreme_quantum"),
                MekInConstants.rl("block/machine_front/chemical_crystallizer"),
                MekInConstants.rl("block/machine_front_active/chemical_crystallizer"));
        simpleMachine(MekInMachines.SUPREME_QUANTUM_CHEMICAL_DISSOLUTION_CHAMBER, true,
                "block/machine/supreme_quantum/chemical_dissolution_chamber",
                MekInConstants.rl("block/tier_decoration/supreme_quantum"),
                MekInConstants.rl("block/machine_front/chemical_dissolution_chamber"),
                MekInConstants.rl("block/machine_front_active/chemical_dissolution_chamber"));
        simpleMachine(MekInMachines.SUPREME_QUANTUM_CHEMICAL_WASHER, true,
                "block/machine/supreme_quantum/chemical_washer",
                MekInConstants.rl("block/tier_decoration/supreme_quantum"),
                MekInConstants.rl("block/machine_front/chemical_washer"),
                MekInConstants.rl("block/machine_front_active/chemical_washer"));
        simpleMachine(
                MekInMachines.SUPREME_QUANTUM_LAZER_COMPRESS_NUCLEO_SYNTHESIZER, true,
                "block/machine/supreme_quantum/lazer_compress_nucleo_synthesizer",
                MekInConstants.rl("block/tier_decoration/supreme_quantum"),
                MekUtConstants.rl("block/machine_front/lazer_compress_nucleo_synthesizer"),
                MekUtConstants.rl("block/machine_front_active/lazer_compress_nucleo_synthesizer"));
        simpleMachine(
                MekInMachines.SUPREME_QUANTUM_SUPERCRITICAL_PHASE_SHIFTER, true,
                "block/machine/supreme_quantum/sps",
                MekInConstants.rl("block/tier_decoration/supreme_quantum"),
                MekUtConstants.rl("block/machine_front/sps"),
                MekUtConstants.rl("block/machine_front_active/sps"));
    }

    protected void simpleMachine(MachineRegistryObject<?, ?, ?, ?> registryObject,
            boolean energy,
            String modelLocBase,
            ResourceLocation decorationLoc,
            ResourceLocation frontLoc,
            ResourceLocation activeFrontLoc) {
        simpleMachine(registryObject.getBlock(), energy, modelLocBase, decorationLoc, frontLoc, activeFrontLoc);
    }

    protected void simpleMachine(
            BlockRegistryObject<?, ?> registryObject,
            boolean energy,
            String modelLocBase,
            ResourceLocation decorationLoc,
            ResourceLocation frontLoc,
            ResourceLocation activeFrontLoc) {

        exFileHelper.trackGenerated(activeFrontLoc, TEXTURE);
        exFileHelper.trackGenerated(frontLoc, TEXTURE);
        exFileHelper.trackGenerated(decorationLoc, TEXTURE);

        ModelFile base = new UncheckedModelFile(MekUtConstants.rl(energy
                ? "block/base/machine_base_energy"
                : "block/base/machine_base"));

        ModelFile inactive = models().getBuilder(modelLocBase).parent(base)
                .texture("front", frontLoc)
                .texture("decoration", decorationLoc);

        ModelFile active = models().getBuilder(modelLocBase + "_active").parent(base)
                .texture("front", activeFrontLoc)
                .texture("decoration", decorationLoc);

        getVariantBuilder(registryObject.get())
                .forAllStates(state -> {
                    Direction facing = state.getValue(
                            BlockStateProperties.HORIZONTAL_FACING);

                    boolean lit = ((AttributeStateActive) (Attributes.ACTIVE_LIGHT)).isActive(state);

                    return ConfiguredModel.builder()
                            .modelFile(lit ? active : inactive)
                            .rotationY(((int) facing.toYRot() + 180) % 360)
                            .build();
                });

        simpleBlockItem(
                registryObject.get(),
                inactive);

    }

}
