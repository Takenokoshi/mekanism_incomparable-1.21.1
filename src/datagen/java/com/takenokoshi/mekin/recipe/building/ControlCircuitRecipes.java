package com.takenokoshi.mekin.recipe.building;

import java.util.function.Function;

import com.glodblock.github.appflux.common.AFSingletons;
import com.glodblock.github.extendedae.common.EAESingletons;
import com.glodblock.github.extendedae.recipe.CrystalAssemblerRecipeBuilder;
import com.jerry.mekextras.common.registries.ExtraItems;
import com.takenokoshi.mekin.core.MekInConstants;
import com.takenokoshi.mekin.registries.MekInBlocks;
import com.takenokoshi.mekin.registries.MekInChemicals;
import com.takenokoshi.mekin.registries.MekInFluids;
import com.takenokoshi.mekin.registries.MekInItems;
import com.takenokoshi.mekut.recipe.builder.ItemStackListFluidChemicalToItemRecipeBuilder;
import com.takenokoshi.mekut.registries.MekUtChemicals;
import com.takenokoshi.mekut.registries.MekUtItems;

import appeng.core.definitions.AEItems;
import appeng.recipes.handlers.InscriberProcessType;
import appeng.recipes.handlers.InscriberRecipeBuilder;
import fr.iglee42.evolvedmekanism.registries.EMItems;
import gripe._90.megacells.definition.MEGAItems;
import mekanism.common.registries.MekanismChemicals;
import mekanism.common.registries.MekanismItems;
import mekanism.common.resource.PrimaryResource;
import mekanism.common.resource.ResourceType;
import mekanism.common.tags.MekanismTags;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;
import net.pedroksl.advanced_ae.common.definitions.AAEItems;

