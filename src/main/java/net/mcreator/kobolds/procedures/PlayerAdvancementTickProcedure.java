package net.mcreator.kobolds.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import net.mcreator.kobolds.init.KoboldsModItems;
import net.mcreator.kobolds.entity.KoboldEngineerEntity;
import net.mcreator.kobolds.entity.KoboldEnchanterEntity;
import net.mcreator.kobolds.entity.KoboldCaptainEntity;
import net.mcreator.kobolds.KoboldsMod;

import java.util.Map;
import java.util.Iterator;
import java.util.HashMap;

@Mod.EventBusSubscriber
public class PlayerAdvancementTickProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			Entity entity = event.player;
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", entity.getX());
			dependencies.put("y", entity.getY());
			dependencies.put("z", entity.getZ());
			dependencies.put("world", entity.level);
			dependencies.put("entity", entity);
			dependencies.put("event", event);
			execute(dependencies);
		}
	}

	public static void execute(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				KoboldsMod.LOGGER.warn("Failed to load dependency entity for procedure PlayerAdvancementTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				KoboldsMod.LOGGER.warn("Failed to load dependency world for procedure PlayerAdvancementTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		LevelAccessor world = (LevelAccessor) dependencies.get("world");
		if ((entity instanceof ServerPlayer _plr && _plr.level instanceof ServerLevel
				? _plr.getAdvancements().getOrStartProgress(_plr.server.getAdvancements().getAdvancement(new ResourceLocation("kobolds:axer")))
						.isDone()
				: false) == false
				&& (entity instanceof Player _playerHasItem
						? _playerHasItem.getInventory().contains(new ItemStack(KoboldsModItems.KOBOLD_IRON_AXE))
						: false)) {
			if (entity instanceof ServerPlayer _player) {
				Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("kobolds:axer"));
				AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
				if (!_ap.isDone()) {
					Iterator _iterator = _ap.getRemainingCriteria().iterator();
					while (_iterator.hasNext())
						_player.getAdvancements().award(_adv, (String) _iterator.next());
				}
			}
		}
		if ((entity instanceof ServerPlayer _plr && _plr.level instanceof ServerLevel
				? _plr.getAdvancements()
						.getOrStartProgress(_plr.server.getAdvancements().getAdvancement(new ResourceLocation("kobolds:stone_diamond"))).isDone()
				: false) == false
				&& ((entity instanceof Player _playerHasItem
						? _playerHasItem.getInventory().contains(new ItemStack(KoboldsModItems.KOBOLD_IRON_PICKAXE))
						: false)
						|| (entity instanceof Player _playerHasItem
								? _playerHasItem.getInventory().contains(new ItemStack(KoboldsModItems.KOBOLD_IRON_SHOVEL))
								: false))) {
			if (entity instanceof ServerPlayer _player) {
				Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("kobolds:stone_diamond"));
				AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
				if (!_ap.isDone()) {
					Iterator _iterator = _ap.getRemainingCriteria().iterator();
					while (_iterator.hasNext())
						_player.getAdvancements().award(_adv, (String) _iterator.next());
				}
			}
		}
		if ((entity instanceof ServerPlayer _plr && _plr.level instanceof ServerLevel
				? _plr.getAdvancements()
						.getOrStartProgress(_plr.server.getAdvancements().getAdvancement(new ResourceLocation("kobolds:kobold_trio_advancement")))
						.isDone()
				: false) == false
				&& !world.getEntitiesOfClass(KoboldEnchanterEntity.class,
						AABB.ofSize(new Vec3((entity.getX()), (entity.getY()), (entity.getZ())), 16, 16, 16), e -> true).isEmpty()
				&& !world.getEntitiesOfClass(KoboldEngineerEntity.class,
						AABB.ofSize(new Vec3((entity.getX()), (entity.getY()), (entity.getZ())), 16, 16, 16), e -> true).isEmpty()
				&& !world.getEntitiesOfClass(KoboldCaptainEntity.class,
						AABB.ofSize(new Vec3((entity.getX()), (entity.getY()), (entity.getZ())), 16, 16, 16), e -> true).isEmpty()) {
			if (entity instanceof ServerPlayer _player) {
				Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("kobolds:kobold_trio_advancement"));
				AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
				if (!_ap.isDone()) {
					Iterator _iterator = _ap.getRemainingCriteria().iterator();
					while (_iterator.hasNext())
						_player.getAdvancements().award(_adv, (String) _iterator.next());
				}
			}
		}
	}
}
