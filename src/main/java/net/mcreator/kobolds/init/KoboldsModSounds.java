
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.kobolds.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;
import java.util.HashMap;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class KoboldsModSounds {
	public static Map<ResourceLocation, SoundEvent> REGISTRY = new HashMap<>();
	static {
		REGISTRY.put(new ResourceLocation("kobolds", "kobold_idle"), new SoundEvent(new ResourceLocation("kobolds", "kobold_idle")));
		REGISTRY.put(new ResourceLocation("kobolds", "kobold_hurt"), new SoundEvent(new ResourceLocation("kobolds", "kobold_hurt")));
		REGISTRY.put(new ResourceLocation("kobolds", "kobold_death"), new SoundEvent(new ResourceLocation("kobolds", "kobold_death")));
		REGISTRY.put(new ResourceLocation("kobolds", "kobold_purr"), new SoundEvent(new ResourceLocation("kobolds", "kobold_purr")));
		REGISTRY.put(new ResourceLocation("kobolds", "kobold_trade"), new SoundEvent(new ResourceLocation("kobolds", "kobold_trade")));
	}

	@SubscribeEvent
	public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
		for (Map.Entry<ResourceLocation, SoundEvent> sound : REGISTRY.entrySet())
			event.getRegistry().register(sound.getValue().setRegistryName(sound.getKey()));
	}
}
