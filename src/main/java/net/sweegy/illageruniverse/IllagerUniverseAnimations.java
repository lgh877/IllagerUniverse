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

import net.sweegy.illageruniverse.client.model.animations.quivagerAnimation;
import net.sweegy.illageruniverse.client.model.animations.IllagerBanditAnimation;

import net.minecraft.client.animation.AnimationDefinition;

public class IllagerUniverseAnimations {
	public static final AnimationDefinition[] QuivagerAnimations = {//
			quivagerAnimation.stab, //
			quivagerAnimation.throwAttack, //
			quivagerAnimation.eat, //
	};
}