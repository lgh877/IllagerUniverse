package net.sweegy.illageruniverse.entity;

import net.sweegy.illageruniverse.procedures.BanditOnEntityTickUpdateProcedure;
import net.sweegy.illageruniverse.init.IllagerUniverseModItems;
import net.sweegy.illageruniverse.init.IllagerUniverseModEntities;
import net.sweegy.illageruniverse.client.model.animations.IllagerBanditAnimation;
import net.sweegy.illageruniverse.IActionStateMob;
import net.sweegy.illageruniverse.DoNothingGoal;
import net.sweegy.illageruniverse.ActionStateMobMeleeAttackSprintGoal;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.common.DungeonHooks;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.animal.IronGolem;
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
import net.minecraft.client.animation.AnimationDefinition;

public class BanditEntity extends AbstractIllager implements IActionStateMob {
	public static final EntityDataAccessor<Integer> DATA_actionState = SynchedEntityData.defineId(BanditEntity.class, EntityDataSerializers.INT);
	public final AnimationState animationState0 = new AnimationState();

	public BanditEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(IllagerUniverseModEntities.BANDIT.get(), world);
	}

	public BanditEntity(EntityType<BanditEntity> type, Level world) {
		super(type, world);
		setMaxUpStep(0.6f);
		xpReward = 0;
		setNoAi(false);
		this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(IllagerUniverseModItems.IRON_CUTLASS.get()));
		this.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(IllagerUniverseModItems.IRON_CUTLASS.get()));
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_actionState, 0);
		this.entityData.define(DATA_modelPartsToHide, 0);
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
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
	}

	public AnimationDefinition currentAnimation;
	public static final EntityDataAccessor<Integer> DATA_modelPartsToHide = SynchedEntityData.defineId(BanditEntity.class, EntityDataSerializers.INT);

	@Override
	public void setItemSlot(EquipmentSlot slot, ItemStack itemStack) {
		ItemStack previousItem = this.getItemBySlot(slot);
		super.setItemSlot(slot, itemStack);
		if (!this.level().isClientSide) {
			if (!previousItem.isEmpty() && (itemStack.isEmpty() || previousItem.getItem() != itemStack.getItem())) {
				switch (slot) {
					case HEAD -> entityData.set(DATA_modelPartsToHide, Math.max(0, entityData.get(DATA_modelPartsToHide) & ~1));
					case CHEST -> entityData.set(DATA_modelPartsToHide, Math.max(0, entityData.get(DATA_modelPartsToHide) & ~2));
				}
			}
			if (!itemStack.isEmpty() && (previousItem.isEmpty() || previousItem.getItem() != itemStack.getItem())) {
				switch (slot) {
					case HEAD -> entityData.set(DATA_modelPartsToHide, entityData.get(DATA_modelPartsToHide) | 1);
					case CHEST -> entityData.set(DATA_modelPartsToHide, entityData.get(DATA_modelPartsToHide) | 2);
				}
			}
		}
	}

	public boolean getModelPartToHide(int p_20292_) {
		return (this.entityData.get(DATA_modelPartsToHide) & 1 << p_20292_) != 0;
	}

	public int actionTicks, animTicks, animTicksO, walkTransitionAmplitude, walkTransitionAmplitudeO;

	public int getActionState() {
		return entityData.get(DATA_actionState);
	}

	public void setActionState(int input) {
		entityData.set(DATA_actionState, input);
	}

	public boolean shouldNotMove() {
		return entityData.get(DATA_actionState) != 0;
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
				switch (entityData.get(DATA_actionState)) {
					case 1 -> currentAnimation = IllagerBanditAnimation.attack;
					case 2 -> currentAnimation = IllagerBanditAnimation.lunge;
				}
			}
		}
	}

	/* codes storage
					float partialTicks = ageInTicks - entity.tickCount;
					float walkTransitionAmplitude = Mth.lerp(partialTicks, entity.walkTransitionAmplitudeO, entity.walkTransitionAmplitude) / 3F;
					this.animate(entity.animationState0, IllagerBanditAnimation.idle, ageInTicks, 1f);
					this.animateWalk(IllagerBanditAnimation.walk, limbSwing, limbSwingAmount * walkTransitionAmplitude, 1f, 2f);
					this.animateWalk(IllagerBanditAnimation.aggro, limbSwing, limbSwingAmount * (1 - walkTransitionAmplitude), 1f, 2f);
					if (entity.getActionState() != 0) {
						float animTicks = Mth.lerp(partialTicks, entity.animTicksO, entity.animTicks);
						this.animateWalk(IllagerUniverseAnimations.BanditAnimations[entity.getActionState() - 1], animTicks, 1f, 1f, 1f);
					}
	*/
	{
	}

	@Override
	public MobType getMobType() {
		return MobType.ILLAGER;
	}

	@Override
	public SoundEvent getAmbientSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.pillager.ambient"));
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.pillager.hurt"));
	}

	@Override
	public SoundEvent getDeathSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.pillager.death"));
	}

	@Override
	public SoundEvent getCelebrateSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.pillager.celebrate"));
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
	public void tick() {
		super.tick();
		if (this.level().isClientSide()) {
			this.animationState0.animateWhen(true, this.tickCount);
		}
	}

	@Override
	public void baseTick() {
		super.baseTick();
		BanditOnEntityTickUpdateProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ(), this);
	}

	public static void init() {
		DungeonHooks.addDungeonMob(IllagerUniverseModEntities.BANDIT.get(), 180);
		Raid.RaiderType.create("bandit", IllagerUniverseModEntities.BANDIT.get(), new int[]{0, 0, 2, 3, 1, 1, 0, 3});
	}

	@Override
	public void applyRaidBuffs(int num, boolean logic) {
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
		builder = builder.add(Attributes.MAX_HEALTH, 16);
		builder = builder.add(Attributes.ARMOR, 0);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 5);
		builder = builder.add(Attributes.FOLLOW_RANGE, 16);
		return builder;
	}
}