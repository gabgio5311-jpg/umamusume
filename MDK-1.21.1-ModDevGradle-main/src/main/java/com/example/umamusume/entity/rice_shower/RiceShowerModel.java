package com.example.umamusume.entity.rice_shower;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;
import com.example.umamusume.UmaMusumeMod;

public class RiceShowerModel extends GeoModel<com.example.umamusume.entity.rice_shower.RiceShowerEntity> {

    @Override
    public ResourceLocation getModelResource(com.example.umamusume.entity.rice_shower.RiceShowerEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(UmaMusumeMod.MOD_ID, "geo/rice_shower.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(com.example.umamusume.entity.rice_shower.RiceShowerEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(UmaMusumeMod.MOD_ID, "textures/entity/rice_shower.png");
    }

    @Override
    public ResourceLocation getAnimationResource(com.example.umamusume.entity.rice_shower.RiceShowerEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(UmaMusumeMod.MOD_ID, "animations/rice_shower.animation.json");
    }
}
