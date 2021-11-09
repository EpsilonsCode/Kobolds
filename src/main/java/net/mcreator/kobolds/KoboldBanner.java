package net.mcreator.kobolds.item;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BannerPatternItem;

import net.mcreator.kobolds.init.KoboldsModTabs;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class KoboldBanner {
	public static final BannerPattern PATTERN_KOBOLD = addBanner("kobold");

	private static BannerPattern addBanner(String name) {
		return BannerPattern.create(name.toUpperCase(), name, "kobolds." + name, true);
	}

	@SubscribeEvent
	public static void registerItem(RegistryEvent.Register<Item> event) {
		event.getRegistry().register(
				new BannerPatternItem(PATTERN_KOBOLD, (new Item.Properties()).stacksTo(1).tab(KoboldsModTabs.TAB_KOBOLD_CREATIVE_TAB).rarity(Rarity.UNCOMMON))
						.setRegistryName("kobolds:banner_pattern_kobold"));
	}
}
