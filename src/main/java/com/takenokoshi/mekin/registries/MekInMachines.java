package com.takenokoshi.mekin.registries;

import com.jerry.mekextras.api.ExtraUpgrade;
import com.takenokoshi.mekaddonlib.registration.GuiSizedMachineRegistryObject;
import com.takenokoshi.mekaddonlib.registration.MachineDeferredRegister;
import com.takenokoshi.mekaddonlib.registration.SimpleMachineRegistryObject;
import com.takenokoshi.mekin.blockentity.abs.BEAbstractAET;
import com.takenokoshi.mekin.blockentity.abs.BEAbstractChemicalCrystallizer;
import com.takenokoshi.mekin.blockentity.abs.BEAbstractChemicalExtractor;
import com.takenokoshi.mekin.blockentity.abs.BEAbstractChemicalLeachingChamber;
import com.takenokoshi.mekin.blockentity.abs.BEAbstractChemicalOxidizer;
import com.takenokoshi.mekin.blockentity.abs.BEAbstractChemicalWasher;
import com.takenokoshi.mekin.blockentity.abs.BEAbstractCompactAPT;
import com.takenokoshi.mekin.blockentity.abs.BEAbstractElectrolyticSeparator;
import com.takenokoshi.mekin.blockentity.abs.BEAbstractItemStackChemicalToItemStackMachine;
import com.takenokoshi.mekin.blockentity.abs.BEAbstractItemStackToItemStackMachine;
import com.takenokoshi.mekin.blockentity.abs.BEAbstractRotaryCondensentrator;
import com.takenokoshi.mekin.blockentity.abs.BEAbstractTEPS;
import com.takenokoshi.mekin.blockentity.absolutemachine.*;
import com.takenokoshi.mekin.blockentity.machine.BEChemicalExtractor;
import com.takenokoshi.mekin.blockentity.machine.BEChemicalLeachingChamber;
import com.takenokoshi.mekin.blockentity.machine.BEChemicalRefiner;
import com.takenokoshi.mekin.blockentity.machine.BECompactAPT;
import com.takenokoshi.mekin.blockentity.machine.BEFluxCondenser;
import com.takenokoshi.mekin.blockentity.machine.BlockEntityAET;
import com.takenokoshi.mekin.blockentity.suprememachine.BESupremeChemicalCrystallizer;
import com.takenokoshi.mekin.blockentity.suprememachine.BESupremeChemicalDissolutionChamber;
import com.takenokoshi.mekin.blockentity.suprememachine.BESupremeChemicalWasher;
import com.takenokoshi.mekin.blockentity.suprememachine.BESupremeLazerCopmpressNucleoSynthesizer;
import com.takenokoshi.mekin.blockentity.suprememachine.BESupremeSPS;
import com.takenokoshi.mekin.core.MekInConstants;
import com.takenokoshi.mekin.core.MekInMathUtils;
import com.takenokoshi.mekut.blockentity.abs.BEAbstractCompactBoiler;
import com.takenokoshi.mekut.blockentity.abs.BEAbstractCompactFissionReactor;
import com.takenokoshi.mekut.blockentity.abs.BEAbstractCompactIndustrialTurbine;
import com.takenokoshi.mekut.blockentity.abs.BEAbstractCompactSPS;
import com.takenokoshi.mekut.blockentity.abs.BEAbstractCompactThermalEvaporationPlant;
import com.takenokoshi.mekut.blockentity.abs.BEAbstractEnergizedSmelter;
import com.takenokoshi.mekut.blockentity.interfaces.machine.IBiChemicalToObjectRecipeMachine;
import com.takenokoshi.mekut.blockentity.interfaces.machine.IFluidToObjectMachine;
import com.takenokoshi.mekut.blockentity.interfaces.machine.IItemStackChemicalToItemStackMachine;
import com.takenokoshi.mekut.blockentity.interfaces.machine.IItemStackListFluidChemicalToItemFluidChemicalRecipeMachine;
import com.takenokoshi.mekut.blockentity.interfaces.machine.IItemStackListFluidChemicalToItemRecipeMachine;
import com.takenokoshi.mekut.core.MekUtMathUtils;
import com.takenokoshi.mekut.registries.MekUtMachines;

import fr.iglee42.evolvedmekanism.config.EMConfig;
import mekanism.api.Upgrade;
import mekanism.api.math.MathUtils;
import mekanism.common.MekanismLang;
import mekanism.common.attachments.component.AttachedSideConfig;
import mekanism.common.config.MekanismConfig;
import mekanism.common.lib.transmitter.TransmissionType;
import mekanism.common.registries.MekanismSounds;
import mekanism.common.util.ChemicalUtil;
import mekanism.generators.common.registries.GeneratorsSounds;

public class MekInMachines {
    public static final MachineDeferredRegister MACHINES = new MachineDeferredRegister(MekInConstants.MODID);

