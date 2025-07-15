package net.libraum.platypus.sound;

import net.libraum.platypus.PlatypusMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final SoundEvent ENTITY_PLATYPUS_ATTACK = registerSoundEvent("entity.platypus.attack");
    public static final SoundEvent ENTITY_PLATYPUS_DEATH = registerSoundEvent("entity.platypus.death");
    public static final SoundEvent ENTITY_PLATYPUS_HURT = registerSoundEvent("entity.platypus.hurt");
    public static final SoundEvent ENTITY_PLATYPUS_IDLE_AIR = registerSoundEvent("entity.platypus.idle_air");
    public static final SoundEvent ENTITY_PLATYPUS_IDLE_WATER = registerSoundEvent("entity.platypus.idle_water");
    public static final SoundEvent ENTITY_PLATYPUS_SPLASH = registerSoundEvent("entity.platypus.splash");
    public static final SoundEvent ENTITY_PLATYPUS_SWIM = registerSoundEvent("entity.platypus.swim");

    public static final SoundEvent ITEM_BUCKET_FILL_PLATYPUS = registerSoundEvent("item.bucket.fill_platypus");
    public static final SoundEvent ITEM_BUCKET_EMPTY_PLATYPUS = registerSoundEvent("item.bucket.empty_platypus");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(PlatypusMod.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        PlatypusMod.LOGGER.info("Registering Sounds for " + PlatypusMod.MOD_ID);
    }
}
