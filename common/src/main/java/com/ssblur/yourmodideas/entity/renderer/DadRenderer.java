package com.ssblur.yourmodideas.entity.renderer;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.math.Axis;
import com.ssblur.yourmodideas.YourModIdeas;
import com.ssblur.yourmodideas.entity.DadEntity;
import net.minecraft.client.particle.DustParticle;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.resources.ResourceLocation;
import org.joml.Quaternionf;

public class DadRenderer extends EntityRenderer<DadEntity> {
  public static final ResourceLocation TEXTURE = new ResourceLocation(YourModIdeas.MOD_ID, "textures/entity/dad.png");

  public static final RenderType TEXTURE_LAYER =
    RenderType
      .create(
        YourModIdeas.MOD_ID + ":dad",
        DefaultVertexFormat.POSITION_COLOR_TEX_LIGHTMAP,
        VertexFormat.Mode.QUADS,
        256,
        false,
        false,
        RenderType.CompositeState.builder().setTextureState(new RenderStateShard.TextureStateShard(TEXTURE, false, false))
          .setCullState(new RenderStateShard.CullStateShard(false))
          .setLightmapState(new RenderStateShard.LightmapStateShard(true))
          .setShaderState(RenderStateShard.POSITION_COLOR_TEX_SHADER)
          .createCompositeState(true)
      );

  public DadRenderer(EntityRendererProvider.Context context) {
    super(context);
  }

  @Override
  public ResourceLocation getTextureLocation(DadEntity entity) {
    return TEXTURE;
  }

  @Override
  public void render(DadEntity entity, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
    super.render(entity, f, g, poseStack, multiBufferSource, i);

    float yl = 0f;
    float yh = 1.5f;
    float zl = 0f;
    float zr = 0f;
    float xl = -0.75f;
    float xr = 0.75f;

    var pos = entity.position();
    poseStack.pushPose();

    var scale = entity.getScale();
    poseStack.scale(scale, scale, scale);
    poseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
    poseStack.mulPose(Axis.YP.rotationDegrees(180.0f));

    var pose = poseStack.last().pose();
    var buffer = multiBufferSource.getBuffer(TEXTURE_LAYER);

    buffer.vertex(pose, xl, yl, zl).color(1f, 1f, 1f, 1f).uv(1, 1).uv2(i).endVertex();
    buffer.vertex(pose, xr, yl, zr).color(1f, 1f, 1f, 1f).uv(0, 1).uv2(i).endVertex();
    buffer.vertex(pose, xr, yh, zr).color(1f, 1f, 1f, 1f).uv(0, 0).uv2(i).endVertex();
    buffer.vertex(pose, xl, yh, zl).color(1f, 1f, 1f, 1f).uv(1, 0).uv2(i).endVertex();

    poseStack.popPose();
  }
}
