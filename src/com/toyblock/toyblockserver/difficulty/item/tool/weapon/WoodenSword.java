package com.toyblock.toyblockserver.difficulty.item.tool.weapon;

import com.toyblock.toyblockserver.difficulty.item.WeaponLore;
import com.toyblock.toyblockserver.difficulty.item.tool.ToolEdit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.math.BigDecimal;

public class WoodenSword {
    public double getDamage(int level) {
        double nomalDamage = 4; //기본
        double damage = 0.1;    //레벨당
        double sum = damage*(10-level);
        double value = nomalDamage-sum;
        return value;
    }
    public double getAttackSpeed(int level) {
        double nomalSpeed = 1.6; //기본
        double speed = 0.05;      //레벨당
        double sum = speed*(10-level);
        double value = nomalSpeed-sum;
        return value;
    }
    public double getSoulBound(int level) {
        double nomalSoulBound = 50; //기본
        double soulBound = 0.5;      //레벨당
        double sum = soulBound*(10-level);
        double value = nomalSoulBound-sum;
        return value;
    }
    //4,1.6
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
        meta.setDisplayName(ChatColor.LIGHT_PURPLE+"나무검 강화");
        item.setItemMeta(meta);
        return item;
    }
    public ItemStack woodenSword_Lv1() {

        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        ToolEdit setting = new ToolEdit();
        int level = 1;
        setting.setAttribute(item,level,10,getDamage(level),getAttackSpeed(level),getSoulBound(level));
        return item;
    }
    public ItemStack woodenSword_Lv2() {
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        ToolEdit setting = new ToolEdit();
        int level = 2;
        setting.setAttribute(item,level,10,getDamage(level),getAttackSpeed(level),getSoulBound(level));
        return item;
    }
    public ItemStack woodenSword_Lv3() {

        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        ToolEdit setting = new ToolEdit();
        int level = 3;
        setting.setAttribute(item,level,10,getDamage(level),getAttackSpeed(level),getSoulBound(level));
        return item;
    }
    public ItemStack woodenSword_Lv4() {

        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        ToolEdit setting = new ToolEdit();
        int level = 4;
        setting.setAttribute(item,level,10,getDamage(level),getAttackSpeed(level),getSoulBound(level));
        return item;
    }
    public ItemStack woodenSword_Lv5() {

        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        ToolEdit setting = new ToolEdit();
        int level = 5;
        setting.setAttribute(item,level,10,getDamage(level),getAttackSpeed(level),getSoulBound(level));
        return item;
    }
    public ItemStack woodenSword_Lv6() { //특수능력 1

        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        ToolEdit setting = new ToolEdit();
        int level = 6;
        setting.setAttribute(item,level,10,getDamage(level),getAttackSpeed(level),getSoulBound(level));
        return item;
    }
    public ItemStack woodenSword_Lv7() {

        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        ToolEdit setting = new ToolEdit();
        int level = 7;
        setting.setAttribute(item,level,10,getDamage(level),getAttackSpeed(level),getSoulBound(level));
        return item;
    }
    public ItemStack woodenSword_Lv8() { //특수능력 2

        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        ToolEdit setting = new ToolEdit();
        int level = 8;
        setting.setAttribute(item,level,10,getDamage(level),getAttackSpeed(level),getSoulBound(level));
        return item;
    }
    public ItemStack woodenSword_Lv9() {

        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        ToolEdit setting = new ToolEdit();
        int level = 9;
        setting.setAttribute(item,level,10,getDamage(level),getAttackSpeed(level),getSoulBound(level));
        return item;
    }
    public ItemStack woodenSword_Lv10() { //특수능력 3

        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        ToolEdit setting = new ToolEdit();
        int level = 10;
        setting.setAttribute(item,level,10,getDamage(level),getAttackSpeed(level),getSoulBound(level));
        return item;
    }
    public ItemStack upSword_Lv1() { //특수능력 3

        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        ToolEdit setting = new ToolEdit();
        int level = 1;
        setting.setAttribute(item,level,10,getDamage(level),getAttackSpeed(level),getSoulBound(level));
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(1);
        item.setItemMeta(meta);
        return item;
    }


}
