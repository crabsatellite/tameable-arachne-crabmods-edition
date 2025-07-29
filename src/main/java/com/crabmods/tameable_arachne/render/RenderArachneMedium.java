package com.crabmods.tameable_arachne.render;

import com.crabmods.tameable_arachne.entity.EntityArachneMedium;
import com.crabmods.tameable_arachne.model.ModelArachneMedium;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderArachneMedium extends MobRenderer<EntityArachneMedium, ModelArachneMedium<EntityArachneMedium>> {

    // Texture resources - exactly like 1.12.2 but using modern ResourceLocation
    private static final ResourceLocation ARACHNE_MEDIUM_TEXTURES_00 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/arachne_medium/arachne_medium_00.png");
    private static final ResourceLocation TAMED_ARACHNE_MEDIUM_TEXTURES_00 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/arachne_medium/arachne_medium_tame_00.png");
    private static final ResourceLocation ARACHNE_MEDIUM_TEXTURES_01 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/arachne_medium/arachne_medium_01.png");
    private static final ResourceLocation TAMED_ARACHNE_MEDIUM_TEXTURES_01 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/arachne_medium/arachne_medium_tame_01.png");

    public RenderArachneMedium(EntityRendererProvider.Context context, ModelArachneMedium<EntityArachneMedium> model) {
        super(context, model, 0.6F); // Shadow size exactly like 1.12.2
    }

    @Override
    public ResourceLocation getTextureLocation(EntityArachneMedium entity) {
        // Texture selection logic exactly like 1.12.2
        if (entity.getArachneType() == 1) {
            return entity.isTame() ? TAMED_ARACHNE_MEDIUM_TEXTURES_01 : ARACHNE_MEDIUM_TEXTURES_01;
        }

        return entity.isTame() ? TAMED_ARACHNE_MEDIUM_TEXTURES_00 : ARACHNE_MEDIUM_TEXTURES_00;
    }

    @Override
    public void render(EntityArachneMedium entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        // Prepare model for rendering (handle wink animation, etc.)
        if (this.model instanceof ModelArachneMedium) {
            ((ModelArachneMedium<EntityArachneMedium>) this.model).prepareForRender(entity);
        }

        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
