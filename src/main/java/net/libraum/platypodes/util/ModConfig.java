package net.libraum.platypodes.util;

import eu.midnightdust.lib.config.MidnightConfig;

public class ModConfig extends MidnightConfig {
    /** Platypus Attributes */
//    @Comment(centered = true) public static Comment attributes;
//    @Entry public static double healthMultiplier = 14.0;
//    @Entry public static double speedMultiplier = 1.0;
//    @Entry(category = ATTRIBUTES) public static double attackMultiplier = 2.0;
//    @Comment(centered = true) public static Comment attributesRestart;

    /** Platypus Spawning */
    @Comment(name = "Platypus Spawning:", centered = true) public static Comment spawning;
    @Entry(name = "Enable Spawning") public static boolean enableSpawns = true;
    @Entry(name = "Spawn During") public static SpawnDuring spawnDuring = SpawnDuring.DAWNANDDUSK;
    public enum SpawnDuring {
        DAWNANDDUSK, NIGHT, ALLDAY
    }
    @Entry(name = "Rare Variant Chance", min = 1) public static int rareVariantChance = 1200;
    @Entry(name = "Spawn Weight", min = 1) public static int spawnWeight = 1;
    @Entry(name = "Minimum Group Size", min = 0) public static int minSpawnGroup = 1;
    @Entry(name = "Maximum Group Size", min = 1) public static int maxSpawnGroup = 1;

}
