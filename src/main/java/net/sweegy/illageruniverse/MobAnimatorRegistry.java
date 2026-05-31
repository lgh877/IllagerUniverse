/**
 * The code of this mod element is always locked.
 *
 * You can register new events in this class too.
 *
 * If you want to make a plain independent class, create it using
 * Project Browser -> New... and make sure to make the class
 * outside net.sweegy.illageruniverse as this package is managed by MCreator.
 *
 * If you change workspace package, modid or prefix, you will need
 * to manually adapt this file to these changes or remake it.
 *
 * This class will be added in the mod root package.
*/
package net.sweegy.illageruniverse;

import net.sweegy.illageruniverse.entity.ShadowGoatEntity;
import net.sweegy.illageruniverse.entity.QuivagerEntity;
import net.sweegy.illageruniverse.entity.BanditEntity;
import net.sweegy.illageruniverse.client.model.animations.quivagerAnimation;
import net.sweegy.illageruniverse.client.model.animations.IllagerBanditAnimation;
import net.sweegy.illageruniverse.client.model.Modelshadow_goat;
import net.sweegy.illageruniverse.client.model.Modelquivager;
import net.sweegy.illageruniverse.client.model.ModelIllagerBandit;

import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.util.Mth;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.ArmedModel;

import java.util.List;

import com.mojang.math.Axis;
import com.mojang.blaze3d.vertex.PoseStack;

