package com.ssblur.obt.events;

import com.ssblur.obt.OBTGameRules;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.EntityEvent;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class EntityHurtEvent implements EntityEvent.LivingHurt {
  public static final Random RANDOM = new Random();
  public static Map<String, Map<String, Long>> timers = new HashMap<>();
  @Override
  public EventResult hurt(LivingEntity entity, DamageSource source, float amount) {
    var level = entity.level();
    if(OBTGameRules.getValue(level, OBTGameRules.INSOMNIA)) {
      if (entity instanceof Phantom && source.is(DamageTypes.ON_FIRE)) {
        entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, Integer.MAX_VALUE, 0, true, false));
        entity.setRemainingFireTicks(0);
        return EventResult.interruptFalse();
      }
    }

    if(OBTGameRules.getValue(level, OBTGameRules.EMOTIONAL_DAMAGE)) {
      if (entity instanceof Player player) {
        if(!timers.containsKey(player.getStringUUID()))
          timers.put(player.getStringUUID(), new HashMap<>());
        var timer = timers.get(player.getStringUUID());
        if(timer.getOrDefault(source.getMsgId(), level.getGameTime() - 1) > level.getGameTime())
          return EventResult.pass();
        timer.put(source.getMsgId(), level.getGameTime() + 100);

        if(I18n.exists("message.obt.emotional_damage_%s_0".formatted(source.getMsgId()))) {
          int total = 0;
          while(I18n.exists("message.obt.emotional_damage_%s_%d".formatted(source.getMsgId(), total)))
            total++;

          player.sendSystemMessage(Component.translatable("message.obt.emotional_damage_%s_%d".formatted(source.getMsgId(), RANDOM.nextInt(total))));
        }
      }
    }
    return EventResult.pass();
  }
}
