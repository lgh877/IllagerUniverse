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

public class ActionStateMobMeleeAttackGoal<T extends PathfinderMob & IActionStateMob> extends MeleeAttackGoal {
	protected final T actionStateMob;
	private final int meleeAttackState;

	public ActionStateMobMeleeAttackGoal(T a, double b, boolean c, int meleeAttackState) {
		super(a, b, c);
		actionStateMob = a;
		this.meleeAttackState = meleeAttackState;
	}

	public ActionStateMobMeleeAttackGoal(T a, double b, boolean c) {
		this(a, b, c, 1);
	}

	@Override
	protected boolean isTimeToAttack() {
		return !actionStateMob.shouldNotMove();
	}

	@Override
	protected void resetAttackCooldown() {
		actionStateMob.setActionState(meleeAttackState);
		super.resetAttackCooldown();
	}
}