public class MobAnimatorRegistry {
	public static final class AnimatedBanditModel extends ModelIllagerBandit<BanditEntity> implements ArmedModel, IHierachicalHeadedModel, ArmorWearingModel {
		private final ModelPart root;
		private boolean shouldPlayAnimation;
		private final HierarchicalModel animator = new HierarchicalModel<BanditEntity>() {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(BanditEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				shouldPlayAnimation = entity.getActionState() != 0;
				float partialTicks = ageInTicks - entity.tickCount;
				float walkTransitionAmplitude = Mth.lerp(partialTicks, entity.walkTransitionAmplitudeO, entity.walkTransitionAmplitude) / 3F;
				this.animate(entity.animationState0, IllagerBanditAnimation.idle, ageInTicks, 1f);
				this.animateWalk(IllagerBanditAnimation.walk, limbSwing, limbSwingAmount * walkTransitionAmplitude, 1f, 2f);
				this.animateWalk(IllagerBanditAnimation.aggro, limbSwing, limbSwingAmount * (1 - walkTransitionAmplitude), 1f, 2f);
				if (shouldPlayAnimation) {
					float animTicks = Mth.lerp(partialTicks, entity.animTicksO, entity.animTicks);
					this.animateWalk(IllagerUniverseAnimations.BanditAnimations[entity.getActionState() - 1], animTicks, 1f, 1f, 1f);
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
					posestack.translate(0, -0.125, 0);
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

		public AnimatedBanditModel(ModelPart root) {
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

	public static final class AnimatedQuivagerModel extends Modelquivager<QuivagerEntity> implements ArmedModel, IHierachicalHeadedModel, ArmorWearingModel {
		private final ModelPart root;
		private boolean shouldPlayAnimation;
		private final HierarchicalModel animator = new HierarchicalModel<QuivagerEntity>() {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(QuivagerEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				shouldPlayAnimation = entity.getActionState() != 0;
				float partialTicks = ageInTicks - entity.tickCount;
				float walkTransitionAmplitude = Mth.lerp(partialTicks, entity.walkTransitionAmplitudeO, entity.walkTransitionAmplitude) / 3F;
				this.animateWalk(quivagerAnimation.walk, limbSwing, limbSwingAmount * walkTransitionAmplitude, 1f, 1.5f);
				this.animateWalk(quivagerAnimation.sprint, limbSwing, limbSwingAmount * (1 - walkTransitionAmplitude), 2f, 2f);
				if (shouldPlayAnimation) {
					float animTicks = Mth.lerp(partialTicks, entity.animTicksO, entity.animTicks);
					this.animateWalk(IllagerUniverseAnimations.QuivagerAnimations[entity.getActionState() - 1], animTicks, 1f, 1f, 1f);
				}
			}
		};

		public void translateToHand(HumanoidArm p_102925_, PoseStack p_102926_) {
			body.translateAndRotate(p_102926_);
			(p_102925_ == HumanoidArm.LEFT ? this.left_arm : this.right_arm).translateAndRotate(p_102926_);
		}

		public void translateToHead(PoseStack poseStack) {
			body.translateAndRotate(poseStack);
			trueHead.translateAndRotate(poseStack);
			head.translateAndRotate(poseStack);
		}

		@Override
		public void translateArmor(CustomArmorLayer.ArmorModelPart modelPart, PoseStack posestack, boolean innerModel) {
			body.translateAndRotate(posestack);
			switch (modelPart) {
				case HEAD :
					trueHead.translateAndRotate(posestack);
					head.translateAndRotate(posestack);
					posestack.translate(0, -0.125, 0);
					break;
				case BODY :
					body_armor.translateAndRotate(posestack);
					break;
				case RIGHT_ARM :
					right_arm.translateAndRotate(posestack);
					posestack.translate(0.25, -0.125, 0);
					break;
				case LEFT_ARM :
					left_arm.translateAndRotate(posestack);
					posestack.translate(-0.25, -0.125, 0);
					break;
				case RIGHT_LEG :
					leg0.translateAndRotate(posestack);
					posestack.translate(0.125, -0.75, 0);
					break;
				case LEFT_LEG :
					leg1.translateAndRotate(posestack);
					posestack.translate(-0.125, -0.75, 0);
					break;
			}
		}

		public AnimatedQuivagerModel(ModelPart root) {
			super(root);
			this.root = root;
		}

		@Override
		public void setupAnim(QuivagerEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		}
	}

	public static final class AnimatedShadowGoatModel extends Modelshadow_goat<ShadowGoatEntity> implements IHierachicalHeadedModel, ArmorWearingModel {
		private final ModelPart root;
		private final HierarchicalModel animator = new HierarchicalModel<ShadowGoatEntity>() {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(ShadowGoatEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				List<FadingOutAnimation> list = entity.getFadingAnims();
				float partialTicks = ageInTicks - entity.tickCount;
				float animTicks = Mth.lerp(partialTicks, entity.animTicksO, entity.animTicks);
				this.animateWalk(IllagerUniverseAnimations.ShadowGoatAnimations[entity.animIdx], animTicks, (float) Math.min(entity.fadeInTicks + partialTicks, 5) / 5f, 1f, 1f);
				for (int i = list.size() - 1; i >= 0; i--) {
					FadingOutAnimation anim = list.get(i);
					if (anim.shouldBeRemoved) {
						anim.cleanup();
						continue;
					}
					this.animateWalk(IllagerUniverseAnimations.ShadowGoatAnimations[anim.animIdx], anim.finalTime, (float) Math.max(anim.remainingFadeOutTime - partialTicks, 0) / anim.maxFadeOutTime, 1f, 1f);
				}
			}
		};

		public void translateToHead(PoseStack poseStack) {
			All.translateAndRotate(poseStack);
			body.translateAndRotate(poseStack);
			poseStack.translate(0, 0.1, 0.25);
		}

		@Override
		public void translateArmor(CustomArmorLayer.ArmorModelPart modelPart, PoseStack posestack, boolean innerModel) {
			All.translateAndRotate(posestack);
			switch (modelPart) {
				case HEAD :
					body.translateAndRotate(posestack);
					trueHead.translateAndRotate(posestack);
					head.translateAndRotate(posestack);
					posestack.scale(0.625f, 0.625f, 0.625f);
					posestack.translate(0, 0.375, 0);
					break;
				case BODY :
					body.translateAndRotate(posestack);
					posestack.mulPose(Axis.XP.rotationDegrees(90.0F));
					posestack.translate(0, -0.25, 0);
					posestack.scale(1.125f, 1.1666f, 2.5f);
					break;
				case RIGHT_ARM :
					right_front_leg.translateAndRotate(posestack);
					posestack.translate(0.25, 0, 0);
					posestack.scale(0.85f, 0.8333f, 0.85f);
					break;
				case LEFT_ARM :
					left_front_leg.translateAndRotate(posestack);
					posestack.translate(-0.25, 0, 0);
					posestack.scale(0.85f, 0.8333f, 0.85f);
					break;
				case RIGHT_LEG :
					right_back_leg.translateAndRotate(posestack);
					posestack.translate(0.125, -0.5, 0);
					posestack.scale(0.85f, 0.8333f, 0.85f);
					break;
				case LEFT_LEG :
					left_back_leg.translateAndRotate(posestack);
					posestack.translate(-0.125, -0.5, 0);
					posestack.scale(0.85f, 0.8333f, 0.85f);
					break;
			}
		}

		public AnimatedShadowGoatModel(ModelPart root) {
			super(root);
			this.root = root;
		}

		@Override
		public void setupAnim(ShadowGoatEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		}
	}
}