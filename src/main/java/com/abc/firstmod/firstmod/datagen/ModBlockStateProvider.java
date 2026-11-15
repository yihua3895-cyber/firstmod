package com.abc.firstmod.firstmod.datagen;

import com.abc.firstmod.firstmod.Firstmod;
import com.abc.firstmod.firstmod.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

/** 同时生成方块状态、方块模型及其物品模型。 */
public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, Firstmod.MOD_ID, helper);
    }

    @Override
    protected void registerStatesAndModels() {
        ModBlocks.BLOCKS.getEntries().forEach(entry ->
                simpleBlockWithItem(entry.get(), cubeAll(entry.get())));
        simpleBlockWithItem(Firstmod.EXAMPLE_BLOCK.get(),
                models().cubeAll("example_block", mcLoc("block/stone")));
    }
}
