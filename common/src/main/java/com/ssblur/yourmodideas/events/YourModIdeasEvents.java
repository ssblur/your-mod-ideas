package com.ssblur.yourmodideas.events;

import com.ssblur.yourmodideas.YourModIdeas;
import com.ssblur.yourmodideas.events.network.SyncedRules;
import dev.architectury.event.events.client.ClientTooltipEvent;
import dev.architectury.event.events.common.*;
import dev.architectury.networking.NetworkManager;
import dev.architectury.platform.Platform;
import net.fabricmc.api.EnvType;
import net.minecraft.resources.ResourceLocation;

public class YourModIdeasEvents {
  public static final String MOD_ID = YourModIdeas.MOD_ID;
  public static final ResourceLocation SYNC_MESSAGE = new ResourceLocation(MOD_ID, "sync");

  public static void register() {
    EntityEvent.ADD.register(new EntitySpawnedEvent());
    TickEvent.PLAYER_PRE.register(new EntityTickEvent());
    TickEvent.SERVER_LEVEL_PRE.register(new ServerTickEvent());
    EntityEvent.LIVING_HURT.register(new EntityHurtEvent());
    InteractionEvent.RIGHT_CLICK_BLOCK.register(new BlockInteractEvent());
    InteractionEvent.INTERACT_ENTITY.register(new EntityInteractEvent());
    ClientTooltipEvent.ITEM.register(new AddTooltipEvent());
    PlayerEvent.PLAYER_JOIN.register(new PlayerJoinedEvent());
    ChatEvent.DECORATE.register(new PlayerChatEvent());

    if(Platform.getEnv() == EnvType.CLIENT) {
      NetworkManager.registerReceiver(NetworkManager.Side.S2C, SYNC_MESSAGE, SyncedRules::receive);
    }
  }
}
