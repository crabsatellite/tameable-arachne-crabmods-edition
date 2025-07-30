package com.crabmods.tameable_arachne;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TameableArachneSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, TameableArachneMod.MODID);

    // Arachne sounds
    public static final RegistryObject<SoundEvent> ARACHNE_ATTACK = registerSound("entity.arachne.attack");
    public static final RegistryObject<SoundEvent> ARACHNE_HURT = registerSound("entity.arachne.hurt");

    // Arachne Medium sounds  
    public static final RegistryObject<SoundEvent> ARACHNE_MEDIUM_ATTACK = registerSound("entity.arachne_medium.attack");
    public static final RegistryObject<SoundEvent> ARACHNE_MEDIUM_HURT = registerSound("entity.arachne_medium.hurt");

    // Shared sounds
    public static final RegistryObject<SoundEvent> SHARED_GREETINGS = registerSound("entity.shared.greetings");

    private static RegistryObject<SoundEvent> registerSound(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(TameableArachneMod.MODID, name)));
    }
}
