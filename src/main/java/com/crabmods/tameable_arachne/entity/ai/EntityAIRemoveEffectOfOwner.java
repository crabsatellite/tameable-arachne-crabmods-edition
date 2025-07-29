package com.crabmods.tameable_arachne.entity.ai;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.effect.MobEffect;

public class EntityAIRemoveEffectOfOwner extends Goal {
    private final TamableAnimal thePet;
    private LivingEntity theOwner;
    private final float effectRange;
    private final MobEffect effectId;

    public EntityAIRemoveEffectOfOwner(TamableAnimal animal, float effectRange, MobEffect effect) {
        this.thePet = animal;
        this.effectRange = effectRange;
        this.effectId = effect;
        this.setFlags(java.util.EnumSet.of(Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        LivingEntity owner = this.thePet.getOwner();

        if (owner == null) {
            return false;
        } else if (this.thePet.distanceToSqr(owner) > this.effectRange * this.effectRange) {
            return false;
        } else {
            this.theOwner = owner;
            return true;
        }
    }

    @Override
    public boolean canContinueToUse() {
        return this.thePet.distanceToSqr(this.theOwner) < this.effectRange * this.effectRange;
    }

    @Override
    public void start() {
        // Nothing to do when starting
    }

    @Override
    public void stop() {
        this.theOwner = null;
    }

    @Override
    public void tick() {
        if (this.theOwner.hasEffect(this.effectId)) {
            this.theOwner.removeEffect(this.effectId);
        }
    }
}
