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

import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.LivingEntity;

public class ActionStateMobMeleeAttackGoalNoAttack<T extends PathfinderMob & IActionStateMob> extends MeleeAttackGoal {
	protected final T actionStateMob;
	private final int meleeAttackState;

	public ActionStateMobMeleeAttackGoalNoAttack(T a, double b, boolean c, int meleeAttackState) {
		super(a, b, c);
		actionStateMob = a;
		this.meleeAttackState = meleeAttackState;
	}

	public ActionStateMobMeleeAttackGoalNoAttack(T a, double b, boolean c) {
		this(a, b, c, 1);
	}

	@Override
	protected boolean isTimeToAttack() {
		return !actionStateMob.shouldNotMove();
	}

	protected void checkAndPerformAttack(LivingEntity p_25557_, double p_25558_) {
		double d0 = this.getAttackReachSqr(p_25557_);
		if (p_25558_ < d0 && this.getTicksUntilNextAttack() == 0) {
			actionStateMob.setActionState(meleeAttackState);
			this.resetAttackCooldown();
		}
	}
}