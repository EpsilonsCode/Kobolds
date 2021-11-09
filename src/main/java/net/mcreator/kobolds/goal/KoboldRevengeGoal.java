package net.mcreator.kobolds.goal;

import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.mcreator.kobolds.entity.AbstractKoboldEntity;

public class KoboldRevengeGoal extends HurtByTargetGoal {
	public KoboldRevengeGoal(AbstractKoboldEntity kobold) {
		super(kobold);
		this.setAlertOthers(new Class[]{AbstractKoboldEntity.class});
	}

	@Override
	public void start() {
		super.start();
		for (AbstractKoboldEntity kobolds : this.mob.level.getEntitiesOfClass(AbstractKoboldEntity.class,
				this.mob.getBoundingBox().inflate(32.0D), (kobold) -> kobold.getTarget() == null)) {
			kobolds.setTarget(this.mob.getTarget());
		}
	}
}
