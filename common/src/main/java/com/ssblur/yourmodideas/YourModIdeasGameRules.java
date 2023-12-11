package com.ssblur.yourmodideas;

import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.GameRules.*;

public class YourModIdeasGameRules {
  // Features from the second video
  public static Key<BooleanValue> EVIL_FOXES;
  public static Key<BooleanValue> KILLER_SQUATS;
  public static Key<BooleanValue> INSOMNIA;
  public static Key<BooleanValue> MILK_EVERYTHING;

  public static void init() {
    EVIL_FOXES = GameRules.register("yourModIdeas:evilFoxes", Category.SPAWNING, BooleanValue.create(true));
    KILLER_SQUATS = GameRules.register("yourModIdeas:killerSquats", Category.UPDATES, BooleanValue.create(false));
    INSOMNIA = GameRules.register("yourModIdeas:insomnia", Category.PLAYER, BooleanValue.create(false));
    MILK_EVERYTHING = GameRules.register("yourModIdeas:milkEverything", Category.PLAYER, BooleanValue.create(false));
  }
}
