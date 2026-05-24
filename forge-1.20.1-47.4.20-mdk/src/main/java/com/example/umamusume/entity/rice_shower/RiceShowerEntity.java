package com.example.umamusume.entity.rice_shower;

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

public class RiceShowerEntity extends BaseUmaEntity implements GeoEntity {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public RiceShowerEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    @Override
    protected String getUmaName() {return "Rice Shower";}

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 4, state -> {
            if (state.isMoving())
                return state.setAndContinue(RawAnimation.begin().thenLoop("animation.rice_shower.run"));
            return state.setAndContinue(RawAnimation.begin().thenLoop("animation.rice_shower.idle"));
        }));
    }
    @Override
    protected ResourceLocation getGuiTexture() {
        return new ResourceLocation(UmaMusumeMod.MOD_ID, "textures/gui/rice_shower.png");
    }
    @Override
    protected String[] getDialogues() {
        return new String[]{"Olá! Eu sou Rice Shower!",
                "Vamos correr juntos hoje?",
                "Até mais!"};
    }
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}