package net.sweegy.illageruniverse.client.renderer;

import net.sweegy.illageruniverse.entity.UpgraderArmorEntity;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.HumanoidModel;

import java.util.function.Function;

import com.mojang.blaze3d.vertex.PoseStack;

public class UpgraderArmorRenderer extends HumanoidMobRenderer<UpgraderArmorEntity, HumanoidModel<UpgraderArmorEntity>> {
	private final ResourceLocation entityTexture = new ResourceLocation("illager_universe:textures/entities/nothing.png");

	public UpgraderArmorRenderer(EntityRendererProvider.Context context) {
		super(context, new UpgraderArmorModel(context.bakeLayer(ModelLayers.PLAYER)), 0.5f);
		this.addLayer(new HumanoidArmorLayer(this, new HumanoidModel(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)), new HumanoidModel(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), context.getModelManager()));
		model.head.y = 24;
		model.hat.y = 24;
		model.body.y = 12;
		model.leftArm.y = 14;
		model.rightArm.y = 14;
	}

	@Override
	public ResourceLocation getTextureLocation(UpgraderArmorEntity entity) {
		return entityTexture;
	}

	protected void scale(UpgraderArmorEntity p_115983_, PoseStack p_115984_, float p_115985_) {
		if (p_115983_.tickCount < 3)
			p_115984_.scale(0, 0, 0);
		else {
			float scale = p_115983_.tickCount < 12 ? (p_115983_.tickCount - 2 + p_115985_) * 0.1f : 1;
			p_115984_.scale(scale, scale, scale);
		}
	}

	private static class UpgraderArmorModel extends HumanoidModel {
		public UpgraderArmorModel(ModelPart p_170677_) {
			this(p_170677_, RenderType::entityCutoutNoCull);
		}

		public UpgraderArmorModel(ModelPart p_170679_, Function<ResourceLocation, RenderType> p_170680_) {
			super(p_170679_, p_170680_);
		}

		@Override
		public void prepareMobModel(LivingEntity p_102861_, float p_102862_, float p_102863_, float p_102864_) {
		}

		@Override
		public void setupAnim(LivingEntity p_102866_, float p_102867_, float p_102868_, float p_102869_, float p_102870_, float p_102871_) {
		}
	}
}