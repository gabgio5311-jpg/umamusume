package com.example.umamusume.entity.gold_ship;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;
import com.example.umamusume.UmaMusumeMod;

public class GoldShipModel extends GeoModel<com.example.umamusume.entity.gold_ship.GoldShipEntity> {

    @Override
    public ResourceLocation getModelResource(com.example.umamusume.entity.gold_ship.GoldShipEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(UmaMusumeMod.MOD_ID, "geo/gold_ship.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(com.example.umamusume.entity.gold_ship.GoldShipEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(UmaMusumeMod.MOD_ID, "textures/entity/gold_ship.png");
    }

    @Override
    public ResourceLocation getAnimationResource(com.example.umamusume.entity.gold_ship.GoldShipEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(UmaMusumeMod.MOD_ID, "animations/gold_ship.animation.json");
    }
}
