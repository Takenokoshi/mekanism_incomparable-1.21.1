package com.takenokoshi.mekin.registries;

import com.jerry.mekextras.api.ExtraUpgrade;
import com.takenokoshi.mekaddonlib.registration.GuiSizedMachineRegistryObject;
import com.takenokoshi.mekaddonlib.registration.MachineDeferredRegister;
import com.takenokoshi.mekaddonlib.registration.SimpleMachineRegistryObject;
import com.takenokoshi.mekin.blockentity.abs.BEAbstractAET;
import com.takenokoshi.mekin.blockentity.abs.BEAbstractChemicalExtractor;
import com.takenokoshi.mekin.blockentity.abs.BEAbstractChemicalLeachingChamber;
import com.takenokoshi.mekin.blockentity.abs.BEAbstractChemicalOxidizer;
import com.takenokoshi.mekin.blockentity.abs.BEAbstractChemicalWasher;
import com.takenokoshi.mekin.blockentity.abs.BEAbstractElectrolyticSeparator;
import com.takenokoshi.mekin.blockentity.abs.BEAbstractItemStackChemicalToItemStackMachine;
import com.takenokoshi.mekin.blockentity.abs.BEAbstractItemStackToItemStackMachine;
import com.takenokoshi.mekin.blockentity.abs.BEAbstractRotaryCondensentrator;
import com.takenokoshi.mekin.blockentity.infinitemachine.*;
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

import mekanism.api.Upgrade;
import mekanism.api.math.MathUtils;
import mekanism.common.MekanismLang;
import mekanism.common.attachments.component.AttachedSideConfig;
import mekanism.common.config.MekanismConfig;
import mekanism.common.lib.transmitter.TransmissionType;
import mekanism.common.registries.MekanismSounds;
import mekanism.common.util.ChemicalUtil;
import mekanism.generators.common.registries.GeneratorsSounds;

public class MekInInfiniteMachines {

    public static final MachineDeferredRegister MACHINES = new MachineDeferredRegister(MekInConstants.MODID);

    public static final GuiSizedMachineRegistryObject<BEInfiniteBoiler> INFINITE_MULTIVERSAL_BOILER = MACHINES
            .registerGuiSized("infinite_multiversal_boiler",
                    BEAbstractCompactBoiler.SIDE_CONFIG,
                    BEAbstractCompactBoiler.getContainerAdder(2_310_144_000_000L, 622_080_000_000L, 991_728_000_000L,
                            0x7fffffff)::accept,
                    BEInfiniteBoiler::new,
                    BEInfiniteBoiler.class,
                    MekUtMachines.COMPACT_BOILER.descriptionEntry,
                    builder -> builder
                            .withSideConfig(TransmissionType.CHEMICAL, TransmissionType.FLUID, TransmissionType.HEAT)
                            .withSound(MekanismSounds.CHARGEPAD)
                            .withSupportedUpgrades(Upgrade.MUFFLING));

    public static final SimpleMachineRegistryObject<BEInfiniteChemicalCrystallizer> INFINITE_MULTIVERSAL_CHEMICAL_CRYSTALLIZER = MACHINES
            .registerSimple("infinite_multiversal_chemical_crystallizer",
                    AttachedSideConfig.CRYSTALLIZER,
                    IItemStackChemicalToItemStackMachine.getContainerAdder(960_000_000L)::accept,
                    BEInfiniteChemicalCrystallizer::new,
                    BEInfiniteChemicalCrystallizer.class,
                    MekanismLang.DESCRIPTION_CHEMICAL_CRYSTALLIZER,
                    builder -> builder
                            .withEnergyConfig(
                                    MekanismConfig.usage.chemicalCrystallizer,
                                    MekInMathUtils.multiplyClamped(MekanismConfig.storage.chemicalCrystallizer, 4800))
                            .withSideConfig(TransmissionType.ITEM, TransmissionType.CHEMICAL,
                                    TransmissionType.ENERGY)
                            .withSound(MekanismSounds.CHEMICAL_WASHER)
                            .withSupportedUpgrades(Upgrade.ENERGY, Upgrade.SPEED, Upgrade.MUFFLING,
                                    ExtraUpgrade.STACK));

