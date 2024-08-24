package com.ssblur.obt.items;

import com.ssblur.obt.OBTDamageSources;
import com.ssblur.obt.effect.OBTEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Objects;

public class MayonnaiseItem extends Item {
  public MayonnaiseItem(Properties properties) {
    super(
      properties
        .food(new FoodProperties.Builder().saturationModifier(4).nutrition(2).alwaysEdible().build())
    );
  }

  @Override
  public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {
    int l = 0;
    var mayo = Objects.requireNonNull(OBTEffects.get(OBTEffects.MAYO));
    if(livingEntity.hasEffect(mayo)) {
      var effect = livingEntity.getEffect(mayo);
      assert effect != null;
      l = effect.getAmplifier() + 1;
    }
    if(l >= 4)
      livingEntity.hurt(OBTDamageSources.mayoDamage(livingEntity), Float.POSITIVE_INFINITY);

    livingEntity.addEffect(new MobEffectInstance(mayo, 2400, l));

    return super.finishUsingItem(itemStack, level, livingEntity);
  }
}
