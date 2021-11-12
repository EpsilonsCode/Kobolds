
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.kobolds.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.mcreator.kobolds.client.renderer.KoboldWarriorRenderer;
import net.mcreator.kobolds.client.renderer.KoboldRenderer;
import net.mcreator.kobolds.client.renderer.KoboldEnchanterRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KoboldsModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(KoboldsModEntities.KOBOLD, KoboldRenderer::new);
		event.registerEntityRenderer(KoboldsModEntities.KOBOLD_WARRIOR, KoboldWarriorRenderer::new);
		event.registerEntityRenderer(KoboldsModEntities.KOBOLD_ENCHANTER, KoboldEnchanterRenderer::new);
	}
}
