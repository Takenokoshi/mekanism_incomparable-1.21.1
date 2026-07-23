package com.takenokoshi.mekin.recipe.building;

import com.fxd927.mekanismelements.common.registries.MSGases;
import com.takenokoshi.mekin.core.MekInConstants;
import com.takenokoshi.mekin.registries.MekInChemicals;
import com.takenokoshi.mekin.registries.MekInItems;
import com.takenokoshi.mekut.recipe.builder.ItemStackListFluidChemicalToItemRecipeBuilder;
import com.takenokoshi.mekut.recipe.builder.MekUtItemChemicalToItemRecipeBuilder;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import mekanism.common.registries.MekanismChemicals;
import net.minecraft.data.recipes.RecipeOutput;
import net.neoforged.neoforge.common.Tags;
import net.pedroksl.advanced_ae.common.definitions.AAEFluids;

public class ProcessorRecipes {
    public static void buildRecipes(RecipeOutput output) {
        MekUtItemChemicalToItemRecipeBuilder
                .chemicalCut(
                        IngredientCreatorAccess.item().from(MekInItems.SEMICONDUCTIVE_ALLOY_INGOT, 4),
                        IngredientCreatorAccess.chemicalStack().from(MSGases.COMPRESSED_AIR.asStack(1)),
                        MekInItems.SEMICONDUCTIVE_ALLOY_CIRCUIT_BOARD.asStack(4),
                        true)
                .build(output, MekInConstants.rl("printed_processor/semiconductive_alloy_circuit_board/chemical_cut"));
        MekUtItemChemicalToItemRecipeBuilder
                .chemicalCut(
                        IngredientCreatorAccess.item().from(MekInItems.ASTRAL_GLOWSTONE_INGOT, 1),
                        IngredientCreatorAccess.chemicalStack().from(MSGases.NITRIC_ACID.asStack(1)),
                        MekInItems.PRINTED_PHOTON_PROCESSOR.asStack(1),
                        true)
                .build(output, MekInConstants.rl("printed_processor/photon/chemical_cut"));
        MekUtItemChemicalToItemRecipeBuilder
                .chemicalCut(
                        IngredientCreatorAccess.item().from(MekInItems.NEUTRONIUM_INGOT, 1),
                        IngredientCreatorAccess.chemicalStack().from(MSGases.NITRIC_ACID.asStack(1)),
                        MekInItems.PRINTED_GRAVITON_PROCESSOR.asStack(1),
                        true)
                .build(output, MekInConstants.rl("printed_processor/graviton/chemical_cut"));
        MekUtItemChemicalToItemRecipeBuilder
                .chemicalCut(
                        IngredientCreatorAccess.item().from(MekInItems.METASTABLE_ALLOY_INGOT, 1),
                        IngredientCreatorAccess.chemicalStack().from(MSGases.NITRIC_ACID.asStack(1)),
                        MekInItems.PRINTED_DIMENTIONAL_PROCESSOR.asStack(1),
                        true)
                .build(output, MekInConstants.rl("printed_processor/dimentional/chemical_cut"));
        MekUtItemChemicalToItemRecipeBuilder
                .chemicalCut(
                        IngredientCreatorAccess.item().from(MekInItems.ANTIMATTER_SYNCHRONIZED_AMETHYST_SHARD, 1),
                        IngredientCreatorAccess.chemicalStack().from(MSGases.NITRIC_ACID.asStack(1)),
                        MekInItems.PRINTED_OBSERVATION_PROCESSOR.asStack(1),
                        true)
                .build(output, MekInConstants.rl("printed_processor/observation/chemical_cut"));
        MekUtItemChemicalToItemRecipeBuilder
                .chemicalCut(
                        IngredientCreatorAccess.item().from(MekInItems.AMETHYST_SYNCHRONIZED_METASTABLE_ALLOY_INGOT, 1),
                        IngredientCreatorAccess.chemicalStack().from(MSGases.NITRIC_ACID.asStack(1)),
                        MekInItems.PRINTED_TRANSMISSION_PROCESSOR.asStack(1),
                        true)
                .build(output, MekInConstants.rl("printed_processor/transmission/chemical_cut"));
        MekUtItemChemicalToItemRecipeBuilder
                .chemicalCut(
                        IngredientCreatorAccess.item().from(MekInItems.NATURAL_ENVIRONMENT_SYNCHRONIZED_NEUTRONIUM_INGOT, 1),
                        IngredientCreatorAccess.chemicalStack().from(MSGases.NITRIC_ACID.asStack(1)),
                        MekInItems.PRINTED_RANDOM_PROCESSOR.asStack(1),
                        true)
                .build(output, MekInConstants.rl("printed_processor/random/chemical_cut"));
        MekUtItemChemicalToItemRecipeBuilder
                .chemicalCut(
                        IngredientCreatorAccess.item().from(MekInItems.CESIUM_133_SYNCHRONIZED_CERTUS_QUARTZ_CRYSTAL, 1),
                        IngredientCreatorAccess.chemicalStack().from(MSGases.NITRIC_ACID.asStack(1)),
                        MekInItems.PRINTED_CLOCK_PROCESSOR.asStack(1),
                        true)
                .build(output, MekInConstants.rl("printed_processor/clock/chemical_cut"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(MekInItems.PHOTON_PROCESSOR.asStack(4))
                .addItemInput(MekInItems.PRINTED_PHOTON_PROCESSOR, 4)
                .addItemInput(MekInItems.SEMICONDUCTIVE_ALLOY_CIRCUIT_BOARD, 4)
                .setFluidInput(Tags.Fluids.WATER, 100)
                .setChemicalInput(MekanismChemicals.REDSTONE.asStack(80))
                .build(output, MekInConstants.rl("processor/photon"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(MekInItems.GRAVITON_PROCESSOR.asStack(1))
                .addItemInput(MekInItems.PRINTED_GRAVITON_PROCESSOR, 1)
                .addItemInput(MekInItems.SEMICONDUCTIVE_ALLOY_CIRCUIT_BOARD, 1)
                .setFluidInput(AAEFluids.QUANTUM_INFUSION.stack(100))
                .setChemicalInput(MekInChemicals.SCARLET_SILVER.asStack(160))
                .build(output, MekInConstants.rl("processor/graviton"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(MekInItems.DIMENTIONAL_PROCESSOR.asStack(1))
                .addItemInput(MekInItems.PRINTED_DIMENTIONAL_PROCESSOR, 1)
                .addItemInput(MekInItems.SEMICONDUCTIVE_ALLOY_CIRCUIT_BOARD, 1)
                .setFluidInput(AAEFluids.QUANTUM_INFUSION.stack(100))
                .setChemicalInput(MekInChemicals.SCARLET_SILVER.asStack(160))
                .build(output, MekInConstants.rl("processor/dimentional"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(MekInItems.OBSERVATION_PROCESSOR.asStack(1))
                .addItemInput(MekInItems.PRINTED_OBSERVATION_PROCESSOR, 1)
                .addItemInput(MekInItems.SEMICONDUCTIVE_ALLOY_CIRCUIT_BOARD, 2)
                .setFluidInput(AAEFluids.QUANTUM_INFUSION.stack(1000))
                .setChemicalInput(MekInChemicals.SCARLET_SILVER.asStack(5120))
                .build(output, MekInConstants.rl("processor/observation"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(MekInItems.TRANSMISSION_PROCESSOR.asStack(1))
                .addItemInput(MekInItems.PRINTED_TRANSMISSION_PROCESSOR, 1)
                .addItemInput(MekInItems.SEMICONDUCTIVE_ALLOY_CIRCUIT_BOARD, 2)
                .setFluidInput(AAEFluids.QUANTUM_INFUSION.stack(1000))
                .setChemicalInput(MekInChemicals.SCARLET_SILVER.asStack(5120))
                .build(output, MekInConstants.rl("processor/transmission"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(MekInItems.RANDOM_PROCESSOR.asStack(1))
                .addItemInput(MekInItems.PRINTED_RANDOM_PROCESSOR, 1)
                .addItemInput(MekInItems.SEMICONDUCTIVE_ALLOY_CIRCUIT_BOARD, 2)
                .setFluidInput(AAEFluids.QUANTUM_INFUSION.stack(1000))
                .setChemicalInput(MekInChemicals.SCARLET_SILVER.asStack(5120))
                .build(output, MekInConstants.rl("processor/random"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(MekInItems.CLOCK_PROCESSOR.asStack(1))
                .addItemInput(MekInItems.PRINTED_CLOCK_PROCESSOR, 1)
                .addItemInput(MekInItems.SEMICONDUCTIVE_ALLOY_CIRCUIT_BOARD, 2)
                .setFluidInput(AAEFluids.QUANTUM_INFUSION.stack(1000))
                .setChemicalInput(MekInChemicals.SCARLET_SILVER.asStack(5120))
                .build(output, MekInConstants.rl("processor/clock"));
    }
}
