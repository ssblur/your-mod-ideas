package com.ssblur.obt.block;

import com.ssblur.obt.OBTMod;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class OBTBlocks {
  public static final String MOD_ID = OBTMod.MOD_ID;

  public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(MOD_ID, Registries.BLOCK);

  public static final RegistrySupplier<Block> FROGGY_CHAIR = BLOCKS.register("froggy_chair", () ->
    new FurnitureBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
  public static final RegistrySupplier<Block> EVIL_FROGGY_CHAIR = BLOCKS.register("evil_froggy_chair", () ->
    new FurnitureBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
  public static final RegistrySupplier<Block> ERROR_BLOCK = BLOCKS.register("error_block", () ->
    new FurnitureBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
  public static final RegistrySupplier<Block> MISSING_TEXTURE_BLOCK = BLOCKS.register("missing_texture_block", () ->
    new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
  public static final RegistrySupplier<Block> OVERWORLD_PORTAL = BLOCKS.register("overworld_portal", () ->
    new OverworldPortalBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEDROCK).noCollission()));

  public static void register() {
    BLOCKS.register();
  }
}
