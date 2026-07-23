package com.takenokoshi.mekin.recipe.building;

import com.jerry.mekextras.common.registries.ExtraChemicals;
import com.jerry.mekmm.common.registries.MoreMachineChemicals;
import com.takenokoshi.mekin.core.MekInConstants;
import com.takenokoshi.mekin.registries.MekInChemicals;
import com.takenokoshi.mekin.registries.MekInItems;
import com.takenokoshi.mekut.enums.MUMaterial;
import com.takenokoshi.mekut.recipe.builder.BiChemicalToItemRecipeBuilder;
import com.takenokoshi.mekut.registries.MekUtChemicals;

import fr.iglee42.evolvedmekanism.registries.EMChemicals;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import mekanism.common.registries.MekanismChemicals;
import mekanism.common.resource.PrimaryResource;
import net.minecraft.data.recipes.RecipeOutput;

public class StellerGenesisRecipes {
    public static void buildRecipes(RecipeOutput output) {
        BiChemicalToItemRecipeBuilder
                .stellarGenesis(
                        IngredientCreatorAccess.chemicalStack().fromHolder(MekInChemicals.AMETHYST, 400_000_000_000L),
                        IngredientCreatorAccess.chemicalStack().from(MoreMachineChemicals.UU_MATTER.asStack(320000)),
                        MekInItems.AMETHYST_STARLIGHT.asStack(1))
                .build(output, MekInConstants.rl("steller_genesis/starlight/amethyst"));
        BiChemicalToItemRecipeBuilder
                .stellarGenesis(
                        IngredientCreatorAccess.chemicalStack().fromHolder(MekInChemicals.CERTUS_QUARTZ,
                                400_000_000_000L),
                        IngredientCreatorAccess.chemicalStack().from(MoreMachineChemicals.UU_MATTER.asStack(320000)),
                        MekInItems.CERTUS_QUARTZ_STARLIGHT.asStack(1))
                .build(output, MekInConstants.rl("steller_genesis/starlight/certus_quartz"));
        BiChemicalToItemRecipeBuilder
                .stellarGenesis(
                        IngredientCreatorAccess.chemicalStack().fromHolder(MekInChemicals.COAL, 400_000_000_000L),
                        IngredientCreatorAccess.chemicalStack().from(MoreMachineChemicals.UU_MATTER.asStack(320000)),
                        MekInItems.COAL_STARLIGHT.asStack(1))
                .build(output, MekInConstants.rl("steller_genesis/starlight/coal"));
        BiChemicalToItemRecipeBuilder
                .stellarGenesis(
                        IngredientCreatorAccess.chemicalStack().fromHolder(MekInChemicals.COPPER, 400_000_000_000L),
                        IngredientCreatorAccess.chemicalStack().from(MoreMachineChemicals.UU_MATTER.asStack(320000)),
                        MekInItems.COPPER_STARLIGHT.asStack(1))
                .build(output, MekInConstants.rl("steller_genesis/starlight/copper"));
        BiChemicalToItemRecipeBuilder
                .stellarGenesis(
                        IngredientCreatorAccess.chemicalStack().fromHolder(MekanismChemicals.DIAMOND, 400_000_000_000L),
                        IngredientCreatorAccess.chemicalStack().from(MoreMachineChemicals.UU_MATTER.asStack(320000)),
                        MekInItems.DIAMOND_STARLIGHT.asStack(1))
                .build(output, MekInConstants.rl("steller_genesis/starlight/diamond"));
        BiChemicalToItemRecipeBuilder
                .stellarGenesis(
                        IngredientCreatorAccess.chemicalStack().fromHolder(MekInChemicals.EMERALD, 400_000_000_000L),
                        IngredientCreatorAccess.chemicalStack().from(MoreMachineChemicals.UU_MATTER.asStack(320000)),
                        MekInItems.EMERALD_STARLIGHT.asStack(1))
                .build(output, MekInConstants.rl("steller_genesis/starlight/emerald"));
        BiChemicalToItemRecipeBuilder
                .stellarGenesis(
                        IngredientCreatorAccess.chemicalStack().fromHolder(MekInChemicals.ENTRO, 400_000_000_000L),
                        IngredientCreatorAccess.chemicalStack().from(MoreMachineChemicals.UU_MATTER.asStack(320000)),
                        MekInItems.ENTRO_STARLIGHT.asStack(1))
                .build(output, MekInConstants.rl("steller_genesis/starlight/entro"));
        BiChemicalToItemRecipeBuilder
                .stellarGenesis(
                        IngredientCreatorAccess.chemicalStack().fromHolder(MekInChemicals.FLUORITE, 400_000_000_000L),
                        IngredientCreatorAccess.chemicalStack().from(MoreMachineChemicals.UU_MATTER.asStack(320000)),
                        MekInItems.FLUORITE_STARLIGHT.asStack(1))
                .build(output, MekInConstants.rl("steller_genesis/starlight/fluorite"));
        BiChemicalToItemRecipeBuilder
                .stellarGenesis(
                        IngredientCreatorAccess.chemicalStack().fromHolder(MekanismChemicals.GOLD, 400_000_000_000L),
                        IngredientCreatorAccess.chemicalStack().from(MoreMachineChemicals.UU_MATTER.asStack(320000)),
                        MekInItems.GOLD_STARLIGHT.asStack(1))
                .build(output, MekInConstants.rl("steller_genesis/starlight/gold"));
        BiChemicalToItemRecipeBuilder
                .stellarGenesis(
                        IngredientCreatorAccess.chemicalStack().fromHolder(MekInChemicals.IRON, 400_000_000_000L),
                        IngredientCreatorAccess.chemicalStack().from(MoreMachineChemicals.UU_MATTER.asStack(320000)),
                        MekInItems.IRON_STARLIGHT.asStack(1))
                .build(output, MekInConstants.rl("steller_genesis/starlight/iron"));
        BiChemicalToItemRecipeBuilder
                .stellarGenesis(
                        IngredientCreatorAccess.chemicalStack().fromHolder(MekInChemicals.LAPIS_LAZULI,
                                400_000_000_000L),
                        IngredientCreatorAccess.chemicalStack().from(MoreMachineChemicals.UU_MATTER.asStack(320000)),
                        MekInItems.LAPIS_LAZULI_STARLIGHT.asStack(1))
                .build(output, MekInConstants.rl("steller_genesis/starlight/lapis_lazuli"));
        BiChemicalToItemRecipeBuilder
                .stellarGenesis(
                        IngredientCreatorAccess.chemicalStack().fromHolder(ExtraChemicals.LEAD, 400_000_000_000L),
                        IngredientCreatorAccess.chemicalStack().from(MoreMachineChemicals.UU_MATTER.asStack(320000)),
                        MekInItems.LEAD_STARLIGHT.asStack(1))
                .build(output, MekInConstants.rl("steller_genesis/starlight/lead"));
        BiChemicalToItemRecipeBuilder
                .stellarGenesis(
                        IngredientCreatorAccess.chemicalStack().fromHolder(MekInChemicals.NAQUADAH, 400_000_000_000L),
                        IngredientCreatorAccess.chemicalStack().from(MoreMachineChemicals.UU_MATTER.asStack(320000)),
                        MekInItems.NAQUADAH_STARLIGHT.asStack(1))
                .build(output, MekInConstants.rl("steller_genesis/starlight/naquadah"));
        BiChemicalToItemRecipeBuilder
                .stellarGenesis(
                        IngredientCreatorAccess.chemicalStack().fromHolder(MekUtChemicals.NETHERITE,
                                5_000_000_000_000L),
                        IngredientCreatorAccess.chemicalStack().from(MoreMachineChemicals.UU_MATTER.asStack(320000)),
                        MekInItems.NETHERITE_STARLIGHT.asStack(1))
                .build(output, MekInConstants.rl("steller_genesis/starlight/netherite"));
        BiChemicalToItemRecipeBuilder
                .stellarGenesis(
                        IngredientCreatorAccess.chemicalStack().fromHolder(MekanismChemicals.OSMIUM,
                                8_000_000_000_000L),
                        IngredientCreatorAccess.chemicalStack().from(MoreMachineChemicals.UU_MATTER.asStack(320000)),
                        MekInItems.OSMIUM_STARLIGHT.asStack(1))
                .build(output, MekInConstants.rl("steller_genesis/starlight/osmium"));
        BiChemicalToItemRecipeBuilder
                .stellarGenesis(
                        IngredientCreatorAccess.chemicalStack().fromHolder(MekInChemicals.QUARTZ, 400_000_000_000L),
                        IngredientCreatorAccess.chemicalStack().from(MoreMachineChemicals.UU_MATTER.asStack(320000)),
                        MekInItems.QUARTZ_STARLIGHT.asStack(1))
                .build(output, MekInConstants.rl("steller_genesis/starlight/quartz"));
        BiChemicalToItemRecipeBuilder
                .stellarGenesis(
                        IngredientCreatorAccess.chemicalStack().fromHolder(MekanismChemicals.REDSTONE,
                                400_000_000_000L),
                        IngredientCreatorAccess.chemicalStack().from(MoreMachineChemicals.UU_MATTER.asStack(320000)),
                        MekInItems.REDSTONE_STARLIGHT.asStack(1))
                .build(output, MekInConstants.rl("steller_genesis/starlight/redstone"));
        BiChemicalToItemRecipeBuilder
                .stellarGenesis(
                        IngredientCreatorAccess.chemicalStack().fromHolder(MekanismChemicals.TIN, 400_000_000_000L),
                        IngredientCreatorAccess.chemicalStack().from(MoreMachineChemicals.UU_MATTER.asStack(320000)),
                        MekInItems.TIN_STARLIGHT.asStack(1))
                .build(output, MekInConstants.rl("steller_genesis/starlight/tin"));
        BiChemicalToItemRecipeBuilder
                .stellarGenesis(
                        IngredientCreatorAccess.chemicalStack().fromHolder(EMChemicals.URANIUM, 400_000_000_000L),
                        IngredientCreatorAccess.chemicalStack().from(MoreMachineChemicals.UU_MATTER.asStack(320000)),
                        MekInItems.URANIUM_STARLIGHT.asStack(1))
                .build(output, MekInConstants.rl("steller_genesis/starlight/uranium"));
        BiChemicalToItemRecipeBuilder
                .stellarGenesis(
                        IngredientCreatorAccess.chemicalStack().fromHolder(
                                MekanismChemicals.PROCESSED_RESOURCES.get(PrimaryResource.IRON).getDelegate(),
                                50_000_000L),
                        IngredientCreatorAccess.chemicalStack().fromHolder(
                                MekUtChemicals.MU_MATERIALS_DIRTY_SLURRY.get(MUMaterial.CERTUS_QUARTZ),
                                20_000_000L),
                        MekInItems.NEUTRON_STAR_FRAGMENT.asStack(1))
                .build(output, MekInConstants.rl("steller_genesis/neutron_star_flagment"));
    }
}
