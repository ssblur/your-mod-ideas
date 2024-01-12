package com.ssblur.yourmodideas.events;

import com.ssblur.yourmodideas.YourModIdeasGameRules;
import com.ssblur.yourmodideas.events.network.SyncedRules;
import dev.architectury.event.events.common.PlayerEvent;
import net.minecraft.server.level.ServerPlayer;

public class PlayerJoinedEvent implements PlayerEvent.PlayerJoin {
  @Override
  public void join(ServerPlayer player) {
    for(var rule: YourModIdeasGameRules.syncedRules) {
      SyncedRules.send(player, rule.getId(), player.server.overworld().getGameRules().getBoolean(rule));
    }
  }
}
