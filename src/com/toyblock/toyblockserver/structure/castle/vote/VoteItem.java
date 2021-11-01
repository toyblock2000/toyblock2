package com.toyblock.toyblockserver.structure.castle.vote;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.UUID;

public class VoteItem {
    public ItemStack getPlayerHead (UUID uuid) {
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        skullMeta.cu
        skullMeta.setOwningPlayer(Bukkit.getOfflinePlayer(uuid);
        skull.setItemMeta(skullMeta);
        return skull;
    }
    public ItemStack investment () {
        ItemStack investCoin = new ItemStack(Material.EMERALD);
        ItemMeta meta = investCoin.getItemMeta();
        meta.setLocalizedName("모인 투자금");
        meta.addEnchant(Enchantment.DURABILITY,1,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        investCoin.setItemMeta(meta);
        return investCoin;
    }
    public ItemStack investment_put () {
        ItemStack investCoin = new ItemStack(Material.EMERALD);
        ItemMeta meta = investCoin.getItemMeta();
        meta.setLocalizedName("내가 투자할 액수");
        meta.addEnchant(Enchantment.DURABILITY,1,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        investCoin.setItemMeta(meta);
        return investCoin;
    }
    public ItemStack investmentGoal () {
        ItemStack investCoin = new ItemStack(Material.EMERALD);
        ItemMeta meta = investCoin.getItemMeta();
        meta.setLocalizedName("목표 투자금");
        investCoin.setItemMeta(meta);
        return investCoin;
    }
    public ItemStack wall () {
        ItemStack investCoin = new ItemStack(Material.WHITE_STAINED_GLASS);
        ItemMeta meta = investCoin.getItemMeta();
        meta.setLocalizedName("");
        investCoin.setItemMeta(meta);
        return investCoin;
    }
    public ItemStack pathBlock_Level1 () {
        ItemStack investCoin = new ItemStack(Material.DIRT_PATH);
        ItemMeta meta = investCoin.getItemMeta();
        meta.setLocalizedName("목표 : Level 1 / 길");
        meta.addEnchant(Enchantment.DURABILITY,1,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        investCoin.setItemMeta(meta);
        return investCoin;
    }
    public ItemStack investment_1give () {
        ItemStack investCoin = new ItemStack(Material.GREEN_WOOL);
        ItemMeta meta = investCoin.getItemMeta();
        meta.setLocalizedName("투자금 1원 추가");
        investCoin.setItemMeta(meta);
        return investCoin;
    }
    public ItemStack investment_5give () {
        ItemStack investCoin = new ItemStack(Material.YELLOW_WOOL);
        ItemMeta meta = investCoin.getItemMeta();
        meta.setLocalizedName("투자금 5원 추가");
        investCoin.setItemMeta(meta);
        return investCoin;
    }
    public ItemStack investment_10give () {
        ItemStack investCoin = new ItemStack(Material.RED_WOOL);
        ItemMeta meta = investCoin.getItemMeta();
        meta.setLocalizedName("투자금 10원 추가");
        investCoin.setItemMeta(meta);
        return investCoin;
    }
    public ItemStack exit () {
        ItemStack investCoin = new ItemStack(Material.BOOK);
        ItemMeta meta = investCoin.getItemMeta();
        meta.setLocalizedName("종료");
        investCoin.setItemMeta(meta);
        return investCoin;
    }
    public ItemStack investmentGoalSign() {
        ItemStack investCoin = new ItemStack(Material.BOOK);
        ItemMeta meta = investCoin.getItemMeta();
        meta.setLocalizedName("종료");
        investCoin.setItemMeta(meta);
        return investCoin;
    }
    public ItemStack investmentSign() {
        ItemStack investCoin = new ItemStack(Material.BOOK);
        ItemMeta meta = investCoin.getItemMeta();
        meta.setLocalizedName("종료");
        investCoin.setItemMeta(meta);
        return investCoin;
    }



}
