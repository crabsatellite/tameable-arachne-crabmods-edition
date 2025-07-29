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

public class ModelArachneDetailed<T extends Entity> extends EntityModel<T> {
    private final ModelPart head;
    private final ModelPart face;
    private final ModelPart body;
    private final ModelPart rightBreast;
    private final ModelPart leftBreast;
    private final ModelPart rightArm0;
    private final ModelPart rightArm1;
    private final ModelPart leftArm0;
    private final ModelPart leftArm1;
    private final ModelPart abdomen;
    private final ModelPart abdomenMid;
    private final ModelPart abdomenEnd;
    
    // Spider legs - front pair (pedipalps)
    private final ModelPart rightLeg11;
    private final ModelPart rightLeg12;
    private final ModelPart leftLeg11;
    private final ModelPart leftLeg12;
    
    // Spider legs - 4 pairs of walking legs
    private final ModelPart rightLeg20;
    private final ModelPart rightLeg21;
    private final ModelPart rightLeg22;
    private final ModelPart rightLeg23;
    
    private final ModelPart rightLeg30;
    private final ModelPart rightLeg31;
    private final ModelPart rightLeg32;
    private final ModelPart rightLeg33;
    
    private final ModelPart rightLeg40;
    private final ModelPart rightLeg41;
    private final ModelPart rightLeg42;
    private final ModelPart rightLeg43;
    
    private final ModelPart rightLeg50;
    private final ModelPart rightLeg51;
    private final ModelPart rightLeg52;
    private final ModelPart rightLeg53;
    
    private final ModelPart leftLeg20;
    private final ModelPart leftLeg21;
    private final ModelPart leftLeg22;
    private final ModelPart leftLeg23;
    
    private final ModelPart leftLeg30;
    private final ModelPart leftLeg31;
    private final ModelPart leftLeg32;
    private final ModelPart leftLeg33;
    
    private final ModelPart leftLeg40;
    private final ModelPart leftLeg41;
    private final ModelPart leftLeg42;
    private final ModelPart leftLeg43;
    
    private final ModelPart leftLeg50;
    private final ModelPart leftLeg51;
    private final ModelPart leftLeg52;
    private final ModelPart leftLeg53;

