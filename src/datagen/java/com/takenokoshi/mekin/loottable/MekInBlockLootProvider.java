package com.takenokoshi.mekin.loottable;

import java.util.List;

import com.takenokoshi.mekin.registries.MekInBlocks;
import com.takenokoshi.mekin.registries.MekInMachines;

import mekanism.common.registration.impl.BlockRegistryObject;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

//BaseBlockLootTables is copy of https://github.com/mekanism/Mekanism/blob/1.21.x/src/datagen/main/java/mekanism/common/loot/table/BaseBlockLootTables.java
public class MekInBlockLootProvider extends BaseBlockLootTables {

    protected MekInBlockLootProvider(Provider registries) {
        super(registries);
    }

    @Override
    protected void generate() {

        dropSelfWithContents(MekInMachines.MACHINES.blockRegister.getPrimaryEntries());
        List.of(new BlockRegistryObject[] {
                MekInBlocks.ABSOLUTE_CONTROL_MATRIX,
                MekInBlocks.ADVANCED_CONTROL_MATRIX,
                MekInBlocks.AUGMENT_CONTROL_MATRIX,
                MekInBlocks.BASIC_CONTROL_MATRIX,
                MekInBlocks.COSMIC_CONTROL_MATRIX,
                MekInBlocks.DENSE_CONTROL_MATRIX,
                MekInBlocks.DIGITAL_CONTROL_MATRIX,
                MekInBlocks.ELITE_CONTROL_MATRIX,
                MekInBlocks.INFINITE_CONTROL_MATRIX,
                MekInBlocks.MULTIVERSAL_CONTROL_MATRIX,
                MekInBlocks.OVERCLOCKED_CONTROL_MATRIX,
                MekInBlocks.QUANTUM_CONTROL_MATRIX,
                MekInBlocks.STANDARD_CONTROL_MATRIX,
                MekInBlocks.SUPREME_CONTROL_MATRIX,
                MekInBlocks.ULTIMATE_CONTROL_MATRIX,
        }).forEach(this::dropSelf);

    }

    protected void dropSelf(BlockRegistryObject<?, ?> block) {
        this.dropSelf(block.get());
    }

    protected void createOreDrop(Block block, Item item, int amount) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        add(block, this.createSilkTouchDispatchTable(block,
                this.applyExplosionDecay(block, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(amount)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE))))));
    }

    protected void createOreDrop(Block block, Item item, int min, int max) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        add(block, this.createSilkTouchDispatchTable(block,
                this.applyExplosionDecay(block, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE))))));
    }

    protected void createRedstoneOreDrops(Block block, Item item) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        add(block, this.createSilkTouchDispatchTable(block,
                this.applyExplosionDecay(block, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 5.0F)))
                        .apply(ApplyBonusCount
                                .addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE))))));
    }

}