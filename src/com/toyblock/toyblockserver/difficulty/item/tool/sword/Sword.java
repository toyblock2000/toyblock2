package com.toyblock.toyblockserver.difficulty.item.tool.sword;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Sword {
    Material wooden = Material.WOODEN_SWORD;
    Material stone = Material.STONE_SWORD;
    public ItemStack get(ItemStack material , int level) {

        Material mate = material.getType();
        if(mate == wooden) {
            WoodenSword item = new WoodenSword();
            return item.get(level);
        }
        if(mate == stone) {
            StoneSword item = new StoneSword();
            return item.get(level);
        }
        return material;

    }
}
