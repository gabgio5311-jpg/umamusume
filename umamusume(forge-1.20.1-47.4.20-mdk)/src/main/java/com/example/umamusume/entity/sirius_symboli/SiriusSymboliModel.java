package com.example.umamusume.entity.sirius_symboli;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;
import com.example.umamusume.UmaMusumeMod;

public class SiriusSymboliModel extends GeoModel<com.example.umamusume.entity.sirius_symboli.SiriusSymboliEntity> {

    @Override
    public ResourceLocation getModelResource(com.example.umamusume.entity.sirius_symboli.SiriusSymboliEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(UmaMusumeMod.MOD_ID, "geo/sirius_symboli.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(com.example.umamusume.entity.sirius_symboli.SiriusSymboliEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(UmaMusumeMod.MOD_ID, "textures/entity/sirius_symboli.png");
    }

    @Override
    public ResourceLocation getAnimationResource(com.example.umamusume.entity.sirius_symboli.SiriusSymboliEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(UmaMusumeMod.MOD_ID, "animations/sirius_symboli.animation.json");
    }
}
