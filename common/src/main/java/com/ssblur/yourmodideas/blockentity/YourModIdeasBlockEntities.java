package com.ssblur.yourmodideas.blockentity;

import com.ssblur.yourmodideas.YourModIdeas;
import com.ssblur.yourmodideas.block.YourModIdeasBlocks;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class YourModIdeasBlockEntities {
  public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(YourModIdeas.MOD_ID, Registries.BLOCK_ENTITY_TYPE);

  public static final RegistrySupplier<BlockEntityType<OverworldPortalBlockEntity>> OVERWORLD_PORTAL = BLOCK_ENTITIES.register(
    "overworld_portal",
    () -> BlockEntityType.Builder.of(OverworldPortalBlockEntity::new, YourModIdeasBlocks.OVERWORLD_PORTAL.get()).build(null)
  );

  public static void register() {
    BLOCK_ENTITIES.register();
  }
}
