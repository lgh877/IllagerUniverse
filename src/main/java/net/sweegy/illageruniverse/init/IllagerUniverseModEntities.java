/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.sweegy.illageruniverse.init;

import net.sweegy.illageruniverse.entity.*;
import net.sweegy.illageruniverse.IllagerUniverseMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class IllagerUniverseModEntities {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, IllagerUniverseMod.MODID);
	public static final RegistryObject<EntityType<BanditEntity>> BANDIT = register("bandit",
			EntityType.Builder.<BanditEntity>of(BanditEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(BanditEntity::new)

					.sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<IronForkProjectileEntity>> IRON_FORK_PROJECTILE = register("iron_fork_projectile", EntityType.Builder.<IronForkProjectileEntity>of(IronForkProjectileEntity::new, MobCategory.MISC)
			.setCustomClientFactory(IronForkProjectileEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<QuivagerEntity>> QUIVAGER = register("quivager",
			EntityType.Builder.<QuivagerEntity>of(QuivagerEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(QuivagerEntity::new)

					.sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<BombagerEntity>> BOMBAGER = register("bombager",
			EntityType.Builder.<BombagerEntity>of(BombagerEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(BombagerEntity::new)

					.sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<ShadowGoatEntity>> SHADOW_GOAT = register("shadow_goat",
			EntityType.Builder.<ShadowGoatEntity>of(ShadowGoatEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(ShadowGoatEntity::new)

					.sized(0.9f, 1.3f));
	public static final RegistryObject<EntityType<UpgraderEntity>> UPGRADER = register("upgrader",
			EntityType.Builder.<UpgraderEntity>of(UpgraderEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(UpgraderEntity::new)

					.sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<UpgraderArmorEntity>> UPGRADER_ARMOR = register("upgrader_armor",
			EntityType.Builder.<UpgraderArmorEntity>of(UpgraderArmorEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(UpgraderArmorEntity::new)

					.sized(0.6f, 0.6f));

	// Start of user code block custom entities
	// End of user code block custom entities
	private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(registryname));
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			BanditEntity.init();
			QuivagerEntity.init();
			BombagerEntity.init();
			ShadowGoatEntity.init();
			UpgraderEntity.init();
			UpgraderArmorEntity.init();
		});
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(BANDIT.get(), BanditEntity.createAttributes().build());
		event.put(QUIVAGER.get(), QuivagerEntity.createAttributes().build());
		event.put(BOMBAGER.get(), BombagerEntity.createAttributes().build());
		event.put(SHADOW_GOAT.get(), ShadowGoatEntity.createAttributes().build());
		event.put(UPGRADER.get(), UpgraderEntity.createAttributes().build());
		event.put(UPGRADER_ARMOR.get(), UpgraderArmorEntity.createAttributes().build());
	}
}