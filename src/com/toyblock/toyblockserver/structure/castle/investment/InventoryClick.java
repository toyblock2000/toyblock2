package com.toyblock.toyblockserver.structure.castle.investment;

import com.toyblock.toyblockserver.structure.castle.vote.InvestmentNpc;
import com.toyblock.toyblockserver.structure.castle.vote.PathInvestment;
import net.minecraft.world.inventory.InventoryClickType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryClick implements Listener {
    @EventHandler
    public void invClick(InventoryClickEvent event) {
        Player player = (Player)event.getWhoClicked();
        if(!PathInvestment.Inv.containsKey(event.getInventory())) {
            return;
        }
        if(event.getClickedInventory().getType().equals(InventoryType.PLAYER)) {
            event.setCancelled(true);
            return;
        }
        if(event.getRawSlot()==37) {
            event.setCancelled(true);
            player.getInventory().get


        }

    }
    public int invFound_LageChest(Inventory inv ,ItemStack found) {
        for (int i = 54;i<89)

    }

}
