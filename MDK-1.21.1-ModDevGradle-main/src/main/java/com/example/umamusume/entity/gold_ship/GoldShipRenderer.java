package com.example.umamusume.entity.gold_ship;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import com.example.umamusume.UmaMusumeMod;

public class GoldShipRenderer extends GeoEntityRenderer<com.example.umamusume.entity.gold_ship.GoldShipEntity> {
    public GoldShipRenderer(EntityRendererProvider.Context context) {
        super(context, new com.example.umamusume.entity.gold_ship.GoldShipModel());
    }

    @Override
    public ResourceLocation getTextureLocation(com.example.umamusume.entity.gold_ship.GoldShipEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(UmaMusumeMod.MOD_ID, "textures/entity/gold_ship.png");
    }
}