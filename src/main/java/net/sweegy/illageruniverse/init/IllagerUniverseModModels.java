/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.sweegy.illageruniverse.init;

import net.sweegy.illageruniverse.client.model.*;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class IllagerUniverseModModels {
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(Modeltoque.LAYER_LOCATION, Modeltoque::createBodyLayer);
		event.registerLayerDefinition(Modelshadow_goat.LAYER_LOCATION, Modelshadow_goat::createBodyLayer);
		event.registerLayerDefinition(ModelIllagerBandit.LAYER_LOCATION, ModelIllagerBandit::createBodyLayer);
		event.registerLayerDefinition(Modelquivager.LAYER_LOCATION, Modelquivager::createBodyLayer);
		event.registerLayerDefinition(Modelbombager.LAYER_LOCATION, Modelbombager::createBodyLayer);
		event.registerLayerDefinition(ModelApron.LAYER_LOCATION, ModelApron::createBodyLayer);
	}
}