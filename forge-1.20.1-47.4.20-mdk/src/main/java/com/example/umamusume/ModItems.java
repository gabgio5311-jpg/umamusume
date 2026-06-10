package com.example.umamusume;

import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, UmaMusumeMod.MOD_ID);

    public static final RegistryObject<Item> sirius_symboli_spawn_egg =
            ITEMS.register("sirius_symboli_spawn_egg",
                    () -> new ForgeSpawnEggItem(ModEntities.SIRIUS_SYMBOLI, 0x1A5F5F, 0x4A6B50,
                            new Item.Properties()));
    public static final RegistryObject<Item> oguri_cap_spawn_egg =
            ITEMS.register("oguri_cap_spawn_egg",
                    () -> new ForgeSpawnEggItem(ModEntities.OGURI_CAP, 0xFFFFF, 0xae9fe1,
                            new Item.Properties()));
    public static final RegistryObject<Item> tamamo_cross_spawn_egg =
            ITEMS.register("tamamo_cross_spawn_egg",
                    () -> new ForgeSpawnEggItem(ModEntities.TAMAMO_CROSS, 0x4169e1, 0xFFFFFF,
                            new Item.Properties()));
    public static final RegistryObject<Item> rice_shower_spawn_egg =
            ITEMS.register("rice_shower_spawn_egg",
                    () -> new ForgeSpawnEggItem(ModEntities.RICE_SHOWER, 0x32244a, 0x76273c,
                            new Item.Properties()));
    public static final RegistryObject<Item> mambo_spawn_egg =
            ITEMS.register("mambo_spawn_egg",
                    () -> new ForgeSpawnEggItem(ModEntities.MAMBO, 0x7a70ad, 0x7b304b,
                            new Item.Properties()));
    public static final RegistryObject<Item> gold_ship_spawn_egg =
            ITEMS.register("gold_ship_spawn_egg",
                    () -> new ForgeSpawnEggItem(ModEntities.GOLD_SHIP, 0xff0002, 0xcfd5d6,
                            new Item.Properties()));
    public static final RegistryObject<Item> agnes_tachyon_spawn_egg =
            ITEMS.register("agnes_tachyon_spawn_egg",
                    () -> new ForgeSpawnEggItem(ModEntities.AGNES_TACHYON, 0xff0002, 0xcfd5d6,
                            new Item.Properties()));
    public static final RegistryObject<Item> mejiro_mcqueen_spawn_egg =
            ITEMS.register("mejiro_mcqueen_spawn_egg",
                    () -> new ForgeSpawnEggItem(ModEntities.MEJIRO_MCQUEEN, 0xff0002, 0xcfd5d6,
                            new Item.Properties()));
    public static final RegistryObject<Item> silence_suzuka_spawn_egg =
            ITEMS.register("silence_suzuka_spawn_egg",
                    () -> new ForgeSpawnEggItem(ModEntities.SILENCE_SUZUKA, 0xff0002, 0xcfd5d6,
                            new Item.Properties()));
}