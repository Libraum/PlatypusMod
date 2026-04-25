package net.libraum.platypodes.entity.custom;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Dynamic;
import net.libraum.platypodes.entity.ModEntities;
import net.libraum.platypodes.entity.ai.PlatypusAI;
import net.libraum.platypodes.util.ModSensorType;
import net.libraum.platypodes.items.ModItems;
import net.libraum.platypodes.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.item.ItemStack;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class PlatypusEntity extends Axolotl {
    public PlatypusEntity(EntityType<? extends PlatypusEntity> entityType, Level world) {
        super(entityType, world);
    }
    protected static final ImmutableList<? extends SensorType<? extends Sensor<? super PlatypusEntity>>> SENSOR_TYPES = ImmutableList.of(
             SensorType.NEAREST_LIVING_ENTITIES, SensorType.NEAREST_ADULT, SensorType.HURT_BY, ModSensorType.PLATYPUS_TEMPTATIONS
    );

    public static AttributeSupplier.Builder createPlatypusAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 14)
                .add(Attributes.MOVEMENT_SPEED, 1.0)
                .add(Attributes.ATTACK_DAMAGE, 2.0);
    }

    /** Brain */
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new PlatypusBreatheAirGoal(this, 0.5));
    }

    @Override
    protected Brain<?> makeBrain(Dynamic<?> dynamic) {
        return PlatypusAI.create(Brain.provider(MEMORY_TYPES, SENSOR_TYPES).makeBrain(dynamic));
    }

    /** Breeding + Bucket */
    @Override
    public boolean isFood(ItemStack itemStack) {
        return itemStack.is(ModItems.YABBY);
    }

    private static boolean shouldBabyBeDifferent(RandomSource random) {
        return random.nextInt(1200) == 0;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob entity) {
        PlatypusEntity platypusEntity = ModEntities.PLATYPUS.create(world);
        if (platypusEntity != null) {
            Variant variant;
            if (shouldBabyBeDifferent(this.random)) {
                variant = PlatypusEntity.Variant.getRareSpawnVariant(this.random);
            } else {
                variant = this.random.nextBoolean() ? this.getVariant() : ((PlatypusEntity)entity).getVariant();
            }

            platypusEntity.setVariant(variant);
            platypusEntity.setPersistenceRequired();
        }

        return platypusEntity;
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(ModItems.PLATYPUS_BUCKET);
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
        return 6000;
    }

    @Override
    public boolean canBreatheUnderwater() {
        return false;
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
        private final PathfinderMob mob;
        private final double speed;

        public PlatypusBreatheAirGoal(PathfinderMob pathfinderMob, Double d) {
            this.mob = pathfinderMob;
            this.speed = d;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        public boolean canUse() {
            return this.mob.getAirSupply() < 140;
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
                    Mth.floor(this.mob.getX() - (double)1.0F),
                    this.mob.getBlockY(),
                    Mth.floor(this.mob.getZ() - (double)1.0F),
                    Mth.floor(this.mob.getX() + (double)1.0F),
                    Mth.floor(this.mob.getY() + (double)8.0F),
                    Mth.floor(this.mob.getZ() + (double)1.0F)
            );
            BlockPos blockPos = null;

            for(BlockPos blockPos2 : iterable) {
                if (this.givesAir(this.mob.level(), blockPos2)) {
                    blockPos = blockPos2;
                    break;
                }
            }

            if (blockPos == null) {
                blockPos = BlockPos.containing(this.mob.getX(), this.mob.getY() + (double)8.0F, this.mob.getZ());
            }

            this.mob.getNavigation().moveTo(
                    (double)blockPos.getX(),
                    (double)(blockPos.getY() + 1),
                    (double)blockPos.getZ(),
                    (double)this.speed
            );
        }

        public void tick() {
            this.findAirPosition();
            this.mob.moveRelative(0.02F, new Vec3((double)this.mob.xxa, (double)this.mob.yya, (double)this.mob.zza));
            this.mob.move(MoverType.SELF, this.mob.getDeltaMovement());
        }

        private boolean givesAir(LevelReader levelReader, BlockPos blockPos) {
            BlockState blockState = levelReader.getBlockState(blockPos);
            return (levelReader.getFluidState(blockPos).isEmpty() || blockState.is(Blocks.BUBBLE_COLUMN)) && blockState.isPathfindable(levelReader, blockPos, PathComputationType.LAND);
        }
    }
}