    public static final SimpleMachineRegistryObject<BEInfiniteChemicalCutter> INFINITE_MULTIVERSAL_CHEMICAL_CUTTER = MACHINES
            .registerSimple("infinite_multiversal_chemical_cutter",
                    AttachedSideConfig.EXTRA_MACHINE,
                    IItemStackChemicalToItemStackMachine.getContainerAdder(9_600_000_000L)::accept,
                    BEInfiniteChemicalCutter::new,
                    BEInfiniteChemicalCutter.class,
                    MekUtMachines.CHEMICAL_CUTTER.descriptionEntry,
                    builder -> builder
                            .withSideConfig(TransmissionType.ITEM, TransmissionType.CHEMICAL, TransmissionType.ENERGY)
                            .withEnergyConfig(
                                    MekanismConfig.usage.chemicalCrystallizer,
                                    MekInMathUtils.multiplyClamped(MekanismConfig.storage.chemicalCrystallizer, 2400))
                            .withSupportedUpgrades(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.CHEMICAL));

    public static final SimpleMachineRegistryObject<BEInfiniteChemicalDissolutionChamber> INFINITE_MULTIVERSAL_CHEMICAL_DISSOLUTION_CHAMBER = MACHINES
            .registerSimple("infinite_multiversal_chemical_dissolution_chamber",
                    AttachedSideConfig.DISSOLUTION,
                    BEAbstractAET.getContainerAdder(960_000_000L)::accept,
                    BEInfiniteChemicalDissolutionChamber::new,
                    BEInfiniteChemicalDissolutionChamber.class,
                    MekanismLang.DESCRIPTION_CHEMICAL_DISSOLUTION_CHAMBER,
                    builder -> builder
                            .withEnergyConfig(
                                    MekanismConfig.usage.chemicalDissolutionChamber,
                                    MekInMathUtils.multiplyClamped(MekanismConfig.storage.chemicalDissolutionChamber,
                                            4800))
                            .withSideConfig(TransmissionType.ITEM, TransmissionType.CHEMICAL,
                                    TransmissionType.ENERGY)
                            .withSound(MekanismSounds.CHEMICAL_DISSOLUTION_CHAMBER)
                            .withSupportedUpgrades(Upgrade.ENERGY, Upgrade.SPEED, Upgrade.MUFFLING, Upgrade.CHEMICAL,
                                    ExtraUpgrade.STACK));

    public static final SimpleMachineRegistryObject<BEInfiniteChemicalExtractor> INFINITE_MULTIVERSAL_CHEMICAL_EXTRACTOR = MACHINES
            .registerSimple("infinite_multiversal_chemical_extractor",
                    BEAbstractChemicalExtractor.SIDE_CONFIG,
                    BEAbstractChemicalExtractor.getContainerAdder(4_800_000_000L, 240_000_000)::accept,
                    BEInfiniteChemicalExtractor::new,
                    BEInfiniteChemicalExtractor.class,
                    MekInMachines.CHEMICAL_EXTRACTOR.descriptionEntry,
                    builder -> builder
                            .withEnergyConfig(
                                    MekInMathUtils.multiplyClamped(MekanismConfig.usage.chemicalWasher, 20),
                                    MekInMathUtils.multiplyClamped(MekanismConfig.storage.chemicalWasher, 2400))
                            .withSideConfig(TransmissionType.FLUID, TransmissionType.CHEMICAL, TransmissionType.ENERGY,
                                    TransmissionType.ITEM)
                            .withSound(MekanismSounds.CHEMICAL_WASHER)
                            .withSupportedUpgrades(Upgrade.SPEED, Upgrade.ENERGY, ExtraUpgrade.STACK,
                                    Upgrade.MUFFLING));

