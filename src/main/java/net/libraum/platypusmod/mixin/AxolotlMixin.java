package net.libraum.platypusmod.mixin;

import net.libraum.platypusmod.entity.custom.PlatypusBrain;
import net.libraum.platypusmod.entity.custom.PlatypusEntity;
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