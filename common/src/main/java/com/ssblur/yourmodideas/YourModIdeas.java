package com.ssblur.yourmodideas;

import com.google.common.base.Suppliers;
import com.ssblur.yourmodideas.events.EntitySpawnedEvent;
import dev.architectury.event.events.common.EntityEvent;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrarManager;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;

import java.util.function.Supplier;

public class YourModIdeas {
  public static final String MOD_ID = "yourmodideas";

  public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(MOD_ID, Registries.CREATIVE_MODE_TAB);
  public static final RegistrySupplier<CreativeModeTab> TAB = TABS.register("your_mod_ideas", () ->
      CreativeTabRegistry.create(Component.translatable("itemGroup." + MOD_ID + ".your_mod_ideas"),
          () -> new ItemStack(Items.SKELETON_SKULL)));
  
  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registries.ITEM);
//  public static final RegistrySupplier<Item> EXAMPLE_ITEM = ITEMS.register("example_item", () ->
//      new Item(new Item.Properties().arch$tab(YourModIdeas.EXAMPLE_TAB)));

  public static void init() {
    YourModIdeasGameRules.init();

    EntityEvent.ADD.register(new EntitySpawnedEvent());

    TABS.register();
    ITEMS.register();
  }
}
