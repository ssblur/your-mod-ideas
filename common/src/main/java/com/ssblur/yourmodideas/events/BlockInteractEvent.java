package com.ssblur.yourmodideas.events;

import com.ssblur.yourmodideas.YourModIdeasGameRules;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.InteractionEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.BedBlock;

public class BlockInteractEvent implements InteractionEvent.RightClickBlock {
  @Override
  public EventResult click(Player player, InteractionHand hand, BlockPos pos, Direction face) {
    var level = player.level();
    if(YourModIdeasGameRules.getValue(level, YourModIdeasGameRules.INSOMNIA))
      if(level.getBlockState(pos).getBlock() instanceof BedBlock) {
        player.displayClientMessage(Component.translatable("message.yourmodideas.insomnia"), true);
        return EventResult.interruptFalse();
      }
    return EventResult.pass();
  }
}
