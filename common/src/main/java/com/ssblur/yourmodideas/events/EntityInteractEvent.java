package com.ssblur.yourmodideas.events;

import com.ssblur.yourmodideas.YourModIdeasGameRules;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.InteractionEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class EntityInteractEvent implements InteractionEvent.InteractEntity {
  @Override
  public EventResult interact(Player player, Entity entity, InteractionHand hand) {
    var level = player.level();
    if(level.getGameRules().getBoolean(YourModIdeasGameRules.MILK_EVERYTHING))
      if(player.getItemInHand(hand).is(Items.BUCKET)) {
        var item = new ItemStack(Items.MILK_BUCKET);
        item.setHoverName(Component.translatable("item.yourmodideas.milk", entity.getName()));
        player.setItemInHand(hand, item);
        return EventResult.interruptTrue();
      }
    return EventResult.pass();
  }
}
