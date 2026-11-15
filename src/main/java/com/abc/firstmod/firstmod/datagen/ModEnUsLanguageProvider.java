package com.abc.firstmod.firstmod.datagen;

import com.abc.firstmod.firstmod.Firstmod;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

/** 英文翻译；仅维护 en_us 语言条目。 */
public class ModEnUsLanguageProvider extends LanguageProvider {
    public ModEnUsLanguageProvider(PackOutput output) {
        super(output, Firstmod.MOD_ID, "en_us");
    }
    @Override
    protected void addTranslations() {
        add("creativetab.firstmod.firstmod_tab", "Firstmod");
        add("item.firstmod.ruby", "Ruby");
        add("item.firstmod.sapphire", "Sapphire");
        add("item.firstmod.silver_ingot", "Silver Ingot");
        add("block.firstmod.ruby_block", "Block of Ruby");
        add("block.firstmod.sapphire_block", "Block of Sapphire");
        add("block.firstmod.silver_block", "Block of Silver");
        add("block.firstmod.ruby_ore", "Ruby Ore");
        add("block.firstmod.sapphire_ore", "Sapphire Ore");
        add("block.firstmod.silver_ore", "Silver Ore");
        add("item.firstmod.raw_silver", "Raw Silver");
    }
}
