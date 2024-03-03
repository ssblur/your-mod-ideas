package com.ssblur.yourmodideas.mixin;

import com.ssblur.yourmodideas.YourModIdeasGameRules;
import com.ssblur.yourmodideas.block.YourModIdeasBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public class PortaltemMixin {
  private static final BlockPos[] CHECKLIST = new BlockPos[]{
    new BlockPos(-1, 0, -1),
    new BlockPos(-2, 0, -1),
    new BlockPos(-1, 0, -2),
    new BlockPos(-2, 0, -2),
  };

  @Inject(
    method = "tick",
    at = @At("TAIL")
  )
  private void tick(CallbackInfo info) {
    var self = (ItemEntity) (Object) this;
    var level = self.level();
    if(YourModIdeasGameRules.getValue(level, YourModIdeasGameRules.OVERWORLD_PORTAL)) {
      if(!self.getItem().is(Items.GRASS_BLOCK)) return;

      var pos = self.blockPosition();
      for(var blockPos: CHECKLIST)
        if(checkPortal(level, pos.offset(blockPos))) self.remove(Entity.RemovalReason.DISCARDED);
    }
  }

  private static final BlockPos[] FRAME = new BlockPos[]{
    new BlockPos(1, 0, 0),
    new BlockPos(2, 0, 0),
    new BlockPos(0, 0, 1),
    new BlockPos(0, 0, 2),
    new BlockPos(1, 0, 3),
    new BlockPos(2, 0, 3),
    new BlockPos(3, 0, 1),
    new BlockPos(3, 0, 2),
  };
  private static final BlockPos[] CENTER = new BlockPos[]{
    new BlockPos(1, 0, 1),
    new BlockPos(1, 0, 2),
    new BlockPos(2, 0, 1),
    new BlockPos(2, 0, 2),
  };
  private boolean checkPortal(Level level, BlockPos pos) {
    for(var blockPos: FRAME)
      if(!level.getBlockState(pos.offset(blockPos)).is(Blocks.STONE))
        return false;
    for(var blockPos: CENTER)
      if(!level.getBlockState(pos.offset(blockPos)).is(Blocks.AIR))
        return false;
    createPortal(level, pos.offset(1, 0, 1));
    return true;
  }

  private void createPortal(Level level, BlockPos pos) {
    var blockState = YourModIdeasBlocks.OVERWORLD_PORTAL.get().defaultBlockState();
    level.setBlock(pos, blockState, 0);
    level.setBlock(pos.offset(1, 0, 0), blockState, 0);
    level.setBlock(pos.offset(1, 0, 1), blockState, 0);
    level.setBlockAndUpdate(pos.offset(0, 0, 1), blockState);
    level.sendBlockUpdated(pos.offset(0, 0, 0), Blocks.AIR.defaultBlockState(), blockState, 4);
    level.sendBlockUpdated(pos.offset(1, 0, 0), Blocks.AIR.defaultBlockState(), blockState, 4);
    level.sendBlockUpdated(pos.offset(1, 0, 1), Blocks.AIR.defaultBlockState(), blockState, 4);
  }
}
