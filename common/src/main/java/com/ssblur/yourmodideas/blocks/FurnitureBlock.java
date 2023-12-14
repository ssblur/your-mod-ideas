package com.ssblur.yourmodideas.blocks;

import com.mojang.serialization.MapCodec;
import dev.architectury.platform.Platform;
import dev.architectury.registry.client.rendering.RenderTypeRegistry;
import net.fabricmc.api.EnvType;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public class FurnitureBlock extends HorizontalDirectionalBlock {
  public static final MapCodec<FurnitureBlock> CODEC = simpleCodec(FurnitureBlock::new);

  public FurnitureBlock(Properties properties) {
    super(properties.noOcclusion());
    this.registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH));

    if(Platform.getEnv() == EnvType.CLIENT && !Platform.isForgeLike())
      RenderTypeRegistry.register(RenderType.cutout(), this);
  }

  @Override
  protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
    return CODEC;
  }

  public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
    return this.defaultBlockState().setValue(FACING, blockPlaceContext.getHorizontalDirection().getOpposite());
  }

  @Override
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
    builder.add(FACING);
  }
}
