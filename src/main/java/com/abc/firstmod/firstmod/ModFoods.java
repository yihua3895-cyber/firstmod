package com.abc.firstmod.firstmod;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties RUBY_APPLE = new FoodProperties.Builder()
            .nutrition(10)
            .saturationMod(2.0F)
            .alwaysEat()
            // 等级从 0 开始：4 为抗性提升 V；1200 tick 为一分钟。
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 4), 1.0F)
            .build();
}
