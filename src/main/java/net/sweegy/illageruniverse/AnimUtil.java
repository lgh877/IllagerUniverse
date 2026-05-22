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

import org.joml.Vector3f;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.animation.AnimationDefinition;

public class AnimUtil {
	public static final Vector3f ANIMATION_VECTOR_CACHE = new Vector3f();

	public static void animateWalkLike(HierarchicalModel<?> model, AnimationDefinition animDef, float limbSwing, float limbSwingAmount, float speed) {
		long i = (long) (limbSwing * 50.0F * speed);
		float f = limbSwingAmount;
		KeyframeAnimations.animate(model, animDef, i, limbSwingAmount, ANIMATION_VECTOR_CACHE);
	}
}