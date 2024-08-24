package com.ssblur.obt.recipes;

import com.ssblur.obt.OBTMod;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class OBTRecipes {
  public static final DeferredRegister<RecipeSerializer<?>> RECIPES = DeferredRegister.create(OBTMod.MOD_ID, Registries.RECIPE_SERIALIZER);

  public static final RegistrySupplier<RecipeSerializer<?>> RULE_RECIPE = RECIPES.register("rule_shaped",
    OBTRecipe.Serializer::new
  );

  public static void register() {
    RECIPES.register();
  }
}
