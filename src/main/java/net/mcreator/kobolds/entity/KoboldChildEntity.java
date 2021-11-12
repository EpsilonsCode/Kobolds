
package net.mcreator.kobolds.entity;

import net.minecraftforge.fmllegacy.network.FMLPlayMessages;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Entity;

import net.mcreator.kobolds.procedures.KoboldBaseTickChildProcedure;
import net.mcreator.kobolds.entity.AbstractKoboldEntity;
import net.mcreator.kobolds.init.KoboldsModEntities;

import com.google.common.collect.ImmutableMap;

public class KoboldChildEntity extends AbstractKoboldEntity {
	public KoboldChildEntity(FMLPlayMessages.SpawnEntity packet, Level world) {
		this(KoboldsModEntities.KOBOLD_CHILD, world);
	}

	public KoboldChildEntity(EntityType<KoboldChildEntity> type, Level world) {
		super(type, world);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new PanicGoal(this, 1.2));
	}

	public boolean isBaby() {
		return true;
	}

	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return this.isBaby() ? 0.66F : 1.26F;
	}

	public static void init() {
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.25);
		builder = builder.add(Attributes.MAX_HEALTH, 12);
		builder = builder.add(Attributes.ARMOR, 0);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 1);
		return builder;
	}

	@Override
	public void baseTick() {
		super.baseTick();
		double x = this.getX();
		double y = this.getY();
		double z = this.getZ();
		Entity entity = this;
		Level world = this.level;
		KoboldBaseTickChildProcedure.execute(ImmutableMap.<String, Object>builder().put("entity", entity).put("world", world).build());
	}
}
