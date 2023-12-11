package com.ssblur.yourmodideas.events;

import com.ssblur.yourmodideas.YourModIdeasGameRules;
import dev.architectury.event.events.common.TickEvent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;

public class ServerTickEvent implements TickEvent.ServerLevelTick {
  @Override
  public void tick(ServerLevel instance) {
    YourModIdeasGameRules.UNMENDING_ENCHANT_FLAG = instance.getGameRules().getBoolean(YourModIdeasGameRules.UNMENDING_ENCHANT);
  }
}
