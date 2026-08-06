package com.takenokoshi.mekin.mixin.mekmm;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.jerry.mekmm.common.util.MoreMachineUtils;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;

import fr.iglee42.evolvedmekanism.interfaces.InitializableEnum;
import fr.iglee42.evolvedmekanism.tiers.EMFactoryTier;
import mekanism.common.tier.FactoryTier;

@Mixin(value = { MoreMachineUtils.class }, remap = false)
public class MoreMachineUtilsMixin {

    @ModifyReturnValue(method = "getFactoryTier", at = @At("RETURN"))
    private static FactoryTier[] mekanism_incomparable$removeNullFactoryTier(FactoryTier[] original) {
        if (EMFactoryTier.OVERCLOCKED == null) {
            InitializableEnum initializableEnum = (InitializableEnum) (Object) FactoryTier.BASIC;
            initializableEnum.evolvedmekanism$initNewValues();
        }
        return Set.copyOf(Stream.concat(
                Arrays.stream(original).filter(Objects::nonNull),
                Arrays.stream(new FactoryTier[] {
                        FactoryTier.BASIC,
                        FactoryTier.ADVANCED,
                        FactoryTier.ELITE,
                        FactoryTier.ULTIMATE,
                        EMFactoryTier.OVERCLOCKED,
                        EMFactoryTier.QUANTUM,
                        EMFactoryTier.DENSE,
                        EMFactoryTier.MULTIVERSAL,
                        EMFactoryTier.CREATIVE,
                }).filter(Objects::nonNull)// 
        ).toList()).toArray(FactoryTier[]::new);
    }
}
