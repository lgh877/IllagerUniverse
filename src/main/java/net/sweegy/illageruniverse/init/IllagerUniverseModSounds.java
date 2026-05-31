/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.sweegy.illageruniverse.init;

import net.sweegy.illageruniverse.IllagerUniverseMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;

public class IllagerUniverseModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, IllagerUniverseMod.MODID);
	public static final RegistryObject<SoundEvent> UPGRADER_IDLE = REGISTRY.register("upgrader_idle", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("illager_universe", "upgrader_idle")));
	public static final RegistryObject<SoundEvent> UPGRADER_DEATH = REGISTRY.register("upgrader_death", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("illager_universe", "upgrader_death")));
	public static final RegistryObject<SoundEvent> UPGRADER_HURT = REGISTRY.register("upgrader_hurt", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("illager_universe", "upgrader_hurt")));
}