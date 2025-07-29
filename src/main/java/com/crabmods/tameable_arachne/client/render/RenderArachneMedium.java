package com.crabmods.tameable_arachne.client.render;

import com.crabmods.tameable_arachne.TameableArachneMod;
import com.crabmods.tameable_arachne.entity.EntityArachneMedium;
import com.crabmods.tameable_arachne.model.ModelArachneMedium;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderArachneMedium extends MobRenderer<EntityArachneMedium, ModelArachneMedium<EntityArachneMedium>> {
    private static final ResourceLocation ARACHNE_MEDIUM_TEXTURE_WILD = ResourceLocation.fromNamespaceAndPath(TameableArachneMod.MODID, "textures/entity/arachne_medium/arachne_medium_00.png");
    private static final ResourceLocation ARACHNE_MEDIUM_TEXTURE_TAME = ResourceLocation.fromNamespaceAndPath(TameableArachneMod.MODID, "textures/entity/arachne_medium/arachne_medium_tame_00.png");

    public RenderArachneMedium(EntityRendererProvider.Context context) {
        super(context, new ModelArachneMedium<>(context.bakeLayer(ModModelLayers.ARACHNE_MEDIUM_LAYER)), 1.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityArachneMedium entity) {
        if (entity.isTame()) {
            return entity.getArachneType() == 1 ? 
                ResourceLocation.fromNamespaceAndPath(TameableArachneMod.MODID, "textures/entity/arachne_medium/arachne_medium_tame_01.png") :
                ARACHNE_MEDIUM_TEXTURE_TAME;
        } else {
            return entity.getArachneType() == 1 ? 
                ResourceLocation.fromNamespaceAndPath(TameableArachneMod.MODID, "textures/entity/arachne_medium/arachne_medium_01.png") :
                ARACHNE_MEDIUM_TEXTURE_WILD;
        }
    }
}
