package net.libraum.platypodes.items.custom;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;

public class PlatypusBucketItem extends MobBucketItem {
    public PlatypusBucketItem(EntityType<?> type, Fluid fluid, SoundEvent emptyingSound, Properties settings) {
        super(type, fluid, emptyingSound, settings);
    }
    public static float getVariant(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        return ((float) tag.getInt("Variant") / 4);
    }
}
