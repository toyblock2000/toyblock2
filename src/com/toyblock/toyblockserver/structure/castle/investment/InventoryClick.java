package com.toyblock.toyblockserver.structure.castle.investment;

import com.toyblock.toyblockserver.structure.castle.vote.InvestmentNpc;
import com.toyblock.toyblockserver.structure.castle.vote.PathInvestment;
import com.toyblock.toyblockserver.structure.castle.vote.VoteItem;
import net.minecraft.world.inventory.InventoryClickType;
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
    @EventHandler
    public void invClick(InventoryClickEvent event) {
        Player player = (Player)event.getWhoClicked();
        Inventory inv = event.getInventory();
        ItemStack emerald = new ItemStack(Material.EMERALD);
        if(!PathInvestment.Inv.containsKey(event.getInventory())) {
            return;
        }
        if(event.getClickedInventory().getType().equals(InventoryType.PLAYER)) {
            event.setCancelled(true);
            return;
        }
        if(event.getRawSlot()==37) {
            event.setCancelled(true);
            if(!removeItem_LageChest(inv,emerald,1)) {
                return;
            }
        }

    }
    public boolean removeItem_LageChest(Inventory inv ,ItemStack find_item,int find_amount) {
        int amount;
        ArrayList<Integer> slot = new ArrayList<>();
        for (int i = 54;i<89;i++) {
            ItemStack item = inv.getItem(i);
            if(item.equals(find_item)) {
                amount = item.getAmount();
                if(amount >= find_amount) {
                    for(int i1 = 0;i1 < slot.size();i1++) {
                        int a = slot.get(i1);
                        inv.clear(a);
                    }
                    if(find_amount-amount == 0) {
                        inv.clear(i);

                    }
                    else {
                        inv.getItem(i).setAmount(find_amount-amount);

                    }
                    return true;
                }
                slot.add(i);

            }
        }
        return false;

    }
    public void addInvestment(Inventory inv) {
        VoteItem item = new VoteItem();
        int box = inv.getItem(41).getAmount();
        if(box==0) {
            inv.setItem(41,item.investment_put());
        }
        else {
            inv.getItem(41).setAmount(box+1);
        }
        return;
    }

}
