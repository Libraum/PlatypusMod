package net.libraum.platypodes.mixin;

import net.minecraft.world.entity.MobCategory;
import org.spongepowered.asm.mixin.*;

@Debug(export = true)
@Mixin(MobCategory.class)
public enum MobCategoryMixin {
    PLATYPODES_PLATYPUS("platypus", 2, true, false, 128);

    @Shadow
    MobCategoryMixin(String string2, int j, boolean bl, boolean bl2, int k) {
    }
}
