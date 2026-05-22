package net.sweegy.illageruniverse.client.renderer;

import net.sweegy.illageruniverse.entity.BanditEntity;
import net.sweegy.illageruniverse.client.model.animations.IllagerBanditAnimation;
import net.sweegy.illageruniverse.client.model.ModelIllagerBandit;
import net.sweegy.illageruniverse.IHierachicalHeadedModel;
import net.sweegy.illageruniverse.HierachicalHeadLayer;
import net.sweegy.illageruniverse.CustomArmorLayer;
import net.sweegy.illageruniverse.ArmorWearingModel;

import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.ArmedModel;

import com.mojang.blaze3d.vertex.PoseStack;

public class BanditRenderer extends MobRenderer<BanditEntity, BanditRenderer.AnimatedModel> {
	private final ResourceLocation entityTexture = new ResourceLocation("illager_universe:textures/entities/bandit.png");

	public BanditRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(ModelIllagerBandit.LAYER_LOCATION)), 0.5f);
		addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()));
		addLayer(new HierachicalHeadLayer<>(this, context.getModelSet(), context.getItemInHandRenderer()));
		CustomArmorLayer.addCustomArmorLayers(this, context, ModelLayers.PLAYER_INNER_ARMOR, ModelLayers.PLAYER_OUTER_ARMOR);
	}

	@Override
	public ResourceLocation getTextureLocation(BanditEntity entity) {
		return entityTexture;
	}

	public static final class AnimatedModel extends ModelIllagerBandit<BanditEntity> implements ArmedModel, IHierachicalHeadedModel, ArmorWearingModel {
		private final ModelPart root;
		private final HierarchicalModel animator = new HierarchicalModel<BanditEntity>() {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(BanditEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				float partialTicks = ageInTicks - entity.tickCount;
				float walkTransitionAmplitude = Mth.lerp(partialTicks, entity.walkTransitionAmplitudeO, entity.walkTransitionAmplitude) / 3F;
				this.animate(entity.animationState0, IllagerBanditAnimation.idle, ageInTicks, 1f);
				this.animateWalk(IllagerBanditAnimation.walk, limbSwing, limbSwingAmount * walkTransitionAmplitude, 1f, 2f);
				this.animateWalk(IllagerBanditAnimation.aggro, limbSwing, limbSwingAmount * (1 - walkTransitionAmplitude), 1f, 2f);
				if (entity.getActionState() != 0) {
					float animTicks = Mth.lerp(partialTicks, entity.animTicksO, entity.animTicks);
					this.animateWalk(entity.currentAnimation, animTicks, 1f, 1f, 1f);
				}
			}
		};

		public void translateToHand(HumanoidArm arm, PoseStack poseStack) {
			Torso.translateAndRotate(poseStack);
			if (arm == HumanoidArm.LEFT) {
				LeftArm.translateAndRotate(poseStack);
				left_hand.translateAndRotate(poseStack);
			} else {
				RightArm.translateAndRotate(poseStack);
				right_hand.translateAndRotate(poseStack);
			}
		}

		public void translateToHead(PoseStack poseStack) {
			Torso.translateAndRotate(poseStack);
			trueHead.translateAndRotate(poseStack);
			Head.translateAndRotate(poseStack);
		}

		@Override
		public void translateArmor(CustomArmorLayer.ArmorModelPart modelPart, PoseStack posestack, boolean innerModel) {
			Torso.translateAndRotate(posestack);
			switch (modelPart) {
				case HEAD :
					trueHead.translateAndRotate(posestack);
					Head.translateAndRotate(posestack);
					posestack.scale(1, 1.25f, 1);
					break;
				case BODY :
					body_armor.translateAndRotate(posestack);
					break;
				case RIGHT_ARM :
					RightArm.translateAndRotate(posestack);
					posestack.translate(0.25, -0.125, 0);
					break;
				case LEFT_ARM :
					LeftArm.translateAndRotate(posestack);
					posestack.translate(-0.25, -0.125, 0);
					break;
				case RIGHT_LEG :
					RightLeg.translateAndRotate(posestack);
					posestack.translate(0.125, -0.75, 0);
					break;
				case LEFT_LEG :
					LeftLeg.translateAndRotate(posestack);
					posestack.translate(-0.125, -0.75, 0);
					break;
			}
		}

		public AnimatedModel(ModelPart root) {
			super(root);
			this.root = root;
		}

		@Override
		public void setupAnim(BanditEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			hat.visible = !entity.getModelPartToHide(0);
			jacket.visible = !entity.getModelPartToHide(1);
			super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		}
	}
}