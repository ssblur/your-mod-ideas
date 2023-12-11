package com.ssblur.yourmodideas.enchantments;

import com.ssblur.yourmodideas.YourModIdeas;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.Enchantment;

public class YourModIdeasEnchantments {
  public static final String MOD_ID = YourModIdeas.MOD_ID;
  public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(MOD_ID, Registries.ENCHANTMENT);

  public static final RegistrySupplier<UnMendingEnchant> UNMENDING = ENCHANTMENTS.register("unmending", UnMendingEnchant::new);


  public static void register() {
    ENCHANTMENTS.register();
  }
}
