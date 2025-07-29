package com.crabmods.tameable_arachne.item;

import com.crabmods.tameable_arachne.TameableArachneConfig;
import com.crabmods.tameable_arachne.entity.EntityArachne;
import com.crabmods.tameable_arachne.entity.EntityArachneMedium;
import com.crabmods.tameable_arachne.entity.EntityHarpy;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;

public class ItemMagicCandy extends Item {
    public static final FoodProperties MAGIC_CANDY_FOOD = new FoodProperties.Builder()
            .nutrition(2)
            .saturationMod(1.0F)
            .build();

    public ItemMagicCandy(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        if (target instanceof TamableAnimal tamable && tamable.isTame() && tamable.isOwnedBy(player)) {
            if (target instanceof EntityArachne arachne) {
                return powerUpEntity(stack, player, arachne, hand);
            } else if (target instanceof EntityArachneMedium arachneMedium) {
                return powerUpEntity(stack, player, arachneMedium, hand);
            } else if (target instanceof EntityHarpy harpy) {
                return powerUpEntity(stack, player, harpy, hand);
            }
        }
        return InteractionResult.PASS;
    }

    private InteractionResult powerUpEntity(ItemStack stack, Player player, TamableAnimal entity, InteractionHand hand) {
        Level level = entity.level();
        
        if (!level.isClientSide) {
            // Check if entity can be powered up
            int currentLevel = getPowerLevel(entity);
            if (currentLevel >= TameableArachneConfig.powerUpLimit) {
                return InteractionResult.FAIL;
            }

            // Power up the entity
            powerUp(entity);
            
            // Consume the item
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }

            // Add particles or effects here if needed
            return InteractionResult.SUCCESS;
        }
        
        return InteractionResult.CONSUME;
    }

    private int getPowerLevel(TamableAnimal entity) {
        return entity.getPersistentData().getInt("PowerLevel");
    }

    private void powerUp(TamableAnimal entity) {
        int currentLevel = getPowerLevel(entity);
        entity.getPersistentData().putInt("PowerLevel", currentLevel + 1);
        
        // Increase stats based on entity type and config
        float hpIncrease = TameableArachneConfig.hpUp;
        float attackIncrease = TameableArachneConfig.attackUp;
        
        // Apply stat increases
        entity.setHealth(entity.getHealth() + hpIncrease);
        entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH)
                .setBaseValue(entity.getAttributeValue(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH) + hpIncrease);
        
        if (entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE) != null) {
            entity.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE)
                    .setBaseValue(entity.getAttributeValue(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE) + attackIncrease);
        }
    }
}

