package com.abc.firstmod.firstmod.datagen;

import com.abc.firstmod.firstmod.Firstmod;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

/** 简体中文翻译；仅维护 zh_cn 语言条目。 */
public class ModZhCnLanguageProvider extends LanguageProvider {
    public ModZhCnLanguageProvider(PackOutput output) {
        super(output, Firstmod.MOD_ID, "zh_cn");
    }
    @Override
    protected void addTranslations() {
        add("item.firstmod.ruby_coal", "红宝石煤炭");
        add("item.firstmod.ruby_apple", "红宝石苹果");
        add("creativetab.firstmod.firstmod_tab", "Firstmod");
        add("item.firstmod.ruby", "红宝石");
        add("item.firstmod.sapphire", "蓝宝石");
        add("item.firstmod.silver_ingot", "银锭");
        add("block.firstmod.ruby_block", "红宝石块");
        add("block.firstmod.sapphire_block", "蓝宝石块");
        add("block.firstmod.silver_block", "银块");
        add("block.firstmod.ruby_ore", "红宝石矿石");
        add("block.firstmod.sapphire_ore", "蓝宝石矿石");
        add("block.firstmod.silver_ore", "银矿石");
        add("item.firstmod.raw_silver", "粗银");
    }
}
