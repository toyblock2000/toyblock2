package com.toyblock.toyblockserver.buildframe;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class FrameItem {
    public ItemStack save() {
        ItemStack item = new ItemStack(Material.NETHER_STAR);
        item.getItemMeta().setDisplayName("Save");
        return item;
    }
    public ItemStack save2x2() {
        ItemStack item = new ItemStack(Material.NETHER_STAR);
        item.getItemMeta().setDisplayName("Save2x2");
        return item;
    }
}
