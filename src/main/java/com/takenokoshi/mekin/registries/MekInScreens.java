package com.takenokoshi.mekin.registries;

import com.takenokoshi.mekaddonlib.registration.MachineRegistryObject;
import com.takenokoshi.mekin.blockentity.abs.BEAbstractTEPS;
import com.takenokoshi.mekin.blockentity.absolutemachine.*;
import com.takenokoshi.mekin.blockentity.machine.*;
import com.takenokoshi.mekin.blockentity.suprememachine.*;
import com.takenokoshi.mekin.gui.machine.*;
import com.takenokoshi.mekut.gui.machine.*;

import mekanism.client.ClientRegistrationUtil;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import mekanism.common.tile.base.TileEntityMekanism;
import net.minecraft.client.gui.screens.MenuScreens.ScreenConstructor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

public class MekInScreens {
    public static void registerScreens(RegisterMenuScreensEvent event) {

        registerMachineGui(event, MekInMachines.ANTINEUTRONIC_EXISTENCE_TRANSMUTATOR, GuiAET<BlockEntityAET>::new);
        registerMachineGui(event, MekInMachines.FLUX_CONDENSER, GuiFluxCondenser::new);
        registerMachineGui(event, MekInMachines.CHEMICAL_EXTRACTOR, GuiChemicalExtractor<BEChemicalExtractor>::new);
        registerMachineGui(event, MekInMachines.CHEMICAL_LEACHING_CHAMBER,
                GuiChemicalLeacher<BEChemicalLeachingChamber>::new);
        registerMachineGui(event, MekInMachines.CHEMICAL_REFINER, GuiChemicalCutter<BEChemicalRefiner>::new);
        registerMachineGui(event, MekInMachines.TEPS, GuiCompactAPT<BEAbstractTEPS>::new);
        registerMachineGui(event, MekInMachines.COMPACT_ANTIMATTER_PROTOMOLECULAR_TRANSMUTATOR,
                GuiCompactAPT<BECompactAPT>::new);

        registerMachineGui(event, MekInMachines.ABSOLUTE_OVERCLOCKED_CHEMICAL_CUTTER,
                GuiChemicalCutter<BEAbsoluteChemicalCutter>::new);
        registerMachineGui(event, MekInMachines.ABSOLUTE_OVERCLOCKED_CHEMICAL_INJECTION_CHAMBER,
                GuiChemicalCutter<BEAbsoluteChemicalInjectionChamber>::new);
        registerMachineGui(event, MekInMachines.ABSOLUTE_OVERCLOCKED_CHEMICAL_OXIDIZER,
                GuiAbsoluteChemicalOxidizer<BEAbsoluteChemicalOxidizer>::new);
        registerMachineGui(event, MekInMachines.ABSOLUTE_OVERCLOCKED_CRUSHER,
                GuiItemStackToItemStackMachine<BEAbsoluteCrusher>::new);
        registerMachineGui(event, MekInMachines.ABSOLUTE_OVERCLOCKED_ELECTROLYTIC_SEPARATOR,
                GuiAbsoluteElectrolyticSeparator<BEAbsoluteElectrolyticSeparator>::new);
        registerMachineGui(event, MekInMachines.ABSOLUTE_OVERCLOCKED_ENERIZED_SMELTER,
                GuiTweakedEnergizedSmelter<BEAbsoluteEnergizedSmelter>::new);
        registerMachineGui(event, MekInMachines.ABSOLUTE_OVERCLOCKED_ENRICHMENT_CHAMBER,
                GuiItemStackToItemStackMachine<BEAbsoluteEnrichmentChamber>::new);
        registerMachineGui(event, MekInMachines.ABSOLUTE_OVERCLOCKED_ICE_MAKER,
                GuiFluidToObjectMachine<BEAbsoluteIceMaker>::new);
        registerMachineGui(event, MekInMachines.ABSOLUTE_OVERCLOCKED_METALLURGIC_INFUSER,
                GuiAbsoluteMetallurgicInfuser<BEAbsoluteMetallurgicInfuser>::new);
        registerMachineGui(event, MekInMachines.ABSOLUTE_OVERCLOCKED_OSMIUM_COMPRESSOR,
                GuiChemicalCutter<BEAbsoluteOsmiumCompressor>::new);
        registerMachineGui(event, MekInMachines.ABSOLUTE_OVERCLOCKED_PAINTING_MACHINE,
                GuiAbsolutePaintingMachine<BEAbsolutePaintingMachine>::new);
        registerMachineGui(event, MekInMachines.ABSOLUTE_OVERCLOCKED_PURIFICATION_CHAMBER,
                GuiChemicalCutter<BEAbsolutePurificationChamber>::new);
        registerMachineGui(event, MekInMachines.ABSOLUTE_OVERCLOCKED_ROTARY_CONDENSENTRATOR,
                GuiAbsoluteRotaryCondensentrator<BEAbsoluteRotaryCondensentrator>::new);
        registerMachineGui(event, MekInMachines.ABSOLUTE_OVERCLOCKED_SMALL_DIGITAL_ASSEMBLER,
                GuiSmallDigitalAssembler<BEAbsoluteSmallDigitalAssembler>::new);
        registerMachineGui(event, MekInMachines.ABSOLUTE_OVERCLOCKED_SMALL_DIGITAL_REACTION_CAHMBER,
                GuiSmallDigitalReactionChamber<BEAbsoluteSmallDigitalReactionChamber>::new);
        registerMachineGui(event, MekInMachines.ABSOLUTE_OVERCLOCKED_THERMAL_EVAPORATION_PLANT,
                GuiCompactThermalEvaporationPlant<BEAbsoluteTEP>::new);

        registerMachineGui(event, MekInMachines.SUPREME_QUANTUM_CHEMICAL_CRYSTALLIZER,
                GuiSupremeChemicalCrystallizer<BESupremeChemicalCrystallizer>::new);
        registerMachineGui(event, MekInMachines.SUPREME_QUANTUM_CHEMICAL_DISSOLUTION_CHAMBER,
                GuiAET<BESupremeChemicalDissolutionChamber>::new);
        registerMachineGui(event, MekInMachines.SUPREME_QUANTUM_CHEMICAL_WASHER,
                GuiSupremeChemicalWasher<BESupremeChemicalWasher>::new);
        registerMachineGui(event, MekInMachines.SUPREME_QUANTUM_LAZER_COMPRESS_NUCLEO_SYNTHESIZER,
                GuiBiChemicalToChemicalMachine<BESupremeLazerCopmpressNucleoSynthesizer>::new);
        registerMachineGui(event, MekInMachines.SUPREME_QUANTUM_SUPERCRITICAL_PHASE_SHIFTER,
                GuiCompactSPS<BESupremeSPS>::new);
    }

    public static <BE extends TileEntityMekanism, CONTAINER extends MekanismTileContainer<BE>, GUI extends Screen & MenuAccess<CONTAINER>> void registerMachineGui(
            RegisterMenuScreensEvent event, MachineRegistryObject<BE, ?, CONTAINER, ?> registryObject,
            ScreenConstructor<CONTAINER, GUI> constructor) {
        ClientRegistrationUtil.registerScreen(event, registryObject.getContainer(), constructor);
    }
}
