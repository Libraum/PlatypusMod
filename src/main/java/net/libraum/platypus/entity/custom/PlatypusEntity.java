package net.libraum.platypus.entity.custom;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Dynamic;
import net.libraum.platypus.entity.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.ai.goal.AnimalMateGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AxolotlEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class PlatypusEntity extends AxolotlEntity {
    public PlatypusEntity(EntityType<? extends PlatypusEntity> entityType, World world) {
        super(entityType, world);
    }
    protected static final ImmutableList<? extends SensorType<? extends Sensor<? super PlatypusEntity>>> SENSORS = ImmutableList.of(
            SensorType.NEAREST_ADULT, SensorType.HURT_BY, SensorType.AXOLOTL_TEMPTATIONS
    );

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new AnimalMateGoal(this, 1.0));
        this.goalSelector.add(2, new TemptGoal(this,0.5, Ingredient.ofItems(Items.SPIDER_EYE),false));
    }

    public static DefaultAttributeContainer.Builder createPlatypusAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 14)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(Items.SPIDER_EYE);
    }

    @Override
    protected Brain<?> deserializeBrain(Dynamic<?> dynamic) {
        return PlatypusBrain.create(Brain.createProfile(MEMORY_MODULES, SENSORS).deserialize(dynamic));
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntities.PLATYPUS.create(world);
    }
}
