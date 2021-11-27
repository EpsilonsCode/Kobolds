
package net.mcreator.kobolds.entity;

import net.minecraftforge.fmllegacy.network.FMLPlayMessages;

import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.CrossbowAttackMob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.RangedCrossbowAttackGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.nbt.CompoundTag;

import net.mcreator.kobolds.procedures.KoboldSpawnProcedure;
import net.mcreator.kobolds.init.KoboldsModEntities;
import net.mcreator.kobolds.entity.AbstractKoboldEntity;

import javax.annotation.Nullable;

public class SkeleboldEntity extends Skeleton implements CrossbowAttackMob, RangedAttackMob {
	public SkeleboldEntity(FMLPlayMessages.SpawnEntity packet, Level world) {
		this(KoboldsModEntities.SKELEBOLD, world);
	}

	public SkeleboldEntity(EntityType<SkeleboldEntity> type, Level world) {
		super(type, world);
		getEyePosition(0.5F);
	}

	private static final EntityDataAccessor<Boolean> DATA_CHARGING_STATE = SynchedEntityData.defineId(AbstractKoboldEntity.class,
			EntityDataSerializers.BOOLEAN);

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(1, new RangedCrossbowAttackGoal<>(this, 1.0D, 21.0F));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, AbstractKoboldEntity.class, true, false));
	}

	@Override
	public double getMyRidingOffset() {
		return this.isBaby() ? 0.0D : -0.15D;
	}

	@Override
	public void performRangedAttack(LivingEntity target, float distanceFactor) {
		if (this.getMainHandItem().getItem() instanceof CrossbowItem) {
			this.performCrossbowAttack(this, 6.0F);
		}
	}

	@Override
	public void onCrossbowAttackPerformed() {
		this.noActionTime = 0;
	}

	@Override
	public void shootCrossbowProjectile(LivingEntity arg0, ItemStack arg1, Projectile arg2, float arg3) {
		this.shootCrossbowProjectile(this, arg0, arg2, arg3, 1.6F);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_CHARGING_STATE, false);
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason,
			@Nullable SpawnGroupData livingdata, @Nullable CompoundTag tag) {
		SpawnGroupData retval = super.finalizeSpawn(world, difficulty, reason, livingdata, tag);
		double x = this.getX();
		double y = this.getY();
		double z = this.getZ();
		Entity entity = this;

		KoboldSpawnProcedure.execute(entity);
		return retval;
	}

	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return this.isBaby() ? 0.66F : 1.26F;
	}

	public boolean isCharging() {
		return this.entityData.get(DATA_CHARGING_STATE);
	}

	public void setChargingCrossbow(boolean charging) {
		this.entityData.set(DATA_CHARGING_STATE, charging);
	}

	public static void init() {
		SpawnPlacements.register(KoboldsModEntities.SKELEBOLD, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Monster::checkMonsterSpawnRules);
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.25);
		builder = builder.add(Attributes.MAX_HEALTH, 18);
		builder = builder.add(Attributes.ARMOR, 0);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 3);
		return builder;
	}
}
