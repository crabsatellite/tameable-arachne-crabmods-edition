package com.crabmods.tameable_arachne.client.render;

import com.crabmods.tameable_arachne.TameableArachneMod;
import com.crabmods.tameable_arachne.entity.EntityHarpy;
import com.crabmods.tameable_arachne.model.ModelHarpy;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderHarpy extends MobRenderer<EntityHarpy, ModelHarpy<EntityHarpy>> {
    private static final ResourceLocation[] HARPY_TEXTURES_WILD = {
        ResourceLocation.fromNamespaceAndPath(TameableArachneMod.MODID, "textures/entity/harpy/harpy_00.png"),
        ResourceLocation.fromNamespaceAndPath(TameableArachneMod.MODID, "textures/entity/harpy/harpy_01.png"),
        ResourceLocation.fromNamespaceAndPath(TameableArachneMod.MODID, "textures/entity/harpy/harpy_02.png"),
        ResourceLocation.fromNamespaceAndPath(TameableArachneMod.MODID, "textures/entity/harpy/harpy_03.png"),
        ResourceLocation.fromNamespaceAndPath(TameableArachneMod.MODID, "textures/entity/harpy/harpy_04.png"),
        ResourceLocation.fromNamespaceAndPath(TameableArachneMod.MODID, "textures/entity/harpy/harpy_05.png"),
        ResourceLocation.fromNamespaceAndPath(TameableArachneMod.MODID, "textures/entity/harpy/harpy_06.png"),
        ResourceLocation.fromNamespaceAndPath(TameableArachneMod.MODID, "textures/entity/harpy/harpy_07.png"),
        ResourceLocation.fromNamespaceAndPath(TameableArachneMod.MODID, "textures/entity/harpy/harpy_08.png"),
        ResourceLocation.fromNamespaceAndPath(TameableArachneMod.MODID, "textures/entity/harpy/harpy_09.png"),
        ResourceLocation.fromNamespaceAndPath(TameableArachneMod.MODID, "textures/entity/harpy/harpy_10.png"),
        ResourceLocation.fromNamespaceAndPath(TameableArachneMod.MODID, "textures/entity/harpy/harpy_11.png"),
        ResourceLocation.fromNamespaceAndPath(TameableArachneMod.MODID, "textures/entity/harpy/harpy_12.png"),
        ResourceLocation.fromNamespaceAndPath(TameableArachneMod.MODID, "textures/entity/harpy/harpy_13.png")
    };

    private static final ResourceLocation[] HARPY_TEXTURES_TAME = {
        ResourceLocation.fromNamespaceAndPath(TameableArachneMod.MODID, "textures/entity/harpy/harpy_tame_00.png"),
        ResourceLocation.fromNamespaceAndPath(TameableArachneMod.MODID, "textures/entity/harpy/harpy_tame_01.png"),
        ResourceLocation.fromNamespaceAndPath(TameableArachneMod.MODID, "textures/entity/harpy/harpy_tame_02.png"),
        ResourceLocation.fromNamespaceAndPath(TameableArachneMod.MODID, "textures/entity/harpy/harpy_tame_03.png"),
        ResourceLocation.fromNamespaceAndPath(TameableArachneMod.MODID, "textures/entity/harpy/harpy_tame_04.png"),
        ResourceLocation.fromNamespaceAndPath(TameableArachneMod.MODID, "textures/entity/harpy/harpy_tame_05.png"),
        ResourceLocation.fromNamespaceAndPath(TameableArachneMod.MODID, "textures/entity/harpy/harpy_tame_06.png"),
        ResourceLocation.fromNamespaceAndPath(TameableArachneMod.MODID, "textures/entity/harpy/harpy_tame_07.png"),
        ResourceLocation.fromNamespaceAndPath(TameableArachneMod.MODID, "textures/entity/harpy/harpy_tame_08.png"),
        ResourceLocation.fromNamespaceAndPath(TameableArachneMod.MODID, "textures/entity/harpy/harpy_tame_09.png"),
        ResourceLocation.fromNamespaceAndPath(TameableArachneMod.MODID, "textures/entity/harpy/harpy_tame_10.png"),
        ResourceLocation.fromNamespaceAndPath(TameableArachneMod.MODID, "textures/entity/harpy/harpy_tame_11.png"),
        ResourceLocation.fromNamespaceAndPath(TameableArachneMod.MODID, "textures/entity/harpy/harpy_tame_12.png"),
        ResourceLocation.fromNamespaceAndPath(TameableArachneMod.MODID, "textures/entity/harpy/harpy_tame_13.png")
    };

    public RenderHarpy(EntityRendererProvider.Context context) {
        super(context, new ModelHarpy<>(context.bakeLayer(ModModelLayers.HARPY_LAYER)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityHarpy entity) {
        int textureIndex = entity.getArachneType() % (entity.isTame() ? HARPY_TEXTURES_TAME.length : HARPY_TEXTURES_WILD.length);
        return entity.isTame() ? HARPY_TEXTURES_TAME[textureIndex] : HARPY_TEXTURES_WILD[textureIndex];
    }
}

