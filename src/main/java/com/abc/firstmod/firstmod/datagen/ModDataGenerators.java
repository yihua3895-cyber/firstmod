package com.abc.firstmod.firstmod.datagen;

import com.abc.firstmod.firstmod.Firstmod;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Set;

/** 将数据提供器接入 Forge；只有创建 Provider 类而不注册，是不会生成文件的。 */
@Mod.EventBusSubscriber(modid = Firstmod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModDataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        var generator = event.getGenerator();
        var output = generator.getPackOutput();
        var helper = event.getExistingFileHelper();
        generator.addProvider(event.includeClient(), new ModBlockStateProvider(output, helper));
        generator.addProvider(event.includeClient(), new ModItemModelProvider(output, helper));
        generator.addProvider(event.includeClient(), new ModEnUsLanguageProvider(output));
        generator.addProvider(event.includeClient(), new ModZhCnLanguageProvider(output));
        generator.addProvider(event.includeServer(), new ModRecipeProvider(output));
        generator.addProvider(event.includeServer(), new ModBlockTagsProvider(output, event.getLookupProvider(), helper));
        // Forge 在运行 runData 时触发此事件；这是 MOD 事件总线上的事件。
        // includeServer 表示是否生成服务端数据（掉落表、配方、标签等）。
        // build.gradle 中的 --all 已包含服务端数据，所以会执行这个提供器。
        event.getGenerator().addProvider(event.includeServer(), new LootTableProvider(
                // 输出位置由 build.gradle 决定，本项目是 src/generated/resources。
                event.getGenerator().getPackOutput(),
                // 不要求生成原版的全部掉落表；这里只生成 ModBlocks 的方块掉落表。
                Set.of(),
                List.of(new LootTableProvider.SubProviderEntry(
                        // 方法引用：生成器需要时调用构造方法，创建我们的规则提供器。
                        ModBlockLootTablesProvider::new,
                        // 声明这些是方块掉落表，使用方块掉落所需的上下文参数。
                        LootContextParamSets.BLOCK))));
    }
}
