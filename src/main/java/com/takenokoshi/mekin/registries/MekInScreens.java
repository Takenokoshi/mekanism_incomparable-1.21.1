package com.takenokoshi.mekin.registries;

import com.takenokoshi.mekaddonlib.registration.MachineRegistryObject;
import com.takenokoshi.mekin.blockentity.abs.BEAbstractTEPS;
import com.takenokoshi.mekin.blockentity.absolutemachine.*;
import com.takenokoshi.mekin.blockentity.infinitemachine.*;
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
        registerMachineGui(event, MekInMachines.CHEMICAL_EXTRACTOR, GuiChemicalExtractor<BEChemicalExtractor>::new);
        registerMachineGui(event, MekInMachines.CHEMICAL_LEACHING_CHAMBER,
                GuiChemicalLeachingChamber<BEChemicalLeachingChamber>::new);
        registerMachineGui(event, MekInMachines.CHEMICAL_REFINER, GuiChemicalCutter<BEChemicalRefiner>::new);
        registerMachineGui(event, MekInMachines.COMPACT_ANTIMATTER_PROTOMOLECULAR_TRANSMUTATOR,
                GuiCompactAPT<BECompactAPT>::new);
        registerMachineGui(event, MekInMachines.COMPACT_NAQUADAH_REACTOR,
                GuiCompactFusionReactor<BECompactNaquadahReactor>::new);
        registerMachineGui(event, MekInMachines.FLUX_CONDENSER, GuiFluxCondenser::new);
        registerMachineGui(event, MekInMachines.LIGHTNING_FABRICATOR,
                GuiLightningFabricator<BELightningFabricator>::new);
        registerMachineGui(event, MekInMachines.LIGHTNING_MATERIALIZER, GuiLightningMaterializer::new);
        registerMachineGui(event, MekInMachines.LIGHTNING_RECOLLECTOR, GuiLightningRecollector::new);
        registerMachineGui(event, MekInMachines.LIGHTNING_TRANSFORMER,
                GuiLightningTransformer<BELightningTransformer>::new);
        registerMachineGui(event, MekInMachines.METEOR_COLLECTOR, GuiMeteorCollector::new);
        registerMachineGui(event, MekInMachines.ADVANCED_METEOR_COLLECTOR, GuiMeteorCollector::new);
        registerMachineGui(event, MekInMachines.TEPS, GuiCompactAPT<BEAbstractTEPS>::new);

        registerMachineGui(event, MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_BOILER,
                GuiCompactBoiler<BEAbsoluteBoiler>::new);
        registerMachineGui(event, MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_CHEMICAL_CUTTER,
                GuiChemicalCutter<BEAbsoluteChemicalCutter>::new);
        registerMachineGui(event, MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_CHEMICAL_INFUSER,
                GuiAbsoluteChemicalInfuser<BEAbsoluteChemicalInfuser>::new);
        registerMachineGui(event, MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_CHEMICAL_INJECTION_CHAMBER,
                GuiChemicalCutter<BEAbsoluteChemicalInjectionChamber>::new);
        registerMachineGui(event, MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_CHEMICAL_OXIDIZER,
                GuiAbsoluteChemicalOxidizer<BEAbsoluteChemicalOxidizer>::new);
        registerMachineGui(event, MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_CRUSHER,
                GuiItemStackToItemStackMachine<BEAbsoluteCrusher>::new);
        registerMachineGui(event, MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_ELECTROLYTIC_SEPARATOR,
                GuiAbsoluteElectrolyticSeparator<BEAbsoluteElectrolyticSeparator>::new);
        registerMachineGui(event, MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_ENERIZED_SMELTER,
                GuiTweakedEnergizedSmelter<BEAbsoluteEnergizedSmelter>::new);
        registerMachineGui(event, MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_ENRICHMENT_CHAMBER,
                GuiItemStackToItemStackMachine<BEAbsoluteEnrichmentChamber>::new);
        registerMachineGui(event, MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_FISSION_REACTOR,
                GuiCompactFissionReactor<BEAbsoluteFissionReactor>::new);
        registerMachineGui(event, MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_ICE_MAKER,
                GuiFluidToObjectMachine<BEAbsoluteIceMaker>::new);
        registerMachineGui(event, MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_INDUSTRIAL_TURBINE,
                GuiCompactIndustrialTurbine<BEAbsoluteIndustrialTurbine>::new);
        registerMachineGui(event, MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_METALLURGIC_INFUSER,
                GuiAbsoluteMetallurgicInfuser<BEAbsoluteMetallurgicInfuser>::new);
        registerMachineGui(event, MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_OSMIUM_COMPRESSOR,
                GuiChemicalCutter<BEAbsoluteOsmiumCompressor>::new);
        registerMachineGui(event, MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_PAINTING_MACHINE,
                GuiAbsolutePaintingMachine<BEAbsolutePaintingMachine>::new);
        registerMachineGui(event, MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_PRECISION_SAWMILL,
                GuiItemStackToItemStackMachine<BEAbsolutePrecisionSawmill>::new);
        registerMachineGui(event, MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_PURIFICATION_CHAMBER,
                GuiChemicalCutter<BEAbsolutePurificationChamber>::new);
        registerMachineGui(event, MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_RECYCLER,
                GuiItemStackToItemStackMachine<BEAbsoluteRecycler>::new);
        registerMachineGui(event, MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_ROTARY_CONDENSENTRATOR,
                GuiAbsoluteRotaryCondensentrator<BEAbsoluteRotaryCondensentrator>::new);
        registerMachineGui(event, MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_SMALL_DIGITAL_ASSEMBLER,
                GuiSmallDigitalAssembler<BEAbsoluteSmallDigitalAssembler>::new);
        registerMachineGui(event, MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_SMALL_DIGITAL_REACTION_CAHMBER,
                GuiSmallDigitalReactionChamber<BEAbsoluteSmallDigitalReactionChamber>::new);
        registerMachineGui(event, MekInAbsoluteMachines.ABSOLUTE_OVERCLOCKED_THERMAL_EVAPORATION_PLANT,
                GuiCompactThermalEvaporationPlant<BEAbsoluteTEP>::new);

        registerMachineGui(event, MekInSupremeMachines.SUPREME_QUANTUM_CHEMICAL_CRYSTALLIZER,
                GuiSupremeChemicalCrystallizer<BESupremeChemicalCrystallizer>::new);
        registerMachineGui(event, MekInSupremeMachines.SUPREME_QUANTUM_CHEMICAL_DISSOLUTION_CHAMBER,
                GuiAET<BESupremeChemicalDissolutionChamber>::new);
        registerMachineGui(event, MekInSupremeMachines.SUPREME_QUANTUM_CHEMICAL_WASHER,
                GuiSupremeChemicalWasher<BESupremeChemicalWasher>::new);
        registerMachineGui(event, MekInSupremeMachines.SUPREME_QUANTUM_LAZER_COMPRESS_NUCLEO_SYNTHESIZER,
                GuiBiChemicalToChemicalMachine<BESupremeLCNS>::new);
        registerMachineGui(event, MekInSupremeMachines.SUPREME_QUANTUM_SUPERCRITICAL_PHASE_SHIFTER,
                GuiCompactSPS<BESupremeSPS>::new);

        registerMachineGui(event, MekInInfiniteMachines.INFINITE_MULTIVERSAL_ANTIMATTER_PROTOMOLECULAR_TRANSMUTATOR,
                GuiCompactAPT<BEInfiniteAPT>::new);
        registerMachineGui(event, MekInInfiniteMachines.INFINITE_MULTIVERSAL_BOILER,
                GuiCompactBoiler<BEInfiniteBoiler>::new);
        registerMachineGui(event, MekInInfiniteMachines.INFINITE_MULTIVERSAL_CHEMICAL_CRYSTALLIZER,
                GuiSupremeChemicalCrystallizer<BEInfiniteChemicalCrystallizer>::new);
        registerMachineGui(event, MekInInfiniteMachines.INFINITE_MULTIVERSAL_CHEMICAL_CUTTER,
                GuiChemicalCutter<BEInfiniteChemicalCutter>::new);
        registerMachineGui(event, MekInInfiniteMachines.INFINITE_MULTIVERSAL_CHEMICAL_DISSOLUTION_CHAMBER,
                GuiAET<BEInfiniteChemicalDissolutionChamber>::new);
        registerMachineGui(event, MekInInfiniteMachines.INFINITE_MULTIVERSAL_CHEMICAL_EXTRACTOR,
                GuiChemicalExtractor<BEInfiniteChemicalExtractor>::new);
        registerMachineGui(event, MekInInfiniteMachines.INFINITE_MULTIVERSAL_CHEMICAL_INFUSER,
                GuiAbsoluteChemicalInfuser<BEInfiniteChemicalInfuser>::new);
        registerMachineGui(event, MekInInfiniteMachines.INFINITE_MULTIVERSAL_CHEMICAL_INJECTION_CHAMBER,
                GuiChemicalCutter<BEInfiniteChemicalInjectionChamber>::new);
        registerMachineGui(event, MekInInfiniteMachines.INFINITE_MULTIVERSAL_CHEMICAL_LEACHING_CHAMBER,
                GuiChemicalLeachingChamber<BEInfiniteChemicalLeachingChamber>::new);
        registerMachineGui(event, MekInInfiniteMachines.INFINITE_MULTIVERSAL_CHEMICAL_OXIDIZER,
                GuiAbsoluteChemicalOxidizer<BEInfiniteChemicalOxidizer>::new);
        registerMachineGui(event, MekInInfiniteMachines.INFINITE_MULTIVERSAL_CHEMICAL_REFINER,
                GuiChemicalCutter<BEInfiniteChemicalRefiner>::new);
        registerMachineGui(event, MekInInfiniteMachines.INFINITE_MULTIVERSAL_CHEMICAL_WASHER,
                GuiSupremeChemicalWasher<BEInfiniteChemicalWasher>::new);
        registerMachineGui(event, MekInInfiniteMachines.INFINITE_MULTIVERSAL_CRUSHER,
                GuiItemStackToItemStackMachine<BEInfiniteCrusher>::new);
        registerMachineGui(event, MekInInfiniteMachines.INFINITE_MULTIVERSAL_ELECTROLYTIC_SEPARATOR,
                GuiAbsoluteElectrolyticSeparator<BEInfiniteElectrolyticSeparator>::new);
        registerMachineGui(event, MekInInfiniteMachines.INFINITE_MULTIVERSAL_ENERGIZED_SMELTER,
                GuiTweakedEnergizedSmelter<BEInfiniteEnergizedSmelter>::new);
        registerMachineGui(event, MekInInfiniteMachines.INFINITE_MULTIVERSAL_ENRICHMENT_CHAMBER,
                GuiItemStackToItemStackMachine<BEInfiniteEnrichmentChamber>::new);
        registerMachineGui(event, MekInInfiniteMachines.INFINITE_MULTIVERSAL_FISSION_REACTOR,
                GuiCompactFissionReactor<BEInfiniteFissionReactor>::new);
        registerMachineGui(event, MekInInfiniteMachines.INFINITE_MULTIVERSAL_ICE_MAKER,
                GuiFluidToObjectMachine<BEInfiniteIceMaker>::new);
        registerMachineGui(event, MekInInfiniteMachines.INFINITE_MULTIVERSAL_INDUSTRIAL_TURBINE,
                GuiCompactIndustrialTurbine<BEInfiniteIndustrialTurbine>::new);
        registerMachineGui(event, MekInInfiniteMachines.INFINITE_MULTIVERSAL_LAZER_COMPRESS_NUCLEO_SYNTHESIZER,
                GuiBiChemicalToChemicalMachine<BEInfiniteLCNS>::new);
        registerMachineGui(event, MekInInfiniteMachines.INFINITE_MULTIVERSAL_LIGHTNING_FABRICATOR,
                GuiLightningFabricator<BEInfiniteLightningFabricator>::new);
        registerMachineGui(event, MekInInfiniteMachines.INFINITE_MULTIVERSAL_METALLURGIC_INFUSER,
                GuiAbsoluteMetallurgicInfuser<BEInfiniteMetallurgicInfuser>::new);
        registerMachineGui(event, MekInInfiniteMachines.INFINITE_MULTIVERSAL_OSMIUM_COMPRESSOR,
                GuiChemicalCutter<BEInfiniteOsmiumCompressor>::new);
        registerMachineGui(event, MekInInfiniteMachines.INFINITE_MULTIVERSAL_PAINTING_MACHINE,
                GuiAbsolutePaintingMachine<BEInfinitePaintingMachine>::new);
        registerMachineGui(event, MekInInfiniteMachines.INFINITE_MULTIVERSAL_PRECISION_SAWMILL,
                GuiItemStackToItemStackMachine<BEInfinitePrecisionSawmill>::new);
        registerMachineGui(event, MekInInfiniteMachines.INFINITE_MULTIVERSAL_PURIFICATION_CHAMBER,
                GuiChemicalCutter<BEInfinitePurificationChamber>::new);
        registerMachineGui(event, MekInInfiniteMachines.INFINITE_MULTIVERSAL_RECYCLER,
                GuiItemStackToItemStackMachine<BEInfiniteRecycler>::new);
        registerMachineGui(event, MekInInfiniteMachines.INFINITE_MULTIVERSAL_ROTARY_CONDENSENTRATOR,
                GuiAbsoluteRotaryCondensentrator<BEInfiniteRotaryCondensentrator>::new);
        registerMachineGui(event, MekInInfiniteMachines.INFINITE_MULTIVERSAL_SMALL_DIGITAL_ASSEMBLER,
                GuiSmallDigitalAssembler<BEInfiniteSmallDigitalAssembler>::new);
        registerMachineGui(event, MekInInfiniteMachines.INFINITE_MULTIVERSAL_SMALL_DIGITAL_REACTION_CHAMBER,
                GuiSmallDigitalReactionChamber<BEInfiniteSmallDigitalReactionChamber>::new);
        registerMachineGui(event, MekInInfiniteMachines.INFINITE_MULTIVERSAL_SUPERCRITICAL_PHASE_SHIFTER,
                GuiCompactSPS<BEInfiniteSPS>::new);
        registerMachineGui(event, MekInInfiniteMachines.INFINITE_MULTIVERSAL_THERMAL_EVAPORATION_PLANT,
                GuiCompactThermalEvaporationPlant<BEInfiniteTEP>::new);
    }

    public static <BE extends TileEntityMekanism, CONTAINER extends MekanismTileContainer<BE>, GUI extends Screen & MenuAccess<CONTAINER>> void registerMachineGui(
            RegisterMenuScreensEvent event, MachineRegistryObject<BE, ?, CONTAINER, ?> registryObject,
            ScreenConstructor<CONTAINER, GUI> constructor) {
        ClientRegistrationUtil.registerScreen(event, registryObject.getContainer(), constructor);
    }
}
