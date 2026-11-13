// Made with Blockbench 4.12.5
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package net.libraum.platypodes.entity.client;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.libraum.platypodes.entity.custom.Platypus;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LerpingModel;
import net.minecraft.util.Mth;
import org.joml.Vector3f;

import java.util.Map;

@Environment(EnvType.CLIENT)
public class PlatypusModel<T extends Platypus & LerpingModel> extends AgeableListModel<T> {
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart leftFrontLeg;
	private final ModelPart leftHindLeg;
	private final ModelPart rightFrontLeg;
	private final ModelPart rightHindLeg;
	private final ModelPart tail;

	public PlatypusModel(ModelPart root) {
		super(true, 8.0f, 3.35f);
		this.body = root.getChild("body");
		this.head = this.body.getChild("head");
		this.rightHindLeg = this.body.getChild("right_hind_leg");
		this.leftHindLeg = this.body.getChild("left_hind_leg");
		this.rightFrontLeg = this.body.getChild("right_front_leg");
		this.leftFrontLeg = this.body.getChild("left_front_leg");
		this.tail = this.body.getChild("tail");
	}
	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition modelPartData = modelData.getRoot();
		PartDefinition modelPartData2 = modelPartData.addOrReplaceChild(
				"body",
				CubeListBuilder.create()
						.texOffs(0, 10).addBox(-4.0F, -4.0F, -9.0F, 8.0F, 6.0F, 10.0F),
				PartPose.offset(0.0F, 18.0F, 4.0F)
		);
		CubeDeformation dilation = new CubeDeformation(0F);
		modelPartData2.addOrReplaceChild(
				"head",
				CubeListBuilder.create()
						.texOffs(0, 0).addBox(-4.0F, -3.0F, -5.0F, 8.0F, 5.0F, 5.0F, dilation)
						.texOffs(26, 1).addBox(-3.5F, -0.3F, -9.0F, 7.0F, 2.0F, 4.0F, dilation),
				PartPose.offset(0.0F, 0.0F, -9.0F)
		);

		CubeListBuilder modelPartBuilder4 = CubeListBuilder.create()
				.texOffs(1, 10).addBox(0.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, dilation)
				.texOffs(-4,14).addBox(1.5F, 0.5F, -2.0F, 3.0F, 0.0F, 4.0F);
		CubeListBuilder modelPartBuilder5 = CubeListBuilder.create()
				.texOffs(1, 10).mirror().addBox(-2.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, dilation)
				.texOffs(-4, 14).mirror().addBox(-4.5F, 0.5F, -2.0F, 3.0F, 0.0F, 4.0F, dilation);
		modelPartData2.addOrReplaceChild("left_front_leg", modelPartBuilder4, PartPose.offset(4.0F, 1.0F, -8.5F));
		modelPartData2.addOrReplaceChild("left_hind_leg", modelPartBuilder4, PartPose.offset(4.0F, 1.0F, -0.5F));
		modelPartData2.addOrReplaceChild("right_front_leg", modelPartBuilder5, PartPose.offset(-4.0F, 1.0F, -8.5F));
		modelPartData2.addOrReplaceChild("right_hind_leg", modelPartBuilder5, PartPose.offset(-4.0F, 1.0F, -0.5F));

