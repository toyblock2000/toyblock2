package com.toyblock.toyblockserver.difficulty.item.weapon;

import org.bukkit.Material;
import org.bukkit.Warning;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class WoodenSword {
    public ItemStack woodenSword_Lv1() {
        //공격력 2
        //공속 0.5
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        WeaponLore setting = new WeaponLore();
        setting.setAttribute(item,1,2,1.1);
        return item;
    }
    public ItemStack woodenSword_Lv2() {
        //2
        //0.6
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        WeaponLore setting = new WeaponLore();
        setting.setAttribute(item,2,2,1.2);
        return item;
    }
    public ItemStack woodenSword_Lv3() {
        //2.5
        //0.6
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        WeaponLore setting = new WeaponLore();
        setting.setAttribute(item,3,2.5,1.2);
        return item;
    }
    public ItemStack woodenSword_Lv4() {
        //2.5
        //0.7
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        WeaponLore setting = new WeaponLore();
        setting.setAttribute(item,4,2.5,1.3);
        return item;
    }
    public ItemStack woodenSword_Lv5() {
        //3
        //0.7
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        WeaponLore setting = new WeaponLore();
        setting.setAttribute(item,5,3,1.3);
        return item;
    }
    public ItemStack woodenSword_Lv6() { //특수능력 1
        //3
        //0.8
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        WeaponLore setting = new WeaponLore();
        setting.setAttribute(item,6,3,1.4);
        return item;
    }
    public ItemStack woodenSword_Lv7() {
        //3.5
        //0.8
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        WeaponLore setting = new WeaponLore();
        setting.setAttribute(item,7,3.5,1.4);
        return item;
    }
    public ItemStack woodenSword_Lv8() { //특수능력 2
        //3.5
        //0.9
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        WeaponLore setting = new WeaponLore();
        setting.setAttribute(item,8,3.5,1.5);
        return item;
    }
    public ItemStack woodenSword_Lv9() {
        //4
        //0.9
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        WeaponLore setting = new WeaponLore();
        setting.setAttribute(item,9,4,1.5);
        return item;
    }
    public ItemStack woodenSword_Lv10() { //특수능력 3
        //4
        //1
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        WeaponLore setting = new WeaponLore();
        setting.setAttribute(item,10,4,1.6);
        return item;
    }


}
