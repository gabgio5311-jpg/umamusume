package com.example.umamusume;

import com.example.umamusume.entity.sirius_symboli.SiriusSymboliEntity;
import com.example.umamusume.entity.oguri_cap.OguriCapEntity;
import com.example.umamusume.entity.tamamo_cross.TamamoCrossEntity;
import com.example.umamusume.entity.rice_shower.RiceShowerEntity;
import com.example.umamusume.entity.mambo.MamboEntity;
import com.example.umamusume.entity.gold_ship.GoldShipEntity;
import com.example.umamusume.worldgen.ModStructures;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib.GeckoLib;

@Mod(UmaMusumeMod.MOD_ID)
public class UmaMusumeMod {

    public static final String MOD_ID = "umamusume";

    public UmaMusumeMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        GeckoLib.initialize();

        bus.addListener(this::registerAttributes);
        ModEntities.ENTITY_TYPES.register(bus);
        ModItems.ITEMS.register(bus);
        ModCreativeTab.CREATIVE_TABS.register(bus);
        ModStructures.STRUCTURE_TYPES.register(bus);
        ModStructures.STRUCTURE_PIECE_TYPES.register(bus);
    }

    private void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.SIRIUS_SYMBOLI.get(), SiriusSymboliEntity.createAttributes().build());
        event.put(ModEntities.OGURI_CAP.get(), OguriCapEntity.createAttributes().build());
        event.put(ModEntities.TAMAMO_CROSS.get(), TamamoCrossEntity.createAttributes().build());
        event.put(ModEntities.RICE_SHOWER.get(), RiceShowerEntity.createAttributes().build());
        event.put(ModEntities.MAMBO.get(), MamboEntity.createAttributes().build());
        event.put(ModEntities.GOLD_SHIP.get(), GoldShipEntity.createAttributes().build());
    }
    public static final org.apache.logging.log4j.Logger LOGGER =
            org.apache.logging.log4j.LogManager.getLogger();

}

