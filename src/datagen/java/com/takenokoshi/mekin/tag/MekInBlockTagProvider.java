package com.takenokoshi.mekin.tag;

import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.Nullable;

import com.takenokoshi.mekin.core.MekInConstants;
import com.takenokoshi.mekin.registries.MekInAbsoluteMachines;
import com.takenokoshi.mekin.registries.MekInBlocks;
import com.takenokoshi.mekin.registries.MekInInfiniteMachines;
import com.takenokoshi.mekin.registries.MekInMachines;
import com.takenokoshi.mekin.registries.MekInSupremeMachines;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;

public class MekInBlockTagProvider extends BlockTagsProvider {

    public MekInBlockTagProvider(PackOutput output, CompletableFuture<Provider> lookupProvider,
            @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MekInConstants.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(MekInMachines.MACHINES.blockRegister.getPrimaryEntries().stream().map(DeferredHolder::get)
                        .toArray(Block[]::new))
                .add(MekInAbsoluteMachines.MACHINES.blockRegister.getPrimaryEntries().stream().map(DeferredHolder::get)
                        .toArray(Block[]::new))
                .add(MekInSupremeMachines.MACHINES.blockRegister.getPrimaryEntries().stream().map(DeferredHolder::get)
                        .toArray(Block[]::new))
                .add(MekInInfiniteMachines.MACHINES.blockRegister.getPrimaryEntries().stream().map(DeferredHolder::get)
                        .toArray(Block[]::new))
                .add(MekInBlocks.BLOCKS.getPrimaryEntries().stream().map(DeferredHolder::get)
                        .toArray(Block[]::new));
    }

}
