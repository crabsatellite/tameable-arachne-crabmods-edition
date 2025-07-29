package com.crabmods.tameable_arachne.render;

import com.crabmods.tameable_arachne.entity.EntityArachne;
import com.crabmods.tameable_arachne.model.ModelArachne;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderArachne extends MobRenderer<EntityArachne, ModelArachne<EntityArachne>> {

    // Texture resources - exactly like 1.12.2 but using modern ResourceLocation
    private static final ResourceLocation ARACHNE_TEXTURES_00 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/arachne/arachne_00.png");
    private static final ResourceLocation TAMED_ARACHNE_TEXTURES_00 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/arachne/arachne_tame_00.png");
    private static final ResourceLocation ARACHNE_TEXTURES_01 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/arachne/arachne_01.png");
    private static final ResourceLocation TAMED_ARACHNE_TEXTURES_01 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/arachne/arachne_tame_01.png");

    public RenderArachne(EntityRendererProvider.Context context, ModelArachne<EntityArachne> model) {
        super(context, model, 0.6F); // Shadow size exactly like 1.12.2
    }

    @Override
    public ResourceLocation getTextureLocation(EntityArachne entity) {
        // Texture selection logic exactly like 1.12.2
        if (entity.getArachneType() == 1) {
            return entity.isTame() ? TAMED_ARACHNE_TEXTURES_01 : ARACHNE_TEXTURES_01;
        }

        return entity.isTame() ? TAMED_ARACHNE_TEXTURES_00 : ARACHNE_TEXTURES_00;
    }

    @Override
    public void render(EntityArachne entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        // Prepare model for rendering (handle wink animation, etc.)
        if (this.model instanceof ModelArachne) {
            ((ModelArachne<EntityArachne>) this.model).prepareForRender(entity);
        }

        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
