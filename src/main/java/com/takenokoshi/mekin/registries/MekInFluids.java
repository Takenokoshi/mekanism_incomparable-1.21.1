package com.takenokoshi.mekin.registries;

import com.takenokoshi.mekin.core.MekInConstants;

import mekanism.common.Mekanism;
import mekanism.common.registration.impl.FluidDeferredRegister;
import mekanism.common.registration.impl.FluidRegistryObject;

public class MekInFluids {
    public static final FluidDeferredRegister FLUIDS = new FluidDeferredRegister(MekInConstants.MODID);

    public static final FluidRegistryObject<?, ?, ?, ?, ?> FLUX = FLUIDS.register("flux",
            props -> props.density(1).canSwim(false),
            rProps -> rProps.texture(Mekanism.rl("liquid/energy"), Mekanism.rl("liquid/energy")));
}