    public static final SimpleMachineRegistryObject<BEInfiniteChemicalInjectionChamber> INFINITE_MULTIVERSAL_CHEMICAL_INJECTION_CHAMBER = MACHINES
            .registerSimple("infinite_multiversal_chemical_injection_chamber",
                    AttachedSideConfig.ADVANCED_MACHINE_INPUT_ONLY,
                    BEAbstractItemStackChemicalToItemStackMachine.getContainerAdder(96_000_000L)::accept,
                    BEInfiniteChemicalInjectionChamber::new,
                    BEInfiniteChemicalInjectionChamber.class,
                    MekanismLang.DESCRIPTION_CHEMICAL_INJECTION_CHAMBER,
                    builder -> builder
                            .withSideConfig(TransmissionType.ITEM, TransmissionType.CHEMICAL, TransmissionType.ENERGY)
                            .withEnergyConfig(MekanismConfig.usage.chemicalInjectionChamber,
                                    MekInMathUtils.multiplyClamped(MekanismConfig.storage.chemicalInjectionChamber,
                                            2400))
                            .withSound(MekanismSounds.CHEMICAL_INJECTION_CHAMBER)
                            .withSupportedUpgrades(Upgrade.ENERGY, Upgrade.SPEED, Upgrade.CHEMICAL, Upgrade.MUFFLING,
                                    ExtraUpgrade.STACK));

    public static final GuiSizedMachineRegistryObject<BEInfiniteChemicalLeachingChamber> INFINITE_MULTIVERSAL_CHEMICAL_LEACHING_CHAMBER = MACHINES
            .registerGuiSized("infinite_multiversal_chemical_leaching_chamber",
                    BEAbstractChemicalLeachingChamber.SIDE_CONFIG,
                    BEAbstractChemicalLeachingChamber.getContainerAdder(96_000_000L, 4_800_000)::accept,
                    BEInfiniteChemicalLeachingChamber::new,
                    BEInfiniteChemicalLeachingChamber.class,
                    MekInMachines.CHEMICAL_LEACHING_CHAMBER.descriptionEntry,
                    builder -> builder
                            .withEnergyConfig(
                                    MekInMathUtils.multiplyClamped(MekanismConfig.usage.pressurizedReactionBase, 16),
                                    MekInMathUtils.multiplyClamped(MekanismConfig.storage.pressurizedReactionBase,
                                            38400))
                            .withSideConfig(TransmissionType.ITEM, TransmissionType.FLUID, TransmissionType.CHEMICAL,
                                    TransmissionType.ENERGY)
                            .withSound(MekanismSounds.PURIFICATION_CHAMBER)
                            .withSupportedUpgrades(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING,
                                    ExtraUpgrade.STACK));

    public static final SimpleMachineRegistryObject<BEInfiniteChemicalOxidizer> INFINITE_MULTIVERSAL_CHEMICAL_OXIDIZER = MACHINES
            .registerSimple("infinite_multiversal_chemical_oxidizer",
                    AttachedSideConfig.CHEMICAL_OUT_MACHINE,
                    BEAbstractChemicalOxidizer.getContainerAdder(960_000_000L)::accept,
                    BEInfiniteChemicalOxidizer::new,
                    BEInfiniteChemicalOxidizer.class,
                    builder -> builder
                            .withSideConfig(TransmissionType.ITEM, TransmissionType.CHEMICAL, TransmissionType.ENERGY)
                            .withEnergyConfig(MekanismConfig.usage.chemicalOxidizer,
                                    MekInMathUtils.multiplyClamped(MekanismConfig.storage.chemicalOxidizer, 2400))
                            .withSound(MekanismSounds.CHEMICAL_OXIDIZER)
                            .withSupportedUpgrades(Upgrade.ENERGY, Upgrade.SPEED, Upgrade.MUFFLING,
                                    ExtraUpgrade.STACK));

