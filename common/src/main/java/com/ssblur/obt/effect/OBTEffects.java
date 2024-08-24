package com.ssblur.obt.effect;

import com.ssblur.obt.OBTMod;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import org.jetbrains.annotations.Nullable;

public class OBTEffects {
  public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(OBTMod.MOD_ID, Registries.MOB_EFFECT);
  public static final RegistrySupplier<MobEffect> MAYO = EFFECTS.register("mayo", MayoStatusEffect::new);

  public static void register() {
    EFFECTS.register();
  }

  @Nullable
  public static Holder<MobEffect> get(RegistrySupplier<MobEffect> effect) {
    return EFFECTS.getRegistrar().getHolder(effect.getId());
  }
}
