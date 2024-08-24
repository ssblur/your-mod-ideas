package com.ssblur.obt.block;

import com.ssblur.obt.blockentity.OverworldPortalBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEventListener;
import org.jetbrains.annotations.Nullable;

public class OverworldPortalBlock extends Block implements EntityBlock {
  public OverworldPortalBlock(Properties properties) {
    super(properties);
  }

  @Nullable
  @Override
  public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
    return new OverworldPortalBlockEntity(blockPos, blockState);
  }

  @Nullable
  @Override
  public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
    return OverworldPortalBlockEntity::tick;
  }

  @Nullable
  @Override
  public <T extends BlockEntity> GameEventListener getListener(ServerLevel serverLevel, T blockEntity) {
    return EntityBlock.super.getListener(serverLevel, blockEntity);
  }

  @Override
  public void neighborChanged(BlockState blockState, Level level, BlockPos blockPos, Block block, BlockPos blockPos2, boolean bl) {
    super.neighborChanged(blockState, level, blockPos, block, blockPos2, bl);
    if(
      !level.getBlockState(blockPos.north()).is(Blocks.STONE)
      && !level.getBlockState(blockPos.north()).is(OBTBlocks.OVERWORLD_PORTAL.get())
    )
      level.setBlockAndUpdate(blockPos, Blocks.AIR.defaultBlockState());
    else if(
      !level.getBlockState(blockPos.south()).is(Blocks.STONE)
        && !level.getBlockState(blockPos.south()).is(OBTBlocks.OVERWORLD_PORTAL.get())
    )
      level.setBlockAndUpdate(blockPos, Blocks.AIR.defaultBlockState());
    else if(
      !level.getBlockState(blockPos.east()).is(Blocks.STONE)
        && !level.getBlockState(blockPos.east()).is(OBTBlocks.OVERWORLD_PORTAL.get())
    )
      level.setBlockAndUpdate(blockPos, Blocks.AIR.defaultBlockState());
    else if(
      !level.getBlockState(blockPos.west()).is(Blocks.STONE)
        && !level.getBlockState(blockPos.west()).is(OBTBlocks.OVERWORLD_PORTAL.get())
    )
      level.setBlockAndUpdate(blockPos, Blocks.AIR.defaultBlockState());
  }
}
