package net.sweegy.illageruniverse.client.renderer;

import net.sweegy.illageruniverse.entity.BanditEntity;
import net.sweegy.illageruniverse.client.model.ModelIllagerBandit;
import net.sweegy.illageruniverse.MobAnimatorRegistry;
import net.sweegy.illageruniverse.HierachicalHeadLayer;
import net.sweegy.illageruniverse.CustomArmorLayer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelLayers;

public class BanditRenderer extends MobRenderer<BanditEntity, MobAnimatorRegistry.AnimatedBanditModel> {
	private final ResourceLocation entityTexture = new ResourceLocation("illager_universe:textures/entities/bandit.png");

	public BanditRenderer(EntityRendererProvider.Context context) {
		super(context, new MobAnimatorRegistry.AnimatedBanditModel(context.bakeLayer(ModelIllagerBandit.LAYER_LOCATION)), 0.5f);
		addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()));
		addLayer(new HierachicalHeadLayer<>(this, context.getModelSet(), context.getItemInHandRenderer()));
		CustomArmorLayer.addCustomArmorLayers(this, context, ModelLayers.PLAYER_INNER_ARMOR, ModelLayers.PLAYER_OUTER_ARMOR);
	}

	@Override
	public ResourceLocation getTextureLocation(BanditEntity entity) {
		return entityTexture;
	}
}