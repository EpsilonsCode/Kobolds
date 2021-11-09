
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.kobolds.init;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;

public class KoboldsModTabs {
	public static CreativeModeTab TAB_KOBOLD_CREATIVE_TAB;

	public static void load() {
		TAB_KOBOLD_CREATIVE_TAB = new CreativeModeTab("tabkobold_creative_tab") {
			@Override
			public ItemStack makeIcon() {
				return new ItemStack(KoboldsModBlocks.KOBOLD_BLOCK_SKULL);
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
}
