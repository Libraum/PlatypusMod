package net.libraum.platypodes;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.libraum.platypodes.blocks.ModBlocks;
import net.libraum.platypodes.entity.ModEntities;
import net.libraum.platypodes.entity.client.ModModelLayers;
import net.libraum.platypodes.entity.client.PlatypusEntityModel;
import net.libraum.platypodes.entity.client.PlatypusEntityRenderer;
import net.libraum.platypodes.util.ModModelPredicates;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public class PlatypodesClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PLATYPUS_BURROW, RenderLayer.getCutout());
        EntityRendererRegistry.register(ModEntities.PLATYPUS, PlatypusEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.PLATYPUS, PlatypusEntityModel::getTexturedModelData);
        ModModelPredicates.registerModelPredicates();
    }
}
