package com.ssblur.yourmodideas.fabric;

import com.ssblur.yourmodideas.YourModIdeas;
import net.fabricmc.api.ModInitializer;

public class YourModIdeasFabric implements ModInitializer {
  @Override
  public void onInitialize() {
    YourModIdeas.init();
  }
}
