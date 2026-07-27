package com.takenokoshi.mekin.registries;

import com.jerry.mekextras.api.ExtraUpgrade;
import com.takenokoshi.mekaddonlib.registration.MachineDeferredRegister;
import com.takenokoshi.mekaddonlib.registration.SimpleMachineRegistryObject;
import com.takenokoshi.mekin.blockentity.abs.BEAbstractAET;
import com.takenokoshi.mekin.blockentity.abs.BEAbstractChemicalCrystallizer;
import com.takenokoshi.mekin.blockentity.abs.BEAbstractChemicalWasher;
import com.takenokoshi.mekin.blockentity.suprememachine.BESupremeChemicalCrystallizer;
import com.takenokoshi.mekin.blockentity.suprememachine.BESupremeChemicalDissolutionChamber;
import com.takenokoshi.mekin.blockentity.suprememachine.BESupremeChemicalWasher;
import com.takenokoshi.mekin.blockentity.suprememachine.BESupremeLCNS;
import com.takenokoshi.mekin.blockentity.suprememachine.BESupremeSPS;
import com.takenokoshi.mekin.core.MekInConstants;
import com.takenokoshi.mekin.core.MekInMathUtils;
import com.takenokoshi.mekut.blockentity.abs.BEAbstractCompactSPS;
import com.takenokoshi.mekut.blockentity.interfaces.machine.IBiChemicalToObjectRecipeMachine;
import com.takenokoshi.mekut.core.MekUtMathUtils;
import com.takenokoshi.mekut.registries.MekUtMachines;

import mekanism.api.Upgrade;
import mekanism.common.MekanismLang;
import mekanism.common.attachments.component.AttachedSideConfig;
import mekanism.common.config.MekanismConfig;
import mekanism.common.lib.transmitter.TransmissionType;
import mekanism.common.registries.MekanismSounds;

public class MekInSupremeMachines {
    
    
    public static final MachineDeferredRegister MACHINES = new MachineDeferredRegister(MekInConstants.MODID);

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
                                    MekInMathUtils.multiplyClamped(MekanismConfig.storage.chemicalCrystallizer, 40))
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
                                            40))
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
                                    MekInMathUtils.multiplyClamped(MekanismConfig.storage.chemicalWasher, 40))
                            .withSideConfig(TransmissionType.ITEM, TransmissionType.FLUID, TransmissionType.CHEMICAL,
                                    TransmissionType.ENERGY)
                            .withSound(MekanismSounds.CHEMICAL_WASHER)
                            .withSupportedUpgrades(Upgrade.ENERGY, Upgrade.SPEED, Upgrade.MUFFLING,
                                    ExtraUpgrade.STACK));

    public static final SimpleMachineRegistryObject<BESupremeLCNS> SUPREME_QUANTUM_LAZER_COMPRESS_NUCLEO_SYNTHESIZER = MACHINES
            .registerSimple("supreme_quantum_lazer_compress_nucleo_synthesizer",
                    AttachedSideConfig.CHEMICAL_INFUSING,
                    IBiChemicalToObjectRecipeMachine.getToChemicalContainerAdder(8_000_000_000L)::accept,
                    BESupremeLCNS::new,
                    BESupremeLCNS.class,
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
