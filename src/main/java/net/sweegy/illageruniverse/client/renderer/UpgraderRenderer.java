package net.sweegy.illageruniverse.client.renderer;

import net.sweegy.illageruniverse.entity.UpgraderEntity;
import net.sweegy.illageruniverse.client.model.Modelupgrader;
import net.sweegy.illageruniverse.IllagerUniverseAnimations;
import net.sweegy.illageruniverse.FadingOutAnimation;

import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;

import java.util.List;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class UpgraderRenderer extends MobRenderer<UpgraderEntity, Modelupgrader<UpgraderEntity>> {
	private final ResourceLocation entityTexture = new ResourceLocation("illager_universe:textures/entities/upgrader.png");

	public UpgraderRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(Modelupgrader.LAYER_LOCATION)), 0.5f);
		this.addLayer(new RenderLayer<UpgraderEntity, Modelupgrader<UpgraderEntity>>(this) {
			final ResourceLocation LAYER_TEXTURE = new ResourceLocation("illager_universe:textures/entities/upgrader_diamond_glow.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, UpgraderEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURE));
				this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
			}
		});
	}

	@Override
	public ResourceLocation getTextureLocation(UpgraderEntity entity) {
		return entityTexture;
	}

	private static final class AnimatedModel extends Modelupgrader<UpgraderEntity> {
		private final ModelPart root;
		private final HierarchicalModel animator = new HierarchicalModel<UpgraderEntity>() {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(UpgraderEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				List<FadingOutAnimation> list = entity.getFadingAnims();
				float partialTicks = ageInTicks - entity.tickCount;
				float animTicks = Mth.lerp(partialTicks, entity.animTicksO, entity.animTicks);
				this.animateWalk(IllagerUniverseAnimations.UpgraderAnimations[entity.animIdx], animTicks, (float) Math.min(entity.fadeInTicks + partialTicks, 5) / 5f, 1f, 1f);
				for (int i = list.size() - 1; i >= 0; i--) {
					FadingOutAnimation anim = list.get(i);
					if (anim.shouldBeRemoved) {
						anim.cleanup();
						continue;
					}
					this.animateWalk(IllagerUniverseAnimations.UpgraderAnimations[anim.animIdx], anim.finalTime, (float) Math.max(anim.remainingFadeOutTime - partialTicks, 0) / anim.maxFadeOutTime, 1f, 1f);
				}
			}
		};

		public AnimatedModel(ModelPart root) {
			super(root);
			this.root = root;
		}

		@Override
		public void setupAnim(UpgraderEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			boolean hideArm = entity.animIdx != 1;
			arms.visible = hideArm;
			right_arm.visible = !hideArm;
			left_arm.visible = !hideArm;
			body.yRot = !hideArm ? body.yRot : 0;
			super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		}
	}
}