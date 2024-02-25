package com.ssblur.yourmodideas.mixin;

import com.ssblur.yourmodideas.YourModIdeasGameRules;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public abstract class IsEdibleMixin {
  private static final FoodProperties FOOD_PROPERTIES = new FoodProperties.Builder().alwaysEat().nutrition(1).saturationMod(0).build();

  @Inject(
    method = "isEdible",
    at = @At(value = "RETURN"),
    cancellable = true
  )
  private void isEdible(CallbackInfoReturnable<Boolean> info) {
    Item self = (Item) (Object) this;
    if (self instanceof BlockItem blockItem) {
      Block block = blockItem.getBlock();
      if (block == Blocks.SAND || block == Blocks.RED_SAND)
        info.setReturnValue(YourModIdeasGameRules.EAT_SAND_FLAG);
    }
  }

  @Inject(
    method = "getFoodProperties",
    at = @At(value = "RETURN"),
    cancellable = true
  )
  private void getFoodProperties(CallbackInfoReturnable<FoodProperties> info) {
    Item self = (Item) (Object) this;
    if (self instanceof BlockItem blockItem) {
      Block block = blockItem.getBlock();
      if (block == Blocks.SAND || block == Blocks.RED_SAND)
        info.setReturnValue(FOOD_PROPERTIES);
    }
  }
}
