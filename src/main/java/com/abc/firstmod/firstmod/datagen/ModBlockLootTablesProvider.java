package com.abc.firstmod.firstmod.datagen;

import com.abc.firstmod.firstmod.ModBlocks;
import com.abc.firstmod.firstmod.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

/**
 * ModBlocks 中全部方块的掉落表生成规则（Minecraft 1.20.1 / Forge）。
 * 开发时运行 Gradle 的 runData，由本类生成 JSON；游戏运行时读取 JSON。
 * 不要直接修改生成的 JSON，否则下次 runData 会覆盖修改。
 */
public class ModBlockLootTablesProvider extends BlockLootSubProvider {
    public ModBlockLootTablesProvider() {
        // 第一个参数：不受爆炸损耗影响的物品集合。空集合表示没有特殊豁免。
        // 第二个参数：生成时允许使用的功能标志；这里启用所有功能标志。
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        // 红宝石块：掉落方块自身，并使用原版的爆炸存活条件。
        // get() 取得已注册的方块对象；dropSelf 帮我们构造完整的掉落表。
        dropSelf(ModBlocks.RUBY_BLOCK.get());
        // 同样的规则可以复用：每增加这一行，就能生成一个独立的 JSON 文件。
        dropSelf(ModBlocks.SAPPHIRE_BLOCK.get());
        dropSelf(ModBlocks.SILVER_BLOCK.get());

        // add 的第一个参数决定给哪个方块生成掉落表。
        // createOreDrop 构造与钻石矿类似的规则：
        // 1. 精准采集时掉落矿石自身；
        // 2. 否则基础掉落一个红宝石，时运按原版 ore_drops 公式增加数量；
        // 3. 对物品掉落应用爆炸损耗。
        add(ModBlocks.RUBY_ORE.get(),
                createOreDrop(ModBlocks.RUBY_ORE.get(), ModItems.RUBY.get()));

        // 蓝宝石矿：保持原来手写表的规则，固定掉落一个蓝宝石。
        // dropOther 表示掉落另一个物品，同时自动加入爆炸存活条件。
        // 这里没有精准采集分支，也没有时运函数；这两种附魔都不改变掉落。
        dropOther(ModBlocks.SAPPHIRE_ORE.get(), ModItems.SAPPHIRE.get());

        // 银矿：类似原版铜矿，但产物是我们的粗银，不能直接使用
        // createCopperOreDrops（该方法内部把产物固定写成了原版粗铜）。
        // createSilkTouchDispatchTable 会生成两个分支：
        // 有精准采集 -> 掉落银矿石自身；否则 -> 执行下面的粗银掉落规则。
        add(ModBlocks.SILVER_ORE.get(), createSilkTouchDispatchTable(
                ModBlocks.SILVER_ORE.get(),
                // 爆炸损耗包裹粗银分支；最终作用在数量和时运计算后的物品上。
                applyExplosionDecay(ModBlocks.SILVER_ORE.get(),
                        LootItem.lootTableItem(ModItems.RAW_SILVER.get())
                                // 先随机设置基础数量为 2～5 个（包含两端）。
                                .apply(SetItemCountFunction.setCount(
                                        UniformGenerator.between(2.0F, 5.0F)))
                                // 再按原版矿石的时运公式增加数量。
                                .apply(ApplyBonusCount.addOreBonusCount(
                                        Enchantments.BLOCK_FORTUNE)))));

        // 注意：采集工具等级仍由方块属性和方块标签决定。
        // 挖矿经验仍由 ModBlocks 中的 DropExperienceBlock 决定，不写在此表里。
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        // 直接读取 ModBlocks 注册器中的全部方块，避免手动维护第二份清单。
        // getEntries() 取得注册条目，map(RegistryObject::get) 将条目转换成方块。
        // 基类会要求每个方块都有掉落规则；新增方块后若忘记在 generate()
        // 中添加规则，runData 就会报错，帮助我们及时发现遗漏。
        // Firstmod 中另一个注册器里的 example_block 不在此范围内；
        // 它原本没有掉落表，本次不为它新增玩法规则。
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).toList();
    }
}