    public static final SimpleMachineRegistryObject<BEInfiniteChemicalWasher> INFINITE_MULTIVERSAL_CHEMICAL_WASHER = MACHINES
            .registerSimple("infinite_multiversal_chemical_washer",
                    AttachedSideConfig.WASHER,
                    BEAbstractChemicalWasher.getContainerAdder(960_000_000L, 96_000_000)::accept,
                    BEInfiniteChemicalWasher::new,
                    BEInfiniteChemicalWasher.class,
                    MekanismLang.DESCRIPTION_CHEMICAL_WASHER,
                    builder -> builder
                            .withEnergyConfig(
                                    MekanismConfig.usage.chemicalWasher,
                                    MekInMathUtils.multiplyClamped(MekanismConfig.storage.chemicalWasher, 4800))
                            .withSideConfig(TransmissionType.ITEM, TransmissionType.FLUID, TransmissionType.CHEMICAL,
                                    TransmissionType.ENERGY)
                            .withSound(MekanismSounds.CHEMICAL_WASHER)
                            .withSupportedUpgrades(Upgrade.ENERGY, Upgrade.SPEED, Upgrade.MUFFLING,
                                    ExtraUpgrade.STACK));

    public static final SimpleMachineRegistryObject<BEInfiniteCrusher> INFINITE_MULTIVERSAL_CRUSHER = MACHINES
            .registerSimple("infinite_multiversal_crusher",
                    AttachedSideConfig.ELECTRIC_MACHINE,
                    BEAbstractItemStackToItemStackMachine::addContainersToItem,
                    BEInfiniteCrusher::new,
                    BEInfiniteCrusher.class,
                    MekanismLang.DESCRIPTION_CRUSHER,
                    builder -> builder
                            .withSideConfig(TransmissionType.ITEM, TransmissionType.ENERGY)
                            .withEnergyConfig(MekanismConfig.usage.crusher,
                                    MekInMathUtils.multiplyClamped(MekanismConfig.storage.crusher, 2400))
                            .withSound(MekanismSounds.CRUSHER)
                            .withSupportedUpgrades(Upgrade.ENERGY, Upgrade.SPEED, Upgrade.MUFFLING,
                                    ExtraUpgrade.STACK));

    public static final SimpleMachineRegistryObject<BEInfiniteElectrolyticSeparator> INFINITE_MULTIVERSAL_ELECTROLYTIC_SEPARATOR = MACHINES
            .registerSimple("infinite_multiversal_electrolytic_separator",
                    AttachedSideConfig.SEPARATOR,
                    BEAbstractElectrolyticSeparator.getContainerAdder(480_000, 480_000L)::accept,
                    BEInfiniteElectrolyticSeparator::new,
                    BEInfiniteElectrolyticSeparator.class,
                    MekanismLang.DESCRIPTION_ELECTROLYTIC_SEPARATOR,
                    builder -> builder
                            .withSideConfig(TransmissionType.ITEM, TransmissionType.FLUID, TransmissionType.CHEMICAL,
                                    TransmissionType.ENERGY)
                            .withEnergyConfig(
                                    () -> MathUtils.multiplyClamped(2, ChemicalUtil.hydrogenEnergyDensity()),
                                    MekInMathUtils.multiplyClamped(MekanismConfig.storage.electrolyticSeparator, 2400))
                            .withSound(MekanismSounds.ELECTROLYTIC_SEPARATOR)
                            .withSupportedUpgrades(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING,
                                    ExtraUpgrade.STACK));

    public static final SimpleMachineRegistryObject<BEInfiniteEnergizedSmelter> INFINITE_MULTIVERSAL_ENERGIZED_SMELTER = MACHINES
            .registerSimple("infinite_multiversal_energized_smelter",
                    AttachedSideConfig.CHEMICAL_OUT_MACHINE,
                    BEAbstractEnergizedSmelter::addContainersToItem,
                    BEInfiniteEnergizedSmelter::new,
                    BEInfiniteEnergizedSmelter.class,
                    MekUtMachines.TWEAKED_ENERGIZED_SMELTER.descriptionEntry,
                    builder -> builder
                            .withEnergyConfig(
                                    MekanismConfig.usage.energizedSmelter,
                                    MekInMathUtils.multiplyClamped(MekanismConfig.storage.energizedSmelter, 2400))
                            .withSideConfig(TransmissionType.ITEM, TransmissionType.CHEMICAL, TransmissionType.ENERGY)
                            .withSound(MekanismSounds.ENERGIZED_SMELTER)
                            .withSupportedUpgrades(Upgrade.ENERGY, Upgrade.SPEED, Upgrade.MUFFLING));

