package net.sweegy.illageruniverse.procedures;

import net.minecraft.world.entity.Entity;

public class DistToMobFlatSquareProcedure {
	public static double execute(double x, double z, Entity entity) {
		if (entity == null)
			return 0;
		return Math.pow(entity.getX() - x, 2) + Math.pow(entity.getZ() - z, 2);
	}
}