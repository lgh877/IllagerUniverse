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

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.Item;

import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class UpgraderArmorSetsRegistry {
	// 1. Data Structure: [Set Index (String), Armor Items Array [4]]
	// String ID is used for flexibility to support future configuration files.
	private static final Map<String, Item[]> ARMOR_SETS = new HashMap<>();
	// 2. Armor Slot Array Index Constants
	// Order: [0: Boots, 1: Leggings, 2: Chestplate, 3: Helmet]
	public static final int BOOTS_INDEX = 0;
	public static final int LEGGINGS_INDEX = 1;
	public static final int CHESTPLATE_INDEX = 2;
	public static final int HELMET_INDEX = 3;

	/**
	 * Registers a new armor set into the registry.
	 * This method can be called in a loop when parsing external Config/JSON files in the future.
	 *
	 * @param setId      Unique identifier for the armor set
	 * @param boots      Boots item
	 * @param leggings   Leggings item
	 * @param chestplate Chestplate item
	 * @param helmet     Helmet item
	 */
	public static void registerSet(String setId, Item boots, Item leggings, Item chestplate, Item helmet) {
		Item[] armorParts = new Item[4];
		armorParts[BOOTS_INDEX] = boots;
		armorParts[LEGGINGS_INDEX] = leggings;
		armorParts[CHESTPLATE_INDEX] = chestplate;
		armorParts[HELMET_INDEX] = helmet;
		ARMOR_SETS.put(setId, armorParts);
	}

	/**
	 * Initializes default vanilla or built-in armor sets.
	 * This hardcoded part can be replaced or extended by a Config loader later.
	 */
	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		// Examples of default registered sets
		registerSet("iron", Items.IRON_BOOTS, Items.IRON_LEGGINGS, Items.IRON_CHESTPLATE, Items.IRON_HELMET);
		registerSet("gold", Items.GOLDEN_BOOTS, Items.GOLDEN_LEGGINGS, Items.GOLDEN_CHESTPLATE, Items.GOLDEN_HELMET);
		registerSet("diamond", Items.DIAMOND_BOOTS, Items.DIAMOND_LEGGINGS, Items.DIAMOND_CHESTPLATE, Items.DIAMOND_HELMET);
	}

	/**
	 * Gets the armor item array [4] associated with the given set ID.
	 * * @param setId The ID of the armor set to retrieve
	 * @return Item[4] array. Returns an empty array of size 4 containing null values if not found.
	 */
	public static Item[] getArmorSet(String setId) {
		return ARMOR_SETS.getOrDefault(setId, new Item[4]);
	}

	/**
	 * Returns an unmodifiable view of all registered armor sets for safety.
	 */
	public static Map<String, Item[]> getAllSets() {
		return Collections.unmodifiableMap(ARMOR_SETS);
	}

	/**
	 * Clears all registered data from memory.
	 * Essential for dynamic config reloading or /reload commands.
	 */
	public static void clear() {
		ARMOR_SETS.clear();
	}
}