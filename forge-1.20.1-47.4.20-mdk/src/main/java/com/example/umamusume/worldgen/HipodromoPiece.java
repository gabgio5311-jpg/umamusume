package com.example.umamusume.worldgen;

import com.example.umamusume.ModEntities;
import com.example.umamusume.UmaMusumeMod;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

public class HipodromoPiece extends TemplateStructurePiece {

    private static final ResourceLocation TEMPLATE_IE = new ResourceLocation(UmaMusumeMod.MOD_ID, "hipodromoie");
    private static final ResourceLocation TEMPLATE_ID = new ResourceLocation(UmaMusumeMod.MOD_ID, "hipodromoid");
    private static final ResourceLocation TEMPLATE_SE = new ResourceLocation(UmaMusumeMod.MOD_ID, "hipodromose");
    private static final ResourceLocation TEMPLATE_SD = new ResourceLocation(UmaMusumeMod.MOD_ID, "hipodromosd");

    private final StructureTemplateManager templateManager;

    // CONSTRUTOR 1: Chamado quando o hipódromo é gerado pela primeira vez
    public HipodromoPiece(StructureTemplateManager manager, BlockPos pos) {
        super(ModStructures.HIPODROMO_PIECE.get(), 0, manager,
                TEMPLATE_ID,
                "hipodromoid",
                new StructurePlaceSettings().setMirror(Mirror.NONE).setRotation(Rotation.NONE),
                pos);
        this.templateManager = manager;

        int larguraTotal = 53;
        int alturaTotal = 8;
        int profundidadeTotal = 71;

        this.boundingBox = BoundingBox.fromCorners(pos, pos.offset(larguraTotal - 1, alturaTotal - 1, profundidadeTotal - 1));
    }

    // CONSTRUTOR 2: Chamado quando o Minecraft recarrega a chunk do mundo salvo
    public HipodromoPiece(StructurePieceSerializationContext context, CompoundTag tag) {
        super(ModStructures.HIPODROMO_PIECE.get(), tag, context.structureTemplateManager(),
                loc -> new StructurePlaceSettings().setMirror(Mirror.NONE).setRotation(Rotation.NONE));
        this.templateManager = context.structureTemplateManager();

        int larguraTotal = 53;
        int alturaTotal = 8;
        int profundidadeTotal = 71;

        BlockPos posBase = new BlockPos(this.boundingBox.minX(), this.boundingBox.minY(), this.boundingBox.minZ());
        this.boundingBox = BoundingBox.fromCorners(posBase, posBase.offset(larguraTotal - 1, alturaTotal - 1, profundidadeTotal - 1));
    }

    @Override
    protected void handleDataMarker(String marker, BlockPos pos, ServerLevelAccessor level, RandomSource random, BoundingBox box) {
    }

