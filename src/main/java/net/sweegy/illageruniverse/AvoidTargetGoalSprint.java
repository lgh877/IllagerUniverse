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

import net.minecraft.world.entity.PathfinderMob;

public class AvoidTargetGoalSprint extends AvoidTargetGoal {
	public AvoidTargetGoalSprint(PathfinderMob mob, float maxDistance, double walkSpeed) {
		super(mob, maxDistance, walkSpeed);
	}

	public void start() {
		super.start();
		mob.setSprinting(true);
	}

	public void stop() {
		super.stop();
		mob.setSprinting(false);
	}
}