package com.ssblur.obt.mixin;

import com.ssblur.obt.OBTGameRules;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemMixin {
  @Unique
  private static final FoodProperties FOOD_PROPERTIES = new FoodProperties.Builder().alwaysEdible().nutrition(1).saturationModifier(0).build();

  @Inject(
    method = "getDestroySpeed",
    at = @At("RETURN"),
    cancellable = true
  )
  private void getDestroySpeed$obt(ItemStack itemStack, BlockState blockState, CallbackInfoReturnable<Float> info) {
    if(OBTGameRules.TOOLS_SLOW_AS_THEY_BREAK_FLAG) {
      float speed = info.getReturnValue() / 4f;
      float percent = 1f - ((float) itemStack.getDamageValue()) / ((float) itemStack.getMaxDamage());
      info.setReturnValue(speed + speed * percent * 3f);
    }
  }

  @Inject(
    method = "inventoryTick",
    at = @At(value = "HEAD")
  )
  private void inventoryTick$obt(ItemStack itemStack, Level level, Entity entity, int i, boolean bl, CallbackInfo info) {
    Item self = (Item) (Object) this;
    if (self instanceof BlockItem blockItem) {
      Block block = blockItem.getBlock();
      if ((block == Blocks.SAND || block == Blocks.RED_SAND) && OBTGameRules.EAT_SAND_FLAG && !itemStack.has(DataComponents.FOOD))
        itemStack.set(DataComponents.FOOD, FOOD_PROPERTIES);
    }
  }
}
