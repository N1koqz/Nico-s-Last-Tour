package com.nicooo.item;

import com.nicooo.NicoooLastTour;

import com.nicooo.block.ModBlocks;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorMaterials;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.List;

public class ModItems {

    // Blocks Items

    public static final Item CHITO_STATUE_HEAD = registerItem(
            "chito_statue_head", new BlockItem(ModBlocks.CHITO_STATUE_HEAD_BLOCK, new Item.Properties()
                    .component(
                            DataComponents.LORE, new ItemLore(java.util.List.of(Component
                                    .translatable("itemLore.nicooo-last-tour.chito_statue_head").withStyle(ChatFormatting.GRAY))
                            )
                    )
                    .setId(ResourceKey.create(BuiltInRegistries.ITEM.key(), Identifier.fromNamespaceAndPath(NicoooLastTour.MOD_ID,
                                                    "chito_statue_head"))
                    )
            )
    );

    public static final Item CHITO_STATUE_BODY = registerItem(
            "chito_statue_body", new BlockItem(ModBlocks.CHITO_STATUE_BODY_BLOCK, new Item.Properties()
                    .component(
                            DataComponents.LORE, new ItemLore(java.util.List.of(Component
                                    .translatable("itemLore.nicooo-last-tour.chito_statue_body").withStyle(ChatFormatting.GRAY))
                            )
                    )
                    .setId(ResourceKey.create(BuiltInRegistries.ITEM.key(), Identifier.fromNamespaceAndPath(NicoooLastTour.MOD_ID,
                                            "chito_statue_body"))
                    )
            )
    );

    // PLUSHIEEES

    public static final Item CHITO_FUMO = registerItem(
            "chito_fumo", new BlockItem(ModBlocks.CHITO_FUMO_BLOCK, new Item.Properties()
                    .component(
                            DataComponents.LORE, new ItemLore(java.util.List.of(Component
                                    .translatable("itemLore.nicooo-last-tour.chito_fumo").withStyle(ChatFormatting.GRAY))
                            )
                    )
                    .equippable(EquipmentSlot.HEAD)
                    .setId(ResourceKey.create(BuiltInRegistries.ITEM.key(), Identifier.fromNamespaceAndPath(NicoooLastTour.MOD_ID, "chito_fumo")))
            )
    );

    public static final Item YUURI_FUMO = registerItem(
            "yuuri_fumo", new BlockItem(ModBlocks.YUURI_FUMO_BLOCK, new Item.Properties()
                    .component(
                            DataComponents.LORE, new ItemLore(java.util.List.of(Component
                                    .translatable("itemLore.nicooo-last-tour.yuuri_fumo").withStyle(ChatFormatting.GRAY))
                            )
                    )
                    .equippable(EquipmentSlot.HEAD)
                    .setId(ResourceKey.create(BuiltInRegistries.ITEM.key(), Identifier.fromNamespaceAndPath(NicoooLastTour.MOD_ID, "yuuri_fumo")))
            )
    );

    // Armor pieces

    public static final Item CHITO_HELMET = registerItem(
            "chito_helmet", new Item(new Item.Properties()
                    .component(
                            DataComponents.LORE, new ItemLore(java.util.List.of(Component
                                    .translatable("itemLore.nicooo-last-tour.chito_helmet").withStyle(ChatFormatting.GRAY))
                            )
                    )
                    .humanoidArmor(ArmorMaterials.IRON,ArmorType.HELMET)
                    .setId(ResourceKey.create(BuiltInRegistries.ITEM.key(), Identifier.fromNamespaceAndPath(NicoooLastTour.MOD_ID, "chito_helmet")))
                    .equippable(EquipmentSlot.HEAD)
            )
    );

    public static final Item YUURI_HELMET = registerItem(
            "yuuri_helmet", new Item(new Item.Properties()
                    .component(
                            DataComponents.LORE, new ItemLore(java.util.List.of(Component
                                    .translatable("itemLore.nicooo-last-tour.yuuri_helmet").withStyle(ChatFormatting.GRAY))
                            )
                    )
                    .humanoidArmor(ArmorMaterials.IRON,ArmorType.HELMET)
                    .setId(ResourceKey.create(BuiltInRegistries.ITEM.key(), Identifier.fromNamespaceAndPath(NicoooLastTour.MOD_ID, "yuuri_helmet")))
                    .equippable(EquipmentSlot.HEAD)
            )
    );