    public static final SimpleMachineRegistryObject<BEInfiniteEnrichmentChamber> INFINITE_MULTIVERSAL_ENRICHMENT_CHAMBER = MACHINES
            .registerSimple("infinite_multiversal_enrichment_chamber",
                    AttachedSideConfig.ELECTRIC_MACHINE,
                    BEAbstractItemStackToItemStackMachine::addContainersToItem,
                    BEInfiniteEnrichmentChamber::new,
                    BEInfiniteEnrichmentChamber.class,
                    MekanismLang.DESCRIPTION_ENRICHMENT_CHAMBER,
                    builder -> builder
                            .withSideConfig(TransmissionType.ITEM, TransmissionType.ENERGY)
                            .withEnergyConfig(MekanismConfig.usage.enrichmentChamber,
                                    MekInMathUtils.multiplyClamped(MekanismConfig.storage.enrichmentChamber, 2400))
                            .withSound(MekanismSounds.ENRICHMENT_CHAMBER)
                            .withSupportedUpgrades(Upgrade.ENERGY, Upgrade.SPEED, Upgrade.MUFFLING,
                                    ExtraUpgrade.STACK));

    public static final GuiSizedMachineRegistryObject<BEInfiniteFissionReactor> INFINITE_MULTIVERSAL_FISSION_REACTOR = MACHINES
            .registerGuiSized("infinite_multiversal_fission_reactor",
                    BEAbstractCompactFissionReactor.SIDE_CONFIG,
                    item -> BEAbstractCompactFissionReactor.addContainers(item,
                            36_864_000_000L,
                            4_166_400_000.0d,
                            0x7fffffff,
                            1_399_680_000_000L,
                            13_996_800_000_000L),
                    BEInfiniteFissionReactor::new,
                    BEInfiniteFissionReactor.class,
                    MekUtMachines.COMPACT_FISSION_REACTOR.descriptionEntry,
                    builder -> builder
                            .withSideConfig(TransmissionType.CHEMICAL, TransmissionType.FLUID, TransmissionType.HEAT)
                            .withSound(GeneratorsSounds.FISSION_REACTOR)
                            .withSupportedUpgrades(Upgrade.MUFFLING));

    public static final SimpleMachineRegistryObject<BEInfiniteIceMaker> INFINITE_MULTIVERSAL_ICE_MAKER = MACHINES
            .registerSimple("infinite_multiversal_ice_maker",
                    IFluidToObjectMachine.SIDE_CONFIG_TO_ITEM,
                    IFluidToObjectMachine.getToItemContainerAdder(200_000)::accept,
                    BEInfiniteIceMaker::new,
                    BEInfiniteIceMaker.class,
                    MekUtMachines.ICE_MAKER.descriptionEntry,
                    builder -> builder
                            .withSideConfig(TransmissionType.ITEM, TransmissionType.FLUID, TransmissionType.ENERGY)
                            .withEnergyConfig(
                                    MekanismConfig.usage.chemicalCrystallizer,
                                    MekInMathUtils.multiplyClamped(MekanismConfig.storage.chemicalCrystallizer, 2400))
                            .withSupportedUpgrades(Upgrade.SPEED, Upgrade.ENERGY));

