package net.sweegy.illageruniverse.client.renderer;

import net.sweegy.illageruniverse.entity.IronForkProjectileEntity;
import net.sweegy.illageruniverse.DirectionalItemRenderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.MultiBufferSource;

import com.mojang.blaze3d.vertex.PoseStack;

public class IronForkProjectileRenderer extends DirectionalItemRenderer<IronForkProjectileEntity> {
	public IronForkProjectileRenderer(EntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	public void render(IronForkProjectileEntity entityIn, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, int packedLightIn) {
		super.render(entityIn, entityYaw, partialTicks, poseStack, bufferIn, packedLightIn);
	}
}