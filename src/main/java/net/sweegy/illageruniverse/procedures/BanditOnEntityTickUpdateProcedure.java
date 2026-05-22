package net.sweegy.illageruniverse.procedures;

import net.sweegy.illageruniverse.entity.BanditEntity;
import net.sweegy.illageruniverse.VectorHelper;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Entity;

public class BanditOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		Entity target = null;
		/*
		entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3(x,y,z));*/
		BanditEntity mob = (BanditEntity) entity;
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
			int actionState = mob.getActionState();
			if (actionState == 0) {
				if ((mob.tickCount & 15) == 0) {
					target = entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null;
					if (target != null) {
						if (entity.onGround() && Math.random() < 0.1) {
							mob.setActionState(2);
						}
					}
				}
			} else {
				int actionTicks = mob.actionTicks++;
				switch (actionState) {
					case 1 :
						if (actionTicks > 12) {
							mob.setActionState(0);
							mob.actionTicks = 0;
						}
						break;
					case 2 :
						if (actionTicks == 1) {
							Vec3 lookVec = VectorHelper.calculateFlatViewVector(mob.yBodyRot);
							double moveSpeed = mob.getAttribute(Attributes.MOVEMENT_SPEED).getValue();
							entity.push(lookVec.x * moveSpeed * 4, moveSpeed, lookVec.z * moveSpeed * 4);
						} else if (actionTicks < 6) {
							double width = mob.getBbWidth() * 0.75;
							if (mob.getTarget() != null) {
								for (Entity entityiterator : world.getEntities(null, new AABB(x - width, y, z - width, x + width, y + mob.getBbHeight(), z + width))) {
									if (entityiterator == mob.getTarget()) {
										mob.doHurtTarget(entityiterator);
										break;
									}
								}
							}
						} else if (actionTicks > 11) {
							mob.setActionState(0);
							mob.actionTicks = 0;
						}
						break;
				}
			}
		}
	}
}