package com.crabmods.tameable_arachne.model;

import com.crabmods.tameable_arachne.entity.EntityArachneMedium;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

public class ModelArachneMedium<T extends Entity> extends EntityModel<T> {
    // All model parts
    private final ModelPart head1;
    private final ModelPart face;
    private final ModelPart rightbreast;
    private final ModelPart leftbreast;
    private final ModelPart rightarm0;
    private final ModelPart rightarm1;
    private final ModelPart leftarm0;
    private final ModelPart leftarm1;
    private final ModelPart body1;
    private final ModelPart body31;
    private final ModelPart body4j;
    private final ModelPart body51;
    
    // Front legs (chelicerae)
    private final ModelPart rightleg11;
    private final ModelPart rightleg12j;
    private final ModelPart rightleg12;
    private final ModelPart leftleg11;
    private final ModelPart leftleg12j;
    private final ModelPart leftleg12;
    
    // Walking legs
    private final ModelPart rightleg20;
    private final ModelPart rightleg21;
    private final ModelPart rightleg22j;
    private final ModelPart rightleg22;
    private final ModelPart rightleg23j;
    private final ModelPart rightleg23;
    
    private final ModelPart leftleg20;
    private final ModelPart leftleg21;
    private final ModelPart leftleg22j;
    private final ModelPart leftleg22;
    private final ModelPart leftleg23j;
    private final ModelPart leftleg23;
    
    private final ModelPart rightleg30;
    private final ModelPart rightleg31;
    private final ModelPart rightleg32j;
    private final ModelPart rightleg32;
    private final ModelPart rightleg33j;
    private final ModelPart rightleg33;
    
    private final ModelPart leftleg30;
    private final ModelPart leftleg31;
    private final ModelPart leftleg32j;
    private final ModelPart leftleg32;
    private final ModelPart leftleg33j;
    private final ModelPart leftleg33;
    
    private final ModelPart rightleg40;
    private final ModelPart rightleg41;
    private final ModelPart rightleg42j;
    private final ModelPart rightleg42;
    private final ModelPart rightleg43j;
    private final ModelPart rightleg43;
    
    private final ModelPart leftleg40;
    private final ModelPart leftleg41;
    private final ModelPart leftleg42j;
    private final ModelPart leftleg42;
    private final ModelPart leftleg43j;
    private final ModelPart leftleg43;
    
    private final ModelPart rightleg50;
    private final ModelPart rightleg51;
    private final ModelPart rightleg52j;
    private final ModelPart rightleg52;
    private final ModelPart rightleg53j;
    private final ModelPart rightleg53;
    
    private final ModelPart leftleg50;
    private final ModelPart leftleg51;
    private final ModelPart leftleg52j;
    private final ModelPart leftleg52;
    private final ModelPart leftleg53j;
    private final ModelPart leftleg53;

