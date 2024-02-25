package com.ssblur.yourmodideas;

import com.google.common.base.Suppliers;
import com.ssblur.yourmodideas.blocks.YourModIdeasBlocks;
import com.ssblur.yourmodideas.enchantments.YourModIdeasEnchantments;
import com.ssblur.yourmodideas.events.EntitySpawnedEvent;
import com.ssblur.yourmodideas.events.EntityTickEvent;
import com.ssblur.yourmodideas.events.YourModIdeasEvents;
import com.ssblur.yourmodideas.items.YourModIdeasItems;
import dev.architectury.event.events.common.EntityEvent;
import dev.architectury.event.events.common.TickEvent;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrarManager;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.item.*;
import net.minecraft.world.level.GameRules;

import java.util.function.Supplier;

public class YourModIdeas {
  public static final String MOD_ID = "yourmodideas";

  public static void init() {
    YourModIdeasGameRules.init();
    YourModIdeasEvents.register();
    YourModIdeasBlocks.register();
    YourModIdeasItems.register();
    YourModIdeasEnchantments.register();
  }
}
