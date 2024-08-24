package com.ssblur.obt.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.UndeadHorseRenderer;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.level.Level;

import java.util.Random;

@Environment(value=EnvType.CLIENT)
public class NightmareRenderer extends UndeadHorseRenderer {
  ResourceLocation LOCATION = ResourceLocation.parse("textures/entity/horse/horse_skeleton.png");
  private static final Random RANDOM = new Random();
  public NightmareRenderer(EntityRendererProvider.Context context) {
    super(context, ModelLayers.SKELETON_HORSE);
  }

  public ResourceLocation getTextureLocation(AbstractHorse abstractHorse) {
    return LOCATION;
  }

  @Override
  public void render(AbstractHorse mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
    super.render(mob, f, g, poseStack, multiBufferSource, i);

    if(RANDOM.nextFloat() > 0.3f) return;
    double x = mob.getX() + RANDOM.nextDouble(0.7d) - 0.35d;
    double y = mob.getY() + RANDOM.nextDouble(0.7d) + 0.45d;
    double z = mob.getZ() + RANDOM.nextDouble(0.7d) - 0.35d;
    if(mob.level().dimension() == Level.NETHER) {
      mob.level().addParticle(ParticleTypes.FLAME, x, y, z, 0, 0.023d, 0);
    } else {
      mob.level().addParticle(ParticleTypes.SMOKE, x, y, z, 0, 0.023d, 0);
    }
  }
}
