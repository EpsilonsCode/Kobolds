package net.mcreator.kobolds.world.structure;

import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.core.Registry;

public class StructureFeatureRegister {
	public static final ConfiguredStructureFeature<?, ?> CONFIGURED_SMALL_DEN = StructureRegister.SMALL_DEN.get().configured(FeatureConfiguration.NONE);

	public static void registerConfiguredStructures() {
		Registry<ConfiguredStructureFeature<?, ?>> registry = BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE;

		Registry.register(registry, new ResourceLocation("kobolds", "small_den"), CONFIGURED_SMALL_DEN);
	}
}
