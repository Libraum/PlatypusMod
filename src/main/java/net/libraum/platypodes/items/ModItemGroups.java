package net.libraum.platypodes.items;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.libraum.platypodes.Platypodes;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;


public class ModItemGroups {
    public static final CreativeModeTab PLATYPUS_GROUP = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            ResourceLocation.tryBuild(Platypodes.MOD_ID,"platypodes"),
            FabricItemGroup.builder().title(Component.translatable("itemgroup.platypodes"))
                    .icon(() -> new ItemStack(ModItems.PLATYPUS_BUCKET)).displayItems((displayContext, entries) -> {
                        entries.accept(ModItems.PLATYPUS_SPAWN_EGG);
                        entries.accept(ModItems.PLATYPUS_BUCKET);
                        entries.accept(ModItems.YABBY);
                    }).build());
    
    public static void registerItemGroups() {
        Platypodes.LOGGER.info("Registering Item Groups for " + Platypodes.MOD_ID);
    }
}
