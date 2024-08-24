package com.ssblur.obt.events;

import com.ssblur.obt.OBTDamageSources;
import com.ssblur.obt.OBTGameRules;
import dev.architectury.event.events.common.TickEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
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
    if(OBTGameRules.getValue(level, OBTGameRules.KILLER_SQUATS))
      if(player.isCrouching()) {
        timers.put(player, timers.getOrDefault(player, random.nextInt(120) + 20) - 1);
        if(timers.get(player) <= 0)
          player.hurt(OBTDamageSources.squatDamage(player), Float.POSITIVE_INFINITY);
      } else {
        timers.remove(player);
      }

    if(OBTGameRules.getValue(level, OBTGameRules.SUN_BLINDNESS)) {
      if (level.dimension() == Level.OVERWORLD && !level.isRaining()) {
        Vec3 eyeVec = player.getViewVector(0f);
        float angle = level.getSunAngle(0f) + (float) (Math.PI / 2);
        Vec3 sunVec = new Vec3(Mth.cos(angle), Mth.sin(angle), 0.0);
        if (eyeVec.dot(sunVec) > 0.98) {
          HitResult result = player.pick(512.0, 0, true);
          if (result.getType() == HitResult.Type.MISS)
            player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 200));
        }
      }
    }

    if(OBTGameRules.getValue(level, OBTGameRules.INVENTORY_SHIFT)) {
      if(level.getGameTime() % 40 == 0) {
        var inventory = player.getInventory();
        ItemStack first = inventory.getItem(0);
        for (int i = 1; i < inventory.getContainerSize(); i++) {
          inventory.setItem(i - 1, inventory.getItem(i));
        }
        inventory.setItem(inventory.getContainerSize() - 1, first);
      }
    }

    if(OBTGameRules.getValue(level, OBTGameRules.DEDICATED_SLOTS)) {
      if(level.getGameTime() % 10 == 0) {
        var inventory = player.getInventory();
        for (int i = 0; i < inventory.getContainerSize(); i++) {
          var item = inventory.getItem(i);
          if(item.getItem() instanceof SwordItem && i != 0) {
            item.setDamageValue(item.getDamageValue() + 1);
            if (item.getDamageValue() >= item.getMaxDamage())
              inventory.setItem(i, ItemStack.EMPTY);
          } else if(item.getItem() instanceof AxeItem && i != 1) {
            item.setDamageValue(item.getDamageValue() + 1);
            if (item.getDamageValue() >= item.getMaxDamage())
              inventory.setItem(i, ItemStack.EMPTY);
          } else if(item.getItem() instanceof PickaxeItem && i != 2) {
            item.setDamageValue(item.getDamageValue() + 1);
            if (item.getDamageValue() >= item.getMaxDamage())
              inventory.setItem(i, ItemStack.EMPTY);
          } else if(item.getItem() instanceof ShovelItem && i != 3) {
            item.setDamageValue(item.getDamageValue() + 1);
            if (item.getDamageValue() >= item.getMaxDamage())
              inventory.setItem(i, ItemStack.EMPTY);
          }
        }
      }
    }
  }
}
