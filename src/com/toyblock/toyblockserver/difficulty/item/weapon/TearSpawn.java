package com.toyblock.toyblockserver.difficulty.item.weapon;

import org.bukkit.inventory.ItemStack;

public class TearSpawn {
    public ItemStack woodenTearSpawn(ItemStack item ,int tear) {
        WoodenSword sword = new WoodenSword();
        if(tear == 2) {
            return sword.woodenSword_Lv2();
        }
        if(tear == 3) {
            return sword.woodenSword_Lv3();
        }
        if(tear == 4) {
            return sword.woodenSword_Lv4();
        }
        if(tear == 5) {
            return sword.woodenSword_Lv5();
        }
        if(tear == 6) {
            return sword.woodenSword_Lv6();
        }
        if(tear == 7) {
            return sword.woodenSword_Lv7();
        }
        if(tear == 8) {
            return sword.woodenSword_Lv8();
        }
        if(tear == 9) {
           return sword.woodenSword_Lv9();
        }
        if(tear == 10) {
            return sword.woodenSword_Lv10();
        }
        return item;
    }
}
