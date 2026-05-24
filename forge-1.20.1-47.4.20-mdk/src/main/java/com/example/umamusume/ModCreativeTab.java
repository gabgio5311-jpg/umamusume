package com.example.umamusume;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTab {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, UmaMusumeMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> UMA_TAB = CREATIVE_TABS.register("uma_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.umamusume"))
                    .icon(() -> ModItems.sirius_symboli_spawn_egg.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.sirius_symboli_spawn_egg.get());
                        output.accept(ModItems.oguri_cap_spawn_egg.get());
                        output.accept(ModItems.tamamo_cross_spawn_egg.get());
                        output.accept(ModItems.rice_shower_spawn_egg.get());
                        output.accept(ModItems.mambo_spawn_egg.get());
                        output.accept(ModItems.gold_ship_spawn_egg.get());
                        output.accept(ModItems.agnes_tachyon_spawn_egg.get());
                        output.accept(ModItems.mejiro_mcqueen_spawn_egg.get());
                    })
                    .build());
}