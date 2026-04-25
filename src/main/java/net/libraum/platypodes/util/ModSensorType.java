package net.libraum.platypodes.util;

import net.libraum.platypodes.entity.ai.PlatypusAI;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.ai.sensing.TemptingSensor;

import java.util.function.Supplier;

public class ModSensorType {
    public static final SensorType<TemptingSensor> PLATYPUS_TEMPTATIONS = register("platypus_temptations", () -> new TemptingSensor(PlatypusAI.getTemptations()));

    private static <U extends Sensor<?>> SensorType<U> register(String string, Supplier<U> supplier) {
        return (SensorType) Registry.register(BuiltInRegistries.SENSOR_TYPE, ResourceLocation.withDefaultNamespace(string), new SensorType(supplier));
    }
}
