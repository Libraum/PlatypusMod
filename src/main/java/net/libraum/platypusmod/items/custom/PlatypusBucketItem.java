package net.libraum.platypusmod.items.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.EntityBucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvent;

public class PlatypusBucketItem extends EntityBucketItem {
    public PlatypusBucketItem(EntityType<?> type, Fluid fluid, SoundEvent emptyingSound, Settings settings) {
        super(type, fluid, emptyingSound, settings);
    }

    public static float getVariant(ItemStack stack) {
        NbtCompound tag = stack.getOrCreateNbt();
        return ((float) tag.getInt("Variant") / 4);
    }
}
