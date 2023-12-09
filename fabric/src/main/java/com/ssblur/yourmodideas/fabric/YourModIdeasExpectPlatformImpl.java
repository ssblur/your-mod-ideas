package com.ssblur.yourmodideas.fabric;

import com.ssblur.yourmodideas.YourModIdeasExpectPlatform;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class YourModIdeasExpectPlatformImpl {
  /**
   * This is our actual method to {@link YourModIdeasExpectPlatform#getConfigDirectory()}.
   */
  public static Path getConfigDirectory() {
    return FabricLoader.getInstance().getConfigDir();
  }
}
