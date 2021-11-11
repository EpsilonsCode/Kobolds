package net.mcreator.kobolds.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.mcreator.kobolds.client.model.TestModelKobold;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;

import net.mcreator.kobolds.entity.KoboldEntity;

import net.mcreator.kobolds.client.model.ModelKobold;
import net.minecraft.world.entity.Mob;

public class KoboldRenderer extends HumanoidMobRenderer<Mob, TestModelKobold<Mob>> {
	public KoboldRenderer(EntityRendererProvider.Context context) {
		super(context, new TestModelKobold(context.bakeLayer(ModelKobold.LAYER_LOCATION)), 0.36f);

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
		super.render( p_115455_,  p_115456_,  p_115457_,  stack,  p_115459_,  p_115460_);
		stack.popPose();
	}
}
