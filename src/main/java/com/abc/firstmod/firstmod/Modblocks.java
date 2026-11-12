package com.abc.firstmod.firstmod;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Modblocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Firstmod.MOD_ID);
    public static final DeferredRegister<Item> BLOCK_ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Firstmod.MOD_ID);
    // strength 的两个参数分别是硬度和爆炸抗性。
    public static final RegistryObject<Block> RUBY_BLOCK =
            BLOCKS.register("ruby_block", () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_RED).strength(5F, 6.0F)
                    .sound(SoundType.METAL).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> SAPPHIRE_BLOCK =
            BLOCKS.register("sapphire_block", () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLUE).strength(5F, 6.0F)
                    .sound(SoundType.METAL).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> SILVER_BLOCK =
            BLOCKS.register("silver_block", () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL).strength(3F, 6.0F)
                    .sound(SoundType.METAL).requiresCorrectToolForDrops()));

    public static final RegistryObject<Item> RUBY_BLOCK_ITEM =
            BLOCK_ITEMS.register("ruby_block", () -> new BlockItem(RUBY_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> SAPPHIRE_BLOCK_ITEM =
            BLOCK_ITEMS.register("sapphire_block", () -> new BlockItem(SAPPHIRE_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> SILVER_BLOCK_ITEM =
            BLOCK_ITEMS.register("silver_block", () -> new BlockItem(SILVER_BLOCK.get(), new Item.Properties()));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        BLOCK_ITEMS.register(eventBus);
    }
}
