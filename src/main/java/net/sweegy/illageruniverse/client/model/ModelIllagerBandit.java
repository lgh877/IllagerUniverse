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
public class ModelIllagerBandit<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("illager_universe", "model_illager_bandit"), "main");
	public final ModelPart Torso;
	public final ModelPart jacket;
	public final ModelPart body_armor;
	public final ModelPart trueHead;
	public final ModelPart Head;
	public final ModelPart hat;
	public final ModelPart LeftLeg;
	public final ModelPart RightLeg;
	public final ModelPart RightArm;
	public final ModelPart right_hand;
	public final ModelPart LeftArm;
	public final ModelPart left_hand;

	public ModelIllagerBandit(ModelPart root) {
		this.Torso = root.getChild("Torso");
		this.jacket = this.Torso.getChild("jacket");
		this.body_armor = this.Torso.getChild("body_armor");
		this.trueHead = this.Torso.getChild("trueHead");
		this.Head = this.trueHead.getChild("Head");
		this.hat = this.Head.getChild("hat");
		this.LeftLeg = this.Torso.getChild("LeftLeg");
		this.RightLeg = this.Torso.getChild("RightLeg");
		this.RightArm = this.Torso.getChild("RightArm");
		this.right_hand = this.RightArm.getChild("right_hand");
		this.LeftArm = this.Torso.getChild("LeftArm");
		this.left_hand = this.LeftArm.getChild("left_hand");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition Torso = partdefinition.addOrReplaceChild("Torso", CubeListBuilder.create(), PartPose.offset(0.0F, 7.5F, 0.0F));
		PartDefinition jacket = Torso.addOrReplaceChild("jacket", CubeListBuilder.create().texOffs(0, 38).addBox(-4.0F, -24.0F, -4.0F, 8.0F, 18.0F, 6.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 16.5F, 1.0F));
		PartDefinition body_armor = Torso.addOrReplaceChild("body_armor", CubeListBuilder.create().texOffs(16, 20).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.5F, 0.0F));
		PartDefinition trueHead = Torso.addOrReplaceChild("trueHead", CubeListBuilder.create(), PartPose.offset(0.0F, -7.5F, 0.0F));
		PartDefinition Head = trueHead.addOrReplaceChild("Head",
				CubeListBuilder.create().texOffs(24, 0).addBox(-1.0F, -3.0F, -6.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition hat = Head.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -34.0F, -5.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.45F)), PartPose.offset(0.0F, 24.0F, 1.0F));
		PartDefinition LeftLeg = Torso.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(0, 22).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.0F, 4.5F, 0.0F));
		PartDefinition RightLeg = Torso.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(0, 22).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 4.5F, 0.0F));
		PartDefinition RightArm = Torso.addOrReplaceChild("RightArm",
				CubeListBuilder.create().texOffs(56, 46).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)).texOffs(40, 46).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-5.0F, -5.5F, 0.0F));
		PartDefinition right_hand = RightArm.addOrReplaceChild("right_hand", CubeListBuilder.create(), PartPose.offset(-1.0F, 0.0F, 0.0F));
		PartDefinition LeftArm = Torso.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(56, 46).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)).mirror(false).texOffs(40, 46).mirror()
				.addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(5.0F, -5.5F, 0.0F));
		PartDefinition left_hand = LeftArm.addOrReplaceChild("left_hand", CubeListBuilder.create(), PartPose.offset(1.0F, 0.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Torso.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.trueHead.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.trueHead.xRot = headPitch / (180F / (float) Math.PI);
	}
}