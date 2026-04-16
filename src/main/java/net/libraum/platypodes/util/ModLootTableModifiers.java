package net.libraum.platypodes.util;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.libraum.platypodes.items.ModItems;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraft.resources.ResourceLocation;

public class ModLootTableModifiers {
    private static final ResourceLocation Village_Fisher_Chest_ID =
            new ResourceLocation("minecraft", "chests/village/village_fisher");

    public static void modifyLootTables() {
                LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if(Village_Fisher_Chest_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(0.33f))
                        .add(LootItem.lootTableItem(ModItems.YABBY))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 3.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }
        });
    }
}
