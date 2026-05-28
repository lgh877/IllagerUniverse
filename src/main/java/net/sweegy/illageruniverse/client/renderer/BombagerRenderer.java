package net.sweegy.illageruniverse.client.renderer;

import net.sweegy.illageruniverse.entity.BombagerEntity;
import net.sweegy.illageruniverse.client.model.Modelbombager;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class BombagerRenderer extends MobRenderer<BombagerEntity, Modelbombager<BombagerEntity>> {
	private final ResourceLocation entityTexture = new ResourceLocation("illager_universe:textures/entities/bombager.png");

	public BombagerRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelbombager<BombagerEntity>(context.bakeLayer(Modelbombager.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(BombagerEntity entity) {
		return entityTexture;
	}
}