    public static final SimpleMachineRegistryObject<BlockEntityAET> ANTINEUTRONIC_EXISTENCE_TRANSMUTATOR = MACHINES
            .registerSimple("antineutronic_existence_transmutator",
                    AttachedSideConfig.DISSOLUTION,
                    BEAbstractAET.getContainerAdder(200_000_000L)::accept,
                    BlockEntityAET::new,
                    BlockEntityAET.class,
                    builder -> builder
                            .withEnergyConfig(
                                    MekInMathUtils.multiplyClamped(EMConfig.general.aptEnergyConsumption, 160),
                                    MekInMathUtils.multiplyClamped(EMConfig.general.aptEnergyStorage, 160))
                            .withSideConfig(TransmissionType.ITEM, TransmissionType.CHEMICAL, TransmissionType.ENERGY)
                            .withSupportedUpgrades(Upgrade.ENERGY, Upgrade.SPEED, Upgrade.MUFFLING,
                                    ExtraUpgrade.STACK)
                            .withSound(MekanismSounds.SPS));

    public static final SimpleMachineRegistryObject<BEChemicalExtractor> CHEMICAL_EXTRACTOR = MACHINES
            .registerSimple("chemical_extractor",
                    BEAbstractChemicalExtractor.SIDE_CONFIG,
                    BEAbstractChemicalExtractor.getContainerAdder(2_000_000L, 2_000_000)::accept,
                    BEChemicalExtractor::new,
                    BEChemicalExtractor.class,
                    builder -> builder
                            .withEnergyConfig(
                                    MekInMathUtils.multiplyClamped(MekanismConfig.usage.chemicalWasher, 20),
                                    MekInMathUtils.multiplyClamped(MekanismConfig.storage.chemicalWasher, 20))
                            .withSideConfig(TransmissionType.FLUID, TransmissionType.CHEMICAL, TransmissionType.ENERGY,
                                    TransmissionType.ITEM)
                            .withSound(MekanismSounds.CHEMICAL_WASHER)
                            .withSupportedUpgrades(Upgrade.SPEED, Upgrade.ENERGY, ExtraUpgrade.STACK,
                                    Upgrade.MUFFLING));

    public static final GuiSizedMachineRegistryObject<BEChemicalLeachingChamber> CHEMICAL_LEACHING_CHAMBER = MACHINES
            .registerGuiSized("chemical_leaching_chamber",
                    BEAbstractChemicalLeachingChamber.SIDE_CONFIG,
                    BEAbstractChemicalLeachingChamber.getContainerAdder(40000L, 40000)::accept,
                    BEChemicalLeachingChamber::new,
                    BEChemicalLeachingChamber.class,
                    builder -> builder
                            .withEnergyConfig(
                                    MekInMathUtils.multiplyClamped(MekanismConfig.usage.pressurizedReactionBase, 16),
                                    MekInMathUtils.multiplyClamped(MekanismConfig.storage.pressurizedReactionBase, 320))
                            .withSideConfig(TransmissionType.ITEM, TransmissionType.FLUID, TransmissionType.CHEMICAL,
                                    TransmissionType.ENERGY)
                            .withSound(MekanismSounds.PURIFICATION_CHAMBER)
                            .withSupportedUpgrades(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING,
                                    ExtraUpgrade.STACK));

    public static final SimpleMachineRegistryObject<BEChemicalRefiner> CHEMICAL_REFINER = MACHINES
            .registerSimple("chemical_refiner",
                    AttachedSideConfig.ADVANCED_MACHINE_INPUT_ONLY,
                    IItemStackChemicalToItemStackMachine.getContainerAdder(2000L)::accept,
                    BEChemicalRefiner::new,
                    BEChemicalRefiner.class,
                    builder -> builder
                            .withEnergyConfig(
                                    MekInMathUtils.multiplyClamped(MekanismConfig.usage.chemicalInjectionChamber, 4),
                                    MekInMathUtils.multiplyClamped(MekanismConfig.storage.chemicalInjectionChamber, 80))
                            .withSideConfig(TransmissionType.ITEM, TransmissionType.CHEMICAL, TransmissionType.ENERGY)
                            .withSound(MekanismSounds.CHEMICAL_INJECTION_CHAMBER)
                            .withSupportedUpgrades(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.CHEMICAL, Upgrade.MUFFLING,
                                    ExtraUpgrade.STACK));

    public static final SimpleMachineRegistryObject<BEFluxCondenser> FLUX_CONDENSER = MACHINES
            .registerSimple("flux_condenser",
                    BEFluxCondenser.SIDE_CONFIG,
                    BEFluxCondenser::addContainrsToItem,
                    BEFluxCondenser::new,
                    BEFluxCondenser.class,
                    builder -> builder
                            .withSideConfig(TransmissionType.ENERGY, TransmissionType.FLUID, TransmissionType.ITEM)
                            .withSound(MekanismSounds.ROTARY_CONDENSENTRATOR)
                            .withSupportedUpgrades(Upgrade.MUFFLING));

