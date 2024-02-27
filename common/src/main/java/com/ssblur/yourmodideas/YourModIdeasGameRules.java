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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
  public static Key<BooleanValue> FAST_WITHER_SKELETONS;
  public static Key<BooleanValue> BABABOOEY;
  public static Key<BooleanValue> SUN_BLINDNESS;
  public static Key<BooleanValue> INVENTORY_SHIFT;
  public static Key<BooleanValue> DEDICATED_SLOTS;
  public static Key<BooleanValue> TEDIOUS_DIAMONDS;
  public static Key<BooleanValue> TOOLS_SLOW_AS_THEY_BREAK;


  public static boolean UNMENDING_ENCHANT_FLAG;
  public static boolean EAT_SAND_FLAG;
  public static boolean TOOLS_SLOW_AS_THEY_BREAK_FLAG;

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
    FAST_WITHER_SKELETONS = register("yourModIdeas:fastWitherSkeletons");
    BABABOOEY = register("yourModIdeas:bababooey");
    SUN_BLINDNESS = register("yourModIdeas:sunBlindness");
    INVENTORY_SHIFT = register("yourModIdeas:inventoryShift");
    DEDICATED_SLOTS = register("yourModIdeas:dedicatedSlots", true);
    TEDIOUS_DIAMONDS = register("yourModIdeas:tediousDiamonds");
    TOOLS_SLOW_AS_THEY_BREAK = register("yourModIdeas:toolsSlowAsTheyBreak");


    HELL_MODE = register("yourModIdeas:hellMode", true);
  }

  public static boolean getValue(Level level, Key<BooleanValue> key) {
    if(level == null) return false;
    if(level.isClientSide)
      return SyncedRules.getValue(HELL_MODE) || SyncedRules.getValue(key);
    return level.getGameRules().getBoolean(HELL_MODE) || level.getGameRules().getBoolean(key);
  }

  private static final Map<String, Key<BooleanValue>> keyMap = new HashMap<>();
  public static boolean getValue(Level level, String key) {
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
