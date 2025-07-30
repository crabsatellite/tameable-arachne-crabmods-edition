package com.crabmods.tameable_arachne.model;

import com.crabmods.tameable_arachne.entity.EntityHarpy;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class ModelHarpy<T extends Entity> extends EntityModel<T> {
    // Model layer location for registration
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath("tameable_arachne", "harpy"), "main");

    private final ModelPart head1;
    private final ModelPart face;
    private final ModelPart body1;
    private final ModelPart tail;
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
    private final ModelPart rightleg0;
    private final ModelPart rightleg1;
    private final ModelPart rightleg2;
    private final ModelPart rightleg4;
    private final ModelPart rightleg5;
    private final ModelPart rightleg6;
    private final ModelPart rightleg7;
    private final ModelPart rightleg8;
    private final ModelPart leftleg0;
    private final ModelPart leftleg1;
    private final ModelPart leftleg2;
    private final ModelPart leftleg4;
    private final ModelPart leftleg5;
    private final ModelPart leftleg6;
    private final ModelPart leftleg7;
    private final ModelPart leftleg8;

    public ModelHarpy(ModelPart root) {
        this.body1 = root.getChild("body1");
        this.head1 = this.body1.getChild("head1");
        this.face = this.head1.getChild("face");
        this.tail = this.body1.getChild("tail");
        this.rightbreast = this.body1.getChild("rightbreast");
        this.leftbreast = this.body1.getChild("leftbreast");
        
        this.rightarm0 = this.body1.getChild("rightarm0");
        this.rightarm1 = this.rightarm0.getChild("rightarm1");
        this.rightarm2 = this.rightarm1.getChild("rightarm2");
        this.rightarm3 = this.rightarm1.getChild("rightarm3");
        this.rightarm4 = this.rightarm2.getChild("rightarm4");
        
        this.leftarm0 = this.body1.getChild("leftarm0");
        this.leftarm1 = this.leftarm0.getChild("leftarm1");
        this.leftarm2 = this.leftarm1.getChild("leftarm2");
        this.leftarm3 = this.leftarm1.getChild("leftarm3");
        this.leftarm4 = this.leftarm2.getChild("leftarm4");
        
        this.rightleg0 = this.body1.getChild("rightleg0");
        this.rightleg1 = this.rightleg0.getChild("rightleg1");
        this.rightleg2 = this.rightleg1.getChild("rightleg2");
        this.rightleg4 = this.rightleg2.getChild("rightleg4");
        this.rightleg5 = this.rightleg4.getChild("rightleg5");
        this.rightleg6 = this.rightleg5.getChild("rightleg6");
        this.rightleg7 = this.rightleg5.getChild("rightleg7");
        this.rightleg8 = this.rightleg5.getChild("rightleg8");

        this.leftleg0 = this.body1.getChild("leftleg0");
        this.leftleg1 = this.leftleg0.getChild("leftleg1");
        this.leftleg2 = this.leftleg1.getChild("leftleg2");
        this.leftleg4 = this.leftleg2.getChild("leftleg4");
        this.leftleg5 = this.leftleg4.getChild("leftleg5");
        this.leftleg6 = this.leftleg5.getChild("leftleg6");
        this.leftleg7 = this.leftleg5.getChild("leftleg7");
        this.leftleg8 = this.leftleg5.getChild("leftleg8");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        // Body - positioned like in 1.12.2
        PartDefinition body1 = partdefinition.addOrReplaceChild("body1", CubeListBuilder.create()
                .texOffs(0, 32).addBox(-3F, 0F, -2F, 6, 9, 4)
                .texOffs(0, 45).addBox(-3.5F, 9F, -2F, 7, 2, 4),
                PartPose.offset(0F, -2F, 0F));

        // Head attached to body1 like in 1.12.2 - fixed texture mapping
        PartDefinition head1 = body1.addOrReplaceChild("head1", CubeListBuilder.create()
                .texOffs(0, 0).addBox(-4F, -8F, -4F, 8, 8, 8)  // Upper head
                .texOffs(32, 0).addBox(-4F, -8F, -4F, 8, 8, 8, new CubeDeformation(0.5F)), // Hair/hat layer
                PartPose.offset(0F, 0F, 0F));

        // Face attached to head - simplified like standard player model
        head1.addOrReplaceChild("face", CubeListBuilder.create()
                .texOffs(24, 0).addBox(-4F, -8F, -4.01F, 8, 8, 0),
                PartPose.ZERO);

        // Tail attached to body1
        body1.addOrReplaceChild("tail", CubeListBuilder.create()
                .texOffs(110, 8).addBox(-4.5F, 0F, 0F, 9, 12, 0),
                PartPose.offsetAndRotation(0F, 9F, 2F, 0.6283185F, 0F, 0F));

        // Breasts attached to body1
        body1.addOrReplaceChild("rightbreast", CubeListBuilder.create()
                .texOffs(20, 32).addBox(-3F, 0F, 0F, 3, 3, 3),
                PartPose.offsetAndRotation(-0.1F, 0.8F, -2F, -0.6108652F, 0.0349066F, 0.0872665F));

        body1.addOrReplaceChild("leftbreast", CubeListBuilder.create()
                .texOffs(20, 38).addBox(0F, 0F, 0F, 3, 3, 3),
                PartPose.offsetAndRotation(0.1F, 0.8F, -2F, -0.6108652F, -0.0349066F, -0.0872665F));

        // Right wing/arm system - exactly like 1.12.2
        PartDefinition rightarm0 = body1.addOrReplaceChild("rightarm0", CubeListBuilder.create(),
                PartPose.offset(-3F, 1.5F, 0F));

        PartDefinition rightarm1 = rightarm0.addOrReplaceChild("rightarm1", CubeListBuilder.create()
                .texOffs(0, 51).addBox(-2F, -1F, -1F, 2, 11, 2),
                PartPose.rotation(0F, 0F, 0.1570796F));

        PartDefinition rightarm2 = rightarm1.addOrReplaceChild("rightarm2", CubeListBuilder.create()
                .texOffs(16, 51).addBox(-1F, 0F, 0F, 2, 4, 2),
                PartPose.offsetAndRotation(-1F, 10F, -1F, 0.6283185F, 0F, 0F));

        rightarm1.addOrReplaceChild("rightarm3", CubeListBuilder.create()
                .texOffs(96, 22).addBox(0F, 0F, 0F, 0, 10, 7),
                PartPose.offset(-1F, 0F, 0F));

        rightarm2.addOrReplaceChild("rightarm4", CubeListBuilder.create()
                .texOffs(110, 20).addBox(-0.01F, 0F, -1F, 0, 13, 9),
                PartPose.offset(0F, 0F, 1F));

        // Left wing/arm system - exactly like 1.12.2
        PartDefinition leftarm0 = body1.addOrReplaceChild("leftarm0", CubeListBuilder.create(),
                PartPose.offset(3F, 1.5F, 0F));

        PartDefinition leftarm1 = leftarm0.addOrReplaceChild("leftarm1", CubeListBuilder.create()
                .texOffs(8, 51).addBox(0F, -1F, -1F, 2, 11, 2),
                PartPose.rotation(0F, 0F, -0.1570796F));

        PartDefinition leftarm2 = leftarm1.addOrReplaceChild("leftarm2", CubeListBuilder.create()
                .texOffs(16, 57).addBox(-1F, 0F, 0F, 2, 4, 2),
                PartPose.offsetAndRotation(1F, 10F, -1F, 0.6283185F, 0F, 0F));

        leftarm1.addOrReplaceChild("leftarm3", CubeListBuilder.create()
                .texOffs(96, 44).addBox(0F, 0F, 0F, 0, 10, 7),
                PartPose.offset(1F, 0F, 0F));

        leftarm2.addOrReplaceChild("leftarm4", CubeListBuilder.create()
                .texOffs(110, 42).addBox(0.01F, 0F, -1F, 0, 13, 9),
                PartPose.offset(0F, 0F, 1F));

        // Right bird leg - exactly like 1.12.2
        createBirdLeg(body1, "rightleg", -2F, 11F, 0F, 64, true);

        // Left bird leg - exactly like 1.12.2
        createBirdLeg(body1, "leftleg", 2F, 11F, 0F, 80, false);

        return LayerDefinition.create(meshdefinition, 128, 64);
    }

    private static void createBirdLeg(PartDefinition parent, String legName, float x, float y, float z, int texU, boolean isRight) {
        PartDefinition leg0 = parent.addOrReplaceChild(legName + "0", CubeListBuilder.create(),
                PartPose.offset(x, y, z));

        PartDefinition leg1 = leg0.addOrReplaceChild(legName + "1", CubeListBuilder.create()
                .texOffs(texU, 0).addBox(-2F, 0F, -2F, 4, 7, 4),
                PartPose.rotation(-0.1570796F, 0F, 0F));

        PartDefinition leg2 = leg1.addOrReplaceChild(legName + "2", CubeListBuilder.create()
                .texOffs(texU + 3, 11).addBox(-1.5F, 0F, 0F, 3, 2, 2)
                .texOffs(texU + 4, 15).addBox(-1F, 2F, 0F, 2, 2, 2),
                PartPose.offsetAndRotation(0F, 7F, -2F, 0.9424778F, 0F, 0F));

        PartDefinition leg4 = leg2.addOrReplaceChild(legName + "4", CubeListBuilder.create()
                .texOffs(texU + 4, 19).addBox(-1F, 0F, -2F, 2, 6, 2),
                PartPose.offsetAndRotation(0F, 4F, 2F, -0.8726647F, 0F, 0F));

        PartDefinition leg5 = leg4.addOrReplaceChild(legName + "5", CubeListBuilder.create()
                .texOffs(texU + 5, 27).addBox(-1F, 0F, 0F, 2, 3, 1),
                PartPose.offsetAndRotation(0F, 6F, 0F, -1.4835295F, 0F, 0F));

        // Talons/claws
        leg5.addOrReplaceChild(legName + "6", CubeListBuilder.create()
                .texOffs(texU + 6, 37).addBox(-0.5F, -2F, 0F, 1, 8, 1),
                PartPose.ZERO);

        leg5.addOrReplaceChild(legName + "7", CubeListBuilder.create()
                .texOffs(texU + 4, 31).addBox(-0.5F, 0F, 0F, 1, 5, 1),
                PartPose.offsetAndRotation(-0.5F, 0.5F, 0F, 0F, 0F, 0.1919862F));

        leg5.addOrReplaceChild(legName + "8", CubeListBuilder.create()
                .texOffs(texU + 8, 31).addBox(-0.5F, 0F, 0F, 1, 5, 1),
                PartPose.offsetAndRotation(0.5F, 0.5F, 0F, 0F, 0F, -0.1919862F));
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        // Head rotation - exactly like 1.12.2
        this.head1.xRot = headPitch / (180F / (float) Math.PI);
        this.head1.yRot = netHeadYaw / (180F / (float) Math.PI);

        // Check if entity is Harpy and handle special animations
        if (entity instanceof EntityHarpy entityharpy) {
            // Handle sitting animation - exactly like 1.12.2
            if (entityharpy.isInSittingPose()) {
                setRotation(rightarm0, 0F, 0F, 0F);
                setRotation(leftarm0, 0F, 0F, 0F);
                setRotation(rightarm1, -0.4712389F, -0.4014257F, -0.122173F);
                setRotation(leftarm1, -0.4712389F, 0.4014257F, 0.122173F);
                setRotation(rightarm3, 0F, -0.7853982F, 0F);
                setRotation(leftarm3, 0F, 0.7853982F, 0F);
                setRotation(rightarm4, 0F, -0.7853982F, 0F);
                setRotation(leftarm4, 0F, 0.7853982F, 0F);
            } else {
                setRotation(rightarm1, 0F, 0F, 0.1570796F);
                setRotation(leftarm1, 0F, 0F, -0.1570796F);
                setRotation(rightarm3, 0F, 0F, 0F);
                setRotation(leftarm3, 0F, 0F, 0F);
                setRotation(rightarm4, 0F, 0F, 0F);
                setRotation(leftarm4, 0F, 0F, 0F);
            }

            // Wing animation when not sitting - like Minecraft chicken
            if (!entityharpy.isInSittingPose()) {
                this.rightarm0.xRot = Mth.cos(limbSwing * 0.7F + (float) Math.PI) * 1.2F * limbSwingAmount * 0.5F;
                this.leftarm0.xRot = Mth.cos(limbSwing * 0.7F) * 1.2F * limbSwingAmount * 0.5F;
                this.rightarm0.xRot += Mth.sin(ageInTicks * 0.07F) * 0.05F;
                this.leftarm0.xRot -= Mth.sin(ageInTicks * 0.07F) * 0.05F;

                // Wing flapping animation - only when not on ground (like chicken)
                if (!entityharpy.onGround()) {
                    // Fast wing flapping when falling/flying with limited angle to prevent clipping
                    float wingAngle = Mth.cos(ageInTicks * 1.2F) * 1.1F; // Increased amplitude
                    // Ensure wings only extend outward, never inward toward body
                    wingAngle = Math.abs(wingAngle); // Always positive to prevent inward folding
                    this.rightarm0.zRot = wingAngle; // Right wing extends right
                    this.leftarm0.zRot = -wingAngle; // Left wing extends left
                } else {
                    // Wings folded when on ground
                    this.rightarm0.zRot = 0F;
                    this.leftarm0.zRot = 0F;
                }
            }
        }

        // Leg walking animation - exactly like 1.12.2
        this.rightleg0.xRot = Mth.cos(limbSwing * 0.7F) * 1.1F * limbSwingAmount;
        this.leftleg0.xRot = Mth.cos(limbSwing * 0.7F + (float) Math.PI) * 1.1F * limbSwingAmount;
    }

    private void setRotation(ModelPart model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        // Handle wink animation before rendering - exactly like 1.12.2
        // This needs to be called here to match the 1.12.2 render method behavior

        // Scale down like in 1.12.2 (0.7x scale)
        poseStack.pushPose();
        poseStack.scale(0.7F, 0.7F, 0.7F);
        poseStack.translate(0.0F, 10.0F / 16.0F, 0.0F);

        // Render the model - exactly like 1.12.2 where head1 and body1 are rendered separately
        head1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        body1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);

        poseStack.popPose();
    }

    // Method to handle entity-specific rendering like wink animation - call before renderToBuffer
    public void prepareForRender(Entity entity) {
        if (entity instanceof EntityHarpy entityharpy) {
            // Handle wink animation exactly like 1.12.2
            if (entityharpy.getWinkTimer() > 0) {
                face.visible = false;
            } else {
                face.visible = true;
            }
        }
    }
}
