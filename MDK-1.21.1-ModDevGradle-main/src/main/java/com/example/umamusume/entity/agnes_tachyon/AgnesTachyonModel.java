package com.example.umamusume.entity.agnes_tachyon;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;
import com.example.umamusume.UmaMusumeMod;

public class AgnesTachyonModel extends GeoModel<com.example.umamusume.entity.agnes_tachyon.AgnesTachyonEntity> {

    @Override
    public ResourceLocation getModelResource(com.example.umamusume.entity.agnes_tachyon.AgnesTachyonEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(UmaMusumeMod.MOD_ID, "geo/agnes_tachyon.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(com.example.umamusume.entity.agnes_tachyon.AgnesTachyonEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(UmaMusumeMod.MOD_ID, "textures/entity/agnes_tachyon.png");
    }

    @Override
    public ResourceLocation getAnimationResource(com.example.umamusume.entity.agnes_tachyon.AgnesTachyonEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(UmaMusumeMod.MOD_ID, "animations/agnes_tachyon.animation.json");
    }
}
