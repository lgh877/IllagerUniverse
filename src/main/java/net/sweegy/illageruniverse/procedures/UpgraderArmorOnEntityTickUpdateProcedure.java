package net.sweegy.illageruniverse.procedures;

import net.sweegy.illageruniverse.entity.UpgraderArmorEntity;
import net.sweegy.illageruniverse.ICanWearArmors;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;

import java.util.Comparator;

public class UpgraderArmorOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double slotType = 0;
		if (!world.isClientSide()) {
			entity.push(0, 0.04, 0);
			UpgraderArmorEntity mob = (UpgraderArmorEntity) entity;
			slotType = entity.getPersistentData().getDouble("slotType");
			{
				final Vec3 _center = new Vec3(x, (y + 0.6), z);
				for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(2 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList()) {
					if ((entityiterator instanceof ICanWearArmors) && mob.isAlliedTo(entityiterator)
							&& (entityiterator instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, (int) slotType)) : ItemStack.EMPTY).getItem() == ItemStack.EMPTY.getItem()) {
						if (entityiterator instanceof LivingEntity _living) {
							_living.setItemSlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, (int) slotType),
									(entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, (int) slotType)) : ItemStack.EMPTY));
						}
						world.gameEvent(entityiterator, GameEvent.EQUIP, new Vec3(x, y, z));
						if (!entity.level().isClientSide())
							entity.discard();
						break;
					}
				}
			}
			if ((entity.getDeltaMovement()).length() < 0.1 && mob.lifeTime++ > 100) {
				if (!entity.level().isClientSide())
					entity.discard();
			}
		}
	}
}