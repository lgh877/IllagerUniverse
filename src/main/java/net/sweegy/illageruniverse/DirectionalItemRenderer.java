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

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.Mth;
import net.minecraft.core.BlockPos;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.MultiBufferSource;

import com.mojang.math.Axis;
import com.mojang.blaze3d.vertex.PoseStack;

@OnlyIn(Dist.CLIENT)
public class DirectionalItemRenderer<T extends Entity & ItemSupplier> extends ThrownItemRenderer<T> {
	private final ItemRenderer itemRenderer;
	private final float scale;
	private final boolean fullBright;

	public DirectionalItemRenderer(EntityRendererProvider.Context context, float scale, boolean fullBright) {
		super(context, scale, fullBright);
		this.itemRenderer = context.getItemRenderer();
		this.scale = scale;
		this.fullBright = fullBright;
	}

	public DirectionalItemRenderer(EntityRendererProvider.Context context) {
		this(context, 1.0F, false);
	}

	protected int getBlockLightLevel(T p_116092_, BlockPos p_116093_) {
		return this.fullBright ? 15 : super.getBlockLightLevel(p_116092_, p_116093_);
	}

	@Override
	public void render(T entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
		if (entity.tickCount >= 2 || !(this.entityRenderDispatcher.camera.getEntity().distanceToSqr(entity) < 12.25D)) {
			poseStack.pushPose();
			poseStack.scale(this.scale, this.scale, this.scale);
			float yRot = Mth.lerp(partialTicks, entity.yRotO, entity.getYRot());
			float xRot = Mth.lerp(partialTicks, entity.xRotO, entity.getXRot());
			poseStack.mulPose(Axis.YP.rotationDegrees(yRot - 90.0F));
			poseStack.mulPose(Axis.ZP.rotationDegrees(xRot));
			poseStack.mulPose(Axis.ZP.rotationDegrees(-135.0F));
			this.itemRenderer.renderStatic(//
					entity.getItem(), //
					ItemDisplayContext.FIXED, //
					packedLight, //
					OverlayTexture.NO_OVERLAY, //
					poseStack, buffer, //
					entity.level(), //
					entity.getId()//
			);
			poseStack.popPose();
		}
	}
}