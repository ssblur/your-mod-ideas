package com.ssblur.yourmodideas.items;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class PlayerBow extends BowItem {
  public PlayerBow(Properties properties) {
    super(properties);
  }

  @Override
  public void releaseUsing(ItemStack itemStack, Level level, LivingEntity livingEntity, int i) {
    int j = this.getUseDuration(itemStack) - i;
    float f = BowItem.getPowerForTime(j);
    if (f < 0.1) return;
    var vec3 = livingEntity.getLookAngle().normalize().scale(2);
    livingEntity.push(vec3.x, vec3.y, vec3.z);
    level.playSound(
      null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(),
      SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0f, 1.0f / (level.getRandom().nextFloat() * 0.4f + 1.2f) + f * 0.5f
    );
  }

  @Override
  public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
    ItemStack itemStack = player.getItemInHand(interactionHand);
    player.startUsingItem(interactionHand);
    return InteractionResultHolder.consume(itemStack);
  }

  @Override
  public UseAnim getUseAnimation(ItemStack itemStack) {
    return UseAnim.BOW;
  }
}
