package net.libraum.platypodes.util;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.libraum.platypodes.items.ModItems;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraft.resources.ResourceLocation;

public class ModLootTableModifiers {

    private static final ResourceLocation VILLAGE_FISHER_CHEST_ID =
            ResourceLocation.tryBuild("minecraft", "chests/village/village_fisher");
    private static final ResourceLocation SEAGRASS =
            ResourceLocation.tryBuild("minecraft", "blocks/seagrass");
    private static final ResourceLocation TALL_SEAGRASS =
            ResourceLocation.tryBuild("minecraft", "blocks/tall_seagrass");

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {

            if(VILLAGE_FISHER_CHEST_ID.equals(key.location()) && ModConfig.fisherChests) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(0.33f))
                        .add(LootItem.lootTableItem(ModItems.YABBY))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 3.0f)).build());
                tableBuilder.pool(poolBuilder.build());
            }

            if(SEAGRASS.equals(key.location())) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(ModConfig.seagrassDrops)) /* Default: 5% */
                        .when(InvertedLootItemCondition.invert(MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS))))
                        .when(ExplosionCondition.survivesExplosion())
                        .add(LootItem.lootTableItem(ModItems.YABBY))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)).build());
                tableBuilder.pool(poolBuilder.build());
            }

            if(TALL_SEAGRASS.equals(key.location())) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(ModConfig.seagrassDrops)) /* Default: 5% */
                        .when(InvertedLootItemCondition.invert(MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS))))
                        .when(ExplosionCondition.survivesExplosion())
                        .add(LootItem.lootTableItem(ModItems.YABBY))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build());
                tableBuilder.pool(poolBuilder.build());
            }
        });
    }
}
