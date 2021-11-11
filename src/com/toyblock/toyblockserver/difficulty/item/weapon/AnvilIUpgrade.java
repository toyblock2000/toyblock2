package com.toyblock.toyblockserver.difficulty.item.weapon;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;

public class AnvilIUpgrade implements Listener {
    @EventHandler
    public void upgrade(PrepareAnvilEvent event) {
        if(!(event.getInventory().getItem(1).getItemMeta().getDisplayName().equals("업그레이드"))) {
            return;
        }
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

}
