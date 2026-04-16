package net.libraum.platypodes.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.libraum.platypodes.Platypodes;
import net.libraum.platypodes.entity.custom.PlatypusEntity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

public class ModEntities {
    public static final EntityType<PlatypusEntity> PLATYPUS = Registry.register(BuiltInRegistries.ENTITY_TYPE,
            new ResourceLocation(Platypodes.MOD_ID, "platypus"),
            FabricEntityTypeBuilder.create(MobCategory.AXOLOTLS, PlatypusEntity::new)
                    .dimensions(EntityDimensions.fixed(0.8f,0.4f)).build());
    public static void registerModEntities() {
        Platypodes.LOGGER.info("Registering Entities for " + Platypodes.MOD_ID);
    }
}
