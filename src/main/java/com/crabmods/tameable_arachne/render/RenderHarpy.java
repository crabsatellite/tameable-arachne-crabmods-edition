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
    // 未驯服的Harpy纹理（14种变体） - 按照1.12.2命名风格
    private static final ResourceLocation harpyTextures_00 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_00.png");
    private static final ResourceLocation harpyTextures_01 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_01.png");
    private static final ResourceLocation harpyTextures_02 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_02.png");
    private static final ResourceLocation harpyTextures_03 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_03.png");
    private static final ResourceLocation harpyTextures_04 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_04.png");
    private static final ResourceLocation harpyTextures_05 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_05.png");
    private static final ResourceLocation harpyTextures_06 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_06.png");
    private static final ResourceLocation harpyTextures_07 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_07.png");
    private static final ResourceLocation harpyTextures_08 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_08.png");
    private static final ResourceLocation harpyTextures_09 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_09.png");
    private static final ResourceLocation harpyTextures_10 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_10.png");
    private static final ResourceLocation harpyTextures_11 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_11.png");
    private static final ResourceLocation harpyTextures_12 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_12.png");
    private static final ResourceLocation harpyTextures_13 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_13.png");

    // 已驯服的Harpy纹理（14种变体） - 按照1.12.2命名风格
    private static final ResourceLocation tamedHarpyTextures_00 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_tame_00.png");
    private static final ResourceLocation tamedHarpyTextures_01 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_tame_01.png");
    private static final ResourceLocation tamedHarpyTextures_02 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_tame_02.png");
    private static final ResourceLocation tamedHarpyTextures_03 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_tame_03.png");
    private static final ResourceLocation tamedHarpyTextures_04 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_tame_04.png");
    private static final ResourceLocation tamedHarpyTextures_05 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_tame_05.png");
    private static final ResourceLocation tamedHarpyTextures_06 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_tame_06.png");
    private static final ResourceLocation tamedHarpyTextures_07 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_tame_07.png");
    private static final ResourceLocation tamedHarpyTextures_08 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_tame_08.png");
    private static final ResourceLocation tamedHarpyTextures_09 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_tame_09.png");
    private static final ResourceLocation tamedHarpyTextures_10 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_tame_10.png");
    private static final ResourceLocation tamedHarpyTextures_11 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_tame_11.png");
    private static final ResourceLocation tamedHarpyTextures_12 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_tame_12.png");
    private static final ResourceLocation tamedHarpyTextures_13 = ResourceLocation.fromNamespaceAndPath("tameable_arachne", "textures/entity/harpy/harpy_tame_13.png");

    public RenderHarpy(EntityRendererProvider.Context context) {
        super(context, new ModelHarpy<>(context.bakeLayer(ModelHarpy.LAYER_LOCATION)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityHarpy entity) {
        // 按照1.12.2的逻辑：使用if语句而不是switch，使用getTextureNo()方法
        boolean tamed = entity.isTame();
        int id = entity.getTextureNo();

        if (id == 1) return tamed ? tamedHarpyTextures_01 : harpyTextures_01;
        if (id == 2) return tamed ? tamedHarpyTextures_02 : harpyTextures_02;
        if (id == 3) return tamed ? tamedHarpyTextures_03 : harpyTextures_03;
        if (id == 4) return tamed ? tamedHarpyTextures_04 : harpyTextures_04;
        if (id == 5) return tamed ? tamedHarpyTextures_05 : harpyTextures_05;
        if (id == 6) return tamed ? tamedHarpyTextures_06 : harpyTextures_06;
        if (id == 7) return tamed ? tamedHarpyTextures_07 : harpyTextures_07;
        if (id == 8) return tamed ? tamedHarpyTextures_08 : harpyTextures_08;
        if (id == 9) return tamed ? tamedHarpyTextures_09 : harpyTextures_09;
        if (id == 10) return tamed ? tamedHarpyTextures_10 : harpyTextures_10;
        if (id == 11) return tamed ? tamedHarpyTextures_11 : harpyTextures_11;
        if (id == 12) return tamed ? tamedHarpyTextures_12 : harpyTextures_12;
        if (id == 13) return tamed ? tamedHarpyTextures_13 : harpyTextures_13;
        
        return tamed ? tamedHarpyTextures_00 : harpyTextures_00;
    }

    @Override
    protected float getBob(EntityHarpy entity, float partialTick) {
        // 按照1.12.2的行为：翅膀不扇动，返回固定值
        // 因为1.12.2版本中这些字段没有被更新，始终保持初始值
        return 0.0F;
    }
}
