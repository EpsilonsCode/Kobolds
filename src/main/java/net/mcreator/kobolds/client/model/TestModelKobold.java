package net.mcreator.kobolds.client.model;

import com.ibm.icu.text.MessagePattern;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Mob;

public class TestModelKobold <T extends Mob> extends HumanoidModel<T> {
    public TestModelKobold(ModelPart p_170821_) {
        super(p_170821_);
    }

    public static LayerDefinition createBodyLayer() {
        CubeDeformation p_170812_ = CubeDeformation.NONE;
        MeshDefinition meshdefinition = PlayerModel.createMesh(p_170812_, false);
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition hat = partdefinition.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(0, 0), PartPose.offset(2.0F, 14.0F, 0.0F));

        PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 31).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 14.0F, 0.0F));

        PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(13, 31).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 14.0F, 0.0F));

        PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(33, 16).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(3.5F, 5.85F, 0.0F));

        PartDefinition righ_tarm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(46, 16).addBox(-0.5F, 0.0F, -1.5F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 5.85F, 0.0F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -7.0F, -3.5F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(22, 0).addBox(-2.5F, -3.0F, -6.5F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(1, 3).addBox(-0.5F, -3.85F, -5.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 4.0F, -0.5F));

        PartDefinition lefthorn_r1 = head.addOrReplaceChild("lefthorn_r1", CubeListBuilder.create().texOffs(36, 0).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -7.0F, 2.0F, -0.6109F, 0.3054F, 0.1745F));

        PartDefinition righthorn_r1 = head.addOrReplaceChild("righthorn_r1", CubeListBuilder.create().texOffs(45, 0).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -7.0F, 2.0F, -0.6109F, -0.3054F, -0.1745F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(3, 15).addBox(-3.0F, -9.0F, -2.0F, 6.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 14.0F, 0.0F));

        PartDefinition tail_r1 = body.addOrReplaceChild("tail_r1", CubeListBuilder.create().texOffs(24, 15).addBox(-1.0F, -3.0F, 2.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.6109F, 0.0F, 0.0F));
/*
        PartDefinition head = partdefinition.addOrReplaceChild("head",
                CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -7.0F, -3.5F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)).texOffs(22, 0)
                        .addBox(-2.5F, -3.0F, -6.5F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(1, 3)
                        .addBox(-0.5F, -3.85F, -5.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, -10.0F, -0.5F));

        PartDefinition lefthorn_r1 = head.addOrReplaceChild("lefthorn_r1",
                CubeListBuilder.create().texOffs(36, 0).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(2.0F, -7.0F, 2.0F, -0.6109F, 0.3054F, 0.1745F));
        PartDefinition righthorn_r1 = head.addOrReplaceChild("righthorn_r1",
                CubeListBuilder.create().texOffs(45, 0).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-2.0F, -7.0F, 2.0F, -0.6109F, -0.3054F, -0.1745F));
        PartDefinition tail_r1 = partdefinition.getChild("body").addOrReplaceChild("tail_r1",
                CubeListBuilder.create().texOffs(24, 15).addBox(-1.0F, -3.0F, 2.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.6109F, 0.0F, 0.0F));
        /*
                PartDefinition body = partdefinition.addOrReplaceChild("body",
                CubeListBuilder.create().texOffs(3, 15).addBox(-3.0F, -10.0F, -2.0F, 6.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.5F, 14.0F, 0.0F));
        PartDefinition rightarm = body.addOrReplaceChild("right_arm",
                CubeListBuilder.create().texOffs(46, 16).addBox(-3.0F, -0.85F, -1.5F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-3.0F, -9.0F, 0.0F));
        PartDefinition leftarm = body.addOrReplaceChild("left_arm",
                CubeListBuilder.create().texOffs(33, 16).addBox(0.0F, -0.85F, -1.5F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offset(3.0F, -9.0F, 0.0F));
        PartDefinition rightleg = body.addOrReplaceChild("right_leg",
                CubeListBuilder.create().texOffs(13, 31).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-1.5F, 0.0F, 0.0F));
        PartDefinition leftleg = body.addOrReplaceChild("left_leg",
                CubeListBuilder.create().texOffs(0, 31).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offset(1.5F, 0.0F, 0.0F));



         */
        return LayerDefinition.create(meshdefinition, 64, 64);
    }
    @Override
    public void renderToBuffer(PoseStack p_102034_, VertexConsumer p_102035_, int p_102036_, int p_102037_, float p_102038_, float p_102039_, float p_102040_, float p_102041_) {
        //super.renderToBuffer(p_102034_, p_102035_, p_102036_, p_102037_, p_102038_, p_102039_, p_102040_, p_102041_);
        p_102034_.pushPose();
        p_102034_.translate(0, 0.12, 0);
        this.head.render(p_102034_, p_102035_, p_102036_, p_102037_, p_102038_, p_102039_, p_102040_, p_102041_);
        p_102034_.popPose();
        this.leftArm.render(p_102034_, p_102035_, p_102036_, p_102037_, p_102038_, p_102039_, p_102040_, p_102041_);
        this.rightArm.render(p_102034_, p_102035_, p_102036_, p_102037_, p_102038_, p_102039_, p_102040_, p_102041_);
        this.leftLeg.render(p_102034_, p_102035_, p_102036_, p_102037_, p_102038_, p_102039_, p_102040_, p_102041_);
        this.rightLeg.render(p_102034_, p_102035_, p_102036_, p_102037_, p_102038_, p_102039_, p_102040_, p_102041_);
        p_102034_.pushPose();
        p_102034_.translate(0, 0.7, 0);
        this.body.render(p_102034_, p_102035_, p_102036_, p_102037_, p_102038_, p_102039_, p_102040_, p_102041_);

        p_102034_.popPose();
    }
}
