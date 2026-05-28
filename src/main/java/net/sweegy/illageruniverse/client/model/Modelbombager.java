package net.sweegy.illageruniverse.client.model;

import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.EntityModel;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

// Made with Blockbench 5.1.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
public class Modelbombager<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("illager_universe", "modelbombager"), "main");
	public final ModelPart body;
	public final ModelPart body_armor;
	public final ModelPart cape;
	public final ModelPart trueHead;
	public final ModelPart head;
	public final ModelPart head_armor;
	public final ModelPart nose;
	public final ModelPart right_leg;
	public final ModelPart left_leg;
	public final ModelPart right_arm;
	public final ModelPart staff;
	public final ModelPart tnt;
	public final ModelPart tnt_block;
	public final ModelPart right_arm_armor;
	public final ModelPart left_arm;
	public final ModelPart left_arm_armor;

	public Modelbombager(ModelPart root) {
		this.body = root.getChild("body");
		this.body_armor = this.body.getChild("body_armor");
		this.cape = this.body.getChild("cape");
		this.trueHead = this.body.getChild("trueHead");
		this.head = this.trueHead.getChild("head");
		this.head_armor = this.head.getChild("head_armor");
		this.nose = this.head.getChild("nose");
		this.right_leg = this.body.getChild("right_leg");
		this.left_leg = this.body.getChild("left_leg");
		this.right_arm = this.body.getChild("right_arm");
		this.staff = this.right_arm.getChild("staff");
		this.tnt = this.staff.getChild("tnt");
		this.tnt_block = this.tnt.getChild("tnt_block");
		this.right_arm_armor = this.right_arm.getChild("right_arm_armor");
		this.left_arm = this.body.getChild("left_arm");
		this.left_arm_armor = this.left_arm.getChild("left_arm_armor");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 20).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition body_armor = body.addOrReplaceChild("body_armor", CubeListBuilder.create().texOffs(0, 38).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 20.0F, 6.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition cape = body.addOrReplaceChild("cape", CubeListBuilder.create().texOffs(0, 75).addBox(-4.0F, 0.0F, -1.0F, 8.0F, 20.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 4.0F));
		PartDefinition trueHead = body.addOrReplaceChild("trueHead", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition head = trueHead.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition head_armor = head.addOrReplaceChild("head_armor", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -34.0F, -4.0F, 8.0F, 12.0F, 8.0F, new CubeDeformation(0.45F)), PartPose.offset(0.0F, 24.0F, 0.0F));
		PartDefinition nose = head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(24, 0).addBox(-1.0F, -1.0F, -6.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));
		PartDefinition right_leg = body.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 22).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 12.0F, 0.0F));
		PartDefinition left_leg = body.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 22).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.0F, 12.0F, 0.0F));
		PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(44, 22).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-5.0F, 2.0F, 0.0F, -1.5708F, 0.0F, 0.0F));
		PartDefinition staff = right_arm.addOrReplaceChild("staff", CubeListBuilder.create().texOffs(56, 38).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, 8.0F, 0.0F, 1.5708F, 0.0F, 0.0F));
		PartDefinition staff_r1 = staff.addOrReplaceChild("staff_r1", CubeListBuilder.create().texOffs(46, 50).addBox(0.0F, -6.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.0F, 0.0F, 0.9599F));
		PartDefinition staff_r2 = staff.addOrReplaceChild("staff_r2", CubeListBuilder.create().texOffs(46, 50).addBox(-2.0F, -6.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.0F, 0.0F, -0.9599F));
		PartDefinition tnt = staff.addOrReplaceChild("tnt", CubeListBuilder.create().texOffs(28, 52).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -9.0F, 0.0F));
		PartDefinition tnt_block = tnt.addOrReplaceChild("tnt_block", CubeListBuilder.create().texOffs(0, 96).addBox(-8.0F, -8.0F, -8.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition right_arm_armor = right_arm.addOrReplaceChild("right_arm_armor", CubeListBuilder.create().texOffs(48, 96).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(44, 22).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(5.0F, 2.0F, 0.0F));
		PartDefinition left_arm_armor = left_arm.addOrReplaceChild("left_arm_armor", CubeListBuilder.create().texOffs(48, 96).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)).mirror(false),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.trueHead.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.trueHead.xRot = headPitch / (180F / (float) Math.PI);
	}
}