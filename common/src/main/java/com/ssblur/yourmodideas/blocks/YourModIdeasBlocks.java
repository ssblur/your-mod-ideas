package com.ssblur.yourmodideas.blocks;

import com.ssblur.yourmodideas.YourModIdeas;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class YourModIdeasBlocks {
  public static final String MOD_ID = YourModIdeas.MOD_ID;

  public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(MOD_ID, Registries.BLOCK);

  public static final RegistrySupplier<Block> FROGGY_CHAIR = BLOCKS.register("froggy_chair", () ->
    new FurnitureBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_PLANKS)));

  public static void register() {
    BLOCKS.register();
  }
}
