package com.ssblur.obt.events;

import com.ssblur.obt.OBTDamageSources;
import com.ssblur.obt.OBTGameRules;
import dev.architectury.event.events.common.ChatEvent;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

public class PlayerChatEvent implements ChatEvent.Decorate {
  @Override
  public void decorate(@Nullable ServerPlayer player, ChatEvent.ChatComponent component) {
    if(player == null) return;
    var level = player.level();
    if(OBTGameRules.getValue(level, OBTGameRules.BABABOOEY))
      if(component.get().getString().toLowerCase(Locale.ROOT).contains("bababooey"))
        player.hurt(OBTDamageSources.bababooeyDamage(player), Float.POSITIVE_INFINITY);
  }
}
