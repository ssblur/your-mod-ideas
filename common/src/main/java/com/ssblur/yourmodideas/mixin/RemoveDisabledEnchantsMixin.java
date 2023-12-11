package com.ssblur.yourmodideas.mixin;

import com.ssblur.yourmodideas.YourModIdeasGameRules;
import com.ssblur.yourmodideas.enchantments.UnMendingEnchant;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(EnchantmentHelper.class)
public class RemoveDisabledEnchantsMixin {
  @Inject(
    method = "getAvailableEnchantmentResults",
    at = @At(value = "RETURN"),
    cancellable = true
  )
  private static void getAvailableEnchantmentResults(int i, ItemStack itemStack, boolean bl, CallbackInfoReturnable<List<EnchantmentInstance>> info) {
    if(!YourModIdeasGameRules.UNMENDING_ENCHANT_FLAG) {
      var result = info.getReturnValue();
      result.removeIf(enchantment -> enchantment.enchantment instanceof UnMendingEnchant);
      info.setReturnValue(result);
    }
  }
}