    public static final GuiSizedMachineRegistryObject<BEAbstractTEPS> TEPS = MACHINES
            .registerGuiSized("tachyonic_elementary_particle_synthesizer",
                    AttachedSideConfig.ADVANCED_MACHINE_INPUT_ONLY,
                    BEAbstractTEPS.getContainerAddar(1)::accept,
                    BEAbstractTEPS.getConstructor(1),
                    BEAbstractTEPS.class,
                    builder -> builder
                            .withEnergyConfig(
                                    MekInMathUtils.multiplyClamped(EMConfig.general.aptEnergyConsumption, 20),
                                    MekInMathUtils.multiplyClamped(EMConfig.general.aptEnergyStorage, 20))
                            .withSideConfig(TransmissionType.ITEM, TransmissionType.CHEMICAL, TransmissionType.ENERGY)
                            .withSupportedUpgrades(Upgrade.MUFFLING)
                            .withSound(MekanismSounds.SPS));

    public static final GuiSizedMachineRegistryObject<BECompactAPT> COMPACT_ANTIMATTER_PROTOMOLECULAR_TRANSMUTATOR = MACHINES
            .registerGuiSized("compact_antimatter_protomolecular_transmutator",
                    AttachedSideConfig.ADVANCED_MACHINE_INPUT_ONLY,
                    BEAbstractCompactAPT.getContainerAddar(10000L)::accept,
                    BECompactAPT::new,
                    BECompactAPT.class,
                    builder -> builder
                            .withEnergyConfig(
                                    EMConfig.general.aptEnergyConsumption,
                                    EMConfig.general.aptEnergyStorage)
                            .withSideConfig(TransmissionType.ITEM, TransmissionType.CHEMICAL, TransmissionType.ENERGY)
                            .withSupportedUpgrades(Upgrade.MUFFLING)
                            .withSound(MekanismSounds.SPS));

    public static final GuiSizedMachineRegistryObject<BEAbsoluteBoiler> ABSOLUTE_OVERCLOCKED_BOILER = MACHINES
            .registerGuiSized("absolute_overclocked_boiler",
                    BEAbstractCompactBoiler.SIDE_CONFIG,
                    BEAbstractCompactBoiler.getContainerAdder(19_251_200_000L, 5_184_000_000L, 8_294_400_000L,
                            123_200_000)::accept,
                    BEAbsoluteBoiler::new,
                    BEAbsoluteBoiler.class,
                    MekUtMachines.COMPACT_BOILER.descriptionEntry,
                    builder -> builder
                            .withSideConfig(TransmissionType.CHEMICAL, TransmissionType.FLUID, TransmissionType.HEAT)
                            .withSound(MekanismSounds.CHARGEPAD)
                            .withSupportedUpgrades(Upgrade.MUFFLING));

    public static final SimpleMachineRegistryObject<BEAbsoluteChemicalCutter> ABSOLUTE_OVERCLOCKED_CHEMICAL_CUTTER = MACHINES
            .registerSimple("absolute_overclocked_chemical_cutter",
                    AttachedSideConfig.EXTRA_MACHINE,
                    IItemStackChemicalToItemStackMachine.getContainerAdder(4_000_000L)::accept,
                    BEAbsoluteChemicalCutter::new,
                    BEAbsoluteChemicalCutter.class,
                    MekUtMachines.CHEMICAL_CUTTER.descriptionEntry,
                    builder -> builder
                            .withSideConfig(TransmissionType.ITEM, TransmissionType.CHEMICAL, TransmissionType.ENERGY)
                            .withEnergyConfig(
                                    MekanismConfig.usage.chemicalCrystallizer,
                                    MekInMathUtils.multiplyClamped(MekanismConfig.storage.chemicalCrystallizer, 20))
                            .withSupportedUpgrades(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.CHEMICAL));

    public static final SimpleMachineRegistryObject<BEAbsoluteChemicalInjectionChamber> ABSOLUTE_OVERCLOCKED_CHEMICAL_INJECTION_CHAMBER = MACHINES
            .registerSimple("absolute_overclocked_chemical_injection_chamber",
                    AttachedSideConfig.ADVANCED_MACHINE_INPUT_ONLY,
                    BEAbstractItemStackChemicalToItemStackMachine.getContainerAdder(40000L)::accept,
                    BEAbsoluteChemicalInjectionChamber::new,
                    BEAbsoluteChemicalInjectionChamber.class,
                    MekanismLang.DESCRIPTION_CHEMICAL_INJECTION_CHAMBER,
                    builder -> builder
                            .withSideConfig(TransmissionType.ITEM, TransmissionType.CHEMICAL, TransmissionType.ENERGY)
                            .withEnergyConfig(MekanismConfig.usage.chemicalInjectionChamber,
                                    MekInMathUtils.multiplyClamped(MekanismConfig.storage.chemicalInjectionChamber, 20))
                            .withSound(MekanismSounds.CHEMICAL_INJECTION_CHAMBER)
                            .withSupportedUpgrades(Upgrade.ENERGY, Upgrade.SPEED, Upgrade.CHEMICAL, Upgrade.MUFFLING,
                                    ExtraUpgrade.STACK));

