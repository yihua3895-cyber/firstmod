package com.abc.firstmod.firstmod.datagen;

import com.abc.firstmod.firstmod.Firstmod;
import com.abc.firstmod.firstmod.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

/** 材料物品使用对应纹理生成平面模型。 */
public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, Firstmod.MOD_ID, helper);
    }

    @Override
    protected void registerModels() {
        ModItems.ITEMS.getEntries().forEach(entry -> {
            if (entry == ModItems.RUBY_APPLE) {
                withExistingParent("ruby_apple", mcLoc("item/generated"))
                        .texture("layer0", mcLoc("item/apple"));
            } else if (entry == ModItems.RUBY_COAL) {
                withExistingParent("ruby_coal", mcLoc("item/generated"))
                        .texture("layer0", modLoc("item/ruby_coal"));
            } else {
                basicItem(entry.get());
            }
        });
    }
}
