package com.ssblur.yourmodideas.entity;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.animal.horse.SkeletonHorse;
import net.minecraft.world.level.Level;

public class NightmareEntity extends Horse {
  public NightmareEntity(EntityType<? extends Horse> entityType, Level level) {
    super(entityType, level);
  }

  @Override
  public void tick() {
    super.tick();
    if(level().dimension() == Level.NETHER) {
      this.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 60, 3, false, false));
      this.addEffect(new MobEffectInstance(MobEffects.JUMP, 60, 3, false, false));
      this.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 60, 1, false, false));
      this.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 60, 1, false, false));
    }
  }

  @Override
  public boolean fireImmune() {
    return true;
  }
}
