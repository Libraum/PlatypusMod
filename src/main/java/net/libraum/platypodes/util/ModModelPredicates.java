package net.libraum.platypodes.util;

import net.libraum.platypodes.Platypodes;
import net.libraum.platypodes.items.ModItems;
import net.libraum.platypodes.items.custom.PlatypusBucketItem;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;

public class ModModelPredicates {
    public static void registerModelPredicates() {
        ItemProperties.register(ModItems.PLATYPUS_BUCKET, ResourceLocation.tryBuild(Platypodes.MOD_ID, "variant"),
                (stack, world, entity, seed) -> PlatypusBucketItem.getVariant(stack));
    }
}
