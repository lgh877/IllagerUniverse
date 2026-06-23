package net.sweegy.illageruniverse.procedures;

import net.sweegy.illageruniverse.entity.UpgraderEntity;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.Mth;

public class UpgraderOnInitialEntitySpawnProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		/*
		if (entity instanceof UpgraderEntity _datEntSetI)
		_datEntSetI.getEntityData().set(UpgraderEntity.DATA_variant, (int)(world.getDifficulty()==Difficulty.PEACEFUL?0:Mth.nextInt(RandomSource.create(), 1, 10)));*/
		UpgraderEntity mob = (UpgraderEntity) entity;
		mob.setVariant(Mth.nextInt(mob.getRandom(), 0, world.getDifficulty().getId()) - 1);
	}
}