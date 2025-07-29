package com.crabmods.tameable_arachne.model;

import com.crabmods.tameable_arachne.entity.EntityArachne;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class ModelArachne<T extends Entity> extends EntityModel<T> {
    private final ModelPart head1;
    private final ModelPart face;
    private final ModelPart body1;
    private final ModelPart rightbreast;
    private final ModelPart leftbreast;
    private final ModelPart rightarm0;
    private final ModelPart rightarm1;
    private final ModelPart leftarm0;
    private final ModelPart leftarm1;
    private final ModelPart body31;
    private final ModelPart body41;
    private final ModelPart body51;
    private final ModelPart rightleg11;
    private final ModelPart rightleg12;
    private final ModelPart rightleg20;
    private final ModelPart rightleg21;
    private final ModelPart rightleg22;
    private final ModelPart rightleg23;
    private final ModelPart rightleg30;
    private final ModelPart rightleg31;
    private final ModelPart rightleg32;
    private final ModelPart rightleg33;
    private final ModelPart rightleg40;
    private final ModelPart rightleg41;
    private final ModelPart rightleg42;
    private final ModelPart rightleg43;
    private final ModelPart rightleg50;
    private final ModelPart rightleg51;
    private final ModelPart rightleg52;
    private final ModelPart rightleg53;
    private final ModelPart leftleg11;
    private final ModelPart leftleg12;
    private final ModelPart leftleg20;
    private final ModelPart leftleg21;
    private final ModelPart leftleg22;
    private final ModelPart leftleg23;
    private final ModelPart leftleg30;
    private final ModelPart leftleg31;
    private final ModelPart leftleg32;
    private final ModelPart leftleg33;
    private final ModelPart leftleg40;
    private final ModelPart leftleg41;
    private final ModelPart leftleg42;
    private final ModelPart leftleg43;
    private final ModelPart leftleg50;
    private final ModelPart leftleg51;
    private final ModelPart leftleg52;
    private final ModelPart leftleg53;

    public ModelArachne(ModelPart root) {
        this.body1 = root.getChild("body1");
        this.head1 = this.body1.getChild("head1");
        this.face = this.head1.getChild("face");
        this.rightbreast = this.body1.getChild("rightbreast");
        this.leftbreast = this.body1.getChild("leftbreast");
        this.rightarm0 = this.body1.getChild("rightarm0");
        this.rightarm1 = this.rightarm0.getChild("rightarm1");
        this.leftarm0 = this.body1.getChild("leftarm0");
        this.leftarm1 = this.leftarm0.getChild("leftarm1");

        this.body31 = root.getChild("body31");
        this.body41 = this.body31.getChild("body41");
        this.body51 = this.body31.getChild("body51");

        this.rightleg11 = this.body31.getChild("rightleg11");
        this.rightleg12 = this.rightleg11.getChild("rightleg12");
        this.leftleg11 = this.body31.getChild("leftleg11");
        this.leftleg12 = this.leftleg11.getChild("leftleg12");

        this.rightleg20 = this.body31.getChild("rightleg20");
        this.rightleg21 = this.rightleg20.getChild("rightleg21");
        this.rightleg22 = this.rightleg21.getChild("rightleg22");
        this.rightleg23 = this.rightleg22.getChild("rightleg23");

        this.rightleg30 = this.body31.getChild("rightleg30");
        this.rightleg31 = this.rightleg30.getChild("rightleg31");
        this.rightleg32 = this.rightleg31.getChild("rightleg32");
        this.rightleg33 = this.rightleg32.getChild("rightleg33");

        this.rightleg40 = this.body31.getChild("rightleg40");
        this.rightleg41 = this.rightleg40.getChild("rightleg41");
        this.rightleg42 = this.rightleg41.getChild("rightleg42");
        this.rightleg43 = this.rightleg42.getChild("rightleg43");

        this.rightleg50 = this.body31.getChild("rightleg50");
        this.rightleg51 = this.rightleg50.getChild("rightleg51");
        this.rightleg52 = this.rightleg51.getChild("rightleg52");
        this.rightleg53 = this.rightleg52.getChild("rightleg53");

        this.leftleg20 = this.body31.getChild("leftleg20");
        this.leftleg21 = this.leftleg20.getChild("leftleg21");
        this.leftleg22 = this.leftleg21.getChild("leftleg22");
        this.leftleg23 = this.leftleg22.getChild("leftleg23");

        this.leftleg30 = this.body31.getChild("leftleg30");
        this.leftleg31 = this.leftleg30.getChild("leftleg31");
        this.leftleg32 = this.leftleg31.getChild("leftleg32");
        this.leftleg33 = this.leftleg32.getChild("leftleg33");

        this.leftleg40 = this.body31.getChild("leftleg40");
        this.leftleg41 = this.leftleg40.getChild("leftleg41");
        this.leftleg42 = this.leftleg41.getChild("leftleg42");
        this.leftleg43 = this.leftleg42.getChild("leftleg43");

        this.leftleg50 = this.body31.getChild("leftleg50");
        this.leftleg51 = this.leftleg50.getChild("leftleg51");
        this.leftleg52 = this.leftleg51.getChild("leftleg52");
        this.leftleg53 = this.leftleg52.getChild("leftleg53");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        // Body (main torso) - positioned like in 1.12.2
        PartDefinition body1 = partdefinition.addOrReplaceChild("body1", CubeListBuilder.create()
                        .texOffs(0, 12).addBox(-2F, 0F, -1.5F, 4, 6, 3)
                        .texOffs(0, 21).addBox(-2.5F, 6F, -1.5F, 5, 3, 3),
                PartPose.offset(0F, 8.5F, -2.5F));

        // Head attached to body1 like in 1.12.2
        PartDefinition head1 = body1.addOrReplaceChild("head1", CubeListBuilder.create()
                        .texOffs(0, 0).addBox(-3F, -6F, -3F, 6, 6, 6)
                        .texOffs(24, 0).addBox(-3F, 0F, 0F, 6, 6, 3)
                        .texOffs(42, 0).addBox(-3F, -6F, -3F, 6, 12, 6, new CubeDeformation(0.3F)),
                PartPose.ZERO);

        // Face attached to head
        head1.addOrReplaceChild("face", CubeListBuilder.create()
                        .texOffs(42, 18).addBox(-3F, -6F, -3.01F, 6, 6, 0),
                PartPose.ZERO);

        // Breasts attached to body1
        body1.addOrReplaceChild("rightbreast", CubeListBuilder.create()
                        .texOffs(0, 27).addBox(-1.9F, -0.7F, -3.8F, 2, 2, 3),
                PartPose.rotation(0.8203047F, 0.0523599F, 0.0872665F));

        body1.addOrReplaceChild("leftbreast", CubeListBuilder.create()
                        .texOffs(10, 27).addBox(-0.1F, -0.7F, -3.8F, 2, 2, 3),
                PartPose.rotation(0.8203047F, -0.0523599F, -0.0872665F));

        // Arms attached to body1
        PartDefinition rightarm0 = body1.addOrReplaceChild("rightarm0", CubeListBuilder.create(),
                PartPose.offset(-3F, 1.2F, 0F));
        rightarm0.addOrReplaceChild("rightarm1", CubeListBuilder.create()
                        .texOffs(24, 9).addBox(-1F, -1F, -1F, 2, 9, 2),
                PartPose.ZERO);

        PartDefinition leftarm0 = body1.addOrReplaceChild("leftarm0", CubeListBuilder.create(),
                PartPose.offset(3F, 1.2F, 0F));
        leftarm0.addOrReplaceChild("leftarm1", CubeListBuilder.create()
                        .texOffs(32, 9).addBox(-1F, -1F, -1F, 2, 9, 2),
                PartPose.ZERO);

        // Spider body parts
        PartDefinition body31 = partdefinition.addOrReplaceChild("body31", CubeListBuilder.create()
                        .texOffs(0, 33).addBox(-3F, -2F, -3.5F, 6, 4, 7)
                        .texOffs(0, 44).addBox(2.5F, -1.5F, -3F, 1, 3, 6)
                        .texOffs(0, 53).addBox(-3.5F, -1.5F, -3F, 1, 3, 6),
                PartPose.offset(0F, 17F, 0F));

        body31.addOrReplaceChild("body41", CubeListBuilder.create()
                        .texOffs(14, 44).addBox(-2F, -1.5F, -0.5F, 4, 3, 1),
                PartPose.offsetAndRotation(0F, 0F, 4F, 0.0698131F, 0F, 0F));

        body31.addOrReplaceChild("body51", CubeListBuilder.create()
                        .texOffs(26, 52).addBox(-3.5F, -2.5F, 0.5F, 7, 5, 1)
                        .texOffs(42, 52).addBox(-4F, -3F, 1.5F, 8, 6, 1)
                        .texOffs(26, 33).addBox(-5F, -3.5F, 2.5F, 10, 7, 12)
                        .texOffs(70, 33).addBox(-4F, -4.5F, 3.5F, 8, 1, 9)
                        .texOffs(104, 33).addBox(5F, -2.5F, 3.5F, 1, 5, 9)
                        .texOffs(104, 47).addBox(-6F, -2.5F, 3.5F, 1, 5, 9)
                        .texOffs(70, 43).addBox(-4F, -3F, 14.5F, 8, 6, 2),
                PartPose.offsetAndRotation(0F, 0F, 4F, 0.1396263F, 0F, 0F));

        // Front legs (pedipalps) attached to body31
        PartDefinition rightleg11 = body31.addOrReplaceChild("rightleg11", CubeListBuilder.create()
                        .texOffs(66, 0).addBox(-1F, 0F, -1F, 2, 5, 2),
                PartPose.offsetAndRotation(-1.5F, 0.5F, -3.5F, -2.059489F, 0.3141593F, 0F));
        rightleg11.addOrReplaceChild("rightleg12", CubeListBuilder.create()
                        .texOffs(67, 7).addBox(-0.5F, 0F, 0F, 1, 5, 2),
                PartPose.offsetAndRotation(0F, 5F, -1F, 1.343904F, 0F, 0F));

        PartDefinition leftleg11 = body31.addOrReplaceChild("leftleg11", CubeListBuilder.create()
                        .texOffs(66, 14).addBox(-1F, 0F, -1F, 2, 5, 2),
                PartPose.offsetAndRotation(1.5F, 0.5F, -3.5F, -2.059489F, -0.3141593F, 0F));
        leftleg11.addOrReplaceChild("leftleg12", CubeListBuilder.create()
                        .texOffs(67, 21).addBox(-0.5F, 0F, 0F, 1, 5, 2),
                PartPose.offsetAndRotation(0F, 5F, -1F, 1.343904F, 0F, 0F));

        // Right spider legs - exactly like 1.12.2
        createSpiderLeg(body31, "rightleg20", -3F, 2F, -2.5F, 74, true);
        createSpiderLeg(body31, "rightleg30", -3F, 2F, -1F, 74, true);
        createSpiderLeg(body31, "rightleg40", -3F, 2F, 1F, 74, true);
        createSpiderLeg(body31, "rightleg50", -3F, 2F, 2.5F, 74, true);

        // Left spider legs - exactly like 1.12.2
        createSpiderLeg(body31, "leftleg20", 3F, 2F, -2.5F, 82, false);
        createSpiderLeg(body31, "leftleg30", 3F, 2F, -1F, 82, false);
        createSpiderLeg(body31, "leftleg40", 3F, 2F, 1F, 82, false);
        createSpiderLeg(body31, "leftleg50", 3F, 2F, 2.5F, 82, false);

        return LayerDefinition.create(meshdefinition, 128, 64);
    }

    private static void createSpiderLeg(PartDefinition parent, String name, float x, float y, float z, int texU, boolean isRight) {
        PartDefinition leg0 = parent.addOrReplaceChild(name, CubeListBuilder.create(),
                PartPose.offset(x, y, z));

        PartDefinition leg1 = leg0.addOrReplaceChild(name.replace("0", "1"), CubeListBuilder.create()
                        .texOffs(texU, 0).addBox(-1F, 0F, -1F, 2, 7, 2, new CubeDeformation(0.2F)),
                PartPose.rotation(0F, 0F, isRight ? 2.460914F : -2.460914F));

        PartDefinition leg2 = leg1.addOrReplaceChild(name.replace("0", "2"), CubeListBuilder.create()
                        .texOffs(texU, 9).addBox(isRight ? 0F : -2F, 0F, -1F, 2, 7, 2, new CubeDeformation(0.1F)),
                PartPose.offsetAndRotation(isRight ? -1F : 1F, 7F, 0F, 0F, 0F, isRight ? -1.343903F : 1.343903F));

        leg2.addOrReplaceChild(name.replace("0", "3"), CubeListBuilder.create()
                        .texOffs(texU, 18).addBox(isRight ? 0F : -2F, 0F, -1F, 2, 8, 2),
                PartPose.offsetAndRotation(0F, 7F, 0F, 0F, 0F, isRight ? -0.8552116F : 0.8552116F));
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        // Head rotation - exactly like 1.12.2
        this.head1.xRot = headPitch / (180F / (float) Math.PI);
        this.head1.yRot = netHeadYaw / (180F / (float) Math.PI);

        // Reset pedipalp rotations
        this.rightleg11.xRot = -2.059489F;
        this.leftleg11.xRot = -2.059489F;

        // Check if entity is Arachne and handle special animations
        if (entity instanceof EntityArachne entityarachne) {
            // Handle sitting animation - exactly like 1.12.2
            if (entityarachne.isInSittingPose()) {
                setRotation(rightarm0, 0F, 0F, 0F);
                setRotation(leftarm0, 0F, 0F, 0F);
                setRotation(rightarm1, -0.3839724F, -0.0872665F, -0.2443461F);
                setRotation(leftarm1, -0.3839724F, 0.1047198F, 0.2443461F);
            } else {
                setRotation(rightarm1, 0F, 0F, 0.2443461F);
                setRotation(leftarm1, 0F, 0F, -0.2443461F);
            }

            // Handle attack animation - exactly like 1.12.2
            int attackTimer = entityarachne.getAttackTimer();
            if (attackTimer > 0) {
                float f = -0.25F + 0.4F * this.func_78172_a(attackTimer - ageInTicks, 6.0F);
                this.rightleg11.xRot = -f - 2.059489F;
                this.rightleg12.xRot = f + 1.343904F;
                if (attackTimer < 10) {
                    float f2 = -0.25F + 0.4F * this.func_78172_a((float) attackTimer + 3 - ageInTicks, 6.0F);
                    this.leftleg11.xRot = -f2 - 2.059489F;
                    this.leftleg12.xRot = f2 + 1.343904F;
                }
            } else {
                this.rightleg11.xRot = -2.059489F;
                this.leftleg11.xRot = -2.059489F;
                this.rightleg12.xRot = 1.343904F;
                this.leftleg12.xRot = 1.343904F;
            }
        }

        // Arm swinging animation - exactly like 1.12.2
        this.rightarm0.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F;
        this.leftarm0.xRot = Mth.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
        this.rightarm0.xRot += Mth.sin(ageInTicks * 0.067F) * 0.05F;
        this.leftarm0.xRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F;

        // Spider leg base rotations - exactly like 1.12.2
        float f6 = -2.460914F;
        this.rightleg51.zRot = -f6;
        this.leftleg51.zRot = f6;
        this.rightleg41.zRot = -f6;
        this.leftleg41.zRot = f6;
        this.rightleg31.zRot = -f6;
        this.leftleg31.zRot = f6;
        this.rightleg21.zRot = -f6;
        this.leftleg21.zRot = f6;

        // Base leg positions - exactly like 1.12.2
        float f7 = -0.0F;
        float f8 = 0.3926991F;
        this.rightleg50.yRot = f8 * 2.0F + f7;
        this.leftleg50.yRot = -f8 * 2.0F - f7;
        this.rightleg40.yRot = f8 * 1.0F + f7;
        this.leftleg40.yRot = -f8 * 1.0F - f7;
        this.rightleg30.yRot = -f8 * 1.0F + f7;
        this.leftleg30.yRot = f8 * 1.0F - f7;
        this.rightleg20.yRot = -f8 * 2.0F + f7;
        this.leftleg20.yRot = f8 * 2.0F - f7;

        // Walking animation - exactly like 1.12.2
        float f9 = -(Mth.cos(limbSwing * 0.6662F * 2.0F + 0.0F) * 0.4F) * limbSwingAmount;
        float f10 = -(Mth.cos(limbSwing * 0.6662F * 2.0F + (float) Math.PI) * 0.4F) * limbSwingAmount;
        float f11 = -(Mth.cos(limbSwing * 0.6662F * 2.0F + ((float) Math.PI / 2F)) * 0.4F) * limbSwingAmount;
        float f12 = -(Mth.cos(limbSwing * 0.6662F * 2.0F + ((float) Math.PI * 3F / 2F)) * 0.4F) * limbSwingAmount;

        float f13 = Math.abs(Mth.sin(limbSwing * 0.6662F + 0.0F) * 0.4F) * limbSwingAmount;
        float f14 = Math.abs(Mth.sin(limbSwing * 0.6662F + (float) Math.PI) * 0.4F) * limbSwingAmount;
        float f15 = Math.abs(Mth.sin(limbSwing * 0.6662F + ((float) Math.PI / 2F)) * 0.4F) * limbSwingAmount;
        float f16 = Math.abs(Mth.sin(limbSwing * 0.6662F + ((float) Math.PI * 3F / 2F)) * 0.4F) * limbSwingAmount;

        this.rightleg50.yRot += f9;
        this.leftleg50.yRot -= f9;
        this.rightleg40.yRot += f10;
        this.leftleg40.yRot -= f10;
        this.rightleg30.yRot += f11;
        this.leftleg30.yRot -= f11;
        this.rightleg20.yRot += f12;
        this.leftleg20.yRot -= f12;

        this.rightleg51.zRot += f13;
        this.leftleg51.zRot -= f13;
        this.rightleg41.zRot += f14;
        this.leftleg41.zRot -= f14;
        this.rightleg31.zRot += f15;
        this.leftleg31.zRot -= f15;
        this.rightleg21.zRot += f16;
        this.leftleg21.zRot -= f16;
    }

    private float func_78172_a(float p_78172_1_, float p_78172_2_) {
        return (Math.abs(p_78172_1_ % p_78172_2_ - p_78172_2_ * 0.5F) - p_78172_2_ * 0.25F) / (p_78172_2_ * 0.25F);
    }

    private void setRotation(ModelPart model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        // Handle wink animation by hiding face - exactly like 1.12.2
        body1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        body31.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    // Separate method for handling entity-specific rendering like wink animation
    public void prepareForRender(Entity entity) {
        if (entity instanceof EntityArachne entityarachne) {
            face.visible = entityarachne.getWinkTimer() <= 0;
        }
    }
}