    public static final SimpleMachineRegistryObject<BEAbsoluteChemicalOxidizer> ABSOLUTE_OVERCLOCKED_CHEMICAL_OXIDIZER = MACHINES
            .registerSimple("absolute_overclocked_chemical_oxidizer",
                    AttachedSideConfig.CHEMICAL_OUT_MACHINE,
                    BEAbstractChemicalOxidizer.getContainerAdder(400_000L)::accept,
                    BEAbsoluteChemicalOxidizer::new,
                    BEAbsoluteChemicalOxidizer.class,
                    MekanismLang.DESCRIPTION_CHEMICAL_OXIDIZER,
                    builder -> builder
                            .withSideConfig(TransmissionType.ITEM, TransmissionType.CHEMICAL, TransmissionType.ENERGY)
                            .withEnergyConfig(MekanismConfig.usage.chemicalOxidizer,
                                    MekInMathUtils.multiplyClamped(MekanismConfig.storage.chemicalOxidizer, 20))
                            .withSound(MekanismSounds.CHEMICAL_OXIDIZER)
                            .withSupportedUpgrades(Upgrade.ENERGY, Upgrade.SPEED, Upgrade.MUFFLING,
                                    ExtraUpgrade.STACK));

    public static final SimpleMachineRegistryObject<BEAbsoluteCrusher> ABSOLUTE_OVERCLOCKED_CRUSHER = MACHINES
            .registerSimple("absolute_overclocked_crusher",
                    AttachedSideConfig.ELECTRIC_MACHINE,
                    BEAbstractItemStackToItemStackMachine::addContainersToItem,
                    BEAbsoluteCrusher::new,
                    BEAbsoluteCrusher.class,
                    MekanismLang.DESCRIPTION_CRUSHER,
                    builder -> builder
                            .withSideConfig(TransmissionType.ITEM, TransmissionType.ENERGY)
                            .withEnergyConfig(MekanismConfig.usage.crusher,
                                    MekInMathUtils.multiplyClamped(MekanismConfig.storage.crusher, 20))
                            .withSound(MekanismSounds.CRUSHER)
                            .withSupportedUpgrades(Upgrade.ENERGY, Upgrade.SPEED, Upgrade.MUFFLING,
                                    ExtraUpgrade.STACK));

    public static final SimpleMachineRegistryObject<BEAbsoluteElectrolyticSeparator> ABSOLUTE_OVERCLOCKED_ELECTROLYTIC_SEPARATOR = MACHINES
            .registerSimple("absolute_overclocked_electrolytic_separator",
                    AttachedSideConfig.SEPARATOR,
                    BEAbstractElectrolyticSeparator.getContainerAdder(480_000, 480_000L)::accept,
                    BEAbsoluteElectrolyticSeparator::new,
                    BEAbsoluteElectrolyticSeparator.class,
                    MekanismLang.DESCRIPTION_ELECTROLYTIC_SEPARATOR,
                    builder -> builder
                            .withSideConfig(TransmissionType.ITEM, TransmissionType.FLUID, TransmissionType.CHEMICAL,
                                    TransmissionType.ENERGY)
                            .withEnergyConfig(
                                    () -> MathUtils.multiplyClamped(2, ChemicalUtil.hydrogenEnergyDensity()),
                                    MekInMathUtils.multiplyClamped(MekanismConfig.storage.electrolyticSeparator, 20))
                            .withSound(MekanismSounds.ELECTROLYTIC_SEPARATOR)
                            .withSupportedUpgrades(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING,
                                    ExtraUpgrade.STACK));

    public static final SimpleMachineRegistryObject<BEAbsoluteEnergizedSmelter> ABSOLUTE_OVERCLOCKED_ENERIZED_SMELTER = MACHINES
            .registerSimple("absolute_overclocked_energized_smelter",
                    AttachedSideConfig.CHEMICAL_OUT_MACHINE,
                    BEAbstractEnergizedSmelter::addContainersToItem,
                    BEAbsoluteEnergizedSmelter::new,
                    BEAbsoluteEnergizedSmelter.class,
                    MekUtMachines.TWEAKED_ENERGIZED_SMELTER.descriptionEntry,
                    builder -> builder
                            .withEnergyConfig(
                                    MekanismConfig.usage.energizedSmelter,
                                    MekInMathUtils.multiplyClamped(MekanismConfig.storage.energizedSmelter, 20))
                            .withSideConfig(TransmissionType.ITEM, TransmissionType.CHEMICAL, TransmissionType.ENERGY)
                            .withSound(MekanismSounds.ENERGIZED_SMELTER)
                            .withSupportedUpgrades(Upgrade.ENERGY, Upgrade.SPEED, Upgrade.MUFFLING));

