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

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.LivingEntity;

import javax.annotation.Nullable;

import java.util.EnumSet;

public class FollowMobIndirectly<E extends PathfinderMob & IMobFollower> extends Goal {
	private final E mob;
	@Nullable
	private LivingEntity target;
	private double wantedX;
	private double wantedY;
	private double wantedZ;
	private final double speedModifier;
	private final float within;
	private final int maxInterval;

	public FollowMobIndirectly(E mob, float within, double speedModifier) {
		this.mob = mob;
		this.speedModifier = speedModifier;
		this.within = within;
		this.maxInterval = 10;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE));
	}

	@Override
	public boolean canUse() {
		if (this.mob.getRandom().nextInt(this.maxInterval) != 0) {
			return false;
		}
		this.target = this.mob.getFollowingMob();
		if (this.target == null || !this.target.isAlive()) {
			mob.setFollowingMob((LivingEntity) null);
			return false;
		}
		if (this.mob.distanceToSqr(this.target) < (double) (this.within * this.within)) {
			return false;
		}
		Vec3 vec3 = DefaultRandomPos.getPosTowards(this.mob, 24, 7, this.target.position(), (Math.PI / 4));
		if (vec3 == null) {
			return false;
		} else {
			this.wantedX = vec3.x;
			this.wantedY = vec3.y;
			this.wantedZ = vec3.z;
			return true;
		}
	}

	@Override
	public boolean canContinueToUse() {
		return !this.mob.getNavigation().isDone() && this.target != null && this.target.isAlive() && this.mob.distanceToSqr(this.target) > (double) (this.within * this.within);
	}

	@Override
	public void start() {
		mob.setSprinting(true);
		this.mob.getNavigation().moveTo(this.wantedX, this.wantedY, this.wantedZ, this.speedModifier);
	}

	@Override
	public void stop() {
		mob.setSprinting(false);
		this.target = null;
		this.mob.getNavigation().stop();
	}
}