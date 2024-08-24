package com.ssblur.obt.items;

import com.ssblur.obt.data.OBTDataComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

public class BucketedBlock extends Item {
  public BucketedBlock(Properties properties) {
    super(properties.stacksTo(1));
  }

  @Override
  public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
    if(level instanceof ServerLevel server) {
      var itemStack = player.getItemInHand(interactionHand);

      int distance = 0;
      Vec3 pos;
      BlockPos blockPos;
      do {
        distance++;
        pos = player.getEyePosition().add(player.getLookAngle().normalize().scale(distance));
        blockPos = new BlockPos((int) Math.round(pos.x()), (int) Math.round(pos.y()), (int) Math.round(pos.z()));
      } while(level.getBlockState(blockPos).getBlock() == Blocks.AIR && distance <= 5);
      distance--;
      pos = player.getEyePosition().add(player.getLookAngle().normalize().scale(distance));
      blockPos = new BlockPos((int) Math.round(pos.x()), (int) Math.round(pos.y()), (int) Math.round(pos.z()));

      FallingBlockEntity entity = EntityType.FALLING_BLOCK.create(
        server,
        null,
        blockPos,
        MobSpawnType.BUCKET,
        true,
        true
      );
      if(entity != null) {
        var tag = itemStack.get(OBTDataComponents.TAG);
        if(tag == null) return InteractionResultHolder.success(new ItemStack(Items.BUCKET));
        entity.load(tag);
        entity.setPos(pos);
        level.addFreshEntity(entity);
      }
    }

    return InteractionResultHolder.success(new ItemStack(Items.BUCKET));
  }
}
