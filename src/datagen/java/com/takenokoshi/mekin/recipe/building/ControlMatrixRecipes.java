package com.takenokoshi.mekin.recipe.building;

import com.extendedae_plus.init.ModItems;
import com.fxd927.mekanismelements.common.registries.MSItems;
import com.glodblock.github.appflux.common.AFSingletons;
import com.jerry.mekextras.common.registries.ExtraItems;
import com.takenokoshi.mekin.core.MekInConstants;
import com.takenokoshi.mekin.registries.MekInBlocks;
import com.takenokoshi.mekin.registries.MekInItems;
import com.takenokoshi.mekut.recipe.builder.ItemStackListFluidChemicalToItemRecipeBuilder;
import com.takenokoshi.mekut.registries.MekUtItems;

import appeng.core.definitions.AEItems;
import fr.iglee42.evolvedmekanism.registries.EMItems;
import gripe._90.megacells.definition.MEGAItems;
import mekanism.common.registries.MekanismChemicals;
import mekanism.common.registries.MekanismFluids;
import mekanism.common.registries.MekanismItems;
import mekanism.common.tags.MekanismTags;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.Tags;
import net.pedroksl.advanced_ae.common.definitions.AAEBlocks;
import net.pedroksl.advanced_ae.common.definitions.AAEFluids;
import net.pedroksl.advanced_ae.common.definitions.AAEItems;

public class ControlMatrixRecipes {

