package com.example.umamusume.entity.agnes_tachyon;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import com.example.umamusume.UmaMusumeMod;

public class AgnesTachyonRenderer extends GeoEntityRenderer<com.example.umamusume.entity.agnes_tachyon.AgnesTachyonEntity> {
    public AgnesTachyonRenderer(EntityRendererProvider.Context context) {
        super(context, new com.example.umamusume.entity.agnes_tachyon.AgnesTachyonModel());
    }

    @Override
    public ResourceLocation getTextureLocation(com.example.umamusume.entity.agnes_tachyon.AgnesTachyonEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(UmaMusumeMod.MOD_ID, "textures/entity/agnes_tachyon.png");
    }
}