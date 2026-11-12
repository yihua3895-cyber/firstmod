package com.abc.firstmod.firstmod;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class modcreativetab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Firstmod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> FIRSTMOD_TAB =
            CREATIVE_MODE_TABS.register("firstmod_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("creativetab.firstmod.firstmod_tab"))
                    .icon(() -> new ItemStack(moditems.RUBY.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(moditems.RUBY.get());
                        output.accept(moditems.SAPPHIRE.get());
                        output.accept(moditems.SILVER_INGOT.get());
                        output.accept(Modblocks.RUBY_BLOCK.get());
                        output.accept(Modblocks.SAPPHIRE_BLOCK.get());
                        output.accept(Modblocks.SILVER_BLOCK.get());                        output.accept(Firstmod.EXAMPLE_BLOCK_ITEM.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
