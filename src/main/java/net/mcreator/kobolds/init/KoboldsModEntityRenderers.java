
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.kobolds.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.mcreator.kobolds.client.renderer.ZomboldRenderer;
import net.mcreator.kobolds.client.renderer.SkeleboldRenderer;
import net.mcreator.kobolds.client.renderer.KoboldWarriorRenderer;
import net.mcreator.kobolds.client.renderer.KoboldRenderer;
import net.mcreator.kobolds.client.renderer.KoboldPirateRenderer;
import net.mcreator.kobolds.client.renderer.KoboldEngineerRenderer;
import net.mcreator.kobolds.client.renderer.KoboldEnchanterRenderer;
import net.mcreator.kobolds.client.renderer.KoboldChildRenderer;
import net.mcreator.kobolds.client.renderer.KoboldCaptainRenderer;
import net.mcreator.kobolds.client.renderer.DrownedZomboldRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KoboldsModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(KoboldsModEntities.KOBOLD, KoboldRenderer::new);
		event.registerEntityRenderer(KoboldsModEntities.KOBOLD_WARRIOR, KoboldWarriorRenderer::new);
		event.registerEntityRenderer(KoboldsModEntities.KOBOLD_ENCHANTER, KoboldEnchanterRenderer::new);
		event.registerEntityRenderer(KoboldsModEntities.KOBOLD_ENGINEER, KoboldEngineerRenderer::new);
		event.registerEntityRenderer(KoboldsModEntities.KOBOLD_PIRATE, KoboldPirateRenderer::new);
		event.registerEntityRenderer(KoboldsModEntities.KOBOLD_CAPTAIN, KoboldCaptainRenderer::new);
		event.registerEntityRenderer(KoboldsModEntities.KOBOLD_CHILD, KoboldChildRenderer::new);
		event.registerEntityRenderer(KoboldsModEntities.ZOMBOLD, ZomboldRenderer::new);
		event.registerEntityRenderer(KoboldsModEntities.DROWNED_ZOMBOLD, DrownedZomboldRenderer::new);
		event.registerEntityRenderer(KoboldsModEntities.SKELEBOLD, SkeleboldRenderer::new);
	}
}
