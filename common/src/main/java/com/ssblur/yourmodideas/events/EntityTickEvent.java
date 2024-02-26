package com.ssblur.yourmodideas.events;

import com.ssblur.yourmodideas.YourModIdeasDamageSources;
import com.ssblur.yourmodideas.YourModIdeasGameRules;
import dev.architectury.event.events.common.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.HashMap;
import java.util.Random;

public class EntityTickEvent implements TickEvent.Player {
  HashMap<net.minecraft.world.entity.player.Player, Integer> timers = new HashMap<>();
  Random random = new Random();

  @Override
  public void tick(net.minecraft.world.entity.player.Player player) {
    var level = player.level();
    if(YourModIdeasGameRules.getValue(level, YourModIdeasGameRules.KILLER_SQUATS))
      if(player.isCrouching()) {
        timers.put(player, timers.getOrDefault(player, random.nextInt(120) + 20) - 1);
        if(timers.get(player) <= 0)
          player.hurt(YourModIdeasDamageSources.squatDamage(player), Float.POSITIVE_INFINITY);
      } else {
        timers.remove(player);
      }

    if(YourModIdeasGameRules.getValue(level, YourModIdeasGameRules.SUN_BLINDNESS)) {
      if (level.dimensionType().bedWorks() && !level.isRaining()) {
        float angle = level.getSunAngle(0f) + (float) (Math.PI / 2);
        Vec3 eyeVec = player.getViewVector(0f);
        Vec3 sunVec = new Vec3(Mth.cos(angle), Mth.sin(angle), 0.0);
        double product = eyeVec.dot(sunVec);
        if (product > 0.98) {
          HitResult result = player.pick(512.0, Minecraft.getInstance().getFrameTime(), true);
          if (result.getType() == HitResult.Type.MISS)
            player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 200));
        }
      }
    }
  }
}
