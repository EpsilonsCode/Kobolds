package net.mcreator.kobolds.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.Difficulty;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

import net.mcreator.kobolds.init.KoboldsModEntities;
import net.mcreator.kobolds.entity.SkeleboldEntity;
import net.mcreator.kobolds.KoboldsMod;

import java.util.Map;

public class SkeleboldSummonProcedure {
	public static void execute(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				KoboldsMod.LOGGER.warn("Failed to load dependency x for procedure SkeleboldSummon!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				KoboldsMod.LOGGER.warn("Failed to load dependency y for procedure SkeleboldSummon!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				KoboldsMod.LOGGER.warn("Failed to load dependency z for procedure SkeleboldSummon!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				KoboldsMod.LOGGER.warn("Failed to load dependency world for procedure SkeleboldSummon!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		LevelAccessor world = (LevelAccessor) dependencies.get("world");
		if (world.canSeeSkyFromBelowWater(new BlockPos((int) (Math.floor(x)), (int) (Math.floor(y)), (int) (Math.floor(z))))
				&& !(world instanceof Level _lvl ? _lvl.isDay() : false)
				&& (world.getDifficulty() == Difficulty.NORMAL || world.getDifficulty() == Difficulty.HARD)) {
			if (!world
					.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3((Math.floor(x)), (Math.floor(y)), (Math.floor(z))), 64, 64, 64), e -> true)
					.isEmpty()
					|| !world.getEntitiesOfClass(ServerPlayer.class,
							AABB.ofSize(new Vec3((Math.floor(x)), (Math.floor(y)), (Math.floor(z))), 64, 64, 64), e -> true).isEmpty()) {
				world.destroyBlock(new BlockPos((int) (Math.floor(x)), (int) (Math.floor(y)), (int) (Math.floor(z))), false);
				if (world instanceof ServerLevel _level) {
					LightningBolt entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level);
					entityToSpawn.moveTo(Vec3.atBottomCenterOf(new BlockPos((int) (Math.floor(x)), (int) (Math.floor(y)), (int) (Math.floor(z)))));
					entityToSpawn.setVisualOnly(true);
					_level.addFreshEntity(entityToSpawn);
				}
				if (world instanceof ServerLevel _level) {
					Entity entityToSpawn = new SkeleboldEntity(KoboldsModEntities.SKELEBOLD, _level);
					entityToSpawn.moveTo((Math.floor(x)), (Math.floor(y)), (Math.floor(z)), world.getRandom().nextFloat() * 360F, 0);
					if (entityToSpawn instanceof Mob _mobToSpawn)
						_mobToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED,
								null, null);
					world.addFreshEntity(entityToSpawn);
				}
			}
		}
	}
}
