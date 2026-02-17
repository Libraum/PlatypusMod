package net.libraum.platypusmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.libraum.platypusmod.entity.ModEntities;
import net.libraum.platypusmod.entity.client.ModModelLayers;
import net.libraum.platypusmod.entity.client.PlatypusEntityModel;
import net.libraum.platypusmod.entity.client.PlatypusEntityRenderer;
import net.libraum.platypusmod.util.ModModelPredicates;

@Environment(EnvType.CLIENT)
public class PlatypusModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.PLATYPUS, PlatypusEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.PLATYPUS, PlatypusEntityModel::getTexturedModelData);
        ModModelPredicates.registerModelPredicates();
    }
}
