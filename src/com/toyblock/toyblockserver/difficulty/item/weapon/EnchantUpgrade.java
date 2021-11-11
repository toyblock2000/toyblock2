package com.toyblock.toyblockserver.difficulty.item.weapon;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

public class EnchantUpgrade implements Listener {
    @EventHandler
    public void enchantEvent (EnchantItemEvent event) {
        WeaponLore lore = new WeaponLore();
        ItemStack item = event.getItem();
        lore.addEnchantLore(item);
        Bukkit.getPlayer("toy_block").chat("설정완료");
    }
    @EventHandler
    public void enchantclick(InventoryClickEvent event) {
        if(!(event.getClickedInventory().getType().equals(InventoryType.ENCHANTING))) {
            return;
        }
        if(event.getSlot() == 0) {
            WeaponLore lore = new WeaponLore();

            ItemStack item = event.getInventory().getItem(0);
            lore.addEnchantLore(item);
            event.getInventory().setItem(0,item);
        }
    }
}
