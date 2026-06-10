package com.example.umamusume.worldgen;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.structure.BuiltinStructureSets;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import java.util.List;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.BlockColumn;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import java.util.Optional;

public class    HipodromoStructure extends Structure {

    public static final MapCodec<HipodromoStructure> CODEC = simpleCodec(HipodromoStructure::new);

    public HipodromoStructure(StructureSettings settings) {
        super(settings);
    }

    // Sets de estruturas vanilla que não podem coincidir com o hipódromo.
    private static final List<ResourceKey<StructureSet>> ESTRUTURAS_A_EVITAR = List.of(
            BuiltinStructureSets.VILLAGES,
            BuiltinStructureSets.PILLAGER_OUTPOSTS
    );
    // Raio (em chunks) de exclusão ao redor do hipódromo (~70 blocos de footprint).
    private static final int RAIO_EXCLUSAO_CHUNKS = 10;

    @Override
    public Optional<GenerationStub> findGenerationPoint(GenerationContext context) {
        // Se já existe outra estrutura por perto, não gera o hipódromo aqui.
        for (ResourceKey<StructureSet> set : ESTRUTURAS_A_EVITAR) {
            if (temEstruturaPorPerto(context, set, RAIO_EXCLUSAO_CHUNKS)) {
                return Optional.empty();
            }
        }

        BlockPos centerPos = context.chunkPos().getMiddleBlockPosition(0);

        // Verifica água e gelo em vários pontos
        for (int dx = -26; dx <= 26; dx += 8) {
            for (int dz = -26; dz <= 26; dz += 8) {
                int sy = context.chunkGenerator().getFirstOccupiedHeight(
                        centerPos.getX() + dx, centerPos.getZ() + dz,
                        Heightmap.Types.WORLD_SURFACE_WG,
                        context.heightAccessor(), context.randomState());
                int fy = context.chunkGenerator().getFirstOccupiedHeight(
                        centerPos.getX() + dx, centerPos.getZ() + dz,
                        Heightmap.Types.OCEAN_FLOOR_WG,
                        context.heightAccessor(), context.randomState());
                if (sy > fy) return Optional.empty();

                BlockColumn col = context.chunkGenerator().getBaseColumn(
                        centerPos.getX() + dx, centerPos.getZ() + dz,
                        context.heightAccessor(), context.randomState());
                BlockState block = col.getBlock(sy);
                if (block.is(Blocks.ICE) || block.is(Blocks.PACKED_ICE) ||
                        block.is(Blocks.BLUE_ICE) || block.is(Blocks.FROSTED_ICE)) {
                    return Optional.empty();
                }
            }
        }

        // Verifica bioma em vários pontos
        var biomeSource = context.chunkGenerator().getBiomeSource();
        var climate = context.randomState().sampler();
        int[] checkX = {0, 26, 52, 0, 52, 0, 26, 52};
        int[] checkZ = {0, 0, 0, 35, 35, 70, 70, 70};

        for (int i = 0; i < checkX.length; i++) {
            var biome = biomeSource.getNoiseBiome(
                    (centerPos.getX() + checkX[i]) >> 2,
                    64 >> 2,
                    (centerPos.getZ() + checkZ[i]) >> 2,
                    climate
            );
            if (biome.is(net.minecraft.tags.BiomeTags.IS_OCEAN) ||
                    biome.is(Biomes.CHERRY_GROVE)) {
                return Optional.empty();
            }
        }

        int surfaceY = context.chunkGenerator().getFirstOccupiedHeight(
                centerPos.getX(), centerPos.getZ(),
                Heightmap.Types.WORLD_SURFACE_WG,
                context.heightAccessor(), context.randomState()
        );

        return onTopOfChunkCenter(context, Heightmap.Types.WORLD_SURFACE_WG,
                builder -> {
                    builder.addPiece(new HipodromoPiece(
                            context.structureTemplateManager(),
                            new BlockPos(centerPos.getX(), surfaceY, centerPos.getZ())
                    ));
                });
    }

    /**
     * Verifica, sem precisar do {@code ChunkGeneratorStructureState} (não exposto aqui),
     * se algum chunk de placement do {@code structureSet} cai dentro de {@code raioChunks}
     * chunks do hipódromo. Replica o que a {@code exclusion_zone} faz internamente, mas
     * permite checar vários sets de uma vez. Só funciona para sets do tipo random_spread
     * (caso de aldeias e postos de saqueadores).
     */
    private static boolean temEstruturaPorPerto(GenerationContext context,
                                                ResourceKey<StructureSet> structureSet,
                                                int raioChunks) {
        StructureSet set = context.registryAccess()
                .registryOrThrow(Registries.STRUCTURE_SET)
                .getOrThrow(structureSet);
        if (!(set.placement() instanceof RandomSpreadStructurePlacement spread)) {
            return false;
        }
        long seed = context.seed();
        int centerX = context.chunkPos().x;
        int centerZ = context.chunkPos().z;
        for (int x = centerX - raioChunks; x <= centerX + raioChunks; x++) {
            for (int z = centerZ - raioChunks; z <= centerZ + raioChunks; z++) {
                ChunkPos potencial = spread.getPotentialStructureChunk(seed, x, z);
                if (potencial.x == x && potencial.z == z) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public StructureType<?> type() {
        return ModStructures.HIPODROMO_TYPE.get();
    }
}