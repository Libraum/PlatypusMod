package net.libraum.platypodes.entity.custom;

import com.google.common.collect.ImmutableList;
import net.libraum.platypodes.entity.ModEntities;
import net.libraum.platypodes.items.ModItems;
import net.libraum.platypodes.sound.ModSounds;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.BreathAirGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.animal.axolotl.Axolotl.Variant;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class PlatypusEntity extends Axolotl {
    public PlatypusEntity(EntityType<? extends PlatypusEntity> entityType, Level world) {
        super(entityType, world);
    }
    protected static final ImmutableList<? extends SensorType<? extends Sensor<? super PlatypusEntity>>> SENSORS = ImmutableList.of(
            SensorType.NEAREST_ADULT, SensorType.HURT_BY
    );

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new BreathAirGoal(this));
        this.goalSelector.addGoal(1, new BreedGoal(this, 0.2));
        this.goalSelector.addGoal(2, new TemptGoal(this,0.3, Ingredient.of(ModItems.YABBY),false));
    }

    public static AttributeSupplier.Builder createPlatypusAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 14)
                .add(Attributes.MOVEMENT_SPEED, 1.0)
                .add(Attributes.ATTACK_DAMAGE, 2.0);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(ModItems.YABBY);
    }

//    @Override
//    protected Brain<?> deserializeBrain(Dynamic<?> dynamic) {
//        return PlatypusBrain.create(Brain.createProfile(MEMORY_MODULES, SENSORS).deserialize(dynamic));
//    }

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

    /** Prevent suffocating on land */
    @Override
    protected void handleAirSupply(int air) {
        if (this.isAlive() && !this.isInWaterRainOrBubble()) {
            this.setAirSupply(air - 1);
            if (this.getAirSupply() == -20) {
                this.setAirSupply(0);
                //this.damage(this.getDamageSources().dryOut(), 2.0F);
            }
        } else {
            this.setAirSupply(this.getMaxAirSupply());
        }
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
}
