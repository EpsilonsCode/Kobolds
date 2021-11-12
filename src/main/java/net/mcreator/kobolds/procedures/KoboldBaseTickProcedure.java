package net.mcreator.kobolds.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.Minecraft;

import net.mcreator.kobolds.init.KoboldsModItems;
import net.mcreator.kobolds.init.KoboldsModEntities;
import net.mcreator.kobolds.entity.KoboldChildEntity;
import net.mcreator.kobolds.KoboldsMod;

import java.util.Random;
import java.util.Map;

public class KoboldBaseTickProcedure {
	public static void execute(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				KoboldsMod.LOGGER.warn("Failed to load dependency entity for procedure KoboldBaseTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				KoboldsMod.LOGGER.warn("Failed to load dependency world for procedure KoboldBaseTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		LevelAccessor world = (LevelAccessor) dependencies.get("world");
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) < 12 && entity.getPersistentData().getDouble("TimerHeal") == 0) {
			if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == Items.TRIDENT) {
				entity.getPersistentData().putDouble("TimerHeal", 120);
			} else if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == (ItemStack.EMPTY).getItem()
					&& !(entity instanceof LivingEntity _livEnt ? _livEnt.isBaby() : false)) {
				entity.getPersistentData().putDouble("TimerHeal", 6000);
				new Object() {
					private int ticks = 0;
					private float waitTicks;
					private LevelAccessor world;

					public void start(LevelAccessor world, int waitTicks) {
						this.waitTicks = waitTicks;
						MinecraftForge.EVENT_BUS.register(this);
						this.world = world;
					}

					@SubscribeEvent
					public void tick(TickEvent.ServerTickEvent event) {
						if (event.phase == TickEvent.Phase.END) {
							this.ticks += 1;
							if (this.ticks >= this.waitTicks)
								run();
						}
					}

					private void run() {
						if (entity instanceof LivingEntity _entity) {
							ItemStack _setstack = new ItemStack(KoboldsModItems.KOBOLD_POTION_HEALTH);
							_setstack.setCount(1);
							_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
							if (_entity instanceof ServerPlayer _serverPlayer)
								_serverPlayer.getInventory().setChanged();
						}
						if (entity instanceof LivingEntity _entity)
							_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 32, -4, (false), (false)));
						if (world instanceof Level _level)
							_level.playSound(_level.isClientSide() ? Minecraft.getInstance().player : null, (entity.getX()), (entity.getY()),
									(entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.drink")),
									SoundSource.NEUTRAL, 1, 1);
						new Object() {
							private int ticks = 0;
							private float waitTicks;
							private LevelAccessor world;

							public void start(LevelAccessor world, int waitTicks) {
								this.waitTicks = waitTicks;
								MinecraftForge.EVENT_BUS.register(this);
								this.world = world;
							}

							@SubscribeEvent
							public void tick(TickEvent.ServerTickEvent event) {
								if (event.phase == TickEvent.Phase.END) {
									this.ticks += 1;
									if (this.ticks >= this.waitTicks)
										run();
								}
							}

							private void run() {
								if (entity instanceof LivingEntity _entity)
									_entity.addEffect(new MobEffectInstance(MobEffects.HEAL, 1, 0, (false), (false)));
								if (entity instanceof LivingEntity _entity)
									_entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 440, 1, (false), (false)));
								if (entity instanceof LivingEntity _entity) {
									ItemStack _setstack = (ItemStack.EMPTY);
									_setstack.setCount(1);
									_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
									if (_entity instanceof ServerPlayer _serverPlayer)
										_serverPlayer.getInventory().setChanged();
								}
								MinecraftForge.EVENT_BUS.unregister(this);
							}
						}.start(world, 32);
						MinecraftForge.EVENT_BUS.unregister(this);
					}
				}.start(world, 5);
			} else if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
					.getItem() == KoboldsModItems.KOBOLD_IRON_AXE) {
				entity.getPersistentData().putDouble("TimerHeal", 6000);
				new Object() {
					private int ticks = 0;
					private float waitTicks;
					private LevelAccessor world;

					public void start(LevelAccessor world, int waitTicks) {
						this.waitTicks = waitTicks;
						MinecraftForge.EVENT_BUS.register(this);
						this.world = world;
					}

					@SubscribeEvent
					public void tick(TickEvent.ServerTickEvent event) {
						if (event.phase == TickEvent.Phase.END) {
							this.ticks += 1;
							if (this.ticks >= this.waitTicks)
								run();
						}
					}

					private void run() {
						if (entity instanceof LivingEntity _entity) {
							ItemStack _setstack = new ItemStack(KoboldsModItems.KOBOLD_POTION_HEALTH);
							_setstack.setCount(1);
							_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
							if (_entity instanceof ServerPlayer _serverPlayer)
								_serverPlayer.getInventory().setChanged();
						}
						if (entity instanceof LivingEntity _entity)
							_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 32, -4, (false), (false)));
						if (world instanceof Level _level)
							_level.playSound(_level.isClientSide() ? Minecraft.getInstance().player : null, (entity.getX()), (entity.getY()),
									(entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.drink")),
									SoundSource.NEUTRAL, 1, 1);
						new Object() {
							private int ticks = 0;
							private float waitTicks;
							private LevelAccessor world;

							public void start(LevelAccessor world, int waitTicks) {
								this.waitTicks = waitTicks;
								MinecraftForge.EVENT_BUS.register(this);
								this.world = world;
							}

							@SubscribeEvent
							public void tick(TickEvent.ServerTickEvent event) {
								if (event.phase == TickEvent.Phase.END) {
									this.ticks += 1;
									if (this.ticks >= this.waitTicks)
										run();
								}
							}

							private void run() {
								if (entity instanceof LivingEntity _entity)
									_entity.addEffect(new MobEffectInstance(MobEffects.HEAL, 1, 0, (false), (false)));
								if (entity instanceof LivingEntity _entity)
									_entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 440, 1, (false), (false)));
								if (entity instanceof LivingEntity _entity) {
									ItemStack _setstack = new ItemStack(KoboldsModItems.KOBOLD_IRON_AXE);
									_setstack.setCount(1);
									_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
									if (_entity instanceof ServerPlayer _serverPlayer)
										_serverPlayer.getInventory().setChanged();
								}
								MinecraftForge.EVENT_BUS.unregister(this);
							}
						}.start(world, 32);
						MinecraftForge.EVENT_BUS.unregister(this);
					}
				}.start(world, 5);
			}
		}
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == (ItemStack.EMPTY).getItem()
				&& (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == (ItemStack.EMPTY).getItem()) {
			if (!EntityTypeTags.getAllTags().getTagOrEmpty(new ResourceLocation("kobolds:enchanter")).contains(entity.getType())
					&& !(entity instanceof LivingEntity _livEnt ? _livEnt.isBaby() : false)) {
				if (Math.random() >= 0.7) {
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
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == Items.TRIDENT
				&& entity.getPersistentData().getDouble("TimerTrident") == 1200) {
			if (entity instanceof LivingEntity _entity) {
				ItemStack _setstack = new ItemStack(KoboldsModItems.KOBOLD_IRON_SWORD);
				_setstack.setCount(1);
				_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
				if (_entity instanceof ServerPlayer _serverPlayer)
					_serverPlayer.getInventory().setChanged();
			}
			if (entity instanceof LivingEntity _entity) {
				ItemStack _setstack = (ItemStack.EMPTY);
				_setstack.setCount(1);
				_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
				if (_entity instanceof ServerPlayer _serverPlayer)
					_serverPlayer.getInventory().setChanged();
			}
		}
		if (entity.getPersistentData().getDouble("TimerHeal") > 0) {
			entity.getPersistentData().putDouble("TimerHeal", (entity.getPersistentData().getDouble("TimerHeal") - 1));
		}
		if (entity.getPersistentData().getDouble("TimerApple") == 12000) {
			if (world instanceof ServerLevel _level) {
				Entity entityToSpawn = new KoboldChildEntity(KoboldsModEntities.KOBOLD_CHILD, _level);
				entityToSpawn.moveTo((entity.getX()), (entity.getY()), (entity.getZ()), world.getRandom().nextFloat() * 360F, 0);
				if (entityToSpawn instanceof Mob _mobToSpawn)
					_mobToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED, null,
							null);
				world.addFreshEntity(entityToSpawn);
			}
			entity.getPersistentData().putDouble("TimerApple", (entity.getPersistentData().getDouble("TimerApple") - 1));
		} else if (entity.getPersistentData().getDouble("TimerApple") > 0) {
			entity.getPersistentData().putDouble("TimerApple", (entity.getPersistentData().getDouble("TimerApple") - 1));
		}
		if (entity.getPersistentData().getDouble("TimerTrident") > 1) {
			entity.getPersistentData().putDouble("TimerTrident", (entity.getPersistentData().getDouble("TimerTrident") - 1));
		} else if (entity.getPersistentData().getDouble("TimerTrident") == 1
				&& (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == (ItemStack.EMPTY).getItem()) {
			if (entity instanceof LivingEntity _entity) {
				ItemStack _setstack = (ItemStack.EMPTY);
				_setstack.setCount(1);
				_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
				if (_entity instanceof ServerPlayer _serverPlayer)
					_serverPlayer.getInventory().setChanged();
			}
			if (entity instanceof LivingEntity _entity) {
				ItemStack _setstack = new ItemStack(Items.TRIDENT);
				_setstack.setCount(1);
				_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
				if (_entity instanceof ServerPlayer _serverPlayer)
					_serverPlayer.getInventory().setChanged();
			}
			entity.getPersistentData().putDouble("TimerTrident", (entity.getPersistentData().getDouble("TimerTrident") - 1));
		}
	}
}
