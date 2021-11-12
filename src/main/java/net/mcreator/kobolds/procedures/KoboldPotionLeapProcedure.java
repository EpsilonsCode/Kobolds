package net.mcreator.kobolds.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;

import net.mcreator.kobolds.KoboldsMod;

import java.util.Map;

public class KoboldPotionLeapProcedure {
	public static void execute(Map<String, Object> dependencies) {
		Entity entity = (Entity) dependencies.get("entity");
		if (entity.isAlive()) {
			if (entity instanceof LivingEntity livingentity) {
				livingentity.addEffect(new MobEffectInstance(MobEffects.JUMP, 1200, 0));
				livingentity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 1200, 0));
			}
		}
	}
}