		modelPartData2.addOrReplaceChild(
				"tail",
				CubeListBuilder.create().texOffs(26, 9).addBox(-3.5F, -2.0F, 0.0F, 7.0F, 3.0F, 8.0F),
				PartPose.offset(0.0F, 0.0F, 1.0F)
		);
		return LayerDefinition.create(modelData, 64, 64);
	}

	@Override
	protected Iterable<ModelPart> headParts() {
		return ImmutableList.of();
	}

	@Override
	protected Iterable<ModelPart> bodyParts() {
		return ImmutableList.of(this.body);
	}

	public void setupAnim(T platypus, float f, float g, float h, float i, float j) {
		this.resetAngles(platypus, i, j);
		boolean bl = g > 1.0E-5F || platypus.getXRot() != platypus.xRotO || platypus.getYRot() != platypus.yRotO;
		if (platypus.isInWaterOrBubble()) {
			if (bl) {
				this.setMovingInWaterAngles(h, i, j);
			} else {
				this.setStandingInWaterAngles(h);
			}

			this.updateAnglesCache(platypus);
			} else {
				if (platypus.onGround()) {
					if (bl) {
						this.setMovingOnGroundAngles(h, i);
					} else {
						this.setStandingOnGroundAngles(h, i);
					}
				}
				this.updateAnglesCache(platypus);
		}
	}

	private void updateAnglesCache(T platypus) {
		Map<String, Vector3f> map = platypus.getModelRotationValues();
		map.put("body", this.getAngles(this.body));
		map.put("head", this.getAngles(this.head));
		map.put("right_hind_leg", this.getAngles(this.rightHindLeg));
		map.put("left_hind_leg", this.getAngles(this.leftHindLeg));
		map.put("right_front_leg", this.getAngles(this.rightFrontLeg));
		map.put("left_front_leg", this.getAngles(this.leftFrontLeg));
		map.put("tail", this.getAngles(this.tail));
	}

	private Vector3f getAngles(ModelPart part) {
		return new Vector3f(part.xRot, part.yRot, part.zRot);
	}

	private void setAngles(ModelPart part, Vector3f angles) {
		part.setRotation(angles.x(), angles.y(), angles.z());
	}

	private void resetAngles(T platypus, float headYaw, float headPitch) {
		this.body.x = 0.0F;
		this.head.y = 0.0F;
		this.body.y = 21.0F;
		Map<String, Vector3f> map = platypus.getModelRotationValues();
		if (map.isEmpty()) {
			this.body.setRotation(headPitch * (float) (Math.PI / 180.0), headYaw * (float) (Math.PI / 180.0), 0.0F);
			this.head.setRotation(0.0F, 0.0F, 0.0F);
			this.leftHindLeg.setRotation(0.0F, 0.0F, 0.0F);
			this.rightHindLeg.setRotation(0.0F, 0.0F, 0.0F);
			this.leftFrontLeg.setRotation(0.0F, 0.0F, 0.0F);
			this.rightFrontLeg.setRotation(0.0F, 0.0F, 0.0F);
			this.tail.setRotation(0.0F, 0.0F, 0.0F);
		} else {
			this.setAngles(this.body, map.get("body"));
			this.setAngles(this.head, map.get("head"));
			this.setAngles(this.leftHindLeg, map.get("left_hind_leg"));
			this.setAngles(this.rightHindLeg, map.get("right_hind_leg"));
			this.setAngles(this.leftFrontLeg, map.get("left_front_leg"));
			this.setAngles(this.rightFrontLeg, map.get("right_front_leg"));
			this.setAngles(this.tail, map.get("tail"));
		}
	}

	private float lerpAngleDegrees(float start, float end) {
		return this.lerpAngleDegrees(0.05F, start, end);
	}

	private float lerpAngleDegrees(float delta, float start, float end) {
		return Mth.rotLerp(delta, start, end);
	}

	private void setAngles(ModelPart part, float pitch, float yaw, float roll) {
		part.setRotation(this.lerpAngleDegrees(part.xRot, pitch), this.lerpAngleDegrees(part.yRot, yaw), this.lerpAngleDegrees(part.zRot, roll));
	}

	private void setStandingOnGroundAngles(float animationProgress, float headYaw) {
		float f = animationProgress * 0.09F;
		float g = Mth.sin(f);
		float h = g * g - 2.0F * g;
		float i = h * 0.015F;
		this.body.xRot = this.lerpAngleDegrees(0.2F, this.body.xRot, 0.0F);
		this.body.yRot = this.lerpAngleDegrees(this.body.yRot, headYaw * (float) (Math.PI / 180.0));
		this.body.zRot = this.lerpAngleDegrees(this.body.zRot, i);
		this.head.xRot = this.lerpAngleDegrees(this.head.xRot, 0.0F);
		this.head.yRot = this.lerpAngleDegrees(this.head.yRot, 0.0F);
		this.head.zRot = this.lerpAngleDegrees(this.head.zRot, -i);
		this.setAngles(this.leftHindLeg, 0.0F, -0.5F, 0.3F - i);
		this.setAngles(this.leftFrontLeg, 0.0F, 0.5F, 0.3F - i);
		this.setAngles(this.rightHindLeg, 0.0F, 0.5F, -0.3F - i);
		this.setAngles(this.rightFrontLeg, 0.0F, -0.5F, -0.3F - i);
		this.tail.xRot = this.lerpAngleDegrees(this.tail.xRot, -0.2F);
		this.tail.yRot = this.lerpAngleDegrees(this.tail.yRot, -0.1F + 0.1F * h);
	}

	private void setMovingOnGroundAngles(float animationProgress, float headYaw) {
		float f = animationProgress * 0.22F;
		float g = Mth.cos(f);
		float h = (g * g - 2.0F * g) / 5.0F;
		float i = 0.7F * g;
		float j = Mth.cos(f-1.5F) * 0.25F;
		this.body.xRot = this.lerpAngleDegrees(0.2F, this.body.xRot, 0.0F);
		this.body.yRot = this.lerpAngleDegrees(this.body.yRot, 0.09F * -g);
		this.body.zRot = this.lerpAngleDegrees(this.body.zRot, 0.09F * -j);
		this.head.xRot = this.lerpAngleDegrees(this.head.xRot, 0.0F);
		this.head.yRot = this.lerpAngleDegrees(this.head.yRot, 0.09F * g);
		this.head.zRot = this.lerpAngleDegrees(this.head.zRot, 0.0F);
		this.setAngles(this.leftHindLeg, 0F, -0.3F + i, j + 0.15F);
		this.setAngles(this.leftFrontLeg, 0.0F, (float) (Math.PI / 8) - i, -j + 0.05F);
		this.setAngles(this.rightHindLeg, this.leftHindLeg.xRot, 0.3F + i, j - 0.15F);
		this.setAngles(this.rightFrontLeg, this.leftFrontLeg.xRot, (float) (-Math.PI / 8) - i, -j - 0.05F);
		this.tail.xRot = this.lerpAngleDegrees(this.tail.xRot, -0.2F);
		this.tail.yRot = this.lerpAngleDegrees(this.tail.yRot, this.body.yRot * 4);
	}

	private void setStandingInWaterAngles(float animationProgress) {
		float f = animationProgress * 0.075F;
		float g = Mth.cos(f);
		float h = Mth.sin(f);
		float i = h * 0.15F;
		this.body.xRot = this.lerpAngleDegrees(this.body.xRot, -0.15F - 0.075F * g);
		this.head.yRot = this.lerpAngleDegrees(this.head.yRot, 0.0F);
		this.head.zRot = this.lerpAngleDegrees(this.head.zRot, 0.0F);
		this.body.y -= i;
		this.head.xRot = this.lerpAngleDegrees(this.head.xRot, -this.body.xRot);
		this.setAngles(this.leftHindLeg, 0.0F, -1.0F, 0.75F - g * 0.11F);
		this.setAngles(this.leftFrontLeg, 0.3F, 0.6F * -g, 0.5F - h * 0.5F);
		this.copyHindLegAngles();
		this.setAngles(this.rightFrontLeg, 0.3F, 0.6F * -g, -0.5F - h * 0.5F);
		this.tail.xRot = this.lerpAngleDegrees(this.tail.xRot, -0.2F);
		this.tail.yRot = this.lerpAngleDegrees(this.tail.yRot, 0.5F * g);
	}

	private void setMovingInWaterAngles(float animationProgress, float headYaw, float headPitch) {
		float f = animationProgress * 0.33F;
		float g = Mth.sin(f);
		float h = Mth.cos(f);
		float i = 0.13F * g;
		this.body.xRot = this.lerpAngleDegrees(0.1F, this.body.xRot, headPitch * (float) (Math.PI / 180.0) + i);
		this.body.zRot = this.lerpAngleDegrees(0.1F, this.body.zRot, (float) (Math.PI / 180.0));
		this.head.xRot = -i * 1.8F;
		this.head.yRot = this.lerpAngleDegrees(this.head.yRot, 0.0F);
		this.head.zRot = this.lerpAngleDegrees(this.head.zRot, 0.0F);
		this.body.y -= 0.45F * h;
		this.setAngles(this.leftHindLeg, 0.0F, -1.3F - g * 0.8F, 0.75F);
		this.setAngles(this.leftFrontLeg, 1.0F, 3.1F * g, 0.5F - h * 0.5F);
		this.copyHindLegAngles();
		this.setAngles(this.rightFrontLeg, 1.0F, 3.1F * g, -0.5F - h * 0.5F);
		this.tail.xRot = this.lerpAngleDegrees(this.tail.xRot, 0.0F);
		this.tail.yRot = this.lerpAngleDegrees(this.tail.yRot, 0.3F * Mth.cos(f * 0.9F));
	}

	private void copyHindLegAngles() {
		this.setAngles(this.rightHindLeg, this.leftHindLeg.xRot, -this.leftHindLeg.yRot, -this.leftHindLeg.zRot);
	}

	private void copyFrontLegAngles() {
		this.setAngles(this.rightFrontLeg, this.leftFrontLeg.xRot, -this.leftFrontLeg.yRot, -this.leftFrontLeg.zRot);
	}
}