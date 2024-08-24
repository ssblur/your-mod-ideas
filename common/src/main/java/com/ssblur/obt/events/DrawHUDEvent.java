package com.ssblur.obt.events;

import com.ssblur.obt.OBTGameRules;
import com.ssblur.obt.OBTMod;
import dev.architectury.event.events.client.ClientGuiEvent;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

public class DrawHUDEvent implements ClientGuiEvent.RenderHud {
  private static final ResourceLocation menuResource = OBTMod.location("textures/gui/hotbar_overlay.png");

  @Override
  public void renderHud(GuiGraphics graphics, DeltaTracker deltaTracker) {
    if(Minecraft.getInstance().level != null)
      if(OBTGameRules.getValue(Minecraft.getInstance().level, OBTGameRules.DEDICATED_SLOTS)) {
        int sw = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        int sh = Minecraft.getInstance().getWindow().getGuiScaledHeight();
        graphics.blit(menuResource, sw / 2 - 91, sh - 22, 0, 0, 182, 22);
      }
  }
}
