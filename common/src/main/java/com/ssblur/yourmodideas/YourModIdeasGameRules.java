package com.ssblur.yourmodideas;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.ssblur.yourmodideas.events.network.SyncedRules;
import net.minecraft.network.protocol.game.ClientboundSetTimePacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.GameRules.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class YourModIdeasGameRules {
  public static List<Key<BooleanValue>> syncedRules = new ArrayList<>();

  // Features from the second video
  public static Key<BooleanValue> EVIL_FOXES;
  public static Key<BooleanValue> KILLER_SQUATS;
  public static Key<BooleanValue> INSOMNIA;
  public static Key<BooleanValue> MILK_EVERYTHING;
  public static Key<BooleanValue> UNMENDING_ENCHANT;

  // Features from the third video
  public static Key<BooleanValue> BUCKET_SAND;
  public static Key<BooleanValue> NO_MILKABLE;
  public static Key<BooleanValue> TOOLTIPS;
  public static Key<BooleanValue> HELL_MODE;

  // Features from the fourth video
  public static Key<BooleanValue> EAT_SAND;
  public static Key<BooleanValue> EMOTIONAL_DAMAGE;


  public static boolean UNMENDING_ENCHANT_FLAG;
  public static boolean EAT_SAND_FLAG;

  public static void init() {
    EVIL_FOXES = register("yourModIdeas:evilFoxes");
    KILLER_SQUATS = register("yourModIdeas:killerSquats");
    INSOMNIA = register("yourModIdeas:insomnia");
    MILK_EVERYTHING = register("yourModIdeas:milkEverything");
    UNMENDING_ENCHANT = register("yourModIdeas:unmendingEnchant");
    BUCKET_SAND = register("yourModIdeas:bucketSand");
    NO_MILKABLE = register("yourModIdeas:milkNothing");
    TOOLTIPS = register("yourModIdeas:tooltips", true);
    EAT_SAND = register("yourModIdeas:eatSand", true);
    EMOTIONAL_DAMAGE = register("yourModIdeas:emotionalDamage");

    HELL_MODE = register("yourModIdeas:hellMode", true);
  }

  public static boolean getValue(Level level, Key<BooleanValue> key) {
    if(level == null) return false;
    if(level.isClientSide)
      return SyncedRules.getValue(HELL_MODE) || SyncedRules.getValue(key);
    return level.getGameRules().getBoolean(HELL_MODE) || level.getGameRules().getBoolean(key);
  }

  private static Key<BooleanValue> register(String key) {
    return register(key, false);
  }

  private static Key<BooleanValue> register(String key, boolean synced) {
    if(synced) {
      var rule = GameRules.register(key, Category.MISC, BooleanValue.create(false, syncHelper(key)));
      syncedRules.add(rule);
      return rule;
    }
    return GameRules.register(key, Category.MISC, BooleanValue.create(false));
  }

  private static BiConsumer<MinecraftServer, BooleanValue> syncHelper(String key) {
    return (server, value) -> SyncedRules.send(server.getPlayerList().getPlayers(), key, value.get());
  }
}
