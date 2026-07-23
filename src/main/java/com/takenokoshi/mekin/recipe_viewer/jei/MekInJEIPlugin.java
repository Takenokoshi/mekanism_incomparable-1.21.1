package com.takenokoshi.mekin.recipe_viewer.jei;

import java.util.List;

import com.takenokoshi.mekaddonlib.recipe_viewer.jei.MekALRecipeRegistryHelper;
import com.takenokoshi.mekin.core.MekInConstants;
import com.takenokoshi.mekin.recipe_viewer.jei.category.ChemicalLeacherRecipeCategory;
import com.takenokoshi.mekin.recipe_viewer.jei.category.FluidChemicalToBiChemicalRecipeCategory;
import com.takenokoshi.mekin.recipe_viewer.jei.category.FluxCondenserRecipeCategory;
import com.takenokoshi.mekin.recipe_viewer.jei.category.ItemStackChemicalToChemicalRecipeCategory;
import com.takenokoshi.mekin.recipe_viewer.jei.category.TPSRecipeCategory;
import com.takenokoshi.mekin.recipe_viewer.recipe.FluxCondenserRVRecipe;
import com.takenokoshi.mekin.recipe_viewer.type.MekInRecipeViewerRecipeType;
import com.takenokoshi.mekin.registries.MekInBlocks;
import com.takenokoshi.mekin.registries.MekInItems;
import com.takenokoshi.mekin.registries.MekInMachines;
import com.takenokoshi.mekin.registries.MekInRecipeTypes;
import com.takenokoshi.mekut.recipe_viewer.type.MekUtRecipeViewerRecipeType;

import mekanism.client.recipe_viewer.jei.CatalystRegistryHelper;
import mekanism.client.recipe_viewer.jei.MekanismJEI;
import mekanism.client.recipe_viewer.jei.RecipeRegistryHelper;
import mekanism.client.recipe_viewer.jei.machine.ItemStackChemicalToItemStackRecipeCategory;
import mekanism.client.recipe_viewer.type.IRecipeViewerRecipeType;
import mekanism.client.recipe_viewer.type.RecipeViewerRecipeType;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;
import net.minecraft.resources.ResourceLocation;

