package com.toyblock.toyblockserver.quest;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class quest implements Listener {
    String copper_quest = ChatColor.GOLD+"구리퀘스트";
    String copper_quest_box = ChatColor.GOLD+"구리퀘스트 상자";
    public ItemStack quest_zombie_5 () {
        ItemStack paper = new ItemStack(Material.PAPER);
        ItemMeta meta = paper.getItemMeta();
        meta.setDisplayName(copper_quest);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("내용 : 좀비 5마리 잡기!");
        lore.add("리워드 : "+copper_quest_box);
        return paper;
    }
    ItemStack wall = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
    public ItemStack walls () {
        return wall;
    }
    ItemStack copper = new ItemStack(Material.COPPER_INGOT);
    public ItemStack copper() {
        return copper;
    }
    ItemStack copper_reward = new ItemStack(Material.ORANGE_SHULKER_BOX);
    public ItemStack copper_reward() {
        return copper_reward;
    }
    ItemStack iron = new ItemStack(Material.IRON_INGOT);
    public ItemStack iron() {
        return copper;
    }
    ItemStack iron_reward = new ItemStack(Material.WHITE_SHULKER_BOX);
    public ItemStack Iron_reward() {
        return iron_reward;
    }
    ItemStack gold = new ItemStack(Material.GOLD_INGOT);
    public ItemStack gold() {
        return copper;
    }
    ItemStack gold_reward = new ItemStack(Material.YELLOW_SHULKER_BOX);
    public ItemStack gold_reward() {
        return gold_reward;
    }


}
