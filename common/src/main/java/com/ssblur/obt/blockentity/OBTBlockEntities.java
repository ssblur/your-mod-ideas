package com.ssblur.obt.blockentity;

import com.ssblur.obt.OBTMod;
import com.ssblur.obt.block.OBTBlocks;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class OBTBlockEntities {
  public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(OBTMod.MOD_ID, Registries.BLOCK_ENTITY_TYPE);

  @SuppressWarnings("DataFlowIssue")
  public static final RegistrySupplier<BlockEntityType<OverworldPortalBlockEntity>> OVERWORLD_PORTAL = BLOCK_ENTITIES.register(
    "overworld_portal",
    () -> BlockEntityType.Builder.of(OverworldPortalBlockEntity::new, OBTBlocks.OVERWORLD_PORTAL.get()).build(null)
  );

  public static void register() {
    BLOCK_ENTITIES.register();
  }
}
