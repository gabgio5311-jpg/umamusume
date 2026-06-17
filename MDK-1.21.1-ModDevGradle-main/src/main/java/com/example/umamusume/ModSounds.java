package com.example.umamusume;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModSounds {

    // Cria o registro base para os eventos de som do seu mod
    public static final DeferredRegister<SoundEvent> SOUNDS =
            DeferredRegister.create(Registries.SOUND_EVENT, "umamusume");

    // Registra o seu som "mambo"
    public static final DeferredHolder<SoundEvent, SoundEvent> MAMBO = SOUNDS.register("mambo",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("umamusume", "mambo")));

}