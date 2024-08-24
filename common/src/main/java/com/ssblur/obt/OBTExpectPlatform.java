package com.ssblur.obt;

import dev.architectury.injectables.annotations.ExpectPlatform;

import java.nio.file.Path;

public class OBTExpectPlatform {
  @ExpectPlatform
  public static Path getConfigDirectory() {
    throw new AssertionError();
  }
}