    public ModelArachneDetailed(ModelPart root) {
        this.head = root.getChild("head");
        this.face = this.head.getChild("face");
        this.body = root.getChild("body");
        this.rightBreast = this.body.getChild("right_breast");
        this.leftBreast = this.body.getChild("left_breast");
        this.rightArm0 = this.body.getChild("right_arm0");
        this.rightArm1 = this.rightArm0.getChild("right_arm1");
        this.leftArm0 = this.body.getChild("left_arm0");
        this.leftArm1 = this.leftArm0.getChild("left_arm1");
        this.abdomen = root.getChild("abdomen");
        this.abdomenMid = this.abdomen.getChild("abdomen_mid");
        this.abdomenEnd = this.abdomen.getChild("abdomen_end");
        
        // Front legs (pedipalps)
        this.rightLeg11 = this.abdomen.getChild("right_leg11");
        this.rightLeg12 = this.rightLeg11.getChild("right_leg12");
        this.leftLeg11 = this.abdomen.getChild("left_leg11");
        this.leftLeg12 = this.leftLeg11.getChild("left_leg12");
        
        // Walking legs
        this.rightLeg20 = this.abdomen.getChild("right_leg20");
        this.rightLeg21 = this.rightLeg20.getChild("right_leg21");
        this.rightLeg22 = this.rightLeg21.getChild("right_leg22");
        this.rightLeg23 = this.rightLeg22.getChild("right_leg23");
        
        this.rightLeg30 = this.abdomen.getChild("right_leg30");
        this.rightLeg31 = this.rightLeg30.getChild("right_leg31");
        this.rightLeg32 = this.rightLeg31.getChild("right_leg32");
        this.rightLeg33 = this.rightLeg32.getChild("right_leg33");
        
        this.rightLeg40 = this.abdomen.getChild("right_leg40");
        this.rightLeg41 = this.rightLeg40.getChild("right_leg41");
        this.rightLeg42 = this.rightLeg41.getChild("right_leg42");
        this.rightLeg43 = this.rightLeg42.getChild("right_leg43");
        
        this.rightLeg50 = this.abdomen.getChild("right_leg50");
        this.rightLeg51 = this.rightLeg50.getChild("right_leg51");
        this.rightLeg52 = this.rightLeg51.getChild("right_leg52");
        this.rightLeg53 = this.rightLeg52.getChild("right_leg53");
        
        this.leftLeg20 = this.abdomen.getChild("left_leg20");
        this.leftLeg21 = this.leftLeg20.getChild("left_leg21");
        this.leftLeg22 = this.leftLeg21.getChild("left_leg22");
        this.leftLeg23 = this.leftLeg22.getChild("left_leg23");
        
        this.leftLeg30 = this.abdomen.getChild("left_leg30");
        this.leftLeg31 = this.leftLeg30.getChild("left_leg31");
        this.leftLeg32 = this.leftLeg31.getChild("left_leg32");
        this.leftLeg33 = this.leftLeg32.getChild("left_leg33");
        
        this.leftLeg40 = this.abdomen.getChild("left_leg40");
        this.leftLeg41 = this.leftLeg40.getChild("left_leg41");
        this.leftLeg42 = this.leftLeg41.getChild("left_leg42");
        this.leftLeg43 = this.leftLeg42.getChild("left_leg43");
        
        this.leftLeg50 = this.abdomen.getChild("left_leg50");
        this.leftLeg51 = this.leftLeg50.getChild("left_leg51");
        this.leftLeg52 = this.leftLeg51.getChild("left_leg52");
        this.leftLeg53 = this.leftLeg52.getChild("left_leg53");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        // Head with face
        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create()
                .texOffs(0, 0).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F)
                .texOffs(24, 0).addBox(-3.0F, 0.0F, 0.0F, 6.0F, 6.0F, 3.0F)
                .texOffs(42, 0).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 12.0F, 6.0F, new CubeDeformation(0.3F)),
                PartPose.offset(0.0F, 8.5F, -2.5F));
        
        head.addOrReplaceChild("face", CubeListBuilder.create()
                .texOffs(42, 18).addBox(-3.0F, -6.0F, -3.01F, 6.0F, 6.0F, 0.0F),
                PartPose.ZERO);

        // Body (torso) with breasts and arms
        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create()
                .texOffs(0, 12).addBox(-2.0F, 0.0F, -1.5F, 4.0F, 6.0F, 3.0F)
                .texOffs(0, 21).addBox(-2.5F, 6.0F, -1.5F, 5.0F, 3.0F, 3.0F),
                PartPose.offset(0.0F, 8.5F, -2.5F));

        body.addOrReplaceChild("right_breast", CubeListBuilder.create()
                .texOffs(0, 27).addBox(-1.9F, -0.7F, -3.8F, 2.0F, 2.0F, 3.0F),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.8203047F, 0.0523599F, 0.0872665F));

        body.addOrReplaceChild("left_breast", CubeListBuilder.create()
                .texOffs(10, 27).addBox(-0.1F, -0.7F, -3.8F, 2.0F, 2.0F, 3.0F),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.8203047F, -0.0523599F, -0.0872665F));

        // Arms
        PartDefinition rightArm0 = body.addOrReplaceChild("right_arm0", CubeListBuilder.create(),
                PartPose.offset(-3.0F, 1.2F, 0.0F));
        rightArm0.addOrReplaceChild("right_arm1", CubeListBuilder.create()
                .texOffs(24, 9).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 9.0F, 2.0F),
                PartPose.ZERO);

        PartDefinition leftArm0 = body.addOrReplaceChild("left_arm0", CubeListBuilder.create(),
                PartPose.offset(3.0F, 1.2F, 0.0F));
        leftArm0.addOrReplaceChild("left_arm1", CubeListBuilder.create()
                .texOffs(32, 9).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 9.0F, 2.0F),
                PartPose.ZERO);

        // Abdomen (spider part)
        PartDefinition abdomen = partdefinition.addOrReplaceChild("abdomen", CubeListBuilder.create()
                .texOffs(0, 33).addBox(-3.0F, -2.0F, -3.5F, 6.0F, 4.0F, 7.0F)
                .texOffs(0, 44).addBox(2.5F, -1.5F, -3.0F, 1.0F, 3.0F, 6.0F)
                .texOffs(0, 53).addBox(-3.5F, -1.5F, -3.0F, 1.0F, 3.0F, 6.0F),
                PartPose.offset(0.0F, 17.0F, 0.0F));

        abdomen.addOrReplaceChild("abdomen_mid", CubeListBuilder.create()
                .texOffs(14, 44).addBox(-2.0F, -1.5F, -0.5F, 4.0F, 3.0F, 1.0F),
                PartPose.offsetAndRotation(0.0F, 0.0F, 4.0F, 0.0698131F, 0.0F, 0.0F));

        abdomen.addOrReplaceChild("abdomen_end", CubeListBuilder.create()
                .texOffs(26, 52).addBox(-3.5F, -2.5F, 0.5F, 7.0F, 5.0F, 1.0F)
                .texOffs(42, 52).addBox(-4.0F, -3.0F, 1.5F, 8.0F, 6.0F, 1.0F)
                .texOffs(26, 33).addBox(-5.0F, -3.5F, 2.5F, 10.0F, 7.0F, 12.0F)
                .texOffs(70, 33).addBox(-4.0F, -4.5F, 3.5F, 8.0F, 1.0F, 9.0F)
                .texOffs(104, 33).addBox(5.0F, -2.5F, 3.5F, 1.0F, 5.0F, 9.0F)
                .texOffs(104, 47).addBox(-6.0F, -2.5F, 3.5F, 1.0F, 5.0F, 9.0F)
                .texOffs(70, 43).addBox(-4.0F, -3.0F, 14.5F, 8.0F, 6.0F, 2.0F),
                PartPose.offsetAndRotation(0.0F, 0.0F, 4.0F, 0.1396263F, 0.0F, 0.0F));

        // Front legs (pedipalps)
        PartDefinition rightLeg11 = abdomen.addOrReplaceChild("right_leg11", CubeListBuilder.create()
                .texOffs(66, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F),
                PartPose.offsetAndRotation(-1.5F, 0.5F, -3.5F, -2.059489F, 0.3141593F, 0.0F));
        rightLeg11.addOrReplaceChild("right_leg12", CubeListBuilder.create()
                .texOffs(67, 7).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 5.0F, 2.0F),
                PartPose.offsetAndRotation(0.0F, 5.0F, -1.0F, 1.343904F, 0.0F, 0.0F));

        PartDefinition leftLeg11 = abdomen.addOrReplaceChild("left_leg11", CubeListBuilder.create()
                .texOffs(66, 14).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F),
                PartPose.offsetAndRotation(1.5F, 0.5F, -3.5F, -2.059489F, -0.3141593F, 0.0F));
        leftLeg11.addOrReplaceChild("left_leg12", CubeListBuilder.create()
                .texOffs(67, 21).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 5.0F, 2.0F),
                PartPose.offsetAndRotation(0.0F, 5.0F, -1.0F, 1.343904F, 0.0F, 0.0F));

        // Walking legs - right side
        createWalkingLeg(abdomen, "right_leg20", 74, -3.0F, 2.0F, -2.5F, false);
        createWalkingLeg(abdomen, "right_leg30", 74, -3.0F, 2.0F, -1.0F, false);
        createWalkingLeg(abdomen, "right_leg40", 74, -3.0F, 2.0F, 1.0F, false);
        createWalkingLeg(abdomen, "right_leg50", 74, -3.0F, 2.0F, 2.5F, false);

        // Walking legs - left side
        createWalkingLeg(abdomen, "left_leg20", 82, 3.0F, 2.0F, -2.5F, true);
        createWalkingLeg(abdomen, "left_leg30", 82, 3.0F, 2.0F, -1.0F, true);
        createWalkingLeg(abdomen, "left_leg40", 82, 3.0F, 2.0F, 1.0F, true);
        createWalkingLeg(abdomen, "left_leg50", 82, 3.0F, 2.0F, 2.5F, true);

        return LayerDefinition.create(meshdefinition, 128, 64);
    }

    private static void createWalkingLeg(PartDefinition parent, String baseName, int texU, float x, float y, float z, boolean isLeft) {
        PartDefinition leg0 = parent.addOrReplaceChild(baseName, CubeListBuilder.create(), PartPose.offset(x, y, z));
        
        PartDefinition leg1 = leg0.addOrReplaceChild(baseName.replace("0", "1"), CubeListBuilder.create()
                .texOffs(texU, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.2F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, isLeft ? -2.460914F : 2.460914F));
        
        PartDefinition leg2 = leg1.addOrReplaceChild(baseName.replace("0", "2"), CubeListBuilder.create()
                .texOffs(texU, 9).addBox(isLeft ? -2.0F : 0.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.1F)),
                PartPose.offsetAndRotation(isLeft ? 1.0F : -1.0F, 7.0F, 0.0F, 0.0F, 0.0F, isLeft ? 1.343903F : -1.343903F));
        
        leg2.addOrReplaceChild(baseName.replace("0", "3"), CubeListBuilder.create()
                .texOffs(texU, 18).addBox(isLeft ? -2.0F : 0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F),
                PartPose.offsetAndRotation(0.0F, 7.0F, 0.0F, 0.0F, 0.0F, isLeft ? 0.8552116F : -0.8552116F));
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        // Head rotation
        this.head.xRot = headPitch * ((float)Math.PI / 180F);
        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);

        // Arm animation
        this.rightArm0.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F;
        this.leftArm0.xRot = Mth.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
        this.rightArm0.xRot += Mth.sin(ageInTicks * 0.067F) * 0.05F;
        this.leftArm0.xRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F;

        // Sitting animation
        if (entity instanceof EntityArachne arachne && arachne.isInSittingPose()) {
            this.rightArm0.xRot = 0.0F;
            this.rightArm0.yRot = 0.0F;
            this.rightArm0.zRot = 0.0F;
            this.leftArm0.xRot = 0.0F;
            this.leftArm0.yRot = 0.0F;
            this.leftArm0.zRot = 0.0F;
            this.rightArm1.xRot = -0.3839724F;
            this.rightArm1.yRot = -0.0872665F;
            this.rightArm1.zRot = -0.2443461F;
            this.leftArm1.xRot = -0.3839724F;
            this.leftArm1.yRot = 0.1047198F;
            this.leftArm1.zRot = 0.2443461F;
        } else {
            this.rightArm1.xRot = 0.0F;
            this.rightArm1.yRot = 0.0F;
            this.rightArm1.zRot = 0.2443461F;
            this.leftArm1.xRot = 0.0F;
            this.leftArm1.yRot = 0.0F;
            this.leftArm1.zRot = -0.2443461F;
        }

        // Attack animation for front legs
        if (entity instanceof EntityArachne arachne && arachne.getAttackTimer() > 0) {
            int attackTimer = arachne.getAttackTimer();
            float attackAnim = -0.25F + 0.4F * triangleWave(attackTimer - ageInTicks, 6.0F);
            this.rightLeg11.xRot = -attackAnim - 2.059489F;
            this.rightLeg12.xRot = attackAnim + 1.343904F;
            if (attackTimer < 10) {
                float attackAnim2 = -0.25F + 0.4F * triangleWave((float)attackTimer + 3 - ageInTicks, 6.0F);
                this.leftLeg11.xRot = -attackAnim2 - 2.059489F;
                this.leftLeg12.xRot = attackAnim2 + 1.343904F;
            }
        } else {
            this.rightLeg11.xRot = -2.059489F;
            this.leftLeg11.xRot = -2.059489F;
            this.rightLeg12.xRot = 1.343904F;
            this.leftLeg12.xRot = 1.343904F;
        }

        // Complex spider leg walking animation
        float legAngle = -2.460914F;
        this.rightLeg51.zRot = -legAngle;
        this.leftLeg51.zRot = legAngle;
        this.rightLeg41.zRot = -legAngle;
        this.leftLeg41.zRot = legAngle;
        this.rightLeg31.zRot = -legAngle;
        this.leftLeg31.zRot = legAngle;
        this.rightLeg21.zRot = -legAngle;
        this.leftLeg21.zRot = legAngle;

        float legSpread = 0.3926991F;
        this.rightLeg50.yRot = legSpread * 2.0F;
        this.leftLeg50.yRot = -legSpread * 2.0F;
        this.rightLeg40.yRot = legSpread * 1.0F;
        this.leftLeg40.yRot = -legSpread * 1.0F;
        this.rightLeg30.yRot = -legSpread * 1.0F;
        this.leftLeg30.yRot = legSpread * 1.0F;
        this.rightLeg20.yRot = -legSpread * 2.0F;
        this.leftLeg20.yRot = legSpread * 2.0F;

        // Walking animation
        float walkAnim1 = -(Mth.cos(limbSwing * 0.6662F * 2.0F + 0.0F) * 0.4F) * limbSwingAmount;
        float walkAnim2 = -(Mth.cos(limbSwing * 0.6662F * 2.0F + (float)Math.PI) * 0.4F) * limbSwingAmount;
        float walkAnim3 = -(Mth.cos(limbSwing * 0.6662F * 2.0F + ((float)Math.PI / 2F)) * 0.4F) * limbSwingAmount;
        float walkAnim4 = -(Mth.cos(limbSwing * 0.6662F * 2.0F + ((float)Math.PI * 3F / 2F)) * 0.4F) * limbSwingAmount;

        float legLift1 = Math.abs(Mth.sin(limbSwing * 0.6662F + 0.0F) * 0.4F) * limbSwingAmount;
        float legLift2 = Math.abs(Mth.sin(limbSwing * 0.6662F + (float)Math.PI) * 0.4F) * limbSwingAmount;
        float legLift3 = Math.abs(Mth.sin(limbSwing * 0.6662F + ((float)Math.PI / 2F)) * 0.4F) * limbSwingAmount;
        float legLift4 = Math.abs(Mth.sin(limbSwing * 0.6662F + ((float)Math.PI * 3F / 2F)) * 0.4F) * limbSwingAmount;

        this.rightLeg50.yRot += walkAnim1;
        this.leftLeg50.yRot += -walkAnim1;
        this.rightLeg40.yRot += walkAnim2;
        this.leftLeg40.yRot += -walkAnim2;
        this.rightLeg30.yRot += walkAnim3;
        this.leftLeg30.yRot += -walkAnim3;
        this.rightLeg20.yRot += walkAnim4;
        this.leftLeg20.yRot += -walkAnim4;

        this.rightLeg51.zRot += legLift1;
        this.leftLeg51.zRot -= legLift1;
        this.rightLeg41.zRot += legLift2;
        this.leftLeg41.zRot -= legLift2;
        this.rightLeg31.zRot += legLift3;
        this.leftLeg31.zRot -= legLift3;
        this.rightLeg21.zRot += legLift4;
        this.leftLeg21.zRot -= legLift4;
    }

    private float triangleWave(float input, float period) {
        return (Math.abs(input % period - period * 0.5F) - period * 0.25F) / (period * 0.25F);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        abdomen.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}