    public static final SimpleMachineRegistryObject<BEAbsoluteEnrichmentChamber> ABSOLUTE_OVERCLOCKED_ENRICHMENT_CHAMBER = MACHINES
            .registerSimple("absolute_overclocked_enrichment_chamber",
                    AttachedSideConfig.ELECTRIC_MACHINE,
                    BEAbstractItemStackToItemStackMachine::addContainersToItem,
                    BEAbsoluteEnrichmentChamber::new,
                    BEAbsoluteEnrichmentChamber.class,
                    MekanismLang.DESCRIPTION_ENRICHMENT_CHAMBER,
                    builder -> builder
                            .withSideConfig(TransmissionType.ITEM, TransmissionType.ENERGY)
                            .withEnergyConfig(MekanismConfig.usage.enrichmentChamber,
                                    MekInMathUtils.multiplyClamped(MekanismConfig.storage.enrichmentChamber, 20))
                            .withSound(MekanismSounds.ENRICHMENT_CHAMBER)
                            .withSupportedUpgrades(Upgrade.ENERGY, Upgrade.SPEED, Upgrade.MUFFLING,
                                    ExtraUpgrade.STACK));

    public static final GuiSizedMachineRegistryObject<BEAbsoluteFissionReactor> ABSOLUTE_OVERCLOCKED_FISSION_REACTOR = MACHINES
            .registerGuiSized("absolute_overclocked_fission_reactor",
                    BEAbstractCompactFissionReactor.SIDE_CONFIG,
                    item -> BEAbstractCompactFissionReactor.addContainers(item,
                            307_200_000L,
                            34_720_000.0d,
                            0x7fffffff,
                            11_664_000_000L,
                            116_640_000_000L),
                    BEAbsoluteFissionReactor::new,
                    BEAbsoluteFissionReactor.class,
                    MekUtMachines.COMPACT_FISSION_REACTOR.descriptionEntry,
                    builder -> builder
                            .withSideConfig(TransmissionType.CHEMICAL, TransmissionType.FLUID, TransmissionType.HEAT)
                            .withSound(GeneratorsSounds.FISSION_REACTOR)
                            .withSupportedUpgrades(Upgrade.MUFFLING));

    public static final SimpleMachineRegistryObject<BEAbsoluteIceMaker> ABSOLUTE_OVERCLOCKED_ICE_MAKER = MACHINES
            .registerSimple("absolute_overclocked_ice_maker",
                    IFluidToObjectMachine.SIDE_CONFIG_TO_ITEM,
                    IFluidToObjectMachine.getToItemContainerAdder(200_000)::accept,
                    BEAbsoluteIceMaker::new,
                    BEAbsoluteIceMaker.class,
                    MekUtMachines.ICE_MAKER.descriptionEntry,
                    builder -> builder
                            .withSideConfig(TransmissionType.ITEM, TransmissionType.FLUID, TransmissionType.ENERGY)
                            .withEnergyConfig(
                                    MekanismConfig.usage.chemicalCrystallizer,
                                    MekInMathUtils.multiplyClamped(MekanismConfig.storage.chemicalCrystallizer, 20))
                            .withSupportedUpgrades(Upgrade.SPEED, Upgrade.ENERGY));

    public static final SimpleMachineRegistryObject<BEAbsoluteIndustrialTurbine> ABSOLUTE_OVERCLOCKED_INDUSTRIAL_TURBINE = MACHINES
            .registerSimple("absolute_overclocked_industrial_turbine",
                    BEAbstractCompactIndustrialTurbine.SIDE_CONFIG,
                    BEAbstractCompactIndustrialTurbine.getContainerAdder(4_808_960_000L, 259_840_000)::accept,
                    BEAbsoluteIndustrialTurbine::new,
                    BEAbsoluteIndustrialTurbine.class,
                    MekUtMachines.COMPACT_INDUSTRIAL_TURBINE.descriptionEntry,
                    builder -> builder
                            .withSideConfig(TransmissionType.CHEMICAL, TransmissionType.ENERGY, TransmissionType.FLUID,
                                    TransmissionType.ITEM)
                            .withSupportedUpgrades(Upgrade.FILTER));

    public static final SimpleMachineRegistryObject<BEAbsoluteMetallurgicInfuser> ABSOLUTE_OVERCLOCKED_METALLURGIC_INFUSER = MACHINES
            .registerSimple("absolute_overclocked_metallurgic_infuser",
                    AttachedSideConfig.ADVANCED_MACHINE_INPUT_ONLY,
                    BEAbstractItemStackChemicalToItemStackMachine.getContainerAdder(200000L)::accept,
                    BEAbsoluteMetallurgicInfuser::new,
                    BEAbsoluteMetallurgicInfuser.class,
                    MekanismLang.DESCRIPTION_METALLURGIC_INFUSER,
                    builder -> builder
                            .withSideConfig(TransmissionType.ITEM, TransmissionType.CHEMICAL, TransmissionType.ENERGY)
                            .withEnergyConfig(MekanismConfig.usage.metallurgicInfuser,
                                    MekInMathUtils.multiplyClamped(MekanismConfig.storage.metallurgicInfuser, 20))
                            .withSound(MekanismSounds.METALLURGIC_INFUSER)
                            .withSupportedUpgrades(Upgrade.ENERGY, Upgrade.SPEED, Upgrade.MUFFLING,
                                    ExtraUpgrade.STACK));

