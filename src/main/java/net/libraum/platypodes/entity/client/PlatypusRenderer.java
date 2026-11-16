package net.libraum.platypodes.entity.client;

import net.libraum.platypodes.Platypodes;
import net.libraum.platypodes.entity.custom.Platypus;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class PlatypusRenderer extends MobRenderer<Platypus, PlatypusModel<Platypus>> {
    private static final ResourceLocation BROWN_TEXTURE = ResourceLocation.tryBuild(Platypodes.MOD_ID, "textures/entity/platypus/platypus.png");
    private static final ResourceLocation DARK_TEXTURE = ResourceLocation.tryBuild(Platypodes.MOD_ID, "textures/entity/platypus/platypus_dark.png");
    private static final ResourceLocation GOLD_TEXTURE = ResourceLocation.tryBuild(Platypodes.MOD_ID, "textures/entity/platypus/platypus_gold.png");
    private static final ResourceLocation BLUE_TEXTURE = ResourceLocation.tryBuild(Platypodes.MOD_ID, "textures/entity/platypus/platypus_blue.png");
    private static final ResourceLocation BLOOP_TEXTURE = ResourceLocation.tryBuild(Platypodes.MOD_ID, "textures/entity/platypus/platypus_bloop.png");

    public PlatypusRenderer(EntityRendererProvider.Context context) {
        super(context, new PlatypusModel<>(context.bakeLayer(ModModelLayers.PLATYPUS)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(Platypus platypusEntity) {
        return getTexture(platypusEntity.getVariant());
    }

    public ResourceLocation getTexture(Platypus.Variant variant) {
        return switch (variant) {
            case BROWN -> BROWN_TEXTURE;
            case DARK -> DARK_TEXTURE;
            case GOLD -> GOLD_TEXTURE;
            case BLUE -> BLUE_TEXTURE;
            case BLOOP -> BLOOP_TEXTURE;
        };
    }
}
