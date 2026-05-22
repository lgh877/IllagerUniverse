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

import org.checkerframework.checker.units.qual.g;

import net.minecraft.world.phys.Vec3;
import net.minecraft.util.Mth;

public class VectorHelper {
	public static Vec3 calculateViewVector(float xRot, float yRot) {
		float f = xRot * (Mth.PI / 180F);
		float f1 = -yRot * (Mth.PI / 180F);
		float f2 = Mth.cos(f1);
		float f3 = Mth.sin(f1);
		float f4 = Mth.cos(f);
		float f5 = Mth.sin(f);
		return new Vec3((double) (f3 * f4), (double) (-f5), (double) (f2 * f4));
	}

	public static Vec3 calculateFlatViewVector(float yRot) {
		float f1 = -yRot * (Mth.PI / 180F);
		float f2 = Mth.cos(f1);
		float f3 = Mth.sin(f1);
		return new Vec3((double) f3, 0, (double) f2);
	}

	/**
	 * Calculates the required initial horizontal velocity to travel a specific distance 
	 * within a set number of ticks, considering horizontal drag.
	 *
	 * @param d  The target horizontal distance to travel (in blocks).
	 * @param n  The total flight time (in ticks).
	 * @param f  The horizontal drag coefficient (e.g., 0.91 for LivingEntities, 0.99 for arrows).
	 * @return   The required initial horizontal velocity component.
	 */
	public static double getRequiredVelocityFlat(double d, int n, double f) {
		double S = f * (1 - Math.pow(f, n)) / (1 - f);
		return d / S;
	}

	/**
	 * Calculates the required initial vertical velocity to reach a target height 
	 * within a set number of ticks, considering both vertical drag and gravity.
	 *
	 * @param d  The target vertical displacement (Δy) to reach (in blocks).
	 * @param n  The total flight time (in ticks).
	 * @param f  The vertical drag coefficient (e.g., 0.98 for LivingEntities, 0.99 for arrows).
	 * @param g  The gravity acceleration per tick (e.g., 0.08 for players, 0.05 for arrows).
	 * @return   The required initial vertical velocity component.
	 */
	public static double getRequiredVelocity(double d, int n, double f, double g) {
		double S = f * (1 - Math.pow(f, n)) / (1 - f);
		double v_term = (-g * f) / (1 - f);
		double gravityDist = v_term * (n - S / f);
		return (d - gravityDist) / S;
	}

	/**
	 * Calculates the optimal launch velocity to reach a target at a given distance and height 
	 * while attempting to match a specific initial speed.
	 *
	 * @param d        Horizontal distance to the target (in blocks).
	 * @param dy       Height difference to the target (target Y - source Y).
	 * @param targetS  The desired initial launch speed (magnitude of the velocity vector).
	 * @param f        The drag coefficient (e.g., 0.91 for players, 0.99 for arrows).
	 * @param g        The gravity acceleration per tick (e.g., 0.08 for players, 0.05 for arrows).
	 * @param maxIter  The maximum number of ticks (flight time) to simulate for searching the trajectory.
	 * @return         A Vec3 containing the calculated horizontal (x) and vertical (y) velocity components.
	 */
	public static Vec3 getLightweightVelocity(double d, double dy, double targetS, double f, double g, int maxIter) {
		double Sn = 0;
		double Gn = 0;
		double fn = 1.0;
		double bestVx = 0;
		double bestVy = 0;
		double minDiff = Double.MAX_VALUE;
		for (int n = 1; n < maxIter; n++) {
			fn *= f;
			Sn += fn;
			if (n > 1) {
				Gn += (1 - fn / f) / (1 - f) * g;
			}
			double vx = d / Sn;
			double vy = (dy + Gn) / Sn;
			double currentS = Math.sqrt(vx * vx + vy * vy);
			double diff = Math.abs(currentS - targetS);
			if (diff < minDiff) {
				minDiff = diff;
				bestVx = vx;
				bestVy = vy;
			}
			if (currentS < targetS) {
				break;
			}
		}
		return new Vec3(bestVx, bestVy, 0);
	}
}