package com.example.umamusume;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder; // Usamos DeferredHolder aqui também

public class ModCreativeTab {

    // Registrador de abas criativas
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, UmaMusumeMod.MOD_ID);

    // Substituímos RegistryObject por DeferredHolder
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> UMA_TAB = CREATIVE_TABS.register("uma_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.umamusume"))
                    // Lembre-se: agora usamos os nomes das constantes que atualizamos no ModItems (todas em MAIÚSCULAS)
                    .icon(() -> ModItems.SIRIUS_SYMBOLI_SPAWN_EGG.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.SIRIUS_SYMBOLI_SPAWN_EGG.get());
                        output.accept(ModItems.OGURI_CAP_SPAWN_EGG.get());
                        output.accept(ModItems.TAMAMO_CROSS_SPAWN_EGG.get());
                        output.accept(ModItems.RICE_SHOWER_SPAWN_EGG.get());
                        output.accept(ModItems.MAMBO_SPAWN_EGG.get());
                        output.accept(ModItems.GOLD_SHIP_SPAWN_EGG.get());
                        output.accept(ModItems.AGNES_TACHYON_SPAWN_EGG.get());
                        output.accept(ModItems.MEJIRO_MCQUEEN_SPAWN_EGG.get());
                        output.accept(ModItems.SILENCE_SUZUKA_SPAWN_EGG.get());
                    })
                    .build());
}