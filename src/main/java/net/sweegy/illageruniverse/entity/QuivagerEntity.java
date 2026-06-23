package net.sweegy.illageruniverse.entity;

import net.sweegy.illageruniverse.procedures.QuivagerOnEntityTickUpdateProcedure;
import net.sweegy.illageruniverse.init.IllagerUniverseModItems;
import net.sweegy.illageruniverse.init.IllagerUniverseModEntities;
import net.sweegy.illageruniverse.ICanWearArmors;
import net.sweegy.illageruniverse.IActionStateMob;
import net.sweegy.illageruniverse.DoNothingGoal;
import net.sweegy.illageruniverse.ActionStateMobMeleeAttackSprintGoal;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.nbt.CompoundTag;

public class QuivagerEntity extends AbstractIllager implements IActionStateMob, ICanWearArmors {
	public static final EntityDataAccessor<Integer> DATA_actionState = SynchedEntityData.defineId(QuivagerEntity.class, EntityDataSerializers.INT);

	public QuivagerEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(IllagerUniverseModEntities.QUIVAGER.get(), world);
	}

	public QuivagerEntity(EntityType<QuivagerEntity> type, Level world) {
		super(type, world);
		setMaxUpStep(0.6f);
		xpReward = 0;
		setNoAi(false);
		this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(IllagerUniverseModItems.FORK.get()));
		this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(IllagerUniverseModItems.CHEF_ARMOR_SET_HELMET.get()));
		this.setItemSlot(EquipmentSlot.CHEST, new ItemStack(IllagerUniverseModItems.CHEF_ARMOR_SET_CHESTPLATE.get()));
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_actionState, 0);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		goalSelector.addGoal(0, new DoNothingGoal(this));
		goalSelector.addGoal(1, new ActionStateMobMeleeAttackSprintGoal(this, 1, false));
		this.goalSelector.addGoal(1, new RandomStrollGoal(this, 1));
		this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(4, new FloatGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Player.class, true, false));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, AbstractVillager.class, true, false));
		this.targetSelector.addGoal(5, new NearestAttackableTargetGoal(this, AbstractGolem.class, true, false));
	}

	public int actionTicks, animTicks, animTicksO, walkTransitionAmplitude, walkTransitionAmplitudeO;
	public LivingEntity lockedTarget;

	public int getActionState() {
		return entityData.get(DATA_actionState);
	}

	public void setActionState(int input) {
		entityData.set(DATA_actionState, input);
	}

	public boolean shouldNotMove() {
		return entityData.get(DATA_actionState) > 1;
	}

	public boolean isAlliedTo(Entity p_33314_) {
		if (super.isAlliedTo(p_33314_)) {
			return true;
		} else if (p_33314_ instanceof LivingEntity && ((LivingEntity) p_33314_).getMobType() == MobType.ILLAGER) {
			return this.getTeam() == null && p_33314_.getTeam() == null;
		} else {
			return false;
		}
	}

	public void onSyncedDataUpdated(EntityDataAccessor<?> p_21104_) {
		super.onSyncedDataUpdated(p_21104_);
		if (level().isClientSide()) {
			if (p_21104_.equals(DATA_actionState)) {
				animTicks = animTicksO = 0;
			}
		}
	}

	{
	}

	@Override
	public SoundEvent getAmbientSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.vindicator.ambient"));
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.vindicator.hurt"));
	}

	@Override
	public SoundEvent getDeathSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.vindicator.death"));
	}

	@Override
	public SoundEvent getCelebrateSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.vindicator.celebrate"));
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("DataactionState", this.entityData.get(DATA_actionState));
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("DataactionState"))
			this.entityData.set(DATA_actionState, compound.getInt("DataactionState"));
	}

	@Override
	public void baseTick() {
		super.baseTick();
		QuivagerOnEntityTickUpdateProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ(), this);
	}

	public static void init() {
		Raid.RaiderType.create("quivager", IllagerUniverseModEntities.QUIVAGER.get(), new int[]{0, 0, 1, 0, 0, 0, 2, 0});
	}

	@Override
	public void applyRaidBuffs(int num, boolean logic) {
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
		builder = builder.add(Attributes.MAX_HEALTH, 25);
		builder = builder.add(Attributes.ARMOR, 0);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 1);
		builder = builder.add(Attributes.FOLLOW_RANGE, 64);
		return builder;
	}
}