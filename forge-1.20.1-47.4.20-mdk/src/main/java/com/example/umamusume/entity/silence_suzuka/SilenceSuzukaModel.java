package com.example.umamusume.entity.silence_suzuka;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;
import com.example.umamusume.UmaMusumeMod;

public class SilenceSuzukaModel extends GeoModel<SilenceSuzukaEntity> {

    @Override
    public ResourceLocation getModelResource(SilenceSuzukaEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(UmaMusumeMod.MOD_ID, "geo/silence_suzuka.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SilenceSuzukaEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(UmaMusumeMod.MOD_ID, "textures/entity/silence_suzuka.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SilenceSuzukaEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(UmaMusumeMod.MOD_ID, "animations/silence_suzuka.animation.json");
    }
}
