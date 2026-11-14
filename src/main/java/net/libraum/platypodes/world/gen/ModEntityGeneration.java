package net.libraum.platypodes.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.libraum.platypodes.Platypodes;
import net.libraum.platypodes.entity.ModEntities;
import net.libraum.platypodes.entity.custom.Platypus;
import net.libraum.platypodes.util.ModConfig;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.biome.Biomes;

public class ModEntityGeneration {
    public static void registerModSpawns() {
        BiomeModifications.addSpawn(
                BiomeSelectors.includeByKey(Biomes.RIVER),
                MobCategory.WATER_CREATURE,
                ModEntities.PLATYPUS,
                ModConfig.spawnWeight, /* Default: 2 */
                ModConfig.minSpawnGroup, /* Default: 1 */
                ModConfig.maxSpawnGroup /* Default: 1 */
        );

        SpawnPlacements.register(
                ModEntities.PLATYPUS,
                SpawnPlacements.Type.IN_WATER,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Platypus::checkPlatypusSpawnRules
        );

        Platypodes.LOGGER.info("Registering Spawns for " + Platypodes.MOD_ID);
    }
}