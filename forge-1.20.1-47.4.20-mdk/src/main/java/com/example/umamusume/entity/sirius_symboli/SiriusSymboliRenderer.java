package com.example.umamusume.entity.sirius_symboli;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import com.example.umamusume.UmaMusumeMod;

public class SiriusSymboliRenderer extends GeoEntityRenderer<com.example.umamusume.entity.sirius_symboli.SiriusSymboliEntity> {
    public SiriusSymboliRenderer(EntityRendererProvider.Context context) {
        super(context, new com.example.umamusume.entity.sirius_symboli.SiriusSymboliModel());
    }

    @Override
    public ResourceLocation getTextureLocation(com.example.umamusume.entity.sirius_symboli.SiriusSymboliEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(UmaMusumeMod.MOD_ID, "textures/entity/sirius_symboli.png");
    }
}