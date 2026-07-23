package com.takenokoshi.mekin.recipe_viewer.recipe;

import java.util.Collections;
import java.util.List;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.takenokoshi.mekin.blockentity.machine.BEFluxCondenser;
import com.takenokoshi.mekin.core.MekInConstants;
import com.takenokoshi.mekin.registries.MekInFluids;

import mekanism.api.SerializationConstants;
import mekanism.client.recipe_viewer.emi.INamedRVRecipe;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.fluids.FluidStack;

public record FluxCondenserRVRecipe(ResourceLocation id, long inputEnergy, FluidStack output)
        implements INamedRVRecipe {
    public static final Codec<FluxCondenserRVRecipe> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceLocation.CODEC.fieldOf(SerializationConstants.ID).forGetter(FluxCondenserRVRecipe::id),
            Codec.LONG.fieldOf(SerializationConstants.INPUT).forGetter(FluxCondenserRVRecipe::inputEnergy),
            FluidStack.CODEC.fieldOf(SerializationConstants.OUTPUT).forGetter(FluxCondenserRVRecipe::output))
            .apply(instance, FluxCondenserRVRecipe::new));

    public static List<FluxCondenserRVRecipe> getFluxCondenserRecipes() {
        return Collections.singletonList(new FluxCondenserRVRecipe(
                MekInConstants.rl("/flux_condenser"),
                BEFluxCondenser.ENERGY_PER_FLUX,
                MekInFluids.FLUX.asStack(1)));
    }
}
