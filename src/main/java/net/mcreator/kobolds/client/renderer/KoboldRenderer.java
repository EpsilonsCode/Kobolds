package net.mcreator.kobolds.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;

import net.mcreator.kobolds.entity.KoboldEntity;
import net.mcreator.kobolds.client.model.KoboldModel;

public class KoboldRenderer extends MobRenderer<KoboldEntity, KoboldModel<KoboldEntity>> {
	public KoboldRenderer(EntityRendererProvider.Context context) {
		super(context, new KoboldModel(context.bakeLayer(KoboldModel.LAYER_LOCATION)), 0.36f);
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
}
