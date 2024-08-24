package com.ssblur.obt.mixin;

import com.ssblur.obt.OBTGameRules;
import com.ssblur.obt.block.OBTBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public class PortaltemMixin {
  @Inject(
    method = "tick",
    at = @At("TAIL")
  )
  private void tick(CallbackInfo info) {
    var self = (ItemEntity) (Object) this;
    var level = self.level();
    if(OBTGameRules.getValue(level, OBTGameRules.OVERWORLD_PORTAL)) {
      if(!self.getItem().is(Items.GRASS_BLOCK)) return;

      var pos = self.blockPosition();
      for(var blockPos: obt$CHECKLIST)
        if(obt$checkPortal(level, pos.offset(blockPos))) self.remove(Entity.RemovalReason.DISCARDED);
    }
  }

  @Unique
  private static final BlockPos[] obt$CHECKLIST = new BlockPos[]{
    new BlockPos(-1, 0, -1),
    new BlockPos(-2, 0, -1),
    new BlockPos(-1, 0, -2),
    new BlockPos(-2, 0, -2),
  };

  @Unique
  private static final BlockPos[] obt$FRAME = new BlockPos[]{
    new BlockPos(1, 0, 0),
    new BlockPos(2, 0, 0),
    new BlockPos(0, 0, 1),
    new BlockPos(0, 0, 2),
    new BlockPos(1, 0, 3),
    new BlockPos(2, 0, 3),
    new BlockPos(3, 0, 1),
    new BlockPos(3, 0, 2),
  };
  @Unique
  private static final BlockPos[] obt$CENTER = new BlockPos[]{
    new BlockPos(1, 0, 1),
    new BlockPos(1, 0, 2),
    new BlockPos(2, 0, 1),
    new BlockPos(2, 0, 2),
  };

  @Unique
  private boolean obt$checkPortal(Level level, BlockPos pos) {
    for(var blockPos: obt$FRAME)
      if(!level.getBlockState(pos.offset(blockPos)).is(Blocks.STONE))
        return false;
    for(var blockPos: obt$CENTER)
      if(!level.getBlockState(pos.offset(blockPos)).is(Blocks.AIR))
        return false;
    obt$createPortal(level, pos.offset(1, 0, 1));
    return true;
  }

  @Unique
  private void obt$createPortal(Level level, BlockPos pos) {
    var blockState = OBTBlocks.OVERWORLD_PORTAL.get().defaultBlockState();
    level.setBlock(pos, blockState, 0);
    level.setBlock(pos.offset(1, 0, 0), blockState, 0);
    level.setBlock(pos.offset(1, 0, 1), blockState, 0);
    level.setBlockAndUpdate(pos.offset(0, 0, 1), blockState);
    level.sendBlockUpdated(pos.offset(0, 0, 0), Blocks.AIR.defaultBlockState(), blockState, 4);
    level.sendBlockUpdated(pos.offset(1, 0, 0), Blocks.AIR.defaultBlockState(), blockState, 4);
    level.sendBlockUpdated(pos.offset(1, 0, 1), Blocks.AIR.defaultBlockState(), blockState, 4);
  }
}
