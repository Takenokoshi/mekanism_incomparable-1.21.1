package com.takenokoshi.mekin.recipe.output;

import java.util.List;
import java.util.Objects;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import mekanism.api.SerializationConstants;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;

public record MeteorCollectorRecipeOutput(int weight, List<ItemStack> value) {
    public MeteorCollectorRecipeOutput {
        Objects.requireNonNull(value, "Output value cannot be null.");
        if (weight <= 0) {
            throw new IllegalArgumentException("Output weight must be positive.");
        }
        if (value.isEmpty()) {
            throw new IllegalArgumentException("Output value cannot be empty.");
        }
        if (value.stream().anyMatch(ItemStack::isEmpty)) {
            throw new IllegalArgumentException("Output value cannot contain empty item stacks.");
        }
        value = value.stream()
                .map(ItemStack::copy)
                .toList();
    }

    public int valueSize(){
        return value.size();
    }

    @Override
    public final boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o != null && o.getClass() == this.getClass()) {
            MeteorCollectorRecipeOutput other = (MeteorCollectorRecipeOutput) o;
            return this.weight == other.weight && this.value.equals(other.value);
        } else {
            return false;
        }
    }

    @Override
    public final int hashCode() {
        return weight * 31 + value.hashCode();
    }

    public static final MapCodec<MeteorCollectorRecipeOutput> MAP_CODEC = RecordCodecBuilder
            .<MeteorCollectorRecipeOutput>mapCodec(instance -> instance
                    .group(
                            Codec.INT.fieldOf("weight")
                                    .forGetter(MeteorCollectorRecipeOutput::weight),
                            ItemStack.CODEC.listOf(1, 9).fieldOf(SerializationConstants.OUTPUT)
                                    .forGetter(MeteorCollectorRecipeOutput::value))
                    .apply(instance, MeteorCollectorRecipeOutput::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, MeteorCollectorRecipeOutput> STREAM_CODEC = StreamCodec
            .composite(
                    ByteBufCodecs.VAR_INT,
                    MeteorCollectorRecipeOutput::weight,
                    ItemStack.LIST_STREAM_CODEC,
                    MeteorCollectorRecipeOutput::value,
                    MeteorCollectorRecipeOutput::new);
}
