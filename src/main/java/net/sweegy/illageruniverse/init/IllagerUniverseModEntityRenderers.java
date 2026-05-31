/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.sweegy.illageruniverse.init;

import net.sweegy.illageruniverse.client.renderer.*;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class IllagerUniverseModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(IllagerUniverseModEntities.BANDIT.get(), BanditRenderer::new);
		event.registerEntityRenderer(IllagerUniverseModEntities.IRON_FORK_PROJECTILE.get(), IronForkProjectileRenderer::new);
		event.registerEntityRenderer(IllagerUniverseModEntities.QUIVAGER.get(), QuivagerRenderer::new);
		event.registerEntityRenderer(IllagerUniverseModEntities.BOMBAGER.get(), BombagerRenderer::new);
		event.registerEntityRenderer(IllagerUniverseModEntities.SHADOW_GOAT.get(), ShadowGoatRenderer::new);
		event.registerEntityRenderer(IllagerUniverseModEntities.UPGRADER.get(), UpgraderRenderer::new);
	}
}