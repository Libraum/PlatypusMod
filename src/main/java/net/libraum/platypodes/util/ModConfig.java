package net.libraum.platypodes.util;

import eu.midnightdust.lib.config.MidnightConfig;
import net.libraum.platypodes.Platypodes;

public class ModConfig extends MidnightConfig {

    public static void registerModConfig() {
        MidnightConfig.init(Platypodes.MOD_ID, ModConfig.class);
        Platypodes.LOGGER.info("Registering Config for " + Platypodes.MOD_ID);
    }

    /** Platypus Attributes */
    @Comment(centered = true) public static Comment attributes;
    @Entry(min=1.0) public static double maxHealth = 14.0;
    @Entry public static int maxAirSupply = 6000;
    @Entry public static int maxPoisonSupply = 6000;

    /** Platypus Spawning */
    @Comment(centered = true) public static Comment spawning;
    @Entry public static boolean enableSpawns = true;
    @Entry public static SpawnDuring spawnDuring = SpawnDuring.DAWNANDDUSK;
    public enum SpawnDuring {
        DAWNANDDUSK,
        NIGHT,
        ALLDAY
    }
    @Entry(min = 1) public static int rareVariantChance = 1200;
    @Entry(min = 1) public static int spawnWeight = 1;
    @Entry(min = 0) public static int minSpawnGroup = 1;
    @Entry(min = 1) public static int maxSpawnGroup = 1;

    /** Yabby Sources */
    @Comment(centered = true) public static Comment yabbySources;
 //   @Entry(isSlider = true, min = 0f, max = 1f, precision = 100) public static float seagrassDrops = 0.05f;
    @Entry public static boolean fisherChests = true;
    @Entry public static boolean fisherTrades = true;
    @Entry public static boolean wanderingTrades = true;

    @Comment(centered = true) public static Comment requiresRestart;
}
