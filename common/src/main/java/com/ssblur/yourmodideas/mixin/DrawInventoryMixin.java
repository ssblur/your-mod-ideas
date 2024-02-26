package com.ssblur.yourmodideas.mixin;

import com.ssblur.yourmodideas.YourModIdeas;
import com.ssblur.yourmodideas.YourModIdeasGameRules;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(InventoryScreen.class)
public class DrawInventoryMixin {
  private static final ResourceLocation menuResource = new ResourceLocation(YourModIdeas.MOD_ID, "textures/gui/overlay.png");
  @Inject(
    method = "renderBg",
    at = @At("TAIL")
  )
  private void renderBg(GuiGraphics guiGraphics, float f, int i, int j, CallbackInfo ci) {
    if(YourModIdeasGameRules.getValue(Minecraft.getInstance().level, YourModIdeasGameRules.DEDICATED_SLOTS)) {
      var self = (InventoryScreen) (Object) this;
      var left = (self.width - 176) / 2;
      var top = (self.height - 166) / 2;
      guiGraphics.blit(menuResource, left, top, 0, 0, 176, 166);
    }
  }
}
