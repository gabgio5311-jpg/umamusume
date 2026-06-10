package com.example.umamusume.entity.rice_shower;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import com.example.umamusume.UmaMusumeMod;

public class RiceShowerRenderer extends GeoEntityRenderer<com.example.umamusume.entity.rice_shower.RiceShowerEntity> {
    public RiceShowerRenderer(EntityRendererProvider.Context context) {
        super(context, new com.example.umamusume.entity.rice_shower.RiceShowerModel());
    }

    @Override
    public ResourceLocation getTextureLocation(com.example.umamusume.entity.rice_shower.RiceShowerEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(UmaMusumeMod.MOD_ID, "textures/entity/rice_shower.png");
    }
}