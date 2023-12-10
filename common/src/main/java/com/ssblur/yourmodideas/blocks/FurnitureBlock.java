package com.ssblur.yourmodideas.blocks;

import dev.architectury.platform.Platform;
import dev.architectury.registry.client.rendering.RenderTypeRegistry;
import net.fabricmc.api.EnvType;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public class FurnitureBlock extends HorizontalDirectionalBlock {
  public FurnitureBlock(Properties properties) {
    super(properties.noOcclusion());
    this.registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH));

    if(Platform.getEnv() == EnvType.CLIENT && !Platform.isForge())
      RenderTypeRegistry.register(RenderType.cutout(), this);
  }

  public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
    return this.defaultBlockState().setValue(FACING, blockPlaceContext.getHorizontalDirection().getOpposite());
  }

  @Override
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
    builder.add(FACING);
  }
}
