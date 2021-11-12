package net.mcreator.kobolds.client.renderer;

import net.minecraft.world.entity.Mob;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.mcreator.kobolds.client.model.KoboldModel;

import com.mojang.blaze3d.vertex.PoseStack;

public class KoboldRenderer extends HumanoidMobRenderer<Mob, KoboldModel<Mob>> {
	public KoboldRenderer(EntityRendererProvider.Context context) {
		super(context, new KoboldModel(context.bakeLayer(KoboldModel.KOBOLD_MODEL)), 0.36f);
		this.addLayer(new EyesLayer<Mob, KoboldModel<Mob>>(this) {
			@Override
			public RenderType renderType() {
				return RenderType.eyes(new ResourceLocation("kobolds:textures/kobold_glow.png"));
			}
		});
	}

	@Override
	public ResourceLocation getTextureLocation(Mob entity) {
		return new ResourceLocation("kobolds:textures/kobold_base.png");
	}

	@Override
	public void render(Mob p_115455_, float p_115456_, float p_115457_, PoseStack stack, MultiBufferSource p_115459_, int p_115460_) {
		stack.pushPose();
		stack.translate(-0.025, -0.1, 0);
		float scale = 0.88F;
		stack.scale(scale, scale, scale);
		super.render(p_115455_, p_115456_, p_115457_, stack, p_115459_, p_115460_);
		stack.popPose();
	}
}
