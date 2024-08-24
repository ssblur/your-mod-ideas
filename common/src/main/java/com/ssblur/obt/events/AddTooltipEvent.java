package com.ssblur.obt.events;

import com.ssblur.obt.OBTGameRules;
import com.ssblur.obt.items.OBTItems;
import dev.architectury.event.events.client.ClientTooltipEvent;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class AddTooltipEvent implements ClientTooltipEvent.Item {
  @Override
  public void append(ItemStack stack, List<Component> lines, Item.TooltipContext tooltipContext, TooltipFlag flag) {
    var id = OBTItems.ITEMS.getRegistrar().getId(stack.getItem());
    if(id == null) return;
    var key = "itemDesc." + id.toShortLanguageKey();
    if(Minecraft.getInstance().level == null) return;
    boolean rule = OBTGameRules.getValue(Minecraft.getInstance().level, OBTGameRules.TOOLTIPS);
    if(rule && I18n.exists(key))
      lines.add(Component.translatable(key).withStyle(ChatFormatting.GOLD));
  }
}
