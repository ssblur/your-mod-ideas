package com.ssblur.yourmodideas.events;

import com.ssblur.yourmodideas.YourModIdeasDamageSources;
import com.ssblur.yourmodideas.YourModIdeasGameRules;
import dev.architectury.event.events.common.ChatEvent;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

public class PlayerChatEvent implements ChatEvent.Decorate {
  @Override
  public void decorate(@Nullable ServerPlayer player, ChatEvent.ChatComponent component) {
    var level = player.level();
    if(YourModIdeasGameRules.getValue(level, YourModIdeasGameRules.BABABOOEY))
      if(component.get().getString().toLowerCase(Locale.ROOT).contains("bababooey"))
        player.hurt(YourModIdeasDamageSources.bababooeyDamage(player), Float.POSITIVE_INFINITY);
  }
}
