
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

import net.mcreator.kobolds.entity.ZomboldEntity;
import net.mcreator.kobolds.entity.SkeleboldEntity;
import net.mcreator.kobolds.entity.KoboldWarriorEntity;
import net.mcreator.kobolds.entity.KoboldPirateEntity;
import net.mcreator.kobolds.entity.KoboldEntity;
import net.mcreator.kobolds.entity.KoboldEngineerEntity;
import net.mcreator.kobolds.entity.KoboldEnchanterEntity;
import net.mcreator.kobolds.entity.KoboldChildEntity;
import net.mcreator.kobolds.entity.KoboldCaptainEntity;
import net.mcreator.kobolds.entity.DrownedZomboldEntity;

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
	public static final EntityType<KoboldEngineerEntity> KOBOLD_ENGINEER = register("kobold_engineer",
			EntityType.Builder.<KoboldEngineerEntity>of(KoboldEngineerEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(KoboldEngineerEntity::new).sized(0.5f, 1.48f));
	public static final EntityType<KoboldPirateEntity> KOBOLD_PIRATE = register("kobold_pirate",
			EntityType.Builder.<KoboldPirateEntity>of(KoboldPirateEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(KoboldPirateEntity::new).sized(0.5f, 1.48f));
	public static final EntityType<KoboldCaptainEntity> KOBOLD_CAPTAIN = register("kobold_captain",
			EntityType.Builder.<KoboldCaptainEntity>of(KoboldCaptainEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(KoboldCaptainEntity::new).sized(0.5f, 1.48f));
	public static final EntityType<KoboldChildEntity> KOBOLD_CHILD = register("kobold_child",
			EntityType.Builder.<KoboldChildEntity>of(KoboldChildEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(KoboldChildEntity::new).sized(0.5f, 1.48f));
	public static final EntityType<ZomboldEntity> ZOMBOLD = register("zombold",
			EntityType.Builder.<ZomboldEntity>of(ZomboldEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
					.setUpdateInterval(3).setCustomClientFactory(ZomboldEntity::new).sized(0.5f, 1.48f));
	public static final EntityType<DrownedZomboldEntity> DROWNED_ZOMBOLD = register("drowned_zombold",
			EntityType.Builder.<DrownedZomboldEntity>of(DrownedZomboldEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(DrownedZomboldEntity::new).sized(0.5f, 1.48f));
	public static final EntityType<SkeleboldEntity> SKELEBOLD = register("skelebold",
			EntityType.Builder.<SkeleboldEntity>of(SkeleboldEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(SkeleboldEntity::new).sized(0.5f, 1.48f));

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
			KoboldEngineerEntity.init();
			KoboldPirateEntity.init();
			KoboldCaptainEntity.init();
			KoboldChildEntity.init();
			ZomboldEntity.init();
			DrownedZomboldEntity.init();
			SkeleboldEntity.init();
		});
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(KOBOLD, KoboldEntity.createAttributes().build());
		event.put(KOBOLD_WARRIOR, KoboldWarriorEntity.createAttributes().build());
		event.put(KOBOLD_ENCHANTER, KoboldEnchanterEntity.createAttributes().build());
		event.put(KOBOLD_ENGINEER, KoboldEngineerEntity.createAttributes().build());
		event.put(KOBOLD_PIRATE, KoboldPirateEntity.createAttributes().build());
		event.put(KOBOLD_CAPTAIN, KoboldCaptainEntity.createAttributes().build());
		event.put(KOBOLD_CHILD, KoboldChildEntity.createAttributes().build());
		event.put(ZOMBOLD, ZomboldEntity.createAttributes().build());
		event.put(DROWNED_ZOMBOLD, DrownedZomboldEntity.createAttributes().build());
		event.put(SKELEBOLD, SkeleboldEntity.createAttributes().build());
	}
}
