package com.ssblur.yourmodideas.recipes;

import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ssblur.yourmodideas.YourModIdeasGameRules;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.commands.GameRuleCommand;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;

public class YourModIdeasRecipe extends CustomRecipe {
  String rule;
  ShapedRecipe recipe;

  public static Codec<YourModIdeasRecipe> CODEC = RecordCodecBuilder.create(instance -> instance.group(
    Codec.STRING.fieldOf("rule").forGetter(data -> data.rule),
    ShapedRecipe.CODEC.fieldOf("recipe").forGetter(data -> data.recipe)
  ).apply(instance, YourModIdeasRecipe::new));

  public YourModIdeasRecipe(String rule, Recipe<?> recipe) {
    super(CraftingBookCategory.MISC);

    this.rule = rule;
    if(recipe instanceof ShapedRecipe shapedRecipe)
      this.recipe = shapedRecipe;
  }

  @Override
  public boolean matches(CraftingContainer container, Level level) {
    if(YourModIdeasGameRules.getValue(level, rule))
      return recipe.matches(container, level);
    return false;
  }

  @Override
  public ItemStack assemble(CraftingContainer container, RegistryAccess registryAccess) {
    return recipe.assemble(container, registryAccess);
  }

  @Override
  public boolean canCraftInDimensions(int i, int j) {
    return recipe.canCraftInDimensions(i, j);
  }

  @Override
  public RecipeSerializer<?> getSerializer() {
    return YourModIdeasRecipes.RULE_RECIPE.get();
  }



  public static class Serializer implements RecipeSerializer<YourModIdeasRecipe> {
    static final RecipeSerializer<ShapedRecipe> SHAPED_RECIPE = ShapedRecipe.Serializer.SHAPED_RECIPE;
    public Serializer() {}

    @Override
    public Codec<YourModIdeasRecipe> codec() {
      return CODEC;
    }

    @Override
    public YourModIdeasRecipe fromNetwork(FriendlyByteBuf buf) {
      var rule = buf.readUtf();
      var recipe = ShapedRecipe.Serializer.SHAPED_RECIPE.fromNetwork(buf);
      return new YourModIdeasRecipe(rule, recipe);
    }

    public void toNetwork(FriendlyByteBuf buf, YourModIdeasRecipe craftingRecipe) {
      buf.writeUtf(craftingRecipe.rule);
      SHAPED_RECIPE.toNetwork(buf, craftingRecipe.recipe);
    }
  }
}
