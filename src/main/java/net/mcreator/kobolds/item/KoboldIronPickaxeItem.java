
package net.mcreator.kobolds.item;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

import net.mcreator.kobolds.init.KoboldsModTabs;

public class KoboldIronPickaxeItem extends PickaxeItem {
	public KoboldIronPickaxeItem() {
		super(new Tier() {
			public int getUses() {
				return 250;
			}

			public float getSpeed() {
				return 4f;
			}

			public float getAttackDamageBonus() {
				return 2f;
			}

			public int getLevel() {
				return 1;
			}

			public int getEnchantmentValue() {
				return 14;
			}

			public Ingredient getRepairIngredient() {
				return Ingredient.of(new ItemStack(Items.IRON_INGOT));
			}
		}, 1, -2.8f, new Item.Properties().tab(KoboldsModTabs.TAB_KOBOLD_CREATIVE_TAB));
		setRegistryName("kobold_iron_pickaxe");
	}
}
