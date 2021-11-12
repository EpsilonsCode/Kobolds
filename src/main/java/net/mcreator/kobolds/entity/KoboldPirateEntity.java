package net.mcreator.kobolds.entity;

import net.minecraftforge.fmllegacy.network.FMLPlayMessages;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.EntityType;

import net.mcreator.kobolds.init.KoboldsModEntities;
import net.mcreator.kobolds.entity.AbstractKoboldEntity;

public class KoboldPirateEntity extends AbstractKoboldEntity {
	public KoboldPirateEntity(FMLPlayMessages.SpawnEntity packet, Level world) {
		this(KoboldsModEntities.KOBOLD_PIRATE, world);
	}

	public KoboldPirateEntity(EntityType<KoboldPirateEntity> type, Level world) {
		super(type, world);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2, true));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Silverfish.class, true, false));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Zombie.class, true, false));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Villager.class, true, false));
	}

	public static void init() {
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.25);
		builder = builder.add(Attributes.MAX_HEALTH, 18);
		builder = builder.add(Attributes.ARMOR, 2);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 1);
		return builder;
	}
}
