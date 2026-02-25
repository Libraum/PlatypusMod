package net.libraum.platypodes.mixin;

import net.libraum.platypodes.entity.custom.PlatypusBrain;
import net.libraum.platypodes.entity.custom.PlatypusEntity;
import net.minecraft.entity.passive.AxolotlBrain;
import net.minecraft.entity.passive.AxolotlEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AxolotlEntity.class)
public class AxolotlMixin {
	@Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/AxolotlBrain;updateActivities(Lnet/minecraft/entity/passive/AxolotlEntity;)V"), method = "mobTick")
	private void platypusAICheck(AxolotlEntity axolotl) {
		if (axolotl instanceof PlatypusEntity platypus)
			PlatypusBrain.updateActivities(platypus);
		else
			AxolotlBrain.updateActivities(axolotl);
	}
}