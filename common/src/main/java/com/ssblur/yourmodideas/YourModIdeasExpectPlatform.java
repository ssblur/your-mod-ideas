package com.ssblur.yourmodideas;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.platform.Platform;

import java.nio.file.Path;

public class YourModIdeasExpectPlatform {
  @ExpectPlatform
  public static Path getConfigDirectory() {
    throw new AssertionError();
  }
}
