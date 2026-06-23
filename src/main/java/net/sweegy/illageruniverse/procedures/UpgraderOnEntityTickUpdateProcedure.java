package net.sweegy.illageruniverse.procedures;

import net.sweegy.illageruniverse.init.IllagerUniverseModEntities;
import net.sweegy.illageruniverse.entity.UpgraderEntity;
import net.sweegy.illageruniverse.entity.UpgraderArmorEntity;
import net.sweegy.illageruniverse.VectorHelper;
import net.sweegy.illageruniverse.FadingOutAnimation;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.arguments.EntityAnchorArgument;

import java.util.List;
import java.util.ArrayList;

public class UpgraderOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		Entity followingMob = null;
		UpgraderEntity mob = (UpgraderEntity) entity;
		if (world.isClientSide()) {
			mob.animTicksO = mob.animTicks++;
			mob.fadeInTicks = Math.min(mob.fadeInTicks + 1, 5);
			for (FadingOutAnimation anim : mob.getFadingAnims()) {
				anim.tick();
			}
		}
		if (!world.isClientSide()) {
			int actionState = mob.getActionState();
			switch (actionState) {
				case 1 :
					mob.actionTicks++;
					if (mob.actionTicks == 11 && mob.getArmorPieceMob() == null) {
						followingMob = mob.lockedTarget;
						if (followingMob != null) {
							List<Integer> emptySlots = new ArrayList<>();
							for (int i = 0; i < 4; i++)
								if (mob.lockedTarget.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, i)).isEmpty())
									emptySlots.add(i);
							if (!emptySlots.isEmpty()) {
								Vec3 lookVec = VectorHelper.calculateFlatViewVector(mob.yBodyRot);
								int selectedSlotIndex = emptySlots.get(mob.getRandom().nextInt(emptySlots.size()));
								if (world instanceof ServerLevel _level) {
									UpgraderArmorEntity entityToSpawn = IllagerUniverseModEntities.UPGRADER_ARMOR.get().spawn(_level, BlockPos.containing(mob.getX(), mob.getEyeY(), mob.getZ()), MobSpawnType.MOB_SUMMONED);
									if (entityToSpawn != null) {
										entityToSpawn.setYRot(entity.getYRot());
										entityToSpawn.setYBodyRot(entity.getYRot());
										entityToSpawn.setYHeadRot(entity.getYRot());
										entityToSpawn.getPersistentData().putDouble("slotType", selectedSlotIndex);
										entityToSpawn.setOwner(mob);
										entityToSpawn.setItemSlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, selectedSlotIndex), mob.getArmorParts()[selectedSlotIndex].getDefaultInstance());
										mob.setArmorPieceMob(entityToSpawn);
										entityToSpawn.canModifyArmor = false;
										entityToSpawn.setNoGravity(true);
									}
								}
							} else {
								mob.setActionState(0);
								mob.actionTicks = 0;
								mob.lockedTarget = (LivingEntity) null;
								mob.setFollowingMob((LivingEntity) null);
							}
						}
					} else if (mob.actionTicks > 11 && mob.actionTicks < 30) {
						followingMob = mob.armorPieceMob;
						if (followingMob != null) {
							Vec3 lookVec = VectorHelper.calculateFlatViewVector(mob.yBodyRot);
							{
								Entity _ent = followingMob;
								double _tx = mob.getX() + lookVec.x() * 0.6;
								double _ty = mob.getY() + mob.getBbHeight() / 2;
								double _tz = mob.getZ() + lookVec.z() * 0.6;
								_ent.teleportTo(_tx, _ty, _tz);
								if (_ent instanceof ServerPlayer _serverPlayer)
									_serverPlayer.connection.teleport(_tx, _ty, _tz, _ent.getYRot(), _ent.getXRot());
							}
							mob.armorPieceMob.setDeltaMovement(new Vec3(0, (-0.04), 0));
						}
					} else if (mob.actionTicks == 31) {
						followingMob = mob.lockedTarget;
						if (followingMob != null) {
							mob.armorPieceMob.setNoGravity(false);
							double xDiff = followingMob.getX() - mob.armorPieceMob.getX();
							double zDiff = followingMob.getZ() - mob.armorPieceMob.getZ();
							Vec2 flatDir = new Vec2((float) xDiff, (float) zDiff).normalized();
							double dist = Math.sqrt(xDiff * xDiff + zDiff * zDiff);
							double yDiff = followingMob.getY() + followingMob.getBbHeight() / 2 - mob.armorPieceMob.getY();
							double horizontalSpeed = VectorHelper.getRequiredVelocityFlat(dist, (int) (dist * 1.5 + 2), 0.91);
							double verticalSpeed = VectorHelper.getRequiredVelocity(yDiff, (int) (dist * 1.5 + 2), 0.91, 0.04);
							mob.armorPieceMob.setDeltaMovement(new Vec3((double) flatDir.x * horizontalSpeed, verticalSpeed, (double) flatDir.y * horizontalSpeed));
							entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((followingMob.getX()), (followingMob.getY()), (followingMob.getZ())));
							mob.resetArmorPieceMob();
						} else if (mob.getArmorPieceMob() != null) {
							mob.armorPieceMob.setNoGravity(false);
							mob.resetArmorPieceMob();
						}
					} else if (mob.actionTicks > 45) {
						mob.setActionState(0);
						mob.actionTicks = 0;
						mob.lockedTarget = (LivingEntity) null;
						mob.setFollowingMob((LivingEntity) null);
					}
					break;
				default :
					if (Math.abs(entity.getDeltaMovement().x()) + Math.abs(entity.getDeltaMovement().z()) < 0.01) {
						mob.setActionState(0);
					} else {
						mob.setActionState(mob.isSprinting() ? 3 : 2);
					}
					if ((entity.tickCount & 31) == 0 && Math.random() < 0.33) {
						followingMob = mob.mobToFollow;
						if (followingMob != null && mob.distanceToSqr(followingMob) < 576) {
							entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((followingMob.getX()), (followingMob.getY()), (followingMob.getZ())));
							mob.setActionState(1);
							mob.lockedTarget = (LivingEntity) followingMob;
						}
					}
					break;
			}
		}
	}
}