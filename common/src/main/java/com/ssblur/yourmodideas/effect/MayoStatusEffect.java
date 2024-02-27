package com.ssblur.yourmodideas.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class MayoStatusEffect extends MobEffect {
  public MayoStatusEffect() {
    super(MobEffectCategory.HARMFUL, 0xcc00cc);
  }

  @Override
  public void applyEffectTick(LivingEntity entity, int amplifier) {}
}
