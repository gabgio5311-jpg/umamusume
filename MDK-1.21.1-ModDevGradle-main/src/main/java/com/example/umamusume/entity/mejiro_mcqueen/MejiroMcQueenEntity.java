package com.example.umamusume.entity.mejiro_mcqueen;

import com.example.umamusume.entity.BaseUmaEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

public class MejiroMcQueenEntity extends BaseUmaEntity implements GeoEntity {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public MejiroMcQueenEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    @Override
    protected String getUmaName() {
        return "Mejiro McQueen";
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 4, state -> {
            if (state.isMoving())
                return state.setAndContinue(RawAnimation.begin().thenLoop("mejiro_mcqueen.animation.run"));
            return state.setAndContinue(RawAnimation.begin().thenLoop("mejiro_mcqueen.animation.idle"));
        }));
    }
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}