package com.example.umamusume.worldgen;

import com.example.umamusume.UmaMusumeMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModStructures {

    public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES =
            DeferredRegister.create(Registries.STRUCTURE_TYPE, UmaMusumeMod.MOD_ID);

    public static final DeferredRegister<StructurePieceType> STRUCTURE_PIECE_TYPES =
            DeferredRegister.create(Registries.STRUCTURE_PIECE, UmaMusumeMod.MOD_ID);

    public static final RegistryObject<StructureType<HipodromoStructure>> HIPODROMO_TYPE =
            STRUCTURE_TYPES.register("hipodromo",
                    () -> () -> HipodromoStructure.CODEC);

    public static final RegistryObject<StructurePieceType> HIPODROMO_PIECE =
            STRUCTURE_PIECE_TYPES.register("hipodromo_piece",
                    () -> HipodromoPiece::new);
}