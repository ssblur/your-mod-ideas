package com.ssblur.obt;

import com.ssblur.obt.events.network.SyncedRules;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.GameRules.*;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class OBTGameRules {
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
  public static Key<BooleanValue> FAST_WITHER_SKELETONS;
  public static Key<BooleanValue> BABABOOEY;
  public static Key<BooleanValue> SUN_BLINDNESS;
  public static Key<BooleanValue> INVENTORY_SHIFT;
  public static Key<BooleanValue> DEDICATED_SLOTS;
  public static Key<BooleanValue> TEDIOUS_DIAMONDS;
  public static Key<BooleanValue> TOOLS_SLOW_AS_THEY_BREAK;
  public static Key<BooleanValue> DAD;
  public static Key<BooleanValue> OVERWORLD_PORTAL;
  public static Key<BooleanValue> NIGHTMARE;


  public static boolean UNMENDING_ENCHANT_FLAG;
  public static boolean EAT_SAND_FLAG;
  public static boolean TOOLS_SLOW_AS_THEY_BREAK_FLAG;

  public static void init() {
    EVIL_FOXES = register("obt:evilFoxes");
    KILLER_SQUATS = register("obt:killerSquats");
    INSOMNIA = register("obt:insomnia");
    MILK_EVERYTHING = register("obt:milkEverything");
    UNMENDING_ENCHANT = register("obt:unmendingEnchant");
    BUCKET_SAND = register("obt:bucketSand");
    NO_MILKABLE = register("obt:milkNothing");
    TOOLTIPS = register("obt:tooltips", true);
    EAT_SAND = register("obt:eatSand", true);
    EMOTIONAL_DAMAGE = register("obt:emotionalDamage");
    FAST_WITHER_SKELETONS = register("obt:fastWitherSkeletons");
    BABABOOEY = register("obt:bababooey");
    SUN_BLINDNESS = register("obt:sunBlindness");
    INVENTORY_SHIFT = register("obt:inventoryShift");
    DEDICATED_SLOTS = register("obt:dedicatedSlots", true);
    TEDIOUS_DIAMONDS = register("obt:tediousDiamonds");
    TOOLS_SLOW_AS_THEY_BREAK = register("obt:toolsSlowAsTheyBreak");
    DAD = register("obt:dad");
    OVERWORLD_PORTAL = register("obt:overworldPortal");
    NIGHTMARE = register("obt:nightmare");

    HELL_MODE = register("obt:hellMode", true);
  }

  public static boolean getValue(@Nullable Level level, Key<BooleanValue> key) {
    if(level == null) return false;
    if(level.isClientSide)
      return SyncedRules.getValue(HELL_MODE) || SyncedRules.getValue(key);
    return level.getGameRules().getBoolean(HELL_MODE) || level.getGameRules().getBoolean(key);
  }

  private static final Map<String, Key<BooleanValue>> keyMap = new HashMap<>();
  public static boolean getValue(@Nullable Level level, String key) {
    if(level == null) return false;
    if(keyMap.containsKey(key))
      return getValue(level, keyMap.get(key));
    if(level.isClientSide && SyncedRules.getValue(HELL_MODE))
      return true;
    if(level.getGameRules().getBoolean(HELL_MODE))
      return true;

    var visitor = new GameRuleVisitor(key);
    GameRules.visitGameRuleTypes(visitor);
    if(visitor.key != null) {
      keyMap.put(key, visitor.key);
      return getValue(level, visitor.key);
    }
    throw new RuntimeException("Could not find gamerule \"%s\"".formatted(key));
  }

  public static class GameRuleVisitor implements GameRules.GameRuleTypeVisitor {
    String id;
    GameRules.Key<GameRules.BooleanValue> key;
    public GameRuleVisitor(String key) {
      this.id = key;
    }

    @Override
    public void visitBoolean(GameRules.Key<GameRules.BooleanValue> key, GameRules.Type<GameRules.BooleanValue> type) {
      if(key.toString().equals(this.id))
        this.key = key;
    }
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