    public static final Item KANAZAWA_GOGGLES = registerItem(
            "kanazawa_goggles", new NightVisionGogglesItem(new Item.Properties()
                    .component(
                            DataComponents.LORE, new ItemLore(java.util.List.of(Component
                                    .translatable("itemLore.nicooo-last-tour.kanazawa_goggles").withStyle(ChatFormatting.GRAY))
                            )
                    )
                    .humanoidArmor(ArmorMaterials.LEATHER,ArmorType.HELMET)
                    .setId(ResourceKey.create(BuiltInRegistries.ITEM.key(), Identifier.fromNamespaceAndPath(NicoooLastTour.MOD_ID, "kanazawa_goggles")))
                    .equippable(EquipmentSlot.HEAD)
            )
    );

    public static final Item GARDENER_HAT = registerItem(
            "gardener_hat", new Item(new Item.Properties()
                    .component(
                            DataComponents.LORE, new ItemLore(java.util.List.of(Component
                                    .translatable("itemLore.nicooo-last-tour.gardener_hat").withStyle(ChatFormatting.GRAY))
                            )
                    )
                    .humanoidArmor(ArmorMaterials.DIAMOND,ArmorType.HELMET)
                    .setId(ResourceKey.create(BuiltInRegistries.ITEM.key(), Identifier.fromNamespaceAndPath(NicoooLastTour.MOD_ID, "gardener_hat")))
                    .equippable(EquipmentSlot.HEAD)
            )
    );

    public static final Item MAJIME_EGG = registerItem(
            "majime_egg", new Item(new Item.Properties()
                    .component(
                            DataComponents.LORE, new ItemLore(java.util.List.of(Component
                                    .translatable("itemLore.nicooo-last-tour.majime_egg").withStyle(ChatFormatting.GRAY))
                            )
                    )
                    .humanoidArmor(ArmorMaterials.LEATHER,ArmorType.HELMET)
                    .setId(ResourceKey.create(BuiltInRegistries.ITEM.key(), Identifier.fromNamespaceAndPath(NicoooLastTour.MOD_ID, "majime_egg")))
                    .equippable(EquipmentSlot.HEAD)
            )
    );

    // Other Items

    public static final Item POTATO_FLOUR = registerItem(
            "potato_flour", new Item(new Item.Properties()
                    .component(
                            DataComponents.LORE, new ItemLore(java.util.List.of(Component
                                    .translatable("itemLore.nicooo-last-tour.potato_flour").withStyle(ChatFormatting.GRAY))
                            )
                    )
                    .setId(ResourceKey.create(BuiltInRegistries.ITEM.key(), Identifier.fromNamespaceAndPath(NicoooLastTour.MOD_ID,"potato_flour"))))
    );

    public static final Item EMPTY_BEER = registerItem(
            "empty_beer", new Item(new Item.Properties()
                    .component(
                            DataComponents.LORE, new ItemLore(java.util.List.of(Component
                                    .translatable("itemLore.nicooo-last-tour.empty_beer").withStyle(ChatFormatting.GRAY))
                            )
                    )
                    .setId(ResourceKey.create(BuiltInRegistries.ITEM.key(), Identifier.fromNamespaceAndPath(NicoooLastTour.MOD_ID, "empty_beer")))
            )
    );

    // Consumables

    public static final Item BEER = registerItem(
            "beer", new BeerItem(new Item.Properties()
                    .component(
                            DataComponents.LORE, new ItemLore(java.util.List.of(Component
                                    .translatable("itemLore.nicooo-last-tour.beer").withStyle(ChatFormatting.GRAY))
                            )
                    )
                    .stacksTo(6)
                    .setId(ResourceKey.create(BuiltInRegistries.ITEM.key(), Identifier.fromNamespaceAndPath(NicoooLastTour.MOD_ID, "beer")))
                    .usingConvertsTo(ModItems.EMPTY_BEER)
                    .craftRemainder(Items.BUCKET)
            )
    );

    public static final Item RAW_RATIONS = registerItem(
            "raw_rations", new Item(new Item.Properties()
                    .component(
                            DataComponents.LORE, new ItemLore(java.util.List.of(Component
                                    .translatable("itemLore.nicooo-last-tour.raw_rations").withStyle(ChatFormatting.GRAY))
                            )
                    )
                    .setId(ResourceKey.create(BuiltInRegistries.ITEM.key(), Identifier.fromNamespaceAndPath(NicoooLastTour.MOD_ID, "raw_rations")))
                    .food(new FoodProperties(3,2,false)))
    );

    public static final Item RAW_CHOCOLATE_RATIONS = registerItem(
            "raw_chocolate_rations", new Item(new Item.Properties()
                    .component(
                            DataComponents.LORE, new ItemLore(java.util.List.of(Component
                                    .translatable("itemLore.nicooo-last-tour.raw_chocolate_rations").withStyle(ChatFormatting.GRAY))
                            )
                    )
                    .setId(ResourceKey.create(BuiltInRegistries.ITEM.key(), Identifier.fromNamespaceAndPath(NicoooLastTour.MOD_ID, "raw_chocolate_rations"))).food(new FoodProperties(3,2,false)))
    );

