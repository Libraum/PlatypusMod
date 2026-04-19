package net.libraum.platypodes.mixin;

import net.libraum.platypodes.entity.ai.PlatypusBrain;
import net.libraum.platypodes.entity.custom.PlatypusEntity;
import net.minecraft.world.entity.animal.axolotl.AxolotlAi;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Axolotl.class)
public class AxolotlMixin {
	@Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/animal/axolotl/AxolotlAi;updateActivity(Lnet/minecraft/world/entity/animal/axolotl/Axolotl;)V"), method = "customServerAiStep")
	private void platypusAICheck(Axolotl axolotl) {
		if (axolotl instanceof PlatypusEntity platypus)
			PlatypusBrain.updateActivities(platypus);
		else
			AxolotlAi.updateActivity(axolotl);
	}
}