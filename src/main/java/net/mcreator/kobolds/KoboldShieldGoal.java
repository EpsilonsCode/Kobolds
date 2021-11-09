package net.mcreator.kobolds;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.InteractionHand;

import net.mcreator.kobolds.AbstractKoboldEntity;

public class KoboldShieldGoal extends Goal {
	public final AbstractKoboldEntity kobold;

	public KoboldShieldGoal(AbstractKoboldEntity kobold) {
		this.kobold = kobold;
	}

	@Override
	public boolean canUse() {
		return kobold.getOffhandItem().getItem().isShield(kobold.getOffhandItem(), kobold) && raiseShield();
	}

	@Override
	public boolean canContinueToUse() {
		return this.canUse();
	}

	@Override
	public void start() {
		if (kobold.getOffhandItem().getItem().isShield(kobold.getOffhandItem(), kobold))
			kobold.startUsingItem(InteractionHand.OFF_HAND);
	}

	protected boolean raiseShield() {
		LivingEntity target = kobold.getTarget();
		if (kobold.isAggressive()) {
			if (target instanceof RangedAttackMob && kobold.distanceTo(target) >= 0.8D) {
				if (target.getMainHandItem().getItem() instanceof AxeItem) {
					kobold.stopUsingItem();
					return false;
				} else {
					return true;
				}
			} else if (target instanceof LivingEntity && kobold.distanceTo(target) >= 0.5D && kobold.distanceTo(target) <= 2.8D) {
				if (target.getMainHandItem().getItem() instanceof AxeItem) {
					kobold.stopUsingItem();
					return false;
				} else {
					return true;
				}
			} else {
				kobold.stopUsingItem();
				return false;
			}
		} else {
			kobold.stopUsingItem();
			return false;
		}
	}
}