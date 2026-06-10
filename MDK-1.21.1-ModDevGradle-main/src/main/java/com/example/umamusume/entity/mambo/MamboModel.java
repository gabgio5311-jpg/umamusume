package com.example.umamusume.entity.mambo;

import com.example.umamusume.UmaMusumeMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class MamboModel extends GeoModel<com.example.umamusume.entity.mambo.MamboEntity> {

    @Override
    public ResourceLocation getModelResource(MamboEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(UmaMusumeMod.MOD_ID, "geo/mambo.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MamboEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(UmaMusumeMod.MOD_ID, "textures/entity/mambo.png");
    }

    @Override
    public ResourceLocation getAnimationResource(MamboEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(UmaMusumeMod.MOD_ID, "animations/mambo.animation.json");
    }
}