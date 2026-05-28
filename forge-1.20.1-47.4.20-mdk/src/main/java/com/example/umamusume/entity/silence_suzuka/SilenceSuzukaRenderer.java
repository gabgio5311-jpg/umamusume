package com.example.umamusume.entity.silence_suzuka;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import com.example.umamusume.UmaMusumeMod;

public class SilenceSuzukaRenderer extends GeoEntityRenderer<SilenceSuzukaEntity> {
    public SilenceSuzukaRenderer(EntityRendererProvider.Context context) {
        super(context, new SilenceSuzukaModel());
    }

    @Override
    public ResourceLocation getTextureLocation(SilenceSuzukaEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(UmaMusumeMod.MOD_ID, "textures/entity/silence_suzuka.png");
    }
}