package net.libraum.platypus.entity.client;

import net.libraum.platypus.PlatypusMod;
import net.libraum.platypus.entity.custom.PlatypusEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.entity.passive.AxolotlEntity;
import net.minecraft.util.Identifier;

public class PlatypusEntityRenderer extends MobEntityRenderer<PlatypusEntity, PlatypusEntityModel<PlatypusEntity>> {
    private static final Identifier LUCY_TEXTURE = new Identifier(PlatypusMod.MOD_ID, "textures/entity/platypus/platypus_lucy.png");
    private static final Identifier WILD_TEXTURE = new Identifier(PlatypusMod.MOD_ID, "textures/entity/platypus/platypus_wild.png");
    private static final Identifier GOLD_TEXTURE = new Identifier(PlatypusMod.MOD_ID, "textures/entity/platypus/platypus_gold.png");
    private static final Identifier CYAN_TEXTURE = new Identifier(PlatypusMod.MOD_ID, "textures/entity/platypus/platypus_cyan.png");
    private static final Identifier BLUE_TEXTURE = new Identifier(PlatypusMod.MOD_ID, "textures/entity/platypus/platypus_blue.png");

    public PlatypusEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new PlatypusEntityModel<>(context.getPart(ModModelLayers.PLATYPUS)), 0.5f);
    }

    @Override
    public Identifier getTexture(PlatypusEntity platypusEntity) {
        return getTexture(platypusEntity.getVariant());
    }

    public Identifier getTexture(AxolotlEntity.Variant variant) {
        return switch (variant) {
            case LUCY -> LUCY_TEXTURE;
            case WILD -> WILD_TEXTURE;
            case GOLD -> GOLD_TEXTURE;
            case CYAN -> CYAN_TEXTURE;
            case BLUE -> BLUE_TEXTURE;
        };
    }


}
