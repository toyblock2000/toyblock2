package com.toyblock.toyblockserver.inventory.dropchance;

import locate.tool;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class dropchance {
    public void itemChance(ItemStack item) {
        String lore =item.getItemMeta().getLore().get(5);
        lore = lore.replaceAll("[^0-9]","");
        int chance = Integer.parseInt(lore);
    }
    }