    public static final SimpleMachineRegistryObject<BEInfiniteIndustrialTurbine> INFINITE_MULTIVERSAL_INDUSTRIAL_TURBINE = MACHINES
            .registerSimple("infinite_multiversal_industrial_turbine",
                    BEAbstractCompactIndustrialTurbine.SIDE_CONFIG,
                    BEAbstractCompactIndustrialTurbine.getContainerAdder(577_075_200_000L, 0x7fffffff)::accept,
                    BEInfiniteIndustrialTurbine::new,
                    BEInfiniteIndustrialTurbine.class,
                    builder -> builder
                            .withSideConfig(TransmissionType.CHEMICAL, TransmissionType.ENERGY, TransmissionType.FLUID,
                                    TransmissionType.ITEM)
                            .withSupportedUpgrades(Upgrade.FILTER));

    public static final SimpleMachineRegistryObject<BEInfiniteLCNS> INFINITE_MULTIVERSAL_LAZER_COMPRESS_NUCLEO_SYNTHESIZER = MACHINES
            .registerSimple("infinite_multiversal_lazer_compress_nucleo_synthesizer",
                    AttachedSideConfig.CHEMICAL_INFUSING,
                    IBiChemicalToObjectRecipeMachine.getToChemicalContainerAdder(1_920_000_000_000L)::accept,
                    BEInfiniteLCNS::new,
                    BEInfiniteLCNS.class,
                    MekUtMachines.LAZER_COMPRESS_NUCLEO_SYNTHESIZER.descriptionEntry,
                    builder -> builder
                            .withSideConfig(TransmissionType.ITEM, TransmissionType.CHEMICAL, TransmissionType.ENERGY)
                            .withEnergyConfig(
                                    MekanismConfig.usage.antiprotonicNucleosynthesizer,
                                    MekInMathUtils.multiplyClamped(MekanismConfig.storage.antiprotonicNucleosynthesizer,
                                            4800))
                            .withSound(MekanismSounds.ANTIPROTONIC_NUCLEOSYNTHESIZER)
                            .withSupportedUpgrades(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING,
                                    ExtraUpgrade.STACK));

    public static final SimpleMachineRegistryObject<BEInfiniteMetallurgicInfuser> INFINITE_MULTIVERSAL_METALLURGIC_INFUSER = MACHINES
            .registerSimple("infinite_multiversal_metallurgic_infuser",
                    AttachedSideConfig.ADVANCED_MACHINE_INPUT_ONLY,
                    BEAbstractItemStackChemicalToItemStackMachine.getContainerAdder(960_000_000L)::accept,
                    BEInfiniteMetallurgicInfuser::new,
                    BEInfiniteMetallurgicInfuser.class,
                    MekanismLang.DESCRIPTION_METALLURGIC_INFUSER,
                    builder -> builder
                            .withSideConfig(TransmissionType.ITEM, TransmissionType.CHEMICAL, TransmissionType.ENERGY)
                            .withEnergyConfig(MekanismConfig.usage.metallurgicInfuser,
                                    MekInMathUtils.multiplyClamped(MekanismConfig.storage.metallurgicInfuser, 2400))
                            .withSound(MekanismSounds.METALLURGIC_INFUSER)
                            .withSupportedUpgrades(Upgrade.ENERGY, Upgrade.SPEED, Upgrade.MUFFLING,
                                    ExtraUpgrade.STACK));

    public static final SimpleMachineRegistryObject<BEInfiniteOsmiumCompressor> INFINITE_MULTIVERSAL_OSMIUM_COMPRESSOR = MACHINES
            .registerSimple("infinite_multiversal_osmium_compressor",
                    AttachedSideConfig.ADVANCED_MACHINE_INPUT_ONLY,
                    BEAbstractItemStackChemicalToItemStackMachine.getContainerAdder(9_600_000L)::accept,
                    BEInfiniteOsmiumCompressor::new,
                    BEInfiniteOsmiumCompressor.class,
                    MekanismLang.DESCRIPTION_OSMIUM_COMPRESSOR,
                    builder -> builder
                            .withSideConfig(TransmissionType.ITEM, TransmissionType.CHEMICAL, TransmissionType.ENERGY)
                            .withEnergyConfig(MekanismConfig.usage.osmiumCompressor,
                                    MekInMathUtils.multiplyClamped(MekanismConfig.storage.osmiumCompressor, 20))
                            .withSound(MekanismSounds.OSMIUM_COMPRESSOR)
                            .withSupportedUpgrades(Upgrade.ENERGY, Upgrade.SPEED, Upgrade.MUFFLING,
                                    ExtraUpgrade.STACK));

