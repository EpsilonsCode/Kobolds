package net.mcreator.kobolds.entity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fmllegacy.network.NetworkHooks;
import net.minecraftforge.fmllegacy.network.FMLPlayMessages;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.CrossbowAttackMob;
import net.minecraft.world.entity.monster.Creeper;
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
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.protocol.Packet;

import net.mcreator.kobolds.goal.KoboldShieldGoal;
import net.mcreator.kobolds.goal.KoboldTridentAttackGoal;
import net.mcreator.kobolds.goal.KoboldRevengeGoal;

public abstract class AbstractKoboldEntity extends Monster implements CrossbowAttackMob, RangedAttackMob {
	protected AbstractKoboldEntity(EntityType<? extends Monster> type, Level world) {
		super(type, world);
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
			//	} else if (this.getHeldItemOffhand().getItem() instanceof TridentItem) {
			//		TridentEntity tridententity = new TridentEntity(this.world, this, new ItemStack(Items.TRIDENT));
			//		double d0 = target.getPosX() - this.getPosX();
			//		double d1 = target.getPosYHeight(0.3333333333333333D) - tridententity.getPosY();
			//		double d2 = target.getPosZ() - this.getPosZ();
			//		double d3 = (double) MathHelper.sqrt(d0 * d0 + d2 * d2);
			//		tridententity.shoot(d0, d1 + d3 * (double) 0.2F, d2, 1.6F, (float) (14 - this.world.getDifficulty().getId() * 4));
			//		this.playSound(SoundEvents.ENTITY_DROWNED_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
			//		this.world.addEntity(tridententity);
			//		this.getPersistentData().putDouble("TimerTrident", 1200);
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
	public boolean hurt(DamageSource source, float amount) {
		if (source.getDirectEntity() instanceof AbstractArrow)
			return false;
		if (source.getMsgId().equals("trident"))
			return false;
		if (source == DamageSource.IN_FIRE)
			return false;
		return super.hurt(source, amount);
		//I updated these checks partial. For the trident and arrow check, there is suppose to be a secondary check to see if the trident/arrow was from a kobold to make them immune to their own projectiles.
		//Currently, they are immune to all tridents and arrows base on this above code.
	}
}
