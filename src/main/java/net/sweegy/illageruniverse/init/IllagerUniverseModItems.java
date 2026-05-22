/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.sweegy.illageruniverse.init;

import net.sweegy.illageruniverse.item.IronForkItem;
import net.sweegy.illageruniverse.item.IronCutlassItem;
import net.sweegy.illageruniverse.IllagerUniverseMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.ForgeSpawnEggItem;

import net.minecraft.world.item.Item;

public class IllagerUniverseModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, IllagerUniverseMod.MODID);
	public static final RegistryObject<Item> IRON_CUTLASS;
	public static final RegistryObject<Item> BANDIT_SPAWN_EGG;
	public static final RegistryObject<Item> IRON_FORK;
	public static final RegistryObject<Item> QUIVAGER_SPAWN_EGG;
	static {
		IRON_CUTLASS = REGISTRY.register("iron_cutlass", IronCutlassItem::new);
		BANDIT_SPAWN_EGG = REGISTRY.register("bandit_spawn_egg", () -> new ForgeSpawnEggItem(IllagerUniverseModEntities.BANDIT, -1, -1, new Item.Properties()));
		IRON_FORK = REGISTRY.register("iron_fork", IronForkItem::new);
		QUIVAGER_SPAWN_EGG = REGISTRY.register("quivager_spawn_egg", () -> new ForgeSpawnEggItem(IllagerUniverseModEntities.QUIVAGER, -1, -1, new Item.Properties()));
	}
	// Start of user code block custom items
	// End of user code block custom items
}