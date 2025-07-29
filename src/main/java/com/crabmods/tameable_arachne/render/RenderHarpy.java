package com.crabmods.tameable_arachne.render;

import com.crabmods.tameable_arachne.entity.EntityHarpy;
import com.crabmods.tameable_arachne.model.ModelHarpy;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderHarpy extends MobRenderer<EntityHarpy, ModelHarpy<EntityHarpy>> {
    // 未驯服的Harpy纹理（14种变体）
    private static final ResourceLocation HARPY_TEXTURES_00 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_00.png");
    private static final ResourceLocation HARPY_TEXTURES_01 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_01.png");
    private static final ResourceLocation HARPY_TEXTURES_02 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_02.png");
    private static final ResourceLocation HARPY_TEXTURES_03 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_03.png");
    private static final ResourceLocation HARPY_TEXTURES_04 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_04.png");
    private static final ResourceLocation HARPY_TEXTURES_05 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_05.png");
    private static final ResourceLocation HARPY_TEXTURES_06 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_06.png");
    private static final ResourceLocation HARPY_TEXTURES_07 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_07.png");
    private static final ResourceLocation HARPY_TEXTURES_08 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_08.png");
    private static final ResourceLocation HARPY_TEXTURES_09 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_09.png");
    private static final ResourceLocation HARPY_TEXTURES_10 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_10.png");
    private static final ResourceLocation HARPY_TEXTURES_11 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_11.png");
    private static final ResourceLocation HARPY_TEXTURES_12 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_12.png");
    private static final ResourceLocation HARPY_TEXTURES_13 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_13.png");

    // 已驯服的Harpy纹理（14种变体）
    private static final ResourceLocation TAMED_HARPY_TEXTURES_00 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_tame_00.png");
    private static final ResourceLocation TAMED_HARPY_TEXTURES_01 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_tame_01.png");
    private static final ResourceLocation TAMED_HARPY_TEXTURES_02 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_tame_02.png");
    private static final ResourceLocation TAMED_HARPY_TEXTURES_03 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_tame_03.png");
    private static final ResourceLocation TAMED_HARPY_TEXTURES_04 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_tame_04.png");
    private static final ResourceLocation TAMED_HARPY_TEXTURES_05 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_tame_05.png");
    private static final ResourceLocation TAMED_HARPY_TEXTURES_06 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_tame_06.png");
    private static final ResourceLocation TAMED_HARPY_TEXTURES_07 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_tame_07.png");
    private static final ResourceLocation TAMED_HARPY_TEXTURES_08 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_tame_08.png");
    private static final ResourceLocation TAMED_HARPY_TEXTURES_09 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_tame_09.png");
    private static final ResourceLocation TAMED_HARPY_TEXTURES_10 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_tame_10.png");
    private static final ResourceLocation TAMED_HARPY_TEXTURES_11 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_tame_11.png");
    private static final ResourceLocation TAMED_HARPY_TEXTURES_12 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_tame_12.png");
    private static final ResourceLocation TAMED_HARPY_TEXTURES_13 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_tame_13.png");

    public RenderHarpy(EntityRendererProvider.Context context) {
        super(context, new ModelHarpy<>(context.bakeLayer(ModelHarpy.LAYER_LOCATION)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityHarpy entity) {
        boolean tamed = entity.isTame();
        int id = entity.getArachneType();

        switch (id) {
            case 1: return tamed ? TAMED_HARPY_TEXTURES_01 : HARPY_TEXTURES_01;
            case 2: return tamed ? TAMED_HARPY_TEXTURES_02 : HARPY_TEXTURES_02;
            case 3: return tamed ? TAMED_HARPY_TEXTURES_03 : HARPY_TEXTURES_03;
            case 4: return tamed ? TAMED_HARPY_TEXTURES_04 : HARPY_TEXTURES_04;
            case 5: return tamed ? TAMED_HARPY_TEXTURES_05 : HARPY_TEXTURES_05;
            case 6: return tamed ? TAMED_HARPY_TEXTURES_06 : HARPY_TEXTURES_06;
            case 7: return tamed ? TAMED_HARPY_TEXTURES_07 : HARPY_TEXTURES_07;
            case 8: return tamed ? TAMED_HARPY_TEXTURES_08 : HARPY_TEXTURES_08;
            case 9: return tamed ? TAMED_HARPY_TEXTURES_09 : HARPY_TEXTURES_09;
            case 10: return tamed ? TAMED_HARPY_TEXTURES_10 : HARPY_TEXTURES_10;
            case 11: return tamed ? TAMED_HARPY_TEXTURES_11 : HARPY_TEXTURES_11;
            case 12: return tamed ? TAMED_HARPY_TEXTURES_12 : HARPY_TEXTURES_12;
            case 13: return tamed ? TAMED_HARPY_TEXTURES_13 : HARPY_TEXTURES_13;
            default: return tamed ? TAMED_HARPY_TEXTURES_00 : HARPY_TEXTURES_00;
        }
    }

    @Override
    protected float getBob(EntityHarpy entity, float partialTick) {
        // 翅膀拍打动画 - 对应1.12.2版本的handleRotationFloat方法
        float f1 = entity.oFlap + (entity.flap - entity.oFlap) * partialTick;
        float f2 = entity.oFlapSpeed + (entity.destPos - entity.oFlapSpeed) * partialTick;
        return (Mth.sin(f1) + 1.0F) * f2;
    }
}
