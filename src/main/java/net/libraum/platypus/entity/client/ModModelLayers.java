package net.libraum.platypus.entity.client;

import net.libraum.platypus.PlatypusMod;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {
    public static final EntityModelLayer PLATYPUS =
            new EntityModelLayer(new Identifier(PlatypusMod.MOD_ID, "platypus"), "main");
}
