package net.mcreator.kobolds.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;

import net.mcreator.kobolds.KoboldsMod;

import java.util.Map;

public class KoboldPotionFightProcedure {
	public static void execute(Map<String, Object> dependencies) {
		Entity entity = (Entity) dependencies.get("entity");
		if (entity.isAlive()) {
			if (entity instanceof LivingEntity livingentity) {			
				livingentity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2700, 0));
				livingentity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2700, 0));
			}
		}
	}
}
