package com.toyblock.toyblockserver.tool.developer;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class bug implements Listener {
    static HashMap<Player, Integer> debug = new HashMap<>();

    @EventHandler
    public void debugMode(PlayerInteractEvent event) {
        ItemStack hand = event.getPlayer().getInventory().getItemInMainHand();
        if ((hand.getItemMeta().getDisplayName().equals("디버그 모드"))||event.getPlayer().isOp()) {
            Player player = event.getPlayer();
            if (debug.containsKey(player)) {
                debug.remove(player);
                player.sendMessage("디버그 모드 해제");
            } else {
                debug.put(player, 1);
                player.sendMessage("디버그 모드");
            }
        }
    }
    @EventHandler
    public void debugPlayerOutServer(PlayerQuitEvent event) {
        if(bug.debug.containsKey(event.getPlayer())) {
            bug.debug.remove(event.getPlayer());
        }
    }
    public static void chat(String chat) {
        if(bug.debug.isEmpty()) {
            return;
        }
        for(Player player : bug.debug.keySet()) {
            player.sendMessage(chat);
        }
    }
}
