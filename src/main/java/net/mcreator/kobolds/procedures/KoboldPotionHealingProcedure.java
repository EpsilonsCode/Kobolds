package net.mcreator.kobolds.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;

import net.mcreator.kobolds.KoboldsMod;

import java.util.Map;

public class KoboldPotionHealingProcedure {
	public static void execute(Map<String, Object> dependencies) {
		Entity entity = (Entity) dependencies.get("entity");
		if (entity.isAlive()) {
			if (entity instanceof LivingEntity _entity)
				_entity.addEffect(new MobEffectInstance(MobEffects.HEAL, 1, 1));
			if (entity instanceof LivingEntity _entity)
				_entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 360, 0));
		}
	}
}
