/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.sweegy.illageruniverse.init;

import net.sweegy.illageruniverse.item.IronCutlassItem;
import net.sweegy.illageruniverse.item.ForkItem;
import net.sweegy.illageruniverse.item.ChefArmorSetItem;
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
	public static final RegistryObject<Item> QUIVAGER_SPAWN_EGG;
	public static final RegistryObject<Item> BOMBAGER_SPAWN_EGG;
	public static final RegistryObject<Item> CHEF_ARMOR_SET_HELMET;
	public static final RegistryObject<Item> CHEF_ARMOR_SET_CHESTPLATE;
	public static final RegistryObject<Item> FORK;
	public static final RegistryObject<Item> SHADOW_GOAT_SPAWN_EGG;
	static {
		IRON_CUTLASS = REGISTRY.register("iron_cutlass", IronCutlassItem::new);
		BANDIT_SPAWN_EGG = REGISTRY.register("bandit_spawn_egg", () -> new ForgeSpawnEggItem(IllagerUniverseModEntities.BANDIT, -1, -1, new Item.Properties()));
		QUIVAGER_SPAWN_EGG = REGISTRY.register("quivager_spawn_egg", () -> new ForgeSpawnEggItem(IllagerUniverseModEntities.QUIVAGER, -1, -1, new Item.Properties()));
		BOMBAGER_SPAWN_EGG = REGISTRY.register("bombager_spawn_egg", () -> new ForgeSpawnEggItem(IllagerUniverseModEntities.BOMBAGER, -1, -1, new Item.Properties()));
		CHEF_ARMOR_SET_HELMET = REGISTRY.register("chef_armor_set_helmet", ChefArmorSetItem.Helmet::new);
		CHEF_ARMOR_SET_CHESTPLATE = REGISTRY.register("chef_armor_set_chestplate", ChefArmorSetItem.Chestplate::new);
		FORK = REGISTRY.register("fork", ForkItem::new);
		SHADOW_GOAT_SPAWN_EGG = REGISTRY.register("shadow_goat_spawn_egg", () -> new ForgeSpawnEggItem(IllagerUniverseModEntities.SHADOW_GOAT, -1, -1, new Item.Properties()));
	}
	// Start of user code block custom items
	// End of user code block custom items
}