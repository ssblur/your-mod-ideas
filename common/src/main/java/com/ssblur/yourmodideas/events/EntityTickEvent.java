package com.ssblur.yourmodideas.events;

import com.ssblur.yourmodideas.YourModIdeasDamageSources;
import com.ssblur.yourmodideas.YourModIdeasGameRules;
import dev.architectury.event.events.common.TickEvent;

import java.util.HashMap;
import java.util.Random;

public class EntityTickEvent implements TickEvent.Player {
  HashMap<net.minecraft.world.entity.player.Player, Integer> timers = new HashMap<>();
  Random random = new Random();

  @Override
  public void tick(net.minecraft.world.entity.player.Player player) {
    var level = player.level();
    if(level.getGameRules().getBoolean(YourModIdeasGameRules.KILLER_SQUATS))
      if(player.isCrouching()) {
        timers.put(player, timers.getOrDefault(player, random.nextInt(120) + 20) - 1);
        if(timers.get(player) <= 0)
          player.hurt(YourModIdeasDamageSources.squatDamage(player), Float.POSITIVE_INFINITY);
      } else {
        timers.remove(player);
      }
  }
}
