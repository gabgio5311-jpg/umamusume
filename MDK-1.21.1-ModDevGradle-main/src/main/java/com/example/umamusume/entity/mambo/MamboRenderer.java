package com.example.umamusume.entity.mambo;

import com.example.umamusume.UmaMusumeMod;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class MamboRenderer extends GeoEntityRenderer<MamboEntity> {

    public MamboRenderer(EntityRendererProvider.Context context) {
        super(context, new MamboModel());
    }

    @Override
    public ResourceLocation getTextureLocation(MamboEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(UmaMusumeMod.MOD_ID, "textures/entity/mambo.png");
    }
}