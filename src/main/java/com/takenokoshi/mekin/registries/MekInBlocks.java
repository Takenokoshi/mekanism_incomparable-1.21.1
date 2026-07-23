package com.takenokoshi.mekin.registries;

import com.takenokoshi.mekin.core.MekInConstants;

import mekanism.common.registration.impl.BlockDeferredRegister;
import mekanism.common.registration.impl.BlockRegistryObject;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class MekInBlocks {
    public static final BlockDeferredRegister BLOCKS = new BlockDeferredRegister(MekInConstants.MODID);

    public static final BlockRegistryObject<Block, BlockItem> BASIC_CONTROL_MATRIX = BLOCKS
            .register("basic_control_matrix", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN));

    public static final BlockRegistryObject<Block, BlockItem> ADVANCED_CONTROL_MATRIX = BLOCKS
            .register("advanced_control_matrix", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED));

    public static final BlockRegistryObject<Block, BlockItem> ELITE_CONTROL_MATRIX = BLOCKS
            .register("elite_control_matrix", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE));

    public static final BlockRegistryObject<Block, BlockItem> ULTIMATE_CONTROL_MATRIX = BLOCKS
            .register("ultimate_control_matrix", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE));

    public static final BlockRegistryObject<Block, BlockItem> DIGITAL_CONTROL_MATRIX = BLOCKS
            .register("digital_control_matrix", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_MAGENTA));

    public static final BlockRegistryObject<Block, BlockItem> STANDARD_CONTROL_MATRIX = BLOCKS
            .register("standard_control_matrix", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE));

    public static final BlockRegistryObject<Block, BlockItem> AUGMENT_CONTROL_MATRIX = BLOCKS
            .register("augment_control_matrix", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN));

    public static final BlockRegistryObject<Block, BlockItem> ABSOLUTE_CONTROL_MATRIX = BLOCKS
            .register("absolute_control_matrix", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW));

    public static final BlockRegistryObject<Block, BlockItem> SUPREME_CONTROL_MATRIX = BLOCKS
            .register("supreme_control_matrix", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED));

    public static final BlockRegistryObject<Block, BlockItem> COSMIC_CONTROL_MATRIX = BLOCKS
            .register("cosmic_control_matrix", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE));

    public static final BlockRegistryObject<Block, BlockItem> INFINITE_CONTROL_MATRIX = BLOCKS
            .register("infinite_control_matrix", BlockBehaviour.Properties.of().mapColor(MapColor.NONE));

    public static final BlockRegistryObject<Block, BlockItem> OVERCLOCKED_CONTROL_MATRIX = BLOCKS
            .register("overclocked_control_matrix", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN));

    public static final BlockRegistryObject<Block, BlockItem> QUANTUM_CONTROL_MATRIX = BLOCKS
            .register("quantum_control_matrix", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE));

    public static final BlockRegistryObject<Block, BlockItem> DENSE_CONTROL_MATRIX = BLOCKS
            .register("dense_control_matrix", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW));

    public static final BlockRegistryObject<Block, BlockItem> MULTIVERSAL_CONTROL_MATRIX = BLOCKS
            .register("multiversal_control_matrix", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK));

    public static final BlockRegistryObject<Block, BlockItem> SPECTRUM_ALLOY_CASING = BLOCKS
            .register("spectrum_alloy_casing", BlockBehaviour.Properties.of().mapColor(MapColor.NONE));

    public static final BlockRegistryObject<Block, BlockItem> EXOVERSAL_ALLOY_CASING = BLOCKS
            .register("exoversal_alloy_casing", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK));

    public static final BlockRegistryObject<Block, BlockItem> TITANIUM_CASING = BLOCKS
            .register("titanium_casing", BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE));

    

}
