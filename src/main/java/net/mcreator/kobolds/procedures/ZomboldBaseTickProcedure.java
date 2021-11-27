package net.mcreator.kobolds.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.client.Minecraft;

import net.mcreator.kobolds.init.KoboldsModEntities;
import net.mcreator.kobolds.entity.DrownedZomboldEntity;

public class ZomboldBaseTickProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (entity.getPersistentData().getDouble("Drown") < 600) {
			if (entity.isInWater()
					&& ((world.getBlockState(new BlockPos((int) (entity.getX() - 1), (int) (entity.getY() + 1), (int) (entity.getZ() - 1))))
							.getBlock() == Blocks.WATER
							|| (world.getBlockState(new BlockPos((int) (entity.getX() - 1), (int) (entity.getY() + 1), (int) (entity.getZ() - 1))))
									.getBlock() == Blocks.WATER)) {
				entity.getPersistentData().putDouble("Drown", (entity.getPersistentData().getDouble("Drown") + 1));
			} else if (entity.getPersistentData().getDouble("Drown") > 0) {
				entity.getPersistentData().putDouble("Drown", (entity.getPersistentData().getDouble("Drown") - 1));
			}
		} else if (entity.getPersistentData().getDouble("Drown") >= 600) {
			if (!entity.level.isClientSide())
				entity.discard();
			if (world instanceof Level _level)
				_level.playSound(_level.isClientSide() ? Minecraft.getInstance().player : null, (entity.getX()), (entity.getY()), (entity.getZ()),
						ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie.converted_to_drowned")), SoundSource.NEUTRAL, 1, 1);
			if (world instanceof ServerLevel _level) {
				Entity entityToSpawn = new DrownedZomboldEntity(KoboldsModEntities.DROWNED_ZOMBOLD, _level);
				entityToSpawn.moveTo((entity.getX()), (entity.getY()), (entity.getZ()), world.getRandom().nextFloat() * 360F, 0);
				if (entityToSpawn instanceof Mob _mobToSpawn)
					_mobToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED, null,
							null);
				world.addFreshEntity(entityToSpawn);
			}
		}
	}
}
