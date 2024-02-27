package com.ssblur.yourmodideas;

import com.ssblur.yourmodideas.blocks.YourModIdeasBlocks;
import com.ssblur.yourmodideas.effect.YourModIdeasEffects;
import com.ssblur.yourmodideas.enchantments.YourModIdeasEnchantments;
import com.ssblur.yourmodideas.events.YourModIdeasEvents;
import com.ssblur.yourmodideas.items.YourModIdeasItems;
import com.ssblur.yourmodideas.recipes.YourModIdeasRecipes;

public class YourModIdeas {
  public static final String MOD_ID = "yourmodideas";

  public static void init() {
    YourModIdeasGameRules.init();
    YourModIdeasEvents.register();
    YourModIdeasBlocks.register();
    YourModIdeasItems.register();
    YourModIdeasEnchantments.register();
    YourModIdeasRecipes.register();
    YourModIdeasEffects.register();
  }
}
