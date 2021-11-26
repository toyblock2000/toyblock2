package com.toyblock.toyblockserver.structure.village.castle.item;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.swing.text.JTextComponent;
import java.util.ArrayList;

public class Structure {
    public void giveItem(Player player) {
        ItemStack pathMaker = new ItemStack(Material.DIRT_PATH);
        ItemMeta meta = pathMaker.getItemMeta();
        ArrayList<String> loreList = new ArrayList<>();
        loreList.add(0,"길 건설");
        meta.setLore(loreList);
    }

}
