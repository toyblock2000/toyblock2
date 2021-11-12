package com.toyblock.toyblockserver.difficulty.item.weapon;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class AnvilIUpgrade implements Listener {
    @EventHandler
    public void upgrade(PrepareAnvilEvent event) {
        if(!(event.getInventory().getItem(1).getItemMeta().getDisplayName().equals("업그레이드"))) {
            return;
        }
        Inventory inv = event.getInventory();
        WeaponLore weapon = new WeaponLore();
        ItemStack up = weapon.upgradeTear(event.getResult());
        weapon.addEnchantLore(up);
        event.setResult(up);
    }
    @EventHandler
    public void enchant(PrepareAnvilEvent event) {
        if(event.getResult().getEnchantmentLevel(Enchantment.DAMAGE_ALL)>0) {
            WeaponLore weapon = new WeaponLore();
            ItemStack up = event.getResult();
            weapon.addEnchantLore(up);
            event.setResult(up);
        }

    }
    @EventHandler
    public void anvilupgrade(PrepareAnvilEvent event) {

        Inventory inv = event.getInventory();
        WeaponLore weapon = new WeaponLore();
        if (weapon.getTear(inv.getItem(0)) < weapon.getTear(inv.getItem(1))) {
            ItemStack newitem = weapon.setUpgradeTear(inv.getItem(1), inv.getItem(2));
            weapon.addEnchantLore(newitem);
            event.setResult(newitem);
            return;
        }
    }
}


