package com.example.umamusume.worldgen;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.BlockColumn;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import java.util.Optional;

public class HipodromoStructure extends Structure {

    public static final Codec<HipodromoStructure> CODEC = simpleCodec(HipodromoStructure::new);

    public HipodromoStructure(StructureSettings settings) {
        super(settings);
    }

    @Override
    public Optional<GenerationStub> findGenerationPoint(GenerationContext context) {
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
                    biome.value().equals(context.registryAccess()
                            .registryOrThrow(net.minecraft.core.registries.Registries.BIOME)
                            .get(new ResourceLocation("minecraft", "cherry_grove")))) {
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

    @Override
    public StructureType<?> type() {
        return ModStructures.HIPODROMO_TYPE.get();
    }
}