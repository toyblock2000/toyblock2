package com.toyblock.toyblockserver.structure.village.castle.vote;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.datafixers.DSL;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Base64;
import java.util.UUID;

public class VoteItem {
    public static ItemStack getSkull(String base64) {
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
        if (base64 == null || base64.isEmpty())
            return skull;
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", base64));
        Field profileField = null;
        try {
            profileField = skullMeta.getClass().getDeclaredField("profile");
        } catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        profileField.setAccessible(true);
        try {
            profileField.set(skullMeta, profile);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        skull.setItemMeta(skullMeta);
        return skull;
    }

    public ItemStack investment () {
        ItemStack investCoin = new ItemStack(Material.EMERALD);
        ItemMeta meta = investCoin.getItemMeta();
        meta.setDisplayName("모인 투자금");
        meta.addEnchant(Enchantment.DURABILITY,1,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        investCoin.setItemMeta(meta);
        return investCoin;
    }
    public ItemStack investment_put () {
        ItemStack investCoin = getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzFkODI3YTVkZWNiMGFlNzMwYWJiNjk2MTc3NzZlMTg5NGYyYmRiNDY5Njg1NDA0MzMxMTVkMzY4OGZiYWMzOCJ9fX0=");
        ItemMeta meta = investCoin.getItemMeta();
        meta.setDisplayName("내가 투자할 액수");
       // meta.addEnchant(Enchantment.DURABILITY,1,true);
        //meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        investCoin.setItemMeta(meta);
        return investCoin;
    }
    public ItemStack investment_put_null () {
        ItemStack investCoin = getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGNiM2FjZGMxMWNhNzQ3YmY3MTBlNTlmNGM4ZTliM2Q5NDlmZGQzNjRjNjg2OTgzMWNhODc4ZjA3NjNkMTc4NyJ9fX0=");
        ItemMeta meta = investCoin.getItemMeta();
        meta.setDisplayName("내가 투자할 액수 : 현재 0");
        // meta.addEnchant(Enchantment.DURABILITY,1,true);
        //meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        investCoin.setItemMeta(meta);
        return investCoin;
    }
    public ItemStack investmentGoal () {
        ItemStack investCoin = new ItemStack(Material.EMERALD);
        ItemMeta meta = investCoin.getItemMeta();
        meta.setDisplayName("목표 투자금");
        investCoin.setItemMeta(meta);
        return investCoin;
    }
    public ItemStack wall () {
        ItemStack investCoin = new ItemStack(Material.WHITE_STAINED_GLASS);
        ItemMeta meta = investCoin.getItemMeta();
        meta.setDisplayName(".");
        investCoin.setItemMeta(meta);
        return investCoin;
    }
    public ItemStack pathBlock_Level1 () {
        ItemStack investCoin = new ItemStack(Material.DIRT_PATH);
        ItemMeta meta = investCoin.getItemMeta();
        meta.setDisplayName("목표 : Level 1 / 길");
        meta.addEnchant(Enchantment.DURABILITY,1,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        investCoin.setItemMeta(meta);
        return investCoin;
    }
    public ItemStack investment_1give () {
        ItemStack investCoin = new ItemStack(Material.GREEN_WOOL);
        ItemMeta meta = investCoin.getItemMeta();
        meta.setDisplayName("투자금 1원 추가");
        investCoin.setItemMeta(meta);
        return investCoin;
    }
    public ItemStack investment_5give () {
        ItemStack investCoin = new ItemStack(Material.YELLOW_WOOL);
        ItemMeta meta = investCoin.getItemMeta();
        meta.setDisplayName("투자금 5원 추가");
        investCoin.setItemMeta(meta);
        return investCoin;
    }
    public ItemStack investment_10give () {
        ItemStack investCoin = new ItemStack(Material.RED_WOOL);
        ItemMeta meta = investCoin.getItemMeta();
        meta.setDisplayName("투자금 10원 추가");
        investCoin.setItemMeta(meta);
        return investCoin;
    }
    public ItemStack exit () {
        ItemStack investCoin = new ItemStack(Material.BOOK);
        ItemMeta meta = investCoin.getItemMeta();
        meta.setDisplayName("종료");
        investCoin.setItemMeta(meta);
        return investCoin;
    }
    public ItemStack investmentGoal_sign () {
        ItemStack investCoin = getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjViYTk3ODkwODdiMDEyMzA3ZGQ0N2RlMzhjNjYyMmQ3NWQ2YmIyMWViOTcwMTZjNDlhZmU5ZDcwMDQxOTkwOSJ9fX0=");
        ItemMeta meta = investCoin.getItemMeta();
        meta.setDisplayName("총 필요한 투자 금액");
        // meta.addEnchant(Enchantment.DURABILITY,1,true);
        //meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        investCoin.setItemMeta(meta);
        return investCoin;
    }
    public ItemStack investment_sign () {
        ItemStack investCoin = getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGJhNTU2NzFmOTdmZjNiZmM1YmUzMzVhZTkyY2Q5NzQ5YWJkNjE5ZTdhZmMyYTY2NzM1OTdiODBiNzU1Yzc0MSJ9fX0");
        ItemMeta meta = investCoin.getItemMeta();
        meta.setDisplayName("지금까지 모집한 투자 금액");
        // meta.addEnchant(Enchantment.DURABILITY,1,true);
        //meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        investCoin.setItemMeta(meta);
        return investCoin;
    }
    public ItemStack investment_up () {
        ItemStack investCoin = getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzA0MGZlODM2YTZjMmZiZDJjN2E5YzhlYzZiZTUxNzRmZGRmMWFjMjBmNTVlMzY2MTU2ZmE1ZjcxMmUxMCJ9fX0=");
        ItemMeta meta = investCoin.getItemMeta();
        meta.setDisplayName("금액 투자하기");
        // meta.addEnchant(Enchantment.DURABILITY,1,true);
        //meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        investCoin.setItemMeta(meta);
        return investCoin;
    }
    public ItemStack investment_down () {
        ItemStack investCoin = getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzQzNzM0NmQ4YmRhNzhkNTI1ZDE5ZjU0MGE5NWU0ZTc5ZGFlZGE3OTVjYmM1YTEzMjU2MjM2MzEyY2YifX19");
        ItemMeta meta = investCoin.getItemMeta();
        meta.setDisplayName("투자금 설정 되돌리기");
        // meta.addEnchant(Enchantment.DURABILITY,1,true);
        //meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        investCoin.setItemMeta(meta);
        return investCoin;
    }
    public ItemStack equalItem () {
        ItemStack investCoin = getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzg3Njg5ZjgzMzQzNmFhNzExYWJiZDQ1MTY4ODU2Nzc1YTJiMTE0NjU2Y2RmNGRjNWE2YzZmMWFmYWU1MjAifX19");
        ItemMeta meta = investCoin.getItemMeta();
        meta.setDisplayName(".");
        // meta.addEnchant(Enchantment.DURABILITY,1,true);
        //meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        investCoin.setItemMeta(meta);
        return investCoin;
    }
    public ItemStack logsItem () {
        ItemStack investCoin = getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODg4YzQ3MzllMjI4M2FhYTYyODE5NTk0ODZkMTQ5ZjMwNmI2ZTY3MjlhNmExYjNiZjljODUzYTIyYTkifX19");
        ItemMeta meta = investCoin.getItemMeta();
        meta.setDisplayName(".");
        // meta.addEnchant(Enchantment.DURABILITY,1,true);
        //meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        investCoin.setItemMeta(meta);
        return investCoin;
    }



}
