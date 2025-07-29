package com.crabmods.tameable_arachne.client;

import com.crabmods.tameable_arachne.TameableArachneMod;
import com.crabmods.tameable_arachne.client.render.ModModelLayers;
import com.crabmods.tameable_arachne.client.render.RenderArachne;
import com.crabmods.tameable_arachne.client.render.RenderArachneMedium;
import com.crabmods.tameable_arachne.client.render.RenderHarpy;
import com.crabmods.tameable_arachne.model.ModelArachneDetailed;
import com.crabmods.tameable_arachne.model.ModelArachneMedium;
import com.crabmods.tameable_arachne.model.ModelHarpy;

import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TameableArachneMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEventHandler {

    public static void registerEntityRenderers() {
        // This method is called from the main mod class during client setup
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(TameableArachneMod.ARACHNE.get(), RenderArachne::new);
        event.registerEntityRenderer(TameableArachneMod.ARACHNE_MEDIUM.get(), RenderArachneMedium::new);
        event.registerEntityRenderer(TameableArachneMod.HARPY.get(), RenderHarpy::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.ARACHNE_LAYER, ModelArachneDetailed::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.ARACHNE_MEDIUM_LAYER, ModelArachneMedium::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.HARPY_LAYER, ModelHarpy::createBodyLayer);
    }
}

