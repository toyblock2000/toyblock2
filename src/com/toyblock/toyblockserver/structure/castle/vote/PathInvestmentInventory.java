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
    public void createInv (UUID UUID ,int goal) {
        VoteItem item = new VoteItem();
        Inventory inv = Bukkit.createInventory(null,54);
        inv.setItem(0,item.wall());
        inv.setItem(1,item.wall());
        inv.setItem(2,item.wall());
        inv.setItem(3,item.wall());
        inv.setItem(4,item.pathBlock_Level1());
        inv.setItem(5,item.wall());
        inv.setItem(6,item.wall());
        inv.setItem(7,item.wall());
        inv.setItem(8,item.wall());

        inv.setItem(9,item.investmentGoal_sign());
        inv.setItem(10,item.equalItem());
        inv.setItem(11,item.investmentGoal().add(64));
        inv.setItem(17,item.wall());

        inv.setItem(18,item.investment_sign());
        inv.setItem(19,item.equalItem());
        inv.setItem(20,item.investment());
        inv.setItem(26,item.wall());

        inv.setItem(27,item.wall());
        inv.setItem(28,item.wall());
        inv.setItem(29,item.wall());
        inv.setItem(30,item.wall());
        inv.setItem(31,item.wall());
        inv.setItem(32,item.investment_up());
        inv.setItem(33,item.wall());
        inv.setItem(34,item.wall());
        inv.setItem(35,item.wall());

        inv.setItem(36,item.wall());
        inv.setItem(37,item.investment_1give());
        inv.setItem(38,item.investment_5give());
        inv.setItem(39,item.investment_10give());
        inv.setItem(40,item.wall());
        //inv.setItem(41,item.investment_put_null());
        inv.setItem(42,item.wall());
        inv.setItem(43,item.wall());
        inv.setItem(44,item.wall());


        inv.setItem(45,item.wall());
        inv.setItem(46,item.wall());
        inv.setItem(47,item.wall());
        inv.setItem(48,item.wall());
        inv.setItem(49,item.wall());
        inv.setItem(50,item.investment_down());
        inv.setItem(51,item.wall());
        inv.setItem(52,item.wall());
        inv.setItem(53,item.exit());

        if(!setInvestmentGoal(inv,goal)) {
            return;
        }
        PathInvestment.Inv.put(UUID,inv); //인벤등록
        PathInvestment.Inv_amount.put(inv,goal); //투자금 등록
    }
    public boolean setInvestmentGoal (Inventory inv, int goal) {
        int fullGoal = 64*6; //384
        if (goal>fullGoal) {
            return false;
        }
        VoteItem item = new VoteItem();

        int max = goal/64;
        int min = goal%64;
        int start = 11;
        for (int i = 1; i <= max ; i++) {
            inv.setItem(start,item.investmentGoal().add(64));
            start ++;
        }
        inv.setItem(start,item.investmentGoal().add(min));
        return true;
    }

}
