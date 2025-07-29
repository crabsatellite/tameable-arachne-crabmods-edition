package com.crabmods.tameable_arachne.model;

import com.crabmods.tameable_arachne.entity.EntityHarpy;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

public class ModelHarpy<T extends Entity> extends EntityModel<T> {
    // All model parts
    private final ModelPart head1;
    private final ModelPart face;
    private final ModelPart rightbreast;
    private final ModelPart leftbreast;
    private final ModelPart rightarm0;
    private final ModelPart rightarm1;
    private final ModelPart rightarm2;
    private final ModelPart rightarm3;
    private final ModelPart rightarm4;
    private final ModelPart leftarm0;
    private final ModelPart leftarm1;
    private final ModelPart leftarm2;
    private final ModelPart leftarm3;
    private final ModelPart leftarm4;
    private final ModelPart body1;
    private final ModelPart tail;
    
    // Bird legs
    private final ModelPart rightleg0;
    private final ModelPart rightleg1;
    private final ModelPart rightleg2;
    private final ModelPart rightleg3;
    private final ModelPart rightleg4;
    private final ModelPart rightleg5;
    private final ModelPart rightleg6;
    private final ModelPart rightleg7;
    private final ModelPart rightleg8;
    
    private final ModelPart leftleg0;
    private final ModelPart leftleg1;
    private final ModelPart leftleg2;
    private final ModelPart leftleg3;
    private final ModelPart leftleg4;
    private final ModelPart leftleg5;
    private final ModelPart leftleg6;
    private final ModelPart leftleg7;
    private final ModelPart leftleg8;

    public ModelHarpy(ModelPart root) {
        this.body1 = root.getChild("body1");
        this.tail = root.getChild("tail");
        
        this.head1 = this.body1.getChild("head1");
        this.face = this.head1.getChild("face");
        this.rightbreast = this.body1.getChild("rightbreast");
        this.leftbreast = this.body1.getChild("leftbreast");
        
        this.rightarm0 = this.body1.getChild("rightarm0");
        this.rightarm1 = this.rightarm0.getChild("rightarm1");
        this.rightarm2 = this.rightarm1.getChild("rightarm2");
        this.rightarm3 = this.rightarm2.getChild("rightarm3");
        this.rightarm4 = this.rightarm2.getChild("rightarm4");
        
        this.leftarm0 = this.body1.getChild("leftarm0");
        this.leftarm1 = this.leftarm0.getChild("leftarm1");
        this.leftarm2 = this.leftarm1.getChild("leftarm2");
        this.leftarm3 = this.leftarm2.getChild("leftarm3");
        this.leftarm4 = this.leftarm2.getChild("leftarm4");
        
        this.rightleg0 = this.body1.getChild("rightleg0");
        this.rightleg1 = this.rightleg0.getChild("rightleg1");
        this.rightleg2 = this.rightleg1.getChild("rightleg2");
        this.rightleg3 = this.rightleg2.getChild("rightleg3");
        this.rightleg4 = this.rightleg3.getChild("rightleg4");
        this.rightleg5 = this.rightleg4.getChild("rightleg5");
        this.rightleg6 = this.rightleg5.getChild("rightleg6");
        this.rightleg7 = this.rightleg6.getChild("rightleg7");
        this.rightleg8 = this.rightleg6.getChild("rightleg8");
        
        this.leftleg0 = this.body1.getChild("leftleg0");
        this.leftleg1 = this.leftleg0.getChild("leftleg1");
        this.leftleg2 = this.leftleg1.getChild("leftleg2");
        this.leftleg3 = this.leftleg2.getChild("leftleg3");
        this.leftleg4 = this.leftleg3.getChild("leftleg4");
        this.leftleg5 = this.leftleg4.getChild("leftleg5");
        this.leftleg6 = this.leftleg5.getChild("leftleg6");
        this.leftleg7 = this.leftleg6.getChild("leftleg7");
        this.leftleg8 = this.leftleg6.getChild("leftleg8");
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
                .texOffs(32, 0).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 8.0F, 8.0F),
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

