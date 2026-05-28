package net.sweegy.illageruniverse.procedures;

import net.sweegy.illageruniverse.entity.ShadowGoatEntity;
import net.sweegy.illageruniverse.FadingOutAnimation;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

public class ShadowGoatOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		ShadowGoatEntity mob = (ShadowGoatEntity) entity;
		if (world.isClientSide()) {
			mob.animTicksO = mob.animTicks++;
			mob.fadeInTicks = Math.min(mob.fadeInTicks + 1, 5);
			for (FadingOutAnimation anim : mob.fadingAnims) {
				anim.tick();
			}
		}
		if (!world.isClientSide()) {
			int actionState = mob.getActionState();
			switch (actionState) {
				case 1 :
					mob.actionTicks++;
					if (mob.actionTicks > 11) {
						mob.actionTicks = 0;
						mob.setActionState(0);
					}
					break;
				default :
					if (Math.abs(entity.getDeltaMovement().x()) + Math.abs(entity.getDeltaMovement().z()) < 0.01) {
						mob.setActionState(0);
					} else {
						mob.setActionState(mob.isSprinting() ? 3 : 2);
					}
					break;
			}
		}
	}
}