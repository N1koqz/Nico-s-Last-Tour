package com.nicooo.entities;

import com.nicooo.NicoooLastTour;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntities {
    /*public static final EntityType<KettenkradEntity> KETTENKRAD= register(
            "kettenkrad",
            EntityType.Builder.<KettenkradEntity>of(KettenkradEntity::new, MobCategory.MISC)
                    .sized(0.75f, 1.75f)
    );*/

    private static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> builder) {
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(NicoooLastTour.MOD_ID, name));
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, builder.build(key));
    }

    public static void registerModEntities() {
        NicoooLastTour.LOGGER.info("Registering EntityTypes for " + NicoooLastTour.MOD_ID);
    }

}
