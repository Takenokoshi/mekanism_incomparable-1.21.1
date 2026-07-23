package com.takenokoshi.mekin.mixin.minecraft;

import java.util.LinkedHashMap;
import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import com.extendedae_plus.ExtendedAEPlus;
import com.google.gson.JsonElement;
import com.jerry.mekextras.MekanismExtras;
import com.takenokoshi.mekut.core.MekUtConstants;

import fr.iglee42.evolvedmekanism.EvolvedMekanism;
import io.github.masyumero.emextras.EMExtras;
import mekanism.common.Mekanism;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;
import net.pedroksl.advanced_ae.AdvancedAE;

@Mixin(value = { RecipeManager.class }, remap = true)
public class RecipeManagerMixin {
    @ModifyVariable(method = "apply", at = @At("HEAD"), argsOnly = true)
    private Map<ResourceLocation, JsonElement> mekanism_incomparable$removeRecipeById(
            Map<ResourceLocation, JsonElement> original) {
        LinkedHashMap<ResourceLocation, JsonElement> result = new LinkedHashMap<>(original);
        // remove default alloy recipe.
        result.remove(Mekanism.rl("metallurgic_infusing/alloy/infused"));
        result.remove(Mekanism.rl("metallurgic_infusing/alloy/reinforced"));
        result.remove(Mekanism.rl("metallurgic_infusing/alloy/atomic"));
        result.remove(MekUtConstants.rl("metallurgic_infusing/elastic_alloy"));
        result.remove(MekUtConstants.rl("metallurgic_infusing/convergent_alloy"));
        result.remove(MekUtConstants.rl("metallurgic_infusing/composite_alloy"));
        result.remove(MekanismExtras.rl("metallurgic_infusing/alloy/radiance"));
        result.remove(MekanismExtras.rl("metallurgic_infusing/alloy/thermonuclear"));
        result.remove(MekanismExtras.rl("metallurgic_infusing/alloy/shining"));
        result.remove(MekanismExtras.rl("metallurgic_infusing/alloy/spectrum"));
        result.remove(EvolvedMekanism.rl("metallurgic_infusing/alloy/hypercharged"));
        result.remove(EvolvedMekanism.rl("nucleosynthesizing/alloy_subatomic"));
        result.remove(EvolvedMekanism.rl("metallurgic_infusing/alloy/singular"));
        result.remove(EvolvedMekanism.rl("metallurgic_infusing/alloy/exoversal"));
        // remove default control circuit recipe.
        result.remove(Mekanism.rl("control_circuit/basic"));
        result.remove(Mekanism.rl("control_circuit/advanced"));
        result.remove(Mekanism.rl("control_circuit/elite"));
        result.remove(Mekanism.rl("control_circuit/ultimate"));
        result.remove(Mekanism.rl("control_circuit/infused_advanced"));
        result.remove(Mekanism.rl("control_circuit/infused_elite"));
        result.remove(Mekanism.rl("control_circuit/infused_ultimate"));
        result.remove(MekUtConstants.rl("crafting/digital_control_circuit"));
        result.remove(MekUtConstants.rl("crafting/standard_control_circuit"));
        result.remove(MekUtConstants.rl("crafting/augment_control_circuit"));
        result.remove(MekanismExtras.rl("control_circuit/absolute"));
        result.remove(MekanismExtras.rl("control_circuit/infused_absolute"));
        result.remove(MekanismExtras.rl("control_circuit/supreme"));
        result.remove(MekanismExtras.rl("control_circuit/infused_supreme"));
        result.remove(MekanismExtras.rl("control_circuit/cosmic"));
        result.remove(MekanismExtras.rl("control_circuit/infused_cosmic"));
        result.remove(MekanismExtras.rl("control_circuit/infinite"));
        result.remove(MekanismExtras.rl("control_circuit/infused_infinite"));
        result.remove(EvolvedMekanism.rl("control_circuit/overclocked"));
        result.remove(EvolvedMekanism.rl("control_circuit/quantum"));
        result.remove(EvolvedMekanism.rl("control_circuit/dense"));
        result.remove(EvolvedMekanism.rl("control_circuit/multiversal"));
        result.remove(EMExtras.rl("alloying/circuit/advanced"));
        result.remove(EMExtras.rl("alloying/circuit/elite"));
        result.remove(EMExtras.rl("alloying/circuit/ultimate"));
        result.remove(EMExtras.rl("alloying/circuit/absolute"));
        result.remove(EMExtras.rl("alloying/circuit/supreme"));
        result.remove(EMExtras.rl("alloying/circuit/cosmic"));
        result.remove(EMExtras.rl("alloying/circuit/infinity"));
        result.remove(EMExtras.rl("alloying/circuit/overclocked"));
        result.remove(EMExtras.rl("alloying/circuit/quantum"));
        result.remove(EMExtras.rl("alloying/circuit/dense"));
        result.remove(EMExtras.rl("alloying/circuit/multiversal"));

        // remove processing recipes
        for (String name : new String[] { "gold", "iron", "osmium", "copper", "tin", "uranium", "lead" }) {
            result.remove(Mekanism.rl("processing/" + name + "/clump/from_ore"));
            result.remove(Mekanism.rl("processing/" + name + "/clump/from_raw_block"));
            result.remove(Mekanism.rl("processing/" + name + "/clump/from_raw_ore"));
            result.remove(Mekanism.rl("processing/" + name + "/clump/from_shard"));
            result.remove(Mekanism.rl("processing/" + name + "/dust/from_ore"));
            result.remove(Mekanism.rl("processing/" + name + "/dust/from_raw_block"));
            result.remove(Mekanism.rl("processing/" + name + "/dust/from_raw_ore"));
            result.remove(Mekanism.rl("processing/" + name + "/shard/from_ore"));
            result.remove(Mekanism.rl("processing/" + name + "/shard/from_raw_block"));
            result.remove(Mekanism.rl("processing/" + name + "/shard/from_raw_ore"));
            result.remove(Mekanism.rl("processing/" + name + "/slurry/clean"));
            result.remove(Mekanism.rl("processing/" + name + "/slurry/dirty/from_ore"));
            result.remove(Mekanism.rl("processing/" + name + "/slurry/dirty/from_raw_block"));
            result.remove(Mekanism.rl("processing/" + name + "/slurry/dirty/from_raw_ore"));
        }
        for (String name : new String[] { "amethyst", "certus_quartz", "coal", "diamond", "emerald", "entro",
                "fluorite", "lapis_lazuli", "quartz", "redstone", "netherite" }) {
            result.remove(MekUtConstants.rl("processing/" + name + "/clump/from_raw"));
            result.remove(MekUtConstants.rl("processing/" + name + "/clump/from_raw_block"));
            result.remove(MekUtConstants.rl("processing/" + name + "/clump/from_shard"));
            result.remove(MekUtConstants.rl("processing/" + name + "/clean_slurry"));
            result.remove(MekUtConstants.rl("processing/" + name + "/dirty_slurry/from_raw"));
            result.remove(MekUtConstants.rl("processing/" + name + "/dirty_slurry/from_raw_block"));
            result.remove(MekUtConstants.rl("processing/" + name + "/gem/from_raw"));
            result.remove(MekUtConstants.rl("processing/" + name + "/gem/from_raw_block"));
            result.remove(MekUtConstants.rl("processing/" + name + "/dust/from_raw"));
            result.remove(MekUtConstants.rl("processing/" + name + "/dust/from_raw_block"));
            result.remove(MekUtConstants.rl("processing/" + name + "/shard/from_raw"));
            result.remove(MekUtConstants.rl("processing/" + name + "/shard/from_raw_block"));
        }
        result.remove(MekanismExtras.rl("processing/" + "naquadah" + "/clump/from_ore"));
        result.remove(MekanismExtras.rl("processing/" + "naquadah" + "/clump/from_raw_block"));
        result.remove(MekanismExtras.rl("processing/" + "naquadah" + "/clump/from_raw_ore"));
        result.remove(MekanismExtras.rl("processing/" + "naquadah" + "/clump/from_shard"));
        result.remove(MekanismExtras.rl("processing/" + "naquadah" + "/dust/from_ore"));
        result.remove(MekanismExtras.rl("processing/" + "naquadah" + "/dust/from_raw_block"));
        result.remove(MekanismExtras.rl("processing/" + "naquadah" + "/dust/from_raw_ore"));
        result.remove(MekanismExtras.rl("processing/" + "naquadah" + "/shard/from_ore"));
        result.remove(MekanismExtras.rl("processing/" + "naquadah" + "/shard/from_raw_block"));
        result.remove(MekanismExtras.rl("processing/" + "naquadah" + "/shard/from_raw_ore"));
        result.remove(MekanismExtras.rl("processing/" + "naquadah" + "/slurry/clean"));
        result.remove(MekanismExtras.rl("processing/" + "naquadah" + "/slurry/dirty/from_ore"));
        result.remove(MekanismExtras.rl("processing/" + "naquadah" + "/slurry/dirty/from_raw_block"));
        result.remove(MekanismExtras.rl("processing/" + "naquadah" + "/slurry/dirty/from_raw_ore"));

        // remove EAEP Item Recipes
        result.remove(ExtendedAEPlus.id("basic_core"));
        result.remove(ExtendedAEPlus.id("core/compat/infinity_core_3"));
        result.remove(ExtendedAEPlus.id("transform/oblivion_singularity"));
        result.remove(AdvancedAE.makeId("oblivion_singularity"));
        return result;
    }
}
