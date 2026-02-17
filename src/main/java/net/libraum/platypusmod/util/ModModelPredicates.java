package net.libraum.platypusmod.util;

import net.libraum.platypusmod.PlatypusMod;
import net.libraum.platypusmod.items.ModItems;
import net.libraum.platypusmod.items.custom.PlatypusBucketItem;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

public class ModModelPredicates {
    public static void registerModelPredicates() {
        ModelPredicateProviderRegistry.register(ModItems.PLATYPUS_BUCKET, new Identifier(PlatypusMod.MOD_ID, "variant"),
                (stack, world, entity, seed) -> PlatypusBucketItem.getVariant(stack));
    }
}
