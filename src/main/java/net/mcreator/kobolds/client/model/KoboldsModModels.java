package net.mcreator.kobolds.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.mcreator.kobolds.client.model.KoboldModel;
import net.mcreator.kobolds.client.model.KoboldChildModel;
import net.mcreator.kobolds.client.model.ZomboldModel;
import net.mcreator.kobolds.client.model.SkeleboldModel;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class KoboldsModModels {
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(KoboldModel.KOBOLD_MODEL, KoboldModel::createBodyLayer);
		event.registerLayerDefinition(KoboldChildModel.KOBOLD_CHILD_MODEL, KoboldChildModel::createBodyLayer);
		event.registerLayerDefinition(ZomboldModel.ZOMBOLD_MODEL, ZomboldModel::createBodyLayer);
		event.registerLayerDefinition(SkeleboldModel.SKELEBOLD_MODEL, SkeleboldModel::createBodyLayer);
	}
}
