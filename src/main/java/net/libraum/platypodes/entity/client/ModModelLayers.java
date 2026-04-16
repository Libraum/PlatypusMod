package net.libraum.platypodes.entity.client;

import net.libraum.platypodes.Platypodes;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {
    public static final ModelLayerLocation PLATYPUS =
            new ModelLayerLocation(ResourceLocation.tryBuild(Platypodes.MOD_ID, "platypus"), "main");
}
