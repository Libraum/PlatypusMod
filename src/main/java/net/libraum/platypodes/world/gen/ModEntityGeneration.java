package net.libraum.platypodes.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.libraum.platypodes.entity.ModEntities;
import net.libraum.platypodes.entity.custom.PlatypusEntity;
import net.libraum.platypodes.util.ModConfig;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.biome.Biomes;

public class ModEntityGeneration {
    public static void addSpawns() {
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.RIVER), MobCategory.AXOLOTLS,
                ModEntities.PLATYPUS, ModConfig.spawnWeight, ModConfig.minSpawnGroup, ModConfig.maxSpawnGroup); /* Defaults: 1, 1, 1 */

        SpawnPlacements.register(ModEntities.PLATYPUS, SpawnPlacementTypes.IN_WATER,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, PlatypusEntity::checkPlatypusSpawnRules);
    }
}
