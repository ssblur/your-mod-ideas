package com.ssblur.yourmodideas.mixin;

import com.ssblur.yourmodideas.enchantments.YourModIdeasEnchantments;
import net.minecraft.client.renderer.entity.ExperienceOrbRenderer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(ExperienceOrb.class)
public class ExperienceOrbDamageUnMendingMixin {
  @Inject(
    method = "repairPlayerItems",
    at = @At(value = "HEAD"),
    cancellable = true
  )
  private void repairPlayerItems(Player player, int i, CallbackInfoReturnable<Integer> info) {
    int value = ((ExperienceOrb) (Object) this).getValue() * 2;
    Map.Entry<EquipmentSlot, ItemStack> entry = EnchantmentHelper.getRandomItemWith(YourModIdeasEnchantments.UNMENDING.get(), player);
    if (entry != null) {
      ItemStack itemStack = entry.getValue();
      itemStack.hurtAndBreak(value, player, entity -> entity.broadcastBreakEvent(entry.getKey()));
      int k = i - value;
      if (k > 0)
        repairPlayerItems(player, k, info);
      info.setReturnValue(0);
    }
  }
}
