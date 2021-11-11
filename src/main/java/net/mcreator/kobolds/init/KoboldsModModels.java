package net.mcreator.kobolds.init;

import net.mcreator.kobolds.client.model.TestModelKobold;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.mcreator.kobolds.client.model.KoboldModel;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class KoboldsModModels {
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {

		event.registerLayerDefinition(ModelKobold.LAYER_LOCATION, TestModelKobold::createBodyLayer);
	}
}
