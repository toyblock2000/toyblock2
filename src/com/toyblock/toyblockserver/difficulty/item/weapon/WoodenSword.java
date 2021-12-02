package com.toyblock.toyblockserver.difficulty.item.weapon;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Warning;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class WoodenSword {
    public ItemStack getWoodenSword(int level) {
        if(level == 1) {
            return woodenSword_Lv1();
        }
        if(level == 2) {
            return woodenSword_Lv2();
        }
        if(level == 3) {
            return woodenSword_Lv3();
        }
        if(level == 4) {
            return woodenSword_Lv4();
        }
        if(level == 5) {
            return woodenSword_Lv5();
        }
        if(level == 6) {
            return woodenSword_Lv6();
        }
        if(level == 7) {
            return woodenSword_Lv7();
        }
        if(level == 8) {
            return woodenSword_Lv8();
        }
        if(level == 9) {
            return woodenSword_Lv9();
        }
        if(level == 10) {
            return woodenSword_Lv10();
        }
        if(level == 11) {
            return woodenSword_Lv10();
        }
        return null;
    }
    public static ItemStack woodenUpgrade() {
        ItemStack item = new ItemStack(Material.MAGENTA_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.LIGHT_PURPLE+"강화!! (확률 30%)");
        item.setItemMeta(meta);
        return item;
    }
    public ItemStack woodenSword_Lv1() {
        //공격력 2
        //공속 0.5
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        WeaponLore setting = new WeaponLore();
        setting.setAttribute(item,1,10,2,1.1);
        return item;
    }
    public ItemStack woodenSword_Lv2() {
        //2
        //0.6
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        WeaponLore setting = new WeaponLore();
        setting.setAttribute(item,2,10,2,1.2);
        return item;
    }
    public ItemStack woodenSword_Lv3() {
        //2.5
        //0.6
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        WeaponLore setting = new WeaponLore();
        setting.setAttribute(item,3,10,2.5,1.2);
        return item;
    }
    public ItemStack woodenSword_Lv4() {
        //2.5
        //0.7
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        WeaponLore setting = new WeaponLore();
        setting.setAttribute(item,4,10,2.5,1.3);
        return item;
    }
    public ItemStack woodenSword_Lv5() {
        //3
        //0.7
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        WeaponLore setting = new WeaponLore();
        setting.setAttribute(item,5,10,3,1.3);
        return item;
    }
    public ItemStack woodenSword_Lv6() { //특수능력 1
        //3
        //0.8
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        WeaponLore setting = new WeaponLore();
        setting.setAttribute(item,6,10,3,1.4);
        return item;
    }
    public ItemStack woodenSword_Lv7() {
        //3.5
        //0.8
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        WeaponLore setting = new WeaponLore();
        setting.setAttribute(item,7,10,3.5,1.4);
        return item;
    }
    public ItemStack woodenSword_Lv8() { //특수능력 2
        //3.5
        //0.9
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        WeaponLore setting = new WeaponLore();
        setting.setAttribute(item,8,10,3.5,1.5);
        return item;
    }
    public ItemStack woodenSword_Lv9() {
        //4
        //0.9
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        WeaponLore setting = new WeaponLore();
        setting.setAttribute(item,9,10,4,1.5);
        return item;
    }
    public ItemStack woodenSword_Lv10() { //특수능력 3
        //4
        //1
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        WeaponLore setting = new WeaponLore();
        setting.setAttribute(item,10,10,4,1.6);
        return item;
    }


}
