package com.ssblur.obt.neoforge;

import net.neoforged.fml.loading.FMLPaths;

import java.nio.file.Path;

@SuppressWarnings("unused")
public class OBTExpectPlatformImpl {
  public static Path getConfigDirectory() {
        return FMLPaths.CONFIGDIR.get().resolve("obt.json");
    }
}