    public static final Item RATIONS = registerItem(
            "rations", new Item(new Item.Properties()
                    .component(
                            DataComponents.LORE, new ItemLore(java.util.List.of(Component
                                    .translatable("itemLore.nicooo-last-tour.rations").withStyle(ChatFormatting.GRAY))
                            )
                    )
                    .setId(ResourceKey.create(BuiltInRegistries.ITEM.key(), Identifier.fromNamespaceAndPath(NicoooLastTour.MOD_ID, "rations")))
                    .craftRemainder(Items.BUCKET)
                    .food(new FoodProperties(6,3,false)))
    );

    public static final Item CHOCOLATE_RATIONS = registerItem(
            "chocolate_rations", new Item(new Item.Properties()
                    .component(
                            DataComponents.LORE, new ItemLore(java.util.List.of(Component
                                    .translatable("itemLore.nicooo-last-tour.chocolate_rations").withStyle(ChatFormatting.GRAY))
                            )
                    )
                    .setId(ResourceKey.create(BuiltInRegistries.ITEM.key(), Identifier.fromNamespaceAndPath(NicoooLastTour.MOD_ID, "chocolate_rations")))
                    .craftRemainder(Items.BUCKET)
                    .food(new FoodProperties(7,4,false)))
    );

    public static final Item CIGARETTE = registerItem(
            "cigarette", new CigaretteItem(new Item.Properties()
                    .component(
                            DataComponents.LORE, new ItemLore(java.util.List.of(Component
                                    .translatable("itemLore.nicooo-last-tour.cigarette").withStyle(ChatFormatting.GRAY))

                            )
                    )
                    .stacksTo(1)
                    .component(ModComponents.USAGES, 5).component(ModComponents.IS_ACTIVATED, false)
                    .setId(ResourceKey.create(BuiltInRegistries.ITEM.key(), Identifier.fromNamespaceAndPath(NicoooLastTour.MOD_ID, "cigarette"))))
    );

    private static Item registerItem(String name, Item item) {
        return Registry.register(
                BuiltInRegistries.ITEM,
                Identifier.fromNamespaceAndPath(NicoooLastTour.MOD_ID, name),
                item
        );
    }

    public static void registerModItems() {

        NicoooLastTour.LOGGER.info("Loading mod items for: " + NicoooLastTour.MOD_ID);

        Registry.register(
                BuiltInRegistries.CREATIVE_MODE_TAB,
                CUSTOM_CREATIVE_TAB_KEY,
                CUSTOM_CREATIVE_TAB
        );
    }

    public static final ResourceKey<CreativeModeTab> CUSTOM_CREATIVE_TAB_KEY = ResourceKey.create(
            BuiltInRegistries.CREATIVE_MODE_TAB.key(), Identifier.fromNamespaceAndPath(NicoooLastTour.MOD_ID, "creative_tab")
    );

    public static final CreativeModeTab CUSTOM_CREATIVE_TAB = FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItems.CHITO_STATUE_HEAD))
            .title(Component.translatable("Nico's Last Tour"))
            .displayItems((params, output) -> {

                output.accept(ModItems.CHITO_STATUE_BODY); // Blocks Item
                output.accept(ModItems.CHITO_STATUE_HEAD); // Blocks Item

                output.accept(ModItems.CHITO_FUMO); // PLUSHIEEES
                output.accept(ModItems.YUURI_FUMO); // PLUSHIEEES

                output.accept(ModItems.CHITO_HELMET); // Armor piece
                output.accept(ModItems.YUURI_HELMET); // Armor piece
                output.accept(ModItems.KANAZAWA_GOGGLES); // Armor piece
                output.accept(ModItems.GARDENER_HAT); // Armor piece
                output.accept(ModItems.MAJIME_EGG); // Armor piece

                output.accept(ModItems.POTATO_FLOUR); // Other item
                output.accept(ModItems.RAW_RATIONS); // Consumable
                output.accept(ModItems.RATIONS); // Consumable
                output.accept(ModItems.RAW_CHOCOLATE_RATIONS); // Consumable
                output.accept(ModItems.CHOCOLATE_RATIONS); // Consumable
                output.accept(ModItems.BEER); // Consumable
                output.accept(ModItems.EMPTY_BEER); // Other item
                output.accept(ModItems.CIGARETTE); // Consumable

                // And custom ItemStacks

                /*ItemStack stack = new ItemStack(Items.SEA_PICKLE);
                stack.set(DataComponents.ITEM_NAME, Component.literal("Pickle Rick"));
                stack.set(DataComponents.LORE, new ItemLore(List.of(Component.literal("I'm pickle riiick!!"))));
                output.accept(stack);*/
            })
            .build();

}