    public ModelArachneMedium(ModelPart root) {
        this.body1 = root.getChild("body1");
        this.body31 = root.getChild("body31");
        
        this.head1 = this.body1.getChild("head1");
        this.face = this.head1.getChild("face");
        this.rightbreast = this.body1.getChild("rightbreast");
        this.leftbreast = this.body1.getChild("leftbreast");
        this.rightarm0 = this.body1.getChild("rightarm0");
        this.rightarm1 = this.rightarm0.getChild("rightarm1");
        this.leftarm0 = this.body1.getChild("leftarm0");
        this.leftarm1 = this.leftarm0.getChild("leftarm1");
        
        this.body4j = this.body31.getChild("body4j");
        this.body51 = this.body4j.getChild("body51");
        
        // Front legs
        this.rightleg11 = this.body31.getChild("rightleg11");
        this.rightleg12j = this.rightleg11.getChild("rightleg12j");
        this.rightleg12 = this.rightleg11.getChild("rightleg12");
        this.leftleg11 = this.body31.getChild("leftleg11");
        this.leftleg12j = this.leftleg11.getChild("leftleg12j");
        this.leftleg12 = this.leftleg11.getChild("leftleg12");
        
        // Walking legs
        this.rightleg20 = this.body31.getChild("rightleg20");
        this.rightleg21 = this.rightleg20.getChild("rightleg21");
        this.rightleg22j = this.rightleg21.getChild("rightleg22j");
        this.rightleg22 = this.rightleg21.getChild("rightleg22");
        this.rightleg23j = this.rightleg22.getChild("rightleg23j");
        this.rightleg23 = this.rightleg22.getChild("rightleg23");
        
        this.leftleg20 = this.body31.getChild("leftleg20");
        this.leftleg21 = this.leftleg20.getChild("leftleg21");
        this.leftleg22j = this.leftleg21.getChild("leftleg22j");
        this.leftleg22 = this.leftleg21.getChild("leftleg22");
        this.leftleg23j = this.leftleg22.getChild("leftleg23j");
        this.leftleg23 = this.leftleg22.getChild("leftleg23");
        
        this.rightleg30 = this.body31.getChild("rightleg30");
        this.rightleg31 = this.rightleg30.getChild("rightleg31");
        this.rightleg32j = this.rightleg31.getChild("rightleg32j");
        this.rightleg32 = this.rightleg31.getChild("rightleg32");
        this.rightleg33j = this.rightleg32.getChild("rightleg33j");
        this.rightleg33 = this.rightleg32.getChild("rightleg33");
        
        this.leftleg30 = this.body31.getChild("leftleg30");
        this.leftleg31 = this.leftleg30.getChild("leftleg31");
        this.leftleg32j = this.leftleg31.getChild("leftleg32j");
        this.leftleg32 = this.leftleg31.getChild("leftleg32");
        this.leftleg33j = this.leftleg32.getChild("leftleg33j");
        this.leftleg33 = this.leftleg32.getChild("leftleg33");
        
        this.rightleg40 = this.body31.getChild("rightleg40");
        this.rightleg41 = this.rightleg40.getChild("rightleg41");
        this.rightleg42j = this.rightleg41.getChild("rightleg42j");
        this.rightleg42 = this.rightleg41.getChild("rightleg42");
        this.rightleg43j = this.rightleg42.getChild("rightleg43j");
        this.rightleg43 = this.rightleg42.getChild("rightleg43");
        
        this.leftleg40 = this.body31.getChild("leftleg40");
        this.leftleg41 = this.leftleg40.getChild("leftleg41");
        this.leftleg42j = this.leftleg41.getChild("leftleg42j");
        this.leftleg42 = this.leftleg41.getChild("leftleg42");
        this.leftleg43j = this.leftleg42.getChild("leftleg43j");
        this.leftleg43 = this.leftleg42.getChild("leftleg43");
        
        this.rightleg50 = this.body31.getChild("rightleg50");
        this.rightleg51 = this.rightleg50.getChild("rightleg51");
        this.rightleg52j = this.rightleg51.getChild("rightleg52j");
        this.rightleg52 = this.rightleg51.getChild("rightleg52");
        this.rightleg53j = this.rightleg52.getChild("rightleg53j");
        this.rightleg53 = this.rightleg52.getChild("rightleg53");
        
        this.leftleg50 = this.body31.getChild("leftleg50");
        this.leftleg51 = this.leftleg50.getChild("leftleg51");
        this.leftleg52j = this.leftleg51.getChild("leftleg52j");
        this.leftleg52 = this.leftleg51.getChild("leftleg52");
        this.leftleg53j = this.leftleg52.getChild("leftleg53j");
        this.leftleg53 = this.leftleg52.getChild("leftleg53");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        // Body1 (torso)
        PartDefinition body1 = partdefinition.addOrReplaceChild("body1", CubeListBuilder.create()
                .texOffs(0, 16).addBox(-3.0F, 0.0F, -2.0F, 6.0F, 8.0F, 4.0F)
                .texOffs(0, 28).addBox(-3.5F, 8.0F, -2.0F, 7.0F, 4.0F, 5.0F),
                PartPose.offset(0.0F, -1.0F, -3.5F));

        // Head with face
        PartDefinition head1 = body1.addOrReplaceChild("head1", CubeListBuilder.create()
                .texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F)
                .texOffs(32, 0).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 8.0F, 8.0F)
                .texOffs(64, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 16.0F, 8.0F, new CubeDeformation(0.3F)),
                PartPose.ZERO);
        
        head1.addOrReplaceChild("face", CubeListBuilder.create()
                .texOffs(24, 0).addBox(-4.0F, -8.0F, -4.01F, 8.0F, 8.0F, 0.0F),
                PartPose.ZERO);

        // Breasts
        body1.addOrReplaceChild("rightbreast", CubeListBuilder.create()
                .texOffs(0, 37).addBox(-2.8F, -0.8F, -5.4F, 3.0F, 3.0F, 4.0F),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.7679449F, 0.0523599F, 0.0872665F));

        body1.addOrReplaceChild("leftbreast", CubeListBuilder.create()
                .texOffs(14, 37).addBox(-0.2F, -0.8F, -5.4F, 3.0F, 3.0F, 4.0F),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.7679449F, -0.0523599F, -0.0872665F));

        // Arms
        PartDefinition rightarm0 = body1.addOrReplaceChild("rightarm0", CubeListBuilder.create(),
                PartPose.offset(-4.0F, 1.2F, 0.0F));
        rightarm0.addOrReplaceChild("rightarm1", CubeListBuilder.create()
                .texOffs(24, 16).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F)
                .texOffs(40, 16).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.5F)),
                PartPose.ZERO);

        PartDefinition leftarm0 = body1.addOrReplaceChild("leftarm0", CubeListBuilder.create(),
                PartPose.offset(4.0F, 1.2F, 0.0F));
        leftarm0.addOrReplaceChild("leftarm1", CubeListBuilder.create()
                .texOffs(32, 16).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F)
                .texOffs(48, 16).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.5F)),
                PartPose.ZERO);

        // Body31 (spider abdomen)
        PartDefinition body31 = partdefinition.addOrReplaceChild("body31", CubeListBuilder.create()
                .texOffs(0, 44).addBox(-4.0F, 0.0F, -5.0F, 8.0F, 7.0F, 10.0F)
                .texOffs(36, 44).addBox(-1.0F, 0.0F, -8.0F, 1.0F, 5.0F, 8.0F)
                .texOffs(54, 44).addBox(0.0F, 0.0F, -8.0F, 1.0F, 5.0F, 8.0F)
                .texOffs(72, 44).addBox(-3.0F, 0.0F, 5.0F, 6.0F, 5.0F, 1.0F),
                PartPose.offset(0.0F, 20.0F, 2.0F));

        PartDefinition body4j = body31.addOrReplaceChild("body4j", CubeListBuilder.create()
                .texOffs(0, 61).addBox(-3.0F, 0.0F, 0.0F, 6.0F, 5.0F, 8.0F),
                PartPose.offsetAndRotation(0.0F, 7.0F, -5.0F, 0.0698131F, 0.0F, 0.0F));

        body4j.addOrReplaceChild("body51", CubeListBuilder.create()
                .texOffs(28, 61).addBox(-7.0F, 0.0F, 0.0F, 14.0F, 11.0F, 15.0F)
                .texOffs(86, 61).addBox(-5.0F, 11.0F, 2.0F, 10.0F, 3.0F, 11.0F)
                .texOffs(0, 87).addBox(-3.0F, 14.0F, 4.0F, 6.0F, 2.0F, 7.0F)
                .texOffs(26, 87).addBox(-1.0F, 16.0F, 6.0F, 2.0F, 1.0F, 3.0F),
                PartPose.offsetAndRotation(0.0F, 5.0F, 8.0F, 0.1396263F, 0.0F, 0.0F));

        // Front legs (chelicerae)
        createFrontLeg(body31, "rightleg1", -2.0F, 0.0F, -4.0F, -2.443461F, 0.2268928F, 0.0F, false);
        createFrontLeg(body31, "leftleg1", 2.0F, 0.0F, -4.0F, -2.443461F, -0.2268928F, 0.0F, true);

        // Walking legs
        createWalkingLeg(body31, "rightleg2", -4.0F, 2.5F, -2.5F, false);
        createWalkingLeg(body31, "leftleg2", 4.0F, 2.5F, -2.5F, true);
        createWalkingLeg(body31, "rightleg3", -4.0F, 2.5F, -0.5F, false);
        createWalkingLeg(body31, "leftleg3", 4.0F, 2.5F, -0.5F, true);
        createWalkingLeg(body31, "rightleg4", -4.0F, 2.5F, 1.5F, false);
        createWalkingLeg(body31, "leftleg4", 4.0F, 2.5F, 1.5F, true);
        createWalkingLeg(body31, "rightleg5", -4.0F, 2.5F, 3.5F, false);
        createWalkingLeg(body31, "leftleg5", 4.0F, 2.5F, 3.5F, true);

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    private static void createFrontLeg(PartDefinition parent, String baseName, float x, float y, float z, float xRot, float yRot, float zRot, boolean isLeft) {
        PartDefinition leg0 = parent.addOrReplaceChild(baseName + "1", CubeListBuilder.create()
                .texOffs(isLeft ? 86 : 80, 96).addBox(isLeft ? -1.0F : -2.0F, 0.0F, -2.0F, 3.0F, 7.0F, 4.0F)
                .texOffs(isLeft ? 102 : 96, 96).addBox(isLeft ? -1.0F : -2.0F, 0.0F, -2.0F, 3.0F, 7.0F, 4.0F, new CubeDeformation(0.3F)),
                PartPose.offsetAndRotation(x, y, z, xRot, yRot, zRot));

        leg0.addOrReplaceChild(baseName + "2j", CubeListBuilder.create()
                .texOffs(isLeft ? 88 : 82, 107).addBox(-1.0F, -1.5F, -1.5F, 2.0F, 3.0F, 3.0F),
                PartPose.offsetAndRotation(isLeft ? -0.5F : 0.5F, 7.5F, 0.0F, 1.038471F, 0.0F, 0.0F));

        leg0.addOrReplaceChild(baseName + "2", CubeListBuilder.create()
                .texOffs(isLeft ? 86 : 80, 113).addBox(isLeft ? -2.0F : -1.0F, 0.5F, -1.5F, 3.0F, 8.0F, 3.0F)
                .texOffs(isLeft ? 102 : 96, 113).addBox(isLeft ? -2.0F : -1.0F, 0.5F, -1.5F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.2F)),
                PartPose.offsetAndRotation(isLeft ? -0.5F : 0.5F, 7.5F, 0.0F, 2.076942F, 0.0F, 0.0F));
    }

    private static void createWalkingLeg(PartDefinition parent, String baseName, float x, float y, float z, boolean isLeft) {
        int texU = isLeft ? 110 : 98;
        
        PartDefinition leg0 = parent.addOrReplaceChild(baseName + "0", CubeListBuilder.create(),
                PartPose.offset(x, y, z));

        PartDefinition leg1 = leg0.addOrReplaceChild(baseName + "1", CubeListBuilder.create()
                .texOffs(texU, 54).addBox(isLeft ? -3.0F : 0.0F, 0.0F, -1.5F, 3.0F, 9.0F, 3.0F, new CubeDeformation(0.2F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, isLeft ? -2.635447F : 2.635447F));

        leg1.addOrReplaceChild(baseName + "2j", CubeListBuilder.create()
                .texOffs(texU + 2, 96).addBox(-1.0F, -1.5F, -1.0F, 2.0F, 3.0F, 2.0F),
                PartPose.offsetAndRotation(isLeft ? -1.5F : 1.5F, 9.5F, 0.0F, 0.0F, 0.0F, isLeft ? 1.0210175F : -1.0210175F));

        PartDefinition leg2 = leg1.addOrReplaceChild(baseName + "2", CubeListBuilder.create()
                .texOffs(texU, 66).addBox(-1.5F, 0.5F, -1.5F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.1F)),
                PartPose.offsetAndRotation(isLeft ? -1.5F : 1.5F, 9.5F, 0.0F, 0.0F, 0.0F, isLeft ? 2.0420351F : -2.0420351F));

        leg2.addOrReplaceChild(baseName + "3j", CubeListBuilder.create()
                .texOffs(texU + 2, 101).addBox(-1.0F, -1.5F, -1.0F, 2.0F, 3.0F, 2.0F),
                PartPose.offsetAndRotation(0.0F, 11.0F, 0.0F, 0.0F, 0.0F, isLeft ? 0.392699F : -0.392699F));

        leg2.addOrReplaceChild(baseName + "3", CubeListBuilder.create()
                .texOffs(texU, 79).addBox(-1.5F, 0.5F, -1.5F, 3.0F, 2.0F, 3.0F)
                .texOffs(texU + 1, 84).addBox(-1.5F, 2.5F, -1.0F, 3.0F, 1.0F, 2.0F)
                .texOffs(texU + 2, 87).addBox(isLeft ? -1.5F : -0.5F, 3.5F, -1.0F, 2.0F, 2.0F, 2.0F)
                .texOffs(texU + 3, 91).addBox(isLeft ? -1.5F : -0.5F, 5.5F, -0.5F, 2.0F, 1.0F, 1.0F)
                .texOffs(texU + 4, 93).addBox(isLeft ? -1.5F : 0.5F, 6.5F, -0.5F, 1.0F, 2.0F, 1.0F),
                PartPose.offsetAndRotation(0.0F, 11.0F, 0.0F, 0.0F, 0.0F, isLeft ? 0.7853981F : -0.7853981F));
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        // Head rotation
        this.head1.xRot = headPitch * ((float)Math.PI / 180F);
        this.head1.yRot = netHeadYaw * ((float)Math.PI / 180F);

        // Arm movement
        this.rightarm0.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F;
        this.leftarm0.xRot = Mth.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
        this.rightarm0.xRot += Mth.sin(ageInTicks * 0.067F) * 0.05F;
        this.leftarm0.xRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F;

        // Sitting animation
        if (entity instanceof EntityArachneMedium arachneMedium && arachneMedium.isInSittingPose()) {
            this.rightarm0.xRot = 0.0F;
            this.rightarm0.yRot = 0.0F;
            this.rightarm0.zRot = 0.0F;
            this.rightarm1.xRot = -0.3839724F;
            this.rightarm1.yRot = -0.0872665F;
            this.rightarm1.zRot = -0.2443461F;
            this.leftarm0.xRot = 0.0F;
            this.leftarm0.yRot = 0.0F;
            this.leftarm0.zRot = 0.0F;
            this.leftarm1.xRot = -0.3839724F;
            this.leftarm1.yRot = 0.1047198F;
            this.leftarm1.zRot = 0.2443461F;
        }

        // Attack animation for front legs
        if (entity instanceof EntityArachneMedium arachneMedium && arachneMedium.getAttackTimer() > 0) {
            int attackTimer = arachneMedium.getAttackTimer();
            float attackAnim = -0.25F + 0.4F * triangleWave(attackTimer - ageInTicks, 6.0F);
            this.rightleg11.xRot = -attackAnim - 2.059489F;
            this.rightleg12.xRot = attackAnim + 1.343904F;
            if (attackTimer < 10) {
                float attackAnim2 = -0.25F + 0.4F * triangleWave((float)attackTimer + 3 - ageInTicks, 6.0F);
                this.leftleg11.xRot = -attackAnim2 - 2.059489F;
                this.leftleg12.xRot = attackAnim2 + 1.343904F;
            }
        } else {
            this.rightleg11.xRot = -2.059489F;
            this.leftleg11.xRot = -2.059489F;
            this.rightleg12.xRot = 1.343904F;
            this.leftleg12.xRot = 1.343904F;
        }

        // Normal arm position when not sitting
        if (!(entity instanceof EntityArachneMedium arachneMedium && arachneMedium.isInSittingPose())) {
            this.rightarm1.xRot = 0.0F;
            this.rightarm1.yRot = 0.0F;
            this.rightarm1.zRot = 0.2443461F;
            this.leftarm1.xRot = 0.0F;
            this.leftarm1.yRot = 0.0F;
            this.leftarm1.zRot = -0.2443461F;
        }

        // Complex spider leg walking animation for medium arachne
        float legAngle = -2.460914F;
        float walkAnim1 = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        float walkAnim2 = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        float walkAnim3 = Mth.cos(limbSwing * 0.6662F + (float)Math.PI * 0.5F) * 1.4F * limbSwingAmount;
        float walkAnim4 = Mth.cos(limbSwing * 0.6662F + (float)Math.PI * 1.5F) * 1.4F * limbSwingAmount;

        // Apply walking animation to all 8 legs
        this.rightleg21.xRot = walkAnim1;
        this.leftleg21.xRot = walkAnim2;
        this.rightleg31.xRot = walkAnim2;
        this.leftleg31.xRot = walkAnim1;
        this.rightleg41.xRot = walkAnim3;
        this.leftleg41.xRot = walkAnim4;
        this.rightleg51.xRot = walkAnim4;
        this.leftleg51.xRot = walkAnim3;

        // Y rotation for leg spread
        this.rightleg20.yRot = legAngle + walkAnim1 * 0.5F;
        this.leftleg20.yRot = -legAngle - walkAnim1 * 0.5F;
        this.rightleg40.yRot = legAngle + walkAnim2 * 0.5F;
        this.leftleg40.yRot = -legAngle - walkAnim2 * 0.5F;
        this.rightleg30.yRot = legAngle + walkAnim3 * 0.5F;
        this.leftleg30.yRot = -legAngle - walkAnim3 * 0.5F;
        this.rightleg50.yRot = legAngle + walkAnim4 * 0.5F;
        this.leftleg50.yRot = -legAngle - walkAnim4 * 0.5F;

        // Wink animation
        if (entity instanceof EntityArachneMedium arachneMedium && arachneMedium.getWinkTimer() > 0) {
            this.face.zRot = 0.1F;
        } else {
            this.face.zRot = 0.0F;
        }
    }

    private static float triangleWave(float p_78172_, float p_78173_) {
        return (Math.abs(p_78172_ % p_78173_ - p_78173_ * 0.5F) - p_78173_ * 0.25F) / (p_78173_ * 0.25F);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        body31.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}

