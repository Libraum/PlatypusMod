package net.libraum.platypusmod.entity.custom;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Dynamic;
import net.libraum.platypusmod.entity.ModEntities;
import net.libraum.platypusmod.items.ModItems;
import net.libraum.platypusmod.sound.ModSounds;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.ai.goal.AnimalMateGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AxolotlEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class PlatypusEntity extends AxolotlEntity {
    public PlatypusEntity(EntityType<? extends PlatypusEntity> entityType, World world) {
        super(entityType, world);
    }
    protected static final ImmutableList<? extends SensorType<? extends Sensor<? super PlatypusEntity>>> SENSORS = ImmutableList.of(
            SensorType.NEAREST_ADULT, SensorType.HURT_BY
    );

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new AnimalMateGoal(this, 1.0));
        this.goalSelector.add(2, new TemptGoal(this,0.5, Ingredient.ofItems(ModItems.YABBY),false));
    }

    public static DefaultAttributeContainer.Builder createPlatypusAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 14)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(ModItems.YABBY);
    }

//    @Override
//    protected Brain<?> deserializeBrain(Dynamic<?> dynamic) {
//        return PlatypusBrain.create(Brain.createProfile(MEMORY_MODULES, SENSORS).deserialize(dynamic));
//    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntities.PLATYPUS.create(world);
    }

    @Override
    public ItemStack getBucketItem() {
        return new ItemStack(ModItems.PLATYPUS_BUCKET);
    }

    /**
     * Sound Events
    */
    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return this.isTouchingWater() ? ModSounds.ENTITY_PLATYPUS_IDLE_WATER : ModSounds.ENTITY_PLATYPUS_IDLE_AIR;
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
    protected SoundEvent getSplashSound() {
        return ModSounds.ENTITY_PLATYPUS_SPLASH;
    }

    @Nullable
    @Override
    protected SoundEvent getSwimSound() {
        return ModSounds.ENTITY_PLATYPUS_SWIM;
    }

    @Nullable
    @Override
    public SoundEvent getBucketFillSound() {
        return ModSounds.ITEM_BUCKET_FILL_PLATYPUS;
    }
}
