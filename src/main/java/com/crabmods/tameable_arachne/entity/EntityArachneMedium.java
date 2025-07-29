package com.crabmods.tameable_arachne.entity;

import com.crabmods.tameable_arachne.TameableArachneConfig;
import com.crabmods.tameable_arachne.entity.ai.EntityAIFollowArachneOwner;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class EntityArachneMedium extends EntityArachne {

    public EntityArachneMedium(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
        // Size exactly like 1.12.2: 1.2F width, 1.8F height
        this.knockbackFactor = 0.4F; // Reduced knockback compared to regular Arachne
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.4D) // Faster than regular Arachne (0.35D)
                .add(Attributes.MAX_HEALTH, 30.0D) // Higher base health
                .add(Attributes.ATTACK_DAMAGE, 6.0D); // Higher base attack
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(5, new EntityAIFollowArachneOwner(this, 1.0D, 10.0F, 2.0F));
        this.goalSelector.addGoal(6, new RandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
    }

    @Override
    public void setTarget(LivingEntity target) {
        super.setTarget(target);

        // Anger management exactly like 1.12.2
        if (target == null) {
            this.setAngry(false);
        } else if (!this.isTame()) {
            this.setAngry(true);
        }
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return 4; // Smaller spawn clusters than regular Arachne
    }

    @Override
    public boolean canMate(Animal otherAnimal) {
        if (otherAnimal == this) {
            return false;
        } else if (!this.isTame()) {
            return false;
        } else if (!(otherAnimal instanceof EntityArachneMedium)) {
            return false;
        } else {
            EntityArachneMedium arachne = (EntityArachneMedium) otherAnimal;
            return !arachne.isTame() ? false : (arachne.isInSittingPose() ? false : this.isInLove() && arachne.isInLove());
        }
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return null; // No breeding offspring
    }

    // Override stat calculations for Medium variant - exactly like 1.12.2
    @Override
    public int getHpValue() {
        int cnt = this.getAddHp();
        return TameableArachneConfig.arachneMediumBaseHp + (cnt * TameableArachneConfig.hpUp);
    }

    @Override
    public int getAttackValue() {
        int cnt = this.getAddAttack();
        if (isNameBonus()) {
            cnt = cnt + TameableArachneConfig.nameBonusValue;
        }
        return TameableArachneConfig.arachneMediumBaseAttack + (cnt * TameableArachneConfig.attackUp);
    }

    @Override
    public int getDefenseValue() {
        int cnt = this.getAddDefense();
        if (isNameBonus()) {
            cnt = cnt + TameableArachneConfig.nameBonusValue;
        }
        return TameableArachneConfig.arachneMediumBaseDefense + (cnt * TameableArachneConfig.defenseUp);
    }

    private boolean isNameBonus() {
        boolean ret = false;
        if (TameableArachneConfig.nameBonus) {
            try {
                String customName = this.getName().getString();
                if (customName != null && !customName.equals("")) {
                    ret = true;
                }
            } catch (Exception e) {}
        }
        return ret;
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        // Size exactly like 1.12.2: 1.2F width, 1.8F height
        return EntityDimensions.scalable(1.2F, 1.8F);
    }
}

