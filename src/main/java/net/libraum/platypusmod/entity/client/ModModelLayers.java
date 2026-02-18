package net.libraum.platypusmod.entity.client;

import net.libraum.platypusmod.PlatypusMod;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {
    public static final EntityModelLayer PLATYPUS =
            new EntityModelLayer(Identifier.of(PlatypusMod.MOD_ID, "platypus"), "main");
}
