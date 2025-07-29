package com.crabmods.tameable_arachne.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class ModelArachne<T extends Entity> extends EntityModel<T> {
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart rightArm;
    private final ModelPart leftArm;
    private final ModelPart abdomen;
    private final ModelPart rightLeg1;
    private final ModelPart rightLeg2;
    private final ModelPart rightLeg3;
    private final ModelPart rightLeg4;
    private final ModelPart leftLeg1;
    private final ModelPart leftLeg2;
    private final ModelPart leftLeg3;
    private final ModelPart leftLeg4;

    public ModelArachne(ModelPart root) {
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.rightArm = root.getChild("right_arm");
        this.leftArm = root.getChild("left_arm");
        this.abdomen = root.getChild("abdomen");
        this.rightLeg1 = root.getChild("right_leg1");
        this.rightLeg2 = root.getChild("right_leg2");
        this.rightLeg3 = root.getChild("right_leg3");
        this.rightLeg4 = root.getChild("right_leg4");
        this.leftLeg1 = root.getChild("left_leg1");
        this.leftLeg2 = root.getChild("left_leg2");
        this.leftLeg3 = root.getChild("left_leg3");
        this.leftLeg4 = root.getChild("left_leg4");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        // Head
        partdefinition.addOrReplaceChild("head", CubeListBuilder.create()
                .texOffs(0, 0).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F)
                .texOffs(24, 0).addBox(-3.0F, 0.0F, 0.0F, 6.0F, 6.0F, 3.0F),
                PartPose.offset(0.0F, 8.5F, -2.5F));

        // Body (torso)
        partdefinition.addOrReplaceChild("body", CubeListBuilder.create()
                .texOffs(0, 12).addBox(-2.0F, 0.0F, -1.5F, 4.0F, 6.0F, 3.0F)
                .texOffs(0, 21).addBox(-2.5F, 6.0F, -1.5F, 5.0F, 3.0F, 3.0F),
                PartPose.offset(0.0F, 8.5F, -2.5F));

        // Arms
        partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create()
                .texOffs(24, 9).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 9.0F, 2.0F),
                PartPose.offset(-3.0F, 9.7F, -2.5F));

        partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create()
                .texOffs(32, 9).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 9.0F, 2.0F),
                PartPose.offset(3.0F, 9.7F, -2.5F));

        // Abdomen (spider part)
        partdefinition.addOrReplaceChild("abdomen", CubeListBuilder.create()
                .texOffs(26, 33).addBox(-5.0F, -3.5F, 2.5F, 10.0F, 7.0F, 12.0F)
                .texOffs(0, 33).addBox(-3.0F, -2.0F, -3.5F, 6.0F, 4.0F, 7.0F),
                PartPose.offset(0.0F, 17.0F, 0.0F));

        // Spider legs
        partdefinition.addOrReplaceChild("right_leg1", CubeListBuilder.create()
                .texOffs(74, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F),
                PartPose.offsetAndRotation(-3.0F, 19.0F, -2.5F, 0.0F, 0.0F, 2.460914F));

        partdefinition.addOrReplaceChild("right_leg2", CubeListBuilder.create()
                .texOffs(74, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F),
                PartPose.offsetAndRotation(-3.0F, 19.0F, -1.0F, 0.0F, 0.0F, 2.460914F));

        partdefinition.addOrReplaceChild("right_leg3", CubeListBuilder.create()
                .texOffs(74, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F),
                PartPose.offsetAndRotation(-3.0F, 19.0F, 1.0F, 0.0F, 0.0F, 2.460914F));

        partdefinition.addOrReplaceChild("right_leg4", CubeListBuilder.create()
                .texOffs(74, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F),
                PartPose.offsetAndRotation(-3.0F, 19.0F, 2.5F, 0.0F, 0.0F, 2.460914F));

        partdefinition.addOrReplaceChild("left_leg1", CubeListBuilder.create()
                .texOffs(82, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F),
                PartPose.offsetAndRotation(3.0F, 19.0F, -2.5F, 0.0F, 0.0F, -2.460914F));

        partdefinition.addOrReplaceChild("left_leg2", CubeListBuilder.create()
                .texOffs(82, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F),
                PartPose.offsetAndRotation(3.0F, 19.0F, -1.0F, 0.0F, 0.0F, -2.460914F));

        partdefinition.addOrReplaceChild("left_leg3", CubeListBuilder.create()
                .texOffs(82, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F),
                PartPose.offsetAndRotation(3.0F, 19.0F, 1.0F, 0.0F, 0.0F, -2.460914F));

        partdefinition.addOrReplaceChild("left_leg4", CubeListBuilder.create()
                .texOffs(82, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F),
                PartPose.offsetAndRotation(3.0F, 19.0F, 2.5F, 0.0F, 0.0F, -2.460914F));

        return LayerDefinition.create(meshdefinition, 128, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        // Head rotation
        this.head.xRot = headPitch * ((float)Math.PI / 180F);
        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);

        // Arm animation
        this.rightArm.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F;
        this.leftArm.xRot = Mth.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;

        // Breathing animation
        this.rightArm.xRot += Mth.sin(ageInTicks * 0.067F) * 0.05F;
        this.leftArm.xRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F;

        // Spider leg animation
        float legAngle = -2.460914F;
        this.rightLeg1.zRot = -legAngle;
        this.rightLeg2.zRot = -legAngle;
        this.rightLeg3.zRot = -legAngle;
        this.rightLeg4.zRot = -legAngle;
        this.leftLeg1.zRot = legAngle;
        this.leftLeg2.zRot = legAngle;
        this.leftLeg3.zRot = legAngle;
        this.leftLeg4.zRot = legAngle;

        // Walking animation for legs
        float legWalk = 0.3926991F;
        this.rightLeg1.yRot = legWalk * 2.0F + Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.rightLeg2.yRot = legWalk * 1.0F + Mth.cos(limbSwing * 0.6662F + (float)Math.PI * 0.5F) * 1.4F * limbSwingAmount;
        this.rightLeg3.yRot = -legWalk * 1.0F + Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.rightLeg4.yRot = -legWalk * 2.0F + Mth.cos(limbSwing * 0.6662F + (float)Math.PI * 1.5F) * 1.4F * limbSwingAmount;

        this.leftLeg1.yRot = -legWalk * 2.0F - Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leftLeg2.yRot = -legWalk * 1.0F - Mth.cos(limbSwing * 0.6662F + (float)Math.PI * 0.5F) * 1.4F * limbSwingAmount;
        this.leftLeg3.yRot = legWalk * 1.0F - Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.leftLeg4.yRot = legWalk * 2.0F - Mth.cos(limbSwing * 0.6662F + (float)Math.PI * 1.5F) * 1.4F * limbSwingAmount;

        // Sitting animation
        if (entity instanceof com.crabmods.tameable_arachne.entity.EntityArachne arachne && arachne.isInSittingPose()) {
            this.rightArm.xRot = -0.3839724F;
            this.rightArm.yRot = -0.0872665F;
            this.rightArm.zRot = -0.2443461F;
            this.leftArm.xRot = -0.3839724F;
            this.leftArm.yRot = 0.1047198F;
            this.leftArm.zRot = 0.2443461F;
        } else if (entity instanceof com.crabmods.tameable_arachne.entity.EntityArachneMedium arachneMedium && arachneMedium.isInSittingPose()) {
            this.rightArm.xRot = -0.3839724F;
            this.rightArm.yRot = -0.0872665F;
            this.rightArm.zRot = -0.2443461F;
            this.leftArm.xRot = -0.3839724F;
            this.leftArm.yRot = 0.1047198F;
            this.leftArm.zRot = 0.2443461F;
        } else if (entity instanceof com.crabmods.tameable_arachne.entity.EntityHarpy harpy && harpy.isInSittingPose()) {
            this.rightArm.xRot = -0.3839724F;
            this.rightArm.yRot = -0.0872665F;
            this.rightArm.zRot = -0.2443461F;
            this.leftArm.xRot = -0.3839724F;
            this.leftArm.yRot = 0.1047198F;
            this.leftArm.zRot = 0.2443461F;
        }
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        rightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        abdomen.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        rightLeg1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        rightLeg2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        rightLeg3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        rightLeg4.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leftLeg1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leftLeg2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leftLeg3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leftLeg4.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}

