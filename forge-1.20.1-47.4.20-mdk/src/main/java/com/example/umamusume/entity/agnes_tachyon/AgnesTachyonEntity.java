package com.example.umamusume.entity.agnes_tachyon;

import com.example.umamusume.UmaMusumeMod;
import com.example.umamusume.entity.BaseUmaEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

public class AgnesTachyonEntity extends BaseUmaEntity implements GeoEntity {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public AgnesTachyonEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    @Override
    protected String getUmaName() {
        return "Agnes Tachyon";
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 4, state -> {
            if (state.isMoving())
                return state.setAndContinue(RawAnimation.begin().thenLoop("agnes_tachyon.animation.run"));
            return state.setAndContinue(RawAnimation.begin().thenLoop("agnes_tachyon.animation.idle"));
        }));
    }
    @Override
    protected ResourceLocation getGuiTexture() {
        return new ResourceLocation(UmaMusumeMod.MOD_ID, "textures/gui/agnes_tachyon.png");
    }
    @Override
    protected String[] getDialogues() {
        return new String[]{"Olá! Eu sou Agnes Tachyon!",
                "Vamos correr juntos hoje?",
                "Até mais!"};
    }
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}