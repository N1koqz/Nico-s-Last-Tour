package com.nicooo.item;

import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.component.TypedDataComponent;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.datafix.fixes.ItemStackCustomNameToOverrideComponentFix;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.w3c.dom.Text;

import java.awt.*;
import java.util.List;
import java.util.Stack;


public class CigaretteItem extends Item {
    public CigaretteItem(Properties properties) {
        super(properties);
    }


    @Override
    public ItemUseAnimation getUseAnimation(ItemStack stack) {
        return ItemUseAnimation.TOOT_HORN;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 16;
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        LivingEntity entity = player.asLivingEntity();
        boolean isActivated = stack.get(ModComponents.IS_ACTIVATED);
        int usages = stack.get(ModComponents.USAGES);
        if ((player.getOffhandItem().is(Items.FLINT_AND_STEEL) || (player.getOffhandItem().is(ModItems.LIGHTER))) && isActivated == false) {
            isActivated = true;
            if ((entity.is(EntityType.PLAYER))) {
                if (player.getAbilities().instabuild) {
                    stack.set(DataComponents.LORE, new ItemLore(List.of(
                            Component.translatable("itemLore.nicooo-last-tour.cigarette_1").withStyle(ChatFormatting.GRAY),
                            Component.translatable("itemLore.nicooo-last-tour.cigarette_2").withStyle(ChatFormatting.GRAY),
                            Component.translatable("itemLore.nicooo-last-tour.cigarette_3").withStyle(ChatFormatting.GRAY),
                            Component.translatable("itemLore.nicooo-last-tour.cigarette.is_activated").withStyle(ChatFormatting.WHITE)
                    )));
                } else {
                    stack.set(DataComponents.LORE, new ItemLore(List.of(
                            Component.translatable("itemLore.nicooo-last-tour.cigarette_1").withStyle(ChatFormatting.GRAY),
                            Component.translatable("itemLore.nicooo-last-tour.cigarette_2").withStyle(ChatFormatting.GRAY),
                            Component.translatable("itemLore.nicooo-last-tour.cigarette_3").withStyle(ChatFormatting.GRAY),
                            Component.translatable("itemLore.nicooo-last-tour.cigarette.is_activated").withStyle(ChatFormatting.WHITE),
                            Component.translatable("itemTooltip.nicooo-last-tour.cigarette_remaining_usages").withStyle(ChatFormatting.WHITE),
                            Component.literal(String.valueOf(usages)).withStyle(ChatFormatting.GOLD)
                    )));
                }

            }
            stack.set(ModComponents.IS_ACTIVATED, isActivated);
            level.playSound(
                    null, entity.blockPosition(),
                    SoundEvents.FIRECHARGE_USE, SoundSource.PLAYERS,
                    0.5f,
                    1.3f
            );
            stack.set(DataComponents.ITEM_NAME, Component.translatable("item.nicooo-last-tour.cigarette.is_activated"));
            int freeSlot = player.getInventory().getFreeSlot();
            if (freeSlot != -1) {
                player.setItemInHand(InteractionHand.OFF_HAND, ItemStack.EMPTY);

                if (player.getOffhandItem().is(Items.FLINT_AND_STEEL)) {
                    player.addItem(new ItemStack(Items.FLINT_AND_STEEL));
                } else {
                    player.addItem(new ItemStack(ModItems.LIGHTER));
                }
            }
            player.getCooldowns().addCooldown(ModItems.CIGARETTE.getDefaultInstance(), 10);
            return InteractionResult.FAIL;
        }
        if (isActivated){
            player.startUsingItem(hand);

            return InteractionResult.CONSUME;
        } else {
            player.sendOverlayMessage(
                    Component.translatable(
                            "itemTooltip.nicooo-last-tour.cigarette_missing_lighter"
                    )
            );
            return InteractionResult.FAIL;
        }
    }

