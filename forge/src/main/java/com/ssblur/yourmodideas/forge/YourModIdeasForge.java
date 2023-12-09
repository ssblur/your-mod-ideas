package com.ssblur.yourmodideas.forge;

import dev.architectury.platform.forge.EventBuses;
import com.ssblur.yourmodideas.YourModIdeas;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(YourModIdeas.MOD_ID)
public class YourModIdeasForge {
  public YourModIdeasForge() {
    // Submit our event bus to let architectury register our content on the right time
    EventBuses.registerModEventBus(YourModIdeas.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
    YourModIdeas.init();
  }
}
