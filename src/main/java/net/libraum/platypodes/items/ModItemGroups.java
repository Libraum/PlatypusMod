package net.libraum.platypodes.items;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.libraum.platypodes.Platypodes;
import net.libraum.platypodes.blocks.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;


public class ModItemGroups {
    public static final ItemGroup PLATYPUS_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Platypodes.MOD_ID,"platypodes"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.platypodes"))
                    .icon(() -> new ItemStack(ModItems.PLATYPUS_BUCKET)).entries((displayContext, entries) -> {
                        entries.add(ModItems.PLATYPUS_SPAWN_EGG);
                        entries.add(ModItems.PLATYPUS_BUCKET);
                        entries.add(ModItems.YABBY);

                        entries.add(ModBlocks.PLATYPUS_BURROW);
                    }).build());
    
    public static void registerItemGroups() {
        Platypodes.LOGGER.info("Registering Item Groups for " + Platypodes.MOD_ID);
    }
}
