package com.nicooo.item;

import com.nicooo.NicoooLastTour;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public class ModSounds {

    public static final SoundEvent NIGHT_VISION_ON = registerSound("night_vision_on");
    public static final SoundEvent NIGHT_VISION_OFF = registerSound("night_vision_off");

    private static SoundEvent registerSound(String name) {
        Identifier id = Identifier.fromNamespaceAndPath(
                NicoooLastTour.MOD_ID,
                name
        );

        return Registry.register(
                BuiltInRegistries.SOUND_EVENT,
                id,
                SoundEvent.createVariableRangeEvent(id)
        );
    }

    public static void registerModSounds() {
        NicoooLastTour.LOGGER.info(
                "Loading mod sounds for: " + NicoooLastTour.MOD_ID
        );
    }
}