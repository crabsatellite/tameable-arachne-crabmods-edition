package com.crabmods.tameable_arachne.client.render;

import com.crabmods.tameable_arachne.TameableArachneMod;
import com.crabmods.tameable_arachne.entity.EntityArachne;
import com.crabmods.tameable_arachne.model.ModelArachneDetailed;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderArachne extends MobRenderer<EntityArachne, ModelArachneDetailed<EntityArachne>> {
    private static final ResourceLocation ARACHNE_TEXTURE_WILD = ResourceLocation.fromNamespaceAndPath(TameableArachneMod.MODID, "textures/entity/arachne/arachne_00.png");
    private static final ResourceLocation ARACHNE_TEXTURE_TAME = ResourceLocation.fromNamespaceAndPath(TameableArachneMod.MODID, "textures/entity/arachne/arachne_tame_00.png");

    public RenderArachne(EntityRendererProvider.Context context) {
        super(context, new ModelArachneDetailed<>(context.bakeLayer(ModModelLayers.ARACHNE_LAYER)), 0.8F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityArachne entity) {
        if (entity.isTame()) {
            return entity.getArachneType() == 1 ? 
                ResourceLocation.fromNamespaceAndPath(TameableArachneMod.MODID, "textures/entity/arachne/arachne_tame_01.png") : 
                ARACHNE_TEXTURE_TAME;
        } else {
            return entity.getArachneType() == 1 ? 
                ResourceLocation.fromNamespaceAndPath(TameableArachneMod.MODID, "textures/entity/arachne/arachne_01.png") : 
                ARACHNE_TEXTURE_WILD;
        }
    }
}

