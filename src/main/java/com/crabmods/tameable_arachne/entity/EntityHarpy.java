package com.crabmods.tameable_arachne.entity;

import com.crabmods.tameable_arachne.TameableArachneConfig;
import com.crabmods.tameable_arachne.entity.ai.EntityAIFollowArachneOwner;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;

public class EntityHarpy extends EntityArachne
{
    public float field_70886_e;
    public float destPos;
    public float field_70884_g;
    public float field_70888_h;
    public float field_70889_i = 1.0F;

    public EntityHarpy(EntityType<? extends TamableAnimal> entityType, Level level)
    {
        super(entityType, level);
        this.knockbackFactor = 0.2F; // Harpies are lighter, take more knockback
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.ATTACK_DAMAGE, 2.0D)
                .add(Attributes.FLYING_SPEED, 0.4D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.3F));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(5, new EntityAIFollowArachneOwner(this, 1.0D, 10.0F, 2.0F));
        this.goalSelector.addGoal(6, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new RandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
    }

    @Override
    protected void playStepSound(BlockPos pos, net.minecraft.world.level.block.state.BlockState blockState)
    {
        SoundType soundtype = blockState.getSoundType(level(), pos, this);

        if (this.level().getBlockState(pos.above()).getBlock() == Blocks.SNOW)
        {
            soundtype = Blocks.SNOW.getSoundType(blockState, level(), pos, this);
            this.playSound(soundtype.getStepSound(), soundtype.getVolume() * 0.15F, soundtype.getPitch());
        }
        else if (!blockState.liquid())
        {
            this.playSound(soundtype.getStepSound(), soundtype.getVolume() * 0.15F, soundtype.getPitch());
        }
    }

    @Override
    protected void defineSynchedData()
    {
        super.defineSynchedData();
        this.entityData.set(EntityArachne.TYPE, this.random.nextInt(14));
    }

    @Override
    protected void magicCandyThing()
    {
        int texture = this.getTextureNo() + 1;
        this.setTextureNo(texture > 13 ? 0 : texture);
    }

    @Override
    public boolean doHurtTarget(Entity target)
    {
        try
        {
            if (this.random.nextInt(100) < TameableArachneConfig.harpyDropRate)
            {
                ItemStack dropItem = this.getEntityDropItem(target);

                if (dropItem != null)
                {
                    target.spawnAtLocation(dropItem, target.getBbHeight() / 2F);
                }
            }
        }
        catch (Exception e)
        {}

        float damage = (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
        return target.hurt(this.damageSources().mobAttack(this), damage);
    }

    private ItemStack getEntityDropItem(Entity entity)
    {
        ItemStack itemStack = null;

        if (entity instanceof LivingEntity)
        {
            if (entity instanceof Chicken)
            {
                itemStack = new ItemStack(Items.FEATHER, 1);
            }
            else if (entity instanceof Cow)
            {
                itemStack = new ItemStack(Items.LEATHER, 1);
            }
            else if (entity instanceof Horse)
            {
                itemStack = new ItemStack(Items.LEATHER, 1);
            }
            else if (entity instanceof Pig)
            {
                itemStack = new ItemStack(entity.isOnFire() ? Items.COOKED_PORKCHOP : Items.PORKCHOP, 1);
            }
            else if (entity instanceof Sheep sheep)
            {
                itemStack = new ItemStack(Items.WHITE_WOOL, 1);
            }
            else if (entity instanceof Wolf)
            {
                itemStack = new ItemStack(Items.LEATHER, 1);
            }
            else if (entity instanceof SnowGolem)
            {
                itemStack = new ItemStack(Items.SNOWBALL, 1);
            }
            else if (entity instanceof Squid)
            {
                itemStack = new ItemStack(Items.INK_SAC, 1);
            }
            else if (entity instanceof Blaze)
            {
                itemStack = new ItemStack(Items.BLAZE_ROD, 1);
            }
            else if (entity instanceof Creeper)
            {
                itemStack = new ItemStack(Items.GUNPOWDER, 1);
            }
            else if (entity instanceof EnderMan)
            {
                itemStack = new ItemStack(Items.ENDER_PEARL, 1);
            }
            else if (entity instanceof Skeleton)
            {
                itemStack = new ItemStack(Items.BONE, 1);
            }
            else if (entity instanceof Spider)
            {
                itemStack = new ItemStack(Items.STRING, 1);
            }
            else if (entity instanceof Zombie)
            {
                itemStack = new ItemStack(Items.ROTTEN_FLESH, 1);
            }
            else if (entity instanceof Ghast)
            {
                itemStack = new ItemStack(Items.GUNPOWDER, 1);
            }
            else if (entity instanceof Slime)
            {
                itemStack = new ItemStack(Items.SLIME_BALL, 1);
            }
            else if (entity instanceof EntityArachne)
            {
                itemStack = new ItemStack(Items.STRING, 1);
            }
            else if (entity instanceof EntityArachneMedium)
            {
                itemStack = new ItemStack(Items.STRING, 1);
            }
            else if (entity instanceof EntityHarpy)
            {
                itemStack = new ItemStack(Items.FEATHER, 1);
            }
            else
            {
                itemStack = null;
            }
        }
        return itemStack;
    }

    @Override
    public void tick() {
        super.tick();
        
        // Slow falling like a bird when not on ground
        if (!this.onGround() && this.getDeltaMovement().y < 0) {
            // Reduce falling speed by 30% when flapping wings
            this.setDeltaMovement(this.getDeltaMovement().multiply(1.0, 0.7, 1.0));
        }
    }

    @Override
    public boolean causeFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource)
    {
        return false; // Harpies don't take fall damage
    }

    @Override
    public int getMaxSpawnClusterSize()
    {
        return 8;
    }

    @Override
    public boolean canMate(Animal otherAnimal)
    {
        if (otherAnimal == this)
        {
            return false;
        }
        else if (!this.isTame())
        {
            return false;
        }
        else if (!(otherAnimal instanceof EntityHarpy))
        {
            return false;
        }
        else
        {
            EntityHarpy entityHarpy = (EntityHarpy) otherAnimal;
            return !entityHarpy.isTame() ? false : (entityHarpy.isOrderedToSit() ? false : this.isInLove() && entityHarpy.isInLove());
        }
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer)
    {
        return !this.isTame() && this.tickCount > 24000;
    }

    @Override
    public boolean wantsToAttack(LivingEntity target, LivingEntity owner)
    {
        if (!(target instanceof Creeper) && !(target instanceof Ghast))
        {
            if (target instanceof EntityHarpy entityHarpy)
            {
                if (entityHarpy.isTame() && entityHarpy.getOwner() == owner)
                {
                    return false;
                }
            }

            return target instanceof Player && owner instanceof Player && !((Player) owner).canHarmPlayer((Player) target) ? false : !(target instanceof Horse) || !((Horse) target).isTamed();
        }
        else
        {
            return false;
        }
    }

    @Override
    public int getHpValue()
    {
        int cnt = this.getAddHp();
        return TameableArachneConfig.harpyBaseHp + (cnt * TameableArachneConfig.hpUp);
    }

    @Override
    public int getAttackValue()
    {
        int cnt = this.getAddAttack();
        if (isNameBonus())
        {
            cnt = cnt + TameableArachneConfig.nameBonusValue;
        }
        return TameableArachneConfig.harpyBaseAttack + (cnt * TameableArachneConfig.attackUp);
    }

    @Override
    public int getDefenseValue()
    {
        int cnt = this.getAddDefense();
        if (isNameBonus())
        {
            cnt = cnt + TameableArachneConfig.nameBonusValue;
        }
        return TameableArachneConfig.harpyBaseDefense + (cnt * TameableArachneConfig.defenseUp);
    }

    private boolean isNameBonus()
    {
        boolean ret = false;
        if (TameableArachneConfig.nameBonus)
        {
            try
            {
                String customName = this.getCustomName() != null ? this.getCustomName().getString() : null;
                if (customName != null && !customName.equals(""))
                {
                    ret = true;
                }
            }
            catch (Exception e)
            {}
        }
        return ret;
    }
}