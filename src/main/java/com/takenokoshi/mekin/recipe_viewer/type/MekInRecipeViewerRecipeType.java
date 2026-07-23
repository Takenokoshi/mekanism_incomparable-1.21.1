package com.takenokoshi.mekin.recipe_viewer.type;

import com.takenokoshi.mekaddonlib.recipe_viewer.type.RVMekALRecipeTypeWrapper;
import com.takenokoshi.mekin.core.MekInConstants;
import com.takenokoshi.mekin.recipe.MekInRecipeConstants;
import com.takenokoshi.mekin.recipe.inputcache.MekInDoubleInputRecipeCache;
import com.takenokoshi.mekin.recipe.inputcache.MekInTripleInputRecipeCache;
import com.takenokoshi.mekin.recipe.recipes.prefab.FluidChemicalToBiChemicalRecipe;
import com.takenokoshi.mekin.recipe.recipes.prefab.ItemStackChemicalToChemicalRecipe;
import com.takenokoshi.mekin.recipe.recipes.prefab.ItemStackFluidChemicalToItemStackRecipe;
import com.takenokoshi.mekin.recipe_viewer.recipe.FluxCondenserRVRecipe;
import com.takenokoshi.mekin.registries.MekInMachines;
import com.takenokoshi.mekin.registries.MekInRecipeTypes;
import com.takenokoshi.mekut.recipe.inputcache.MekUtDoubleInputRecipeCache.MekUtItemChemical;
import mekanism.api.recipes.ItemStackChemicalToItemStackRecipe;
import mekanism.api.recipes.vanilla_input.ReactionRecipeInput;
import mekanism.api.recipes.vanilla_input.SingleFluidChemicalRecipeInput;
import mekanism.api.recipes.vanilla_input.SingleItemChemicalRecipeInput;
import mekanism.client.recipe_viewer.type.FakeRVRecipeType;

public class MekInRecipeViewerRecipeType {

    public static final RVMekALRecipeTypeWrapper<SingleItemChemicalRecipeInput, ItemStackChemicalToChemicalRecipe, MekInDoubleInputRecipeCache.MekInItemChemical<ItemStackChemicalToChemicalRecipe>> AET = new RVMekALRecipeTypeWrapper<>(
            MekInConstants.rl(MekInRecipeConstants.AET),
            ItemStackChemicalToChemicalRecipe.class,
            MekInRecipeTypes.AET, -3, -3, 170, 79,
            MekInMachines.ANTINEUTRONIC_EXISTENCE_TRANSMUTATOR);

    public static final RVMekALRecipeTypeWrapper<SingleFluidChemicalRecipeInput, FluidChemicalToBiChemicalRecipe, MekInDoubleInputRecipeCache.MekInFluidChemical<FluidChemicalToBiChemicalRecipe>> CHEMICAL_EXTRACTION = new RVMekALRecipeTypeWrapper<>(
            MekInConstants.rl(MekInRecipeConstants.CHEMICAL_EXTRACTION),
            FluidChemicalToBiChemicalRecipe.class,
            MekInRecipeTypes.CHEMICAL_EXTRACTION, -7, -13, 162, 60,
            MekInMachines.CHEMICAL_EXTRACTOR);

    public static final RVMekALRecipeTypeWrapper<SingleItemChemicalRecipeInput, ItemStackChemicalToItemStackRecipe, MekUtItemChemical<ItemStackChemicalToItemStackRecipe>> REFINING = new RVMekALRecipeTypeWrapper<>(
            MekInConstants.rl(MekInRecipeConstants.REFINING),
            ItemStackChemicalToItemStackRecipe.class,
            MekInRecipeTypes.REFINING, -28, -16, 144, 54,
            MekInMachines.CHEMICAL_REFINER);

    public static final RVMekALRecipeTypeWrapper<ReactionRecipeInput, ItemStackFluidChemicalToItemStackRecipe, MekInTripleInputRecipeCache.MekInItemFluidChemical<ItemStackFluidChemicalToItemStackRecipe>> LEACHING = new RVMekALRecipeTypeWrapper<>(
            MekInConstants.rl(MekInRecipeConstants.LEACHING),
            ItemStackFluidChemicalToItemStackRecipe.class,
            MekInRecipeTypes.LEACHING, -3, -15, 170, 60,
            MekInMachines.CHEMICAL_LEACHING_CHAMBER);

    public static final FakeRVRecipeType<FluxCondenserRVRecipe> FLUX_CONDENSER = new FakeRVRecipeType<>(
            MekInConstants.rl("flux_condenser_rv"),
            MekInMachines.FLUX_CONDENSER,
            MekInMachines.FLUX_CONDENSER,
            FluxCondenserRVRecipe.class, -3, -12, 170, 64,
            true);

    public static final RVMekALRecipeTypeWrapper<SingleItemChemicalRecipeInput, ItemStackChemicalToItemStackRecipe, MekUtItemChemical<ItemStackChemicalToItemStackRecipe>> TEPS = new RVMekALRecipeTypeWrapper<>(
            MekInConstants.rl(MekInRecipeConstants.TEPS),
            ItemStackChemicalToItemStackRecipe.class,
            MekInRecipeTypes.TEPS, -3, -12, 168, 74,
            MekInMachines.TEPS);

}
