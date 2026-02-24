package net.libraum.platypusmod.entity.custom;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import net.libraum.platypusmod.entity.ModEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.*;
import net.minecraft.entity.ai.brain.task.*;
import net.minecraft.entity.passive.AxolotlEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;

import java.util.Optional;

public class PlatypusBrain {
    private static final UniformIntProvider WALK_TOWARD_ADULT_RANGE = UniformIntProvider.create(5, 16);

    public static Brain<?> create(Brain<PlatypusEntity> brain) {
        addCoreActivities(brain);
        addIdleActivities(brain);
        brain.setCoreActivities(ImmutableSet.of(Activity.CORE));
        brain.setDefaultActivity(Activity.IDLE);
        brain.resetPossibleActivities();
        return brain;
    }

    private static void addCoreActivities(Brain<PlatypusEntity> brain) {
        brain.setTaskList(
                Activity.CORE,
                0,
                ImmutableList.of(
                        new LookAroundTask(45, 90),
                        new TemptationCooldownTask(MemoryModuleType.TEMPTATION_COOLDOWN_TICKS)
                )
        );
    }

    private static void addIdleActivities(Brain<PlatypusEntity> brain) {
        brain.setTaskList(
                Activity.IDLE,
                ImmutableList.of(
                        Pair.of(0, LookAtMobWithIntervalTask.follow(EntityType.PLAYER, 6.0F, UniformIntProvider.create(30, 60))),
                        Pair.of(1, new BreedTask(ModEntities.PLATYPUS)),
                        Pair.of(
                                2,
                                new RandomTask<>(
                                        ImmutableList.of(
                                                Pair.of(new TemptTask(PlatypusBrain::getTemptedSpeed), 1),
                                                Pair.of(WalkTowardClosestAdultTask.create(WALK_TOWARD_ADULT_RANGE, PlatypusBrain::getAdultFollowingSpeed), 1)
                                        )
                                )
                        ),
                        Pair.of(3, SeekWaterTask.create(6, 0.15F)),
                        Pair.of(
                                4,
                                new CompositeTask<>(
                                        ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryModuleState.VALUE_ABSENT),
                                        ImmutableSet.of(),
                                        CompositeTask.Order.ORDERED,
                                        CompositeTask.RunMode.TRY_ALL,
                                        ImmutableList.of(
                                                Pair.of(StrollTask.createDynamicRadius(0.5F), 2),
                                                Pair.of(StrollTask.create(0.15F, false), 2),
                                                Pair.of(GoTowardsLookTargetTask.create(PlatypusBrain::canGoToLookTarget, PlatypusBrain::getTemptedSpeed, 3), 3),
                                                Pair.of(TaskTriggerer.predicate(Entity::isInsideWaterOrBubbleColumn), 5),
                                                Pair.of(TaskTriggerer.predicate(Entity::isOnGround), 5)
                                        )
                                )
                        )
                )
        );
    }

    private static boolean canGoToLookTarget(LivingEntity entity) {
        World world = entity.getWorld();
        Optional<LookTarget> optional = entity.getBrain().getOptionalRegisteredMemory(MemoryModuleType.LOOK_TARGET);
        if (optional.isPresent()) {
            BlockPos blockPos = optional.get().getBlockPos();
            return world.isWater(blockPos) == entity.isInsideWaterOrBubbleColumn();
        } else {
            return false;
        }
    }

    public static void updateActivities(PlatypusEntity platypus) {
        Brain<AxolotlEntity> brain = platypus.getBrain();
        brain.resetPossibleActivities(ImmutableList.of(Activity.IDLE));
    }

    private static float getAdultFollowingSpeed(LivingEntity entity) {
        return entity.isInsideWaterOrBubbleColumn() ? 0.6F : 0.15F;
    }

    private static float getTemptedSpeed(LivingEntity entity) {
        return entity.isInsideWaterOrBubbleColumn() ? 0.5F : 0.15F;
    }
}