    @Override
    public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int remainingUseDuration) {
        int usages = stack.get(ModComponents.USAGES);
        boolean is_activated = stack.get(ModComponents.IS_ACTIVATED);
        Vec3 look = entity.getViewVector(1.0F);
        double x = entity.getX() + look.x;
        double y = entity.getEyeY() + look.y;
        double z = entity.getZ() + look.z;
        if (is_activated) {
            if (remainingUseDuration == (getUseDuration(stack, entity) - 1)) {
                if (!level.isClientSide()) {
                    level.playSound(
                            null, entity.blockPosition(),
                            SoundEvents.BLAZE_BURN, SoundSource.PLAYERS,
                            0.5f,
                            2f
                    );
                    level.playSound(
                            null, entity.blockPosition(),
                            SoundEvents.BREEZE_INHALE, SoundSource.PLAYERS,
                            0.3f,
                            0.6f
                    );
                }
            }
            level.addParticle(
                    ParticleTypes.FLAME,
                    entity.getX() + look.x * 0.8,
                    entity.getY() + 1.4 + look.y * 0.8,
                    entity.getZ() + look.z * 0.8,
                    0f, 0f, 0f
            );
        }
        super.onUseTick(level, entity, stack, remainingUseDuration);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        int usages = stack.get(ModComponents.USAGES);
        boolean is_activated = stack.get(ModComponents.IS_ACTIVATED);
        Vec3 look = entity.getViewVector(1.0F);
        double x = entity.getX() + look.x;
        double y = entity.getEyeY() + look.y;
        double z = entity.getZ() + look.z;
        if (is_activated) {
            if (entity instanceof Player player) {
                if (!level.isClientSide()) {
                    entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 20, 3, false, false));
                    entity.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 100, 2, false, false));
                }
                player.getCooldowns().addCooldown(ModItems.CIGARETTE.getDefaultInstance(), 50);
            }
            level.addParticle(
                    ParticleTypes.CAMPFIRE_COSY_SMOKE,
                    entity.getX() + look.x * 0.8,
                    entity.getY() + look.y + 1.4 + look.y * 0.8,
                    entity.getZ() + look.z * 0.8,
                    (level.getRandom().nextDouble() - 0.5) * 0.02, 0.1f, (level.getRandom().nextDouble() - 0.5) * 0.02
            );
            level.addParticle(
                    ParticleTypes.CAMPFIRE_COSY_SMOKE, entity.getX() + look.x * 0.8, entity.getY() + look.y + 1.4 + look.y * 0.5, entity.getZ() + look.z * 0.8, (level.getRandom().nextDouble() - 0.5) * 0.02, 0.1f, (level.getRandom().nextDouble() - 0.5) * 0.02
            );
            level.addParticle(
                    ParticleTypes.CAMPFIRE_COSY_SMOKE, entity.getX() + look.x * 0.8, entity.getY() + look.y + 1.4 + look.y * 0.5, entity.getZ() + look.z * 0.8, (level.getRandom().nextDouble() - 0.5) * 0.02, 0.1f, (level.getRandom().nextDouble() - 0.5) * 0.02
            );
            if ((entity instanceof Player player)) {
                if (!(player.getAbilities().instabuild)) {
                    usages -= 1;
                    stack.set(DataComponents.LORE, new ItemLore(java.util.List.of(
                            Component.translatable("itemLore.nicooo-last-tour.cigarette_1").withStyle(ChatFormatting.GRAY),
                            Component.translatable("itemLore.nicooo-last-tour.cigarette_2").withStyle(ChatFormatting.GRAY),
                            Component.translatable("itemLore.nicooo-last-tour.cigarette_3").withStyle(ChatFormatting.GRAY),
                            Component.translatable("itemLore.nicooo-last-tour.cigarette.is_activated").withStyle(ChatFormatting.WHITE),
                            Component.translatable("itemTooltip.nicooo-last-tour.cigarette_remaining_usages").withStyle(ChatFormatting.WHITE),
                            Component.literal(String.valueOf(usages)).withStyle(ChatFormatting.GOLD))
                    ));
                    stack.set(ModComponents.USAGES, usages);
                    if (usages <= 0) {
                        stack.shrink(1);
                        level.playSound(
                                null, entity.blockPosition(),
                                SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS,
                                0.5f,
                                0.8f
                        );
                    }
                    if (entity.is(player)) {
                        if (usages > 0) {
                            player.sendOverlayMessage(
                                    Component.translatable(
                                            "itemTooltip.nicooo-last-tour.cigarette_remaining_usages_overlay_message",
                                            Component.literal(String.valueOf(usages)).withStyle(ChatFormatting.GOLD)
                                    )
                            );
                        } else if (usages == 0) {
                            player.sendOverlayMessage(
                                    Component.translatable(
                                            "itemTooltip.nicooo-last-tour.cigarette_consumed_overlay_message"
                                    )
                            );
                        }
                    }
                } else {
                    stack.set(DataComponents.LORE, new ItemLore(java.util.List.of(
                            Component.translatable("itemLore.nicooo-last-tour.cigarette_1").withStyle(ChatFormatting.GRAY),
                            Component.translatable("itemLore.nicooo-last-tour.cigarette_2").withStyle(ChatFormatting.GRAY),
                            Component.translatable("itemLore.nicooo-last-tour.cigarette_3").withStyle(ChatFormatting.GRAY),
                            Component.translatable("itemLore.nicooo-last-tour.cigarette.is_activated").withStyle(ChatFormatting.WHITE))
                    ));
                }
            }
        }
        return stack;
    }
}
