package net.libraum.platypodes.blocks.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

public class PlatypusBurrowBlockEntity extends BlockEntity {

    public PlatypusBurrowBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PLATYPUS_BURROW_BLOCK_ENTITY, pos, state);
    }
}
