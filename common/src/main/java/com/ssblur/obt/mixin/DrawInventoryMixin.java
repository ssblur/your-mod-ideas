package com.ssblur.obt.mixin;

import com.ssblur.obt.OBTGameRules;
import com.ssblur.obt.OBTMod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(InventoryScreen.class)
public class DrawInventoryMixin {
  @Unique
  private static final ResourceLocation obt$menuResource = OBTMod.location("textures/gui/overlay.png");
  @Inject(
    method = "renderBg",
    at = @At("TAIL")
  )
  private void renderBg(GuiGraphics guiGraphics, float f, int i, int j, CallbackInfo ci) {
    if(Minecraft.getInstance().level != null)
      if(OBTGameRules.getValue(Minecraft.getInstance().level, OBTGameRules.DEDICATED_SLOTS)) {
        var self = (InventoryScreen) (Object) this;
        var left = (self.width - 176) / 2;
        var top = (self.height - 166) / 2;
        guiGraphics.blit(obt$menuResource, left, top, 0, 0, 176, 166);
      }
  }
}
