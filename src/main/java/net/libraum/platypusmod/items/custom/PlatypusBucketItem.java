package net.libraum.platypusmod.items.custom;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.EntityBucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvent;

import java.util.Objects;

public class PlatypusBucketItem extends EntityBucketItem {
    public PlatypusBucketItem(EntityType<?> type, Fluid fluid, SoundEvent emptyingSound, Settings settings) {
        super(type, fluid, emptyingSound, settings);
    }

    public static float getVariant(ItemStack stack) {
        NbtCompound tag = Objects.requireNonNull(stack.get(DataComponentTypes.BUCKET_ENTITY_DATA)).copyNbt();
        return ((float) tag.getInt("Variant") / 4);
    }
}