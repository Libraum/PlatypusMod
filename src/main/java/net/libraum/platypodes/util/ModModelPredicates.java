package net.libraum.platypodes.util;

import net.libraum.platypodes.Platypodes;
import net.libraum.platypodes.items.ModItems;
import net.libraum.platypodes.items.custom.PlatypusBucketItem;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

public class ModModelPredicates {
    public static void registerModelPredicates() {
        ModelPredicateProviderRegistry.register(ModItems.PLATYPUS_BUCKET, Identifier.of(Platypodes.MOD_ID, "variant"),
                (stack, world, entity, seed) -> PlatypusBucketItem.getVariant(stack));
    }
}
