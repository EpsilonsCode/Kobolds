package net.mcreator.kobolds;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Items;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.monster.RangedAttackMob;

import net.mcreator.kobolds.AbstractKoboldEntity;

public class KoboldTridentAttackGoal extends RangedAttackGoal {
	private final AbstractKoboldEntity field_204728_a;

	public KoboldTridentAttackGoal(RangedAttackMob p_i48907_1_, double p_i48907_2_, int p_i48907_4_, float p_i48907_5_) {
		super(p_i48907_1_, p_i48907_2_, p_i48907_4_, p_i48907_5_);
		this.field_204728_a = (AbstractKoboldEntity) p_i48907_1_;
	}

	public boolean canUse() {
		return super.canUse() && this.field_204728_a.getOffhandItem().getItem() == Items.TRIDENT;
	}

	public void start() {
		super.start();
		this.field_204728_a.setAggressive(true);
		this.field_204728_a.startUsingItem(InteractionHand.OFF_HAND);;
	}

	public void stop() {
		super.stop();
		this.field_204728_a.stopUsingItem();
		this.field_204728_a.setAggressive(false);
	}
}
