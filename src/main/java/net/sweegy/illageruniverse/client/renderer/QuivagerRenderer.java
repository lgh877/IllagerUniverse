package net.sweegy.illageruniverse.client.renderer;

import net.sweegy.illageruniverse.entity.QuivagerEntity;
import net.sweegy.illageruniverse.client.model.animations.quivagerAnimation;
import net.sweegy.illageruniverse.client.model.Modelquivager;
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

public class QuivagerRenderer extends MobRenderer<QuivagerEntity, QuivagerRenderer.AnimatedModel> {
	private final ResourceLocation entityTexture = new ResourceLocation("illager_universe:textures/entities/quivager.png");

	public QuivagerRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(Modelquivager.LAYER_LOCATION)), 0.5f);
		this.addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()));
		addLayer(new HierachicalHeadLayer<>(this, context.getModelSet(), context.getItemInHandRenderer()));
		CustomArmorLayer.addCustomArmorLayers(this, context, ModelLayers.PLAYER_INNER_ARMOR, ModelLayers.PLAYER_OUTER_ARMOR);
	}

	@Override
	public ResourceLocation getTextureLocation(QuivagerEntity entity) {
		return entityTexture;
	}

	public static final class AnimatedModel extends Modelquivager<QuivagerEntity> implements ArmedModel, IHierachicalHeadedModel, ArmorWearingModel {
		private final ModelPart root;
		private final HierarchicalModel animator = new HierarchicalModel<QuivagerEntity>() {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(QuivagerEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				float partialTicks = ageInTicks - entity.tickCount;
				float walkTransitionAmplitude = Mth.lerp(partialTicks, entity.walkTransitionAmplitudeO, entity.walkTransitionAmplitude) / 3F;
				this.animateWalk(quivagerAnimation.walk, limbSwing, limbSwingAmount * walkTransitionAmplitude, 1f, 1.5f);
				this.animateWalk(quivagerAnimation.sprint, limbSwing, limbSwingAmount * (1 - walkTransitionAmplitude), 1f, 2f);
				if (entity.getActionState() != 0) {
					float animTicks = Mth.lerp(partialTicks, entity.animTicksO, entity.animTicks);
					this.animateWalk(entity.currentAnimation, animTicks, 1f, 1f, 1f);
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
					posestack.scale(1, 1.25f, 1);
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

		public AnimatedModel(ModelPart root) {
			super(root);
			this.root = root;
		}

		@Override
		public void setupAnim(QuivagerEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			hat.visible = !entity.getModelPartToHide(0);
			apron.visible = !entity.getModelPartToHide(1);
			super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		}
	}
}