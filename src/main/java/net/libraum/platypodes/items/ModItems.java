package net.libraum.platypodes.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.libraum.platypodes.Platypodes;
import net.libraum.platypodes.entity.ModEntities;
import net.libraum.platypodes.items.custom.PlatypusBucketItem;
import net.libraum.platypodes.sound.ModSounds;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    /** Items */
    public static final Item PLATYPUS_SPAWN_EGG = registerItem("platypus_spawn_egg",
            new SpawnEggItem(ModEntities.PLATYPUS, 0x4a2e1b, 0x31373e, new FabricItemSettings()));

    public static final Item PLATYPUS_BUCKET = registerItem("platypus_bucket",
            new PlatypusBucketItem(ModEntities.PLATYPUS, Fluids.WATER, ModSounds.ITEM_BUCKET_EMPTY_PLATYPUS, new Item.Settings().maxCount(1)));

    public static final Item YABBY = registerItem("yabby",
            new Item(new FabricItemSettings()));

    /** Item Groups */
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Platypodes.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Platypodes.LOGGER.info("Registering Items for " + Platypodes.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(content -> {
            content.addBefore(Items.POLAR_BEAR_SPAWN_EGG, ModItems.PLATYPUS_SPAWN_EGG);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> {
            content.addAfter(Items.AXOLOTL_BUCKET, ModItems.PLATYPUS_BUCKET);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> {
            content.addAfter(Items.PUFFERFISH, ModItems.YABBY);
        });
    }
}