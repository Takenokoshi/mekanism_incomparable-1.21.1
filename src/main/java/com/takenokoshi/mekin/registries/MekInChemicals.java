package com.takenokoshi.mekin.registries;

import com.moakiee.ae2lt.AE2LightningTech;
import com.takenokoshi.mekin.core.MekInConstants;

import mekanism.common.Mekanism;
import mekanism.common.registration.impl.ChemicalDeferredRegister;
import mekanism.common.registration.impl.DeferredChemical;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;

public class MekInChemicals {
    public static final ChemicalDeferredRegister CHEMICALS = new ChemicalDeferredRegister(MekInConstants.MODID);

    public static final DeferredChemical<?> NULL = CHEMICALS
            .register("null", MissingTextureAtlasSprite.getLocation(), 0xA464B3);

    public static final DeferredChemical<?> COSMOS_ETHER = CHEMICALS
            .register("cosmos_ether", 0x050816);

    public static final DeferredChemical<?> THUNDERCLOUD = CHEMICALS
            .registerInfuse("thundercloud", 0x40444C);

    public static final DeferredChemical<?> EXTREME_HIGH_VOLTAGE_LIGHTNING = CHEMICALS
            .register("extreme_high_voltage_lightning",
                    ResourceLocation.fromNamespaceAndPath(AE2LightningTech.MODID,
                            "item/extreme_high_voltage_lightning"),
                    0xFFE0EF);
    public static final DeferredChemical<?> HIGH_VOLTAGE_LIGHTNING = CHEMICALS
            .register("high_voltage_lightning",
                    ResourceLocation.fromNamespaceAndPath(AE2LightningTech.MODID,
                            "item/high_voltage_lightning"),
                    0xE8FBFF);

    public static final DeferredChemical<?> POTASSIUM_HYPOCHLORITE = CHEMICALS
            .register("potassium_hypochlorite", 0xFFA8E57A);
    public static final DeferredChemical<?> POTASSIUM_CHLORATE = CHEMICALS.register("potassium_chlorate", 0xFFC1F4D6);

    public static final DeferredChemical<?> SCARLET_SILVER = CHEMICALS.registerInfuse("scarlet_silver", 0xf56767);
    public static final DeferredChemical<?> ELECTRINE = CHEMICALS.registerInfuse("electrine", 0xF3BD97);
    public static final DeferredChemical<?> OBLIVION_SINGULARITY = CHEMICALS
            .registerInfuse("oblivion_singularity", 0x0E0231);

    public static final DeferredChemical<?> CLEAN_OVERLOAD_SLURRY = CHEMICALS
            .register("clean_overload_slurry", Mekanism.rl("slurry/clean"), 0xFFFFA8FD);
    public static final DeferredChemical<?> DIRTY_OVERLOAD_SLURRY = CHEMICALS
            .register("dirty_overload_slurry", Mekanism.rl("slurry/dirty"), 0xFFFFA8FD);

    public static final DeferredChemical<?> IRON = CHEMICALS.registerInfuse("iron", 0xFFAF8FB7);
    public static final DeferredChemical<?> COPPER = CHEMICALS.registerInfuse("copper", 0xFFAA4B19);
    public static final DeferredChemical<?> NAQUADAH = CHEMICALS.registerInfuse("naquadah", 0xFF051602);
    public static final DeferredChemical<?> AMETHYST = CHEMICALS.registerInfuse("amethyst", 0xFFA361FF);
    public static final DeferredChemical<?> CERTUS_QUARTZ = CHEMICALS.registerInfuse("certus_quartz", 0xFFC9F2FF);
    public static final DeferredChemical<?> ENTRO = CHEMICALS.registerInfuse("entro", 0xFF03B99A);
    public static final DeferredChemical<?> FLUORITE = CHEMICALS.registerInfuse("fluorite", 0xFF78FFBE);
    public static final DeferredChemical<?> OVERLOAD = CHEMICALS.registerInfuse("overload", 0xFFFFA8FD);
    public static final DeferredChemical<?> SILICON = CHEMICALS.registerInfuse("silicon", 0xFFDEDEDE);
    public static final DeferredChemical<?> COAL = CHEMICALS.registerInfuse("coal", 0xFF2D2D2D);
    public static final DeferredChemical<?> EMERALD = CHEMICALS.registerInfuse("emerald", 0xFF11C95A);
    public static final DeferredChemical<?> LAPIS_LAZULI = CHEMICALS.registerInfuse("lapis_lazuli", 0xFF2661DB);
    public static final DeferredChemical<?> QUARTZ = CHEMICALS.registerInfuse("quartz", 0xFFF5E6DC);
    public static final DeferredChemical<?> TITANIUM = CHEMICALS.register("titanium", 0xFF4C445A);
    public static final DeferredChemical<?> SILVER = CHEMICALS.register("silver", 0xFFE5E5E5);

