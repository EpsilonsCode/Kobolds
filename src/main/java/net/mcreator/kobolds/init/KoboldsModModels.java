package net.mcreator.kobolds.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.mcreator.kobolds.client.model.KoboldModel;
import net.mcreator.kobolds.client.model.KoboldChildModel;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class KoboldsModModels {
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(KoboldModel.KOBOLD_MODEL, KoboldModel::createBodyLayer);
		event.registerLayerDefinition(KoboldChildModel.KOBOLD_CHILD_MODEL, KoboldChildModel::createBodyLayer);
	}
}
