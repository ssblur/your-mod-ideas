package com.ssblur.obt.fabric;

import com.ssblur.obt.OBTExpectPlatform;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class OBTExpectPlatformImpl {
  /**
   * This is our actual method to {@link OBTExpectPlatform#getConfigDirectory()}.
   */
  public static Path getConfigDirectory() {
    return FabricLoader.getInstance().getConfigDir();
  }
}
