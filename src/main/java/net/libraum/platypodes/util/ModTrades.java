package net.libraum.platypodes.util;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.libraum.platypodes.Platypodes;
import net.libraum.platypodes.items.ModItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.entity.npc.VillagerProfession;

public class ModTrades {

    public static void registerModTrades() {
        onVillagerTrades();
        onWandererTrades();
        Platypodes.LOGGER.info("Registering Custom Trades for " + Platypodes.MOD_ID);
    }

    public static void onVillagerTrades() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FISHERMAN, 1, factories -> {
            if (ModConfig.fisherTrades) {
                factories.add((entity, random) -> new MerchantOffer(
                        new ItemStack(Items.EMERALD, 2),
                        new ItemStack(ModItems.YABBY, 3),
                        6, 2, 0.05f
                ));
            }
        });
    }

    public static void onWandererTrades() {
        TradeOfferHelper.registerWanderingTraderOffers(1, factories -> {
            if (ModConfig.wanderingTrades) {
                factories.add((entity, random) -> new MerchantOffer(
                        new ItemStack(Items.EMERALD, 3),
                        new ItemStack(ModItems.YABBY, 6),
                        8, 3, 0.2f
                ));
            }
        });
    }
}
