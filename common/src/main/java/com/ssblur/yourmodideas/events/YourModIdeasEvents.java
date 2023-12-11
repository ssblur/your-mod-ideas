package com.ssblur.yourmodideas.events;

import dev.architectury.event.events.common.EntityEvent;
import dev.architectury.event.events.common.InteractionEvent;
import dev.architectury.event.events.common.TickEvent;
import net.minecraft.world.entity.Interaction;

public class YourModIdeasEvents {
  public static void register() {
    EntityEvent.ADD.register(new EntitySpawnedEvent());
    TickEvent.PLAYER_PRE.register(new EntityTickEvent());
    EntityEvent.LIVING_HURT.register(new EntityHurtEvent());
    InteractionEvent.RIGHT_CLICK_BLOCK.register(new InteractEvent());
  }
}