@JeiPlugin
public class MekInJEIPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return MekInConstants.rl("jei_plugin");
    }

    public static boolean shouldLoad() {
        // Skip handling if both EMI and JEI are loaded as otherwise some things behave
        // strangely
        // MekIn doesn't have emi integration now, so this method should return true.
        // return !Mekanism.hooks.emi.isLoaded();
        return true;
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration) {
        if (shouldLoad()) {
            MekanismJEI.registerItemSubtypes(registration, MekInItems.ITEMS.getEntries());
            MekanismJEI.registerItemSubtypes(registration, MekInBlocks.BLOCKS.getSecondaryEntries());
            MekanismJEI.registerItemSubtypes(registration, MekInMachines.MACHINES.blockRegister.getSecondaryEntries());
        }
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        if (!shouldLoad()) {
            return;
        }
        IGuiHelper guiHelper = registry.getJeiHelpers().getGuiHelper();
        registry.addRecipeCategories(new IRecipeCategory[] {
                new ItemStackChemicalToChemicalRecipeCategory(guiHelper, MekInRecipeViewerRecipeType.AET),
                new FluidChemicalToBiChemicalRecipeCategory(guiHelper, MekInRecipeViewerRecipeType.CHEMICAL_EXTRACTION),
                new FluxCondenserRecipeCategory(guiHelper, MekInRecipeViewerRecipeType.FLUX_CONDENSER),
                new ChemicalLeacherRecipeCategory(guiHelper, MekInRecipeViewerRecipeType.LEACHING),
                new ItemStackChemicalToItemStackRecipeCategory(guiHelper, MekInRecipeViewerRecipeType.REFINING),
                new TPSRecipeCategory(guiHelper, MekInRecipeViewerRecipeType.TEPS),
        });
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        if (!shouldLoad()) {
            return;
        }
        MekALRecipeRegistryHelper.register(registration, MekInRecipeViewerRecipeType.AET,
                MekInRecipeTypes.AET);
        MekALRecipeRegistryHelper.register(registration, MekInRecipeViewerRecipeType.CHEMICAL_EXTRACTION,
                MekInRecipeTypes.CHEMICAL_EXTRACTION);
        RecipeRegistryHelper.register(registration, MekInRecipeViewerRecipeType.FLUX_CONDENSER,
                FluxCondenserRVRecipe.getFluxCondenserRecipes());
        MekALRecipeRegistryHelper.register(registration, MekInRecipeViewerRecipeType.LEACHING,
                MekInRecipeTypes.LEACHING);
        MekALRecipeRegistryHelper.register(registration, MekInRecipeViewerRecipeType.REFINING,
                MekInRecipeTypes.REFINING);
        MekALRecipeRegistryHelper.register(registration, MekInRecipeViewerRecipeType.TEPS, MekInRecipeTypes.TEPS);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        if (!shouldLoad()) {
            return;
        }
        CatalystRegistryHelper.register(registration, new IRecipeViewerRecipeType[] {
                MekInRecipeViewerRecipeType.AET,
                MekInRecipeViewerRecipeType.CHEMICAL_EXTRACTION,
                MekInRecipeViewerRecipeType.FLUX_CONDENSER,
                MekInRecipeViewerRecipeType.LEACHING,
                MekInRecipeViewerRecipeType.REFINING,
                MekInRecipeViewerRecipeType.TEPS,
        });
        CatalystRegistryHelper.register(registration,
                MekanismJEI.genericRecipeType(MekUtRecipeViewerRecipeType.CHEMICAL_CUT),
                List.of(MekInMachines.ABSOLUTE_OVERCLOCKED_CHEMICAL_CUTTER));
        CatalystRegistryHelper.register(registration,
                MekanismJEI.genericRecipeType(RecipeViewerRecipeType.CRYSTALLIZING),
                List.of(MekInMachines.SUPREME_QUANTUM_CHEMICAL_CRYSTALLIZER));
        CatalystRegistryHelper.register(registration,
                MekanismJEI.genericRecipeType(RecipeViewerRecipeType.DISSOLUTION),
                List.of(MekInMachines.SUPREME_QUANTUM_CHEMICAL_DISSOLUTION_CHAMBER));
        CatalystRegistryHelper.register(registration,
                MekanismJEI.genericRecipeType(RecipeViewerRecipeType.INJECTING),
                List.of(MekInMachines.ABSOLUTE_OVERCLOCKED_CHEMICAL_INJECTION_CHAMBER));
        CatalystRegistryHelper.register(registration,
                MekanismJEI.genericRecipeType(RecipeViewerRecipeType.OXIDIZING),
                List.of(MekInMachines.ABSOLUTE_OVERCLOCKED_CHEMICAL_OXIDIZER));
        CatalystRegistryHelper.register(registration,
                MekanismJEI.genericRecipeType(RecipeViewerRecipeType.WASHING),
                List.of(MekInMachines.SUPREME_QUANTUM_CHEMICAL_WASHER));
        CatalystRegistryHelper.register(registration,
                MekanismJEI.genericRecipeType(RecipeViewerRecipeType.CRUSHING),
                List.of(MekInMachines.ABSOLUTE_OVERCLOCKED_CRUSHER));
        CatalystRegistryHelper.register(registration,
                MekanismJEI.genericRecipeType(RecipeViewerRecipeType.SEPARATING),
                List.of(MekInMachines.ABSOLUTE_OVERCLOCKED_ELECTROLYTIC_SEPARATOR));
        CatalystRegistryHelper.register(registration,
                MekanismJEI.genericRecipeType(MekUtRecipeViewerRecipeType.TWEAKED_SMELLTING),
                List.of(MekInMachines.ABSOLUTE_OVERCLOCKED_ENERIZED_SMELTER));
        CatalystRegistryHelper.register(registration,
                MekanismJEI.genericRecipeType(RecipeViewerRecipeType.ENRICHING),
                List.of(MekInMachines.ABSOLUTE_OVERCLOCKED_ENRICHMENT_CHAMBER));
        CatalystRegistryHelper.register(registration,
                MekanismJEI.genericRecipeType(RecipeViewerRecipeType.METALLURGIC_INFUSING),
                List.of(MekInMachines.ABSOLUTE_OVERCLOCKED_METALLURGIC_INFUSER));
        CatalystRegistryHelper.register(registration,
                MekanismJEI.genericRecipeType(RecipeViewerRecipeType.COMPRESSING),
                List.of(MekInMachines.ABSOLUTE_OVERCLOCKED_OSMIUM_COMPRESSOR));
        CatalystRegistryHelper.register(registration,
                MekanismJEI.genericRecipeType(RecipeViewerRecipeType.PAINTING),
                List.of(MekInMachines.ABSOLUTE_OVERCLOCKED_PAINTING_MACHINE));
        CatalystRegistryHelper.register(registration,
                MekanismJEI.genericRecipeType(RecipeViewerRecipeType.PURIFYING),
                List.of(MekInMachines.ABSOLUTE_OVERCLOCKED_PURIFICATION_CHAMBER));
        CatalystRegistryHelper.register(registration,
                MekanismJEI.genericRecipeType(RecipeViewerRecipeType.CONDENSENTRATING),
                List.of(MekInMachines.ABSOLUTE_OVERCLOCKED_ROTARY_CONDENSENTRATOR));
        CatalystRegistryHelper.register(registration,
                MekanismJEI.genericRecipeType(RecipeViewerRecipeType.DECONDENSENTRATING),
                List.of(MekInMachines.ABSOLUTE_OVERCLOCKED_ROTARY_CONDENSENTRATOR));
        CatalystRegistryHelper.register(registration,
                MekanismJEI.genericRecipeType(MekUtRecipeViewerRecipeType.LAZER_COMPRESS),
                List.of(MekInMachines.SUPREME_QUANTUM_LAZER_COMPRESS_NUCLEO_SYNTHESIZER));
        CatalystRegistryHelper.register(registration,
                MekanismJEI.genericRecipeType(MekUtRecipeViewerRecipeType.SMALL_DIGITAL_ASSEMBLER),
                List.of(MekInMachines.ABSOLUTE_OVERCLOCKED_SMALL_DIGITAL_ASSEMBLER));
        CatalystRegistryHelper.register(registration,
                MekanismJEI.genericRecipeType(MekUtRecipeViewerRecipeType.SMALL_DIGITAL_REACTION_CHAMBER),
                List.of(MekInMachines.ABSOLUTE_OVERCLOCKED_SMALL_DIGITAL_REACTION_CAHMBER));
        CatalystRegistryHelper.register(registration,
                MekanismJEI.genericRecipeType(MekUtRecipeViewerRecipeType.SPS),
                List.of(MekInMachines.SUPREME_QUANTUM_SUPERCRITICAL_PHASE_SHIFTER));
        CatalystRegistryHelper.register(registration,
                MekanismJEI.genericRecipeType(RecipeViewerRecipeType.EVAPORATING),
                List.of(MekInMachines.ABSOLUTE_OVERCLOCKED_THERMAL_EVAPORATION_PLANT));
    }

}
