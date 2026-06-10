package com.example.umamusume;

import com.example.umamusume.entity.sirius_symboli.SiriusSymboliEntity;
import com.example.umamusume.entity.oguri_cap.OguriCapEntity;
import com.example.umamusume.entity.tamamo_cross.TamamoCrossEntity;
import com.example.umamusume.entity.rice_shower.RiceShowerEntity;
import com.example.umamusume.entity.mambo.MamboEntity;
import com.example.umamusume.entity.gold_ship.GoldShipEntity;
import com.example.umamusume.entity.agnes_tachyon.AgnesTachyonEntity;
import com.example.umamusume.entity.mejiro_mcqueen.MejiroMcQueenEntity;
import com.example.umamusume.entity.silence_suzuka.SilenceSuzukaEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder; // NeoForge agora usa DeferredHolder

public class ModEntities {

    // O NeoForge 1.21.1 usa BuiltInRegistries.ENTITY_TYPES para o registro
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, UmaMusumeMod.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<SiriusSymboliEntity>> SIRIUS_SYMBOLI =
            ENTITY_TYPES.register("sirius_symboli",
                    () -> EntityType.Builder.of(SiriusSymboliEntity::new, MobCategory.CREATURE)
                            .sized(0.6f, 1.8f)
                            .build("sirius_symboli"));

    public static final DeferredHolder<EntityType<?>, EntityType<OguriCapEntity>> OGURI_CAP =
            ENTITY_TYPES.register("oguri_cap",
                    () -> EntityType.Builder.of(OguriCapEntity::new, MobCategory.CREATURE)
                            .sized(0.6f, 1.8f)
                            .build("oguri_cap"));

    public static final DeferredHolder<EntityType<?>, EntityType<TamamoCrossEntity>> TAMAMO_CROSS =
            ENTITY_TYPES.register("tamamo_cross",
                    () -> EntityType.Builder.of(TamamoCrossEntity::new, MobCategory.CREATURE)
                            .sized(0.6f, 1.8f)
                            .build("tamamo_cross"));

    public static final DeferredHolder<EntityType<?>, EntityType<RiceShowerEntity>> RICE_SHOWER =
            ENTITY_TYPES.register("rice_shower",
                    () -> EntityType.Builder.of(RiceShowerEntity::new, MobCategory.CREATURE)
                            .sized(0.6f, 1.8f)
                            .build("rice_shower"));

    public static final DeferredHolder<EntityType<?>, EntityType<MamboEntity>> MAMBO =
            ENTITY_TYPES.register("mambo",
                    () -> EntityType.Builder.of(MamboEntity::new, MobCategory.CREATURE)
                            .sized(0.6f, 1.8f)
                            .build("mambo"));

    public static final DeferredHolder<EntityType<?>, EntityType<GoldShipEntity>> GOLD_SHIP =
            ENTITY_TYPES.register("gold_ship",
                    () -> EntityType.Builder.of(GoldShipEntity::new, MobCategory.CREATURE)
                            .sized(0.6f, 1.8f)
                            .build("gold_ship"));

    public static final DeferredHolder<EntityType<?>, EntityType<AgnesTachyonEntity>> AGNES_TACHYON =
            ENTITY_TYPES.register("agnes_tachyon",
                    () -> EntityType.Builder.of(AgnesTachyonEntity::new, MobCategory.CREATURE)
                            .sized(0.6f, 1.8f)
                            .build("agnes_tachyon"));

    public static final DeferredHolder<EntityType<?>, EntityType<MejiroMcQueenEntity>> MEJIRO_MCQUEEN =
            ENTITY_TYPES.register("mejiro_mcqueen",
                    () -> EntityType.Builder.of(MejiroMcQueenEntity::new, MobCategory.CREATURE)
                            .sized(0.6f, 1.8f)
                            .build("mejiro_mcqueen"));

    public static final DeferredHolder<EntityType<?>, EntityType<SilenceSuzukaEntity>> SILENCE_SUZUKA =
            ENTITY_TYPES.register("silence_suzuka",
                    () -> EntityType.Builder.of(SilenceSuzukaEntity::new, MobCategory.CREATURE)
                            .sized(0.6f, 1.8f)
                            .build("silence_suzuka"));
}