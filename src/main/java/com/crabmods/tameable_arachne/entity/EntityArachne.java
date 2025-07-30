package com.crabmods.tameable_arachne.entity;

import java.text.NumberFormat;

import com.crabmods.tameable_arachne.TameableArachneConfig;
import com.crabmods.tameable_arachne.entity.ai.EntityAIFollowArachneOwner;
import com.crabmods.tameable_arachne.item.food.ItemMagicCandy;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
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
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class EntityArachne extends TamableAnimal {
    private static final EntityDataAccessor<Float> LAST_HEALTH = SynchedEntityData.defineId(EntityArachne.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Byte> FLAG = SynchedEntityData.defineId(EntityArachne.class, EntityDataSerializers.BYTE);
    public static final EntityDataAccessor<Integer> TYPE = SynchedEntityData.defineId(EntityArachne.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> ADD_HP = SynchedEntityData.defineId(EntityArachne.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> ADD_ATTACK = SynchedEntityData.defineId(EntityArachne.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> ADD_DEFENSE = SynchedEntityData.defineId(EntityArachne.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> PROTECTION = SynchedEntityData.defineId(EntityArachne.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> FIRE_PROTO = SynchedEntityData.defineId(EntityArachne.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> FALL_PROTO = SynchedEntityData.defineId(EntityArachne.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> BLAST_PROTO = SynchedEntityData.defineId(EntityArachne.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> PROJECTILE_PROTO = SynchedEntityData.defineId(EntityArachne.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Byte> TAMED = SynchedEntityData.defineId(EntityArachne.class, EntityDataSerializers.BYTE);

    public float interestedAngle;
    public float interestedAngleO;
    private int attackTimer;
    private int randmTimer;
    private int winkTimer;
    protected float knockbackFactor = 0.6F;

    public EntityArachne(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
        this.setTame(false);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.35D)
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.ATTACK_DAMAGE, 4.0D);
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
        this.entityData.define(TAMED, (byte) 0);
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

        // Wink animation - exactly like 1.12.2
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
        
        // Interested angle animation - exactly like 1.12.2
        this.interestedAngleO = this.interestedAngle;
        if (this.isAngry()) {
            this.interestedAngle += (1.0F - this.interestedAngle) * 0.4F;
        } else {
            this.interestedAngle += (0.0F - this.interestedAngle) * 0.4F;
        }
    }

    @Override
    public float getEyeHeight(Pose pose) {
        return this.getBbHeight() * 0.9F;
    }

    @OnlyIn(Dist.CLIENT)
    public float getInterestedAngle(float partialTick) {
        return (this.interestedAngleO + (this.interestedAngle - this.interestedAngleO) * partialTick) * 0.15F * (float) Math.PI;
    }

    @Override
    public int getMaxHeadXRot() {
        return this.isInSittingPose() ? 20 : super.getMaxHeadXRot();
    }

    @Override
    public boolean hurt(DamageSource damageSource, float damage) {
        if (this.isInvulnerableTo(damageSource)) {
            return false;
        }

        // Apply protection - exactly like 1.12.2
        if (damage >= 1.0F && getProtection() > 0) {
            damage = damage * ((100F - getProtection()) / 100F);
        }
        if (damageSource.is(DamageTypeTags.IS_FIRE) && damage >= 1.0F && getFireProtection() > 0) {
            damage = damage * ((100F - getFireProtection()) / 100F);
        }
        if (damageSource.is(DamageTypeTags.IS_FALL) && damage >= 1.0F && getFallProtection() > 0) {
            damage = damage * ((100F - getFallProtection()) / 100F);
        }
        if (damageSource.is(DamageTypeTags.IS_EXPLOSION) && damage >= 1.0F && getBlastProtection() > 0) {
            damage = damage * ((100F - getBlastProtection()) / 100F);
        }
        if (damageSource.is(DamageTypeTags.IS_PROJECTILE) && damage >= 1.0F && getProjectileProtection() > 0) {
            damage = damage * ((100F - getProjectileProtection()) / 100F);
        }

        if (damage < 1.0F) {
            return false;
        }

        Entity entity = damageSource.getEntity();
        if (this.isOrderedToSit()) {
            this.setOrderedToSit(false);
        }

        if (entity != null && !(entity instanceof Player) && !(entity instanceof AbstractArrow)) {
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

        // Magic Candy interaction - exactly like 1.12.2
        if (!itemstack.isEmpty() && itemstack.getItem() instanceof ItemMagicCandy) {
            if (!this.isTame() || (this.isTame() && this.isOwnedBy(player))) {
                if (!player.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }

                if (!this.level().isClientSide) {
                    this.magicCandyThing();
                }

                return InteractionResult.SUCCESS;
            }
        }

        if (this.isTame()) {
            // Milk bucket interaction
            if (!itemstack.isEmpty() && itemstack.is(Items.BUCKET) && this.isOwnedBy(player)) {
                itemstack.shrink(1);
                if (itemstack.getCount() == 1) {
                    player.getInventory().setItem(player.getInventory().selected, new ItemStack(Items.MILK_BUCKET));
                } else if (!player.getInventory().add(new ItemStack(Items.MILK_BUCKET))) {
                    player.drop(new ItemStack(Items.MILK_BUCKET), false);
                }
                return InteractionResult.SUCCESS;
            }

            // Power-up items - exactly like 1.12.2
            if (this.isOwnedBy(player) && this.canPowerUp()) {
                if (itemstack.is(Items.GOLD_INGOT)) {
                    if (!player.getAbilities().instabuild) {
                        itemstack.shrink(1);
                    }
                    this.setAddHp(this.getAddHp() + 1);
                    this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(this.getHpValue());
                    return InteractionResult.SUCCESS;
                }

                if (itemstack.is(Items.DIAMOND)) {
                    if (!player.getAbilities().instabuild) {
                        itemstack.shrink(1);
                    }
                    this.setAddAttack(this.getAddAttack() + 1);
                    return InteractionResult.SUCCESS;
                }

                if (itemstack.is(Items.IRON_INGOT)) {
                    if (!player.getAbilities().instabuild) {
                        itemstack.shrink(1);
                    }
                    this.setAddDefense(this.getAddDefense() + 1);
                    return InteractionResult.SUCCESS;
                }
            }

            // Stat reduction blocks
            if (this.isOwnedBy(player)) {
                if (this.getAddHp() > 0 && itemstack.is(Items.GOLD_BLOCK)) {
                    this.setAddHp(this.getAddHp() - 1);
                    this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(this.getHpValue());
                    this.heal(0F);
                    return InteractionResult.SUCCESS;
                }

                if (this.getAddAttack() > 0 && itemstack.is(Items.DIAMOND_BLOCK)) {
                    this.setAddAttack(this.getAddAttack() - 1);
                    return InteractionResult.SUCCESS;
                }

                if (this.getAddDefense() > 0 && itemstack.is(Items.IRON_BLOCK)) {
                    this.setAddDefense(this.getAddDefense() - 1);
                    return InteractionResult.SUCCESS;
                }
            }

            // Enchanted item protection transfer - exactly like 1.12.2
            if (this.isOwnedBy(player) && itemstack.isEnchanted()) {
                boolean update = false;

                // Check for protection enchantments
                if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.ALL_DAMAGE_PROTECTION, itemstack) > 0) {
                    int level = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.ALL_DAMAGE_PROTECTION, itemstack);
                    if (canUpFireProtection()) {
                        int temp = getFireProtection() + (2 * level);
                        if (temp > TameableArachneConfig.fireProtectionLimit) {
                            temp = TameableArachneConfig.fireProtectionLimit;
                        }
                        setFireProtection(temp);
                        update = true;
                    }
                    // Apply to all protection types for "Protection" enchantment
                    if (canUpFallProtection()) {
                        int temp = getFallProtection() + (2 * level);
                        if (temp > TameableArachneConfig.fallProtectionLimit) {
                            temp = TameableArachneConfig.fallProtectionLimit;
                        }
                        setFallProtection(temp);
                        update = true;
                    }
                    if (canUpBlastProtection()) {
                        int temp = getBlastProtection() + (2 * level);
                        if (temp > TameableArachneConfig.blastProtectionLimit) {
                            temp = TameableArachneConfig.blastProtectionLimit;
                        }
                        setBlastProtection(temp);
                        update = true;
                    }
                    if (canUpProjectileProtection()) {
                        int temp = getProjectileProtection() + (2 * level);
                        if (temp > TameableArachneConfig.projectileProtectionLimit) {
                            temp = TameableArachneConfig.projectileProtectionLimit;
                        }
                        setProjectileProtection(temp);
                        update = true;
                    }
                }

                // Individual protection enchantments
                if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FIRE_PROTECTION, itemstack) > 0 && canUpFireProtection()) {
                    int level = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FIRE_PROTECTION, itemstack);
                    int temp = getFireProtection() + (10 * level);
                    if (temp > TameableArachneConfig.fireProtectionLimit) {
                        temp = TameableArachneConfig.fireProtectionLimit;
                    }
                    setFireProtection(temp);
                    update = true;
                }

                if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FALL_PROTECTION, itemstack) > 0 && canUpFallProtection()) {
                    int level = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FALL_PROTECTION, itemstack);
                    int temp = getFallProtection() + (10 * level);
                    if (temp > TameableArachneConfig.fallProtectionLimit) {
                        temp = TameableArachneConfig.fallProtectionLimit;
                    }
                    setFallProtection(temp);
                    update = true;
                }

                if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLAST_PROTECTION, itemstack) > 0 && canUpBlastProtection()) {
                    int level = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLAST_PROTECTION, itemstack);
                    int temp = getBlastProtection() + (10 * level);
                    if (temp > TameableArachneConfig.blastProtectionLimit) {
                        temp = TameableArachneConfig.blastProtectionLimit;
                    }
                    setBlastProtection(temp);
                    update = true;
                }

                if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PROJECTILE_PROTECTION, itemstack) > 0 && canUpProjectileProtection()) {
                    int level = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PROJECTILE_PROTECTION, itemstack);
                    int temp = getProjectileProtection() + (10 * level);
                    if (temp > TameableArachneConfig.projectileProtectionLimit) {
                        temp = TameableArachneConfig.projectileProtectionLimit;
                    }
                    setProjectileProtection(temp);
                    update = true;
                }

                if (update) {
                    if (!player.getAbilities().instabuild) {
                        itemstack.removeTagKey("Enchantments");
                    }
                    return InteractionResult.SUCCESS;
                }
            }

            // Book info display - exactly like 1.12.2
            if (itemstack.is(Items.BOOK)) {
                if (this.level().isClientSide) {
                    NumberFormat format = NumberFormat.getInstance();
                    format.setMaximumFractionDigits(1);
                    float hp = this.entityData.get(LAST_HEALTH) / 2F;
                    float maxHp = getHpValue() / 2F;
                    float attack = getAttackValue() / 2F;
                    float defense = getDefenseValue() / 2F;
                    int powerups = this.getAddHp() + this.getAddAttack() + this.getAddDefense();

                    player.sendSystemMessage(Component.literal("HP:" + format.format(hp) + "/" + format.format(maxHp) + " Attack:" + format.format(attack) + " Defense:" + format.format(defense) + " PowerUp:" + powerups));
                    player.sendSystemMessage(Component.literal("Protections Fire:" + getFireProtection() + " Fall:" + getFallProtection() + " Blast:" + getBlastProtection() + " Projectile:" + getProjectileProtection()));
                }
                return InteractionResult.SUCCESS;
            }

            // Chicken healing - exactly like 1.12.2
            if (itemstack.is(Items.CHICKEN)) {
                if (this.entityData.get(LAST_HEALTH) < this.getHpValue()) {
                    if (!player.getAbilities().instabuild) {
                        itemstack.shrink(1);
                    }
                    float healValue = this.getHpValue() / 2;
                    this.heal(healValue);
                    return InteractionResult.SUCCESS;
                }
            }

            // Sit/stand toggle
            if (this.isOwnedBy(player) && !this.level().isClientSide && !this.isFood(itemstack)) {
                this.setOrderedToSit(!this.isOrderedToSit());
                this.jumping = false;
                this.navigation.stop();
                this.setTarget(null);
                return InteractionResult.SUCCESS;
            }
        } else if (!itemstack.isEmpty() && itemstack.is(Items.CHICKEN) && !this.isAngry()) {
            // Taming with chicken - exactly like 1.12.2
            if (!player.getAbilities().instabuild) {
                itemstack.shrink(1);
            }

            if (!this.level().isClientSide) {
                if (this.random.nextInt(2) == 0) {
                    this.tame(player);
                    this.navigation.stop();
                    this.setTarget(null);
                    this.setOrderedToSit(true);
                    this.setHealth(this.getHpValue());
                    this.level().broadcastEntityEvent(this, (byte) 7);
                } else {
                    this.level().broadcastEntityEvent(this, (byte) 6);
                }
            }

            return InteractionResult.SUCCESS;
        }

        return super.mobInteract(player, hand);
    }

    protected void magicCandyThing() {
        this.setArachneType(this.getArachneType() == 0 ? 1 : 0);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return false;
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return 8;
    }

    public boolean isAngry() {
        return (this.entityData.get(TAMED) & 2) != 0;
    }

    public void setAngry(boolean angry) {
        byte b0 = this.entityData.get(TAMED);
        if (angry) {
            this.entityData.set(TAMED, (byte) (b0 | 2));
        } else {
            this.entityData.set(TAMED, (byte) (b0 & -3));
        }
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return null;
    }

    @Override
    public boolean canMate(Animal otherAnimal) {
        if (otherAnimal == this) {
            return false;
        } else if (!this.isTame()) {
            return false;
        } else if (!(otherAnimal instanceof EntityArachne)) {
            return false;
        } else {
            EntityArachne arachne = (EntityArachne) otherAnimal;
            return !arachne.isTame() ? false : (arachne.isInSittingPose() ? false : this.isInLove() && arachne.isInLove());
        }
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return !this.isTame() && this.tickCount > 24000;
    }

    public boolean wantsToAttack(LivingEntity target, LivingEntity owner) {
        if (!(target instanceof Creeper) && !(target instanceof Ghast)) {
            if (target instanceof EntityArachne) {
                EntityArachne entityarachne = (EntityArachne) target;
                if (entityarachne.isTame() && entityarachne.getOwner() == owner) {
                    return false;
                }
            }
            return target instanceof Player && owner instanceof Player && !((Player) owner).canHarmPlayer((Player) target) ? false : true;
        } else {
            return false;
        }
    }

    @Override
    public void makeStuckInBlock(BlockState state, Vec3 motionMultiplier) {
        // Immune to webs like spiders
    }

    @Override
    public void knockback(double strength, double x, double z) {
        if (this.random.nextDouble() >= this.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE)) {
            this.hasImpulse = true;
            Vec3 vec3 = this.getDeltaMovement();
            Vec3 vec31 = (new Vec3(x, 0.0D, z)).normalize().scale(strength * this.knockbackFactor);
            this.setDeltaMovement(vec3.x / 2.0D - vec31.x, this.onGround() ? Math.min(0.4D, vec3.y / 2.0D + strength) : vec3.y, vec3.z / 2.0D - vec31.z);
        }
    }

    // Getters and setters - exactly like 1.12.2
    public int getArachneType() {
        return this.entityData.get(TYPE);
    }

    public void setArachneType(int type) {
        this.entityData.set(TYPE, type);
    }

    // 1.12.2 compatibility methods
    public int getTextureNo() {
        return this.getArachneType();
    }

    public void setTextureNo(int type) {
        this.setArachneType(type);
    }

    // 1.12.2 compatibility - func_70922_bv is "isAngry" check
    public boolean func_70922_bv() {
        return this.isAngry();
    }

    public int getAddHp() {
        return this.entityData.get(ADD_HP);
    }

    public void setAddHp(int par1) {
        this.entityData.set(ADD_HP, par1);
    }

    public int getAddAttack() {
        return this.entityData.get(ADD_ATTACK);
    }

    public void setAddAttack(int par1) {
        this.entityData.set(ADD_ATTACK, par1);
    }

    public int getAddDefense() {
        return this.entityData.get(ADD_DEFENSE);
    }

    public void setAddDefense(int par1) {
        this.entityData.set(ADD_DEFENSE, par1);
    }

    public int getHpValue() {
        int cnt = this.getAddHp();
        return TameableArachneConfig.arachneBaseHp + (cnt * TameableArachneConfig.hpUp);
    }

    public int getAttackValue() {
        int cnt = this.getAddAttack();
        if (isNameBonus()) {
            cnt = cnt + TameableArachneConfig.nameBonusValue;
        }
        return TameableArachneConfig.arachneBaseAttack + (cnt * TameableArachneConfig.attackUp);
    }

    public int getDefenseValue() {
        int cnt = this.getAddDefense();
        if (isNameBonus()) {
            cnt = cnt + TameableArachneConfig.nameBonusValue;
        }
        return TameableArachneConfig.arachneBaseDefense + (cnt * TameableArachneConfig.defenseUp);
    }

    public int getAttackTimer() {
        return this.attackTimer;
    }

    public int getWinkTimer() {
        return this.winkTimer;
    }

    @Override
    public int getArmorValue() {
        return this.getDefenseValue();
    }

    private boolean canPowerUp() {
        int totalValue = this.getAddHp() + this.getAddAttack() + this.getAddDefense();
        return totalValue < TameableArachneConfig.powerUpLimit;
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

    public int getProtection() {
        return this.entityData.get(PROTECTION);
    }

    public void setProtection(int par1) {
        this.entityData.set(PROTECTION, par1);
    }

    public int getFireProtection() {
        return this.entityData.get(FIRE_PROTO);
    }

    public void setFireProtection(int par1) {
        this.entityData.set(FIRE_PROTO, par1);
    }

    public int getFallProtection() {
        return this.entityData.get(FALL_PROTO);
    }

    public void setFallProtection(int par1) {
        this.entityData.set(FALL_PROTO, par1);
    }

    public int getBlastProtection() {
        return this.entityData.get(BLAST_PROTO);
    }

    public void setBlastProtection(int par1) {
        this.entityData.set(BLAST_PROTO, par1);
    }

    public int getProjectileProtection() {
        return this.entityData.get(PROJECTILE_PROTO);
    }

    public void setProjectileProtection(int par1) {
        this.entityData.set(PROJECTILE_PROTO, par1);
    }

    private boolean canUpProtection() {
        return getProtection() < TameableArachneConfig.protectionLimit;
    }

    private boolean canUpFireProtection() {
        return getFireProtection() < TameableArachneConfig.fireProtectionLimit;
    }

    private boolean canUpFallProtection() {
        return getFallProtection() < TameableArachneConfig.fallProtectionLimit;
    }

    private boolean canUpBlastProtection() {
        return getBlastProtection() < TameableArachneConfig.blastProtectionLimit;
    }

    private boolean canUpProjectileProtection() {
        return getProjectileProtection() < TameableArachneConfig.projectileProtectionLimit;
    }
}

