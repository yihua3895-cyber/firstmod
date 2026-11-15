package com.abc.firstmod.firstmod;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister <Item> ITEMS=
            DeferredRegister.create(ForgeRegistries.ITEMS,Firstmod.MODID);
    // 普通材料物品，默认每组最多堆叠 64 个。
    public static final RegistryObject<Item> RUBY =
            ITEMS.register("ruby", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SAPPHIRE =
            ITEMS.register("sapphire", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SILVER_INGOT =
            ITEMS.register("silver_ingot", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAW_SILVER =
            ITEMS.register("raw_silver", () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
    public static final RegistryObject<Item> RUBY_APPLE =
            ITEMS.register("ruby_apple", () -> new Item(new Item.Properties().food(ModFoods.RUBY_APPLE)));
    // 原版煤炭燃烧 1600 tick，红宝石煤炭为其十倍。
    public static final RegistryObject<Item> RUBY_COAL =
            ITEMS.register("ruby_coal", () -> new ModFuel(new Item.Properties(), 16000));
}
