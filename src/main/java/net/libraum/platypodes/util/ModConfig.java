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
    @Comment(centered = true) public static Comment spawning;
    @Entry public static boolean enableSpawns = true;
    @Entry public static SpawnDuring spawnDuring = SpawnDuring.DAWNANDDUSK;
    public enum SpawnDuring {
        DAWNANDDUSK, NIGHT, ALLDAY
    }
    @Entry(min = 1) public static int rareVariantChance = 1200;
    @Entry(min = 1) public static int spawnWeight = 1;
    @Entry(min = 0) public static int minSpawnGroup = 1;
    @Entry(min = 1) public static int maxSpawnGroup = 1;

    /** Yabby Sources **/
    @Comment(centered = true) public static Comment yabbySources;
    @Entry(isSlider = true, min = 0f, max = 1f, precision = 100) public static float seagrassDrops = 0.05f;
    @Entry public static boolean fisherChests = true;
    //    @Entry public static boolean fisherTrades = true;
//    @Entry public static boolean wanderingTrades = true;
    @Comment(centered = true) public static Comment lootPoolRestart;
}