    public static final DeferredChemical<?> AMETHYST_PLASMA = CHEMICALS.register("amethyst_plasma", 0xFFA361FF);
    public static final DeferredChemical<?> CERTUS_QUARTZ_PLASMA = CHEMICALS
            .register("certus_quartz_plasma", 0xFFC9F2FF);
    public static final DeferredChemical<?> COAL_PLASMA = CHEMICALS.register("coal_plasma", 0xFF2D2D2D);
    public static final DeferredChemical<?> COPPER_PLASMA = CHEMICALS.register("copper_plasma", 0xFFAA4B19);
    public static final DeferredChemical<?> DIAMOND_PLASMA = CHEMICALS.register("diamond_plasma", 0xFF5CDBD5);
    public static final DeferredChemical<?> EMERALD_PLASMA = CHEMICALS.register("emerald_plasma", 0xFF11C95A);
    public static final DeferredChemical<?> ENTRO_PLASMA = CHEMICALS.register("entro_plasma", 0xFF03B99A);
    public static final DeferredChemical<?> FLUORITE_PLASMA = CHEMICALS.register("fluorite_plasma", 0xFF78FFBE);
    public static final DeferredChemical<?> GOLD_PLASMA = CHEMICALS.register("gold_plasma", 0xFFF2CD67);
    public static final DeferredChemical<?> IRON_PLASMA = CHEMICALS.register("iron_plasma", 0xFFAF8FB7);
    public static final DeferredChemical<?> LAPIS_LAZULI_PLASMA = CHEMICALS.register("lapis_lazuli_plasma", 0xFF2661DB);
    public static final DeferredChemical<?> LEAD_PLASMA = CHEMICALS.register("lead_plasma", 0xFFAA4B19);
    public static final DeferredChemical<?> NAQUADAH_PLASMA = CHEMICALS.register("naquadah_plasma", 0xFF051602);
    public static final DeferredChemical<?> NETHERITE_PLASMA = CHEMICALS.register("netherite_plasma", 0xFF433D47);
    public static final DeferredChemical<?> OSMIUM_PLASMA = CHEMICALS.register("osmium_plasma", 0xFF52BDCA);
    public static final DeferredChemical<?> OVERLOAD_PLASMA = CHEMICALS.register("overload_plasma", 0xFFFFA8FD);
    public static final DeferredChemical<?> SILICON_PLASMA = CHEMICALS.register("silicon_plasma", 0xFFDEDEDE);
    public static final DeferredChemical<?> QUARTZ_PLASMA = CHEMICALS.register("quartz_plasma", 0xFFF5E6DC);
    public static final DeferredChemical<?> REDSTONE_PLASMA = CHEMICALS.register("redstone_plasma", 0xFFB30505);
    public static final DeferredChemical<?> TIN_PLASMA = CHEMICALS.register("tin_plasma", 0xFFCCCCD9);
    public static final DeferredChemical<?> URANIUM_PLASMA = CHEMICALS.register("uranium_plasma", 0xFF9BE199);
}
