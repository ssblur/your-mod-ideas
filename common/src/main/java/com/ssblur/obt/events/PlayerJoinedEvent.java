package com.ssblur.obt.events;

import com.ssblur.obt.OBTGameRules;
import com.ssblur.obt.events.network.SyncedRules;
import dev.architectury.event.events.common.PlayerEvent;
import net.minecraft.server.level.ServerPlayer;

public class PlayerJoinedEvent implements PlayerEvent.PlayerJoin {
  @Override
  public void join(ServerPlayer player) {
    for(var rule: OBTGameRules.syncedRules) {
      SyncedRules.send(player, rule.getId(), player.server.overworld().getGameRules().getBoolean(rule));
    }
  }
}
