package com.ssblur.yourmodideas.effect;

import com.ssblur.yourmodideas.YourModIdeas;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;

public class YourModIdeasEffects {
  public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(YourModIdeas.MOD_ID, Registries.MOB_EFFECT);
  public static final RegistrySupplier<MobEffect> MAYO = EFFECTS.register("mayo", MayoStatusEffect::new);

  public static void register() {
    EFFECTS.register();
  }
}
