package net.sweegy.illageruniverse.entity;

import net.sweegy.illageruniverse.procedures.UpgraderOnInitialEntitySpawnProcedure;
import net.sweegy.illageruniverse.procedures.UpgraderOnEntityTickUpdateProcedure;
import net.sweegy.illageruniverse.init.IllagerUniverseModEntities;
import net.sweegy.illageruniverse.IActionStateMob;
import net.sweegy.illageruniverse.FadingOutAnimation;
import net.sweegy.illageruniverse.DoNothingGoal;
import net.sweegy.illageruniverse.ActionStateMobMeleeAttackSprintGoal;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.nbt.CompoundTag;

import javax.annotation.Nullable;

import java.util.List;
import java.util.ArrayList;

public class UpgraderEntity extends Raider implements IActionStateMob {
	public static final EntityDataAccessor<Integer> DATA_actionState = SynchedEntityData.defineId(UpgraderEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_variant = SynchedEntityData.defineId(UpgraderEntity.class, EntityDataSerializers.INT);

	public UpgraderEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(IllagerUniverseModEntities.UPGRADER.get(), world);
	}

	public UpgraderEntity(EntityType<UpgraderEntity> type, Level world) {
		super(type, world);
		setMaxUpStep(0.6f);
		xpReward = 5;
		setNoAi(false);
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_actionState, 0);
		this.entityData.define(DATA_variant, 0);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		goalSelector.addGoal(0, new DoNothingGoal(this));
		goalSelector.addGoal(1, new ActionStateMobMeleeAttackSprintGoal(this, 1, false));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, false) {
			@Override
			protected double getAttackReachSqr(LivingEntity entity) {
				return this.mob.getBbWidth() * this.mob.getBbWidth() + entity.getBbWidth();
			}
		});
		this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1));
		this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(5, new FloatGoal(this));
	}

	public int actionTicks, animTicks, animTicksO, prevActionState = 0, fadeInTicks = 0, animIdx = 0, variantIdx = 0;
	public ArrayList<FadingOutAnimation> fadingAnims = new ArrayList<FadingOutAnimation>();

	public int getActionState() {
		return entityData.get(DATA_actionState);
	}

	public void setActionState(int input) {
		entityData.set(DATA_actionState, input);
	}

	public boolean shouldNotMove() {
		return entityData.get(DATA_actionState) == 1;
	}

	public List<FadingOutAnimation> getFadingAnims() {
		return fadingAnims;
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
				if (animTicks != 0)
					getFadingAnims().add(new FadingOutAnimation(getFadingAnims(), animTicks, Math.min(animTicks, 5), prevActionState));
				prevActionState = animIdx = entityData.get(DATA_actionState);
				animTicks = animTicksO = fadeInTicks = 0;
			} else if (p_21104_.equals(DATA_variant)) {
				variantIdx = entityData.get(DATA_variant);
			}
		}
	}

	{
	}

	@Override
	public MobType getMobType() {
		return MobType.ILLAGER;
	}

	@Override
	public SoundEvent getAmbientSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("illager_universe:upgrader_idle"));
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("illager_universe:upgrader_hurt"));
	}

	@Override
	public SoundEvent getDeathSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("illager_universe:upgrader_death"));
	}

	@Override
	public SoundEvent getCelebrateSound() {
		return SoundEvents.EMPTY;
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData livingdata, @Nullable CompoundTag tag) {
		SpawnGroupData retval = super.finalizeSpawn(world, difficulty, reason, livingdata, tag);
		UpgraderOnInitialEntitySpawnProcedure.execute(world, this);
		return retval;
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("DataactionState", this.entityData.get(DATA_actionState));
		compound.putInt("Datavariant", this.entityData.get(DATA_variant));
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("DataactionState"))
			this.entityData.set(DATA_actionState, compound.getInt("DataactionState"));
		if (compound.contains("Datavariant"))
			this.entityData.set(DATA_variant, compound.getInt("Datavariant"));
	}

	@Override
	public void baseTick() {
		super.baseTick();
		UpgraderOnEntityTickUpdateProcedure.execute(this.level(), this);
	}

	public static void init() {
		Raid.RaiderType.create("upgrader", IllagerUniverseModEntities.UPGRADER.get(), new int[]{0, 4, 3, 3, 4, 4, 4, 2});
	}

	@Override
	public void applyRaidBuffs(int num, boolean logic) {
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
		builder = builder.add(Attributes.MAX_HEALTH, 24);
		builder = builder.add(Attributes.ARMOR, 3);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 1);
		builder = builder.add(Attributes.FOLLOW_RANGE, 16);
		return builder;
	}
}