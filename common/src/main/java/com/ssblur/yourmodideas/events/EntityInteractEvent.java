package com.ssblur.yourmodideas.events;

import com.ssblur.yourmodideas.YourModIdeasGameRules;
import com.ssblur.yourmodideas.items.YourModIdeasItems;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.InteractionEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class EntityInteractEvent implements InteractionEvent.InteractEntity {
  @Override
  public EventResult interact(Player player, Entity entity, InteractionHand hand) {
    var level = player.level();
    if(level.getGameRules().getBoolean(YourModIdeasGameRules.BUCKET_SAND))
      if(player.getItemInHand(hand).is(Items.BUCKET) && entity instanceof FallingBlockEntity fallingBlock) {
        var item = new ItemStack(YourModIdeasItems.BUCKETED_BLOCK.get());
        entity.save(item.getOrCreateTag());
        item.setHoverName(Component.translatable("item.yourmodideas.bucketed_block", fallingBlock.getBlockState().getBlock().getName()));

        if(!player.getInventory().add(item)) {
          level.addFreshEntity(item.getEntityRepresentation());
        }
        player.getItemInHand(hand).shrink(1);

        entity.remove(Entity.RemovalReason.DISCARDED);
        player.getCooldowns().addCooldown(YourModIdeasItems.BUCKETED_BLOCK.get(), 1);
        return EventResult.interruptTrue();
      }
    if(level.getGameRules().getBoolean(YourModIdeasGameRules.MILK_EVERYTHING))
      if(player.getItemInHand(hand).is(Items.BUCKET)) {
        var item = new ItemStack(Items.MILK_BUCKET);
        item.setHoverName(Component.translatable("item.yourmodideas.milk", entity.getName()));
        if(!player.getInventory().add(item)) {
          level.addFreshEntity(item.getEntityRepresentation());
        }
        player.getItemInHand(hand).shrink(1);
        return EventResult.interruptTrue();
      }
    return EventResult.pass();
  }
}