        // Right wing system
        PartDefinition rightarm0 = body1.addOrReplaceChild("rightarm0", CubeListBuilder.create(),
                PartPose.offset(-4.0F, 1.2F, 0.0F));
        
        PartDefinition rightarm1 = rightarm0.addOrReplaceChild("rightarm1", CubeListBuilder.create()
                .texOffs(24, 16).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1570796F));
        
        PartDefinition rightarm2 = rightarm1.addOrReplaceChild("rightarm2", CubeListBuilder.create()
                .texOffs(32, 16).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F),
                PartPose.offsetAndRotation(0.0F, 12.0F, 0.0F, 0.6283185F, 0.0F, 0.0F));
        
        rightarm2.addOrReplaceChild("rightarm3", CubeListBuilder.create()
                .texOffs(40, 16).addBox(-8.0F, 0.0F, 0.0F, 16.0F, 12.0F, 0.0F),
                PartPose.ZERO);
        
        rightarm2.addOrReplaceChild("rightarm4", CubeListBuilder.create()
                .texOffs(40, 28).addBox(-8.0F, 0.0F, 0.0F, 16.0F, 12.0F, 0.0F),
                PartPose.ZERO);

        // Left wing system
        PartDefinition leftarm0 = body1.addOrReplaceChild("leftarm0", CubeListBuilder.create(),
                PartPose.offset(4.0F, 1.2F, 0.0F));
        
        PartDefinition leftarm1 = leftarm0.addOrReplaceChild("leftarm1", CubeListBuilder.create()
                .texOffs(24, 30).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1570796F));
        
        PartDefinition leftarm2 = leftarm1.addOrReplaceChild("leftarm2", CubeListBuilder.create()
                .texOffs(32, 30).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F),
                PartPose.offsetAndRotation(0.0F, 12.0F, 0.0F, 0.6283185F, 0.0F, 0.0F));
        
        leftarm2.addOrReplaceChild("leftarm3", CubeListBuilder.create()
                .texOffs(72, 16).addBox(-8.0F, 0.0F, 0.0F, 16.0F, 12.0F, 0.0F),
                PartPose.ZERO);
        
        leftarm2.addOrReplaceChild("leftarm4", CubeListBuilder.create()
                .texOffs(72, 28).addBox(-8.0F, 0.0F, 0.0F, 16.0F, 12.0F, 0.0F),
                PartPose.ZERO);

        // Right bird leg
        PartDefinition rightleg0 = body1.addOrReplaceChild("rightleg0", CubeListBuilder.create(),
                PartPose.offset(-2.0F, 12.0F, 0.0F));
        
        PartDefinition rightleg1 = rightleg0.addOrReplaceChild("rightleg1", CubeListBuilder.create()
                .texOffs(0, 44).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1570796F, 0.0F, 0.0F));
        
        PartDefinition rightleg2 = rightleg1.addOrReplaceChild("rightleg2", CubeListBuilder.create()
                .texOffs(8, 44).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F),
                PartPose.offsetAndRotation(0.0F, 8.0F, 0.0F, 0.9424778F, 0.0F, 0.0F));
        
        PartDefinition rightleg3 = rightleg2.addOrReplaceChild("rightleg3", CubeListBuilder.create()
                .texOffs(16, 44).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 6.0F, 1.0F),
                PartPose.offset(0.0F, 8.0F, 0.0F));
        
        PartDefinition rightleg4 = rightleg3.addOrReplaceChild("rightleg4", CubeListBuilder.create()
                .texOffs(20, 44).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F),
                PartPose.offsetAndRotation(0.0F, 6.0F, 0.0F, -0.8726647F, 0.0F, 0.0F));
        
        PartDefinition rightleg5 = rightleg4.addOrReplaceChild("rightleg5", CubeListBuilder.create()
                .texOffs(24, 44).addBox(-0.5F, 0.0F, -2.0F, 1.0F, 1.0F, 4.0F),
                PartPose.offsetAndRotation(0.0F, 4.0F, 0.0F, -1.4835295F, 0.0F, 0.0F));
        
        PartDefinition rightleg6 = rightleg5.addOrReplaceChild("rightleg6", CubeListBuilder.create()
                .texOffs(34, 44).addBox(-1.0F, 0.0F, -3.0F, 2.0F, 1.0F, 3.0F),
                PartPose.offset(0.0F, 1.0F, -2.0F));
        
        rightleg6.addOrReplaceChild("rightleg7", CubeListBuilder.create()
                .texOffs(44, 44).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 1.0F, 1.0F),
                PartPose.offsetAndRotation(0.5F, 1.0F, -3.0F, 0.0F, 0.0F, 0.1919862F));
        
        rightleg6.addOrReplaceChild("rightleg8", CubeListBuilder.create()
                .texOffs(48, 44).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 1.0F, 1.0F),
                PartPose.offsetAndRotation(-0.5F, 1.0F, -3.0F, 0.0F, 0.0F, -0.1919862F));

        // Left bird leg (mirror of right)
        PartDefinition leftleg0 = body1.addOrReplaceChild("leftleg0", CubeListBuilder.create(),
                PartPose.offset(2.0F, 12.0F, 0.0F));
        
        PartDefinition leftleg1 = leftleg0.addOrReplaceChild("leftleg1", CubeListBuilder.create()
                .texOffs(0, 54).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1570796F, 0.0F, 0.0F));
        
        PartDefinition leftleg2 = leftleg1.addOrReplaceChild("leftleg2", CubeListBuilder.create()
                .texOffs(8, 54).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F),
                PartPose.offsetAndRotation(0.0F, 8.0F, 0.0F, 0.9424778F, 0.0F, 0.0F));
        
        PartDefinition leftleg3 = leftleg2.addOrReplaceChild("leftleg3", CubeListBuilder.create()
                .texOffs(16, 54).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 6.0F, 1.0F),
                PartPose.offset(0.0F, 8.0F, 0.0F));
        
        PartDefinition leftleg4 = leftleg3.addOrReplaceChild("leftleg4", CubeListBuilder.create()
                .texOffs(20, 54).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F),
                PartPose.offsetAndRotation(0.0F, 6.0F, 0.0F, -0.8726647F, 0.0F, 0.0F));
        
        PartDefinition leftleg5 = leftleg4.addOrReplaceChild("leftleg5", CubeListBuilder.create()
                .texOffs(24, 54).addBox(-0.5F, 0.0F, -2.0F, 1.0F, 1.0F, 4.0F),
                PartPose.offsetAndRotation(0.0F, 4.0F, 0.0F, -1.4835295F, 0.0F, 0.0F));
        
        PartDefinition leftleg6 = leftleg5.addOrReplaceChild("leftleg6", CubeListBuilder.create()
                .texOffs(34, 54).addBox(-1.0F, 0.0F, -3.0F, 2.0F, 1.0F, 3.0F),
                PartPose.offset(0.0F, 1.0F, -2.0F));
        
        leftleg6.addOrReplaceChild("leftleg7", CubeListBuilder.create()
                .texOffs(44, 54).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 1.0F, 1.0F),
                PartPose.offsetAndRotation(0.5F, 1.0F, -3.0F, 0.0F, 0.0F, 0.1919862F));
        
        leftleg6.addOrReplaceChild("leftleg8", CubeListBuilder.create()
                .texOffs(48, 54).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 1.0F, 1.0F),
                PartPose.offsetAndRotation(-0.5F, 1.0F, -3.0F, 0.0F, 0.0F, -0.1919862F));

        // Tail
        partdefinition.addOrReplaceChild("tail", CubeListBuilder.create()
                .texOffs(52, 44).addBox(-4.5F, 0.0F, 0.0F, 9.0F, 12.0F, 0.0F),
                PartPose.offset(0.0F, 11.0F, 1.5F));

        return LayerDefinition.create(meshdefinition, 128, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        // Head rotation
        this.head1.xRot = headPitch * ((float)Math.PI / 180F);
        this.head1.yRot = netHeadYaw * ((float)Math.PI / 180F);

        // Wing flapping animation
        float wingFlap = Mth.cos(ageInTicks * 0.3F) * 0.6F;
        this.rightarm0.zRot = wingFlap;
        this.leftarm0.zRot = -wingFlap;

        // Sitting animation
        if (entity instanceof EntityHarpy entityharpy && entityharpy.isInSittingPose()) {
            this.rightarm0.xRot = 0F;
            this.rightarm0.yRot = 0F;
            this.rightarm0.zRot = 0F;
            this.leftarm0.xRot = 0F;
            this.leftarm0.yRot = 0F;
            this.leftarm0.zRot = 0F;
            
            this.rightarm1.xRot = -0.4712389F;
            this.rightarm1.yRot = -0.4014257F;
            this.rightarm1.zRot = -0.122173F;
            this.leftarm1.xRot = -0.4712389F;
            this.leftarm1.yRot = 0.4014257F;
            this.leftarm1.zRot = 0.122173F;
            
            this.rightarm3.xRot = 0F;
            this.rightarm3.yRot = -0.7853982F;
            this.rightarm3.zRot = 0F;
            this.leftarm3.xRot = 0F;
            this.leftarm3.yRot = 0.7853982F;
            this.leftarm3.zRot = 0F;
            
            this.rightarm4.xRot = 0F;
            this.rightarm4.yRot = -0.7853982F;
            this.rightarm4.zRot = 0F;
            this.leftarm4.xRot = 0F;
            this.leftarm4.yRot = 0.7853982F;
            this.leftarm4.zRot = 0F;
        } else {
            // Normal wing animation
            this.rightarm0.xRot = Mth.cos(limbSwing * 0.7F + (float)Math.PI) * 1.2F * limbSwingAmount * 0.5F;
            this.leftarm0.xRot = Mth.cos(limbSwing * 0.7F) * 1.2F * limbSwingAmount * 0.5F;
            this.rightarm0.xRot += Mth.sin(ageInTicks * 0.07F) * 0.05F;
            this.leftarm0.xRot -= Mth.sin(ageInTicks * 0.07F) * 0.05F;
            
            // Reset wing positions
            this.rightarm1.xRot = 0F;
            this.rightarm1.yRot = 0F;
            this.rightarm1.zRot = 0.1570796F;
            this.leftarm1.xRot = 0F;
            this.leftarm1.yRot = 0F;
            this.leftarm1.zRot = -0.1570796F;
            
            this.rightarm3.xRot = 0F;
            this.rightarm3.yRot = 0F;
            this.rightarm3.zRot = 0F;
            this.leftarm3.xRot = 0F;
            this.leftarm3.yRot = 0F;
            this.leftarm3.zRot = 0F;
            
            this.rightarm4.xRot = 0F;
            this.rightarm4.yRot = 0F;
            this.rightarm4.zRot = 0F;
            this.leftarm4.xRot = 0F;
            this.leftarm4.yRot = 0F;
            this.leftarm4.zRot = 0F;
        }

        // Attack animation
        if (entity instanceof EntityHarpy entityharpy && entityharpy.getAttackTimer() > 0) {
            float attackAngle = Mth.sin(ageInTicks * 0.5F) * 0.3F;
            this.rightarm0.zRot = attackAngle;
            this.leftarm0.zRot = -attackAngle;
        }

        // Bird leg walking animation
        this.rightleg0.xRot = Mth.cos(limbSwing * 0.7F) * 1.1F * limbSwingAmount;
        this.leftleg0.xRot = Mth.cos(limbSwing * 0.7F + (float)Math.PI) * 1.1F * limbSwingAmount;

        // Wink animation
        if (entity instanceof EntityHarpy entityharpy && entityharpy.getWinkTimer() > 0) {
            this.face.visible = false;
        } else {
            this.face.visible = true;
        }
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        // Scale and translate like original - 0.7x scale with vertical offset
        poseStack.pushPose();
        poseStack.scale(0.7F, 0.7F, 0.7F);
        poseStack.translate(0.0F, 10.0F * (1.0F/16.0F), 0.0F);
        
        body1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        tail.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        
        poseStack.popPose();
    }
}

