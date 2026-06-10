package com.example.umamusume;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem; // Importante: NeoForge usa DeferredItem
import net.neoforged.neoforge.common.DeferredSpawnEggItem; // Classe correta para ovos de spawn

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(UmaMusumeMod.MOD_ID);

    public static final DeferredItem<Item> SIRIUS_SYMBOLI_SPAWN_EGG = ITEMS.register("sirius_symboli_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.SIRIUS_SYMBOLI, 0x1A5F5F, 0x4A6B50, new Item.Properties()));

    public static final DeferredItem<Item> OGURI_CAP_SPAWN_EGG = ITEMS.register("oguri_cap_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.OGURI_CAP, 0xFFFFF, 0xae9fe1, new Item.Properties()));

    public static final DeferredItem<Item> TAMAMO_CROSS_SPAWN_EGG = ITEMS.register("tamamo_cross_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.TAMAMO_CROSS, 0x4169e1, 0xFFFFFF, new Item.Properties()));

    public static final DeferredItem<Item> RICE_SHOWER_SPAWN_EGG = ITEMS.register("rice_shower_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.RICE_SHOWER, 0x32244a, 0x76273c, new Item.Properties()));

    public static final DeferredItem<Item> MAMBO_SPAWN_EGG = ITEMS.register("mambo_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.MAMBO, 0x7a70ad, 0x7b304b, new Item.Properties()));

    public static final DeferredItem<Item> GOLD_SHIP_SPAWN_EGG = ITEMS.register("gold_ship_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.GOLD_SHIP, 0xff0002, 0xcfd5d6, new Item.Properties()));

    public static final DeferredItem<Item> AGNES_TACHYON_SPAWN_EGG = ITEMS.register("agnes_tachyon_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.AGNES_TACHYON, 0xff0002, 0xcfd5d6, new Item.Properties()));

    public static final DeferredItem<Item> MEJIRO_MCQUEEN_SPAWN_EGG = ITEMS.register("mejiro_mcqueen_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.MEJIRO_MCQUEEN, 0xff0002, 0xcfd5d6, new Item.Properties()));

    public static final DeferredItem<Item> SILENCE_SUZUKA_SPAWN_EGG = ITEMS.register("silence_suzuka_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.SILENCE_SUZUKA, 0xff0002, 0xcfd5d6, new Item.Properties()));
}