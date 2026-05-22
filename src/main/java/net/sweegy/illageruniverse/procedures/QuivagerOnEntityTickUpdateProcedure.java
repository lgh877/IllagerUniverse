package net.sweegy.illageruniverse.procedures;

import net.sweegy.illageruniverse.init.IllagerUniverseModItems;
import net.sweegy.illageruniverse.init.IllagerUniverseModEntities;
import net.sweegy.illageruniverse.entity.QuivagerEntity;
import net.sweegy.illageruniverse.entity.IronForkProjectileEntity;
import net.sweegy.illageruniverse.VectorHelper;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

public class QuivagerOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double rand = 0;
		double yDiff = 0;
		double dist = 0;
		Entity target = null;
		QuivagerEntity mob = (QuivagerEntity) entity;
		if (world.isClientSide()) {
			mob.walkTransitionAmplitudeO = mob.walkTransitionAmplitude;
			mob.animTicksO = mob.animTicks;
			mob.animTicks++;
			if (!mob.isSprinting()) {
				mob.walkTransitionAmplitude = Math.min(mob.walkTransitionAmplitude + 1, 3);
			} else {
				mob.walkTransitionAmplitude = Math.max(mob.walkTransitionAmplitude - 1, 0);
			}
		}
		if (!world.isClientSide()) {
			if (mob.getActionState() == 0) {
				if ((mob.tickCount & 15) == 0) {
					target = entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null;
					rand = Math.random();
					if (!(target == null)) {
						if (rand < 0.08 && (entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) < (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1)) {
							mob.setActionState(3);
						} else if (rand < 0.15 && entity.getPersistentData().getDouble("thrownCount") < 3) {
							entity.getPersistentData().putDouble("thrownCount", (entity.getPersistentData().getDouble("thrownCount") + 1));
							mob.lockedTarget = (LivingEntity) target;
							mob.setActionState(2);
						}
					} else if (rand < 0.08 && (entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) < (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1)) {
						mob.setActionState(3);
					}
				}
			} else {
				mob.actionTicks++;
				switch (mob.getActionState()) {
					case 1 :
						if (mob.actionTicks > 11) {
							mob.actionTicks = 0;
							mob.setActionState(0);
						}
						break;
					case 2 :
						target = mob.lockedTarget;
						if (!(target == null)) {
							mob.getLookControl().setLookAt(target);
						}
						if (mob.actionTicks == 1) {
							if (entity instanceof LivingEntity _entity) {
								ItemStack _setstack12 = new ItemStack(IllagerUniverseModItems.IRON_FORK.get()).copy();
								_setstack12.setCount(1);
								_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack12);
								if (_entity instanceof Player _player)
									_player.getInventory().setChanged();
							}
						} else if (mob.actionTicks == 10) {
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.vindicator.celebrate")), SoundSource.HOSTILE, 1, 1);
								} else {
									_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.vindicator.celebrate")), SoundSource.HOSTILE, 1, 1, false);
								}
							}
						} else if (mob.actionTicks == 14) {
							if (!(target == null)) {
								rand = Math.sqrt(DistToMobFlatSquareProcedure.execute(x, z, target));
								yDiff = target.getY() - (y + entity.getEyeHeight());
								dist = VectorHelper.getRequiredVelocityFlat(rand, (int) (rand + 2), 0.99);
								yDiff = VectorHelper.getRequiredVelocity(yDiff, (int) (rand + 2), 0.99, 0.05);
								rand = Math.sqrt(Math.pow(dist, 2) + Math.pow(yDiff, 2));
							} else {
								dist = 1;
								yDiff = 0;
								rand = 1;
							}
							Vec3 lookVec = VectorHelper.calculateFlatViewVector(mob.yHeadRot);
							if (world instanceof ServerLevel projectileLevel) {
								Projectile _entityToSpawn = initArrowProjectile(createArrowWeaponItemStack(new IronForkProjectileEntity(IllagerUniverseModEntities.IRON_FORK_PROJECTILE.get(), 0, 0, 0, projectileLevel), 1, (byte) 0), entity, 3, true,
										false, false, AbstractArrow.Pickup.ALLOWED);
								_entityToSpawn.setPos(x, (y + entity.getEyeHeight()), z);
								_entityToSpawn.shoot((lookVec.x() * dist), yDiff, (lookVec.z() * dist), (float) rand, 2);
								projectileLevel.addFreshEntity(_entityToSpawn);
							}
							if (entity instanceof LivingEntity _entity) {
								ItemStack _setstack18 = new ItemStack(Blocks.AIR).copy();
								_setstack18.setCount(1);
								_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack18);
								if (_entity instanceof Player _player)
									_player.getInventory().setChanged();
							}
						} else if (mob.actionTicks == 27) {
							if (entity instanceof LivingEntity _entity) {
								ItemStack _setstack19 = new ItemStack(IllagerUniverseModItems.IRON_FORK.get()).copy();
								_setstack19.setCount(1);
								_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack19);
								if (_entity instanceof Player _player)
									_player.getInventory().setChanged();
							}
						} else if (mob.actionTicks == 34) {
							mob.actionTicks = 0;
							mob.setActionState(0);
						}
						break;
					case 3 :
						if (mob.actionTicks == 1) {
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_leather")), SoundSource.HOSTILE, 1, 1);
								} else {
									_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_leather")), SoundSource.HOSTILE, 1, 1, false);
								}
							}
							if (entity instanceof LivingEntity _entity) {
								ItemStack _setstack21 = new ItemStack(Items.BREAD).copy();
								_setstack21.setCount(1);
								_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack21);
								if (_entity instanceof Player _player)
									_player.getInventory().setChanged();
							}
						} else if (mob.actionTicks == 11) {
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.eat")), SoundSource.HOSTILE, 1, 1);
								} else {
									_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.eat")), SoundSource.HOSTILE, 1, 1, false);
								}
							}
							if (entity instanceof LivingEntity _entity)
								_entity.setHealth((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) + (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1) / 10);
						} else if (mob.actionTicks == 16) {
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.eat")), SoundSource.HOSTILE, 1, 1);
								} else {
									_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.eat")), SoundSource.HOSTILE, 1, 1, false);
								}
							}
							if (entity instanceof LivingEntity _entity)
								_entity.setHealth((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) + (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1) / 10);
						} else if (mob.actionTicks == 26) {
							if (entity instanceof LivingEntity _entity) {
								ItemStack _setstack30 = new ItemStack(Blocks.AIR).copy();
								_setstack30.setCount(1);
								_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack30);
								if (_entity instanceof Player _player)
									_player.getInventory().setChanged();
							}
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.player.burp")), SoundSource.HOSTILE, 1, 1);
								} else {
									_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.player.burp")), SoundSource.HOSTILE, 1, 1, false);
								}
							}
							if (entity instanceof LivingEntity _entity)
								_entity.setHealth((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) + (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1) / 10);
						} else if (mob.actionTicks == 38) {
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")), SoundSource.HOSTILE, 1, 1);
								} else {
									_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron")), SoundSource.HOSTILE, 1, 1, false);
								}
							}
							if (entity instanceof LivingEntity _entity) {
								ItemStack _setstack36 = new ItemStack(IllagerUniverseModItems.IRON_FORK.get()).copy();
								_setstack36.setCount(1);
								_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack36);
								if (_entity instanceof Player _player)
									_player.getInventory().setChanged();
							}
						} else if (mob.actionTicks == 41) {
							mob.actionTicks = 0;
							mob.setActionState(0);
						}
						break;
				}
			}
		}
	}

	private static AbstractArrow initArrowProjectile(AbstractArrow entityToSpawn, Entity shooter, float damage, boolean silent, boolean fire, boolean particles, AbstractArrow.Pickup pickup) {
		entityToSpawn.setOwner(shooter);
		entityToSpawn.setBaseDamage(damage);
		if (silent)
			entityToSpawn.setSilent(true);
		if (fire)
			entityToSpawn.setSecondsOnFire(100);
		if (particles)
			entityToSpawn.setCritArrow(true);
		entityToSpawn.pickup = pickup;
		return entityToSpawn;
	}

	private static AbstractArrow createArrowWeaponItemStack(AbstractArrow entityToSpawn, int knockback, byte piercing) {
		if (knockback > 0)
			entityToSpawn.setKnockback(knockback);
		if (piercing > 0)
			entityToSpawn.setPierceLevel(piercing);
		return entityToSpawn;
	}
}