    public static final SimpleMachineRegistryObject<BEAbsoluteOsmiumCompressor> ABSOLUTE_OVERCLOCKED_OSMIUM_COMPRESSOR = MACHINES
            .registerSimple("absolute_overclocked_osmium_compressor",
                    AttachedSideConfig.ADVANCED_MACHINE_INPUT_ONLY,
                    BEAbstractItemStackChemicalToItemStackMachine.getContainerAdder(40000L)::accept,
                    BEAbsoluteOsmiumCompressor::new,
                    BEAbsoluteOsmiumCompressor.class,
                    MekanismLang.DESCRIPTION_OSMIUM_COMPRESSOR,
                    builder -> builder
                            .withSideConfig(TransmissionType.ITEM, TransmissionType.CHEMICAL, TransmissionType.ENERGY)
                            .withEnergyConfig(MekanismConfig.usage.osmiumCompressor,
                                    MekInMathUtils.multiplyClamped(MekanismConfig.storage.osmiumCompressor, 20))
                            .withSound(MekanismSounds.OSMIUM_COMPRESSOR)
                            .withSupportedUpgrades(Upgrade.ENERGY, Upgrade.SPEED, Upgrade.MUFFLING,
                                    ExtraUpgrade.STACK));

    public static final SimpleMachineRegistryObject<BEAbsolutePaintingMachine> ABSOLUTE_OVERCLOCKED_PAINTING_MACHINE = MACHINES
            .registerSimple("absolute_overclocked_painting_machine",
                    AttachedSideConfig.ADVANCED_MACHINE_INPUT_ONLY,
                    BEAbstractItemStackChemicalToItemStackMachine.getContainerAdder(3000000L)::accept,
                    BEAbsolutePaintingMachine::new,
                    BEAbsolutePaintingMachine.class,
                    MekanismLang.DESCRIPTION_PAINTING_MACHINE,
                    builder -> builder
                            .withSideConfig(TransmissionType.ITEM, TransmissionType.CHEMICAL, TransmissionType.ENERGY)
                            .withEnergyConfig(MekanismConfig.usage.paintingMachine,
                                    MekInMathUtils.multiplyClamped(MekanismConfig.storage.paintingMachine, 20))
                            .withSound(MekanismSounds.PAINTING_MACHINE)
                            .withSupportedUpgrades(Upgrade.ENERGY, Upgrade.SPEED, Upgrade.MUFFLING,
                                    ExtraUpgrade.STACK));

    public static final SimpleMachineRegistryObject<BEAbsolutePurificationChamber> ABSOLUTE_OVERCLOCKED_PURIFICATION_CHAMBER = MACHINES
            .registerSimple("absolute_overclocked_purification_chamber",
                    AttachedSideConfig.ADVANCED_MACHINE_INPUT_ONLY,
                    BEAbstractItemStackChemicalToItemStackMachine.getContainerAdder(40000L)::accept,
                    BEAbsolutePurificationChamber::new,
                    BEAbsolutePurificationChamber.class,
                    MekanismLang.DESCRIPTION_PURIFICATION_CHAMBER,
                    builder -> builder
                            .withSideConfig(TransmissionType.ITEM, TransmissionType.CHEMICAL, TransmissionType.ENERGY)
                            .withEnergyConfig(MekanismConfig.usage.purificationChamber,
                                    MekInMathUtils.multiplyClamped(MekanismConfig.storage.purificationChamber, 20))
                            .withSound(MekanismSounds.PURIFICATION_CHAMBER)
                            .withSupportedUpgrades(Upgrade.ENERGY, Upgrade.SPEED, Upgrade.CHEMICAL, Upgrade.MUFFLING,
                                    ExtraUpgrade.STACK));

    public static final SimpleMachineRegistryObject<BEAbsoluteRotaryCondensentrator> ABSOLUTE_OVERCLOCKED_ROTARY_CONDENSENTRATOR = MACHINES
            .registerSimple("absolute_overclocked_rotary_condensentrator",
                    AttachedSideConfig.ROTARY,
                    BEAbstractRotaryCondensentrator.getContainerAdder(200_000L, 200_000)::accept,
                    BEAbsoluteRotaryCondensentrator::new,
                    BEAbsoluteRotaryCondensentrator.class,
                    MekanismLang.DESCRIPTION_ROTARY_CONDENSENTRATOR,
                    builder -> builder
                            .withSound(MekanismSounds.ROTARY_CONDENSENTRATOR)
                            .withEnergyConfig(
                                    MekanismConfig.usage.rotaryCondensentrator,
                                    MekInMathUtils.multiplyClamped(MekanismConfig.storage.rotaryCondensentrator, 20))
                            .withSideConfig(TransmissionType.CHEMICAL, TransmissionType.FLUID, TransmissionType.ITEM,
                                    TransmissionType.ENERGY)
                            .withSupportedUpgrades(Upgrade.ENERGY, Upgrade.SPEED, Upgrade.MUFFLING,
                                    ExtraUpgrade.STACK));

