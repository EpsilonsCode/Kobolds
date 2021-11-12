
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.kobolds.init;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;

import net.mcreator.kobolds.entity.KoboldWarriorEntity;
import net.mcreator.kobolds.entity.KoboldEntity;
import net.mcreator.kobolds.entity.KoboldEnchanterEntity;

import java.util.List;
import java.util.ArrayList;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class KoboldsModEntities {
	private static final List<EntityType<?>> REGISTRY = new ArrayList<>();
	public static final EntityType<KoboldEntity> KOBOLD = register("kobold",
			EntityType.Builder.<KoboldEntity>of(KoboldEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
					.setUpdateInterval(3).setCustomClientFactory(KoboldEntity::new).sized(0.5f, 1.48f));
	public static final EntityType<KoboldWarriorEntity> KOBOLD_WARRIOR = register("kobold_warrior",
			EntityType.Builder.<KoboldWarriorEntity>of(KoboldWarriorEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(KoboldWarriorEntity::new).sized(0.5f, 1.48f));
	public static final EntityType<KoboldEnchanterEntity> KOBOLD_ENCHANTER = register("kobold_enchanter",
			EntityType.Builder.<KoboldEnchanterEntity>of(KoboldEnchanterEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(KoboldEnchanterEntity::new).sized(0.5f, 1.48f));

	private static <T extends Entity> EntityType<T> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		EntityType<T> entityType = (EntityType<T>) entityTypeBuilder.build(registryname).setRegistryName(registryname);
		REGISTRY.add(entityType);
		return entityType;
	}

	@SubscribeEvent
	public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
		event.getRegistry().registerAll(REGISTRY.toArray(new EntityType[0]));
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			KoboldEntity.init();
			KoboldWarriorEntity.init();
			KoboldEnchanterEntity.init();
		});
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(KOBOLD, KoboldEntity.createAttributes().build());
		event.put(KOBOLD_WARRIOR, KoboldWarriorEntity.createAttributes().build());
		event.put(KOBOLD_ENCHANTER, KoboldEnchanterEntity.createAttributes().build());
	}
}
