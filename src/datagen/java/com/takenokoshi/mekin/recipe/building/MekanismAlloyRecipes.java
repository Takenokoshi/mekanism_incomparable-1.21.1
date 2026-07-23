package com.takenokoshi.mekin.recipe.building;

import com.jerry.mekextras.common.registries.ExtraChemicals;
import com.jerry.mekextras.common.registries.ExtraItems;
import com.takenokoshi.mekin.core.MekInConstants;
import com.takenokoshi.mekin.registries.MekInChemicals;
import com.takenokoshi.mekin.registries.MekInFluids;
import com.takenokoshi.mekin.registries.MekInItems;
import com.takenokoshi.mekut.recipe.builder.ItemStackListFluidChemicalToItemFluidChemicalRecipeBuilder;
import com.takenokoshi.mekut.registries.MekUtChemicals;
import com.takenokoshi.mekut.registries.MekUtItems;

import fr.iglee42.evolvedmekanism.registries.EMItems;
import fr.iglee42.evolvedmekanism.registries.EMTags;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.datagen.recipe.builder.CombinerRecipeBuilder;
import mekanism.api.datagen.recipe.builder.ItemStackChemicalToItemStackRecipeBuilder;
import mekanism.api.datagen.recipe.builder.NucleosynthesizingRecipeBuilder;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import mekanism.common.registries.MekanismChemicals;
import mekanism.common.registries.MekanismItems;
import mekanism.common.resource.PrimaryResource;
import mekanism.common.resource.ResourceType;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.fluids.FluidStack;

