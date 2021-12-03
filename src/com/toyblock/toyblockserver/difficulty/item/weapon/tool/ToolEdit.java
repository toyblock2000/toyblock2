package com.toyblock.toyblockserver.difficulty.item.weapon.tool;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ToolEdit {
    public void setPickaxeLore(ItemStack item, int level, int remitLevel , double energy_discount) {
        List lore = new ArrayList();
        lore.add(" ");
        lore.add(ChatColor.WHITE+" 레벨  : "+ChatColor.LIGHT_PURPLE+level);
        lore.add(ChatColor.WHITE+" 레벨제한  : "+remitLevel);

        lore.add(ChatColor.WHITE+"에너지 소모 감소율: "+energy_discount);
        item.setLore(lore);
    }
}
