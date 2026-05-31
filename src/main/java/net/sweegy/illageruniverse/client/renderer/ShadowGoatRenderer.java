package net.sweegy.illageruniverse.client.renderer;

import net.sweegy.illageruniverse.entity.ShadowGoatEntity;
import net.sweegy.illageruniverse.client.model.Modelshadow_goat;
import net.sweegy.illageruniverse.MobAnimatorRegistry;
import net.sweegy.illageruniverse.HierachicalHeadLayer;
import net.sweegy.illageruniverse.CustomArmorLayer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelLayers;

public class ShadowGoatRenderer extends MobRenderer<ShadowGoatEntity, MobAnimatorRegistry.AnimatedShadowGoatModel> {
	private final ResourceLocation entityTexture = new ResourceLocation("illager_universe:textures/entities/shadow_goat.png");

	public ShadowGoatRenderer(EntityRendererProvider.Context context) {
		super(context, new MobAnimatorRegistry.AnimatedShadowGoatModel(context.bakeLayer(Modelshadow_goat.LAYER_LOCATION)), 0.7f);
		addLayer(new HierachicalHeadLayer<>(this, context.getModelSet(), context.getItemInHandRenderer()));
		CustomArmorLayer.addCustomArmorLayers(this, context, ModelLayers.PLAYER_INNER_ARMOR, ModelLayers.PLAYER_OUTER_ARMOR);
	}

	@Override
	public ResourceLocation getTextureLocation(ShadowGoatEntity entity) {
		return entityTexture;
	}
}