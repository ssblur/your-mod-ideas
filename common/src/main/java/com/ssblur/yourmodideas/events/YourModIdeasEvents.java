package com.ssblur.yourmodideas.events;

import dev.architectury.event.events.common.EntityEvent;
import dev.architectury.event.events.common.InteractionEvent;
import dev.architectury.event.events.common.TickEvent;

public class YourModIdeasEvents {
  public static void register() {
    EntityEvent.ADD.register(new EntitySpawnedEvent());
    TickEvent.PLAYER_PRE.register(new EntityTickEvent());
    TickEvent.SERVER_LEVEL_PRE.register(new ServerTickEvent());
    EntityEvent.LIVING_HURT.register(new EntityHurtEvent());
    InteractionEvent.RIGHT_CLICK_BLOCK.register(new BlockInteractEvent());
    InteractionEvent.INTERACT_ENTITY.register(new EntityInteractEvent());
  }
}
