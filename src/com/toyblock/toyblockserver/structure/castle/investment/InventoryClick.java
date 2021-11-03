package com.toyblock.toyblockserver.structure.castle.investment;

import com.toyblock.toyblockserver.structure.castle.vote.InvestmentNpc;
import com.toyblock.toyblockserver.structure.castle.vote.PathInvestment;
import com.toyblock.toyblockserver.structure.castle.vote.VoteItem;
import net.minecraft.world.inventory.InventoryClickType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class InventoryClick implements Listener {
    Player player = Bukkit.getPlayer("Devil");
    @EventHandler
    public void invClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inv = event.getInventory();
        ItemStack emerald = new ItemStack(Material.EMERALD);
        if (!PathInvestment.Inv_amount.containsKey(event.getInventory())) {
            player.chat("미등록");
            return;
        }
        if (event.getClickedInventory().getType().equals(InventoryType.PLAYER)) {
            event.setCancelled(true);
            player.chat("노플레이어");
            return;
        }
        if (event.getRawSlot() == 37) {
            event.setCancelled(true);
            player.chat("실행 딱 직전");
            removeItem_LageChest(player, emerald, 1);
            addInvestment(inv);
            player.chat("성공 한 거니?");
        }
        if (event.getRawSlot() == 50) {
            event.setCancelled(true);
            int pay =InvestmentPay(inv);
            returnInvestment(player,pay);
            inv.clear(41);
        }
    }

    public void removeItem_LageChest(Player player ,ItemStack find_item,int find_amount) {
        int amount;
        Inventory inv = player.getInventory();
        ArrayList<Integer> slot = new ArrayList<>();
        Material find_mate = find_item.getType();
        for (int i = 0;i<35;i++) {
            ItemStack item = inv.getItem(i);
            if(item == null) {
                player.chat("!");
                continue;
            }
            else if(!item.getType().equals(find_item.getType())) {
                player.chat("2");
                continue;

            }
            player.chat("3에메");
            amount = item.getAmount();


            if(!(amount >= find_amount)) {
                slot.add(i);

                player.chat("3");
                continue;
            }
            if(slot.isEmpty()) {

                inv.getItem(i).setAmount(amount-find_amount);
                player.chat("4");
                return ;
            }
            for (int ii : slot) {
                inv.clear(ii);
                player.chat("5");
            }

            if(find_amount-amount == 0) {
                inv.clear(i);
                player.chat("6");
                return ;

            }
            else if(find_amount-amount > 0) {
                inv.getItem(i).setAmount(amount-find_amount);
                player.chat("7");
                return ;
            }


        }

    }

    public void addInvestment(Inventory inv) {
        VoteItem item = new VoteItem();
        int box;
        player.chat("머지??");
        if(inv.getItem(41) == null) {
            player.chat("머지??왜안댐?");
            inv.setItem(41,item.investment_put());
            return;
        }
        box = inv.getItem(41).getAmount();
        if(!(inv.getItem(41) == null)) {
            player.chat("오잉?");
            inv.getItem(41).setAmount(box+1);
        }
        return;
    }
    public int InvestmentPay (Inventory inv ) {
        int pay = inv.getItem(41).getAmount();
        return pay;
    }
    public void returnInvestment (Player player , int pay) {
        int pay_64 = pay/64;
        int pay_min= pay%64;
        ItemStack emerald_64 = new ItemStack(Material.EMERALD,64);
        ItemStack emerald = new ItemStack(Material.EMERALD,pay_min);
        for(int i = 0 ; i <pay_64;i++) {
            player.getInventory().addItem(emerald_64);
        }
        player.getInventory().addItem(emerald);
    }

}
