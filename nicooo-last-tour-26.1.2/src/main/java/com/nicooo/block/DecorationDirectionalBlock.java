package com.nicooo.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

import net.minecraft.network.protocol.game.ClientboundMoveEntityPacket;
import net.minecraft.world.item.context.BlockPlaceContext;

import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;

import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.swing.text.html.BlockView;

public class DecorationDirectionalBlock extends Block {

    public static final EnumProperty<Direction> FACING =
            HorizontalDirectionalBlock.FACING;



    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (state.getBlock() == ModBlocks.CHITO_FUMO_BLOCK) {
            return Block.box(4, 0, 4, 12, 13.5, 12);
        }

        if (state.getBlock() == ModBlocks.YUURI_FUMO_BLOCK) {
            return Block.box(4, 0, 4, 12, 13.5, 12);
        }

        return Shapes.block();
    }

    @Override
    protected boolean propagatesSkylightDown(BlockState state) {
        return true;
    }

    @Override
    protected float getShadeBrightness(BlockState state, BlockGetter level, BlockPos pos) {
        return 1;
    }

    public DecorationDirectionalBlock(BlockBehaviour.Properties properties) {
        super(properties);

        this.registerDefaultState(
                this.stateDefinition.any()
                        .setValue(FACING, Direction.NORTH)
        );
    }

    @Override
    protected void createBlockStateDefinition(
            StateDefinition.Builder<Block, BlockState> builder
    ) {
        builder.add(FACING);
    }


    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState()
                .setValue(FACING, ctx.getHorizontalDirection().getOpposite());
    }
}