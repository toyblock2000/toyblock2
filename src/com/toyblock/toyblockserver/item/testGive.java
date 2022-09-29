package com.toyblock.toyblockserver.item;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class testGive implements Listener {

    public void give(PlayerInteractEvent event) {
        if (event.getClickedBlock().getType().equals(Material.STONE)) {
            customItem item = new customItem();
            event.getPlayer().chat("기브");
            event.getPlayer().getInventory().addItem(item.ws_damageUp(0));
        }
    }
}