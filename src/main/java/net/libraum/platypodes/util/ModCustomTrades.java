package net.libraum.platypodes.util;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.libraum.platypodes.items.ModItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.entity.npc.VillagerProfession;

public class ModCustomTrades {
    public static void registerCustomTrades() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FISHERMAN, 1,
                factories -> {
                    factories.add((entity, random) -> new MerchantOffer(
                            new ItemCost(Items.EMERALD,2),
                            new ItemStack(ModItems.YABBY,3),
                            6, 2, 0.05f
                    ));
                });

        TradeOfferHelper.registerWanderingTraderOffers(1,
                factories -> {
            factories.add((entity, random) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD,3),
                    new ItemStack(ModItems.YABBY, 6),
                    8, 3, 0.2f
            ));
        });
    }
}
