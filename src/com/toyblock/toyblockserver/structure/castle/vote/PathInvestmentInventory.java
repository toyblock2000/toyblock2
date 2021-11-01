package com.toyblock.toyblockserver.structure.castle.vote;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.UUID;

public class PathInvestmentInventory implements Listener {

    public void start_createInv (UUID UUID) {
        Inventory inv = Bukkit.createInventory(null,54);
        ItemStack investCoin = new ItemStack(Material.EMERALD);
        ItemMeta meta = investCoin.getItemMeta();
        meta.setLocalizedName("모인 투자금");
        meta.addEnchant(Enchantment.DURABILITY,1,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        investCoin.setItemMeta(meta);
        int coin = PathInvestment.InvestmentInv.get(UUID);
        int full = (coin/64);
        int etc = coin&64;
        if(coin <= 64) {
            investCoin.setAmount(coin);
            inv.setItem(19,investCoin);
        }
        else {
            int count = 19;
            for(int a = 0; a <= full;a++) {
                investCoin.setAmount(64);
                inv.setItem(count,investCoin);
                count ++;
            }
        }

    }
    public void createInv (UUID UUID) {
        Inventory inv = Bukkit.createInventory(null,54);
        ItemStack investCoin = new ItemStack(Material.EMERALD);
        ItemMeta meta = investCoin.getItemMeta();
        meta.setLocalizedName("모인 투자금");
        meta.addEnchant(Enchantment.DURABILITY,1,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        investCoin.setItemMeta(meta);

        inv.setItem(20,investCoin);
        }
    }

}
