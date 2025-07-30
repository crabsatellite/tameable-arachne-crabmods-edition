package com.crabmods.tameable_arachne.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeSpawnEggItem;

import java.util.List;
import java.util.function.Supplier;

public class EnchantedSpawnEggItem extends ForgeSpawnEggItem {
    
    private final String creatureType;
    
    public EnchantedSpawnEggItem(Supplier<? extends EntityType<? extends Mob>> entityType, int backgroundColor, int highlightColor, Properties props, String creatureType) {
        super(entityType, backgroundColor, highlightColor, props);
        this.creatureType = creatureType;
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        // Always show enchantment glint
        return true;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        
        // Add creature-specific tooltip based on type
        switch (creatureType) {
            case "arachne":
                tooltip.add(Component.translatable("item.tameable_arachne.arachne_spawn_egg.tooltip1")
                    .withStyle(ChatFormatting.DARK_PURPLE, ChatFormatting.ITALIC));
                tooltip.add(Component.translatable("item.tameable_arachne.arachne_spawn_egg.tooltip2")
                    .withStyle(ChatFormatting.GRAY));
                break;
            case "arachne_medium":
                tooltip.add(Component.translatable("item.tameable_arachne.arachne_medium_spawn_egg.tooltip1")
                    .withStyle(ChatFormatting.DARK_RED, ChatFormatting.ITALIC));
                tooltip.add(Component.translatable("item.tameable_arachne.arachne_medium_spawn_egg.tooltip2")
                    .withStyle(ChatFormatting.GRAY));
                break;
            case "harpy":
                tooltip.add(Component.translatable("item.tameable_arachne.harpy_spawn_egg.tooltip1")
                    .withStyle(ChatFormatting.AQUA, ChatFormatting.ITALIC));
                tooltip.add(Component.translatable("item.tameable_arachne.harpy_spawn_egg.tooltip2")
                    .withStyle(ChatFormatting.GRAY));
                break;
        }
        
        tooltip.add(Component.translatable("item.tameable_arachne.spawn_egg.magical")
            .withStyle(ChatFormatting.GOLD, ChatFormatting.BOLD));
    }
}
