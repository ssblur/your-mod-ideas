package com.ssblur.obt.events.network;

import com.ssblur.obt.events.OBTEvents;
import dev.architectury.networking.NetworkManager;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameRules;

import java.util.HashMap;
import java.util.Map;

public class SyncedRules implements OBTNetworkInterface<SyncedRules.Payload>{
  public static Map<String, Boolean> RULES = new HashMap<>();

  public static boolean getValue(GameRules.Key<GameRules.BooleanValue> flag) {
    return RULES.getOrDefault(flag.getId(), false);
  }

  public static void send(ServerPlayer player, String key, boolean value) {
    NetworkManager.sendToPlayer(player, new Payload(key, value));
  }

  public static void send(Iterable<ServerPlayer> players, String key, boolean value) {
    NetworkManager.sendToPlayers(players, new Payload(key, value));
  }

  @Override
  public CustomPacketPayload.Type<Payload> type() {
    return Payload.TYPE;
  }

  @Override
  public StreamCodec<RegistryFriendlyByteBuf, Payload> streamCodec() {
    return Payload.STREAM_CODEC;
  }

  @Override
  public NetworkManager.Side side() {
    return NetworkManager.Side.S2C;
  }

  @Override
  public void receive(Payload value, NetworkManager.PacketContext context) {
    RULES.put(value.key, value.value);
  }

  public record Payload(String key, boolean value) implements CustomPacketPayload {
    public static final Type<Payload> TYPE = new Type<>(OBTEvents.SYNC_MESSAGE);
    public static final StreamCodec<RegistryFriendlyByteBuf, Payload> STREAM_CODEC = StreamCodec.composite(
      ByteBufCodecs.STRING_UTF8,
      Payload::key,
      ByteBufCodecs.BOOL,
      Payload::value,
      Payload::new
    );

    @Override
    public Type<Payload> type() {
      return TYPE;
    }
  }
}
