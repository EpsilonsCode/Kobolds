package net.mcreator.kobolds.world.structure;

import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.Registry;
import net.minecraft.core.BlockPos;

import mcp.client.Start;

import java.util.List;

import com.mojang.serialization.Codec;

public class KoboldDenSmall extends StructureFeature<NoneFeatureConfiguration> {
	public KoboldDenSmall(Codec<NoneFeatureConfiguration> codec) {
		super(codec);
	}

	@Override
	public GenerationStep.Decoration step() {
		return GenerationStep.Decoration.SURFACE_STRUCTURES;
	}

	@Override
	public StructureFeature.StructureStartFactory<NoneFeatureConfiguration> getStartFactory() {
		return Start::new;
	}

	public static class Start extends StructureStart<NoneFeatureConfiguration> {
		public Start(StructureFeature<NoneFeatureConfiguration> structureIn, ChunkPos chunkPos, int references, long seedIn) {
			super(structureIn, chunkPos, references, seedIn);
		}

		@Override
		public void generatePieces(RegistryAccess registryAccess, ChunkGenerator chunkGenerator, StructureManager templateManagerIn,
				ChunkPos chunkPos, Biome biomeIn, NoneFeatureConfiguration config, LevelHeightAccessor levelHeightAccessor) {

			int x = (chunkPos.x << 4) + 7;
			int z = (chunkPos.z << 4) + 7;

			BlockPos blockpos = new BlockPos(x, -20, z);

			/*JigsawManager.func_242837_a(dynamicRegistryManager,
					new VillageConfig(() -> dynamicRegistryManager.getRegistry(Registry.JIGSAW_POOL_KEY)
							.getOrDefault(new ResourceLocation("kobolds", "koboldsmalldenpool")), 25),
					AbstractVillagePiece::new, chunkGenerator, templateManagerIn, blockpos, this.components, this.rand, true, true);
			this.components.forEach(piece -> piece.offset(0, 1, 0));
			this.components.forEach(piece -> piece.getBoundingBox().minY -= 1);
			this.getBoundingBox();*/
		}
	}
}
