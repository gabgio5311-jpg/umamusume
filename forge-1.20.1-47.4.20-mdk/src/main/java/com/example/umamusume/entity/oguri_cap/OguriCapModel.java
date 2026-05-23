package com.example.umamusume.entity.oguri_cap;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;
import com.example.umamusume.UmaMusumeMod;

public class OguriCapModel extends GeoModel<com.example.umamusume.entity.oguri_cap.OguriCapEntity> {

    @Override
    public ResourceLocation getModelResource(com.example.umamusume.entity.oguri_cap.OguriCapEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(UmaMusumeMod.MOD_ID, "geo/oguri_cap.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(com.example.umamusume.entity.oguri_cap.OguriCapEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(UmaMusumeMod.MOD_ID, "textures/entity/oguri_cap.png");
    }

    @Override
    public ResourceLocation getAnimationResource(com.example.umamusume.entity.oguri_cap.OguriCapEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(UmaMusumeMod.MOD_ID, "animations/oguri_cap.animation.json");
    }
}