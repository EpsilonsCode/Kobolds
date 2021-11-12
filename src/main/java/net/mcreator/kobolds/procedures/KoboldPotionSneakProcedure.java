package net.mcreator.kobolds.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;

import net.mcreator.kobolds.KoboldsMod;

import java.util.Map;

public class KoboldPotionSneakProcedure {
	public static void execute(Map<String, Object> dependencies) {
		Entity entity = (Entity) dependencies.get("entity");
		if (entity.isAlive()) {
			if (entity instanceof LivingEntity livingentity) {
				livingentity.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 2700, 0));
				livingentity.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 2700, 0));
			}
		}
	}
}