public class ControlCircuitRecipes {
    public static void buildRecipes(RecipeOutput output,
            Function<ItemLike, Criterion<InventoryChangeTrigger.TriggerInstance>> has) {
        InscriberRecipeBuilder.inscribe(Tags.Items.DUSTS_REDSTONE, MekInItems.CONTROL_CIRCUIT_COMPONENT, 1)
                .setTop(Ingredient
                        .of(MekanismTags.Items.PROCESSED_RESOURCES.get(ResourceType.INGOT, PrimaryResource.OSMIUM)))
                .setBottom(Ingredient.of(AEItems.SILICON))
                .setMode(InscriberProcessType.PRESS)
                .save(output, MekInConstants.rl("control_circuit/component_0"));
        CrystalAssemblerRecipeBuilder.assemble(MekInItems.CONTROL_CIRCUIT_COMPONENT.asStack(4))
                .input(MekanismTags.Items.PROCESSED_RESOURCES.get(ResourceType.INGOT, PrimaryResource.OSMIUM), 4)
                .input(AEItems.SILICON, 4)
                .input(Tags.Items.DUSTS_REDSTONE, 4)
                .save(output, MekInConstants.rl("control_circuit/component_1"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(MekInItems.CONTROL_CIRCUIT_COMPONENT.asStack(64))
                .addItemInput(MekanismTags.Items.PROCESSED_RESOURCES.get(ResourceType.INGOT, PrimaryResource.OSMIUM),
                        64)
                .addItemInput(AEItems.SILICON, 64)
                .setFluidInput(Tags.Fluids.WATER, 100)
                .setChemicalInput(MekanismChemicals.REDSTONE.asStack(640))
                .build(output, MekInConstants.rl("control_circuit/component_2"));
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, MekInItems.CONTROL_CIRCUIT_CORE)
                .define('c', MekInItems.CONTROL_CIRCUIT_COMPONENT)
                .define('p', AEItems.CALCULATION_PROCESSOR)
                .define('r', Tags.Items.DUSTS_REDSTONE)
                .pattern("rcr")
                .pattern("cpc")
                .pattern("rcr")
                .unlockedBy("unlock", has.apply(MekInItems.CONTROL_CIRCUIT_COMPONENT))
                .save(output, MekInConstants.rl("control_circuit/core_0"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(MekInItems.CONTROL_CIRCUIT_CORE.asStack(4))
                .addItemInput(MekInItems.CONTROL_CIRCUIT_COMPONENT, 16)
                .addItemInput(AEItems.CALCULATION_PROCESSOR, 4)
                .setFluidInput(Tags.Fluids.WATER, 100)
                .setChemicalInput(MekanismChemicals.REDSTONE.asStack(160))
                .build(output, MekInConstants.rl("control_circuit/core_1"));
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, MekanismItems.BASIC_CONTROL_CIRCUIT)
                .define('c', MekInItems.CONTROL_CIRCUIT_COMPONENT)
                .define('b', MekInItems.CONTROL_CIRCUIT_CORE)
                .define('p', AEItems.LOGIC_PROCESSOR)
                .define('a', Tags.Items.INGOTS_IRON)
                .pattern("aca")
                .pattern("pbp")
                .pattern("aca")
                .unlockedBy("unlock", has.apply(MekInItems.CONTROL_CIRCUIT_CORE))
                .save(output, MekInConstants.rl("control_circuit/basic_0"));
        CrystalAssemblerRecipeBuilder
                .assemble(MekanismItems.BASIC_CONTROL_CIRCUIT, 4)
                .input(MekInItems.CONTROL_CIRCUIT_COMPONENT, 8)
                .input(MekInItems.CONTROL_CIRCUIT_CORE, 4)
                .input(AEItems.LOGIC_PROCESSOR, 8)
                .input(Tags.Items.INGOTS_IRON, 16)
                .save(output, MekInConstants.rl("control_circuit/basic_1"));
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, MekanismItems.ADVANCED_CONTROL_CIRCUIT)
                .define('c', MekInItems.CONTROL_CIRCUIT_COMPONENT)
                .define('b', MekanismItems.BASIC_CONTROL_CIRCUIT)
                .define('p', AFSingletons.ENERGY_PROCESSOR)
                .define('a', MekanismItems.INFUSED_ALLOY)
                .pattern("aca")
                .pattern("pbp")
                .pattern("aca")
                .unlockedBy("unlock", has.apply(MekanismItems.BASIC_CONTROL_CIRCUIT))
                .save(output, MekInConstants.rl("control_circuit/advanced_0"));
        CrystalAssemblerRecipeBuilder
                .assemble(MekanismItems.ADVANCED_CONTROL_CIRCUIT, 4)
                .input(MekInItems.CONTROL_CIRCUIT_COMPONENT, 8)
                .input(MekanismItems.BASIC_CONTROL_CIRCUIT, 4)
                .input(AFSingletons.ENERGY_PROCESSOR, 8)
                .input(MekanismItems.INFUSED_ALLOY, 16)
                .save(output, MekInConstants.rl("control_circuit/advanced_1"));
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, MekanismItems.ELITE_CONTROL_CIRCUIT)
                .define('c', MekInItems.CONTROL_CIRCUIT_COMPONENT)
                .define('b', MekanismItems.ADVANCED_CONTROL_CIRCUIT)
                .define('p', AEItems.ENGINEERING_PROCESSOR)
                .define('a', MekanismItems.REINFORCED_ALLOY)
                .pattern("aca")
                .pattern("pbp")
                .pattern("aca")
                .unlockedBy("unlock", has.apply(MekanismItems.ADVANCED_CONTROL_CIRCUIT))
                .save(output, MekInConstants.rl("control_circuit/elite_0"));
        CrystalAssemblerRecipeBuilder
                .assemble(MekanismItems.ELITE_CONTROL_CIRCUIT, 4)
                .input(MekInItems.CONTROL_CIRCUIT_COMPONENT, 8)
                .input(MekanismItems.ADVANCED_CONTROL_CIRCUIT, 4)
                .input(AEItems.ENGINEERING_PROCESSOR, 8)
                .input(MekanismItems.REINFORCED_ALLOY, 16)
                .save(output, MekInConstants.rl("control_circuit/elite_1"));
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, MekanismItems.ULTIMATE_CONTROL_CIRCUIT)
                .define('c', MekInItems.CONTROL_CIRCUIT_COMPONENT)
                .define('b', MekanismItems.ELITE_CONTROL_CIRCUIT)
                .define('p', MEGAItems.ACCUMULATION_PROCESSOR)
                .define('a', MekanismItems.ATOMIC_ALLOY)
                .pattern("aca")
                .pattern("pbp")
                .pattern("aca")
                .unlockedBy("unlock", has.apply(MekanismItems.ELITE_CONTROL_CIRCUIT))
                .save(output, MekInConstants.rl("control_circuit/ultimate_0"));
        CrystalAssemblerRecipeBuilder
                .assemble(MekanismItems.ULTIMATE_CONTROL_CIRCUIT, 4)
                .input(MekInItems.CONTROL_CIRCUIT_COMPONENT, 8)
                .input(MekanismItems.ELITE_CONTROL_CIRCUIT, 4)
                .input(MEGAItems.ACCUMULATION_PROCESSOR, 8)
                .input(MekanismItems.ATOMIC_ALLOY, 16)
                .save(output, MekInConstants.rl("control_circuit/ultimate_1"));
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, MekUtItems.DIGITAL_CONTROL_CIRCUIT)
                .define('c', MekInItems.CONTROL_CIRCUIT_COMPONENT)
                .define('b', MekanismItems.BASIC_CONTROL_CIRCUIT)
                .define('p', AEItems.CALCULATION_PROCESSOR)
                .define('a', MekUtItems.ELASTIC_ALLOY)
                .pattern("aca")
                .pattern("pbp")
                .pattern("aca")
                .unlockedBy("unlock", has.apply(MekanismItems.BASIC_CONTROL_CIRCUIT))
                .save(output, MekInConstants.rl("control_circuit/digital_0"));
        CrystalAssemblerRecipeBuilder
                .assemble(MekUtItems.DIGITAL_CONTROL_CIRCUIT, 4)
                .input(MekInItems.CONTROL_CIRCUIT_COMPONENT, 8)
                .input(MekanismItems.BASIC_CONTROL_CIRCUIT, 4)
                .input(AEItems.CALCULATION_PROCESSOR, 8)
                .input(MekUtItems.ELASTIC_ALLOY, 16)
                .save(output, MekInConstants.rl("control_circuit/digital_1"));
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, MekUtItems.STANDARD_CONTROL_CIRCUIT)
                .define('c', MekInItems.CONTROL_CIRCUIT_COMPONENT)
                .define('b', MekUtItems.DIGITAL_CONTROL_CIRCUIT)
                .define('p', MEGAItems.ACCUMULATION_PROCESSOR)
                .define('a', MekUtItems.CONVERGENT_ALLOY)
                .pattern("aca")
                .pattern("pbp")
                .pattern("aca")
                .unlockedBy("unlock", has.apply(MekUtItems.DIGITAL_CONTROL_CIRCUIT))
                .save(output, MekInConstants.rl("control_circuit/standard_0"));
        CrystalAssemblerRecipeBuilder
                .assemble(MekUtItems.STANDARD_CONTROL_CIRCUIT, 4)
                .input(MekInItems.CONTROL_CIRCUIT_COMPONENT, 8)
                .input(MekUtItems.DIGITAL_CONTROL_CIRCUIT, 4)
                .input(MEGAItems.ACCUMULATION_PROCESSOR, 8)
                .input(MekUtItems.CONVERGENT_ALLOY, 16)
                .save(output, MekInConstants.rl("control_circuit/standard_1"));
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, MekUtItems.AUGMENT_CONTROL_CIRCUIT)
                .define('c', MekInItems.CONTROL_CIRCUIT_COMPONENT)
                .define('b', MekUtItems.STANDARD_CONTROL_CIRCUIT)
                .define('p', EAESingletons.CONCURRENT_PROCESSOR)
                .define('a', MekUtItems.COMPISITE_ALLOY)
                .pattern("aca")
                .pattern("pbp")
                .pattern("aca")
                .unlockedBy("unlock", has.apply(MekUtItems.STANDARD_CONTROL_CIRCUIT))
                .save(output, MekInConstants.rl("control_circuit/augment_0"));
        CrystalAssemblerRecipeBuilder
                .assemble(MekUtItems.AUGMENT_CONTROL_CIRCUIT, 4)
                .input(MekInItems.CONTROL_CIRCUIT_COMPONENT, 8)
                .input(MekUtItems.STANDARD_CONTROL_CIRCUIT, 4)
                .input(EAESingletons.CONCURRENT_PROCESSOR, 8)
                .input(MekUtItems.COMPISITE_ALLOY, 16)
                .save(output, MekInConstants.rl("control_circuit/augment_1"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(ExtraItems.ABSOLUTE_CONTROL_CIRCUIT.asStack(1))
                .addItemInput(MekanismItems.ULTIMATE_CONTROL_CIRCUIT, 1)
                .addItemInput(MekUtItems.AUGMENT_CONTROL_CIRCUIT, 1)
                .addItemInput(MekInBlocks.BASIC_CONTROL_MATRIX, 4)
                .addItemInput(MekInItems.PHOTON_PROCESSOR, 4)
                .addItemInput(ExtraItems.RADIANCE_ALLOY, 4)
                .setFluidInput(Tags.Fluids.WATER, 1000)
                .setChemicalInput(MekanismChemicals.GOLD.asStack(320))
                .build(output, MekInConstants.rl("control_circuit/absolute_0"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(EMItems.OVERCLOCKED_CONTROL_CIRCUIT.asStack(1))
                .addItemInput(MekanismItems.ULTIMATE_CONTROL_CIRCUIT, 1)
                .addItemInput(MekUtItems.AUGMENT_CONTROL_CIRCUIT, 1)
                .addItemInput(MekInBlocks.BASIC_CONTROL_MATRIX, 4)
                .addItemInput(MekInItems.PHOTON_PROCESSOR, 4)
                .addItemInput(EMItems.HYPERCHARGED_ALLOY, 4)
                .setFluidInput(Tags.Fluids.WATER, 1000)
                .setChemicalInput(MekanismChemicals.GOLD.asStack(320))
                .build(output, MekInConstants.rl("control_circuit/overclocked_0"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(ExtraItems.SUPREME_CONTROL_CIRCUIT.asStack(1))
                .addItemInput(ExtraItems.ABSOLUTE_CONTROL_CIRCUIT, 1)
                .addItemInput(MekInBlocks.ADVANCED_CONTROL_MATRIX, 2)
                .addItemInput(MekInBlocks.DIGITAL_CONTROL_MATRIX, 2)
                .addItemInput(AAEItems.QUANTUM_PROCESSOR, 4)
                .addItemInput(ExtraItems.THERMONUCLEAR_ALLOY, 4)
                .setFluidInput(Tags.Fluids.LAVA, 1000)
                .setChemicalInput(MekUtChemicals.FLUIX.asStack(640))
                .build(output, MekInConstants.rl("control_circuit/supreme_0"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(EMItems.QUANTUM_CONTROL_CIRCUIT.asStack(1))
                .addItemInput(EMItems.OVERCLOCKED_CONTROL_CIRCUIT, 1)
                .addItemInput(MekInBlocks.ADVANCED_CONTROL_MATRIX, 2)
                .addItemInput(MekInBlocks.DIGITAL_CONTROL_MATRIX, 2)
                .addItemInput(AAEItems.QUANTUM_PROCESSOR, 4)
                .addItemInput(EMItems.SUBATOMIC_ALLOY, 4)
                .setFluidInput(Tags.Fluids.LAVA, 1000)
                .setChemicalInput(MekUtChemicals.FLUIX.asStack(640))
                .build(output, MekInConstants.rl("control_circuit/quantum_0"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(ExtraItems.COSMIC_CONTROL_CIRCUIT.asStack(1))
                .addItemInput(ExtraItems.SUPREME_CONTROL_CIRCUIT, 1)
                .addItemInput(MekInBlocks.ELITE_CONTROL_MATRIX, 2)
                .addItemInput(MekInBlocks.STANDARD_CONTROL_MATRIX, 2)
                .addItemInput(MekInItems.GRAVITON_PROCESSOR, 4)
                .addItemInput(ExtraItems.SHINING_ALLOY, 4)
                .setFluidInput(Tags.Fluids.LAVA, 1000)
                .setChemicalInput(MekanismChemicals.POLONIUM.asStack(500))
                .build(output, MekInConstants.rl("control_circuit/cosmic_0"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(EMItems.DENSE_CONTROL_CIRCUIT.asStack(1))
                .addItemInput(EMItems.QUANTUM_CONTROL_CIRCUIT, 1)
                .addItemInput(MekInBlocks.ELITE_CONTROL_MATRIX, 2)
                .addItemInput(MekInBlocks.STANDARD_CONTROL_MATRIX, 2)
                .addItemInput(MekInItems.GRAVITON_PROCESSOR, 4)
                .addItemInput(EMItems.SINGULAR_ALLOY, 4)
                .setFluidInput(Tags.Fluids.LAVA, 1000)
                .setChemicalInput(MekanismChemicals.POLONIUM.asStack(500))
                .build(output, MekInConstants.rl("control_circuit/dense_0"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(ExtraItems.INFINITE_CONTROL_CIRCUIT.asStack(1))
                .addItemInput(ExtraItems.COSMIC_CONTROL_CIRCUIT, 1)
                .addItemInput(MekInBlocks.ULTIMATE_CONTROL_MATRIX, 2)
                .addItemInput(MekInBlocks.AUGMENT_CONTROL_MATRIX, 2)
                .addItemInput(MekInItems.DIMENTIONAL_PROCESSOR, 4)
                .addItemInput(ExtraItems.SPECTRUM_ALLOY, 4)
                .setFluidInput(Tags.Fluids.LAVA, 1000)
                .setChemicalInput(MekanismChemicals.ANTIMATTER.asStack(200))
                .build(output, MekInConstants.rl("control_circuit/infinite_0"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(EMItems.MULTIVERSAL_CONTROL_CIRCUIT.asStack(1))
                .addItemInput(EMItems.DENSE_CONTROL_CIRCUIT, 1)
                .addItemInput(MekInBlocks.ULTIMATE_CONTROL_MATRIX, 2)
                .addItemInput(MekInBlocks.AUGMENT_CONTROL_MATRIX, 2)
                .addItemInput(MekInItems.DIMENTIONAL_PROCESSOR, 4)
                .addItemInput(EMItems.EXOVERSAL_ALLOY, 4)
                .setFluidInput(Tags.Fluids.LAVA, 1000)
                .setChemicalInput(MekanismChemicals.ANTIMATTER.asStack(200))
                .build(output, MekInConstants.rl("control_circuit/multiversal_0"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(MekInItems.ANALYSIS_CONTROL_CIRCUIT.asStack(1))
                .addItemInput(ExtraItems.INFINITE_CONTROL_CIRCUIT, 1)
                .addItemInput(EMItems.MULTIVERSAL_CONTROL_CIRCUIT, 1)
                .addItemInput(MekInBlocks.ABSOLUTE_CONTROL_MATRIX, 2)
                .addItemInput(MekInBlocks.OVERCLOCKED_CONTROL_MATRIX, 2)
                .addItemInput(MekInItems.OBSERVATION_PROCESSOR, 4)
                .addItemInput(MekInItems.EXOREFRACTIVE_PRISMATIC_ALLOY, 4)
                .setFluidInput(MekInFluids.FLUX.asStack(10))
                .setChemicalInput(MekInChemicals.NULL.asStack(4))
                .build(output, MekInConstants.rl("control_circuit/analysis_0"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(MekInItems.MEMORY_CONTROL_CIRCUIT.asStack(1))
                .addItemInput(MekInItems.ANALYSIS_CONTROL_CIRCUIT, 1)
                .addItemInput(MekInBlocks.SUPREME_CONTROL_MATRIX, 2)
                .addItemInput(MekInBlocks.QUANTUM_CONTROL_MATRIX, 2)
                .addItemInput(MekInItems.TRANSMISSION_PROCESSOR, 4)
                .addItemInput(MekInItems.TRAJECTORY_ALLOY, 4)
                .setFluidInput(MekInFluids.FLUX.asStack(100))
                .setChemicalInput(MekInChemicals.NULL.asStack(80))
                .build(output, MekInConstants.rl("control_circuit/memory_0"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(MekInItems.RELATIVITY_CONTROL_CIRCUIT.asStack(1))
                .addItemInput(MekInItems.MEMORY_CONTROL_CIRCUIT, 1)
                .addItemInput(MekInBlocks.COSMIC_CONTROL_MATRIX, 2)
                .addItemInput(MekInBlocks.DENSE_CONTROL_MATRIX, 2)
                .addItemInput(MekInItems.RANDOM_PROCESSOR, 4)
                .addItemInput(MekInItems.RESONANCE_ALLOY, 4)
                .setFluidInput(MekInFluids.FLUX.asStack(1000))
                .setChemicalInput(MekInChemicals.NULL.asStack(1600))
                .build(output, MekInConstants.rl("control_circuit/relativity_0"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(MekInItems.TIMESPACE_CONTROL_CIRCUIT.asStack(1))
                .addItemInput(MekInItems.RELATIVITY_CONTROL_CIRCUIT, 1)
                .addItemInput(MekInBlocks.INFINITE_CONTROL_MATRIX, 2)
                .addItemInput(MekInBlocks.MULTIVERSAL_CONTROL_MATRIX, 2)
                .addItemInput(MekInItems.CLOCK_PROCESSOR, 4)
                .addItemInput(MekInItems.COODINATE_ALLOY, 4)
                .setFluidInput(MekInFluids.FLUX.asStack(10000))
                .setChemicalInput(MekInChemicals.NULL.asStack(32000))
                .build(output, MekInConstants.rl("control_circuit/timespace_0"));
    }
}
