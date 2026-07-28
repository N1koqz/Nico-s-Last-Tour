package com.nicooo.item;

import com.nicooo.block.ModBlocks;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class SoupCanItem extends BlockItem {
    public SoupCanItem(Properties properties) {
        super(ModBlocks.SOUP_CAN, properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        LivingEntity entity = player.asLivingEntity();
        if (player.getOffhandItem().is(ModItems.CAN_OPENER)) {
            level.playSound(
                    null, entity.blockPosition(),
                    SoundEvents.IRON_BREAK, SoundSource.PLAYERS,
                    0.5f,
                    1.3f
            );
            int freeSlot = player.getInventory().getFreeSlot();
            player.getOffhandItem().hurtAndBreak(1, player, InteractionHand.OFF_HAND);
            player.getInventory().removeItem(Inventory.SLOT_OFFHAND,1);
            player.setItemInHand(hand, new ItemStack(ModItems.OPENED_SOUP_CAN));
            player.addItem(new ItemStack(ModItems.CAN_OPENER));
            player.getCooldowns().addCooldown(ModItems.OPENED_SOUP_CAN.getDefaultInstance(), 10);
            return InteractionResult.SUCCESS;
        } else {
            player.sendOverlayMessage(
                    Component.translatable(
                            "itemTooltip.nicooo-last-tour.soup_can_missing_can_opener"
                    )
            );
            return InteractionResult.FAIL;
        }
    }
}
