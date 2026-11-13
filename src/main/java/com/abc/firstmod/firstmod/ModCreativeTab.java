package com.abc.firstmod.firstmod;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Firstmod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> FIRSTMOD_TAB =
            CREATIVE_MODE_TABS.register("firstmod_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("creativetab.firstmod.firstmod_tab"))
                    .icon(() -> new ItemStack(ModItems.RUBY.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.RUBY.get());
                        output.accept(ModItems.SAPPHIRE.get());
                        output.accept(ModItems.SILVER_INGOT.get());
                        output.accept(ModItems.RAW_SILVER.get());
                        output.accept(ModBlocks.RUBY_ORE.get());
                        output.accept(ModBlocks.SAPPHIRE_ORE.get());
                        output.accept(ModBlocks.SILVER_ORE.get());
                        output.accept(ModBlocks.RUBY_BLOCK.get());
                        output.accept(ModBlocks.SAPPHIRE_BLOCK.get());
                        output.accept(ModBlocks.SILVER_BLOCK.get());                        output.accept(Firstmod.EXAMPLE_BLOCK_ITEM.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
