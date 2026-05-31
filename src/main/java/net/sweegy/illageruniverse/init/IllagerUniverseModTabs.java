/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.sweegy.illageruniverse.init;

import net.sweegy.illageruniverse.IllagerUniverseMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

public class IllagerUniverseModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, IllagerUniverseMod.MODID);
	public static final RegistryObject<CreativeModeTab> ILLAGER_UNIVERSE_TAB = REGISTRY.register("illager_universe_tab",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.illager_universe.illager_universe_tab")).icon(() -> new ItemStack(Items.VINDICATOR_SPAWN_EGG)).displayItems((parameters, tabData) -> {
				tabData.accept(IllagerUniverseModItems.IRON_CUTLASS.get());
				tabData.accept(IllagerUniverseModItems.BANDIT_SPAWN_EGG.get());
				tabData.accept(IllagerUniverseModItems.QUIVAGER_SPAWN_EGG.get());
				tabData.accept(IllagerUniverseModItems.BOMBAGER_SPAWN_EGG.get());
				tabData.accept(IllagerUniverseModItems.CHEF_ARMOR_SET_HELMET.get());
				tabData.accept(IllagerUniverseModItems.CHEF_ARMOR_SET_CHESTPLATE.get());
				tabData.accept(IllagerUniverseModItems.FORK.get());
				tabData.accept(IllagerUniverseModItems.SHADOW_GOAT_SPAWN_EGG.get());
				tabData.accept(IllagerUniverseModItems.UPGRADER_SPAWN_EGG.get());
			}).build());
}