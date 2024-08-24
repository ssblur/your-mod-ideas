package com.ssblur.obt.mixin;

import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.stream.Stream;

@Mixin(EnchantmentHelper.class)
public class RemoveDisabledEnchantsMixin {
  @Inject(
    method = "getAvailableEnchantmentResults",
    at = @At(value = "RETURN")
  )
  private static void getAvailableEnchantmentResults(int i, ItemStack itemStack, Stream<Holder<Enchantment>> stream, CallbackInfoReturnable<List<EnchantmentInstance>> info) {

  }
}
