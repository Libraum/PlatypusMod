package net.libraum.platypodes.entity.client;

import net.libraum.platypodes.Platypodes;
import net.libraum.platypodes.entity.custom.Platypus;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.resources.ResourceLocation;

public class PlatypusRenderer extends MobRenderer<Platypus, PlatypusModel<Platypus>> {
    private static final ResourceLocation LUCY_TEXTURE = new ResourceLocation(Platypodes.MOD_ID, "textures/entity/platypus/platypus_lucy.png");
    private static final ResourceLocation WILD_TEXTURE = new ResourceLocation(Platypodes.MOD_ID, "textures/entity/platypus/platypus_wild.png");
    private static final ResourceLocation GOLD_TEXTURE = new ResourceLocation(Platypodes.MOD_ID, "textures/entity/platypus/platypus_gold.png");
    private static final ResourceLocation CYAN_TEXTURE = new ResourceLocation(Platypodes.MOD_ID, "textures/entity/platypus/platypus_cyan.png");
    private static final ResourceLocation BLUE_TEXTURE = new ResourceLocation(Platypodes.MOD_ID, "textures/entity/platypus/platypus_blue.png");

    public PlatypusRenderer(EntityRendererProvider.Context context) {
        super(context, new PlatypusModel<>(context.bakeLayer(ModModelLayers.PLATYPUS)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(Platypus platypus) {
        return getTexture(platypus.getVariant());
    }

    public ResourceLocation getTexture(Axolotl.Variant variant) {
        return switch (variant) {
            case LUCY -> LUCY_TEXTURE;
            case WILD -> WILD_TEXTURE;
            case GOLD -> GOLD_TEXTURE;
            case CYAN -> CYAN_TEXTURE;
            case BLUE -> BLUE_TEXTURE;
        };
    }


}
