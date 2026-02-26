package net.libraum.platypodes.blocks;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.libraum.platypodes.Platypodes;
import net.libraum.platypodes.blocks.custom.PlatypusBurrowBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block PLATYPUS_BURROW = registerBlock("platypus_burrow",
            new PlatypusBurrowBlock(FabricBlockSettings.copyOf(Blocks.DIRT)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Platypodes.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(Platypodes.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        Platypodes.LOGGER.info("Registering Blocks for " + Platypodes.MOD_ID);
    }
}
