package com.ssblur.obt.events;

import com.ssblur.obt.OBTGameRules;
import com.ssblur.obt.data.OBTDataComponents;
import com.ssblur.obt.items.OBTItems;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.InteractionEvent;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.Random;

public class EntityInteractEvent implements InteractionEvent.InteractEntity {
  Random random = new Random();
  @Override
  public EventResult interact(Player player, Entity entity, InteractionHand hand) {
    var level = player.level();
    if(OBTGameRules.getValue(level, OBTGameRules.NO_MILKABLE))
      if(player.getItemInHand(hand).is(Items.BUCKET))
        if(OBTGameRules.getValue(level, OBTGameRules.MILK_EVERYTHING) || entity instanceof Cow) {
          player.displayClientMessage(Component.translatable("message.obt.no_milk_" + random.nextInt(3)), true);
          return EventResult.interruptTrue();
        }

    if(OBTGameRules.getValue(level, OBTGameRules.BUCKET_SAND))
      if(player.getItemInHand(hand).is(Items.BUCKET) && entity instanceof FallingBlockEntity fallingBlock) {
        var item = new ItemStack(OBTItems.BUCKETED_BLOCK.get());
        CompoundTag tag = new CompoundTag();
        entity.save(tag);
        item.set(OBTDataComponents.TAG, tag);
        item.set(DataComponents.ITEM_NAME, Component.translatable("item.obt.bucketed_block", fallingBlock.getBlockState().getBlock().getName()));

        if(!player.getInventory().add(item)) {
          var itemEntity = item.getEntityRepresentation();
          if(itemEntity != null)
            level.addFreshEntity(itemEntity);
        }
        player.getItemInHand(hand).shrink(1);

        entity.remove(Entity.RemovalReason.DISCARDED);
        player.getCooldowns().addCooldown(OBTItems.BUCKETED_BLOCK.get(), 1);
        return EventResult.interruptTrue();
      }

    if(OBTGameRules.getValue(level, OBTGameRules.MILK_EVERYTHING))
      if(player.getItemInHand(hand).is(Items.BUCKET)) {
        var item = new ItemStack(Items.MILK_BUCKET);
        item.set(DataComponents.ITEM_NAME, Component.translatable("item.obt.milk", entity.getName()));
        if(!player.getInventory().add(item)) {
          var itemEntity = item.getEntityRepresentation();
          if(itemEntity != null)
            level.addFreshEntity(itemEntity);
        }
        player.getItemInHand(hand).shrink(1);
        return EventResult.interruptTrue();
      }
    return EventResult.pass();
  }
}
