package net.mcreator.kobolds.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.mcreator.kobolds.entity.KoboldEntity;
import net.mcreator.kobolds.client.model.KoboldModel;

import com.mojang.blaze3d.vertex.PoseStack;

public class KoboldRenderer extends MobRenderer<KoboldEntity, KoboldModel<KoboldEntity>> {
	public KoboldRenderer(EntityRendererProvider.Context context) {
		super(context, new KoboldModel(context.bakeLayer(KoboldModel.KOBOLD_MODEL)), 0.36f);
		this.addLayer(new ItemInHandLayer<>(this));
		this.addLayer(new EyesLayer<KoboldEntity, KoboldModel<KoboldEntity>>(this) {
			@Override
			public RenderType renderType() {
				return RenderType.eyes(new ResourceLocation("kobolds:textures/kobold_glow.png"));
			}
		});
	}

	@Override
	public ResourceLocation getTextureLocation(KoboldEntity entity) {
		return new ResourceLocation("kobolds:textures/kobold_base.png");
	}

	@Override
	public void render(KoboldEntity kobold, float f1, float f2, PoseStack stack, MultiBufferSource buffer, int inty) {
		stack.pushPose();
		stack.translate(-0.025, 0, 0);
		float scale = 0.88F;
		stack.scale(scale, scale, scale);
		super.render(kobold, f1, f2, stack, buffer, inty);
		stack.popPose();
	}
}
