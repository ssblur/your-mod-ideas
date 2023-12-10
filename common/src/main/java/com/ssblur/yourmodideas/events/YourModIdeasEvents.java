package com.ssblur.yourmodideas.events;

import dev.architectury.event.events.common.EntityEvent;
import dev.architectury.event.events.common.TickEvent;

public class YourModIdeasEvents {
  public static void register() {
    EntityEvent.ADD.register(new EntitySpawnedEvent());
    TickEvent.PLAYER_PRE.register(new EntityTickEvent());
  }
}
