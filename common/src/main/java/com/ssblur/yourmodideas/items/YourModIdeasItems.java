package com.ssblur.yourmodideas.items;

import com.ssblur.yourmodideas.YourModIdeas;
import com.ssblur.yourmodideas.blocks.YourModIdeasBlocks;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;

public class YourModIdeasItems {
  public static final String MOD_ID = YourModIdeas.MOD_ID;

  public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(MOD_ID, Registries.CREATIVE_MODE_TAB);
  public static final RegistrySupplier<CreativeModeTab> TAB = TABS.register("your_mod_ideas", () ->
    CreativeTabRegistry.create(Component.translatable("itemGroup." + MOD_ID + ".your_mod_ideas"),
      () -> new ItemStack(Items.SKELETON_SKULL)));

  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registries.ITEM);
  public static final RegistrySupplier<Item> BUCKETED_BLOCK = ITEMS.register("bucketed_block", () ->
    new BucketedBlock(new Item.Properties()));
  public static final RegistrySupplier<Item> FROGGY_CHAIR = ITEMS.register("froggy_chair", () ->
    new BlockItem(YourModIdeasBlocks.FROGGY_CHAIR.get(), new Item.Properties().arch$tab(TAB)));
  public static final RegistrySupplier<Item> EVIL_FROGGY_CHAIR = ITEMS.register("evil_froggy_chair", () ->
    new BlockItem(YourModIdeasBlocks.EVIL_FROGGY_CHAIR.get(), new Item.Properties().arch$tab(TAB)));
  public static final RegistrySupplier<Item> ERROR_BLOCK = ITEMS.register("error_block", () ->
    new BlockItem(YourModIdeasBlocks.ERROR_BLOCK.get(), new Item.Properties().arch$tab(TAB)));
  public static final RegistrySupplier<Item> MISSING_TEXTURE_BLOCK = ITEMS.register("missing_texture_block", () ->
    new BlockItem(YourModIdeasBlocks.MISSING_TEXTURE_BLOCK.get(), new Item.Properties().arch$tab(TAB)));
  public static void register() {
    TABS.register();
    ITEMS.register();

    YourModIdeasInstruments.register();
  }
}
