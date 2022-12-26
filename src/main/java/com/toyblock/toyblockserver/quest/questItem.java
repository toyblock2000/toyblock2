package com.toyblock.toyblockserver.quest;

import com.toyblock.toyblockserver.structure.village.castle.vote.InvestmentNpc;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.ShulkerBox;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class questItem implements Listener {

    String copper_quest_box = ChatColor.GOLD+"구리퀘스트 상자";
    public ItemStack quest_zombie_5 () {
        ItemStack paper = new ItemStack(Material.PAPER);
        ItemMeta meta = paper.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD+"좀비 5마리 잡기!");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("내용 : 좀비를 5마리 잡아보자");
        lore.add("보상 : "+copper_quest_box);
        meta.setLore(lore);
        paper.setItemMeta(meta);
        return paper;
    }
    public ItemStack quest_wood_5 () {
        ItemStack paper = new ItemStack(Material.PAPER);
        ItemMeta meta = paper.getItemMeta();

        ArrayList<String> lore = new ArrayList<String>();
        lore.add("내용 : 나무 5개 캐기!");
        lore.add("리워드 : "+copper_quest_box);
        meta.setLore(lore);
        paper.setItemMeta(meta);
        return paper;
    }
    public ItemStack quest_stone_5 () {
        ItemStack paper = new ItemStack(Material.PAPER);
        ItemMeta meta = paper.getItemMeta();

        ArrayList<String> lore = new ArrayList<String>();
        lore.add("내용 : 돌 5개 캐기!");
        lore.add("리워드 : "+copper_quest_box);
        meta.setLore(lore);
        paper.setItemMeta(meta);
        return paper;
    }
    ItemStack wall = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
    public ItemStack walls () {
        item_name(wall,"''");
        return wall;
    }
    ItemStack copper = new ItemStack(Material.COPPER_INGOT);
    public ItemStack copper() {
        item_name(copper,ChatColor.GOLD+"구리 등급");
        return copper;
    }
    ItemStack copper_reward = new ItemStack(Material.ORANGE_SHULKER_BOX);
    public ItemStack copper_reward() {
        item_name(copper_reward,ChatColor.GOLD+"구리퀘스트 보상 (아래중 랜덤 1개)");

        set_copper_box(copper_reward);
        return copper_reward;
    }
    ItemStack iron = new ItemStack(Material.IRON_INGOT);
    public ItemStack iron() {
        item_name(iron,ChatColor.WHITE+"철 등급");
        return iron;
    }
    ItemStack iron_reward = new ItemStack(Material.WHITE_SHULKER_BOX);
    public ItemStack iron_reward() {
        item_name(iron_reward,ChatColor.WHITE+"철퀘스트 보상 (아래중 랜덤 1개)");

        return iron_reward;
    }
    ItemStack gold = new ItemStack(Material.GOLD_INGOT);
    public ItemStack gold() {
        item_name(gold,ChatColor.YELLOW+"금 등급");
        return gold;
    }
    ItemStack gold_reward = new ItemStack(Material.YELLOW_SHULKER_BOX);
    public ItemStack gold_reward() {
        item_name(gold_reward,ChatColor.YELLOW+"금퀘스트 보상 (아래중 랜덤 1개)");

        return gold_reward;
    }
    public void item_name(ItemStack item,String name) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
    }
    public void item_lore(ItemStack item,String add) {
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(add);
        meta.setLore(lore);
        item.setItemMeta(meta);
    }
    ItemStack sword = new ItemStack(Material.STONE_SWORD);
    public void set_copper_box(ItemStack item) {
        BlockStateMeta bsm = (BlockStateMeta) item.getItemMeta();
        ShulkerBox box = (ShulkerBox) bsm.getBlockState();
        Inventory inv = box.getInventory();

        item_name(sword,"돌검");
        inv.setItem(0,sword);
        bsm.setBlockState(box);
        item.setItemMeta(bsm);
        box.update();
    }

}