    public static void buildRecipes(RecipeOutput output) {
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(MekInBlocks.BASIC_CONTROL_MATRIX.asItem(), 1))
                .addItemInput(MekanismItems.BASIC_CONTROL_CIRCUIT.asStack(4))
                .addItemInput(MekanismTags.Items.DUSTS_LITHIUM, 4)
                .addItemInput(MSItems.DUST_CALCIUM_OXIDE.asStack(4))
                .addItemInput(AEItems.CELL_COMPONENT_16K.stack(1))
                .setFluidInput(Tags.Fluids.WATER, 200)
                .setChemicalInput(MekanismChemicals.REDSTONE.asStack(1280))
                .build(output, MekInConstants.rl("control_matrix/basic"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(MekInBlocks.ADVANCED_CONTROL_MATRIX.asItem(), 1))
                .addItemInput(MekanismItems.ADVANCED_CONTROL_CIRCUIT.asStack(4))
                .addItemInput(MekInBlocks.BASIC_CONTROL_MATRIX, 4)
                .addItemInput(MekanismItems.HDPE_SHEET, 16)
                .addItemInput(AEItems.CELL_COMPONENT_256K.stack(1))
                .setFluidInput(Tags.Fluids.WATER, 200)
                .setChemicalInput(MekanismChemicals.GOLD.asStack(1280))
                .build(output, MekInConstants.rl("control_matrix/advanced"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(MekInBlocks.ELITE_CONTROL_MATRIX.asItem(), 1))
                .addItemInput(MekanismItems.ELITE_CONTROL_CIRCUIT.asStack(4))
                .addItemInput(MekInBlocks.ADVANCED_CONTROL_MATRIX, 4)
                .addItemInput(MSItems.NEUTRON_SOURCE_PELLET, 16)
                .addItemInput(MEGAItems.CELL_COMPONENT_4M.stack(1))
                .setFluidInput(MekanismFluids.HEAVY_WATER.asStack(400))
                .setChemicalInput(MekanismChemicals.OSMIUM.asStack(1280))
                .build(output, MekInConstants.rl("control_matrix/elite"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(MekInBlocks.ULTIMATE_CONTROL_MATRIX.asItem(), 1))
                .addItemInput(MekanismItems.ULTIMATE_CONTROL_CIRCUIT.asStack(4))
                .addItemInput(MekInBlocks.ELITE_CONTROL_MATRIX, 4)
                .addItemInput(MekUtItems.IRIDIUM_DUST, 4)
                .addItemInput(MEGAItems.CELL_COMPONENT_64M.stack(1))
                .setFluidInput(MekanismFluids.HEAVY_WATER.asStack(1600))
                .setChemicalInput(MekanismChemicals.ANTIMATTER.asStack(10))
                .build(output, MekInConstants.rl("control_matrix/ultimate"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(MekInBlocks.DIGITAL_CONTROL_MATRIX.asItem(), 1))
                .addItemInput(MekUtItems.DIGITAL_CONTROL_CIRCUIT.asStack(4))
                .addItemInput(MekInBlocks.BASIC_CONTROL_MATRIX, 4)
                .addItemInput(Items.AMETHYST_SHARD, 16)
                .addItemInput(AEItems.CELL_COMPONENT_256K.stack(1))
                .setFluidInput(Tags.Fluids.WATER, 200)
                .setChemicalInput(MekanismChemicals.TIN.asStack(1280))
                .build(output, MekInConstants.rl("control_matrix/digital"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(MekInBlocks.STANDARD_CONTROL_MATRIX.asItem(), 1))
                .addItemInput(MekUtItems.STANDARD_CONTROL_CIRCUIT.asStack(4))
                .addItemInput(MekInBlocks.DIGITAL_CONTROL_MATRIX, 4)
                .addItemInput(AAEBlocks.QUANTUM_UNIT, 16)
                .addItemInput(MEGAItems.CELL_COMPONENT_4M.stack(1))
                .setFluidInput(MekanismFluids.HEAVY_WATER.asStack(400))
                .setChemicalInput(MekanismChemicals.PLUTONIUM.asStack(2000))
                .build(output, MekInConstants.rl("control_matrix/standard"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(MekInBlocks.AUGMENT_CONTROL_MATRIX.asItem(), 1))
                .addItemInput(MekUtItems.AUGMENT_CONTROL_CIRCUIT.asStack(4))
                .addItemInput(MekInBlocks.STANDARD_CONTROL_MATRIX, 4)
                .addItemInput(MekUtItems.IRIDIUM_DUST, 4)
                .addItemInput(MEGAItems.CELL_COMPONENT_64M.stack(1))
                .setFluidInput(MekanismFluids.HEAVY_WATER.asStack(1600))
                .setChemicalInput(MekanismChemicals.POLONIUM.asStack(8000))
                .build(output, MekInConstants.rl("control_matrix/knowledge"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(MekInBlocks.ABSOLUTE_CONTROL_MATRIX.asItem(), 1))
                .addItemInput(ExtraItems.ABSOLUTE_CONTROL_CIRCUIT, 4)
                .addItemInput(MekInBlocks.ULTIMATE_CONTROL_MATRIX, 2)
                .addItemInput(MekInBlocks.AUGMENT_CONTROL_MATRIX, 2)
                .addItemInput(MekInItems.TITANIUM_INGOT, 4)
                .addItemInput(AFSingletons.CORE_256M, 1)
                .setFluidInput(AAEFluids.QUANTUM_INFUSION.stack(2000))
                .setChemicalInput(MekanismChemicals.ANTIMATTER.asStack(8000))
                .build(output, MekInConstants.rl("control_matrix/absolute"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(MekInBlocks.OVERCLOCKED_CONTROL_MATRIX.asItem(), 1))
                .addItemInput(EMItems.OVERCLOCKED_CONTROL_CIRCUIT, 4)
                .addItemInput(MekInBlocks.ULTIMATE_CONTROL_MATRIX, 2)
                .addItemInput(MekInBlocks.AUGMENT_CONTROL_MATRIX, 2)
                .addItemInput(MekInItems.TITANIUM_INGOT, 4)
                .addItemInput(AFSingletons.CORE_256M, 1)
                .setFluidInput(AAEFluids.QUANTUM_INFUSION.stack(2000))
                .setChemicalInput(MekanismChemicals.ANTIMATTER.asStack(8000))
                .build(output, MekInConstants.rl("control_matrix/overclocked"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(MekInBlocks.SUPREME_CONTROL_MATRIX.asItem(), 1))
                .addItemInput(ExtraItems.SUPREME_CONTROL_CIRCUIT, 4)
                .addItemInput(MekInBlocks.ABSOLUTE_CONTROL_MATRIX, 4)
                .addItemInput(MekInItems.SILVER_INGOT, 4)
                .addItemInput(AAEItems.QUANTUM_STORAGE_COMPONENT, 1)
                .setFluidInput(AAEFluids.QUANTUM_INFUSION.stack(2000))
                .setChemicalInput(MekanismChemicals.ANTIMATTER.asStack(8000))
                .build(output, MekInConstants.rl("control_matrix/supreme"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(MekInBlocks.QUANTUM_CONTROL_MATRIX.asItem(), 1))
                .addItemInput(EMItems.QUANTUM_CONTROL_CIRCUIT, 4)
                .addItemInput(MekInBlocks.OVERCLOCKED_CONTROL_MATRIX, 4)
                .addItemInput(MekInItems.SILVER_INGOT, 4)
                .addItemInput(AAEItems.QUANTUM_STORAGE_COMPONENT, 1)
                .setFluidInput(AAEFluids.QUANTUM_INFUSION.stack(2000))
                .setChemicalInput(MekanismChemicals.ANTIMATTER.asStack(8000))
                .build(output, MekInConstants.rl("control_matrix/quantum"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(MekInBlocks.COSMIC_CONTROL_MATRIX.asItem(), 1))
                .addItemInput(ExtraItems.COSMIC_CONTROL_CIRCUIT, 4)
                .addItemInput(MekInBlocks.SUPREME_CONTROL_MATRIX, 4)
                .addItemInput(MekInItems.REDSTONE_STARLIGHT, 1)
                .addItemInput(ModItems.ENERGY_STORAGE_CORE, 1)
                .addItemInput(ModItems.SPATIAL_CORE, 1)
                .setFluidInput(AAEFluids.QUANTUM_INFUSION.stack(2000))
                .setChemicalInput(MekanismChemicals.ANTIMATTER.asStack(8000))
                .build(output, MekInConstants.rl("control_matrix/cosmic"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(MekInBlocks.DENSE_CONTROL_MATRIX.asItem(), 1))
                .addItemInput(EMItems.DENSE_CONTROL_CIRCUIT, 4)
                .addItemInput(MekInBlocks.QUANTUM_CONTROL_MATRIX, 4)
                .addItemInput(MekInItems.REDSTONE_STARLIGHT, 1)
                .addItemInput(ModItems.QUANTUM_STORAGE_CORE, 1)
                .addItemInput(ModItems.STORAGE_CORE, 1)
                .setFluidInput(AAEFluids.QUANTUM_INFUSION.stack(2000))
                .setChemicalInput(MekanismChemicals.ANTIMATTER.asStack(8000))
                .build(output, MekInConstants.rl("control_matrix/dense"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(MekInBlocks.INFINITE_CONTROL_MATRIX.asItem(), 1))
                .addItemInput(ExtraItems.INFINITE_CONTROL_CIRCUIT, 4)
                .addItemInput(MekInBlocks.COSMIC_CONTROL_MATRIX, 4)
                .addItemInput(MekInItems.FLUORITE_STARLIGHT, 1)
                .addItemInput(MekInItems.GOLD_STARLIGHT, 1)
                .addItemInput(MekInItems.NAQUADAH_STARLIGHT, 1)
                .addItemInput(MekInItems.LAPIS_LAZULI_STARLIGHT, 1)
                .addItemInput(ModItems.INFINITY_CORE, 1)
                .setFluidInput(AAEFluids.QUANTUM_INFUSION.stack(2000))
                .setChemicalInput(MekanismChemicals.ANTIMATTER.asStack(8000))
                .build(output, MekInConstants.rl("control_matrix/infinite"));
        ItemStackListFluidChemicalToItemRecipeBuilder
                .smallDigitalAssembler(new ItemStack(MekInBlocks.MULTIVERSAL_CONTROL_MATRIX.asItem(), 1))
                .addItemInput(EMItems.MULTIVERSAL_CONTROL_CIRCUIT, 4)
                .addItemInput(MekInBlocks.DENSE_CONTROL_MATRIX, 4)
                .addItemInput(MekInItems.FLUORITE_STARLIGHT, 1)
                .addItemInput(MekInItems.GOLD_STARLIGHT, 1)
                .addItemInput(MekInItems.NAQUADAH_STARLIGHT, 1)
                .addItemInput(MekInItems.LAPIS_LAZULI_STARLIGHT, 1)
                .addItemInput(ModItems.INFINITY_CORE, 1)
                .setFluidInput(AAEFluids.QUANTUM_INFUSION.stack(2000))
                .setChemicalInput(MekanismChemicals.ANTIMATTER.asStack(8000))
                .build(output, MekInConstants.rl("control_matrix/multiversal"));
    }
}
