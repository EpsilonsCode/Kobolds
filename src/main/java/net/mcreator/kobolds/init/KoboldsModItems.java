
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.kobolds.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.BlockItem;

import net.mcreator.kobolds.item.KoboldPotionWaterItem;
import net.mcreator.kobolds.item.KoboldPotionStealthItem;
import net.mcreator.kobolds.item.KoboldPotionLevitationItem;
import net.mcreator.kobolds.item.KoboldPotionLeapingItem;
import net.mcreator.kobolds.item.KoboldPotionHealthItem;
import net.mcreator.kobolds.item.KoboldPotionFireItem;
import net.mcreator.kobolds.item.KoboldPotionCombatItem;
import net.mcreator.kobolds.item.KoboldIronSwordItem;
import net.mcreator.kobolds.item.KoboldIronShovelItem;
import net.mcreator.kobolds.item.KoboldIronPickaxeItem;
import net.mcreator.kobolds.item.KoboldIronAxeItem;

import java.util.List;
import java.util.ArrayList;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class KoboldsModItems {
	private static final List<Item> REGISTRY = new ArrayList<>();
	public static final Item KOBOLD_POTION_HEALTH = register(new KoboldPotionHealthItem());
	public static final Item KOBOLD_POTION_FIRE = register(new KoboldPotionFireItem());
	public static final Item KOBOLD_POTION_STEALTH = register(new KoboldPotionStealthItem());
	public static final Item KOBOLD_POTION_COMBAT = register(new KoboldPotionCombatItem());
	public static final Item KOBOLD_POTION_WATER = register(new KoboldPotionWaterItem());
	public static final Item KOBOLD_POTION_LEAPING = register(new KoboldPotionLeapingItem());
	public static final Item KOBOLD_POTION_LEVITATION = register(new KoboldPotionLevitationItem());
	public static final Item KOBOLD_BLOCK_SKULL = register(KoboldsModBlocks.KOBOLD_BLOCK_SKULL, KoboldsModTabs.TAB_KOBOLD_CREATIVE_TAB);
	public static final Item KOBOLD_IRON_SWORD = register(new KoboldIronSwordItem());
	public static final Item KOBOLD_IRON_PICKAXE = register(new KoboldIronPickaxeItem());
	public static final Item KOBOLD_IRON_AXE = register(new KoboldIronAxeItem());
	public static final Item KOBOLD_IRON_SHOVEL = register(new KoboldIronShovelItem());

	private static Item register(Item item) {
		REGISTRY.add(item);
		return item;
	}

	private static Item register(Block block, CreativeModeTab tab) {
		return register(new BlockItem(block, new Item.Properties().tab(tab)).setRegistryName(block.getRegistryName()));
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(REGISTRY.toArray(new Item[0]));
	}
}
