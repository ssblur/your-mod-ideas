package com.ssblur.yourmodideas.mixin;

import com.ssblur.yourmodideas.YourModIdeasGameRules;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DiggerItem.class)
public class DiggerItemMixin {
  @Inject(
    method = "getDestroySpeed",
    at = @At("RETURN"),
    cancellable = true
  )
  private void getDestroySpeed(ItemStack itemStack, BlockState blockState, CallbackInfoReturnable<Float> info) {
    if(YourModIdeasGameRules.TOOLS_SLOW_AS_THEY_BREAK_FLAG) {
      float speed = info.getReturnValue() / 4f;
      float percent = 1f - ((float) itemStack.getDamageValue()) / ((float) itemStack.getMaxDamage());
      info.setReturnValue(speed + speed * percent * 3f);
    }
  }
}
