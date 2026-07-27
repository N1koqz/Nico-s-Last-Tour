package com.nicooo.blockEntities;

import com.nicooo.NicoooLastTour;
import com.nicooo.block.ModBlocks;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModBlockEntities {

    public static final BlockEntityType<PortableStoveBlockEntity> PORTABLE_GAS_STOVE =
            registerBlockEntity(
                    "portable_gas_stove",
                    FabricBlockEntityTypeBuilder.create(
                            PortableStoveBlockEntity::new,
                            ModBlocks.PORTABLE_GAS_STOVE
                    ).build()
            );


    private static <T extends net.minecraft.world.level.block.entity.BlockEntity> BlockEntityType<T> registerBlockEntity(
            String name,
            BlockEntityType<T> blockEntityType
    ) {
        return Registry.register(
                BuiltInRegistries.BLOCK_ENTITY_TYPE,
                Identifier.fromNamespaceAndPath(
                        NicoooLastTour.MOD_ID,
                        name
                ),
                blockEntityType
        );
    }


    public static void registerBlockEntities() {
        NicoooLastTour.LOGGER.info(
                "Loading mod block entities for: " + NicoooLastTour.MOD_ID
        );
    }
}