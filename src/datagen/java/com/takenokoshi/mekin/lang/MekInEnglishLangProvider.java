package com.takenokoshi.mekin.lang;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.takenokoshi.mekin.core.MekInConstants;
import com.takenokoshi.mekin.registries.MekInBlocks;
import com.takenokoshi.mekin.registries.MekInChemicals;
import com.takenokoshi.mekin.registries.MekInFluids;
import com.takenokoshi.mekin.registries.MekInItems;
import com.takenokoshi.mekin.registries.MekInMachines;
import com.takenokoshi.mekut.registries.MekUtFluids;

import mekanism.api.text.IHasTranslationKey;
import mekanism.common.registration.impl.DeferredChemical;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class MekInEnglishLangProvider extends LanguageProvider {

    public MekInEnglishLangProvider(PackOutput output) {
        super(output, MekInConstants.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        MekInItems.ITEMS.getEntries().forEach(holder -> {
            add(holder.get(), format(holder.getId().getPath()));
        });
        MekInBlocks.BLOCKS.getPrimaryEntries().forEach(holder -> {
            add(holder.get(), format(holder.getId().getPath()));
        });
        MekInFluids.FLUIDS.getBlockEntries().forEach(holder -> {
            add(holder.get(), format(holder.getId().getPath()));
        });
        MekUtFluids.FLUIDS.getBucketEntries().forEach(holder -> {
            add(holder.get(), format(holder.getId().getPath()));
        });
        MekUtFluids.FLUIDS.getFluidEntries().forEach(holder -> {
            add("fluid.mekanism_incomparable." + holder.getId().getPath(), format(holder.getId().getPath()));
        });
        MekInMachines.MACHINES.getMachines().forEach(machine -> {
            add(machine.getBlock().get(), format(machine.getBlock().getId().getPath()));
            add("container.mekanism_incomparable." + machine.getBlock().getId().getPath(),
                    format(machine.getBlock().getId().getPath()));
        });
        add("creative_tab.mekanism_incomparable.main", "Mekanism: Incomparable - Prototype");
        List.of(new DeferredChemical[] {
                MekInChemicals.NULL,
                MekInChemicals.AMETHYST_PLASMA,
                MekInChemicals.CERTUS_QUARTZ_PLASMA,
                MekInChemicals.COAL_PLASMA,
                MekInChemicals.COPPER_PLASMA,
                MekInChemicals.DIAMOND_PLASMA,
                MekInChemicals.EMERALD_PLASMA,
                MekInChemicals.ENTRO_PLASMA,
                MekInChemicals.FLUORITE_PLASMA,
                MekInChemicals.GOLD_PLASMA,
                MekInChemicals.IRON_PLASMA,
                MekInChemicals.LAPIS_LAZULI_PLASMA,
                MekInChemicals.LEAD_PLASMA,
                MekInChemicals.NAQUADAH_PLASMA,
                MekInChemicals.NETHERITE_PLASMA,
                MekInChemicals.OSMIUM_PLASMA,
                MekInChemicals.QUARTZ_PLASMA,
                MekInChemicals.REDSTONE_PLASMA,
                MekInChemicals.TIN_PLASMA,
                MekInChemicals.URANIUM_PLASMA,
                MekInChemicals.POTASSIUM_HYPOCHLORITE,
                MekInChemicals.POTASSIUM_CHLORATE,
                MekInChemicals.AMETHYST,
                MekInChemicals.CERTUS_QUARTZ,
                MekInChemicals.COAL,
                MekInChemicals.COPPER,
                MekInChemicals.EMERALD,
                MekInChemicals.ENTRO,
                MekInChemicals.FLUORITE,
                MekInChemicals.IRON,
                MekInChemicals.LAPIS_LAZULI,
                MekInChemicals.NAQUADAH,
                MekInChemicals.QUARTZ,
                MekInChemicals.TITANIUM,
                MekInChemicals.SILVER,
                MekInChemicals.ELECTRINE,
                MekInChemicals.SCARLET_SILVER,
                MekInChemicals.OBLIVION_SINGULARITY,
        }).forEach(this::addChemical);

        addLang(MekInMachines.CHEMICAL_EXTRACTOR.descriptionEntry,
                "Uses water to extract purified slurry and gaseous byproducts from dirty slurry.");
        addLang(MekInMachines.CHEMICAL_LEACHING_CHAMBER.descriptionEntry,
                "Leaches raw ores with lava and aqua regia, producing dirty crystals.");
        addLang(MekInMachines.CHEMICAL_REFINER.descriptionEntry,
                "Uses potassium chlorate to refine crystals into their final materials.");
        addLang(MekInMachines.COMPACT_ANTIMATTER_PROTOMOLECULAR_TRANSMUTATOR.descriptionEntry,
                "Antimatter Protomolecular Transmutator in a single block size");
        addLang(MekInMachines.FLUX_CONDENSER.descriptionEntry,
                "Condense Energy To Flux");
        addLang(MekInMachines.TEPS.descriptionEntry,
                "WIP");
    }

    private static String format(String name) {

        String[] split = name.split("_");

        return Arrays.stream(split)
                .map(s -> Character.toUpperCase(s.charAt(0)) + s.substring(1))
                .collect(Collectors.joining(" "));
    }

    private void addChemical(DeferredChemical<?> chemical) {
        add(chemical.getTranslationKey(), format(chemical.getId().getPath()));
    }

    private void addLang(IHasTranslationKey langEntry, String translation) {
        add(langEntry.getTranslationKey(), translation);
    }

}
