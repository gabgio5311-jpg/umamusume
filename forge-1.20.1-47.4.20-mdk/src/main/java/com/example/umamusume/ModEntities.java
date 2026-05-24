package com.example.umamusume;

import com.example.umamusume.entity.sirius_symboli.SiriusSymboliEntity;
import com.example.umamusume.entity.oguri_cap.OguriCapEntity;
import com.example.umamusume.entity.tamamo_cross.TamamoCrossEntity;
import com.example.umamusume.entity.rice_shower.RiceShowerEntity;
import com.example.umamusume.entity.mambo.MamboEntity;
import com.example.umamusume.entity.gold_ship.GoldShipEntity;
import com.example.umamusume.entity.agnes_tachyon.AgnesTachyonEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, UmaMusumeMod.MOD_ID);

    public static final RegistryObject<EntityType<SiriusSymboliEntity>> SIRIUS_SYMBOLI =
            ENTITY_TYPES.register("sirius_symboli",
                    () -> EntityType.Builder.of(SiriusSymboliEntity::new, MobCategory.CREATURE)
                            .sized(0.6f, 1.8f)
                            .build("sirius_symboli"));
    public static final RegistryObject<EntityType<OguriCapEntity>> OGURI_CAP =
            ENTITY_TYPES.register("oguri_cap",
                    () -> EntityType.Builder.of(OguriCapEntity::new, MobCategory.CREATURE)
                            .sized(0.6f, 1.8f)
                            .build("oguri_cap"));

    public static final RegistryObject<EntityType<TamamoCrossEntity>> TAMAMO_CROSS =
            ENTITY_TYPES.register("tamamo_cross",
                    () -> EntityType.Builder.of(TamamoCrossEntity::new, MobCategory.CREATURE)
                            .sized(0.6f, 1.8f)
                            .build("tamamo_cross"));
    public static final RegistryObject<EntityType<RiceShowerEntity>> RICE_SHOWER =
            ENTITY_TYPES.register("rice_shower",
                    () -> EntityType.Builder.of(RiceShowerEntity::new, MobCategory.CREATURE)
                            .sized(0.6f, 1.8f)
                            .build("rice_shower"));
    public static final RegistryObject<EntityType<MamboEntity>> MAMBO =
            ENTITY_TYPES.register("mambo",
                    () -> EntityType.Builder.of(MamboEntity::new, MobCategory.CREATURE)
                            .sized(0.6f, 1.8f)
                            .build("mambo"));
    public static final RegistryObject<EntityType<GoldShipEntity>> GOLD_SHIP =
            ENTITY_TYPES.register("gold_ship",
                    () -> EntityType.Builder.of(GoldShipEntity::new, MobCategory.CREATURE)
                            .sized(0.6f, 1.8f)
                            .build("gold_ship"));
    public static final RegistryObject<EntityType<AgnesTachyonEntity>> AGNES_TACHYON =
            ENTITY_TYPES.register("agnes_tachyon",
                    () -> EntityType.Builder.of(AgnesTachyonEntity::new, MobCategory.CREATURE)
                            .sized(0.6f, 1.8f)
                            .build("agnes_tachyon"));
}

