package com.example.umamusume.entity.mejiro_mcqueen;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import com.example.umamusume.UmaMusumeMod;

public class MejiroMcQueenRenderer extends GeoEntityRenderer<MejiroMcQueenEntity> {
    public MejiroMcQueenRenderer(EntityRendererProvider.Context context) {
        super(context, new MejiroMcQueenModel());
    }

    @Override
    public ResourceLocation getTextureLocation(MejiroMcQueenEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(UmaMusumeMod.MOD_ID, "textures/entity/mejiro_mcqueen.png");
    }
}