package com.ssblur.yourmodideas.events;

import com.ssblur.yourmodideas.YourModIdeasGameRules;
import dev.architectury.event.events.common.TickEvent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;

public class ServerTickEvent implements TickEvent.ServerLevelTick {
  @Override
  public void tick(ServerLevel instance) {
    YourModIdeasGameRules.UNMENDING_ENCHANT_FLAG = YourModIdeasGameRules.getValue(instance, YourModIdeasGameRules.UNMENDING_ENCHANT);
    YourModIdeasGameRules.EAT_SAND_FLAG = YourModIdeasGameRules.getValue(instance, YourModIdeasGameRules.EAT_SAND);
    YourModIdeasGameRules.TOOLS_SLOW_AS_THEY_BREAK_FLAG = YourModIdeasGameRules.getValue(instance, YourModIdeasGameRules.TOOLS_SLOW_AS_THEY_BREAK);
  }
}
