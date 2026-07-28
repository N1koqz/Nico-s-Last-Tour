package com.nicooo.blockEntities;

import com.nicooo.blockEntities.ModBlockEntities;
import com.nicooo.block.PortableStoveBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import static com.nicooo.block.PortableStoveBlock.LIT;

public class PortableStoveBlockEntity extends BlockEntity {

    public PortableStoveBlockEntity(BlockPos pos, BlockState state) {
        super(com.nicooo.blockEntities.ModBlockEntities.PORTABLE_GAS_STOVE, pos, state);
    }
    public static void tick(Level level, BlockPos pos, BlockState state, PortableStoveBlockEntity be) {

        if (!state.getValue(LIT)) {
            return;
        }

        if (level.isClientSide()) {
            level.addParticle(
                    ParticleTypes.SOUL_FIRE_FLAME,
                    pos.getX() + 0.5,
                    pos.getY() + 0.7,
                    pos.getZ() + 0.5,
                    0.0,
                    0.005,
                    0.0
            );
        } else {
            AABB hotArea = new AABB(
                    pos.getX() + 0.25,
                    pos.getY() + 0.8,
                    pos.getZ() + 0.25,

                    pos.getX() + 0.75,
                    pos.getY() + 1.5,
                    pos.getZ() + 0.75
            );

            for (LivingEntity entity : level.getEntitiesOfClass(
                    LivingEntity.class,
                    hotArea
            )) {
                entity.hurt(
                        level.damageSources().hotFloor(),
                        1.0F
                );
            }
        }
    }
}
