package com.crabmods.tameable_arachne;

import com.crabmods.tameable_arachne.client.ClientEventHandler;
import com.crabmods.tameable_arachne.entity.EntityArachne;
import com.crabmods.tameable_arachne.entity.EntityArachneMedium;
import com.crabmods.tameable_arachne.entity.EntityHarpy;
import com.crabmods.tameable_arachne.item.ItemMagicCandy;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(TameableArachneMod.MODID)
public class TameableArachneMod {
    public static final String MODID = "tameable_arachne";

    // Deferred Registers
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    // Entity Types
    public static final RegistryObject<EntityType<EntityArachne>> ARACHNE = ENTITY_TYPES.register("arachne",
            () -> EntityType.Builder.of(EntityArachne::new, MobCategory.CREATURE)
                    .sized(1.4F, 0.9F)
                    .build(ResourceLocation.fromNamespaceAndPath(MODID, "arachne").toString()));

    public static final RegistryObject<EntityType<EntityArachneMedium>> ARACHNE_MEDIUM = ENTITY_TYPES.register("arachne_medium",
            () -> EntityType.Builder.of(EntityArachneMedium::new, MobCategory.CREATURE)
                    .sized(1.6F, 1.1F)
                    .build(ResourceLocation.fromNamespaceAndPath(MODID, "arachne_medium").toString()));

    public static final RegistryObject<EntityType<EntityHarpy>> HARPY = ENTITY_TYPES.register("harpy",
            () -> EntityType.Builder.of(EntityHarpy::new, MobCategory.CREATURE)
                    .sized(0.6F, 1.8F)
                    .build(ResourceLocation.fromNamespaceAndPath(MODID, "harpy").toString()));

    // Items
    public static final RegistryObject<Item> MAGIC_CANDY = ITEMS.register("magic_candy",
            () -> new ItemMagicCandy(new Item.Properties().food(ItemMagicCandy.MAGIC_CANDY_FOOD)));

    // Spawn Eggs
    public static final RegistryObject<Item> ARACHNE_SPAWN_EGG = ITEMS.register("arachne_spawn_egg",
            () -> new ForgeSpawnEggItem(ARACHNE, 0x8B4513, 0x654321, new Item.Properties()));
    
    public static final RegistryObject<Item> ARACHNE_MEDIUM_SPAWN_EGG = ITEMS.register("arachne_medium_spawn_egg",
            () -> new ForgeSpawnEggItem(ARACHNE_MEDIUM, 0x654321, 0x8B4513, new Item.Properties()));
    
    public static final RegistryObject<Item> HARPY_SPAWN_EGG = ITEMS.register("harpy_spawn_egg",
            () -> new ForgeSpawnEggItem(HARPY, 0xFFB6C1, 0xFF69B4, new Item.Properties()));

    public TameableArachneMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register deferred registers
        ENTITY_TYPES.register(modEventBus);
        ITEMS.register(modEventBus);

        // Register event handlers
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::addCreative);
        modEventBus.addListener(this::entityAttributes);

        // Register config
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, TameableArachneConfig.SPEC);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            // Register spawn placements
            SpawnPlacements.register(ARACHNE.get(), SpawnPlacements.Type.ON_GROUND, 
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
            SpawnPlacements.register(ARACHNE_MEDIUM.get(), SpawnPlacements.Type.ON_GROUND, 
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
            SpawnPlacements.register(HARPY.get(), SpawnPlacements.Type.ON_GROUND, 
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        });
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            // Client setup is handled by ClientEventHandler
        });
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(MAGIC_CANDY);
        }
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(ARACHNE_SPAWN_EGG);
            event.accept(ARACHNE_MEDIUM_SPAWN_EGG);
            event.accept(HARPY_SPAWN_EGG);
        }
    }

    @SubscribeEvent
    public void entityAttributes(EntityAttributeCreationEvent event) {
        event.put(ARACHNE.get(), EntityArachne.createAttributes().build());
        event.put(ARACHNE_MEDIUM.get(), EntityArachneMedium.createAttributes().build());
        event.put(HARPY.get(), EntityHarpy.createAttributes().build());
    }
}

