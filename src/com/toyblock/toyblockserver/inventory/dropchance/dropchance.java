package com.toyblock.toyblockserver.inventory.dropchance;

import locate.tool;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class dropchance {
    public int itemChance(ItemStack item) {
        String lore =item.getItemMeta().getLore().get(5);
        lore = lore.replaceAll("[^0-9]","");
        int chance = Integer.parseInt(lore);
        return chance;
    }
    public boolean chance(double probabilityTrue) {
        return Math.random()*100 >= 100 - probabilityTrue;
    }
    public void drop(Inventory inv) {
        int nomber =0;
        for (int i = 0;i<=35;i++) {
            int chance = itemChance(inv.getItem(i));
            if(chance(chance)) {
                inv.(i);
            }
        }
    }
    EventHandler
    public void death(PlayerDeathEvent event) {
        event.get
    }
}