    public static final GuiSizedMachineRegistryObject<BEAbsoluteSmallDigitalAssembler> ABSOLUTE_OVERCLOCKED_SMALL_DIGITAL_ASSEMBLER = MACHINES
            .registerGuiSized("absolute_overclocked_small_digital_assembler",
                    IItemStackListFluidChemicalToItemRecipeMachine.SIDE_CONFIG,
                    IItemStackListFluidChemicalToItemRecipeMachine::addContainersToItem,
                    BEAbsoluteSmallDigitalAssembler::new,
                    BEAbsoluteSmallDigitalAssembler.class,
                    MekUtMachines.SMALL_DIGITAL_ASSEMBLER.descriptionEntry,
                    builder -> builder
                            .withEnergyConfig(
                                    MekanismConfig.usage.formulaicAssemblicator,
                                    MekInMathUtils.multiplyClamped(MekanismConfig.storage.formulaicAssemblicator, 20))
                            .withSideConfig(TransmissionType.ITEM, TransmissionType.FLUID, TransmissionType.CHEMICAL,
                                    TransmissionType.ENERGY)
                            .withSound(MekanismSounds.CHEMICAL_CRYSTALLIZER)
                            .withSupportedUpgrades(Upgrade.ENERGY, Upgrade.SPEED, Upgrade.MUFFLING));

    public static final GuiSizedMachineRegistryObject<BEAbsoluteSmallDigitalReactionChamber> ABSOLUTE_OVERCLOCKED_SMALL_DIGITAL_REACTION_CAHMBER = MACHINES
            .registerGuiSized("absolute_overclocked_small_digital_reaction_chamber",
                    IItemStackListFluidChemicalToItemFluidChemicalRecipeMachine.SIDE_CONFIG,
                    IItemStackListFluidChemicalToItemFluidChemicalRecipeMachine::addContainersToItem,
                    BEAbsoluteSmallDigitalReactionChamber::new,
                    BEAbsoluteSmallDigitalReactionChamber.class,
                    MekUtMachines.SMALL_DIGITAL_REACTION_CHAMBER.descriptionEntry,
                    builder -> builder
                            .withEnergyConfig(
                                    MekanismConfig.usage.pressurizedReactionBase,
                                    MekInMathUtils.multiplyClamped(MekanismConfig.storage.chemicalCrystallizer, 20))
                            .withSideConfig(TransmissionType.ITEM, TransmissionType.FLUID, TransmissionType.CHEMICAL,
                                    TransmissionType.ENERGY)
                            .withSound(MekanismSounds.PRESSURIZED_REACTION_CHAMBER)
                            .withSupportedUpgrades(Upgrade.ENERGY, Upgrade.SPEED, Upgrade.MUFFLING));

    public static final GuiSizedMachineRegistryObject<BEAbsoluteTEP> ABSOLUTE_OVERCLOCKED_THERMAL_EVAPORATION_PLANT = MACHINES
            .registerGuiSized("absolute_overclocked_thermal_evaporation_plant",
                    BEAbstractCompactThermalEvaporationPlant.SIDE_CONFIG,
                    BEAbstractCompactThermalEvaporationPlant.getContainerAdder(100_000_000)::accept,
                    BEAbsoluteTEP::new,
                    BEAbsoluteTEP.class,
                    MekUtMachines.COMPACT_THERMAL_EVAPOLATION_PLANT.descriptionEntry,
                    builder -> builder
                            .withSideConfig(TransmissionType.FLUID, TransmissionType.ITEM, TransmissionType.HEAT)
                            .withSupportedUpgrades(Upgrade.MUFFLING));

    public static final SimpleMachineRegistryObject<BESupremeChemicalCrystallizer> SUPREME_QUANTUM_CHEMICAL_CRYSTALLIZER = MACHINES
            .registerSimple("supreme_quantum_chemical_crystallizer",
                    AttachedSideConfig.CRYSTALLIZER,
                    BEAbstractChemicalCrystallizer.getContainerAdder(4_000_000L)::accept,
                    BESupremeChemicalCrystallizer::new,
                    BESupremeChemicalCrystallizer.class,
                    MekanismLang.DESCRIPTION_CHEMICAL_CRYSTALLIZER,
                    builder -> builder
                            .withEnergyConfig(
                                    MekanismConfig.usage.chemicalCrystallizer,
                                    MekInMathUtils.multiplyClamped(MekanismConfig.storage.chemicalCrystallizer, 20))
                            .withSideConfig(TransmissionType.ITEM, TransmissionType.CHEMICAL,
                                    TransmissionType.ENERGY)
                            .withSound(MekanismSounds.CHEMICAL_WASHER)
                            .withSupportedUpgrades(Upgrade.ENERGY, Upgrade.SPEED, Upgrade.MUFFLING,
                                    ExtraUpgrade.STACK));

