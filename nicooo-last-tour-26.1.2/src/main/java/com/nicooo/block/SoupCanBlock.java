package com.nicooo.block;

import com.nicooo.item.ModComponents;
import com.nicooo.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;

import static net.minecraft.world.level.block.entity.BeaconBlockEntity.playSound;

public class SoupCanBlock extends Block {

    public static final IntegerProperty STACKED =
            IntegerProperty.create("stacked", 1, 4);

    public SoupCanBlock(BlockBehaviour.Properties properties) {
        super(properties);

        this.registerDefaultState(
                this.stateDefinition.any()
                        .setValue(STACKED, 1)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(STACKED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = context.getLevel().getBlockState(context.getClickedPos());

        if (state.is(this)) {
            return state.setValue(STACKED, state.getValue(STACKED) + 1);
        }

        return this.defaultBlockState();
    }

    @Override
    protected boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        ItemStack stack = context.getItemInHand();
        return stack.is(ModItems.SOUP_CAN)
                && state.getValue(STACKED) < 4;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (state.getValue(STACKED) == 1) {
            VoxelShape shape = Block.box(4, 0, 4, 12, 7, 12);
            return shape;
        }
        if (state.getValue(STACKED) == 2) {
            VoxelShape shape = Block.box(1, 0, 1, 15, 7, 15);
            return shape;
        }
        if (state.getValue(STACKED) == 3) {
            VoxelShape shape = Block.box(1, 0, 1, 15, 7, 15);
            return shape;
        }
        if (state.getValue(STACKED) == 4) {
            VoxelShape block1 = Block.box(1, 0, 1, 15, 7, 15);
            VoxelShape block2 = Block.box(4.5, 7, 4.3, 11.3, 14, 11.2);
            return Shapes.join(block1, block2, BooleanOp.OR);
        }
        return Shapes.block();
    }

    @Override
    protected boolean propagatesSkylightDown(BlockState state) {
        return true;
    }

    @Override
    protected float getShadeBrightness(BlockState state, BlockGetter level, BlockPos pos) {
        return 1.0F;
    }

    @Override
    protected InteractionResult useItemOn(
            ItemStack stack,
            BlockState state,
            Level level,
            BlockPos pos,
            Player player,
            InteractionHand hand,
            BlockHitResult hit
    ) {
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }
        if (stack.is(ModItems.SOUP_CAN)) {
            level.playSound(
                    null,
                    pos,
                    SoundEvents.IRON_PLACE,
                    SoundSource.BLOCKS,
                    1f,
                    1f
            );
            return InteractionResult.SUCCESS;
        }
        if (stack.is(ModItems.CAN_OPENER) && (state.getValue(STACKED) == 1)) {
            level.playSound(
                    null,
                    pos,
                    SoundEvents.IRON_BREAK,
                    SoundSource.BLOCKS,
                    1f,
                    1f
            );
            level.setBlock(pos, ModBlocks.OPENED_SOUP_CAN.defaultBlockState(), 1);
            player.getMainHandItem().hurtAndBreak(1, player, InteractionHand.OFF_HAND);
            return InteractionResult.SUCCESS;
        }
        if (stack.isEmpty()) {
            level.playSound(
                    null,
                    pos,
                    SoundEvents.ITEM_PICKUP,
                    SoundSource.BLOCKS,
                    1f,
                    1f
            );
            int newStack = state.getValue(STACKED) - 1;

            if (newStack <= 0) {
                level.removeBlock(pos, false);
            } else {
                level.setBlock(
                        pos,
                        state.setValue(STACKED, newStack),
                        Block.UPDATE_ALL
                );
            }

            player.addItem(new ItemStack(ModItems.SOUP_CAN));

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }
    @Override
    protected List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        return List.of(new ItemStack(ModItems.SOUP_CAN, state.getValue(STACKED)));
    }
}