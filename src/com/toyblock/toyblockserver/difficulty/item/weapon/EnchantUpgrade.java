package com.toyblock.toyblockserver.difficulty.item.weapon;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class EnchantUpgrade implements Listener {
    private InventoryClickEvent event;

    @EventHandler
    public void enchantEvent(EnchantItemEvent event) {
        WeaponLore lore = new WeaponLore();
        ItemStack item = event.getItem();
        lore.addEnchantLore(item);
    }

    @EventHandler
    public void enchantclick(InventoryClickEvent event) {
        if (!(event.getClickedInventory().getType().equals(InventoryType.ENCHANTING))) {
            return;
        }
        Tool_SwordEdit edit = new Tool_SwordEdit();
        edit.addEnchantLore(event.getCurrentItem());
    }
}
