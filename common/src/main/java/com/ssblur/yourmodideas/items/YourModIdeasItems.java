package com.ssblur.yourmodideas.items;

import com.ssblur.yourmodideas.YourModIdeas;
import com.ssblur.yourmodideas.block.YourModIdeasBlocks;
import dev.architectury.platform.Platform;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.item.ItemPropertiesRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.fabricmc.api.EnvType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
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
  public static final RegistrySupplier<Item> PLAYER_BOW = ITEMS.register("player_bow", () ->
    new PlayerBow(new Item.Properties().durability(50).arch$tab(TAB)));
  public static final RegistrySupplier<Item> MAYONNAISE = ITEMS.register("mayonnaise", () ->
    new MayonnaiseItem(new Item.Properties().arch$tab(TAB)));

  public static final RegistrySupplier<Item> FROGGY_CHAIR = ITEMS.register("froggy_chair", () ->
    new BlockItem(YourModIdeasBlocks.FROGGY_CHAIR.get(), new Item.Properties().arch$tab(TAB)));
  public static final RegistrySupplier<Item> EVIL_FROGGY_CHAIR = ITEMS.register("evil_froggy_chair", () ->
    new BlockItem(YourModIdeasBlocks.EVIL_FROGGY_CHAIR.get(), new Item.Properties().arch$tab(TAB)));
  public static final RegistrySupplier<Item> ERROR_BLOCK = ITEMS.register("error_block", () ->
    new BlockItem(YourModIdeasBlocks.ERROR_BLOCK.get(), new Item.Properties().arch$tab(TAB)));
  public static final RegistrySupplier<Item> MISSING_TEXTURE_BLOCK = ITEMS.register("missing_texture_block", () ->
    new BlockItem(YourModIdeasBlocks.MISSING_TEXTURE_BLOCK.get(), new Item.Properties().arch$tab(TAB)));

  public static final RegistrySupplier<Item> CHARCOAL_1 = ITEMS.register("charcoal_1", () ->
    new Item(new Item.Properties()));
  public static final RegistrySupplier<Item> CHARCOAL_2 = ITEMS.register("charcoal_2", () ->
    new Item(new Item.Properties()));
  public static final RegistrySupplier<Item> CHARCOAL_3 = ITEMS.register("charcoal_3", () ->
    new Item(new Item.Properties()));
  public static final RegistrySupplier<Item> CHARCOAL_4 = ITEMS.register("charcoal_4", () ->
    new Item(new Item.Properties()));
  public static final RegistrySupplier<Item> CHARCOAL_5 = ITEMS.register("charcoal_5", () ->
    new Item(new Item.Properties()));
  public static final RegistrySupplier<Item> CHARCOAL_6 = ITEMS.register("charcoal_6", () ->
    new Item(new Item.Properties()));
  public static final RegistrySupplier<Item> CHARCOAL_7 = ITEMS.register("charcoal_7", () ->
    new Item(new Item.Properties()));
  public static final RegistrySupplier<Item> CHARCOAL_8 = ITEMS.register("charcoal_8", () ->
    new Item(new Item.Properties()));
  public static final RegistrySupplier<Item> CHARCOAL_9 = ITEMS.register("charcoal_9", () ->
    new Item(new Item.Properties()));

  public static void register() {
    TABS.register();
    ITEMS.register();

    YourModIdeasInstruments.register();
    if(Platform.getEnv() == EnvType.CLIENT)
      registerClient();
  }

  public static void registerClient() {
    ItemPropertiesRegistry.register(PLAYER_BOW.get(), new ResourceLocation("pull"), (itemStack, clientWorld, livingEntity, i) -> {
      if (livingEntity == null) {
        return 0.0F;
      }
      return livingEntity.getMainHandItem() != itemStack ? 0.0F : (itemStack.getUseDuration() - livingEntity.getUseItemRemainingTicks()) / 20.0F;
    });
    ItemPropertiesRegistry.register(PLAYER_BOW.get(), new ResourceLocation("pulling"), (itemStack, clientWorld, livingEntity, i) -> {
      if (livingEntity == null) {
        return 0.0F;
      }
      return livingEntity.isUsingItem() && livingEntity.getMainHandItem() == itemStack ? 1.0F : 0.0F;
    });
  }
}
