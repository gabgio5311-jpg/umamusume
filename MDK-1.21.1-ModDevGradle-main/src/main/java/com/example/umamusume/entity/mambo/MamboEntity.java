package com.example.umamusume.entity.mambo;

import com.example.umamusume.entity.BaseUmaEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

// Faltavam essas três importações:
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;

import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;
import com.example.umamusume.ModSounds;

public class MamboEntity extends BaseUmaEntity implements GeoEntity {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public MamboEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    @Override
    protected String getUmaName() {
        return "Mambo";
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 4, state -> {
            if (state.isMoving())
                return state.setAndContinue(RawAnimation.begin().thenLoop("mambo.animation.run"));
            return state.setAndContinue(RawAnimation.begin().thenLoop("mambo.animation.idle"));
        }));
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        // Verifica se está no servidor e se é a mão principal (para não tocar o som duas vezes por clique)
        if (!this.level().isClientSide && hand == InteractionHand.MAIN_HAND) {
            // Toca o som do mambo
            this.playSound(ModSounds.MAMBO.get(), 1.0F, 1.0F);
        }

        // Isso aqui é a mágica! Chama o mobInteract da BaseUmaEntity
        // Assim, o sistema de seguir com Shift + Clique continua funcionando perfeitamente!
        return super.mobInteract(player, hand);
    } // <--- Faltava essa chave aqui para fechar o método!

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}