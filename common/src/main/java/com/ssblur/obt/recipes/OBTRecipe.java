package com.ssblur.obt.recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ssblur.obt.OBTGameRules;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

@SuppressWarnings("unused")
public class OBTRecipe extends CustomRecipe {
  public String rule;
  public ShapedRecipe recipe;

  public OBTRecipe(String rule, Recipe<?> recipe) {
    super(CraftingBookCategory.MISC);

    this.rule = rule;
    if(recipe instanceof ShapedRecipe shapedRecipe)
      this.recipe = shapedRecipe;
  }

  @Override
  public boolean matches(CraftingInput container, Level level) {
    if(OBTGameRules.getValue(level, rule))
      return recipe.matches(container, level);
    return false;
  }

  @Override
  public ItemStack assemble(CraftingInput container, HolderLookup.Provider registryAccess) {
    return recipe.assemble(container, registryAccess);
  }

  @Override
  public boolean canCraftInDimensions(int i, int j) {
    return recipe.canCraftInDimensions(i, j);
  }

  @Override
  public RecipeSerializer<?> getSerializer() {
    return OBTRecipes.RULE_RECIPE.get();
  }



  public static class Serializer implements RecipeSerializer<OBTRecipe> {
    public static final MapCodec<OBTRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
      Codec.STRING.fieldOf("rule").forGetter(data -> data.rule),
      ShapedRecipe.CODEC.fieldOf("recipe").forGetter(data -> data.recipe)
    ).apply(instance, OBTRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, OBTRecipe> STREAM_CODEC =  StreamCodec.composite(
      ByteBufCodecs.STRING_UTF8,
      recipe -> recipe.rule,
      ShapedRecipe.Serializer.STREAM_CODEC,
      recipe -> recipe.recipe,
      OBTRecipe::new
    );

    static final RecipeSerializer<ShapedRecipe> SHAPED_RECIPE = ShapedRecipe.Serializer.SHAPED_RECIPE;
    public Serializer() {}

    @Override
    public MapCodec<OBTRecipe> codec() {
      return CODEC;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, OBTRecipe> streamCodec() {
      return STREAM_CODEC;
    }

  }
}
