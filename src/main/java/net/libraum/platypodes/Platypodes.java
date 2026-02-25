package net.libraum.platypodes;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.libraum.platypodes.entity.ModEntities;
import net.libraum.platypodes.entity.custom.PlatypusEntity;
import net.libraum.platypodes.items.ModItemGroups;
import net.libraum.platypodes.items.ModItems;
import net.libraum.platypodes.sound.ModSounds;
import net.libraum.platypodes.util.ModCustomTrades;
import net.libraum.platypodes.util.ModLootTableModifiers;
import net.libraum.platypodes.world.gen.ModWorldGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Platypodes implements ModInitializer {
	public static final String MOD_ID = "platypodes";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModEntities.registerModEntities();
		ModWorldGeneration.generateModWorldGen();

		ModCustomTrades.registerCustomTrades();
		ModLootTableModifiers.modifyLootTables();

		ModSounds.registerSounds();

		FabricDefaultAttributeRegistry.register(ModEntities.PLATYPUS, PlatypusEntity.createPlatypusAttributes());
	}
}