package com.ssblur.yourmodideas.events;

import com.ssblur.yourmodideas.YourModIdeasGameRules;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.EntityEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Phantom;

public class EntityHurtEvent implements EntityEvent.LivingHurt {
  @Override
  public EventResult hurt(LivingEntity entity, DamageSource source, float amount) {
    var level = entity.level();
    if(level.getGameRules().getBoolean(YourModIdeasGameRules.INSOMNIA)) {
      if (entity instanceof Phantom && source.is(DamageTypes.ON_FIRE)) {
        entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, Integer.MAX_VALUE, 0, true, false));
        entity.setRemainingFireTicks(0);
        return EventResult.interruptFalse();
      }
    }
    return EventResult.pass();
  }
}
