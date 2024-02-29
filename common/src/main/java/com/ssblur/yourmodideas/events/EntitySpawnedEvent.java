package com.ssblur.yourmodideas.events;

import com.ssblur.yourmodideas.YourModIdeasGameRules;
import com.ssblur.yourmodideas.entity.YourModIdeasEntities;
import com.ssblur.yourmodideas.goals.FoxStealItemsGoal;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.EntityEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;

import java.util.Random;

public class EntitySpawnedEvent implements EntityEvent.Add {
  static Random random = new Random();
  @Override
  public EventResult add(Entity entity, Level level) {
    if(entity instanceof Fox fox && random.nextFloat() < 0.1f)
      fox.goalSelector.addGoal(3, new FoxStealItemsGoal(fox));

    if(entity instanceof WitherSkeleton witherSkeleton && YourModIdeasGameRules.getValue(level, YourModIdeasGameRules.FAST_WITHER_SKELETONS))
      witherSkeleton.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, Integer.MAX_VALUE, 3));

    if(
      entity instanceof Zombie zombie
        && YourModIdeasGameRules.getValue(level, YourModIdeasGameRules.DAD)
        && random.nextFloat() < 0.2f
    ) {
      YourModIdeasEntities.DAD.get().spawn((ServerLevel) level, zombie.getOnPos(), MobSpawnType.NATURAL);

      zombie.remove(Entity.RemovalReason.DISCARDED);
    }
    return EventResult.pass();
  }
}
