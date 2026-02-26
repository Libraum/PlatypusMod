package net.libraum.platypodes.blocks.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.libraum.platypodes.Platypodes;
import net.libraum.platypodes.blocks.ModBlocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<PlatypusBurrowBlockEntity> PLATYPUS_BURROW_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(Platypodes.MOD_ID, "platypus_burrow"),
                    FabricBlockEntityTypeBuilder.create(PlatypusBurrowBlockEntity::new,
                           ModBlocks.PLATYPUS_BURROW).build());

    public static void registerBlockEntities() {
        Platypodes.LOGGER.info("Registering block entities for " + Platypodes.MOD_ID);
    }
}
