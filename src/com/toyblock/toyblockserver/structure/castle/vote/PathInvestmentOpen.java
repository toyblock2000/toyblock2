package com.toyblock.toyblockserver.structure.castle.vote;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

public class PathInvestmentOpen implements Listener {

    @EventHandler
    public void InvestmentOpen (PlayerInteractEvent event) {

    }
    public void openInv (Player player , UUID UUID) {
        player.openInventory(PathInvestment.Inv.get(UUID));
    }
}
