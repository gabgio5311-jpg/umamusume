package com.example.umamusume.entity.mejiro_mcqueen;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;
import com.example.umamusume.UmaMusumeMod;

public class MejiroMcQueenModel extends GeoModel<MejiroMcQueenEntity> {

    @Override
    public ResourceLocation getModelResource(MejiroMcQueenEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(UmaMusumeMod.MOD_ID, "geo/mejiro_mcqueen.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MejiroMcQueenEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(UmaMusumeMod.MOD_ID, "textures/entity/mejiro_mcqueen.png");
    }

    @Override
    public ResourceLocation getAnimationResource(MejiroMcQueenEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(UmaMusumeMod.MOD_ID, "animations/mejiro_mcqueen.animation.json");
    }
}
