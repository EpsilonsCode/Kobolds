package net.mcreator.kobolds.world.structure;

import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.StructurePieceAccessor;
import net.minecraft.world.level.levelgen.feature.structures.JigsawPlacement;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.Registry;
import net.minecraft.core.BlockPos;

import mcp.client.Start;

import com.mojang.serialization.Codec;

public class KoboldDenSmall extends StructureFeature<NoneFeatureConfiguration> {
	public KoboldDenSmall(Codec<NoneFeatureConfiguration> codec) {
		super(codec);
	}

	@Override
	public StructureFeature.StructureStartFactory<NoneFeatureConfiguration> getStartFactory() {
		return KoboldDenSmall.Start::new;
	}

	@Override
	public GenerationStep.Decoration step() {
		return GenerationStep.Decoration.SURFACE_STRUCTURES;
	}

	public static class Start extends StructureStart<NoneFeatureConfiguration> {
		public Start(StructureFeature<NoneFeatureConfiguration> structureIn, ChunkPos chunkPos, int references, long seedIn) {
			super(structureIn, chunkPos, references, seedIn);
		}

		@Override
		public void generatePieces(RegistryAccess registryAccess, ChunkGenerator chunkGenerator, StructureManager templateManagerIn,
 ChunkPos chunkPos, Biome biomeIn, NoneFeatureConfiguration config, LevelHeightAccessor height) {
				
			int x = (chunkPos.x << 4) + 7;
			int z = (chunkPos.z << 4) + 7;
			
			BlockPos blockpos = new BlockPos(x, -20, z);
			
			JigsawPlacement.addPieces(registryAccess,
 new JigsawConfiguration(() -> registryAccess.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY)
.get(new ResourceLocation("kobolds", "koboldsmalldenpool")), 25),
 PoolElementStructurePiece::new, chunkGenerator, templateManagerIn, blockpos, this, this.random, true, true, height);
					
			this.pieces.forEach(piece -> piece.move(0, 1, 0));
			//this.pieces.forEach(piece -> piece.getBoundingBox().minY -= 1);

			//this.recalculateStructureSize();
			this.getBoundingBox();
		}
	}
}
