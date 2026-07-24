package com.nicooo.item;

import net.minecraft.server.dedicated.Settings;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class NightVisionGogglesItem extends Item {

    public NightVisionGogglesItem(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerLevel level, Entity entity, @Nullable EquipmentSlot slot) {
        super.inventoryTick(stack, level, entity, slot);

        if (!(entity instanceof Player player)) return;
        if (level.isClientSide()) return;

        boolean wearing = player.getItemBySlot(EquipmentSlot.HEAD).is(this);

        boolean active = stack.getOrDefault(ModComponents.NIGHT_VISION_ACTIVE, false);
        if (wearing) {
            if (!(player.hasEffect(MobEffects.NIGHT_VISION)) || (player.getEffect(MobEffects.NIGHT_VISION).getDuration() < 220)) {
                player.addEffect(new MobEffectInstance(
                        MobEffects.NIGHT_VISION,
                        220,
                        0,
                        false,
                        false
                ));
            }
        }
        if (wearing && !active) {
            // Se acaba de poner

            level.playSound(
                    null,
                    entity.blockPosition(),
                    ModSounds.NIGHT_VISION_ON,
                    SoundSource.PLAYERS,
                    0.25f,
                    0.8f
            );

            stack.set(ModComponents.NIGHT_VISION_ACTIVE, true);
        }

        else if (!wearing && active) {
            // Se acaba de quitar
            player.removeEffect(MobEffects.NIGHT_VISION);

            level.playSound(
                    null,
                    entity.blockPosition(),
                    ModSounds.NIGHT_VISION_OFF,
                    SoundSource.PLAYERS,
                    0.25f,
                    0.8f
            );

            stack.set(ModComponents.NIGHT_VISION_ACTIVE, false);
        }
    }
}
