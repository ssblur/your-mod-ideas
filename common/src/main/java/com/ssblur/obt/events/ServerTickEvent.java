package com.ssblur.obt.events;

import com.ssblur.obt.OBTGameRules;
import dev.architectury.event.events.common.TickEvent;
import net.minecraft.server.level.ServerLevel;

public class ServerTickEvent implements TickEvent.ServerLevelTick {
  @Override
  public void tick(ServerLevel instance) {
    OBTGameRules.UNMENDING_ENCHANT_FLAG = OBTGameRules.getValue(instance, OBTGameRules.UNMENDING_ENCHANT);
    OBTGameRules.EAT_SAND_FLAG = OBTGameRules.getValue(instance, OBTGameRules.EAT_SAND);
    OBTGameRules.TOOLS_SLOW_AS_THEY_BREAK_FLAG = OBTGameRules.getValue(instance, OBTGameRules.TOOLS_SLOW_AS_THEY_BREAK);
  }
}
