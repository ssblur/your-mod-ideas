package com.ssblur.yourmodideas;

import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.GameRules.*;

public class YourModIdeasGameRules {
  // Features from the second video
  public static Key<BooleanValue> EVIL_FOXES;

  public static void init() {
    EVIL_FOXES = GameRules.register("yourModIdeas:evilFoxes", Category.UPDATES, BooleanValue.create(true));
  }
}
