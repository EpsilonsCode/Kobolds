package net.mcreator.kobolds.client.model;

import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.ArmedModel;

import net.mcreator.kobolds.entity.AbstractKoboldEntity;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class KoboldModel<T extends AbstractKoboldEntity> extends EntityModel<T> implements ArmedModel, HeadedModel {
	public static final ModelLayerLocation KOBOLD_MODEL = new ModelLayerLocation(new ResourceLocation("kobolds", "kobold"), "main");
	private final ModelPart head;
	private final ModelPart rightarm;
	private final ModelPart leftarm;
	private final ModelPart rightleg;
	private final ModelPart leftleg;
	private final ModelPart body;

	public KoboldModel(ModelPart root) {
		this.head = root.getChild("head");
		this.rightarm = root.getChild("rightarm");
		this.leftarm = root.getChild("leftarm");
		this.rightleg = root.getChild("rightleg");
		this.leftleg = root.getChild("leftleg");
		this.body = root.getChild("body");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition head = partdefinition.addOrReplaceChild("head",
				CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -7.0F, -3.5F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)).texOffs(22, 0)
						.addBox(-2.5F, -3.0F, -6.5F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(1, 3)
						.addBox(-0.5F, -3.85F, -5.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.5F, 4.0F, -0.5F));
		PartDefinition lefthorn = head.addOrReplaceChild("lefthorn",
				CubeListBuilder.create().texOffs(36, 0).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.0F, -7.0F, 2.0F, -0.6109F, 0.3054F, 0.1745F));
		PartDefinition righthorn = head.addOrReplaceChild("righthorn",
				CubeListBuilder.create().texOffs(45, 0).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -7.0F, 2.0F, -0.6109F, -0.3054F, -0.1745F));
		PartDefinition rightarm = partdefinition.addOrReplaceChild("rightarm",
				CubeListBuilder.create().texOffs(46, 16).addBox(-3.0F, -0.85F, -1.5F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-2.5F, 5.0F, 0.0F));
		PartDefinition leftarm = partdefinition.addOrReplaceChild("leftarm",
				CubeListBuilder.create().texOffs(33, 16).addBox(0.0F, -0.85F, -1.5F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offset(3.5F, 5.0F, 0.0F));
		PartDefinition rightleg = partdefinition.addOrReplaceChild("rightleg",
				CubeListBuilder.create().texOffs(13, 31).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-1.0F, 14.0F, 0.0F));
		PartDefinition leftleg = partdefinition.addOrReplaceChild("leftleg",
				CubeListBuilder.create().texOffs(0, 31).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offset(2.0F, 14.0F, 0.0F));
		PartDefinition body = partdefinition.addOrReplaceChild("body",
				CubeListBuilder.create().texOffs(3, 15).addBox(-3.0F, -10.0F, -2.0F, 6.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.5F, 14.0F, 0.0F));
		PartDefinition tail = body.addOrReplaceChild("tail",
				CubeListBuilder.create().texOffs(24, 15).addBox(-1.0F, -3.0F, 2.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.6109F, 0.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	public void setRotateAngle(ModelPart ModelRenderer, float x, float y, float z) {
		ModelRenderer.xRot = x;
		ModelRenderer.yRot = y;
		ModelRenderer.zRot = z;
	}

	@Override
	public void setupAnim(T kobold, float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch) {
		this.rightarm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F;
		this.leftarm.xRot = Mth.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
		this.rightarm.yRot = 0.0F;
		this.rightarm.zRot = 0.0F;
		this.leftarm.yRot = 0.0F;
		this.leftarm.zRot = 0.0F;
		this.rightleg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.leftleg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		this.head.yRot = headYaw * ((float) Math.PI / 180F);
		this.head.xRot = headPitch * ((float) Math.PI / 180F);
		
		this.rightarm.zRot += Mth.cos(ageInTicks * 0.04F) * 0.04F + 0.04F;
		this.leftarm.zRot -= Mth.cos(ageInTicks * 0.04F) * 0.04F + 0.04F;

		if (this.riding) {
			this.rightleg.xRot = -1.5708F;
			this.leftleg.xRot = -1.5708F;
			this.rightleg.yRot = 0.2618F;
			this.leftleg.yRot = -0.2618F;
		}
		
		if (kobold.hasItemInSlot(EquipmentSlot.MAINHAND)) {
			if (kobold.isAggressive()) {
				if (kobold.isLeftHanded()) {
					this.leftarm.xRot = -2.0944F;
					this.leftarm.yRot = -0.1745F;
				} else {
					this.rightarm.xRot = -2.0944F;
					this.rightarm.yRot = 0.1745F;
				}
			} else if (kobold.getMainHandItem().getItem() instanceof CrossbowItem) {
				if (kobold.isLeftHanded()) {
					if (kobold.isCharging()) {
						this.leftarm.xRot = -0.6981F;
						this.leftarm.yRot = 0.3491F;
						this.rightarm.xRot = -1.1345F;
						this.rightarm.yRot = -0.5672F;
					} else if (CrossbowItem.isCharged(kobold.getMainHandItem())) {
						this.leftarm.xRot = -1.4399F;
						this.leftarm.yRot = 0.2618F;
						this.rightarm.xRot = -1.3963F;
						this.rightarm.yRot = -0.3054F;
					}
				} else {
					if (kobold.isCharging()) {
						this.rightarm.xRot = -0.6981F;
						this.rightarm.yRot = -0.3491F;
						this.leftarm.xRot = -1.1345F;
						this.leftarm.yRot = 0.5672F;
					} else if (CrossbowItem.isCharged(kobold.getMainHandItem())) {
						this.rightarm.xRot = -1.4399F;
						this.rightarm.yRot = -0.2618F;
						this.leftarm.xRot = -1.3963F;
						this.leftarm.yRot = 0.3054F;
					}
				}
			}
		}
		
		if (kobold.hasItemInSlot(EquipmentSlot.OFFHAND)) {
			if (kobold.getOffhandItem().getItem() instanceof TridentItem) {
				if (kobold.isAggressive()) {
					if (kobold.isLeftHanded()) {
						this.rightarm.xRot = 2.8798F;
						this.leftarm.xRot = 0.0F;
					} else {
						this.leftarm.xRot = 2.8798F;
						this.rightarm.xRot = 0.0F;
					}
				}
			} else if (kobold.getOffhandItem().getItem() instanceof ShieldItem) {
				if (kobold.isBlocking()) {
					if (kobold.isLeftHanded()) {
						this.rightarm.xRot = -0.6981F;
						this.rightarm.yRot = -0.2618F;
					} else {
						this.leftarm.xRot = -0.6981F;
						this.leftarm.yRot = 0.2618F;
					}
				}
			} else {
				if (kobold.isLeftHanded()) {
					this.rightarm.xRot = -0.8727F;
					this.rightarm.yRot = 0.0873F;
					this.head.xRot = -0.2618F;
					this.head.xRot = 0.1745F;
				} else {
					this.leftarm.xRot = -0.8727F;
					this.leftarm.yRot = 0.0873F;
					this.head.xRot = -0.2618F;
					this.head.xRot = 0.1745F;
				}
			}
		}
		
		if (this.attackTime > 0.0F) {
			if (kobold.isAggressive()) {
				if (kobold.isLeftHanded()) {
					float progress = this.attackTime;
					progress = 1.0F - this.attackTime;
					progress = progress * progress;
					progress = progress * progress;
					progress = 1.0F - progress;
					float f2 = Mth.sin(progress * (float) Math.PI);
					leftarm.xRot = (float) ((double) leftarm.xRot - ((double) f2 / 1.2D - (double) 1.0F));
				} else {
					float progress = this.attackTime;
					progress = 1.0F - this.attackTime;
					progress = progress * progress;
					progress = progress * progress;
					progress = 1.0F - progress;
					float f2 = Mth.sin(progress * (float) Math.PI);
					rightarm.xRot = (float) ((double) rightarm.xRot - ((double) f2 / 1.2D - (double) 1.0F));
				}
			} else {
				if (kobold.hasItemInSlot(EquipmentSlot.OFFHAND)) {
					float progress = this.attackTime;
					this.body.yRot = Mth.sin(Mth.sqrt(progress) * ((float) Math.PI * 2F)) * 0.2F;
					this.rightarm.yRot += this.body.yRot;
					this.leftarm.yRot += this.body.yRot;
					this.leftarm.xRot += this.body.yRot;
					progress = 1.0F - this.attackTime;
					progress = progress * progress;
					progress = progress * progress;
					progress = 1.0F - progress;
					float f2 = Mth.sin(progress * (float) Math.PI);
					float f3 = Mth.sin(this.attackTime * (float) Math.PI) * -(this.head.xRot - 0.7F) * 0.75F;
					rightarm.xRot = (float) ((double) rightarm.xRot - ((double) f2 * 1.2D + (double) f3));
					rightarm.yRot += this.body.yRot * 2.0F;
					rightarm.zRot += Mth.sin(this.attackTime * (float) Math.PI) * -0.4F;
				}
			}
		}
	}

	@Override
	public void translateToHand(HumanoidArm arm, PoseStack poseStack) {
		switch (arm) {
			case LEFT -> {
				this.leftarm.translateAndRotate(poseStack);
				poseStack.translate(0.045, 0.05, 0.0);
				poseStack.scale(0.75F, 0.75F, 0.75F);
			}
			case RIGHT -> {
				this.rightarm.translateAndRotate(poseStack);
				poseStack.translate(-0.045, 0.05, 0.0);
				poseStack.scale(0.75F, 0.75F, 0.75F);
			}
		}
	}

	@Override
	public ModelPart getHead() {
		return this.head;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue,
			float alpha) {
		head.render(poseStack, buffer, packedLight, packedOverlay);
		body.render(poseStack, buffer, packedLight, packedOverlay);
		rightarm.render(poseStack, buffer, packedLight, packedOverlay);
		rightleg.render(poseStack, buffer, packedLight, packedOverlay);
		leftarm.render(poseStack, buffer, packedLight, packedOverlay);
		leftleg.render(poseStack, buffer, packedLight, packedOverlay);
	}
}