public class MekanismAlloyRecipes {
    public static void buildRecipes(RecipeOutput output) {
        // mekanism alloy
        ItemStackChemicalToItemStackRecipeBuilder
                .metallurgicInfusing(
                        IngredientCreatorAccess.item().from(Tags.Items.INGOTS_COPPER, 8),
                        IngredientCreatorAccess.chemicalStack().from(MekanismChemicals.REDSTONE.asStack(80)),
                        MekanismItems.INFUSED_ALLOY.asStack(1),
                        false)
                .build(output, MekInConstants.rl("alloy/infused"));
        ItemStackChemicalToItemStackRecipeBuilder
                .metallurgicInfusing(
                        IngredientCreatorAccess.item().from(
                                MekanismItems.PROCESSED_RESOURCES.get(ResourceType.CRYSTAL, PrimaryResource.COPPER), 1),
                        IngredientCreatorAccess.chemicalStack().from(MekanismChemicals.REDSTONE.asStack(160)),
                        MekanismItems.INFUSED_ALLOY.asStack(32),
                        false)
                .build(output, MekInConstants.rl("alloy/infused_easy"));
        ItemStackChemicalToItemStackRecipeBuilder
                .metallurgicInfusing(
                        IngredientCreatorAccess.item().from(MekanismItems.INFUSED_ALLOY.asStack(8)),
                        IngredientCreatorAccess.chemicalStack().from(MekanismChemicals.DIAMOND.asStack(160)),
                        MekanismItems.REINFORCED_ALLOY.asStack(1),
                        false)
                .build(output, MekInConstants.rl("alloy/reinforced"));
        ItemStackChemicalToItemStackRecipeBuilder
                .metallurgicInfusing(
                        IngredientCreatorAccess.item().from(
                                MekanismItems.PROCESSED_RESOURCES.get(ResourceType.CRYSTAL, PrimaryResource.GOLD), 4),
                        IngredientCreatorAccess.chemicalStack().from(MekanismChemicals.DIAMOND.asStack(320)),
                        MekanismItems.REINFORCED_ALLOY.asStack(16),
                        false)
                .build(output, MekInConstants.rl("alloy/reinforced_easy"));
        ItemStackChemicalToItemStackRecipeBuilder
                .metallurgicInfusing(
                        IngredientCreatorAccess.item().from(MekanismItems.REINFORCED_ALLOY.asStack(8)),
                        IngredientCreatorAccess.chemicalStack().from(MekanismChemicals.REFINED_OBSIDIAN.asStack(320)),
                        MekanismItems.ATOMIC_ALLOY.asStack(1),
                        false)
                .build(output, MekInConstants.rl("alloy/atomic"));
        ItemStackChemicalToItemStackRecipeBuilder
                .metallurgicInfusing(
                        IngredientCreatorAccess.item().from(
                                MekanismItems.PROCESSED_RESOURCES.get(ResourceType.CRYSTAL, PrimaryResource.OSMIUM),
                                16),
                        IngredientCreatorAccess.chemicalStack().from(MekanismChemicals.REFINED_OBSIDIAN.asStack(640)),
                        MekanismItems.ATOMIC_ALLOY.asStack(8),
                        false)
                .build(output, MekInConstants.rl("alloy/atomic_easy"));
        // mekut alloy
        ItemStackChemicalToItemStackRecipeBuilder
                .metallurgicInfusing(
                        IngredientCreatorAccess.item()
                                .from(ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "ingots/tin")), 8),
                        IngredientCreatorAccess.chemicalStack().from(MekUtChemicals.FLUIX.asStack(80)),
                        MekUtItems.ELASTIC_ALLOY.asStack(1),
                        false)
                .build(output, MekInConstants.rl("alloy/elastic"));
        ItemStackChemicalToItemStackRecipeBuilder
                .metallurgicInfusing(
                        IngredientCreatorAccess.item().from(
                                MekanismItems.PROCESSED_RESOURCES.get(ResourceType.CRYSTAL, PrimaryResource.TIN), 1),
                        IngredientCreatorAccess.chemicalStack().from(MekUtChemicals.FLUIX.asStack(160)),
                        MekUtItems.ELASTIC_ALLOY.asStack(32),
                        false)
                .build(output, MekInConstants.rl("alloy/elastic_easy"));
        ItemStackChemicalToItemStackRecipeBuilder
                .metallurgicInfusing(
                        IngredientCreatorAccess.item().from(MekUtItems.ELASTIC_ALLOY.asStack(8)),
                        IngredientCreatorAccess.chemicalStack().from(MekUtChemicals.SINGULARITY.asStack(160)),
                        MekUtItems.CONVERGENT_ALLOY.asStack(1),
                        false)
                .build(output, MekInConstants.rl("alloy/convergent"));
        ItemStackChemicalToItemStackRecipeBuilder
                .metallurgicInfusing(
                        IngredientCreatorAccess.item().from(MekUtItems.CONVERGENT_ALLOY.asStack(8)),
                        IngredientCreatorAccess.chemicalStack().from(MekUtChemicals.ENRICHED_XP.asStack(800)),
                        MekUtItems.COMPISITE_ALLOY.asStack(1),
                        false)
                .build(output, MekInConstants.rl("alloy/composite"));
        // extras alloy
        ItemStackListFluidChemicalToItemFluidChemicalRecipeBuilder
                .smallDigitalReactionChamber(ExtraItems.RADIANCE_ALLOY.asStack(1), FluidStack.EMPTY,
                        ChemicalStack.EMPTY)
                .addItemInput(MekanismItems.ATOMIC_ALLOY, 4)
                .addItemInput(MekUtItems.COMPISITE_ALLOY, 4)
                .addItemInput(MekUtItems.IRIDIUM_INGOT, 4)
                .setFluidInput(Tags.Fluids.LAVA, 200)
                .setChemicalInput(ExtraChemicals.RADIANCE.asStack(320))
                .build(output, MekInConstants.rl("alloy/radiance"));
        ItemStackChemicalToItemStackRecipeBuilder
                .metallurgicInfusing(
                        IngredientCreatorAccess.item().from(ExtraItems.RADIANCE_ALLOY.asStack(4)),
                        IngredientCreatorAccess.chemicalStack().from(ExtraChemicals.THERMONUCLEAR.asStack(480)),
                        ExtraItems.THERMONUCLEAR_ALLOY.asStack(1),
                        false)
                .build(output, MekInConstants.rl("alloy/thermonuclear"));
        ItemStackChemicalToItemStackRecipeBuilder
                .metallurgicInfusing(
                        IngredientCreatorAccess.item().from(ExtraItems.THERMONUCLEAR_ALLOY.asStack(4)),
                        IngredientCreatorAccess.chemicalStack().from(ExtraChemicals.SHINING.asStack(640)),
                        ExtraItems.SHINING_ALLOY.asStack(1),
                        false)
                .build(output, MekInConstants.rl("alloy/shining"));
        ItemStackChemicalToItemStackRecipeBuilder
                .metallurgicInfusing(
                        IngredientCreatorAccess.item().from(ExtraItems.SHINING_ALLOY.asStack(4)),
                        IngredientCreatorAccess.chemicalStack().from(ExtraChemicals.SPECTRUM.asStack(800)),
                        ExtraItems.SPECTRUM_ALLOY.asStack(1),
                        false)
                .build(output, MekInConstants.rl("alloy/spectrum"));
        // evolved alloy
        ItemStackListFluidChemicalToItemFluidChemicalRecipeBuilder
                .smallDigitalReactionChamber(EMItems.HYPERCHARGED_ALLOY.asStack(1), FluidStack.EMPTY,
                        ChemicalStack.EMPTY)
                .addItemInput(MekanismItems.ATOMIC_ALLOY, 4)
                .addItemInput(MekUtItems.COMPISITE_ALLOY, 4)
                .addItemInput(MekUtItems.IRIDIUM_INGOT, 4)
                .setFluidInput(Tags.Fluids.LAVA, 200)
                .setChemicalInput(IngredientCreatorAccess.chemicalStack().from(EMTags.Gases.URANIUM, 320))
                .build(output, MekInConstants.rl("alloy/hypercharged"));
        NucleosynthesizingRecipeBuilder
                .nucleosynthesizing(
                        IngredientCreatorAccess.item().from(EMTags.Items.ALLOYS_HYPERCHARGED, 4),
                        IngredientCreatorAccess.chemicalStack().from(MekanismChemicals.ANTIMATTER.asStack(200)),
                        EMItems.SUBATOMIC_ALLOY.asStack(1),
                        1000, false)
                .build(output, MekInConstants.rl("alloy/subatomic"));
        ItemStackChemicalToItemStackRecipeBuilder
                .metallurgicInfusing(
                        IngredientCreatorAccess.item().from(EMTags.Items.ALLOYS_SUBATOMIC, 4),
                        IngredientCreatorAccess.chemicalStack().from(EMTags.Gases.BETTER_GOLD, 160),
                        EMItems.SINGULAR_ALLOY.asStack(1),
                        false)
                .build(output, MekInConstants.rl("alloy/singular"));
        ItemStackChemicalToItemStackRecipeBuilder
                .metallurgicInfusing(
                        IngredientCreatorAccess.item().from(EMTags.Items.ALLOYS_SINGULAR, 4),
                        IngredientCreatorAccess.chemicalStack().from(EMTags.Gases.PLASLITHERITE, 160),
                        EMItems.EXOVERSAL_ALLOY.asStack(1),
                        false)
                .build(output, MekInConstants.rl("alloy/exoversal"));
        // mekin alloy
        ItemStackListFluidChemicalToItemFluidChemicalRecipeBuilder
                .smallDigitalReactionChamber(MekInItems.EXOREFRACTIVE_PRISMATIC_ALLOY.asStack(1), FluidStack.EMPTY,
                        ChemicalStack.EMPTY)
                .addItemInput(EMTags.Items.ALLOYS_EXOVERSAL, 2)
                .addItemInput(ExtraItems.SPECTRUM_ALLOY, 2)
                .setChemicalInput(MekInChemicals.OBLIVION_SINGULARITY.asStack(160))
                .setFluidInput(MekInFluids.FLUX.asStack(10))
                .build(output, MekInConstants.rl("alloy/exorefractive_prismatic"));
        CombinerRecipeBuilder
                .combining(
                        IngredientCreatorAccess.item().from(MekInItems.IRON_STARLIGHT, 1),
                        IngredientCreatorAccess.item().from(MekInItems.EXOREFRACTIVE_PRISMATIC_ALLOY, 2),
                        MekInItems.TRAJECTORY_ALLOY.asStack(1))
                .build(output, MekInConstants.rl("alloy/trajectory"));
        ItemStackChemicalToItemStackRecipeBuilder
                .metallurgicInfusing(
                        IngredientCreatorAccess.item().from(MekInItems.TRAJECTORY_ALLOY, 2),
                        IngredientCreatorAccess.chemicalStack().from(MekInChemicals.ELECTRINE.asStack(160_000L)),
                        MekInItems.RESONANCE_ALLOY.asStack(1),
                        false)
                .build(output, MekInConstants.rl("alloy/resonance"));
        CombinerRecipeBuilder
                .combining(
                        IngredientCreatorAccess.item().from(MekInItems.ETERNAL_STARLIGHT, 1),
                        IngredientCreatorAccess.item().from(MekInItems.RESONANCE_ALLOY, 2),
                        MekInItems.COODINATE_ALLOY.asStack(1))
                .build(output, MekInConstants.rl("alloy/coodinate"));
    }
}
