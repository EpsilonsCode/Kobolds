package net.mcreator.kobolds.procedures;

import net.minecraftforge.common.BiomeDictionary;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.Registry;
import net.minecraft.core.BlockPos;

import net.mcreator.kobolds.init.KoboldsModEntities;
import net.mcreator.kobolds.entity.KoboldPirateEntity;
import net.mcreator.kobolds.entity.KoboldEntity;
import net.mcreator.kobolds.entity.KoboldEngineerEntity;
import net.mcreator.kobolds.entity.KoboldEnchanterEntity;
import net.mcreator.kobolds.entity.KoboldCaptainEntity;
import net.mcreator.kobolds.KoboldsMod;

import java.util.Map;

public class KoboldBaseTickChildProcedure {
	public static void execute(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				KoboldsMod.LOGGER.warn("Failed to load dependency entity for procedure KoboldBaseTickChild!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				KoboldsMod.LOGGER.warn("Failed to load dependency world for procedure KoboldBaseTickChild!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		LevelAccessor world = (LevelAccessor) dependencies.get("world");
		if (entity.getPersistentData().getDouble("TimerGrow") < 24000) {
			entity.getPersistentData().putDouble("TimerGrow", (entity.getPersistentData().getDouble("TimerGrow") + 1));
		} else if (entity.getPersistentData().getDouble("TimerGrow") >= 24000) {
			if (!entity.level.isClientSide())
				entity.discard();
			if (world.getBiome(new BlockPos((int) (entity.getX()), (int) (entity.getY()), (int) (entity.getZ()))).getRegistryName() != null
					&& BiomeDictionary
							.hasType(
									ResourceKey.create(Registry.BIOME_REGISTRY,
											world.registryAccess().registryOrThrow(Registry.BIOME_REGISTRY)
													.getKey(world.getBiome(
															new BlockPos((int) (entity.getX()), (int) (entity.getY()), (int) (entity.getZ()))))),
									BiomeDictionary.Type.JUNGLE)) {
				if (Math.random() <= 0.02) {
					if (world instanceof ServerLevel _level) {
						Entity entityToSpawn = new KoboldCaptainEntity(KoboldsModEntities.KOBOLD_CAPTAIN, _level);
						entityToSpawn.moveTo((entity.getX()), (entity.getY()), (entity.getZ()), world.getRandom().nextFloat() * 360F, 0);
						if (entityToSpawn instanceof Mob _mobToSpawn)
							_mobToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED,
									null, null);
						world.addFreshEntity(entityToSpawn);
					}
				} else {
					if (world instanceof ServerLevel _level) {
						Entity entityToSpawn = new KoboldPirateEntity(KoboldsModEntities.KOBOLD_PIRATE, _level);
						entityToSpawn.moveTo((entity.getX()), (entity.getY()), (entity.getZ()), world.getRandom().nextFloat() * 360F, 0);
						if (entityToSpawn instanceof Mob _mobToSpawn)
							_mobToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED,
									null, null);
						world.addFreshEntity(entityToSpawn);
					}
				}
			} else if (world.getBiome(new BlockPos((int) (entity.getX()), (int) (entity.getY()), (int) (entity.getZ()))).getRegistryName() != null
					&& BiomeDictionary
							.hasType(
									ResourceKey.create(Registry.BIOME_REGISTRY,
											world.registryAccess().registryOrThrow(Registry.BIOME_REGISTRY)
													.getKey(world.getBiome(
															new BlockPos((int) (entity.getX()), (int) (entity.getY()), (int) (entity.getZ()))))),
									BiomeDictionary.Type.MOUNTAIN)) {
				if (Math.random() <= 0.1) {
					if (world instanceof ServerLevel _level) {
						Entity entityToSpawn = new KoboldEngineerEntity(KoboldsModEntities.KOBOLD_ENGINEER, _level);
						entityToSpawn.moveTo((entity.getX()), (entity.getY()), (entity.getZ()), world.getRandom().nextFloat() * 360F, 0);
						if (entityToSpawn instanceof Mob _mobToSpawn)
							_mobToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED,
									null, null);
						world.addFreshEntity(entityToSpawn);
					}
				} else {
					if (world instanceof ServerLevel _level) {
						Entity entityToSpawn = new KoboldEntity(KoboldsModEntities.KOBOLD, _level);
						entityToSpawn.moveTo((entity.getX()), (entity.getY()), (entity.getZ()), world.getRandom().nextFloat() * 360F, 0);
						if (entityToSpawn instanceof Mob _mobToSpawn)
							_mobToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED,
									null, null);
						world.addFreshEntity(entityToSpawn);
					}
				}
			} else if (Math.random() <= 0.1) {
				if (world instanceof ServerLevel _level) {
					Entity entityToSpawn = new KoboldEnchanterEntity(KoboldsModEntities.KOBOLD_ENCHANTER, _level);
					entityToSpawn.moveTo((entity.getX()), (entity.getY()), (entity.getZ()), world.getRandom().nextFloat() * 360F, 0);
					if (entityToSpawn instanceof Mob _mobToSpawn)
						_mobToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED,
								null, null);
					world.addFreshEntity(entityToSpawn);
				}
			} else {
				if (world instanceof ServerLevel _level) {
					Entity entityToSpawn = new KoboldEntity(KoboldsModEntities.KOBOLD, _level);
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