    public static final SimpleMachineRegistryObject<BESupremeChemicalDissolutionChamber> SUPREME_QUANTUM_CHEMICAL_DISSOLUTION_CHAMBER = MACHINES
            .registerSimple("supreme_quantum_chemical_dissolution_chamber",
                    AttachedSideConfig.DISSOLUTION,
                    BEAbstractAET.getContainerAdder(4_000_000L)::accept,
                    BESupremeChemicalDissolutionChamber::new,
                    BESupremeChemicalDissolutionChamber.class,
                    MekanismLang.DESCRIPTION_CHEMICAL_DISSOLUTION_CHAMBER,
                    builder -> builder
                            .withEnergyConfig(
                                    MekanismConfig.usage.chemicalDissolutionChamber,
                                    MekInMathUtils.multiplyClamped(MekanismConfig.storage.chemicalDissolutionChamber,
                                            20))
                            .withSideConfig(TransmissionType.ITEM, TransmissionType.CHEMICAL,
                                    TransmissionType.ENERGY)
                            .withSound(MekanismSounds.CHEMICAL_DISSOLUTION_CHAMBER)
                            .withSupportedUpgrades(Upgrade.ENERGY, Upgrade.SPEED, Upgrade.MUFFLING, Upgrade.CHEMICAL,
                                    ExtraUpgrade.STACK));

    public static final SimpleMachineRegistryObject<BESupremeChemicalWasher> SUPREME_QUANTUM_CHEMICAL_WASHER = MACHINES
            .registerSimple("supreme_quantum_chemical_washer",
                    AttachedSideConfig.WASHER,
                    BEAbstractChemicalWasher.getContainerAdder(4_000_000L, 400_000)::accept,
                    BESupremeChemicalWasher::new,
                    BESupremeChemicalWasher.class,
                    MekanismLang.DESCRIPTION_CHEMICAL_WASHER,
                    builder -> builder
                            .withEnergyConfig(
                                    MekanismConfig.usage.chemicalWasher,
                                    MekInMathUtils.multiplyClamped(MekanismConfig.storage.chemicalWasher, 20))
                            .withSideConfig(TransmissionType.ITEM, TransmissionType.FLUID, TransmissionType.CHEMICAL,
                                    TransmissionType.ENERGY)
                            .withSound(MekanismSounds.CHEMICAL_WASHER)
                            .withSupportedUpgrades(Upgrade.ENERGY, Upgrade.SPEED, Upgrade.MUFFLING,
                                    ExtraUpgrade.STACK));

    public static final SimpleMachineRegistryObject<BESupremeLazerCopmpressNucleoSynthesizer> SUPREME_QUANTUM_LAZER_COMPRESS_NUCLEO_SYNTHESIZER = MACHINES
            .registerSimple("supreme_quantum_lazer_compress_nucleo_synthesizer",
                    AttachedSideConfig.CHEMICAL_INFUSING,
                    IBiChemicalToObjectRecipeMachine.getToChemicalContainerAdder(8_000_000_000L)::accept,
                    BESupremeLazerCopmpressNucleoSynthesizer::new,
                    BESupremeLazerCopmpressNucleoSynthesizer.class,
                    MekUtMachines.LAZER_COMPRESS_NUCLEO_SYNTHESIZER.descriptionEntry,
                    builder -> builder
                            .withSideConfig(TransmissionType.ITEM, TransmissionType.CHEMICAL, TransmissionType.ENERGY)
                            .withEnergyConfig(
                                    MekanismConfig.usage.antiprotonicNucleosynthesizer,
                                    MekInMathUtils.multiplyClamped(MekanismConfig.storage.antiprotonicNucleosynthesizer,
                                            40))
                            .withSound(MekanismSounds.ANTIPROTONIC_NUCLEOSYNTHESIZER)
                            .withSupportedUpgrades(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING,
                                    ExtraUpgrade.STACK));

    public static final SimpleMachineRegistryObject<BESupremeSPS> SUPREME_QUANTUM_SUPERCRITICAL_PHASE_SHIFTER = MACHINES
            .registerSimple("supreme_quantum_supercritical_phase_shifter",
                    AttachedSideConfig.CENTRIFUGE,
                    BEAbstractCompactSPS.getContainerAdder(800_000)::accept,
                    BESupremeSPS::new,
                    BESupremeSPS.class,
                    MekUtMachines.COMPACT_SUPERCRITICAL_PHASE_SHIFTER.descriptionEntry,
                    builder -> builder
                            .withSideConfig(TransmissionType.CHEMICAL, TransmissionType.ENERGY)
                            .withEnergyConfig(
                                    MekUtMathUtils.getMultiplied(
                                            MekanismConfig.general.spsEnergyPerInput,
                                            MekanismConfig.general.spsInputPerAntimatter),
                                    MekInMathUtils.multiplyClamped(
                                            MekUtMathUtils.getMultiplied(
                                                    MekanismConfig.general.spsEnergyPerInput,
                                                    MekanismConfig.general.spsInputPerAntimatter,
                                                    MekanismConfig.general.spsOutputTankCapacity),
                                            40))
                            .withSound(MekanismSounds.SPS)
                            .withSupportedUpgrades(Upgrade.MUFFLING));
}
