package com.nicooo.block;

import com.nicooo.NicoooLastTour;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ModBlocks {

    public static final Block CHITO_STATUE_HEAD_BLOCK = registerBlock(
            "chito_statue_head_block",
            new DirectionalBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS)
                            .requiresCorrectToolForDrops()
                            .noOcclusion()
                            .isSuffocating((state, level, pos) -> false)
                            .strength(1)
                            .setId(ResourceKey.create(
                            BuiltInRegistries.BLOCK.key(),
                            Identifier.fromNamespaceAndPath(
                                    NicoooLastTour.MOD_ID,
                                    "chito_statue_head_block"
                            )
                    ))
            )
    );

    public static final Block CHITO_STATUE_BODY_BLOCK = registerBlock(
            "chito_statue_body_block",
            new CustomShapeNonDirectionalBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS)
                            .requiresCorrectToolForDrops()
                            .noOcclusion()
                            .strength(1)
                            .isSuffocating((state, level, pos) -> false)
                            .setId(ResourceKey.create(
                            BuiltInRegistries.BLOCK.key(),
                            Identifier.fromNamespaceAndPath(
                                    NicoooLastTour.MOD_ID,
                                    "chito_statue_body_block"
                            )
                    ))
            )
    );

    public static final Block YUURI_FUMO_BLOCK = registerBlock(
            "yuuri_fumo_block",
            new DecorationDirectionalBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)
                            .isViewBlocking((state, level, pos) -> false)
                            .noOcclusion()
                            .isSuffocating((state, level, pos) -> false)
                            .setId(ResourceKey.create(
                                    BuiltInRegistries.BLOCK.key(),
                                    Identifier.fromNamespaceAndPath(
                                            NicoooLastTour.MOD_ID,
                                            "yuuri_fumo_block"
                                    )
                            ))
            )
    );

    public static final Block CHITO_FUMO_BLOCK = registerBlock(
            "chito_fumo_block",
            new DecorationDirectionalBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)
                            .isViewBlocking((state, level, pos) -> false)
                            .noOcclusion()
                            .isSuffocating((state, level, pos) -> false)
                            .setId(ResourceKey.create(
                                    BuiltInRegistries.BLOCK.key(),
                                    Identifier.fromNamespaceAndPath(
                                            NicoooLastTour.MOD_ID,
                                            "chito_fumo_block"
                                    )
                            ))
            )
    );


    private static Block registerBlock(String name, Block block) {
        return Registry.register(
                BuiltInRegistries.BLOCK,
                Identifier.fromNamespaceAndPath(NicoooLastTour.MOD_ID, name),
                block
        );
    }

    public static void registerModBlocks() {

        NicoooLastTour.LOGGER.info("Loading mod blocks for: " + NicoooLastTour.MOD_ID);
    }
}