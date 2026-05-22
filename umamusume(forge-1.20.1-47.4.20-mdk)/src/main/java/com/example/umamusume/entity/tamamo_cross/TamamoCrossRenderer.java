package com.example.umamusume.entity.tamamo_cross;

import com.example.umamusume.UmaMusumeMod;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class TamamoCrossRenderer extends GeoEntityRenderer<TamamoCrossEntity> {

    public TamamoCrossRenderer(EntityRendererProvider.Context context) {
        super(context, new TamamoCrossModel());
    }

    @Override
    public ResourceLocation getTextureLocation(TamamoCrossEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(UmaMusumeMod.MOD_ID, "textures/entity/tamamo_cross.png");
    }
}