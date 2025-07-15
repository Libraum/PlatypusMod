package net.libraum.platypodes.entity.client;

import net.libraum.platypodes.Platypodes;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {
    public static final EntityModelLayer PLATYPUS =
            new EntityModelLayer(new Identifier(Platypodes.MOD_ID, "platypus"), "main");
}
