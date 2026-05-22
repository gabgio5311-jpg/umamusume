package com.example.umamusume.entity.tamamo_cross;

import com.example.umamusume.UmaMusumeMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class TamamoCrossModel extends GeoModel<TamamoCrossEntity> {

    @Override
    public ResourceLocation getModelResource(TamamoCrossEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(UmaMusumeMod.MOD_ID, "geo/tamamo_cross.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(TamamoCrossEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(UmaMusumeMod.MOD_ID, "textures/entity/tamamo_cross.png");
    }

    @Override
    public ResourceLocation getAnimationResource(TamamoCrossEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(UmaMusumeMod.MOD_ID, "animations/tamamo_cross.animation.json");
    }
}