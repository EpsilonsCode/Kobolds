package net.mcreator.kobolds.init;

import net.minecraftforge.fmlserverevents.FMLServerStartingEvent;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.FlatLevelSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;

import net.mcreator.kobolds.world.structure.StructureRegister;
import net.mcreator.kobolds.world.structure.StructureFeatureRegister;

import java.util.Map;
import java.util.HashMap;

import java.lang.reflect.Method;

import com.mojang.serialization.Codec;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class KoboldsModStructures {
	@SubscribeEvent
	public static void onClientSetup(FMLClientSetupEvent event) {
	}

	public void initElements() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		MinecraftForge.EVENT_BUS.register(this);
		IEventBus forgeBus = MinecraftForge.EVENT_BUS;
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		StructureRegister.STRUCTURES.register(modEventBus);
		modEventBus.addListener(this::setup);
		forgeBus.addListener(EventPriority.NORMAL, this::addDimensionalSpacing);
		forgeBus.addListener(EventPriority.HIGH, this::biomeModification);
	}

	public void setup(final FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			StructureRegister.setupStructures();
			StructureFeatureRegister.registerConfiguredStructures();
		});
	}

	public void biomeModification(final BiomeLoadingEvent event) {
		if (event.getCategory() == Biome.BiomeCategory.FOREST) {
			event.getGeneration().addStructureStart(StructureFeatureRegister.CONFIGURED_SMALL_DEN);
		}
	}

	private static Method GETCODEC_METHOD;

	public void addDimensionalSpacing(final WorldEvent.Load event) {
		if (event.getWorld() instanceof ServerLevel) {
			ServerLevel serverWorld = (ServerLevel) event.getWorld();
			try {
				if (GETCODEC_METHOD == null)
					GETCODEC_METHOD = ObfuscationReflectionHelper.findMethod(ChunkGenerator.class, "func_230347_a_");
				ResourceLocation cgRL = Registry.CHUNK_GENERATOR
						.getKey((Codec<? extends ChunkGenerator>) GETCODEC_METHOD.invoke(serverWorld.getChunkSource().generator));
				if (cgRL != null && cgRL.getNamespace().equals("terraforged"))
					return;
			} catch (Exception e) {
			}
			if (serverWorld.getChunkSource().getGenerator() instanceof FlatLevelSource
					|| serverWorld.dimensionType().equals(Level.OVERWORLD)) {
				return;
			}
			Map<StructureFeature<?>, StructureFeatureConfiguration> tempMap = new HashMap<>(
					serverWorld.getChunkSource().generator.getSettings().structureConfig());
			/*tempMap.putIfAbsent(StructureRegister.SMALL_DEN.get(),
					DimensionStructuresSettings.DEFAULTS.get(StructureRegister.SMALL_DEN.get()));*/
		}
	}

	public void init(FMLCommonSetupEvent event) {
		//DeferredWorkQueue.runLater(() -> {});
	}

	public void serverLoad(FMLServerStartingEvent event) {
	}
}
