package net.libraum.platypodes.entity.custom;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import net.libraum.platypodes.entity.ModEntities;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
//import net.minecraft.entity.ai.brain.*;
//import net.minecraft.entity.ai.brain.task.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.*;
import net.minecraft.world.entity.ai.behavior.BabyFollowAdult;
import net.minecraft.world.entity.ai.behavior.FollowTemptation;
import net.minecraft.world.entity.ai.behavior.GateBehavior;
import net.minecraft.world.entity.ai.behavior.PositionTracker;
import net.minecraft.world.entity.ai.behavior.RandomStroll;
import net.minecraft.world.entity.ai.behavior.RunOne;
import net.minecraft.world.entity.ai.behavior.SetWalkTargetFromLookTarget;
import net.minecraft.world.entity.ai.behavior.TryFindWater;
import net.minecraft.world.entity.ai.behavior.declarative.BehaviorBuilder;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.core.BlockPos;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class PlatypusBrain {
    private static final UniformInt WALK_TOWARD_ADULT_RANGE = UniformInt.of(5, 16);

    public static Brain<?> create(Brain<PlatypusEntity> brain) {
        addCoreActivities(brain);
        addIdleActivities(brain);
        brain.setCoreActivities(ImmutableSet.of(Activity.CORE));
        brain.setDefaultActivity(Activity.IDLE);
        brain.useDefaultActivity();
        return brain;
    }

    private static void addCoreActivities(Brain<PlatypusEntity> brain) {
        brain.addActivity(
                Activity.CORE,
                0,
                ImmutableList.of(
                        new LookAtTargetSink(45, 90),
                        new MoveToTargetSink(),
                        new CountDownCooldownTicks(MemoryModuleType.TEMPTATION_COOLDOWN_TICKS)
                )
        );
    }

    private static void addIdleActivities(Brain<PlatypusEntity> brain) {
        brain.addActivity(
                Activity.IDLE,
                ImmutableList.of(
                        Pair.of(0, SetEntityLookTargetSometimes.create(EntityType.PLAYER, 6.0F, UniformInt.of(30, 60))),
                        Pair.of(1, new AnimalMakeLove(ModEntities.PLATYPUS, 0.2F)),
                        Pair.of(
                                2,
                                new RunOne<>(
                                        ImmutableList.of(
                                                Pair.of(new FollowTemptation(PlatypusBrain::getTemptedSpeed), 1),
                                                Pair.of(BabyFollowAdult.create(WALK_TOWARD_ADULT_RANGE, PlatypusBrain::getAdultFollowingSpeed), 1)
                                        )
                                )
                        ),
                        Pair.of(3, TryFindWater.create(6, 0.15F)),
                        Pair.of(
                                4,
                                new GateBehavior<>(
                                        ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT),
                                        ImmutableSet.of(),
                                        GateBehavior.OrderPolicy.ORDERED,
                                        GateBehavior.RunningPolicy.TRY_ALL,
                                        ImmutableList.of(
                                                Pair.of(RandomStroll.swim(0.5F), 2),
                                                Pair.of(RandomStroll.stroll(0.15F, false), 2),
                                                Pair.of(SetWalkTargetFromLookTarget.create(PlatypusBrain::canGoToLookTarget, PlatypusBrain::getTemptedSpeed, 3), 3),
                                                Pair.of(BehaviorBuilder.triggerIf(Entity::isInWaterOrBubble), 5),
                                                Pair.of(BehaviorBuilder.triggerIf(Entity::onGround), 5)
                                        )
                                )
                        )
                )
        );
    }

    private static boolean canGoToLookTarget(LivingEntity entity) {
        Level world = entity.level();
        Optional<PositionTracker> optional = entity.getBrain().getMemory(MemoryModuleType.LOOK_TARGET);
        if (optional.isPresent()) {
            BlockPos blockPos = optional.get().currentBlockPosition();
            return world.isWaterAt(blockPos) == entity.isInWaterOrBubble();
        } else {
            return false;
        }
    }

    public static void updateActivities(PlatypusEntity platypus) {
        Brain<Axolotl> brain = platypus.getBrain();
        brain.setActiveActivityToFirstValid(ImmutableList.of(Activity.IDLE));
    }

    private static float getAdultFollowingSpeed(LivingEntity entity) {
        return entity.isInWaterOrBubble() ? 0.6F : 0.15F;
    }

    private static float getTemptedSpeed(LivingEntity entity) {
        return entity.isInWaterOrBubble() ? 0.5F : 0.15F;
    }
}
