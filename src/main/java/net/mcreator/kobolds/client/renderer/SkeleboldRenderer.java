package net.mcreator.kobolds.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.mcreator.kobolds.entity.SkeleboldEntity;
import net.mcreator.kobolds.client.model.SkeleboldModel;

import com.mojang.blaze3d.vertex.PoseStack;

public class SkeleboldRenderer extends MobRenderer<SkeleboldEntity, SkeleboldModel<SkeleboldEntity>> {
	public SkeleboldRenderer(EntityRendererProvider.Context context) {
		super(context, new SkeleboldModel(context.bakeLayer(SkeleboldModel.SKELEBOLD_MODEL)), 0.36f);
		this.addLayer(new ItemInHandLayer<>(this));
		this.addLayer(new EyesLayer<SkeleboldEntity, SkeleboldModel<SkeleboldEntity>>(this) {
			@Override
			public RenderType renderType() {
				return RenderType.eyes(new ResourceLocation("kobolds:textures/kobold_skeleton_glow.png"));
			}
		});
	}

	@Override
	public ResourceLocation getTextureLocation(SkeleboldEntity entity) {
		return new ResourceLocation("kobolds:textures/kobold_skeleton.png");
	}

	@Override
	public void render(SkeleboldEntity kobold, float f1, float f2, PoseStack stack, MultiBufferSource buffer, int inty) {
		stack.pushPose();
		stack.translate(-0.025, 0, 0);
		float scale = 0.875F;
		stack.scale(scale, scale, scale);
		super.render(kobold, f1, f2, stack, buffer, inty);
		stack.popPose();
	}
}
