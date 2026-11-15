package com.abc.firstmod.firstmod.datagen;

import com.abc.firstmod.firstmod.Firstmod;
import com.abc.firstmod.firstmod.ModBlocks;
import com.abc.firstmod.firstmod.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.RUBY_APPLE.get())
                .pattern("RRR").pattern("RAR").pattern("RRR")
                .define('R', ModItems.RUBY.get())
                .define('A', net.minecraft.world.item.Items.APPLE)
                .unlockedBy("has_ruby", has(ModItems.RUBY.get()))
                .save(output);
        compress(output, ModItems.RUBY.get(), ModBlocks.RUBY_BLOCK.get());
        decompress(output, ModBlocks.RUBY_BLOCK.get(), ModItems.RUBY.get(), "ruby_from_block");
        compress(output, ModItems.SAPPHIRE.get(), ModBlocks.SAPPHIRE_BLOCK.get());
        decompress(output, ModBlocks.SAPPHIRE_BLOCK.get(), ModItems.SAPPHIRE.get(), "sapphire_from_block");
        compress(output, ModItems.SILVER_INGOT.get(), ModBlocks.SILVER_BLOCK.get());
        decompress(output, ModBlocks.SILVER_BLOCK.get(), ModItems.SILVER_INGOT.get(), "silver_ingot_from_block");

        // 熔炼配方：同一种材料的熔炉、高炉配方放在一起。
        blasting(output, ModBlocks.RUBY_ORE.get(), ModItems.RUBY.get(), 1.0F, "ruby_from_blasting_ruby_ore");
        blasting(output, ModBlocks.SAPPHIRE_ORE.get(), ModItems.SAPPHIRE.get(), 1.0F, "sapphire_from_blasting_sapphire_ore");
        blasting(output, ModBlocks.SILVER_ORE.get(), ModItems.SILVER_INGOT.get(), 0.7F, "silver_ingot_from_blasting_silver_ore");
        smelting(output, ModItems.RAW_SILVER.get(), ModItems.SILVER_INGOT.get(), 0.7F, "silver_ingot_from_smelting_raw_silver");
        blasting(output, ModItems.RAW_SILVER.get(), ModItems.SILVER_INGOT.get(), 0.7F, "silver_ingot_from_blasting_raw_silver");
    }

    /** 熔炉每次产出一个物品，耗时 200 tick（10 秒）。 */
    private void smelting(Consumer<FinishedRecipe> output, ItemLike input, ItemLike result,
                          float experience, String recipeId) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(input), RecipeCategory.MISC, result, experience, 200)
                .unlockedBy("has_raw_silver", has(input))
                .save(output, new ResourceLocation(Firstmod.MOD_ID, recipeId));
    }
    /** 高炉每次产出一个物品，耗时 100 tick（5 秒）。 */
    private void blasting(Consumer<FinishedRecipe> output, ItemLike input, ItemLike result,
                          float experience, String recipeId) {
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(input), RecipeCategory.MISC,
                        result, experience, 100)
                .unlockedBy("has_ingredient", has(input))
                .save(output, new ResourceLocation(Firstmod.MOD_ID, recipeId));
    }

    /** 九个材料合成一个储存块。 */
    private void compress(Consumer<FinishedRecipe> output, ItemLike material, ItemLike block) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, block)
                .pattern("###").pattern("###").pattern("###").define('#', material)
                .unlockedBy("has_material", has(material)).save(output);
    }

    /** 一个储存块拆回九个材料。 */
    private void decompress(Consumer<FinishedRecipe> output, ItemLike block, ItemLike material, String reverseId) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, material, 9).requires(block)
                .unlockedBy("has_block", has(block))
                .save(output, new ResourceLocation(Firstmod.MOD_ID, reverseId));
    }
}
