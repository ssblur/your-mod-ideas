package com.ssblur.obt.goals;

import com.ssblur.obt.OBTGameRules;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.EnumSet;
import java.util.List;
import java.util.Random;

public class FoxStealItemsGoal extends Goal {
  Fox fox;
  Random random = new Random();

  public FoxStealItemsGoal(Fox fox) {
    this.fox = fox;
    this.setFlags(EnumSet.of(Flag.MOVE));
  }

  // Only active when it can steal an item, otherwise it will default to the AvoidEntity goal.
  @Override
  public boolean canUse() {
    return fox.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty();
  }

  public void tick() {
    if(!fox.level().getGameRules().getBoolean(OBTGameRules.EVIL_FOXES)) return;
    List<Player> list = fox.level().getEntitiesOfClass(Player.class, fox.getBoundingBox().inflate(8.0, 8.0, 8.0));
    ItemStack itemStack = fox.getItemBySlot(EquipmentSlot.MAINHAND);
    if (itemStack.isEmpty() && !list.isEmpty()) {
      var player = list.get(0);
      if(fox.distanceTo(player) < 1f) {
        var inventory = player.getInventory().items;
        int slot = random.nextInt(inventory.size());
        var item = inventory.get(slot);
        inventory.set(slot, ItemStack.EMPTY);

        fox.setItemSlot(EquipmentSlot.MAINHAND, item);
      } else {
        fox.getNavigation().moveTo(player, 1.2000000476837158);
      }
    }
  }

  public void start() {
    List<Player> list = fox.level().getEntitiesOfClass(Player.class, fox.getBoundingBox().inflate(8.0, 8.0, 8.0));
    if (!list.isEmpty()) {
      fox.getNavigation().moveTo(list.get(0), 1.2000000476837158);
    }

  }
}
