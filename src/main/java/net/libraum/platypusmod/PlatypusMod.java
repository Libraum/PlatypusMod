package net.libraum.platypusmod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.libraum.platypusmod.entity.ModEntities;
import net.libraum.platypusmod.entity.custom.PlatypusEntity;
import net.libraum.platypusmod.items.ModItemGroups;
import net.libraum.platypusmod.items.ModItems;
import net.libraum.platypusmod.sound.ModSounds;
import net.libraum.platypusmod.util.ModCustomTrades;
import net.libraum.platypusmod.util.ModLootTableModifiers;
import net.libraum.platypusmod.world.gen.ModWorldGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlatypusMod implements ModInitializer {
	public static final String MOD_ID = "platypusmod";
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