// Made with Blockbench 4.12.5
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package net.libraum.platypodes.entity.client;

import com.google.common.collect.ImmutableList;
import net.libraum.platypodes.entity.custom.PlatypusEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LerpingModel;
import net.minecraft.util.Mth;
import org.joml.Vector3f;

import java.util.Map;

public class PlatypusEntityModel<T extends PlatypusEntity & LerpingModel> extends AgeableListModel<T> {
	public static final float MOVING_IN_WATER_LEG_PITCH = 1.8849558F;
	private final ModelPart head;
	private final ModelPart topGills;
	private final ModelPart leftGills;
	private final ModelPart rightGills;
	private final ModelPart body;
	private final ModelPart leftFrontLeg;
	private final ModelPart leftHindLeg;
	private final ModelPart rightFrontLeg;
	private final ModelPart rightHindLeg;
	private final ModelPart tail;

	public PlatypusEntityModel(ModelPart root) {
		super(true, 8.0f, 3.35f);
		this.body = root.getChild(PartNames.BODY);
		this.head = this.body.getChild(PartNames.HEAD);
		this.rightHindLeg = this.body.getChild(PartNames.RIGHT_HIND_LEG);
		this.leftHindLeg = this.body.getChild(PartNames.LEFT_HIND_LEG);
		this.rightFrontLeg = this.body.getChild(PartNames.RIGHT_FRONT_LEG);
		this.leftFrontLeg = this.body.getChild(PartNames.LEFT_FRONT_LEG);
		this.tail = this.body.getChild(PartNames.TAIL);
		this.topGills = this.head.getChild(PartNames.TOP_GILLS);
		this.leftGills = this.head.getChild(PartNames.LEFT_GILLS);
		this.rightGills = this.head.getChild(PartNames.RIGHT_GILLS);
	}
	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition modelPartData = modelData.getRoot();
		PartDefinition modelPartData2 = modelPartData.addOrReplaceChild(
				PartNames.BODY,
				CubeListBuilder.create()
						.texOffs(0, 10).addBox(-4.0F, -4.0F, -9.0F, 8.0F, 6.0F, 10.0F),
				PartPose.offset(0.0F, 18.0F, 4.0F)
		);
		CubeDeformation dilation = new CubeDeformation(0F);
		PartDefinition modelPartData3 = modelPartData2.addOrReplaceChild(
				PartNames.HEAD,
				CubeListBuilder.create()
						.texOffs(0, 0).addBox(-4.0F, -3.0F, -5.0F, 8.0F, 5.0F, 5.0F, dilation)
						.texOffs(26, 1).addBox(-3.5F, -0.3F, -9.0F, 7.0F, 2.0F, 4.0F, dilation),
				PartPose.offset(0.0F, 0.0F, -9.0F)
		);
		CubeListBuilder modelPartBuilder = CubeListBuilder.create()
				.texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, dilation);
		CubeListBuilder modelPartBuilder2 = CubeListBuilder.create()
				.texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, dilation);
		CubeListBuilder modelPartBuilder3 = CubeListBuilder.create()
				.texOffs(0, 0).addBox(0F, 0F, 0F, 0F, 0F, 0F, dilation);
		modelPartData3.addOrReplaceChild(PartNames.TOP_GILLS, modelPartBuilder, PartPose.offset(0.0F, 15.0F, -6.0F));
		modelPartData3.addOrReplaceChild(PartNames.LEFT_GILLS, modelPartBuilder2, PartPose.offset(-4.0F, 18.0F, -6.0F));
		modelPartData3.addOrReplaceChild(PartNames.RIGHT_GILLS, modelPartBuilder3, PartPose.offset(4.0F, 18.0F, -6.0F));

		CubeListBuilder modelPartBuilder4 = CubeListBuilder.create()
				.texOffs(1, 10).addBox(-0.5F, 0.5F, -1.0F, 2.0F, 2.0F, 2.0F, dilation)
				.texOffs(0,14).mirror().addBox(-1.5F, 2.0F, 0F, 4.0F, 3.0F, 0.0F);
		CubeListBuilder modelPartBuilder5 = CubeListBuilder.create()
				.texOffs(1, 10).mirror().addBox(-1.5F, 0.5F, -1.0F, 2.0F, 2.0F, 2.0F, dilation).mirror(false)
				.texOffs(0, 14).addBox(-2.5F, 2.0F, 0F, 4.0F, 3.0F, 0.0F, dilation);
		modelPartData2.addOrReplaceChild(PartNames.LEFT_FRONT_LEG, modelPartBuilder4, PartPose.offset(3.5F, 1.0F, -8.0F));
		modelPartData2.addOrReplaceChild(PartNames.LEFT_HIND_LEG, modelPartBuilder4, PartPose.offset(3.5F, 1.0F, -1.0F));
		modelPartData2.addOrReplaceChild(PartNames.RIGHT_FRONT_LEG, modelPartBuilder5, PartPose.offset(-3.5F, 1.0F, -8.0F));
		modelPartData2.addOrReplaceChild(PartNames.RIGHT_HIND_LEG, modelPartBuilder5, PartPose.offset(-3.5F, 1.0F, -1.0F));

		modelPartData2.addOrReplaceChild(
				PartNames.TAIL, CubeListBuilder.create().texOffs(26, 9).addBox(-3.5F, -2.0F, 0.0F, 7.0F, 3.0F, 8.0F),
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

	public void setupAnim(T axolotlEntity, float f, float g, float h, float i, float j) {
		this.resetAngles(axolotlEntity, i, j);
		boolean bl = g > 1.0E-5F || axolotlEntity.getXRot() != axolotlEntity.xRotO || axolotlEntity.getYRot() != axolotlEntity.yRotO;
		if (axolotlEntity.isInWaterOrBubble()) {
			if (bl) {
				this.setMovingInWaterAngles(h, j);
			} else {
				this.setStandingInWaterAngles(h);
			}

			this.updateAnglesCache(axolotlEntity);
			} else {
				if (axolotlEntity.onGround()) {
					if (bl) {
						this.setMovingOnGroundAngles(h, i);
					} else {
						this.setStandingOnGroundAngles(h, i);
					}
				}
				this.updateAnglesCache(axolotlEntity);
		}
	}

	private void updateAnglesCache(T axolotl) {
		Map<String, Vector3f> map = axolotl.getModelRotationValues();
		map.put("body", this.getAngles(this.body));
		map.put("head", this.getAngles(this.head));
		map.put("right_hind_leg", this.getAngles(this.rightHindLeg));
		map.put("left_hind_leg", this.getAngles(this.leftHindLeg));
		map.put("right_front_leg", this.getAngles(this.rightFrontLeg));
		map.put("left_front_leg", this.getAngles(this.leftFrontLeg));
		map.put("tail", this.getAngles(this.tail));
		map.put("top_gills", this.getAngles(this.topGills));
		map.put("left_gills", this.getAngles(this.leftGills));
		map.put("right_gills", this.getAngles(this.rightGills));
	}

	private Vector3f getAngles(ModelPart part) {
		return new Vector3f(part.xRot, part.yRot, part.zRot);
	}

	private void setAngles(ModelPart part, Vector3f angles) {
		part.setRotation(angles.x(), angles.y(), angles.z());
	}

	/**
	 * Resets the angles of the axolotl model.
	 */
	private void resetAngles(T axolotl, float headYaw, float headPitch) {
		this.body.x = 0.0F;
		this.head.y = 0.0F;
		this.body.y = 20.0F;
		Map<String, Vector3f> map = axolotl.getModelRotationValues();
		if (map.isEmpty()) {
			this.body.setRotation(headPitch * (float) (Math.PI / 180.0), headYaw * (float) (Math.PI / 180.0), 0.0F);
			this.head.setRotation(0.0F, 0.0F, 0.0F);
			this.leftHindLeg.setRotation(0.0F, 0.0F, 0.0F);
			this.rightHindLeg.setRotation(0.0F, 0.0F, 0.0F);
			this.leftFrontLeg.setRotation(0.0F, 0.0F, 0.0F);
			this.rightFrontLeg.setRotation(0.0F, 0.0F, 0.0F);
			this.leftGills.setRotation(0.0F, 0.0F, 0.0F);
			this.rightGills.setRotation(0.0F, 0.0F, 0.0F);
			this.topGills.setRotation(0.0F, 0.0F, 0.0F);
			this.tail.setRotation(0.0F, 0.0F, 0.0F);
		} else {
			this.setAngles(this.body, (Vector3f)map.get("body"));
			this.setAngles(this.head, (Vector3f)map.get("head"));
			this.setAngles(this.leftHindLeg, (Vector3f)map.get("left_hind_leg"));
			this.setAngles(this.rightHindLeg, (Vector3f)map.get("right_hind_leg"));
			this.setAngles(this.leftFrontLeg, (Vector3f)map.get("left_front_leg"));
			this.setAngles(this.rightFrontLeg, (Vector3f)map.get("right_front_leg"));
			this.setAngles(this.leftGills, (Vector3f)map.get("left_gills"));
			this.setAngles(this.rightGills, (Vector3f)map.get("right_gills"));
			this.setAngles(this.topGills, (Vector3f)map.get("top_gills"));
			this.setAngles(this.tail, (Vector3f)map.get("tail"));
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
		float h = Mth.cos(f);
		float i = g * g - 2.0F * g;
		float j = h * h - 3.0F * g;
		this.head.xRot = this.lerpAngleDegrees(this.head.xRot, -0.09F * i);
		this.head.yRot = this.lerpAngleDegrees(this.head.yRot, 0.0F);
		this.head.zRot = this.lerpAngleDegrees(this.head.zRot, -0.2F);
		this.tail.yRot = this.lerpAngleDegrees(this.tail.yRot, -0.1F + 0.1F * i);
		this.topGills.xRot = this.lerpAngleDegrees(this.topGills.xRot, 0.6F + 0.05F * j);
		this.leftGills.yRot = this.lerpAngleDegrees(this.leftGills.yRot, -this.topGills.xRot);
		this.rightGills.yRot = this.lerpAngleDegrees(this.rightGills.yRot, -this.leftGills.yRot);
		this.setAngles(this.leftHindLeg, 1.1F, 1.0F, 0.0F);
		this.setAngles(this.leftFrontLeg, 0.8F, 2.3F, -0.5F);
		this.copyLegAngles();
		this.body.xRot = this.lerpAngleDegrees(0.2F, this.body.xRot, 0.0F);
		this.body.yRot = this.lerpAngleDegrees(this.body.yRot, headYaw * (float) (Math.PI / 180.0));
		this.body.zRot = this.lerpAngleDegrees(this.body.zRot, 0.0F);
	}

	private void setMovingOnGroundAngles(float animationProgress, float headYaw) {
		float f = animationProgress * 0.25F;
		float g = Mth.cos(f);
		float h = (g * g - 2.0F * g) / 5.0F;
		float i = 0.7F * g;
		this.head.xRot = this.lerpAngleDegrees(this.head.xRot, 0.0F);
		this.head.yRot = this.lerpAngleDegrees(this.head.yRot, 0.09F * g);
		this.head.zRot = this.lerpAngleDegrees(this.head.zRot, 0.0F);
		this.tail.yRot = this.lerpAngleDegrees(this.tail.yRot, this.head.yRot);
		this.topGills.xRot = this.lerpAngleDegrees(this.topGills.xRot, 0.6F - 0.08F * (g * g + 2.0F * Mth.sin(f)));
		this.leftGills.yRot = this.lerpAngleDegrees(this.leftGills.yRot, -this.topGills.xRot);
		this.rightGills.yRot = this.lerpAngleDegrees(this.rightGills.yRot, -this.leftGills.yRot);
		this.setAngles(this.leftHindLeg, 0.9424779F, 1.5F - h, -0.1F);
		this.setAngles(this.leftFrontLeg, 1.0995574F, (float) (Math.PI / 2) - i, 0.0F);
		this.setAngles(this.rightHindLeg, this.leftHindLeg.xRot, -1.0F - h, 0.0F);
		this.setAngles(this.rightFrontLeg, this.leftFrontLeg.xRot, (float) (-Math.PI / 2) - i, 0.0F);
		this.body.xRot = this.lerpAngleDegrees(0.2F, this.body.xRot, 0.0F);
		this.body.yRot = this.lerpAngleDegrees(this.body.yRot, headYaw * (float) (Math.PI / 180.0));
		this.body.zRot = this.lerpAngleDegrees(this.body.zRot, 0.0F);
	}

	private void setStandingInWaterAngles(float animationProgress) {
		float f = animationProgress * 0.075F;
		float g = Mth.cos(f);
		float h = Mth.sin(f) * 0.15F;
		this.body.xRot = this.lerpAngleDegrees(this.body.xRot, -0.15F + 0.075F * g);
		this.body.y -= h;
		this.head.xRot = this.lerpAngleDegrees(this.head.xRot, -this.body.xRot);
		this.topGills.xRot = this.lerpAngleDegrees(this.topGills.xRot, 0.2F * g);
		this.leftGills.yRot = this.lerpAngleDegrees(this.leftGills.yRot, -0.3F * g - 0.19F);
		this.rightGills.yRot = this.lerpAngleDegrees(this.rightGills.yRot, -this.leftGills.yRot);
		this.setAngles(this.leftHindLeg, (float) (Math.PI * 3.0 / 4.0) - g * 0.11F, 0.47123894F, 1.7278761F);
		this.setAngles(this.leftFrontLeg, (float) (Math.PI / 4) - g * 0.2F, 2.042035F, 0.0F);
		this.copyLegAngles();
		this.tail.yRot = this.lerpAngleDegrees(this.tail.yRot, 0.5F * g);
		this.head.yRot = this.lerpAngleDegrees(this.head.yRot, 0.0F);
		this.head.zRot = this.lerpAngleDegrees(this.head.zRot, 0.0F);
	}

	private void setMovingInWaterAngles(float animationProgress, float headPitch) {
		float f = animationProgress * 0.33F;
		float g = Mth.sin(f);
		float h = Mth.cos(f);
		float i = 0.13F * g;
		this.body.xRot = this.lerpAngleDegrees(0.1F, this.body.xRot, headPitch * (float) (Math.PI / 180.0) + i);
		this.head.xRot = -i * 1.8F;
		this.body.y -= 0.45F * h;
		this.topGills.xRot = this.lerpAngleDegrees(this.topGills.xRot, -0.5F * g - 0.8F);
		this.leftGills.yRot = this.lerpAngleDegrees(this.leftGills.yRot, 0.3F * g + 0.9F);
		this.rightGills.yRot = this.lerpAngleDegrees(this.rightGills.yRot, -this.leftGills.yRot);
		this.tail.yRot = this.lerpAngleDegrees(this.tail.yRot, 0.3F * Mth.cos(f * 0.9F));
		this.setAngles(this.leftHindLeg, 1.8849558F, -0.4F * g, (float) (Math.PI / 2));
		this.setAngles(this.leftFrontLeg, 1.8849558F * h + (float) (Math.PI), -0.2F * h - 0.1F, (float) (Math.PI / 2) + 0.5F);
		this.copyLegAngles();
		this.setAngles(this.rightFrontLeg, 1.8849558F * g + (float) (Math.PI), -0.2F * g - 0.1F, -this.leftFrontLeg.zRot);
		this.head.yRot = this.lerpAngleDegrees(this.head.yRot, 0.0F);
		this.head.zRot = this.lerpAngleDegrees(this.head.zRot, 0.0F);
	}

	private void copyLegAngles() {
		this.setAngles(this.rightHindLeg, this.leftHindLeg.xRot, -this.leftHindLeg.yRot, -this.leftHindLeg.zRot);
		this.setAngles(this.rightFrontLeg, this.leftFrontLeg.xRot, -this.leftFrontLeg.yRot, -this.leftFrontLeg.zRot);
	}
}