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
public class Modelshadow_goat<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("illager_universe", "modelshadow_goat"), "main");
	public final ModelPart All;
	public final ModelPart right_back_leg;
	public final ModelPart left_back_leg;
	public final ModelPart right_front_leg;
	public final ModelPart left_front_leg;
	public final ModelPart body;
	public final ModelPart trueHead;
	public final ModelPart head;
	public final ModelPart mirror;
	public final ModelPart left_horn;
	public final ModelPart nose;
	public final ModelPart right_horn2;

	public Modelshadow_goat(ModelPart root) {
		this.All = root.getChild("All");
		this.right_back_leg = this.All.getChild("right_back_leg");
		this.left_back_leg = this.All.getChild("left_back_leg");
		this.right_front_leg = this.All.getChild("right_front_leg");
		this.left_front_leg = this.All.getChild("left_front_leg");
		this.body = this.All.getChild("body");
		this.trueHead = this.body.getChild("trueHead");
		this.head = this.trueHead.getChild("head");
		this.mirror = this.head.getChild("mirror");
		this.left_horn = this.head.getChild("left_horn");
		this.nose = this.head.getChild("nose");
		this.right_horn2 = this.head.getChild("right_horn2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition All = partdefinition.addOrReplaceChild("All", CubeListBuilder.create(), PartPose.offset(-3.0F, 5.0F, -9.5F));
		PartDefinition right_back_leg = All.addOrReplaceChild("right_back_leg", CubeListBuilder.create().texOffs(49, 29).addBox(-1.5F, 4.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 9.0F, 15.0F));
		PartDefinition left_back_leg = All.addOrReplaceChild("left_back_leg", CubeListBuilder.create().texOffs(36, 29).addBox(-1.5F, 4.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 9.0F, 15.0F));
		PartDefinition right_front_leg = All.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(49, 2).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 9.0F, 5.0F));
		PartDefinition left_front_leg = All.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(35, 2).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 9.0F, 5.0F));
		PartDefinition body = All.addOrReplaceChild("body",
				CubeListBuilder.create().texOffs(1, 1).addBox(-4.5F, -5.75F, -6.25F, 9.0F, 11.0F, 16.0F, new CubeDeformation(0.0F)).texOffs(0, 28).addBox(-5.5F, -6.75F, -7.25F, 11.0F, 14.0F, 11.0F, new CubeDeformation(0.0F)),
				PartPose.offset(3.0F, 7.75F, 8.75F));
		PartDefinition trueHead = body.addOrReplaceChild("trueHead", CubeListBuilder.create(), PartPose.offset(0.0F, -7.75F, -8.75F));
		PartDefinition head = trueHead.addOrReplaceChild("head",
				CubeListBuilder.create().texOffs(23, 52).addBox(0.0F, 7.0F, -4.5F, 0.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(2, 61).addBox(-5.5F, 0.0F, 0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition mirror = head.addOrReplaceChild("mirror", CubeListBuilder.create().texOffs(2, 61).mirror().addBox(-0.5F, 0.0F, 0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(3.0F, 0.0F, 0.0F));
		PartDefinition left_horn = head.addOrReplaceChild("left_horn",
				CubeListBuilder.create().texOffs(12, 55).addBox(-1.0F, -6.25F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(12, 55).addBox(-1.0F, -6.25F, 1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.49F, 1.25F, 0.5F, 0.4363F, -0.5236F, 0.1745F));
		PartDefinition nose = head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(34, 46).addBox(-3.0F, -4.0F, -8.0F, 5.0F, 7.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 2.0F, 1.5F, 1.0036F, 0.0F, 0.0F));
		PartDefinition right_horn2 = head.addOrReplaceChild("right_horn2", CubeListBuilder.create().texOffs(12, 55).mirror().addBox(-1.0F, -6.25F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(12, 55).mirror()
				.addBox(-1.0F, -6.25F, 1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.49F, 1.25F, 0.5F, 0.4363F, 0.5236F, -0.1745F));
		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		All.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.trueHead.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.trueHead.xRot = headPitch / (180F / (float) Math.PI);
	}
}