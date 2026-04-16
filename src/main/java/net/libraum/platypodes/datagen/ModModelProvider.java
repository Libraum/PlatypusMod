package net.libraum.platypodes.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.libraum.platypodes.items.ModItems;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.resources.ResourceLocation;

import java.util.Optional;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) { super(output); }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(ModItems.PLATYPUS_SPAWN_EGG,
                new ModelTemplate(Optional.of(ResourceLocation.fromNamespaceAndPath("item/template_spawn_egg", "minecraft")), Optional.empty()));
        //itemModelGenerator.register(ModItems.PLATYPUS_BUCKET, Models.GENERATED);
        itemModelGenerator.generateFlatItem(ModItems.YABBY, ModelTemplates.FLAT_ITEM);
    }
}
