package com.ssblur.yourmodideas.recipes;

import com.ssblur.yourmodideas.YourModIdeas;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class YourModIdeasRecipes {
  public static final DeferredRegister<RecipeSerializer<?>> RECIPES = DeferredRegister.create(YourModIdeas.MOD_ID, Registries.RECIPE_SERIALIZER);

  public static final RegistrySupplier<RecipeSerializer<?>> RULE_RECIPE = RECIPES.register("rule_shaped",
    YourModIdeasRecipe.Serializer::new
  );

  public static void register() {
    RECIPES.register();
  }
}
