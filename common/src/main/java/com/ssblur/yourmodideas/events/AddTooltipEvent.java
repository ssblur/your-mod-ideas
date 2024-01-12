package com.ssblur.yourmodideas.events;

import com.ssblur.yourmodideas.YourModIdeasGameRules;
import com.ssblur.yourmodideas.items.YourModIdeasItems;
import dev.architectury.event.events.client.ClientTooltipEvent;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class AddTooltipEvent implements ClientTooltipEvent.Item {
  @Override
  public void append(ItemStack stack, List<Component> lines, TooltipFlag flag) {
    var key = "itemDesc." + YourModIdeasItems.ITEMS.getRegistrar().getId(stack.getItem()).toShortLanguageKey();
    boolean rule = YourModIdeasGameRules.getValue(Minecraft.getInstance().level, YourModIdeasGameRules.TOOLTIPS);
    if(rule && I18n.exists(key))
      lines.add(Component.translatable(key).withStyle(ChatFormatting.GOLD));
  }
}
