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
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Objects;

public class AnvilIUpgrade implements Listener {
    //@EventHandler
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
    //@EventHandler
    public void enchant(PrepareAnvilEvent event) {
        if(event.getResult().getEnchantmentLevel(Enchantment.DAMAGE_ALL)>0) {
            WeaponLore weapon = new WeaponLore();
            ItemStack up = event.getResult();
            weapon.addEnchantLore(up);
            event.setResult(up);
        }

    }
    //@EventHandler
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
    String remitLevel_Str = "레벨 제한";
    String level_Str = "레벨";
    public void anvilUpgrade_OrderSheet(PrepareAnvilEvent event) {

        Inventory inv = event.getInventory();
        if(inv.getItem(0).equals(null)) {
            return;
        }
        if(inv.getItem(1).equals(null)) {
           return;
        }
        WeaponLore tool = new WeaponLore();
        ItemStack result = event.getResult();
        ItemStack item1 = inv.getItem(0);
        ItemStack item2 = inv.getItem(1);
        WoodenSword wood = new WoodenSword();
        if(loreFinder(item1,level_Str) == 0) {
            return;
        }
        if(loreFinder(item1,remitLevel_Str)== 10) {
            return;
        }
        int level = (int)loreFinder(item1,level_Str);
        if(wood.getWoodenSword( (level+1)).equals(null)) {
            return;
        }
        if(item2.)
        ItemStack upgradeResult = wood.getWoodenSword(level+1);


    }
    public void moveEnchant(ItemStack item , ItemStack item2) {
        item2.addEnchantments(item.getEnchantments());
    }
    public void moveCustomModelData(ItemStack item, ItemStack item2) {
        ItemMeta meta = item2.getItemMeta();
        meta.setCustomModelData(item.getItemMeta().getCustomModelData() );
        item2.setItemMeta(meta);
    }
    public float loreFinder(ItemStack item, String findStr) {
        if(!(item.getItemMeta().hasLore())) {
            return 0f;
        }
        List lore = item.getItemMeta().getLore();
        for(int i = 0;i<lore.size();i++){
            String str = (String)lore.get(i);
            if(!(str.contains(findStr))) {
                continue;
            }
            return Integer.parseInt(str.replaceAll("[^0-9]", ""));
        }
        return 0f;
    }
}


