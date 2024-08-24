package com.ssblur.obt;

import com.ssblur.obt.block.OBTBlocks;
import com.ssblur.obt.blockentity.OBTBlockEntities;
import com.ssblur.obt.data.OBTDataComponents;
import com.ssblur.obt.effect.OBTEffects;
import com.ssblur.obt.entity.OBTEntities;
import com.ssblur.obt.events.OBTEvents;
import com.ssblur.obt.items.OBTItems;
import com.ssblur.obt.recipes.OBTRecipes;
import net.minecraft.resources.ResourceLocation;

import java.util.Objects;

public class OBTMod {
  public static final String MOD_ID = "obt";

  public static void init() {
    OBTGameRules.init();
    OBTEvents.register();
    OBTBlocks.register();
    OBTItems.register();
    OBTRecipes.register();
    OBTEffects.register();
    OBTEntities.register();
    OBTBlockEntities.register();
    OBTDataComponents.register();
  }

  public static ResourceLocation location(String path) {
    return Objects.requireNonNull(ResourceLocation.tryBuild(MOD_ID, path));
  }
}
