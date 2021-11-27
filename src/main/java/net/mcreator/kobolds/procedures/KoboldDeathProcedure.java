package net.mcreator.kobolds.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.Difficulty;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.Minecraft;

import net.mcreator.kobolds.init.KoboldsModEntities;
import net.mcreator.kobolds.entity.ZomboldEntity;

public class KoboldDeathProcedure {
	public static void execute(LevelAccessor world, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		if (sourceentity instanceof Zombie && !(entity instanceof LivingEntity _livEnt ? _livEnt.isBaby() : false)) {
			if (world.getDifficulty() == Difficulty.HARD) {
				if (!entity.level.isClientSide())
					entity.discard();
				if (world instanceof Level _level)
					_level.playSound(_level.isClientSide() ? Minecraft.getInstance().player : null, (entity.getX()), (entity.getY()), (entity.getZ()),
							ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie_villager.converted")), SoundSource.NEUTRAL, 1,
							(float) 0.7);
				if (world instanceof ServerLevel _level) {
					Entity entityToSpawn = new ZomboldEntity(KoboldsModEntities.ZOMBOLD, _level);
					entityToSpawn.moveTo((entity.getX()), (entity.getY()), (entity.getZ()), world.getRandom().nextFloat() * 360F, 0);
					if (entityToSpawn instanceof Mob _mobToSpawn)
						_mobToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED,
								null, null);
					world.addFreshEntity(entityToSpawn);
				}
			} else if (world.getDifficulty() == Difficulty.NORMAL && Math.random() >= 0.5) {
				if (!entity.level.isClientSide())
					entity.discard();
				if (world instanceof Level _level)
					_level.playSound(_level.isClientSide() ? Minecraft.getInstance().player : null, (entity.getX()), (entity.getY()), (entity.getZ()),
							ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie_villager.converted")), SoundSource.NEUTRAL, 1,
							(float) 0.7);
				if (world instanceof ServerLevel _level) {
					Entity entityToSpawn = new ZomboldEntity(KoboldsModEntities.ZOMBOLD, _level);
					entityToSpawn.moveTo((entity.getX()), (entity.getY()), (entity.getZ()), world.getRandom().nextFloat() * 360F, 0);
					if (entityToSpawn instanceof Mob _mobToSpawn)
						_mobToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED,
								null, null);
					world.addFreshEntity(entityToSpawn);
				}
			}
		}
	}
}
