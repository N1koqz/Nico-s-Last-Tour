package com.nicooo.item;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import java.util.Random;

import static com.ibm.icu.text.PluralRules.Operand.f;

public class BeerItem extends Item {

    public BeerItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemUseAnimation getUseAnimation(ItemStack stack) {
        return ItemUseAnimation.TOOT_HORN;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 32;
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        player.startUsingItem(hand);
        return InteractionResult.CONSUME;
    }

    @Override
    public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int remainingUseDuration) {
        if (!level.isClientSide() && remainingUseDuration % 6 == 0) {
            level.playSound(
                    null,
                    entity.blockPosition(),
                    SoundEvents.GENERIC_DRINK.value(),
                    SoundSource.PLAYERS,
                    0.5f,
                    0.9f + level.getRandom().nextFloat() * 0.05f
            );
        }

        super.onUseTick(level, entity, stack, remainingUseDuration);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {

        if (entity instanceof Player player) {
            if (!level.isClientSide()) {
                entity.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 1500, 0, false, false));
                entity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 600, 1, false, false));
                entity.addEffect(new MobEffectInstance(MobEffects.LUCK, 1500, 3, false, false));
            }
            player.getCooldowns().addCooldown(ModItems.BEER.getDefaultInstance(), 1);
        }

        if (!(entity instanceof Player player && player.getAbilities().instabuild)) {
            stack.shrink(1);
        }

        return stack;
    }

}
