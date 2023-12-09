package com.ssblur.yourmodideas.forge;

import com.ssblur.yourmodideas.YourModIdeasExpectPlatform;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class YourModIdeasExpectPlatformImpl {
  /**
   * This is our actual method to {@link YourModIdeasExpectPlatform#getConfigDirectory()}.
   */
  public static Path getConfigDirectory() {
    return FMLPaths.CONFIGDIR.get();
  }
}
