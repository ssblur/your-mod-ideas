package com.ssblur.obt.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.Random;

public class OverworldPortalBlockEntity extends BlockEntity {
  public static final Random RANDOM = new Random();
  public OverworldPortalBlockEntity(BlockPos blockPos, BlockState blockState) {
    super(OBTBlockEntities.OVERWORLD_PORTAL.get(), blockPos, blockState);
  }

  @SuppressWarnings("unused")
  public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, T blockEntity) {
    for(var entity: level.getEntitiesOfClass(Entity.class, new AABB(pos))) {
      var position = entity.position();
      entity.setPos(position.add(RANDOM.nextInt(16) - 8, 400, RANDOM.nextInt(16) - 8));
      entity.setDeltaMovement(0, 0, 0);
      level.playSound(null, pos, SoundEvents.PORTAL_TRAVEL, SoundSource.BLOCKS);

      for (int i = 0; i < 4; ++i) {
        double x = position.x + RANDOM.nextDouble();
        double y = position.y + RANDOM.nextDouble();
        double z = position.z + RANDOM.nextDouble();
        double dx = ((double)RANDOM.nextFloat() - 0.5) * 0.5;
        double dy = ((double)RANDOM.nextFloat() - 0.5) * 0.5;
        double dz = ((double)RANDOM.nextFloat() - 0.5) * 0.5;
        level.addParticle(ParticleTypes.PORTAL, x, y, z, dx, dy, dz);
      }

      position = entity.position();
      for (int i = 0; i < 4; ++i) {
        double x = position.x + RANDOM.nextDouble();
        double y = position.y + RANDOM.nextDouble();
        double z = position.z + RANDOM.nextDouble();
        double dx = ((double)RANDOM.nextFloat() - 0.5) * 0.5;
        double dy = ((double)RANDOM.nextFloat() - 0.5) * 0.5;
        double dz = ((double)RANDOM.nextFloat() - 0.5) * 0.5;
        level.addParticle(ParticleTypes.PORTAL, x, y, z, dx, dy, dz);
      }
      level.playSound(null, entity.blockPosition(), SoundEvents.PORTAL_TRAVEL, SoundSource.BLOCKS);
    }
  }
}
