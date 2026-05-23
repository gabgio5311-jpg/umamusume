package com.example.umamusume.entity.oguri_cap;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import com.example.umamusume.UmaMusumeMod;

public class OguriCapRenderer extends GeoEntityRenderer<com.example.umamusume.entity.oguri_cap.OguriCapEntity> {
    public OguriCapRenderer(EntityRendererProvider.Context context) {
        super(context, new com.example.umamusume.entity.oguri_cap.OguriCapModel());
    }

    @Override
    public ResourceLocation getTextureLocation(com.example.umamusume.entity.oguri_cap.OguriCapEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(UmaMusumeMod.MOD_ID, "textures/entity/oguri_cap.png");
    }
}