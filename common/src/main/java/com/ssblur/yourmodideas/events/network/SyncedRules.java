package com.ssblur.yourmodideas.events.network;

import com.ssblur.yourmodideas.events.YourModIdeasEvents;
import dev.architectury.networking.NetworkManager;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;

import java.util.HashMap;
import java.util.Map;

public class SyncedRules {
  public static Map<String, Boolean> RULES = new HashMap<>();

  public static boolean getValue(GameRules.Key<GameRules.BooleanValue> flag) {
    return RULES.getOrDefault(flag.getId(), false);
  }

  public static void receive(FriendlyByteBuf buf, NetworkManager.PacketContext context) {
    RULES.put(buf.readUtf(), buf.readBoolean());
  }

  public static void send(ServerPlayer player, String key, boolean value) {
    FriendlyByteBuf out = new FriendlyByteBuf(Unpooled.buffer());
    out.writeUtf(key);
    out.writeBoolean(value);
    NetworkManager.sendToPlayer(player, YourModIdeasEvents.SYNC_MESSAGE, out);
  }

  public static void send(Iterable<ServerPlayer> players, String key, boolean value) {
    FriendlyByteBuf out = new FriendlyByteBuf(Unpooled.buffer());
    out.writeUtf(key);
    out.writeBoolean(value);
    NetworkManager.sendToPlayers(players, YourModIdeasEvents.SYNC_MESSAGE, out);
  }
}
