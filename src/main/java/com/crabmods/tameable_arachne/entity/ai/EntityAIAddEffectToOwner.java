package com.crabmods.tameable_arachne.entity.ai;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.level.Level;

public class EntityAIAddEffectToOwner extends Goal {
    private final TamableAnimal thePet;
    private LivingEntity theOwner;
    private final Level theWorld;
    private final float effectRange;
    private final MobEffect effectId;
    private final int effectTime;
    private final int effectLevel;

    public EntityAIAddEffectToOwner(TamableAnimal animal, float range, MobEffect effect, int duration, int amplifier) {
        this.thePet = animal;
        this.theWorld = animal.level();
        this.effectRange = range;
        this.effectId = effect;
        this.effectTime = duration;
        this.effectLevel = amplifier;
        this.setFlags(java.util.EnumSet.of(Goal.Flag.MOVE));
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
        // Do nothing on start
    }

    @Override
    public void stop() {
        this.theOwner = null;
    }

    @Override
    public void tick() {
        this.theOwner.addEffect(new MobEffectInstance(this.effectId, this.effectTime, this.effectLevel));
    }
}

