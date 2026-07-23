package com.takenokoshi.mekin.recipe.building;

import com.extendedae_plus.init.ModItems;
import com.fxd927.mekanismelements.common.registries.MSItems;
import com.glodblock.github.extendedae.common.EAESingletons;
import com.jerry.mekmm.common.registries.MoreMachineChemicals;
import com.takenokoshi.mekin.core.MekInConstants;
import com.takenokoshi.mekin.registries.MekInItems;
import com.takenokoshi.mekut.recipe.builder.ItemStackListFluidChemicalToItemFluidChemicalRecipeBuilder;
import com.takenokoshi.mekut.registries.MekUtChemicals;
import com.takenokoshi.mekut.registries.MekUtItems;

import appeng.core.definitions.AEItems;
import mekanism.api.chemical.ChemicalStack;
import mekanism.common.registries.MekanismItems;
import mekanism.common.tags.MekanismTags;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.fluids.FluidStack;
import net.pedroksl.advanced_ae.common.definitions.AAEFluids;
import net.pedroksl.advanced_ae.common.definitions.AAEItems;

public class SDRCRecipes {
    public static void buildRecipes(RecipeOutput output) {
        ItemStackListFluidChemicalToItemFluidChemicalRecipeBuilder
                .smallDigitalReactionChamber(MekInItems.SEMICONDUCTIVE_ALLOY_INGOT.asStack(1), FluidStack.EMPTY,
                        ChemicalStack.EMPTY)
                .addItemInput(AEItems.SILICON, 8)
                .addItemInput(EAESingletons.ENTRO_DUST, 1)
                .setFluidInput(MekanismTags.Fluids.LITHIUM, 250)
                .setChemicalInput(MekUtChemicals.FLUIX.asStack(160))
                .build(output, MekInConstants.rl("small_digital_reaction_chamber/semiconductive_alloy_ingot"));
        ItemStackListFluidChemicalToItemFluidChemicalRecipeBuilder
                .smallDigitalReactionChamber(MekInItems.ASTRAL_GLOWSTONE_INGOT.asStack(1), FluidStack.EMPTY,
                        ChemicalStack.EMPTY)
                .addItemInput(MekanismTags.Items.INGOTS_REFINED_GLOWSTONE, 2)
                .addItemInput(EAESingletons.ENTRO_INGOT, 2)
                .addItemInput(MSItems.NEUTRON_SOURCE_PELLET, 4)
                .setFluidInput(Tags.Fluids.WATER, 500)
                .setChemicalInput(MekUtChemicals.ASTRAL_ETHER.asStack(200))
                .build(output, MekInConstants.rl("small_digital_reaction_chamber/astral_glowstone_ingot"));
        ItemStackListFluidChemicalToItemFluidChemicalRecipeBuilder
                .smallDigitalReactionChamber(new ItemStack(ModItems.OBLIVION_SINGULARITY.asItem(), 1), FluidStack.EMPTY,
                        ChemicalStack.EMPTY)
                .addItemInput(AEItems.SINGULARITY, 512)
                .addItemInput(MekUtItems.ARTIFICIAL_STAR, 8)
                .addItemInput(AAEItems.SHATTERED_SINGULARITY, 16)
                .addItemInput(EAESingletons.ENTRO_DUST, 16)
                .addItemInput(Items.NETHERITE_INGOT, 32)
                .addItemInput(MekanismItems.POLONIUM_PELLET, 32)
                .addItemInput(MekInItems.TITANIUM_INGOT, 32)
                .setFluidInput(AAEFluids.QUANTUM_INFUSION.stack(10000))
                .setChemicalInput(MoreMachineChemicals.UU_MATTER.asStack(1000))
                .build(output, MekInConstants.rl("small_digital_reaction_chamber/oblivion_singularity"));
        ItemStackListFluidChemicalToItemFluidChemicalRecipeBuilder
                .smallDigitalReactionChamber(MekInItems.NEUTRONIUM_INGOT.asStack(1), FluidStack.EMPTY,
                        ChemicalStack.EMPTY)
                .addItemInput(MekInItems.NEUTRON_STAR_FRAGMENT, 4)
                .addItemInput(Tags.Items.COBBLESTONES, 262_144)
                .setFluidInput(Tags.Fluids.LAVA, 5000)
                .setChemicalInput(MekUtChemicals.ASTRAL_ETHER.asStack(4000L))
                .setEnergyRequired(1_000_000_000L)
                .build(output, MekInConstants.rl("small_digital_reaction_chamber/neutronium_ingot"));
    }
}
