package com.abc.firstmod.firstmod;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

// 此处的值应与 META-INF/mods.toml 文件中的对应条目一致
@Mod(Firstmod.MOD_ID)
public class Firstmod {

    // 在统一的位置定义模组 ID，供各处引用
    public static final String MOD_ID = "firstmod";
    public static final String MODID = MOD_ID;
    // 直接引用 SLF4J 日志记录器
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    // 示例方块及其物品形式，使用原版石头属性。
    public static final RegistryObject<Block> EXAMPLE_BLOCK = BLOCKS.register("example_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Item> EXAMPLE_BLOCK_ITEM = ITEMS.register("example_block",
            () -> new BlockItem(EXAMPLE_BLOCK.get(), new Item.Properties()));

    public Firstmod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // 注册 commonSetup 方法，使其在模组加载时被调用
        modEventBus.addListener(this::commonSetup);

        // 将方块的延迟注册器注册到模组事件总线，以完成方块注册
        BLOCKS.register(modEventBus);
        // 将物品的延迟注册器注册到模组事件总线，以完成物品注册
        ITEMS.register(modEventBus);
        moditems.register(modEventBus);
        Modblocks.register(modEventBus);
        // 将创造模式物品栏的延迟注册器注册到模组事件总线，以完成物品栏注册
        modcreativetab.register(modEventBus);

        // 将当前实例注册到事件总线，以监听服务器事件及其他需要处理的游戏事件
        MinecraftForge.EVENT_BUS.register(this);

        // 注册本模组的 ForgeConfigSpec，让 Forge 为我们创建和加载配置文件
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // 通用初始化代码
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

    }

    // 可以使用 SubscribeEvent 注解，让事件总线自动发现并调用对应的方法
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // 在服务器启动时执行操作
        LOGGER.info("HELLO from server starting");
    }

    // 可以使用 EventBusSubscriber，自动注册类中所有带有 @SubscribeEvent 注解的静态方法
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // 客户端初始化代码
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
