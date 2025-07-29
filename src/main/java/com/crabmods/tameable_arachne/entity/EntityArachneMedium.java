package com.crabmods.tameable_arachne.entity;

import com.crabmods.tameable_arachne.TameableArachneMod;
import com.crabmods.tameable_arachne.TameableArachneConfig;
import com.crabmods.tameable_arachne.entity.ai.EntityAIFollowArachneOwner;
import com.crabmods.tameable_arachne.item.ItemMagicCandy;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class EntityArachneMedium extends TamableAnimal {
    private static final EntityDataAccessor<Float> LAST_HEALTH = SynchedEntityData.defineId(EntityArachneMedium.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Byte> FLAG = SynchedEntityData.defineId(EntityArachneMedium.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Integer> TYPE = SynchedEntityData.defineId(EntityArachneMedium.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> ADD_HP = SynchedEntityData.defineId(EntityArachneMedium.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> ADD_ATTACK = SynchedEntityData.defineId(EntityArachneMedium.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> ADD_DEFENSE = SynchedEntityData.defineId(EntityArachneMedium.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> PROTECTION = SynchedEntityData.defineId(EntityArachneMedium.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> FIRE_PROTO = SynchedEntityData.defineId(EntityArachneMedium.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> FALL_PROTO = SynchedEntityData.defineId(EntityArachneMedium.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> BLAST_PROTO = SynchedEntityData.defineId(EntityArachneMedium.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> PROJECTILE_PROTO = SynchedEntityData.defineId(EntityArachneMedium.class, EntityDataSerializers.INT);

    private float interestedAngle;
    private float interestedAngleO;
    private int attackTimer;
    private int randmTimer;
    private int winkTimer;
    protected float knockbackFactor = 0.4F;

    public EntityArachneMedium(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
        this.setTame(false);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.4D)
                .add(Attributes.MAX_HEALTH, 30.0D)
                .add(Attributes.ATTACK_DAMAGE, 6.0D);
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
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(LAST_HEALTH, this.getHealth());
        this.entityData.define(FLAG, (byte) 0);
        this.entityData.define(TYPE, this.random.nextInt(8) == 0 ? 1 : 0);
        this.entityData.define(ADD_HP, 0);
        this.entityData.define(ADD_ATTACK, 0);
        this.entityData.define(ADD_DEFENSE, 0);
        this.entityData.define(PROTECTION, 0);
        this.entityData.define(FIRE_PROTO, 0);
        this.entityData.define(FALL_PROTO, 0);
        this.entityData.define(BLAST_PROTO, 0);
        this.entityData.define(PROJECTILE_PROTO, 0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("Angry", this.isAngry());
        tag.putInt("TextureNo", this.getArachneType());
        tag.putInt("AddHp", this.getAddHp());
        tag.putInt("AddAttack", this.getAddAttack());
        tag.putInt("AddDefense", this.getAddDefense());
        tag.putInt("Protection", this.getProtection());
        tag.putInt("FireProtection", this.getFireProtection());
        tag.putInt("FallProtection", this.getFallProtection());
        tag.putInt("BlastProtection", this.getBlastProtection());
        tag.putInt("ProjectileProtection", this.getProjectileProtection());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setAngry(tag.getBoolean("Angry"));
        this.setArachneType(tag.getInt("TextureNo"));
        this.setAddHp(tag.getInt("AddHp"));
        this.setAddAttack(tag.getInt("AddAttack"));
        this.setAddDefense(tag.getInt("AddDefense"));
        this.setProtection(tag.getInt("Protection"));
        this.setFireProtection(tag.getInt("FireProtection"));
        this.setFallProtection(tag.getInt("FallProtection"));
        this.setBlastProtection(tag.getInt("BlastProtection"));
        this.setProjectileProtection(tag.getInt("ProjectileProtection"));
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.PLAYER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.PLAYER_DEATH;
    }

    @Override
    protected float getSoundVolume() {
        return 0.4F;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockState) {
        this.playSound(SoundEvents.SPIDER_STEP, 0.15F, 1.0F);
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (this.attackTimer > 0) {
            --this.attackTimer;
        }

        // Auto heal functionality
        if (TameableArachneConfig.autoHeal) {
            if (this.tickCount % TameableArachneConfig.autoHealInterval == 0) {
                if (this.entityData.get(LAST_HEALTH) < this.getHpValue()) {
                    this.heal((float)TameableArachneConfig.autoHealValue);
                }
            }
        }

        // Wink animation
        if (this.winkTimer > 0) {
            --this.winkTimer;
            if (this.winkTimer == 0) {
                this.winkTimer = -1;
            }
        } else if (this.randmTimer > 0) {
            --this.randmTimer;
            if (this.randmTimer == 0) {
                this.randmTimer = -1;
                this.winkTimer = 3;
            }
        } else {
            if (this.tickCount % 120 == 0) {
                randmTimer = this.random.nextInt(75);
            }
        }

        this.entityData.set(LAST_HEALTH, this.getHealth());
    }

    @Override
    public void tick() {
        super.tick();
        
        this.interestedAngleO = this.interestedAngle;
        if (this.isAngry()) {
            this.interestedAngle += (1.0F - this.interestedAngle) * 0.4F;
        } else {
            this.interestedAngle += (0.0F - this.interestedAngle) * 0.4F;
        }
    }

    @Override
    public boolean hurt(DamageSource damageSource, float damage) {
        if (this.isInvulnerableTo(damageSource)) {
            return false;
        }

        // Apply protection
        if (damage >= 1.0F && getProtection() > 0) {
            damage = damage * ((100F - getProtection()) / 100F);
        }
        if (damageSource.is(net.minecraft.tags.DamageTypeTags.IS_FIRE) && damage >= 1.0F && getFireProtection() > 0) {
            damage = damage * ((100F - getFireProtection()) / 100F);
        }
        if (damageSource.is(net.minecraft.tags.DamageTypeTags.IS_FALL) && damage >= 1.0F && getFallProtection() > 0) {
            damage = damage * ((100F - getFallProtection()) / 100F);
        }
        if (damageSource.is(net.minecraft.tags.DamageTypeTags.IS_EXPLOSION) && damage >= 1.0F && getBlastProtection() > 0) {
            damage = damage * ((100F - getBlastProtection()) / 100F);
        }
        if (damageSource.is(net.minecraft.tags.DamageTypeTags.IS_PROJECTILE) && damage >= 1.0F && getProjectileProtection() > 0) {
            damage = damage * ((100F - getProjectileProtection()) / 100F);
        }

        if (damage < 1.0F) {
            return false;
        }

        Entity entity = damageSource.getEntity();
        if (this.isOrderedToSit()) {
            this.setOrderedToSit(false);
        }

        if (entity != null && !(entity instanceof Player)) {
            damage = (damage + 1.0F) / 2.0F;
        }

        return super.hurt(damageSource, damage);
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        this.attackTimer = 12;
        this.level().broadcastEntityEvent(this, (byte) 4);
        
        int attackValue = this.getAttackValue();
        return target.hurt(this.damageSources().mobAttack(this), attackValue);
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == 4 && this.attackTimer < 1) {
            this.attackTimer = 12;
        } else {
            super.handleEntityEvent(id);
        }
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        if (this.isTame()) {
            if (itemstack.getItem() instanceof ItemMagicCandy) {
                if (!player.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }
                
                // Apply magic candy effects
                this.setAddHp(this.getAddHp() + 1);
                this.setAddAttack(this.getAddAttack() + 1);
                this.setAddDefense(this.getAddDefense() + 1);
                
                this.heal(this.getMaxHealth());
                return InteractionResult.SUCCESS;
            }
            
            if (this.isOwnedBy(player) && !this.level().isClientSide && !this.isFood(itemstack)) {
                this.setOrderedToSit(!this.isOrderedToSit());
                this.jumping = false;
                this.navigation.stop();
                this.setTarget(null);
                return InteractionResult.SUCCESS;
            }
        } else if (itemstack.is(Items.SPIDER_EYE)) {
            if (!player.getAbilities().instabuild) {
                itemstack.shrink(1);
            }

            if (!this.level().isClientSide) {
                if (this.random.nextInt(3) == 0) {
                    this.tame(player);
                    this.navigation.stop();
                    this.setTarget(null);
                    this.setOrderedToSit(true);
                    this.level().broadcastEntityEvent(this, (byte) 7);
                } else {
                    this.level().broadcastEntityEvent(this, (byte) 6);
                }
            }

            return InteractionResult.SUCCESS;
        }

        return super.mobInteract(player, hand);
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
        return TameableArachneMod.ARACHNE_MEDIUM.get().create(level);
    }

    // Getters and setters for data
    public boolean isAngry() {
        return (this.entityData.get(FLAG) & 2) != 0;
    }

    public void setAngry(boolean angry) {
        byte b0 = this.entityData.get(FLAG);
        if (angry) {
            this.entityData.set(FLAG, (byte)(b0 | 2));
        } else {
            this.entityData.set(FLAG, (byte)(b0 & -3));
        }
    }

    public int getArachneType() {
        return this.entityData.get(TYPE);
    }

    public void setArachneType(int type) {
        this.entityData.set(TYPE, type);
    }

    public int getAddHp() {
        return this.entityData.get(ADD_HP);
    }

    public void setAddHp(int hp) {
        this.entityData.set(ADD_HP, hp);
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(this.getHpValue());
    }

    public int getAddAttack() {
        return this.entityData.get(ADD_ATTACK);
    }

    public void setAddAttack(int attack) {
        this.entityData.set(ADD_ATTACK, attack);
        this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(this.getAttackValue());
    }

    public int getAddDefense() {
        return this.entityData.get(ADD_DEFENSE);
    }

    public void setAddDefense(int defense) {
        this.entityData.set(ADD_DEFENSE, defense);
    }

    public int getProtection() {
        return this.entityData.get(PROTECTION);
    }

    public void setProtection(int protection) {
        this.entityData.set(PROTECTION, protection);
    }

    public int getFireProtection() {
        return this.entityData.get(FIRE_PROTO);
    }

    public void setFireProtection(int fireProtection) {
        this.entityData.set(FIRE_PROTO, fireProtection);
    }

    public int getFallProtection() {
        return this.entityData.get(FALL_PROTO);
    }

    public void setFallProtection(int fallProtection) {
        this.entityData.set(FALL_PROTO, fallProtection);
    }

    public int getBlastProtection() {
        return this.entityData.get(BLAST_PROTO);
    }

    public void setBlastProtection(int blastProtection) {
        this.entityData.set(BLAST_PROTO, blastProtection);
    }

    public int getProjectileProtection() {
        return this.entityData.get(PROJECTILE_PROTO);
    }

    public void setProjectileProtection(int projectileProtection) {
        this.entityData.set(PROJECTILE_PROTO, projectileProtection);
    }

    public int getHpValue() {
        int cnt = this.getAddHp();
        return TameableArachneConfig.arachneMediumBaseHp + (cnt * TameableArachneConfig.hpUp);
    }

    public int getAttackValue() {
        int cnt = this.getAddAttack();
        if (isNameBonus()) {
            cnt = cnt + TameableArachneConfig.nameBonusValue;
        }
        return TameableArachneConfig.arachneMediumBaseAttack + (cnt * TameableArachneConfig.attackUp);
    }

    public int getDefenseValue() {
        int cnt = this.getAddDefense();
        if (isNameBonus()) {
            cnt = cnt + TameableArachneConfig.nameBonusValue;
        }
        return TameableArachneConfig.arachneMediumBaseDefense + (cnt * TameableArachneConfig.defenseUp);
    }

    private boolean isNameBonus() {
        return TameableArachneConfig.nameBonus && this.hasCustomName();
    }

    public int getAttackTimer() {
        return this.attackTimer;
    }

    public void setAttackTimer(int timer) {
        this.attackTimer = timer;
    }

    public int getWinkTimer() {
        return this.winkTimer;
    }

    public float getInterestedAngle(float partialTicks) {
        return (this.interestedAngleO + (this.interestedAngle - this.interestedAngleO) * partialTicks) * 0.15F * (float)Math.PI;
    }

    @Override
    public int getMaxHeadXRot() {
        return this.isInSittingPose() ? 20 : super.getMaxHeadXRot();
    }
}

