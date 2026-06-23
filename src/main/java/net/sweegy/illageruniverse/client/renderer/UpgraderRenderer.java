package net.sweegy.illageruniverse.client.renderer;

import net.sweegy.illageruniverse.entity.UpgraderEntity;
import net.sweegy.illageruniverse.client.model.Modelupgrader;
import net.sweegy.illageruniverse.MobAnimatorRegistry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class UpgraderRenderer extends MobRenderer<UpgraderEntity, Modelupgrader<UpgraderEntity>> {
	private final ResourceLocation entityTexture = new ResourceLocation("illager_universe:textures/entities/upgrader.png");

	public UpgraderRenderer(EntityRendererProvider.Context context) {
		super(context, new MobAnimatorRegistry.AnimatedUpgraderModel(context.bakeLayer(Modelupgrader.LAYER_LOCATION)), 0.5f);
		this.addLayer(new RenderLayer<UpgraderEntity, Modelupgrader<UpgraderEntity>>(this) {
			final ResourceLocation[] LAYER_TEXTURES = {//
					new ResourceLocation("illager_universe:textures/entities/upgrader_iron_glow.png"), //
					new ResourceLocation("illager_universe:textures/entities/upgrader_gold_glow.png"), //
					new ResourceLocation("illager_universe:textures/entities/upgrader_diamond_glow.png"),//
			};

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, UpgraderEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(LAYER_TEXTURES[entity.getEntityData().get(UpgraderEntity.DATA_variant)]));
				this.getParentModel().renderToBuffer(poseStack, vertexConsumer, light, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
			}
		});
	}

	@Override
	public ResourceLocation getTextureLocation(UpgraderEntity entity) {
		return entityTexture;
	}
}