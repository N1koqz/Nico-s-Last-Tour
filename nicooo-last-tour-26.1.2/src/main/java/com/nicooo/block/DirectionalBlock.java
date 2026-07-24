package com.nicooo.block;

import com.nicooo.item.ModComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

import net.minecraft.network.protocol.game.ClientboundMoveEntityPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;

import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;

import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.management.ImmutableDescriptor;
import javax.swing.text.html.BlockView;

import static net.minecraft.world.entity.Relative.union;


public class DirectionalBlock extends Block {

    public static final EnumProperty<Direction> FACING =
            HorizontalDirectionalBlock.FACING;

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (state.getBlock() == ModBlocks.CHITO_STATUE_HEAD_BLOCK) {
            if (state.getValue(FACING) == Direction.NORTH) { //DONE
                VoxelShape block1 = Block.box(1, 0, 1, 15, 16, 15);
                VoxelShape block2 = Block.box(1, 5, 0, 15, 16, 1);
                return Shapes.join(block1, block2, BooleanOp.OR);
            }
            if (state.getValue(FACING) == Direction.SOUTH) {
                VoxelShape block1 = Block.box(1, 0, 1, 15, 16, 15);
                VoxelShape block2 = Block.box(1, 5, 15, 15, 16, 16);
                return Shapes.join(block1, block2, BooleanOp.OR);
            }
            if (state.getValue(FACING) == Direction.EAST) {
                VoxelShape block1 = Block.box(1, 0, 1, 15, 16, 15);
                VoxelShape block2 = Block.box(15, 5, 1, 16, 16, 15);
                return Shapes.join(block1, block2, BooleanOp.OR);
            }
            if (state.getValue(FACING) == Direction.WEST) { //DONE
                VoxelShape block1 = Block.box(1, 0, 1, 15, 16, 15);
                VoxelShape block2 = Block.box(0, 5, 1, 1, 16, 15);
                return Shapes.join(block1, block2, BooleanOp.OR);
            }
        }
        return Shapes.block();
    }

    public DirectionalBlock(BlockBehaviour.Properties properties) {
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

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess ticks, BlockPos pos, Direction directionToNeighbour, BlockPos neighbourPos, BlockState neighbourState, RandomSource random) {
        if (directionToNeighbour == Direction.DOWN && neighbourState.isAir()) {
            // Schedule a tick so the block breaks on the next tick
            if (level.getBlockState(pos.below()).isAir()) {
                ticks.scheduleTick(pos, state.getBlock(), 1);
            }
        }
        return super.updateShape(state, level, ticks, pos, directionToNeighbour, neighbourPos, neighbourState, random);
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        level.destroyBlock(pos,true);
        super.tick(state, level, pos, random);
    }
}