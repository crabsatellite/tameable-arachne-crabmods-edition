package com.crabmods.tameable_arachne.client.render;

import com.crabmods.tameable_arachne.TameableArachneMod;
import com.crabmods.tameable_arachne.model.ModelArachneDetailed;
import com.crabmods.tameable_arachne.model.ModelArachneMedium;
import com.crabmods.tameable_arachne.model.ModelHarpy;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.EntityRenderersEvent;

public class ModModelLayers {
    public static final ModelLayerLocation ARACHNE_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(TameableArachneMod.MODID, "arachne"), "main");
    public static final ModelLayerLocation ARACHNE_MEDIUM_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(TameableArachneMod.MODID, "arachne_medium"), "main");
    public static final ModelLayerLocation HARPY_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(TameableArachneMod.MODID, "harpy"), "main");

    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ARACHNE_LAYER, ModelArachneDetailed::createBodyLayer);
        event.registerLayerDefinition(ARACHNE_MEDIUM_LAYER, ModelArachneMedium::createBodyLayer);
        event.registerLayerDefinition(HARPY_LAYER, ModelHarpy::createBodyLayer);
    }
}