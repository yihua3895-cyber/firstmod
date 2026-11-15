package com.abc.firstmod.firstmod.datagen;

import com.abc.firstmod.firstmod.Firstmod;
import com.abc.firstmod.firstmod.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup,
                                ExistingFileHelper helper) {
        super(output, lookup, Firstmod.MOD_ID, helper);
    }

    @Override
    protected void addTags(HolderLookup.Provider lookup) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                ModBlocks.RUBY_BLOCK.get(), ModBlocks.SAPPHIRE_BLOCK.get(), ModBlocks.SILVER_BLOCK.get(),
                ModBlocks.RUBY_ORE.get(), ModBlocks.SAPPHIRE_ORE.get(), ModBlocks.SILVER_ORE.get());
        tag(BlockTags.NEEDS_IRON_TOOL).add(ModBlocks.RUBY_ORE.get(), ModBlocks.SAPPHIRE_ORE.get());
        tag(BlockTags.NEEDS_STONE_TOOL).add(ModBlocks.SILVER_ORE.get());
    }
}
