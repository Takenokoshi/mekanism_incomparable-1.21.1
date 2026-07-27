package com.takenokoshi.mekin.registries;

import com.jerry.genextras.common.config.GeneratorsExtraConfig;
import com.jerry.mekextras.api.ExtraUpgrade;
import com.takenokoshi.mekaddonlib.registration.GuiSizedMachineRegistryObject;
import com.takenokoshi.mekaddonlib.registration.MachineDeferredRegister;
import com.takenokoshi.mekaddonlib.registration.SimpleMachineRegistryObject;
import com.takenokoshi.mekin.blockentity.abs.BEAbstractAET;
import com.takenokoshi.mekin.blockentity.abs.BEAbstractChemicalExtractor;
import com.takenokoshi.mekin.blockentity.abs.BEAbstractChemicalLeachingChamber;
import com.takenokoshi.mekin.blockentity.abs.BEAbstractCompactAPT;
import com.takenokoshi.mekin.blockentity.abs.BEAbstractTEPS;
import com.takenokoshi.mekin.blockentity.machine.BEChemicalExtractor;
import com.takenokoshi.mekin.blockentity.machine.BEChemicalLeachingChamber;
import com.takenokoshi.mekin.blockentity.machine.BEChemicalRefiner;
import com.takenokoshi.mekin.blockentity.machine.BECompactAPT;
import com.takenokoshi.mekin.blockentity.machine.BECompactNaquadahReactor;
import com.takenokoshi.mekin.blockentity.machine.BEFluxCondenser;
import com.takenokoshi.mekin.blockentity.machine.BlockEntityAET;
import com.takenokoshi.mekin.core.MekInConstants;
import com.takenokoshi.mekin.core.MekInMathUtils;
import com.takenokoshi.mekut.blockentity.abs.BEAbstractCompactFusionReactor;
import com.takenokoshi.mekut.blockentity.interfaces.machine.IItemStackChemicalToItemStackMachine;
import fr.iglee42.evolvedmekanism.config.EMConfig;
import mekanism.api.Upgrade;
import mekanism.common.attachments.component.AttachedSideConfig;
import mekanism.common.config.MekanismConfig;
import mekanism.common.lib.transmitter.TransmissionType;
import mekanism.common.registries.MekanismSounds;
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

    public static final GuiSizedMachineRegistryObject<BECompactNaquadahReactor> COMPACT_NAQUADAH_REACTOR = MACHINES
            .registerGuiSized("compact_naquadah_reactor",
                    BEAbstractCompactFusionReactor.SIDE_CONFIG,
                    BECompactNaquadahReactor
                            .getContainerAdder(GeneratorsExtraConfig.extraGenerators.reactorFuelCapacity)::accept,
                    BECompactNaquadahReactor::new,
                    BECompactNaquadahReactor.class,
                    builder -> builder
                            .withSideConfig(TransmissionType.values())
                            .withSound(GeneratorsSounds.FUSION_REACTOR)
                            .withSupportedUpgrades(Upgrade.MUFFLING));

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
}
