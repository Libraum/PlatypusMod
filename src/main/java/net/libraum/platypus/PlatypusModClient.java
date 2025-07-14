package net.libraum.platypus;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.libraum.platypus.entity.ModEntities;
import net.libraum.platypus.entity.client.ModModelLayers;
import net.libraum.platypus.entity.client.PlatypusEntityModel;
import net.libraum.platypus.entity.client.PlatypusEntityRenderer;

public class PlatypusModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.PLATYPUS, PlatypusEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.PLATYPUS, PlatypusEntityModel::getTexturedModelData);
    }
}
