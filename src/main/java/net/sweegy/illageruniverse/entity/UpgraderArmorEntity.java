package net.sweegy.illageruniverse.entity;

import net.sweegy.illageruniverse.procedures.UpgraderArmorOnEntityTickUpdateProcedure;
import net.sweegy.illageruniverse.init.IllagerUniverseModEntities;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.nbt.CompoundTag;

import javax.annotation.Nullable;

import java.util.UUID;

public class UpgraderArmorEntity extends PathfinderMob {
	public UpgraderArmorEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(IllagerUniverseModEntities.UPGRADER_ARMOR.get(), world);
	}

	public UpgraderArmorEntity(EntityType<UpgraderArmorEntity> type, Level world) {
		super(type, world);
		setMaxUpStep(0.6f);
		xpReward = 0;
		setNoAi(false);
		setPersistenceRequired();
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
	}

	public void setItemSlot(EquipmentSlot p_21416_, ItemStack p_21417_) {
		if (canModifyArmor)
			super.setItemSlot(p_21416_, p_21417_);
	}

	public boolean isAlliedTo(Entity entity) {
		if (getOwner() != null)
			return owner.isAlliedTo(entity);
		return super.isAlliedTo(entity);
	}

	public boolean canModifyArmor = true;
	public int lifeTime;
	@Nullable
	private LivingEntity owner;
	@Nullable
	private UUID ownerUUID;

	public void setOwner(@Nullable LivingEntity p_36939_) {
		this.owner = p_36939_;
		this.ownerUUID = p_36939_ == null ? null : p_36939_.getUUID();
	}

	@Nullable
	public LivingEntity getOwner() {
		if (this.owner == null && this.ownerUUID != null && this.level() instanceof ServerLevel) {
			Entity entity = ((ServerLevel) this.level()).getEntity(this.ownerUUID);
			if (entity instanceof LivingEntity) {
				this.owner = (LivingEntity) entity;
			}
		}
		return this.owner;
	}

	public void readAdditionalSaveData(CompoundTag p_36941_) {
		super.readAdditionalSaveData(p_36941_);
		if (p_36941_.hasUUID("Owner")) {
			this.ownerUUID = p_36941_.getUUID("Owner");
		}
		if (p_36941_.contains("lifeTime", 99))
			lifeTime = p_36941_.getInt("lifeTime");
		if (p_36941_.contains("canModifyArmor", 99))
			canModifyArmor = p_36941_.getBoolean("canModifyArmor");
	}

	public void addAdditionalSaveData(CompoundTag p_36943_) {
		super.addAdditionalSaveData(p_36943_);
		if (this.ownerUUID != null) {
			p_36943_.putUUID("Owner", this.ownerUUID);
		}
		p_36943_.putInt("lifeTime", lifeTime);
		p_36943_.putBoolean("canModifyArmor", canModifyArmor);
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(""));
	}

	@Override
	public SoundEvent getDeathSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(""));
	}

	{
	}

	@Override
	public MobType getMobType() {
		return MobType.UNDEFINED;
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return false;
	}

	@Override
	public double getMyRidingOffset() {
		return -0.35D;
	}

	@Override
	public void baseTick() {
		super.baseTick();
		UpgraderArmorOnEntityTickUpdateProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ(), this);
	}

	@Override
	public boolean isPushable() {
		return false;
	}

	@Override
	protected void doPush(Entity entityIn) {
	}

	@Override
	protected void pushEntities() {
	}

	public static void init() {
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
		builder = builder.add(Attributes.MAX_HEALTH, 10);
		builder = builder.add(Attributes.ARMOR, 0);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 3);
		builder = builder.add(Attributes.FOLLOW_RANGE, 16);
		return builder;
	}
}