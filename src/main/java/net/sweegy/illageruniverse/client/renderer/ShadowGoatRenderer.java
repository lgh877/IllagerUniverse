package net.sweegy.illageruniverse.client.renderer;

import net.sweegy.illageruniverse.entity.ShadowGoatEntity;
import net.sweegy.illageruniverse.client.model.Modelshadow_goat;
import net.sweegy.illageruniverse.IllagerUniverseAnimations;
import net.sweegy.illageruniverse.FadingOutAnimation;

import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;

import java.util.List;

public class ShadowGoatRenderer extends MobRenderer<ShadowGoatEntity, Modelshadow_goat<ShadowGoatEntity>> {
	private final ResourceLocation entityTexture = new ResourceLocation("illager_universe:textures/entities/shadow_goat.png");

	public ShadowGoatRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(Modelshadow_goat.LAYER_LOCATION)), 0.7f);
	}

	@Override
	public ResourceLocation getTextureLocation(ShadowGoatEntity entity) {
		return entityTexture;
	}

	private static final class AnimatedModel extends Modelshadow_goat<ShadowGoatEntity> {
		private final ModelPart root;
		private final HierarchicalModel animator = new HierarchicalModel<ShadowGoatEntity>() {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(ShadowGoatEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				List<FadingOutAnimation> list = entity.fadingAnims;
				float partialTicks = ageInTicks - entity.tickCount;
				float animTicks = Mth.lerp(partialTicks, entity.animTicksO, entity.animTicks);
				this.animateWalk(IllagerUniverseAnimations.ShadowGoatAnimations[entity.getActionState()], animTicks, (float) Math.min(entity.fadeInTicks + partialTicks, 5) / 5f, 1f, 1f);
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

		public AnimatedModel(ModelPart root) {
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