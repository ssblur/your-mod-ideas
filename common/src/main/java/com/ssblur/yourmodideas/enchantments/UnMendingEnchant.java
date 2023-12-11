package com.ssblur.yourmodideas.enchantments;


import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class UnMendingEnchant extends Enchantment {
  static EquipmentSlot[] slots = new EquipmentSlot[] {
    EquipmentSlot.MAINHAND,
    EquipmentSlot.OFFHAND,
    EquipmentSlot.CHEST,
    EquipmentSlot.FEET,
    EquipmentSlot.HEAD,
    EquipmentSlot.LEGS
  };
  protected UnMendingEnchant() {
    super(Rarity.COMMON, EnchantmentCategory.BREAKABLE, slots);
  }

  public int getMinCost(int i) {
    return i * 5;
  }

  public int getMaxCost(int i) {
    return this.getMinCost(i) + 10;
  }

  public boolean isTreasureOnly() {
    return false;
  }

  @Override
  public boolean isCurse() {
    return true;
  }
}
