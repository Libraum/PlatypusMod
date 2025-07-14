package net.libraum.platypus.util;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.libraum.platypus.items.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;

public class ModCustomTrades {
    public static void registerCustomTrades() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FISHERMAN, 1,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD,2),
                            new ItemStack(ModItems.YABBY,3),
                            6, 2, 0.05f
                    ));
                });

        TradeOfferHelper.registerWanderingTraderOffers(1,
                factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD,3),
                    new ItemStack(ModItems.YABBY, 6),
                    8, 3, 0.2f
            ));
        });
    }
}
