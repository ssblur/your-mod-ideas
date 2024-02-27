package com.ssblur.yourmodideas.items;

import com.ssblur.yourmodideas.YourModIdeasDamageSources;
import com.ssblur.yourmodideas.effect.YourModIdeasEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MayonnaiseItem extends Item {
  public MayonnaiseItem(Properties properties) {
    super(
      properties
        .food(new FoodProperties.Builder().saturationMod(4).nutrition(2).alwaysEat().build())
    );
  }

  @Override
  public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {
    int l = 0;
    if(livingEntity.hasEffect(YourModIdeasEffects.MAYO.get())) {
      var effect = livingEntity.getEffect(YourModIdeasEffects.MAYO.get());
      assert effect != null;
      l = effect.getAmplifier() + 1;
    }
    if(l >= 4)
      livingEntity.hurt(YourModIdeasDamageSources.mayoDamage(livingEntity), Float.POSITIVE_INFINITY);

    livingEntity.addEffect(new MobEffectInstance(YourModIdeasEffects.MAYO.get(), 2400, l));

    return super.finishUsingItem(itemStack, level, livingEntity);
  }
}
