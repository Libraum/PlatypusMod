package net.libraum.platypodes;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.libraum.platypodes.entity.ModEntities;
import net.libraum.platypodes.entity.client.ModModelLayers;
import net.libraum.platypodes.entity.client.PlatypusEntityModel;
import net.libraum.platypodes.entity.client.PlatypusEntityRenderer;

public class PlatypodesClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.PLATYPUS, PlatypusEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.PLATYPUS, PlatypusEntityModel::getTexturedModelData);
    }
}
