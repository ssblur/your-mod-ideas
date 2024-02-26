package com.ssblur.yourmodideas.events;

import com.mojang.authlib.minecraft.client.MinecraftClient;
import com.ssblur.yourmodideas.YourModIdeas;
import com.ssblur.yourmodideas.YourModIdeasGameRules;
import dev.architectury.event.events.client.ClientGuiEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

public class DrawHUDEvent implements ClientGuiEvent.RenderHud {
  private static final ResourceLocation menuResource = new ResourceLocation(YourModIdeas.MOD_ID, "textures/gui/hotbar_overlay.png");

  @Override
  public void renderHud(GuiGraphics graphics, float tickDelta) {
    if(YourModIdeasGameRules.getValue(Minecraft.getInstance().level, YourModIdeasGameRules.DEDICATED_SLOTS)) {
      int sw = Minecraft.getInstance().getWindow().getGuiScaledWidth();
      int sh = Minecraft.getInstance().getWindow().getGuiScaledHeight();
      graphics.blit(menuResource, sw / 2 - 91, sh - 22, 0, 0, 182, 22);
    }
  }
}
