package net.mcreator.kobolds.entity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fmllegacy.network.NetworkHooks;

import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.CrossbowAttackMob;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.goal.RestrictSunGoal;
import net.minecraft.world.entity.ai.goal.RangedCrossbowAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.OpenDoorGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.protocol.Packet;
import net.minecraft.nbt.CompoundTag;

import net.mcreator.kobolds.procedures.KoboldSpawnProcedure;
import net.mcreator.kobolds.procedures.KoboldDeathProcedure;
import net.mcreator.kobolds.procedures.KoboldBaseTickProcedure;
import net.mcreator.kobolds.goal.KoboldTridentAttackGoal;
import net.mcreator.kobolds.goal.KoboldShieldGoal;
import net.mcreator.kobolds.goal.KoboldRevengeGoal;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMap;

public abstract class AbstractKoboldEntity extends Monster implements CrossbowAttackMob, RangedAttackMob {
	protected AbstractKoboldEntity(EntityType<? extends Monster> type, Level world) {
		super(type, world);
		xpReward = 4;
		setNoAi(false);
		setPersistenceRequired();
		((GroundPathNavigation) this.getNavigation()).setCanOpenDoors(true);
	}

	private static final EntityDataAccessor<Boolean> DATA_CHARGING_STATE = SynchedEntityData.defineId(AbstractKoboldEntity.class,
			EntityDataSerializers.BOOLEAN);

	@Override
	public Packet<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new OpenDoorGoal(this, true));
		this.goalSelector.addGoal(0, new AvoidEntityGoal(this, IronGolem.class, (float) 14, 1.2, 1.8));
		this.goalSelector.addGoal(0, new AvoidEntityGoal(this, Creeper.class, (float) 4, 1.2, 1.6));
		this.goalSelector.addGoal(1, new KoboldShieldGoal(this));
		this.goalSelector.addGoal(1, new KoboldTridentAttackGoal(this, 1.0D, 40, 10.0F));
		this.goalSelector.addGoal(1, new RangedCrossbowAttackGoal<>(this, 1.0D, 21.0F));
		this.targetSelector.addGoal(2, new KoboldRevengeGoal(this));
		this.goalSelector.addGoal(3, new RestrictSunGoal(this));
		this.goalSelector.addGoal(3, new RandomStrollGoal(this, 1));
		this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, LivingEntity.class, (float) 6));
		this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(3, new FloatGoal(this));
	}

	@Override
	public void performRangedAttack(LivingEntity target, float distanceFactor) {
		if (this.getMainHandItem().getItem() instanceof CrossbowItem) {
			this.performCrossbowAttack(this, 6.0F);
		} else if (this.getOffhandItem().getItem() instanceof TridentItem) {
			ThrownTrident trident = new ThrownTrident(this.level, this, new ItemStack(Items.TRIDENT));
			double d0 = target.getX() - this.getX();
			double d1 = target.getY(0.3333333333333333D) - trident.getY();
			double d2 = target.getZ() - this.getZ();
			double d3 = (double) Mth.sqrt((float) (d0 * d0 + d2 * d2));
			trident.shoot(d0, d1 + d3 * (double) 0.2F, d2, 1.6F, (float) (14 - this.level.getDifficulty().getId() * 4));
			this.playSound(SoundEvents.DROWNED_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
			this.level.addFreshEntity(trident);
			this.getPersistentData().putDouble("TimerTrident", 1200);
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

	public boolean isCharging() {
		return this.entityData.get(DATA_CHARGING_STATE);
	}

	public void setChargingCrossbow(boolean charging) {
		this.entityData.set(DATA_CHARGING_STATE, charging);
	}

	@Override
	public MobType getMobType() {
		return MobType.UNDEFINED;
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return false;
	}

	@Override
	public SoundEvent getAmbientSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("kobolds:kobold_idle"));
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		if (this.isBlocking()) {
			return SoundEvents.SHIELD_BLOCK;
		} else {
			return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("kobolds:kobold_hurt"));
		}
	}

	@Override
	public SoundEvent getDeathSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("kobolds:kobold_death"));
	}

	@Override
	public void die(DamageSource source) {
		super.die(source);
		double x = this.getX();
		double y = this.getY();
		double z = this.getZ();
		Entity entity = this;
		Level world = this.level;

		if (source.getDirectEntity() instanceof Zombie) {
			Zombie zombie = (Zombie) source.getDirectEntity();
			KoboldDeathProcedure.execute(ImmutableMap.<String, Object>builder().put("entity", entity).put("sourceentity", zombie).put("world", world).build());
		}
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason,
			@Nullable SpawnGroupData livingdata, @Nullable CompoundTag tag) {
		SpawnGroupData retval = super.finalizeSpawn(world, difficulty, reason, livingdata, tag);
		double x = this.getX();
		double y = this.getY();
		double z = this.getZ();
		Entity entity = this;
		KoboldSpawnProcedure.execute(ImmutableMap.<String, Object>builder().put("entity", entity).build());
		return retval;
	}

	@Override
	public void baseTick() {
		super.baseTick();
		double x = this.getX();
		double y = this.getY();
		double z = this.getZ();
		Entity entity = this;
		Level world = this.level;
		KoboldBaseTickProcedure.execute(ImmutableMap.<String, Object>builder().put("entity", entity).put("world", world).build());
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (source.getDirectEntity() instanceof AbstractArrow) {
			AbstractArrow arrow = (AbstractArrow) source.getDirectEntity();
			if (arrow.getOwner() instanceof AbstractKoboldEntity) {
				return false;
			}
		} else if (source.getDirectEntity() instanceof ThrownTrident) {
			ThrownTrident trident = (ThrownTrident) source.getDirectEntity();
			if (trident.getOwner() instanceof AbstractKoboldEntity) {
				return false;
			}
		}
		if (source == DamageSource.IN_FIRE)
			return false;
		return super.hurt(source, amount);
	}
}
