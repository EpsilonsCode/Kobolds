
package net.mcreator.kobolds.entity;

import net.minecraftforge.fmllegacy.network.FMLPlayMessages;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.EntityType;

import net.mcreator.kobolds.init.KoboldsModEntities;

public class KoboldEngineerEntity extends AbstractKoboldEntity {
	public KoboldEngineerEntity(FMLPlayMessages.SpawnEntity packet, Level world) {
		this(KoboldsModEntities.KOBOLD_ENGINEER, world);
	}

	public KoboldEngineerEntity(EntityType<KoboldEngineerEntity> type, Level world) {
		super(type, world);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2, false));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Villager.class, true, false));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, AbstractIllager.class, true, false));
	}

	public static void init() {
	}

	@Override
	protected boolean canReplaceCurrentItem(ItemStack item, ItemStack stack) {
		if (stack.isEmpty()) {
			return true;
		} else if (item.getItem() instanceof CrossbowItem && stack.getItem() instanceof CrossbowItem) {
			return this.canReplaceEqualItem(item, stack);
		} else if (item.getItem() instanceof ArmorItem) {
			if (EnchantmentHelper.hasBindingCurse(stack)) {
				return false;
			} else if (!(stack.getItem() instanceof ArmorItem)) {
				return true;
			} else {
				ArmorItem armoritem = (ArmorItem) item.getItem();
				ArmorItem armoritem1 = (ArmorItem) stack.getItem();
				if (armoritem.getDefense() != armoritem1.getDefense()) {
					return armoritem.getDefense() > armoritem1.getDefense();
				} else if (armoritem.getToughness() != armoritem1.getToughness()) {
					return armoritem.getToughness() > armoritem1.getToughness();
				} else {
					return this.canReplaceEqualItem(item, stack);
				}
			}
		} else {
			if (item.getItem() instanceof DiggerItem) {
				if (stack.getItem() instanceof BlockItem) {
					return true;
				}
				if (stack.getItem() instanceof DiggerItem) {
					DiggerItem diggeritem = (DiggerItem) item.getItem();
					DiggerItem diggeritem1 = (DiggerItem) stack.getItem();
					if (diggeritem.getAttackDamage() != diggeritem1.getAttackDamage()) {
						return diggeritem.getAttackDamage() > diggeritem1.getAttackDamage();
					}
					return this.canReplaceEqualItem(item, stack);
				}
			}
			return false;
		}
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
