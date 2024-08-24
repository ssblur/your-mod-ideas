package com.ssblur.obt.events.network;

import dev.architectury.networking.NetworkManager;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public interface OBTNetworkInterface<T extends CustomPacketPayload> extends NetworkManager.NetworkReceiver<T> {
  CustomPacketPayload.Type<T> type();
  StreamCodec<RegistryFriendlyByteBuf, T> streamCodec();
  NetworkManager.Side side();
}
