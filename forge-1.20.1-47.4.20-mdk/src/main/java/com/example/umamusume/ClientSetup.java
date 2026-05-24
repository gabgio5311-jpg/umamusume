package com.example.umamusume;

import com.example.umamusume.entity.sirius_symboli.SiriusSymboliRenderer;
import com.example.umamusume.entity.oguri_cap.OguriCapRenderer;
import com.example.umamusume.entity.tamamo_cross.TamamoCrossRenderer;
import com.example.umamusume.entity.rice_shower.RiceShowerRenderer;
import com.example.umamusume.entity.mambo.MamboRenderer;
import com.example.umamusume.entity.gold_ship.GoldShipRenderer;
import com.example.umamusume.entity.agnes_tachyon.AgnesTachyonRenderer;
import com.example.umamusume.entity.mejiro_mcqueen.MejiroMcQueenRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = UmaMusumeMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.SIRIUS_SYMBOLI.get(), SiriusSymboliRenderer::new);
        event.registerEntityRenderer(ModEntities.OGURI_CAP.get(), OguriCapRenderer::new);
        event.registerEntityRenderer(ModEntities.TAMAMO_CROSS.get(), TamamoCrossRenderer::new);
        event.registerEntityRenderer(ModEntities.RICE_SHOWER.get(), RiceShowerRenderer::new);
        event.registerEntityRenderer(ModEntities.MAMBO.get(), MamboRenderer::new);
        event.registerEntityRenderer(ModEntities.GOLD_SHIP.get(), GoldShipRenderer::new);
        event.registerEntityRenderer(ModEntities.AGNES_TACHYON.get(), AgnesTachyonRenderer::new);
        event.registerEntityRenderer(ModEntities.MEJIRO_MCQUEEN.get(), MejiroMcQueenRenderer::new);
    }
}