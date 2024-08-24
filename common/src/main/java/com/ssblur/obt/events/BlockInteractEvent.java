package com.ssblur.obt.events;

import com.ssblur.obt.OBTGameRules;
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
    if(OBTGameRules.getValue(level, OBTGameRules.INSOMNIA))
      if(level.getBlockState(pos).getBlock() instanceof BedBlock) {
        player.displayClientMessage(Component.translatable("message.obt.insomnia"), true);
        return EventResult.interruptFalse();
      }
    return EventResult.pass();
  }
}
