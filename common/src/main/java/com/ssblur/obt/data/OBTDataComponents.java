package com.ssblur.obt.data;

import com.ssblur.obt.OBTMod;
import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.codec.ByteBufCodecs;

import java.util.function.Consumer;

public class OBTDataComponents {
  public static final DeferredRegister<DataComponentType<?>> COMPONENTS = DeferredRegister.create(OBTMod.MOD_ID, Registries.DATA_COMPONENT_TYPE);

  public static final DataComponentType<CompoundTag> TAG = register("tag",
    builder -> builder.persistent(CompoundTag.CODEC).networkSynchronized(ByteBufCodecs.COMPOUND_TAG)
  );

  private static <T> DataComponentType<T> register(String name, Consumer<DataComponentType.Builder<T>> customizer) {
    var builder = DataComponentType.<T>builder();
    customizer.accept(builder);
    var componentType = builder.build();
    COMPONENTS.register(name, () -> componentType);
    return componentType;
  }

  public static void register() {
    COMPONENTS.register();
  }
}
