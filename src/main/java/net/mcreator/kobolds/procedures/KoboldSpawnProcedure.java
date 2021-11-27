package net.mcreator.kobolds.procedures;

import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionHand;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.kobolds.init.KoboldsModItems;
import net.mcreator.kobolds.entity.SkeleboldEntity;
import net.mcreator.kobolds.entity.KoboldPirateEntity;
import net.mcreator.kobolds.entity.KoboldCaptainEntity;

import java.util.Random;

public class KoboldSpawnProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof KoboldCaptainEntity) {
			if (entity instanceof LivingEntity _entity) {
				ItemStack _setstack = (EnchantmentHelper.enchantItem(new Random(), new ItemStack(KoboldsModItems.KOBOLD_IRON_SWORD), 30, (false)));
				_setstack.setCount(1);
				_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
				if (_entity instanceof ServerPlayer _serverPlayer)
					_serverPlayer.getInventory().setChanged();
			}
		} else if (EntityTypeTags.getAllTags().getTagOrEmpty(new ResourceLocation("kobolds:trader")).contains(entity.getType())) {
			if (Math.random() >= 0.6) {
				if (Math.random() <= 0.45 && entity instanceof KoboldPirateEntity) {
					if (entity instanceof LivingEntity _entity) {
						ItemStack _setstack = new ItemStack(Items.TRIDENT);
						_setstack.setCount(1);
						_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
						if (_entity instanceof ServerPlayer _serverPlayer)
							_serverPlayer.getInventory().setChanged();
					}
				} else if (Math.random() >= 0.95) {
					if (entity instanceof LivingEntity _entity) {
						ItemStack _setstack = (EnchantmentHelper.enchantItem(new Random(), new ItemStack(Items.CROSSBOW), 30, (false)));
						_setstack.setCount(1);
						_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
						if (_entity instanceof ServerPlayer _serverPlayer)
							_serverPlayer.getInventory().setChanged();
					}
				} else {
					if (entity instanceof LivingEntity _entity) {
						ItemStack _setstack = new ItemStack(Items.CROSSBOW);
						_setstack.setCount(1);
						_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
						if (_entity instanceof ServerPlayer _serverPlayer)
							_serverPlayer.getInventory().setChanged();
					}
				}
			} else if (Math.random() <= 0.1) {
				if (entity instanceof LivingEntity _entity) {
					ItemStack _setstack = (EnchantmentHelper.enchantItem(new Random(), new ItemStack(KoboldsModItems.KOBOLD_IRON_SWORD), 30,
							(false)));
					_setstack.setCount(1);
					_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
					if (_entity instanceof ServerPlayer _serverPlayer)
						_serverPlayer.getInventory().setChanged();
				}
			} else {
				if (entity instanceof LivingEntity _entity) {
					ItemStack _setstack = new ItemStack(KoboldsModItems.KOBOLD_IRON_SWORD);
					_setstack.setCount(1);
					_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
					if (_entity instanceof ServerPlayer _serverPlayer)
						_serverPlayer.getInventory().setChanged();
				}
			}
		} else if (entity instanceof SkeleboldEntity) {
			if (Math.random() >= 0.6) {
				if (entity instanceof LivingEntity _entity) {
					ItemStack _setstack = (EnchantmentHelper.enchantItem(new Random(), new ItemStack(Items.CROSSBOW), 30, (false)));
					_setstack.setCount(1);
					_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
					if (_entity instanceof ServerPlayer _serverPlayer)
						_serverPlayer.getInventory().setChanged();
				}
			} else {
				if (entity instanceof LivingEntity _entity) {
					ItemStack _setstack = new ItemStack(Items.CROSSBOW);
					_setstack.setCount(1);
					_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
					if (_entity instanceof ServerPlayer _serverPlayer)
						_serverPlayer.getInventory().setChanged();
				}
			}
		}
	}
}
