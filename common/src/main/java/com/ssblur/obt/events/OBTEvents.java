package com.ssblur.obt.events;

import com.ssblur.obt.OBTMod;
import com.ssblur.obt.events.network.OBTNetworkInterface;
import com.ssblur.obt.events.network.SyncedRules;
import dev.architectury.event.events.client.ClientGuiEvent;
import dev.architectury.event.events.client.ClientTooltipEvent;
import dev.architectury.event.events.common.*;
import dev.architectury.networking.NetworkManager;
import dev.architectury.platform.Platform;
import net.fabricmc.api.EnvType;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public class OBTEvents {
  public static final String MOD_ID = OBTMod.MOD_ID;
  public static final ResourceLocation SYNC_MESSAGE = OBTMod.location("sync");

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

    register(new SyncedRules());

    if(Platform.getEnv() == EnvType.CLIENT) {
      ClientGuiEvent.RENDER_HUD.register(new DrawHUDEvent());
    }
  }

  public static <T extends CustomPacketPayload> void register(OBTNetworkInterface<T> networkInterface) {
    if(networkInterface.side() == NetworkManager.Side.C2S || Platform.getEnv() == EnvType.CLIENT)
      NetworkManager.registerReceiver(networkInterface.side(), networkInterface.type(), networkInterface.streamCodec(), networkInterface);

    if(networkInterface.side() == NetworkManager.Side.S2C && Platform.getEnv() == EnvType.SERVER)
      NetworkManager.registerS2CPayloadType(networkInterface.type(), networkInterface.streamCodec());
  }
}