    public static final SimpleMachineRegistryObject<BEInfinitePaintingMachine> INFINITE_MULTIVERSAL_PAINTING_MACHINE = MACHINES
            .registerSimple("infinite_multiversal_painting_machine",
                    AttachedSideConfig.ADVANCED_MACHINE_INPUT_ONLY,
                    BEAbstractItemStackChemicalToItemStackMachine.getContainerAdder(1_440_000_000L)::accept,
                    BEInfinitePaintingMachine::new,
                    BEInfinitePaintingMachine.class,
                    builder -> builder
                            .withSideConfig(TransmissionType.ITEM, TransmissionType.CHEMICAL, TransmissionType.ENERGY)
                            .withEnergyConfig(MekanismConfig.usage.paintingMachine,
                                    MekInMathUtils.multiplyClamped(MekanismConfig.storage.paintingMachine, 2400))
                            .withSound(MekanismSounds.PAINTING_MACHINE)
                            .withSupportedUpgrades(Upgrade.ENERGY, Upgrade.SPEED, Upgrade.MUFFLING,
                                    ExtraUpgrade.STACK));

    public static final SimpleMachineRegistryObject<BEInfinitePurificationChamber> INFINITE_MULTIVERSAL_PURIFICATION_CHAMBER = MACHINES
            .registerSimple("infinite_multiversal_purification_chamber",
                    AttachedSideConfig.ADVANCED_MACHINE_INPUT_ONLY,
                    BEAbstractItemStackChemicalToItemStackMachine.getContainerAdder(9_600_000L)::accept,
                    BEInfinitePurificationChamber::new,
                    BEInfinitePurificationChamber.class,
                    MekanismLang.DESCRIPTION_PURIFICATION_CHAMBER,
                    builder -> builder
                            .withSideConfig(TransmissionType.ITEM, TransmissionType.CHEMICAL, TransmissionType.ENERGY)
                            .withEnergyConfig(MekanismConfig.usage.purificationChamber,
                                    MekInMathUtils.multiplyClamped(MekanismConfig.storage.purificationChamber, 2400))
                            .withSound(MekanismSounds.PURIFICATION_CHAMBER)
                            .withSupportedUpgrades(Upgrade.ENERGY, Upgrade.SPEED, Upgrade.CHEMICAL, Upgrade.MUFFLING,
                                    ExtraUpgrade.STACK));

    public static final SimpleMachineRegistryObject<BEInfiniteRotaryCondensentrator> INFINITE_MULTIVERSAL_ROTARY_CONDENSENTRATOR = MACHINES
            .registerSimple("infinite_multiversal_rotary_condensentrator",
                    AttachedSideConfig.ROTARY,
                    BEAbstractRotaryCondensentrator.getContainerAdder(480_000_000L, 24_000_000)::accept,
                    BEInfiniteRotaryCondensentrator::new,
                    BEInfiniteRotaryCondensentrator.class,
                    builder -> builder
                            .withSound(MekanismSounds.ROTARY_CONDENSENTRATOR)
                            .withEnergyConfig(
                                    MekanismConfig.usage.rotaryCondensentrator,
                                    MekInMathUtils.multiplyClamped(MekanismConfig.storage.rotaryCondensentrator, 2400))
                            .withSideConfig(TransmissionType.CHEMICAL, TransmissionType.FLUID, TransmissionType.ITEM,
                                    TransmissionType.ENERGY)
                            .withSupportedUpgrades(Upgrade.ENERGY, Upgrade.SPEED, Upgrade.MUFFLING,
                                    ExtraUpgrade.STACK));

