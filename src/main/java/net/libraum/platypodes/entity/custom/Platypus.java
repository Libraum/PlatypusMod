package net.libraum.platypodes.entity.custom;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Dynamic;
import net.libraum.platypodes.entity.ModEntities;
import net.libraum.platypodes.entity.ai.PlatypusAI;
import net.libraum.platypodes.util.ModConfig;
import net.libraum.platypodes.util.ModSensorType;
import net.libraum.platypodes.items.ModItems;
import net.libraum.platypodes.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

import static net.minecraft.world.entity.animal.WaterAnimal.checkSurfaceWaterAnimalSpawnRules;

public class Platypus extends Axolotl {
    private static final EntityDataAccessor<Integer> DATA_POISON_SUPPLY;
    public Platypus(EntityType<? extends Platypus> entityType, Level level) {
        super(entityType, level);
    }
    protected static final ImmutableList<? extends SensorType<? extends Sensor<? super Platypus>>> SENSOR_TYPES = ImmutableList.of(
             SensorType.NEAREST_LIVING_ENTITIES, SensorType.NEAREST_ADULT, SensorType.HURT_BY, ModSensorType.PLATYPUS_TEMPTATIONS
    );
    static {
        DATA_POISON_SUPPLY = SynchedEntityData.defineId(Platypus.class, EntityDataSerializers.INT);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_POISON_SUPPLY, this.getMaxPoisonSupply());
    }

    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.putInt("Poison", this.getPoisonSupply());
    }

    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        this.setPoisonSupply(compoundTag.getInt("Poison"));
    }

    public static AttributeSupplier.Builder createPlatypusAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 14.0) /* Default: 14.0 */
                .add(Attributes.MOVEMENT_SPEED, 1.0) /* Default: 1.0 */
                .add(Attributes.ATTACK_DAMAGE, 2.0); /* Default: 2.0 */
    }

    /** Spawn Conditions */
    public static boolean checkPlatypusSpawnRules(EntityType<? extends LivingEntity> entityType, LevelAccessor levelAccessor, MobSpawnType mobSpawnType, BlockPos blockPos, RandomSource randomSource) {
        float na = 0.7403491f; /* Dawn Start */
        float nz = 0.8348362f; /* Dawn End */
        float ka = 0.21548219f; /* Dusk Start */
        float kz = 0.26870248f; /* Dusk End */
        if (ModConfig.enableSpawns) {
            if (ModConfig.spawnDuring == ModConfig.SpawnDuring.DAWNANDDUSK) {
                if (levelAccessor.getTimeOfDay(0) >= na && levelAccessor.getTimeOfDay(0) <= nz && randomSource.nextFloat() < 0.15f) {
                    return checkSurfaceWaterAnimalSpawnRules((EntityType<? extends WaterAnimal>) entityType, levelAccessor, mobSpawnType, blockPos, randomSource);
                }
                if (levelAccessor.getTimeOfDay(0) >= ka && levelAccessor.getTimeOfDay(0) <= kz && randomSource.nextFloat() < 0.15f) {
                    return checkSurfaceWaterAnimalSpawnRules((EntityType<? extends WaterAnimal>) entityType, levelAccessor, mobSpawnType, blockPos, randomSource);
                }
            }
            if (ModConfig.spawnDuring == ModConfig.SpawnDuring.NIGHT) {
                if (levelAccessor.getTimeOfDay(0) >= ka && levelAccessor.getTimeOfDay(0) <= na && randomSource.nextFloat() < 0.15f) {
                    return checkSurfaceWaterAnimalSpawnRules((EntityType<? extends WaterAnimal>) entityType, levelAccessor, mobSpawnType, blockPos, randomSource);
                }
            }
            if (ModConfig.spawnDuring == ModConfig.SpawnDuring.ALLDAY) {
                if (randomSource.nextFloat() < 0.15f) {
                    return checkSurfaceWaterAnimalSpawnRules((EntityType<? extends WaterAnimal>) entityType, levelAccessor, mobSpawnType, blockPos, randomSource);
                }
            }
        }
            return false;
    }

    /** Behaviour */
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new PlatypusBreatheAirGoal(this, 0.5));
//        this.goalSelector.addGoal(1, new PanicGoal(this, 0.5));
//        this.goalSelector.addGoal(1, new BreedGoal(this, 1.0));
//        this.goalSelector.addGoal(2, new TemptGoal(this, 0.5, Ingredient.of(ModItems.YABBY), false));
//        this.goalSelector.addGoal(2, new FollowParentGoal(this, 0.5));
//        this.goalSelector.addGoal(4, new PlatypusRandomStrollGoal(this, 0.4));
//        this.goalSelector.addGoal(4, new PlatypusRandomSwimmingGoal(this, 1.0, 1));
//        this.goalSelector.addGoal(5, new PlatypusGoToWaterGoal(this, 0.5));
//        this.goalSelector.addGoal(3, new PlatypusGoToBeachGoal(this, 1.0));
//        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    @Override
    protected Brain<?> makeBrain(Dynamic<?> dynamic) {
        return PlatypusAI.create(Brain.provider(MEMORY_TYPES, SENSOR_TYPES).makeBrain(dynamic));
    }

    public Brain<Axolotl> getBrain() { return super.getBrain(); }

    /** Breeding + Bucket */
    @Override
    public boolean isFood(ItemStack itemStack) {
        return itemStack.is(ModItems.YABBY);
    }

    private static boolean shouldBabyBeDifferent(RandomSource random) {
        return random.nextInt(ModConfig.rareVariantChance) == 0; /* Default: 1200 */
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob entity) {
        Platypus platypus = ModEntities.PLATYPUS.create(world);
        if (platypus != null) {
            Variant variant;
            if (shouldBabyBeDifferent(this.random)) {
                variant = Platypus.Variant.getRareSpawnVariant(this.random);
            } else {
                variant = this.random.nextBoolean() ? this.getVariant() : ((Platypus)entity).getVariant();
            }

            platypus.setVariant(variant);
            platypus.setPersistenceRequired();
        }

        return platypus;
    }

    /** Interact */
    @Override
    public ItemStack getBucketItemStack() { return new ItemStack(ModItems.PLATYPUS_BUCKET); }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand interactionHand) {
        ItemStack itemStack = player.getItemInHand(interactionHand);
        if (itemStack.is(Items.GLASS_BOTTLE) && Platypus.this.getPoisonSupply() == this.getMaxPoisonSupply()) {
            player.playSound(SoundEvents.BOTTLE_FILL, 1.0f, 1.0f);
            ItemStack itemStack2 = ItemUtils.createFilledResult(itemStack, player, PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.POISON));
            player.setItemInHand(interactionHand, itemStack2);
            if (!player.isCreative()) {
                Platypus.this.setPoisonSupply(0);
            }
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        } else {
            return super.mobInteract(player, interactionHand);
        }
    }

    /** Breathe on land + drowning */
    @Override
    protected void handleAirSupply(int air) {
        if (this.isAlive() && this.isInWaterRainOrBubble()) {
            this.setAirSupply(air - 1);
            if (this.getAirSupply() == -20) {
                this.setAirSupply(0);
                this.hurt(this.damageSources().drown(), 2.0F);
            }
        } else {
            this.setAirSupply(this.getMaxAirSupply());
        }
    }

    @Override
    public int getMaxAirSupply() {
        return ModConfig.maxAirSupply;
    } /* Default: 6000 */

    @Override
    public boolean canBreatheUnderwater() {
        return false;
    }

    /** Poison Handler */
    public void baseTick() {
        int i = this.getPoisonSupply();
        super.baseTick();
        if (!this.isNoAi()) {
            this.handlePoisonSupply(i);
        }
    }

    public int getMaxPoisonSupply() {
        return ModConfig.maxPoisonSupply;
    } /* Default: 6000 */

    public int getPoisonSupply() {
        return this.entityData.get(DATA_POISON_SUPPLY);
    }

    public void setPoisonSupply(int i) {
        this.entityData.set(DATA_POISON_SUPPLY, i);
    }

    protected void handlePoisonSupply(int tick) {
        if (this.isAlive() && !this.isBaby()) {
            this.setPoisonSupply(tick + 1);
            if (this.getPoisonSupply() >= this.getMaxPoisonSupply()) {
                this.setPoisonSupply(this.getMaxPoisonSupply());
            }
        } else {
            this.setPoisonSupply(0);
        }
    }

    @Override
    public boolean hurt(DamageSource damageSource, float f) {
        boolean bl = super.hurt(damageSource, f);
        if (bl && damageSource.getDirectEntity() instanceof LivingEntity entity) {
            if (this.isAlive()
                    && !damageSource.isCreativePlayer()
                    && this.getPoisonSupply() == this.getMaxPoisonSupply()) {
                entity.addEffect(new MobEffectInstance(MobEffects.POISON, 100));
                this.playSound(ModSounds.ENTITY_PLATYPUS_ATTACK, 1.0F, 1.0F);
                this.setPoisonSupply(0);
            }
        }
        return bl;
    }

    /** Sound Events */
    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return this.isInWater() ? ModSounds.ENTITY_PLATYPUS_IDLE_WATER : ModSounds.ENTITY_PLATYPUS_IDLE_AIR;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.ENTITY_PLATYPUS_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.ENTITY_PLATYPUS_DEATH;
    }

    @Nullable
    @Override
    protected SoundEvent getSwimSplashSound() {
        return ModSounds.ENTITY_PLATYPUS_SPLASH;
    }

    @Nullable
    @Override
    protected SoundEvent getSwimSound() {
        return ModSounds.ENTITY_PLATYPUS_SWIM;
    }

    @Nullable
    @Override
    public SoundEvent getPickupSound() {
        return ModSounds.ITEM_BUCKET_FILL_PLATYPUS;
    }

    /** Custom Goals */
    static class PlatypusBreatheAirGoal extends Goal {
        private final Platypus platypus;
        private final double speed;

        public PlatypusBreatheAirGoal(Platypus platypus, Double d) {
            this.platypus = platypus;
            this.speed = d;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        public boolean canUse() {
            return this.platypus.getAirSupply() < 140;
        }

        public boolean canContinueToUse() {
            return this.canUse();
        }

        public boolean isInterruptable() {
            return false;
        }

        public void start() {
            this.findAirPosition();
        }

        private void findAirPosition() {
            Iterable<BlockPos> iterable = BlockPos.betweenClosed(
                    Mth.floor(this.platypus.getX() - (double)1.0F),
                    this.platypus.getBlockY(),
                    Mth.floor(this.platypus.getZ() - (double)1.0F),
                    Mth.floor(this.platypus.getX() + (double)1.0F),
                    Mth.floor(this.platypus.getY() + (double)8.0F),
                    Mth.floor(this.platypus.getZ() + (double)1.0F)
            );
            BlockPos blockPos = null;

            for(BlockPos blockPos2 : iterable) {
                if (this.givesAir(this.platypus.level(), blockPos2)) {
                    blockPos = blockPos2;
                    break;
                }
            }

            if (blockPos == null) {
                blockPos = BlockPos.containing(this.platypus.getX(), this.platypus.getY() + (double)8.0F, this.platypus.getZ());
            }

            this.platypus.getNavigation().moveTo(
                    (double)blockPos.getX(),
                    (double)(blockPos.getY() + 1),
                    (double)blockPos.getZ(),
                    (double)this.speed
            );
        }

        public void tick() {
            this.findAirPosition();
            this.platypus.moveRelative(0.02F, new Vec3((double)this.platypus.xxa, (double)this.platypus.yya, (double)this.platypus.zza));
            this.platypus.move(MoverType.SELF, this.platypus.getDeltaMovement());
        }

        private boolean givesAir(LevelReader levelReader, BlockPos blockPos) {
            BlockState blockState = levelReader.getBlockState(blockPos);
            return (levelReader.getFluidState(blockPos).isEmpty() || blockState.is(Blocks.BUBBLE_COLUMN)) && blockState.isPathfindable(levelReader, blockPos, PathComputationType.LAND);
        }
    }
}
