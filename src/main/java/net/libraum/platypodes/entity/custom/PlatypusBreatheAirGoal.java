package net.libraum.platypodes.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class PlatypusBreatheAirGoal extends Goal {
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
