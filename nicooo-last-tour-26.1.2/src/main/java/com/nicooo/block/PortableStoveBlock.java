package com.nicooo.block;

import com.mojang.serialization.MapCodec;
import com.nicooo.blockEntities.ModBlockEntities;
import com.nicooo.blockEntities.ModBlockEntities;
import com.nicooo.blockEntities.PortableStoveBlockEntity;
import com.nicooo.item.ModComponents;
import com.nicooo.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;

import static org.apache.logging.log4j.core.util.OptionConverter.toInt;

public class PortableStoveBlock extends BaseEntityBlock {

    public static final EnumProperty<Direction> FACING =
            HorizontalDirectionalBlock.FACING;

    public static final BooleanProperty LIT =
            BlockStateProperties.LIT;

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(PortableStoveBlock::new);
    }

    public PortableStoveBlock(BlockBehaviour.Properties properties) {
        super(properties);

        this.registerDefaultState(
                this.stateDefinition.any()
                        .setValue(FACING, Direction.NORTH)
                        .setValue(LIT, false)
        );
    }


    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        VoxelShape block1 = Block.box(4, 0, 4, 12, 6, 12);
        VoxelShape block2 = Block.box(7, 6, 7, 9, 8.5, 9);
        VoxelShape block3 = Block.box(6, 8.5, 6, 10, 12.5, 10);

        VoxelShape shape = Shapes.join(block1, block2, BooleanOp.OR);
        return Shapes.join(shape, block3, BooleanOp.OR);
    }


    @Override
    protected boolean propagatesSkylightDown(BlockState state) {
        return true;
    }


    @Override
    protected float getShadeBrightness(BlockState state, BlockGetter level, BlockPos pos) {
        return 1;
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT);
    }


    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState()
                .setValue(FACING, ctx.getHorizontalDirection().getOpposite());
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

        ItemStack held = player.getItemInHand(hand);
        if (held.is(Items.FLINT_AND_STEEL)||(held.is(ModItems.LIGHTER))) {
            if (!state.getValue(LIT)) {
                level.setBlock(
                        pos,
                        state.setValue(LIT, true),
                        Block.UPDATE_ALL
                );

                level.playSound(
                        null,
                        pos,
                        SoundEvents.FIRECHARGE_USE,
                        SoundSource.BLOCKS,
                        1f,
                        1f
                );
            }

            return InteractionResult.SUCCESS;
        }

        if (held.isEmpty()) {
            if (state.getValue(LIT)) {
                level.setBlock(
                        pos,
                        state.setValue(LIT, false),
                        Block.UPDATE_ALL
                );
                level.playSound(
                        null,
                        pos,
                        SoundEvents.FIRE_EXTINGUISH,
                        SoundSource.BLOCKS,
                        0.5f,
                        0.8f
                );
            }
            if (player.isCrouching()) {
                level.removeBlock(pos, false);
                player.addItem(new ItemStack(ModItems.PORTABLE_GAS_STOVE));
            }
            return InteractionResult.SUCCESS;
        }

        if (held.is(ModItems.CIGARETTE)) {
            if (state.getValue(LIT)) {
                if (!stack.get(ModComponents.IS_ACTIVATED)) {
                    boolean isActivated = stack.get(ModComponents.IS_ACTIVATED);
                    isActivated = true;
                    int usages = stack.get(ModComponents.USAGES);
                    if (player.getAbilities().instabuild) {
                        stack.set(DataComponents.LORE, new ItemLore(List.of(
                                Component.translatable("itemLore.nicooo-last-tour.cigarette.is_activated"),
                                Component.translatable("itemTooltip.nicooo-last-tour.cigarette_remaining_usages")
                                        .withStyle(ChatFormatting.GRAY),
                                Component.literal(String.valueOf(usages))
                                        .withStyle(ChatFormatting.GOLD)
                        )));
                    }
                    stack.set(ModComponents.IS_ACTIVATED, isActivated);
                    level.playSound(
                            null,
                            pos,
                            SoundEvents.FIRECHARGE_USE,
                            SoundSource.PLAYERS,
                            0.5f,
                            1.3f
                    );
                    stack.set(DataComponents.ITEM_NAME, Component.translatable("item.nicooo-last-tour.cigarette.is_activated"));
                    player.getCooldowns().addCooldown(ModItems.CIGARETTE.getDefaultInstance(), 10);
                }
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }


    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PortableStoveBlockEntity(pos, state);
    }


    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(
            Level level,
            BlockState state,
            net.minecraft.world.level.block.entity.BlockEntityType<T> type
    ) {
        return createTickerHelper(
                type,
                com.nicooo.blockEntities.ModBlockEntities.PORTABLE_GAS_STOVE,
                PortableStoveBlockEntity::tick
        );
    }
}