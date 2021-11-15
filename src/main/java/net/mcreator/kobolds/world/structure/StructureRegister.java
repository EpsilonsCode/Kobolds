package net.mcreator.kobolds.world.structure;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fmllegacy.RegistryObject;

import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.data.BuiltinRegistries;

import net.mcreator.kobolds.world.structure.KoboldDenSmall;

import java.util.Map;
import java.util.HashMap;

import com.google.common.collect.ImmutableMap;

public class StructureRegister {
	public static final DeferredRegister<StructureFeature<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, "kobolds");

	public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> SMALL_DEN = STRUCTURES.register("kobold_den_small",() -> (new KoboldDenSmall(NoneFeatureConfiguration.CODEC)));

	public static void setupStructures() {
		setupMapSpacingAndLand(SMALL_DEN.get(), new StructureFeatureConfiguration(12, 9, 432676512), true);
	}

	public static <F extends StructureFeature<?>> void setupMapSpacingAndLand(F structure, StructureFeatureConfiguration structureSeparationSettings, boolean transformSurroundingLand) {

		StructureFeature.STRUCTURES_REGISTRY.put(structure.getRegistryName().toString(), structure);

		if (transformSurroundingLand) {
			BuiltinRegistries.NOISE_GENERATOR_SETTINGS.entrySet().forEach(settings -> {
				Map<StructureFeature<?>, StructureFeatureConfiguration> structureMap = settings.getValue().structureSettings().structureConfig();
				
				if (structureMap instanceof ImmutableMap) {
					Map<StructureFeature<?>, StructureFeatureConfiguration> tempMap = new HashMap<>(structureMap);
					tempMap.put(structure, structureSeparationSettings);
				} else {
					structureMap.put(structure, structureSeparationSettings);
				}
			});
		}
	}
}