    public static final GuiSizedMachineRegistryObject<BEInfiniteSmallDigitalAssembler> INFINITE_MULTIVERSAL_SMALL_DIGITAL_ASSEMBLER = MACHINES
            .registerGuiSized("infinite_multiversal_small_digital_assembler",
                    IItemStackListFluidChemicalToItemRecipeMachine.SIDE_CONFIG,
                    IItemStackListFluidChemicalToItemRecipeMachine::addContainersToItem,
                    BEInfiniteSmallDigitalAssembler::new,
                    BEInfiniteSmallDigitalAssembler.class,
                    MekUtMachines.SMALL_DIGITAL_ASSEMBLER.descriptionEntry,
                    builder -> builder
                            .withEnergyConfig(
                                    MekanismConfig.usage.formulaicAssemblicator,
                                    MekInMathUtils.multiplyClamped(MekanismConfig.storage.formulaicAssemblicator, 2400))
                            .withSideConfig(TransmissionType.ITEM, TransmissionType.FLUID, TransmissionType.CHEMICAL,
                                    TransmissionType.ENERGY)
                            .withSound(MekanismSounds.CHEMICAL_CRYSTALLIZER)
                            .withSupportedUpgrades(Upgrade.ENERGY, Upgrade.SPEED, Upgrade.MUFFLING));

    public static final GuiSizedMachineRegistryObject<BEInfiniteSmallDigitalReactionChamber> INFINITE_MULTIVERSAL_SMALL_DIGITAL_REACTION_CHAMBER = MACHINES
            .registerGuiSized("infinite_multiversal_small_digital_reaction_chamber",
                    IItemStackListFluidChemicalToItemFluidChemicalRecipeMachine.SIDE_CONFIG,
                    IItemStackListFluidChemicalToItemFluidChemicalRecipeMachine::addContainersToItem,
                    BEInfiniteSmallDigitalReactionChamber::new,
                    BEInfiniteSmallDigitalReactionChamber.class,
                    MekUtMachines.SMALL_DIGITAL_REACTION_CHAMBER.descriptionEntry,
                    builder -> builder
                            .withEnergyConfig(
                                    MekanismConfig.usage.pressurizedReactionBase,
                                    MekInMathUtils.multiplyClamped(MekanismConfig.storage.chemicalCrystallizer, 2400))
                            .withSideConfig(TransmissionType.ITEM, TransmissionType.FLUID, TransmissionType.CHEMICAL,
                                    TransmissionType.ENERGY)
                            .withSound(MekanismSounds.PRESSURIZED_REACTION_CHAMBER)
                            .withSupportedUpgrades(Upgrade.ENERGY, Upgrade.SPEED, Upgrade.MUFFLING));

    public static final SimpleMachineRegistryObject<BEInfiniteSPS> INFINITE_MULTIVERSAL_SUPERCRITICAL_PHASE_SHIFTER = MACHINES
            .registerSimple("infinite_multiversal_supercritical_phase_shifter",
                    AttachedSideConfig.CENTRIFUGE,
                    BEAbstractCompactSPS.getContainerAdder(192_000_000)::accept,
                    BEInfiniteSPS::new,
                    BEInfiniteSPS.class,
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
                                            4800))
                            .withSound(MekanismSounds.SPS)
                            .withSupportedUpgrades(Upgrade.MUFFLING));

    public static final GuiSizedMachineRegistryObject<BEInfiniteTEP> INFINITE_MULTIVERSAL_THERMAL_EVAPORATION_PLANT = MACHINES
            .registerGuiSized("infinite_multiversal_thermal_evaporation_plant",
                    BEAbstractCompactThermalEvaporationPlant.SIDE_CONFIG,
                    BEAbstractCompactThermalEvaporationPlant.getContainerAdder(0x7fffffff)::accept,
                    BEInfiniteTEP::new, BEInfiniteTEP.class,
                    MekUtMachines.COMPACT_THERMAL_EVAPOLATION_PLANT.descriptionEntry,
                    builder -> builder
                            .withSideConfig(TransmissionType.FLUID, TransmissionType.ITEM, TransmissionType.HEAT)
                            .withSupportedUpgrades(Upgrade.MUFFLING));
}
