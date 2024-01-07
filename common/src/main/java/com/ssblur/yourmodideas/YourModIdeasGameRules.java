package com.ssblur.yourmodideas;

import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.GameRules.*;
import net.minecraft.world.level.Level;

public class YourModIdeasGameRules {
  // Features from the second video
  public static Key<BooleanValue> EVIL_FOXES;
  public static Key<BooleanValue> KILLER_SQUATS;
  public static Key<BooleanValue> INSOMNIA;
  public static Key<BooleanValue> MILK_EVERYTHING;
  public static Key<BooleanValue> UNMENDING_ENCHANT;

  // Features from the third video
  public static Key<BooleanValue> BUCKET_SAND;
  public static Key<BooleanValue> NO_MILKABLE;
  public static Key<BooleanValue> HELL_MODE;

  public static boolean UNMENDING_ENCHANT_FLAG;

  public static void init() {
    EVIL_FOXES = GameRules.register("yourModIdeas:evilFoxes", Category.SPAWNING, BooleanValue.create(true));
    KILLER_SQUATS = GameRules.register("yourModIdeas:killerSquats", Category.UPDATES, BooleanValue.create(false));
    INSOMNIA = GameRules.register("yourModIdeas:insomnia", Category.PLAYER, BooleanValue.create(false));
    MILK_EVERYTHING = GameRules.register("yourModIdeas:milkEverything", Category.MOBS, BooleanValue.create(false));
    UNMENDING_ENCHANT = GameRules.register("yourModIdeas:unmendingEnchant", Category.MISC, BooleanValue.create(false));
    BUCKET_SAND = GameRules.register("yourModIdeas:bucketSand", Category.MISC, BooleanValue.create(false));
    NO_MILKABLE = GameRules.register("yourModIdeas:milkNothing", Category.MOBS, BooleanValue.create(false));

    HELL_MODE = GameRules.register("yourModIdeas:hellMode", Category.MISC, BooleanValue.create(false));
  }

  public static boolean getValue(Level level, Key<BooleanValue> key) {
    return level.getGameRules().getBoolean(HELL_MODE) || level.getGameRules().getBoolean(key);
  }
}