    @Override
    public void postProcess(net.minecraft.world.level.WorldGenLevel level,
                            net.minecraft.world.level.StructureManager structureManager,
                            net.minecraft.world.level.chunk.ChunkGenerator generator,
                            RandomSource random,
                            BoundingBox box,
                            net.minecraft.world.level.ChunkPos chunkPos,
                            BlockPos pos) {

        BoundingBox bounds = this.getBoundingBox();

        // Expansão do trator mantida (sua ideia genial!)
        int margemFolhas = 6;
        int minX = Math.max(bounds.minX() - margemFolhas, chunkPos.getMinBlockX());
        int maxX = Math.min(bounds.maxX() + margemFolhas, chunkPos.getMaxBlockX());
        int minZ = Math.max(bounds.minZ() - margemFolhas, chunkPos.getMinBlockZ());
        int maxZ = Math.min(bounds.maxZ() + margemFolhas, chunkPos.getMaxBlockZ());

        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

        // PARTE A: Limpeza superior (Ar) - Do nível exato do chão para CIMA
        for (int x = minX; x <= maxX; x++) {
            for (int z = minZ; z <= maxZ; z++) {
                // CORREÇÃO: Começa exatamente no minY (sem o -1) para não destruir o chão
                for (int y = bounds.minY(); y <= level.getMaxBuildHeight(); y++) {
                    mutablePos.set(x, y, z);
                    // Como roda antes da estrutura colar, podemos apagar tudo sem medo
                    if (!level.getBlockState(mutablePos).isAir()) {
                        level.setBlock(mutablePos, Blocks.AIR.defaultBlockState(), 2);
                    }
                }
            }
        }

        // PARTE B: Fundação inferior (Terra) - De 1 bloco abaixo do chão para BAIXO
        for (int x = minX; x <= maxX; x++) {
            for (int z = minZ; z <= maxZ; z++) {
                // CORREÇÃO: Começa no minY - 1 para fazer a base debaixo da estrutura
                for (int y = bounds.minY() - 1; y >= level.getMinBuildHeight(); y--) {
                    mutablePos.set(x, y, z);
                    BlockState currentState = level.getBlockState(mutablePos);
                    if (!currentState.isAir() && !currentState.is(Blocks.WATER) && !currentState.is(Blocks.TALL_GRASS)) {
                        break; // Bateu no chão firme do Minecraft, para de colocar terra
                    }
                    level.setBlock(mutablePos, Blocks.DIRT.defaultBlockState(), 2);
                }
            }
        }

        // RENDERIZA TODAS AS 4 PEÇAS MANUALMENTE
        BlockPos origem = new BlockPos(bounds.minX(), bounds.minY(), bounds.minZ());


        StructurePlaceSettings settings = new StructurePlaceSettings()
                .setMirror(Mirror.NONE)
                .setRotation(Rotation.NONE)
                .setBoundingBox(box); // clipa pra chunk atual mas renderiza sempre


// ID
        templateManager.getOrCreate(TEMPLATE_ID)
                .placeInWorld(level, origem, origem, settings, random, 2);


// IE
        templateManager.getOrCreate(TEMPLATE_IE)
                .placeInWorld(level, origem.offset(48, 0, 0), origem.offset(48, 0, 0), settings, random, 2);


// SD
        templateManager.getOrCreate(TEMPLATE_SD)
                .placeInWorld(level, origem.offset(0, 0, 48), origem.offset(0, 0, 48), settings, random, 2);


// SE
        templateManager.getOrCreate(TEMPLATE_SE)
                .placeInWorld(level, origem.offset(48, 0, 48), origem.offset(48, 0, 48), settings, random, 2);

        // PARTE A: Limpeza superior (Ar) - Do nível exato do chão para CIMA
        for (int x = minX; x <= maxX; x++) {
            for (int z = minZ; z <= maxZ; z++) {
                // CORREÇÃO: Começa exatamente no minY (sem o -1) para não destruir o chão
                for (int y = bounds.minY(); y <= level.getMaxBuildHeight(); y++) {
                    mutablePos.set(x, y, z);
                    BlockState state = level.getBlockState(mutablePos);
                    // Como roda antes da estrutura colar, podemos apagar tudo sem medo
                    if (!level.getBlockState(mutablePos).isAir()&& !state.is(Blocks.QUARTZ_BLOCK)
                            && !state.is(Blocks.QUARTZ_PILLAR)
                            && !state.is(Blocks.SMOOTH_QUARTZ)
                            && !state.is(Blocks.QUARTZ_STAIRS)
                            && !state.is(Blocks.IRON_BARS)
                            && !state.is(Blocks.IRON_BLOCK)
                            && !state.is(Blocks.GRASS_BLOCK)
                            && !state.is(Blocks.SANDSTONE)) {
                        level.setBlock(mutablePos, Blocks.AIR.defaultBlockState(), 2);
                    }
                }
            }
        }

        // SPAWN DAS GAROTAS
        int minY = bounds.minY();
        int centerX = bounds.minX() + 26;
        int centerZ = bounds.minZ() + 35;

        if (chunkPos.x == (centerX >> 4) && chunkPos.z == (centerZ >> 4)) {
            BlockPos spawnPos = new BlockPos(centerX, minY + 2, centerZ);

            java.util.List<net.minecraft.world.entity.EntityType<?>> umas = java.util.List.of(
                    ModEntities.SIRIUS_SYMBOLI.get(),
                    ModEntities.OGURI_CAP.get(),
                    ModEntities.TAMAMO_CROSS.get(),
                    ModEntities.RICE_SHOWER.get(),
                    ModEntities.MAMBO.get(),
                    ModEntities.GOLD_SHIP.get()
            );

            int offsetX = 0;
            for (net.minecraft.world.entity.EntityType<?> entityType : umas) {
                net.minecraft.world.entity.Entity entity = entityType.create(level.getLevel());
                if (entity != null) {
                    entity.moveTo(spawnPos.getX() + offsetX + 0.5, spawnPos.getY(), spawnPos.getZ() + 0.5, 0, 0);
                    if (entity instanceof net.minecraft.world.entity.Mob mob) {
                        mob.setPersistenceRequired();
                    }
                    level.addFreshEntity(entity);
                    offsetX += 3;
                }
            }
        }
    }
}