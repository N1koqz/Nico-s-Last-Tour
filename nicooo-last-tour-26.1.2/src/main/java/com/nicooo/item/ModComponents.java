package com.nicooo.item;

import com.mojang.serialization.Codec;
import com.nicooo.NicoooLastTour;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class ModComponents {
    public static void initialize() {
        NicoooLastTour.LOGGER.info("Registering {} components", NicoooLastTour.MOD_ID);

    }
    public static final DataComponentType<Integer> USAGES = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            Identifier.fromNamespaceAndPath(NicoooLastTour.MOD_ID, "usages"),
            DataComponentType.<Integer>builder().persistent(Codec.INT).build()
    );
    public static final DataComponentType<Boolean> IS_ACTIVATED = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            Identifier.fromNamespaceAndPath(NicoooLastTour.MOD_ID, "is_activated"),
            DataComponentType.<Boolean>builder().persistent(Codec.BOOL).build()
    );
    public static final DataComponentType<Boolean> NIGHT_VISION_ACTIVE = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            Identifier.fromNamespaceAndPath(NicoooLastTour.MOD_ID, "night_vision_active"),
            DataComponentType.<Boolean>builder().persistent(Codec.BOOL).build()
    );
}
