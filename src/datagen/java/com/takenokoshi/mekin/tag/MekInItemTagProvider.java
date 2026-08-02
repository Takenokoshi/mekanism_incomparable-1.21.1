package com.takenokoshi.mekin.tag;

import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.Nullable;

import com.takenokoshi.mekin.core.MekInConstants;
import com.takenokoshi.mekin.recipe.MekInMaterial;
import com.takenokoshi.mekin.registries.MekInItems;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class MekInItemTagProvider extends ItemTagsProvider {

    public MekInItemTagProvider(PackOutput output,
            CompletableFuture<Provider> lookupProvider,
            CompletableFuture<TagLookup<Item>> parentProvider,
            CompletableFuture<TagLookup<Block>> blockTags,
            @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, parentProvider, blockTags, MekInConstants.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(Provider provider) {
        tag(MekInMaterial.OVERLOAD.rawTag())
                .add(MekInItems.RAW_OVERLOAD.get());
        tag(MekInMaterial.OVERLOAD.clumpTag())
                .add(MekInItems.OVERLOAD_CLUMP.get());
        tag(MekInMaterial.OVERLOAD.crystalTag())
                .add(MekInItems.OVERLOAD_CRYSTAL.get());
        tag(MekInMaterial.OVERLOAD.shardTag())
                .add(MekInItems.OVERLOAD_SHARD.get());
    }

}
