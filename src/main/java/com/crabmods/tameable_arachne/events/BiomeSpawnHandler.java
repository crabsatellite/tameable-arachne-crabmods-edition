package com.crabmods.tameable_arachne.events;

import com.crabmods.tameable_arachne.TameableArachneMod;
import com.crabmods.tameable_arachne.TameableArachneConfig;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

public class BiomeSpawnHandler {

    public static final ResourceKey<BiomeModifier> ADD_ARACHNE_SPAWNS = ResourceKey.create(
            ForgeRegistries.Keys.BIOME_MODIFIERS, 
            ResourceLocation.fromNamespaceAndPath(TameableArachneMod.MODID, "add_arachne_spawns"));

    public static final ResourceKey<BiomeModifier> ADD_ARACHNE_MEDIUM_SPAWNS = ResourceKey.create(
            ForgeRegistries.Keys.BIOME_MODIFIERS, 
            ResourceLocation.fromNamespaceAndPath(TameableArachneMod.MODID, "add_arachne_medium_spawns"));

    public static final ResourceKey<BiomeModifier> ADD_HARPY_SPAWNS = ResourceKey.create(
            ForgeRegistries.Keys.BIOME_MODIFIERS, 
            ResourceLocation.fromNamespaceAndPath(TameableArachneMod.MODID, "add_harpy_spawns"));

    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        // Add Arachne spawns to forest biomes
        context.register(ADD_ARACHNE_SPAWNS, new ForgeBiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_FOREST),
                java.util.List.of(new MobSpawnSettings.SpawnerData(TameableArachneMod.ARACHNE.get(), 35, 1, 4))
        ));

        // Add Arachne Medium spawns to forest biomes  
        context.register(ADD_ARACHNE_MEDIUM_SPAWNS, new ForgeBiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_FOREST),
                java.util.List.of(new MobSpawnSettings.SpawnerData(TameableArachneMod.ARACHNE_MEDIUM.get(), 25, 1, 3))
        ));

        // Add Harpy spawns to forest biomes
        context.register(ADD_HARPY_SPAWNS, new ForgeBiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_FOREST),
                java.util.List.of(new MobSpawnSettings.SpawnerData(TameableArachneMod.HARPY.get(), 30, 1, 3))
        ));
    }
}
