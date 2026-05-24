package com.example.umamusume.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;

public abstract class BaseUmaEntity extends PathfinderMob {

    private boolean following = false;

    protected BaseUmaEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.MOVEMENT_SPEED, 0.38);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 8.0f));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 0.6));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (this.level().isClientSide) return InteractionResult.SUCCESS;
        if (player.isShiftKeyDown()) {
            following = !following;
            if (following) {
                player.sendSystemMessage(Component.literal(getUmaName() + " está te seguindo!"));
            } else {
                player.sendSystemMessage(Component.literal(getUmaName() + " parou de te seguir."));
            }
            return InteractionResult.SUCCESS;
        }
        return super.mobInteract(player, hand);
    }

    @Override
    public void tick() {
        super.tick();
        if (following && !this.level().isClientSide) {
            Player nearest = this.level().getNearestPlayer(this, 32);
            if (nearest != null) {
                double dist = this.distanceTo(nearest);
                if (dist > 3.0) {
                    this.getNavigation().moveTo(nearest, 1.0);
                } else {
                    this.getNavigation().stop();
                }
            }
        }
    }

    // Cada Uma sobrescreve esse método com seu nome
    protected abstract String getUmaName();
}
